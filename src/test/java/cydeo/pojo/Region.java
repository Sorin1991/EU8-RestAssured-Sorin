package cydeo.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Region {

    //region_id
    private int region_id;
    private String region_name;
    private List<Link> links;


}
