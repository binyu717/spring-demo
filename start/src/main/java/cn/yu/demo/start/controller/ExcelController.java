package cn.yu.demo.start.controller;

import cn.yu.demo.client.model.UserExcelDTO;
import cn.yu.demo.service.common.util.excel2.ExcelUtils;
import cn.yu.demo.service.common.util.excel2.MultipleSheetModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * excel controller
 *
 * @author bin.yu
 * @create 2020-06-07 20:49
 * @info best wishes no bug
 **/
@RestController()
@RequestMapping("excel")
@Api(value = "excel")
public class ExcelController {

    @RequestMapping(value = "export/user", method = RequestMethod.POST)
    @ApiOperation(value = "导出用户信息")
    public void exportUser(HttpServletResponse response) throws Exception{
        UserExcelDTO userExcelDTO = new UserExcelDTO();
        userExcelDTO.setId(1L);
        userExcelDTO.setName("yubin");
        userExcelDTO.setSex(1);
        userExcelDTO.setAge(28);
        userExcelDTO.setEducation(5);
        userExcelDTO.setSchool("长沙理工");
        userExcelDTO.setAddressExcelDTOList(Lists.newArrayList());
        for (int i = 0; i < 2; i++) {
            UserExcelDTO.AddressExcelDTO addressExcelDTO = new UserExcelDTO.AddressExcelDTO();
            addressExcelDTO.setProvince("湖南"+i);
            addressExcelDTO.setCity("长沙"+i);
            addressExcelDTO.setArea("a");
            addressExcelDTO.setDetail("b");
        }
        List<MultipleSheetModel> sheetModels = new ArrayList<>();
        MultipleSheetModel userSheet = new MultipleSheetModel();
        userSheet.setClassType(UserExcelDTO.class);
        userSheet.setData(Arrays.asList(userExcelDTO));
        userSheet.setSheetName("用户");
        sheetModels.add(userSheet);

        MultipleSheetModel addressSheet = new MultipleSheetModel();
        addressSheet.setClassType(UserExcelDTO.AddressExcelDTO.class);
        addressSheet.setData(Arrays.asList(userExcelDTO.getAddressExcelDTOList()));
        addressSheet.setSheetName("地址");
        sheetModels.add(addressSheet);
        ExcelUtils.multipleWriteExcel(response, sheetModels, "test", ExcelTypeEnum.XLSX);
    }
}
