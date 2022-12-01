package Lesson4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.xml.transform.Result;

@Data
public class SearchRecipesResponce1 {
    private List<Result> results = null;
    private Integer offset;
    private Integer number;
    private Integer totalResults;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}