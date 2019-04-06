package army.helpful.prosoha.message.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

public class SolutionTitle extends  BasicModel {

    private List<SolutionContent> solutionContents = new ArrayList<>();
}
