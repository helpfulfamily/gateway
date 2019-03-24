package army.helpful.prosoha.message.model;




public class Content extends BasicModel {

    private String text;
    private Title title;
    public Content() {
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
