package army.helpful.prosoha.message.model;

import org.springframework.messaging.support.GenericMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TitleMessage   {
     List<Title> titleList;

    public List<Title> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<Title> titleList) {
        this.titleList = titleList;
    }
}
