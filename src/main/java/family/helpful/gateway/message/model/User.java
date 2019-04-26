package family.helpful.gateway.message.model;


import java.util.ArrayList;
import java.util.List;


public class User extends BasicModel {

    public String username;
    public String profilePhotoUrl;
    public String coverUrl;

    Long currentThankAmount;
    private List<Transaction> sentTransactions = new ArrayList<>();

    private List<Transaction> receivedTransactions = new ArrayList<>();

    private List<ProblemContent> problemContents = new ArrayList<>();

    private List<SolutionContent> solutionContents = new ArrayList<>();
    private List<ProblemTitle> problemTitles = new ArrayList<>();
    private List<SolutionTitle> solutionTitles = new ArrayList<>();
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

    public List<ProblemContent> getProblemContents() {
        return problemContents;
    }

    public void setProblemContents(List<ProblemContent> problemContents) {
        this.problemContents = problemContents;
    }

    public List<SolutionContent> getSolutionContents() {
        return solutionContents;
    }

    public void setSolutionContents(List<SolutionContent> solutionContents) {
        this.solutionContents = solutionContents;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public List<Transaction> getSentTransactions() {
        return sentTransactions;
    }

    public void setSentTransactions(List<Transaction> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }

    public List<Transaction> getReceivedTransactions() {
        return receivedTransactions;
    }

    public void setReceivedTransactions(List<Transaction> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }

    public Long getCurrentThankAmount() {
        return currentThankAmount;
    }

    public void setCurrentThankAmount(Long currentThankAmount) {
        this.currentThankAmount = currentThankAmount;
    }

    public List<ProblemTitle> getProblemTitles() {
        return problemTitles;
    }

    public void setProblemTitles(List<ProblemTitle> problemTitles) {
        this.problemTitles = problemTitles;
    }

    public List<SolutionTitle> getSolutionTitles() {
        return solutionTitles;
    }

    public void setSolutionTitles(List<SolutionTitle> solutionTitles) {
        this.solutionTitles = solutionTitles;
    }
}
