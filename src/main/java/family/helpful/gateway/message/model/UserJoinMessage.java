package family.helpful.gateway.message.model;

public class UserJoinMessage {
   private   User user;
   private   String channelName;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
