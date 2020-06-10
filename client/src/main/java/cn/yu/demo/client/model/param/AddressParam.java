package cn.yu.demo.client.model.param;

import cn.yu.demo.client.common.valid.group.CreateGroup;
import cn.yu.demo.client.common.valid.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * address param
 *
 * @author bin.yu
 * @create 2020-02-23 15:36
 * @info best wishes no bug
 **/
@Data
public class AddressParam {

    @NotBlank(message = "VALIDATION-USER-0004",groups = {CreateGroup.class, UpdateGroup.class})
    private String province;

    private String city;

    private String area;

    private String detail;

}
