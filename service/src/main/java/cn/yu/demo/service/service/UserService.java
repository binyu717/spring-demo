package cn.yu.demo.service.service;

import cn.yu.demo.client.model.dto.UserDTO;

/**
 * user service
 *
 * @author bin.yu
 * @create 2020-06-10 22:17
 * @info best wishes no bug
 **/
public interface UserService {

    UserDTO findById(Long id);
}
