package family.helpful.gateway.message.model;


import java.util.ArrayList;
import java.util.List;

public class Channel extends BasicModel {


    Long currentThankAmount;
    private List<ProblemTitle> problemTitles = new ArrayList<>();

    public List<ProblemTitle> getProblemTitles() {
        return problemTitles;
    }

    public void setProblemTitles(List<ProblemTitle> problemTitles) {
        this.problemTitles = problemTitles;
    }

    public Long getCurrentThankAmount() {
        return currentThankAmount;
    }

    public void setCurrentThankAmount(Long currentThankAmount) {
        this.currentThankAmount = currentThankAmount;
    }
}
