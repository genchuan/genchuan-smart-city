package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 车辆录入 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CarInputRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11968")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌")
    private String plateNo;

    @Schema(description = "车位ID，关联车位表", requiredMode = Schema.RequiredMode.REQUIRED, example = "7352")
    @ExcelProperty("车位ID，关联车位表")
    private Long spaceId;

    @Schema(description = "录入时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("录入时间")
    private LocalDateTime inputTime;

    @Schema(description = "审核状态：待审核/已通过/已驳回，关联字典car_input_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("审核状态：待审核/已通过/已驳回，关联字典car_input_status")
    private String status;

    @Schema(description = "片区ID，关联片区表", requiredMode = Schema.RequiredMode.REQUIRED, example = "15735")
    @ExcelProperty("片区ID，关联片区表")
    private Long areaId;

    @Schema(description = "片区名称")
    @ExcelProperty("片区名称")
    private String areaName;

    @Schema(description = "录入人ID，关联芋道用户表system_user", example = "2919")
    @ExcelProperty("录入人ID，关联芋道用户表system_user")
    private Long inputUserId;

    @Schema(description = "录入人名称")
    @ExcelProperty("录入人名称")
    private String inputUserName;

    @Schema(description = "审核人ID，关联芋道用户表system_user", example = "18416")
    @ExcelProperty("审核人ID，关联芋道用户表system_user")
    private Long auditUserId;

    @Schema(description = "审核人名称")
    @ExcelProperty("审核人名称")
    private String auditUserName;

    @Schema(description = "车位编号")
    @ExcelProperty("车位编号")
    private String spaceNo;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核意见")
    @ExcelProperty("审核意见")
    private String auditComment;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者，创建人账号/姓名")
    @ExcelProperty("创建者，创建人账号/姓名")
    private String creator;

    @Schema(description = "更新者，更新人账号/姓名")
    @ExcelProperty("更新者，更新人账号/姓名")
    private String updater;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}