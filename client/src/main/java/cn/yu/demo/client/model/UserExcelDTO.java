package cn.yu.demo.client.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.List;

/**
 * user dto
 *
 * @author bin.yu
 * @create 2020-06-07 20:52
 * @info best wishes no bug
 **/
@Data
public class UserExcelDTO {

    @ExcelIgnore
    private Long id;

    @ExcelProperty(value = "名字")
    private String name;

    @ExcelProperty(value = "性别")
    private int sex;

    @ExcelProperty(value = "年龄")
    private Integer age;

    @ExcelProperty(value = "学历")
    private int education;

    @ExcelProperty(value = "学校")
    private String school;

    private List<AddressExcelDTO> addressExcelDTOList;

    @Data
    public static class AddressExcelDTO {

        @ExcelProperty(value = "省份")
        private String province;
        @ExcelProperty(value = "城市")
        private String city;
        @ExcelProperty(value = "地区")
        private String area;
        @ExcelProperty(value = "详细地址")
        private String detail;
    }
}
