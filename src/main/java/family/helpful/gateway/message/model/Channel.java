package family.helpful.gateway.message.model;


import java.util.ArrayList;
import java.util.List;

public class Channel extends BasicModel {

    Long currentObserverAmount;

    Long currentThankAmount;
    private User user;
    private List<ChannelContent> channelContents = new ArrayList<>();




    public Long getCurrentThankAmount() {
        return currentThankAmount;
    }

    public void setCurrentThankAmount(Long currentThankAmount) {
        this.currentThankAmount = currentThankAmount;
    }

    public Long getCurrentObserverAmount() {
        return currentObserverAmount;
    }

    public void setCurrentObserverAmount(Long currentObserverAmount) {
        this.currentObserverAmount = currentObserverAmount;
    }

    public List<ChannelContent> getChannelContents() {
        return channelContents;
    }

    public void setChannelContents(List<ChannelContent> channelContents) {
        this.channelContents = channelContents;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
