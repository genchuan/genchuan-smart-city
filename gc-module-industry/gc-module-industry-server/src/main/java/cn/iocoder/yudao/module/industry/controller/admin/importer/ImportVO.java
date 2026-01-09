package cn.iocoder.yudao.module.industry.controller.admin.importer;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ImportVO {
    @ExcelProperty("id") // 第一列
    private Long id;

    @ExcelProperty("orderNo") // 第二列
    private String orderNo;

    @ExcelProperty("time")
    private LocalDateTime time;

    @ExcelProperty("bigDecimalNum")
    private BigDecimal bigDecimalNum;

    @ExcelProperty("date")
    private Date date;

    @ExcelProperty("localDate")
    private LocalDate localDate;

}
