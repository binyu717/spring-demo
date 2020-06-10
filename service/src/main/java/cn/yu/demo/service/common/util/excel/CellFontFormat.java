package cn.yu.demo.service.common.util.excel;

import org.apache.poi.ss.usermodel.IndexedColors;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CellFontFormat {

    String fontName() default "";

    short fontHeightInPoints() default 11;

    IndexedColors fontColor() default IndexedColors.BLACK;

    boolean bold() default false;
}
