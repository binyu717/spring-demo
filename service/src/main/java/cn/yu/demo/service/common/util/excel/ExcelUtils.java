package cn.yu.demo.service.common.util.excel;

import cn.yu.demo.service.config.Exception.ExceptionHandler;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @ClassName ExcelUtils
 * @Description 导出工具类
 * @Author yubin
 * @Date 2019-07-26 10:46
 */
@Slf4j
public class ExcelUtils {

    /**
     * 私有化构造方法
     */
    private ExcelUtils() {
    }


    /**
     * 导出 Excel ：一个 sheet，带表头
     * 自定义WriterHandler 可以定制行列数据进行灵活化操作
     *
     * @param response  HttpServletResponse
     * @param list      数据 list，每个元素为一个 BaseRowModel
     * @param fileName  导出的文件名
     * @param sheetName 导入文件的 sheet 名
     * @throws Exception
     */
    public static <T extends BaseRowModel> void writeExcel(HttpServletResponse response, List<T> list, String fileName, ExcelTypeEnum excelTypeEnum,
                                                           String sheetName, Class<T> classType) throws Exception {
        if (sheetName == null || "".equals(sheetName)) {
            sheetName = "sheet1";
        }
        if (excelTypeEnum == ExcelTypeEnum.XLSX) {
            ExcelWriter writer = EasyExcelFactory.getWriterWithTempAndHandler(null,
                    getOutputStream(fileName, response, excelTypeEnum), excelTypeEnum, true, new WriterHandler07<>(classType));
            EasyExcel.write(fileName, classType).sheet("模板").doWrite(list);
            Sheet sheet = new Sheet(1, 0, classType);
            sheet.setSheetName(sheetName);
            try {
                writer.write(list, sheet);
            } finally {
                writer.finish();
            }
        }
        else if (excelTypeEnum == ExcelTypeEnum.XLS) {
            ExcelWriterFactory writer = new ExcelWriterFactory(getOutputStream(fileName, response, excelTypeEnum), excelTypeEnum);
            Sheet sheet = new Sheet(1, 0, classType);
            sheet.setSheetName(sheetName);
            try {
                writer.write(list, sheet);
            } finally {
                writer.finish();
                writer.close();
            }
        }

    }

    /**
     * 读取 Excel(多个 sheet)
     * 将多sheet合并成一个list数据集，通过自定义ExcelReader继承AnalysisEventListener
     * 重写invoke doAfterAllAnalysed方法
     * getExtendsBeanList 主要是做Bean的属性拷贝 ，可以通过ExcelReader中添加的数据集直接获取
     *
     * @param excel    文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @return Excel 数据 list
     */
    public static <T extends BaseRowModel> List<T> readExcel(MultipartFile excel, Class<T> rowModel) throws Exception {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return new ArrayList<>();
        }
        for (Sheet sheet : reader.getSheets()) {
            sheet.setClazz(rowModel);
            reader.read(sheet);
        }
        return getExtendsBeanList(excelListener.getDataList(), rowModel);
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel       文件
     * @param rowModel    实体类映射，继承 BaseRowModel 类
     * @param sheetNo     sheet 的序号 从1开始
     * @param headLineNum 表头行数，默认为1
     * @return Excel 数据 list
     */
    public static <T extends BaseRowModel> List<T> readExcel(MultipartFile excel, Class<T> rowModel, int sheetNo,
                                                             int headLineNum) throws Exception {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return new ArrayList<>();
        }
        reader.read(new Sheet(sheetNo, headLineNum, rowModel));
        return getExtendsBeanList(excelListener.getDataList(), rowModel);
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

    /**
     * 返回 ExcelReader
     *
     * @param excel         需要解析的 Excel 文件
     * @param excelListener new ExcelListener()
     */
    private static ExcelReader getReader(MultipartFile excel,
                                         ExcelListener excelListener) throws Exception {
        String fileName = excel.getOriginalFilename();
        if (fileName == null) {
            ExceptionHandler.publish("", "文件格式错误！");
        }
        if (!fileName.toLowerCase().endsWith(ExcelTypeEnum.XLS.getValue()) && !fileName.toLowerCase().endsWith(ExcelTypeEnum.XLSX.getValue())) {
            ExceptionHandler.publish("", "文件格式错误！");
        }
        InputStream inputStream;
        try {
            inputStream = excel.getInputStream();
            return new ExcelReader(inputStream, null, excelListener, false);
        }
        catch (IOException e) {
            //do something
        }
        return null;
    }

    /**
     * 利用BeanCopy转换list
     */
    public static <T extends BaseRowModel> List<T> getExtendsBeanList(List<?> list, Class<T> typeClazz) {
        return MyBeanCopy.convert(list, typeClazz);
    }
}
