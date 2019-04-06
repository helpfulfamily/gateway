package army.helpful.prosoha.message.model;


public class SolutionContent extends BasicModel {

    private String text;


    private ProblemTitle problemTitle;

    private SolutionTitle solutionTitle;

    private User user;


    private boolean firstContent;

    public SolutionContent() {
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SolutionTitle getSolutionTitle() {
        return solutionTitle;
    }

    public void setSolutionTitle(SolutionTitle solutionTitle) {
        this.solutionTitle = solutionTitle;
    }

    public boolean isFirstContent() {
        return firstContent;
    }

    public void setFirstContent(boolean firstContent) {
        this.firstContent = firstContent;
    }


    public String toString(){
     return   this.getClass().getSimpleName();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
