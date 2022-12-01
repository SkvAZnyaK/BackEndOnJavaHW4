package Lesson4;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Result {
    private Integer id;
    private String title;
    private String image;
    private String imageType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}