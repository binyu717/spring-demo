package cn.yu.demo.service.common.util.excel;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CellStyleFormat {

    /**
     * 水平居中方式 默认左居中
     *
     * @see HorizontalAlignment
     */
    HorizontalAlignment horizontalAlignment() default HorizontalAlignment.LEFT;

    /**
     * 字体设置
     *
     * @see org.apache.poi.xssf.usermodel.XSSFFont
     * @see org.apache.poi.hssf.usermodel.HSSFFont
     */
    CellFontFormat cellFont() default @CellFontFormat();


    /**
     * 背景颜色
     *
     * @see IndexedColors
     */
    IndexedColors fillBackgroundColor() default IndexedColors.WHITE;

}
