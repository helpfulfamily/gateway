package family.helpful.gateway.message.model;


import java.util.List;

public class ProblemContentMessage {
    List<ProblemContent> problemContentList;

    public List<ProblemContent> getProblemContentList() {
        return problemContentList;
    }

    public void setProblemContentList(List<ProblemContent> problemContentList) {
        this.problemContentList = problemContentList;
    }
}
