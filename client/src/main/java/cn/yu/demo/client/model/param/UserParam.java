package cn.yu.demo.client.model.param;


import cn.yu.demo.client.common.valid.group.CreateGroup;
import cn.yu.demo.client.common.valid.group.UpdateGroup;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * user param
 *
 * @author bin.yu
 * @create 2020-02-23 15:22
 * @info best wishes no bug
 **/
@Data
public class UserParam {

    @NotBlank(message = "VALIDATION-USER-0003",groups = {UpdateGroup.class})
    private Long id;

    @NotBlank(message = "VALIDATION-USER-0000",groups = {CreateGroup.class, UpdateGroup.class})
    private String name;

    @Pattern(regexp="^[0-9]{1,2}$",message = "VALIDATION-USER-0001",groups = {CreateGroup.class, UpdateGroup.class})
    private int sex;

    @NotNull(message = "VALIDATION-USER-0002",groups = {CreateGroup.class, UpdateGroup.class})
    private Integer age;

    private int education;

    private String school;

    @Valid
    private AddressParam address;
}
