package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.accessrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import cn.idev.excel.annotation.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 通行记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AccessRecordRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "人脸信息ID")
    @ExcelProperty("人脸信息ID")
    private Long faceId;

    @Schema(description = "人员姓名")
    @ExcelProperty("人员姓名")
    private String userName;

    @Schema(description = "通行区域")
    @ExcelProperty("通行区域")
    private String accessArea;

    @Schema(description = "通行时间，格式时间戳")
    @ExcelProperty("通行时间")
    private LocalDateTime accessTime;

    @Schema(description = "验证方式（人脸/刷卡）")
    @ExcelProperty("验证方式")
    private String verifyType;

    @Schema(description = "通行状态（正常通行/异常通行）")
    @ExcelProperty("通行状态")
    private String accessStatus;

    @Schema(description = "抓拍照片存储路径")
    @ExcelProperty("抓拍照片")
    private String snapImg;

    @Schema(description = "核查结果")
    @ExcelProperty("核查结果")
    private String checkResult;

    @Schema(description = "告警状态")
    @ExcelProperty("告警状态")
    private String alarmStatus;

    @Schema(description = "处置结果")
    @ExcelProperty("处置结果")
    private String handleResult;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
