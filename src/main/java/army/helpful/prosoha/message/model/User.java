package army.helpful.prosoha.message.model;



public class User extends BasicModel {

    public String username;
    public String profilePhotoUrl;

    public String toString(){

        return   this.getClass().getSimpleName();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }
}
