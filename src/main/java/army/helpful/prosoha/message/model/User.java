package army.helpful.prosoha.message.model;

import java.util.ArrayList;
import java.util.List;


public class User extends BasicModel {

    public String username;
    public String profilePhotoUrl;
    public String coverUrl;


    private List<ProblemContent> problemContents = new ArrayList<>();

    private List<SolutionContent> solutionContents = new ArrayList<>();

   public String toString(){

        return   this.getClass().getSimpleName();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

}
