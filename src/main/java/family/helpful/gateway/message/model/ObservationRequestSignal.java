package family.helpful.gateway.message.model;

public class ObservationRequestSignal {
    Long observerUserId;
    String observerUsername;
    Long channelId;
    Long currentObserverAmount;
    String objectType;

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
}
