package family.helpful.gateway.message.model;

public class ObservationRequestSignal {
    Long observerUserId;
    String observerUsername;
    Long channelId;
    String channelName;
    Long currentObserverAmount;
    String objectType;
    boolean observe;

    public Long getObserverUserId() {
        return observerUserId;
    }

    public void setObserverUserId(Long observerUserId) {
        this.observerUserId = observerUserId;
    }

    public Long getCurrentObserverAmount() {
        return currentObserverAmount;
    }

    public void setCurrentObserverAmount(Long currentObserverAmount) {
        this.currentObserverAmount = currentObserverAmount;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObserverUsername() {
        return observerUsername;
    }

    public void setObserverUsername(String observerUsername) {
        this.observerUsername = observerUsername;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public boolean isObserve() {
        return observe;
    }

    public void setObserve(boolean observe) {
        this.observe = observe;
    }
}
