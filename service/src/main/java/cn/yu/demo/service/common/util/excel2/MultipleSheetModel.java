package cn.yu.demo.service.common.util.excel2;

import lombok.Data;

import java.util.List;

/**
 * muilti sheet excel
 *
 * @author bin.yu
 * @create 2020-06-07 19:52
 * @info best wishes no bug
 **/
@Data
public class MultipleSheetModel {

    private String sheetName;

    private Class classType;

    private List data;

}
