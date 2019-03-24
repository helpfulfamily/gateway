package army.helpful.prosoha.actions;

public enum EnumActionStatus {

    SUCCESS("publish content action is successfull");


    String description;
    EnumActionStatus(String description ){

        this.description= description;

    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
