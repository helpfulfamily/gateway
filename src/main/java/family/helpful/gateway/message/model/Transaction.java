package family.helpful.gateway.message.model;


public class Transaction extends BasicModel{

    private User sender;
    private User receiver;

    String objectType;
    Long   objectId;

    Long amount;

    String status;

    Long lastThankAmountSender;
    Long lastThankAmountReceiver;
    Long lastThankAmountObject;
    public Long getLastThankAmountSender() {
        return lastThankAmountSender;
    }

    public void setLastThankAmountSender(Long lastThankAmountSender) {
        this.lastThankAmountSender = lastThankAmountSender;
    }

    public Long getLastThankAmountReceiver() {
        return lastThankAmountReceiver;
    }

    public void setLastThankAmountReceiver(Long lastThankAmountReceiver) {
        this.lastThankAmountReceiver = lastThankAmountReceiver;
    }

    public Long getLastThankAmountObject() {
        return lastThankAmountObject;
    }

    public void setLastThankAmountObject(Long lastThankAmountObject) {
        this.lastThankAmountObject = lastThankAmountObject;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}
