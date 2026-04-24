package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 结束停车 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EndParkRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19031")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌")
    private String plateNo;

    @Schema(description = "车位ID，关联车位表", requiredMode = Schema.RequiredMode.REQUIRED, example = "9244")
    @ExcelProperty("车位ID，关联车位表")
    private Long spaceId;

    @Schema(description = "结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("结束时间")
    private LocalDateTime endTime;

    @Schema(description = "缴费状态：待支付/已支付/已取消，关联字典end_park_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("缴费状态：待支付/已支付/已取消，关联字典end_park_status")
    private String status;

    @Schema(description = "片区ID，关联片区表", requiredMode = Schema.RequiredMode.REQUIRED, example = "5559")
    @ExcelProperty("片区ID，关联片区表")
    private Long areaId;

    @Schema(description = "片区名称")
    @ExcelProperty("片区名称")
    private String areaName;

    @Schema(description = "车位编号")
    @ExcelProperty("车位编号")
    private String spaceNo;

    @Schema(description = "操作人ID，关联芋道用户表system_user", example = "8424")
    @ExcelProperty("操作人ID，关联芋道用户表system_user")
    private Long operatorId;

    @Schema(description = "操作人姓名")
    @ExcelProperty("操作人姓名")
    private String operatorName;

    @Schema(description = "订单编号")
    @ExcelProperty("订单编号")
    private String orderNo;

    @Schema(description = "备注", example = "随便")
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