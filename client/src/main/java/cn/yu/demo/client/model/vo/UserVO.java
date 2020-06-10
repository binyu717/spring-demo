package cn.yu.demo.client.model.vo;

import cn.yu.demo.client.model.param.AddressParam;
import lombok.Data;

/**
 * user vo
 *
 * @author bin.yu
 * @create 2020-02-23 15:22
 * @info best wishes no bug
 **/
@Data
public class UserVO {

    private Long id;

    private String name;

    private int sex;

    private Integer age;

    private int education;

    private String school;

    private AddressParam address;

}
