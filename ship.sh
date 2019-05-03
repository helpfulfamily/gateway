app_name=$1
app_version=$2



build_local_project () {
                echo ">>>>>>>>>>>>>>>>>>> BUILDING LOCAL PROJECT"

                if [[ $app_name =~ "gateway" ]]; then
                                 mvn clean install -Dspring.profiles.active=dev
                    else
                                 echo "App name does not contain 'gateway'. Therefore exiting."
                                 exit 0
                     fi

                    }

docker_process(){
        echo ">>>>>>>>>>>>>>>>>>> DOCKER IMAGE BUILD"

        docker build . -t $app_name:$app_version

        echo ">>>>>>>>>>>>>>>>>>> DOCKER IMAGE TAG"
        docker tag $app_name:$app_version hunaltay/$app_name:$app_version

        echo ">>>>>>>>>>>>>>>>>>> DOCKER IMAGE PUSH"
        docker push hunaltay/$app_name:$app_version


}
openshift_process_route(){
 echo ">>>>>>>>>>>>>>>>>>> OPENSHIFT: CREATE ROUTE"

         oc create route edge --service=$app_name \
                              --insecure-policy='Redirect' \
                              --port=8443-tcp

}


openshift_process(){
              echo ">>>>>>>>>>>>>>>>>>> OPENSHIFT: CREATE APP"

              oc delete all --selector app=$app_name

              oc new-app hunaltay/$app_name:$app_version

              openshift_process_route



}
configmap_process(){
if [[ $app_name =~ "gateway" ]]; then
     echo ">>>>>>>>>>>>>>>>>>> OPENSHIFT: CREATE ConfigMap "

 GEN_CONFIG=$(sed  '/^\s*$/d'  config/application-pr.properties \
  | sed -e 's/^/--from-literal=/' | tr "\n" ' ')



    if [ "X$GEN_CONFIG" = "X" ]; then
        echo "No config found for prlooking config/application-pr.properties"
        echo "Exting!"
        exit 1
    fi

    oc delete configmap $app_name-config   -n helpfularmy
    oc create configmap $app_name-config  $GEN_CONFIG   -n helpfularmy
    oc label  configmap $app_name-config   app=$app_name -n helpfularmy

    echo ">>>>>>>>>>>>>>>>>>> OPENSHIFT: CREATE ENV VARIABLES "
    oc set env dc/$app_name --from configmap/$app_name-config
fi

}

if [[ -n "$app_name" ]]; then
        if [[ -n "$app_version" ]]; then

            oc project helpfularmy

            build_local_project



            if [[ -n "$recreate" ]]; then

            delete_project

            create_project

            else
                echo "Countinue without re-creating helpful project"
            fi


        docker_process

        openshift_process

        configmap_process


        else
            echo "Put the app version"
        fi
else
    echo "Put the app name"
fi

