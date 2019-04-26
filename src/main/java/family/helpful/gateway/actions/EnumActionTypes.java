package family.helpful.gateway.actions;

public enum EnumActionTypes {

    publishContent("publish content"),
    findByTitleWithAmount("find contents"),
    getAllWithAmount("load title list");

    String description;
    EnumActionTypes(String description ){

        this.description= description;

    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
