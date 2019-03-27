keytool -genkey -alias tomcat  \
     -keystore ks.p12 \
    -storetype PKCS12 \
    -keyalg RSA \
    -storepass hemligt \
    -validity 730 \
    -keysize 2048 