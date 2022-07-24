package cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Region {

    //region_id
    //if you jsonkey and variable name not matching, you can map it with JsonProperty
    @JsonProperty("region_id")
    private int RId;
    @JsonProperty("region_name")
    private String region_name;
    @JsonProperty ("links")
    private List<Link> links;


}
