package family.helpful.gateway.message.model;


import java.util.ArrayList;
import java.util.List;

public class SolutionTitle extends  BasicModel {

    private List<SolutionContent> solutionContents = new ArrayList<>();
    private User user;
    Long currentThankAmount;

    private List<Channel> channels = new ArrayList<>();
    public Long getCurrentThankAmount() {
        return currentThankAmount;
    }

    public void setCurrentThankAmount(Long currentThankAmount) {
        this.currentThankAmount = currentThankAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SolutionContent> getSolutionContents() {
        return solutionContents;
    }

    public void setSolutionContents(List<SolutionContent> solutionContents) {
        this.solutionContents = solutionContents;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }
}
