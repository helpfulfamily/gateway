package army.helpful.prosoha.message.model;


import java.util.ArrayList;
import java.util.List;


public class Title extends BasicModel {

    private List<Content> contents = new ArrayList<>();

    public Title() {
    }
    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }
}
