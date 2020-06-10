package cn.yu.demo.service.service.impl;
import cn.yu.demo.client.model.param.AddressParam;

import cn.yu.demo.client.model.dto.UserDTO;
import cn.yu.demo.service.common.convertor.UserConvertor;
import cn.yu.demo.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * user service impl
 *
 * @author bin.yu
 * @create 2020-06-10 22:18
 * @info best wishes no bug
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserConvertor userConvertor;

    @Override
    public UserDTO findById(Long id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(0L);
        userDTO.setName("");
        userDTO.setSex(0);
        userDTO.setAge(0);
        userDTO.setEducation(0);
        userDTO.setSchool("");
        userDTO.setAddress(new AddressParam());
        return userDTO;
    }

}
