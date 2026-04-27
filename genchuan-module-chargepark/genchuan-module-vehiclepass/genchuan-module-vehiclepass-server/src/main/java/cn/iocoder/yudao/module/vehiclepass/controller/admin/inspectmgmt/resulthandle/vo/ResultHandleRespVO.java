package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 结果处置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ResultHandleRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8467")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联任务ID，关联稽查任务表inspect_task", requiredMode = Schema.RequiredMode.REQUIRED, example = "3993")
    @ExcelProperty("关联任务ID，关联稽查任务表inspect_task")
    private Long taskId;

    @Schema(description = "违规类型：违规通行/欠费逃费/其他，关联字典result_handle_violation_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("违规类型：违规通行/欠费逃费/其他，关联字典result_handle_violation_type")
    private String violationType;

    @Schema(description = "处置方式：补缴费用/限制入场/警告/其他，关联字典result_handle_handle_method", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("处置方式：补缴费用/限制入场/警告/其他，关联字典result_handle_handle_method")
    private String handleMethod;

    @Schema(description = "处置类型：通过/执行，关联字典result_handle_handle_type")
    @ExcelProperty("处置类型：通过/执行")
    private String handleType;

    @Schema(description = "状态：待审核/待处置/已完成/已驳回，关联字典result_handle_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态：待审核/待处置/已完成/已驳回，关联字典result_handle_status")
    private String status;

    @Schema(description = "片区ID，关联片区表", example = "16056")
    @ExcelProperty("片区ID，关联片区表")
    private Long areaId;

    @Schema(description = "片区名称")
    @ExcelProperty("片区名称")
    private String areaName;

    @Schema(description = "处置人ID，关联芋道用户表system_user", example = "15936")
    @ExcelProperty("处置人ID，关联芋道用户表system_user")
    private Long handleUserId;

    @Schema(description = "处置人名称")
    @ExcelProperty("处置人名称")
    private String handleUserName;

    @Schema(description = "处置时间")
    @ExcelProperty("处置时间")
    private LocalDateTime handleTime;

    @Schema(description = "整改状态：未整改/已整改，关联字典result_handle_rectify_status", example = "2")
    @ExcelProperty("整改状态：未整改/已整改，关联字典result_handle_rectify_status")
    private String rectifyStatus;

    @Schema(description = "驳回理由", example = "不好")
    @ExcelProperty("驳回理由")
    private String rejectReason;

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