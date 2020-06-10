package cn.yu.demo.client.model.dto;

import cn.yu.demo.client.model.param.AddressParam;
import lombok.Data;

/**
 * user dto
 *
 * @author bin.yu
 * @create 2020-06-10 22:19
 * @info best wishes no bug
 **/
@Data
public class UserDTO {

    private Long id;

    private String name;

    private int sex;

    private Integer age;

    private int education;

    private String school;

    private AddressParam address;
}
