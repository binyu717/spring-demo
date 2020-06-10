package cn.yu.demo.service.common.convertor;

import cn.yu.demo.client.model.dto.UserDTO;
import cn.yu.demo.client.model.param.UserParam;
import cn.yu.demo.client.model.vo.UserVO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * user convertor
 *
 * @author bin.yu
 * @create 2020-02-23 15:29
 * @info best wishes no bug
 **/
@Component
@Mapper(componentModel = "spring")
public interface UserConvertor {

    UserVO paramToVO(UserParam param);

    UserVO dtoToVO(UserDTO userDTO);
}
