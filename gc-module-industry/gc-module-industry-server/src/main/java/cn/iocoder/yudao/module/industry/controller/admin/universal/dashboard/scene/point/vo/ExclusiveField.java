package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.point.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 场景点位数据-专属字段 Response VO")
@Data
public class ExclusiveField {
    @Schema(description = "专属字段名称", example = "deviceStatus")
    @ExcelProperty("专属字段名称")
    private String name;  // 字段名（来自场景字段表）
    @Schema(description = "专属字段值", example = "90")
    @ExcelProperty("专属字段名称")
    private Object value; // 字段值（来自业务数据）
}
