package army.helpful.prosoha.message.model;



import java.util.ArrayList;
import java.util.List;


public class ProblemTitle extends BasicModel {
      private List<ProblemContent> problemContents = new ArrayList<>();
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
