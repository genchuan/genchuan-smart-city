package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 无牌入场 Response VO")
@Data
public class UnplateEnterRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车辆类型")
    @ExcelProperty("车辆类型")
    private String carType;

    @Schema(description = "车辆颜色")
    @ExcelProperty("车辆颜色")
    private String carColor;

    @Schema(description = "联系电话（脱敏）")
    @ExcelProperty("联系电话（脱敏）")
    private String phone;

    @Schema(description = "登记时间")
    @ExcelProperty("登记时间")
    private LocalDateTime registerTime;

    @Schema(description = "审核状态")
    @ExcelProperty("审核状态")
    private String status;

    @Schema(description = "场站ID")
    @ExcelProperty("场站ID")
    private Long stationId;

    @Schema(description = "场站名称")
    @ExcelProperty("场站名称")
    private String stationName;

    @Schema(description = "审核人")
    @ExcelProperty("审核人")
    private String auditUserName;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核意见")
    @ExcelProperty("审核意见")
    private String auditComment;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;
}