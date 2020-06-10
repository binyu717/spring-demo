package cn.yu.demo.service.common.util.excel2;

import cn.yu.demo.service.config.Exception.ExceptionHandler;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * excel util
 *
 * @author bin.yu
 * @create 2020-06-07 14:47
 * @info best wishes no bug
 **/
public class ExcelUtils {

    /**
     * 导出 Excel
     *
     * @param response  HttpServletResponse
     * @param list      数据 list，每个元素为一个 BaseRowModel
     * @param fileName  导出的文件名
     * @param sheetName 导入文件的 sheet 名
     * @throws Exception
     */
    public static <T> void writeExcel(HttpServletResponse response, List<T> list, String fileName, ExcelTypeEnum excelTypeEnum,
                                                           String sheetName, Class<T> classType) throws Exception {
        if (sheetName == null || "".equals(sheetName)) {
            sheetName = "sheet1";
        }
        EasyExcel.write(getOutputStream(fileName, response, excelTypeEnum), classType).sheet(sheetName).doWrite(list);
    }

    /**
     * 导出 Excel,包含指定字段
     *
     * @param response  HttpServletResponse
     * @param list      数据 list，每个元素为一个 BaseRowModel
     * @param fileName  导出的文件名
     * @param excelTypeEnum 类型
     * @param sheetName 导入文件的 sheet 名
     * @param classType 导出model类型
     * @param includeColumnFiledNames 包含字段
     * @throws Exception
     */
    public static <T> void includeWriteExcel(HttpServletResponse response, List<T> list, String fileName, ExcelTypeEnum excelTypeEnum,
                                                                  String sheetName, Class<T> classType, Set<String> includeColumnFiledNames) throws Exception {
        if (sheetName == null || "".equals(sheetName)) {
            sheetName = "sheet1";
        }
        EasyExcel.write(getOutputStream(fileName, response, excelTypeEnum), classType)
                .includeColumnFiledNames(includeColumnFiledNames)
                .sheet(sheetName).doWrite(list);
    }

    /**
     * 导出 Excel，排除指定字段
     *
     * @param response  HttpServletResponse
     * @param list      数据 list，每个元素为一个 BaseRowModel
     * @param fileName  导出的文件名
     * @param excelTypeEnum 类型
     * @param sheetName 导入文件的 sheet 名
     * @param classType 导出model类型
     * @param excludeColumnFiledNames 排除字段
     * @throws Exception
     */
    public static <T> void excludeWriteExcel(HttpServletResponse response, List<T> list, String fileName, ExcelTypeEnum excelTypeEnum,
                                                                  String sheetName, Class<T> classType, Set<String> excludeColumnFiledNames) throws Exception {
        if (sheetName == null || "".equals(sheetName)) {
            sheetName = "sheet1";
        }
        EasyExcel.write(getOutputStream(fileName, response, excelTypeEnum), classType)
                .excludeColumnFiledNames(excludeColumnFiledNames)
                .sheet(sheetName).doWrite(list);
     }

    /**
     * 多sheet导出
     * @param response
     * @param sheetModels
     * @param fileName
     * @param excelTypeEnum
     * @param <T>
     * @throws Exception
     */
    public static <T> void multipleWriteExcel(HttpServletResponse response, List<MultipleSheetModel> sheetModels,
                                              String fileName, ExcelTypeEnum excelTypeEnum) throws Exception{
        ExcelWriter build = EasyExcel.write(getOutputStream(fileName, response, excelTypeEnum)).build();
        for (MultipleSheetModel sheetModel : sheetModels) {
            WriteSheet writeSheet = EasyExcel.writerSheet(sheetModel.getSheetName()).head(sheetModel.getClassType()).build();
            build.write(sheetModel.getData(), writeSheet);
        }
        build.finish();
    }



    /**
     * 导出文件时为Writer生成OutputStream
     */
    private static OutputStream getOutputStream(String fileName, HttpServletResponse response, ExcelTypeEnum excelTypeEnum) throws Exception {
        //创建本地文件
        String filePath = fileName + excelTypeEnum.getValue();
        try {
            fileName = new String(filePath.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setLocale(new Locale("zh", "CN"));
            return response.getOutputStream();
        }
        catch (IOException e) {
            ExceptionHandler.publish("", "创建文件失败！");
            return null;
        }
    }

}
