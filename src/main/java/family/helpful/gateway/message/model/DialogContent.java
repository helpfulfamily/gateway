package family.helpful.gateway.message.model;

import lombok.ToString;

@ToString
public class DialogContent extends BasicModel {

    Long currentThankAmount;
    private String text;
    private User sender;
    private User receiver;

    public void setSender(User sender) {
        this.sender = sender;
    }
}
