package cn.yu.demo.service.common.convertor;

import cn.yu.demo.client.model.dto.UserDTO;
import cn.yu.demo.client.model.param.UserParam;
import cn.yu.demo.client.model.vo.UserVO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class UserConvertorImpl implements UserConvertor {

    @Override
    public UserVO paramToVO(UserParam param) {
        if ( param == null ) {
            return null;
        }

        UserVO userVO = new UserVO();

        userVO.setId( param.getId() );
        userVO.setName( param.getName() );
        userVO.setSex( param.getSex() );
        userVO.setAge( param.getAge() );
        userVO.setEducation( param.getEducation() );
        userVO.setSchool( param.getSchool() );
        userVO.setAddress( param.getAddress() );

        return userVO;
    }

    @Override
    public UserVO dtoToVO(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserVO userVO = new UserVO();

        userVO.setId( userDTO.getId() );
        userVO.setName( userDTO.getName() );
        userVO.setSex( userDTO.getSex() );
        userVO.setAge( userDTO.getAge() );
        userVO.setEducation( userDTO.getEducation() );
        userVO.setSchool( userDTO.getSchool() );
        userVO.setAddress( userDTO.getAddress() );

        return userVO;
    }
}
