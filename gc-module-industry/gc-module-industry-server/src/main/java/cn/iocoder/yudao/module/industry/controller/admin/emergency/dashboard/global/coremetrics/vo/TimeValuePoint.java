package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor  // 无参构造器
@AllArgsConstructor // 全参构造器
public class TimeValuePoint {
    @Schema(description = "日期，yyyy-MM-dd", example = "2025-11-19")
    @ExcelProperty("日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Schema(description = "对应值", example = "60")
    @ExcelProperty("对应值")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private BigDecimal value;


}
