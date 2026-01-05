package cn.iocoder.yudao.module.industry.controller.admin.importer;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class importVO {
    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("orderNo")
    private String orderNo;
}
