package cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 放行记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PassRecordRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13009")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车牌")
    @ExcelProperty("车牌")
    private String plateNo;

    @Schema(description = "放行原因：人工开闸 / 特殊车辆 / 其他，关联字典：pass_record_pass_reason", requiredMode = Schema.RequiredMode.REQUIRED, example = "不好")
    @ExcelProperty("放行原因：人工开闸 / 特殊车辆 / 其他，关联字典：pass_record_pass_reason")
    private String passReason;

    @Schema(description = "放行时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("放行时间")
    private LocalDateTime passTime;

    @Schema(description = "抓拍图片地址", example = "https://www.iocoder.cn")
    @ExcelProperty("抓拍图片地址")
    private String imageUrl;

    @Schema(description = "状态：正常记录 / 异常记录，关联字典：pass_record_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态：正常记录 / 异常记录，关联字典：pass_record_status")
    private String status;

    @Schema(description = "场站ID，关联场站表", requiredMode = Schema.RequiredMode.REQUIRED, example = "10614")
    @ExcelProperty("场站ID，关联场站表")
    private Long stationId;

    @Schema(description = "场站名称")
    @ExcelProperty("场站名称")
    private String stationName;

    @Schema(description = "操作人ID，关联芋道用户表 system_user", example = "48")
    @ExcelProperty("操作人ID，关联芋道用户表 system_user")
    private Long operatorId;

    @Schema(description = "操作人姓名")
    @ExcelProperty("操作人姓名")
    private String operatorName;

    @Schema(description = "操作时间")
    @ExcelProperty("操作时间")
    private LocalDateTime operatorTime;

    @Schema(description = "核查结果")
    @ExcelProperty("核查结果")
    private String checkResult;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}