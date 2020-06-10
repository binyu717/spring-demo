package cn.yu.demo.start.controller;


import cn.yu.demo.client.common.valid.group.CreateGroup;
import cn.yu.demo.client.common.valid.group.UpdateGroup;
import cn.yu.demo.client.model.ResponseMsg;
import cn.yu.demo.client.model.param.UserParam;
import cn.yu.demo.client.model.vo.UserVO;
import cn.yu.demo.service.common.convertor.UserConvertor;
import cn.yu.demo.service.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * user controller
 *
 * @author bin.yu
 * @create 2020-02-23 15:19
 * @info best wishes no bug
 **/
@RestController()
@RequestMapping("user")
@Api(value = "用户")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConvertor userConvertor;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "新建用户信息")
    public ResponseMsg addUser(@RequestBody @Validated(CreateGroup.class) UserParam userParam) {
        UserVO userVO = userConvertor.paramToVO(userParam);
        return ResponseMsg.buildSuccess("新增成功",userVO);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改用户信息")
    public ResponseMsg modifyUser(@RequestBody @Validated(UpdateGroup.class) UserParam userParam) {
        UserVO userVO = userConvertor.paramToVO(userParam);
        return ResponseMsg.buildSuccess("修改成功",userVO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户")
    public ResponseMsg deleteUser(@PathVariable("id") @NotNull(message = "VALIDATION-USER-0003") Long id) {
        return ResponseMsg.buildSuccess("删除成功", id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户信息by id")
    public ResponseMsg findById(@PathVariable("id") @NotNull(message = "VALIDATION-USER-0003") Long id) {
        return ResponseMsg.buildSuccess("", userConvertor.dtoToVO(userService.findById(id)));
    }


}
