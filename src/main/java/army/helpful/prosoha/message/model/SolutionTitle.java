package army.helpful.prosoha.message.model;


import java.util.ArrayList;
import java.util.List;

public class SolutionTitle extends  BasicModel {

    private List<SolutionContent> solutionContents = new ArrayList<>();
    private User user;
    Long currentThankAmount;

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
}
