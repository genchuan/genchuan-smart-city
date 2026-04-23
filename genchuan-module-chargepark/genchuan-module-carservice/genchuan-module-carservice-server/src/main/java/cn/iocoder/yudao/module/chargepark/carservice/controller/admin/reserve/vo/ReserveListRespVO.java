package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 预约列表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ReserveListRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("用户 ID")
    private Long userId;

    @Schema(description = "用户名（关联 system_user.nickname）")
    @ExcelProperty("用户名")
    private String userName;

    @Schema(description = "场站 ID")
    @ExcelProperty("场站 ID")
    private Long stationId;

    @Schema(description = "场站名（关联 station_info.name）")
    @ExcelProperty("场站名")
    private String stationName;

    @Schema(description = "车位 ID")
    @ExcelProperty("车位 ID")
    private Long spaceId;

    @Schema(description = "预约时间")
    @ExcelProperty("预约时间")
    private LocalDateTime reserveTime;

    @Schema(description = "预约类型,关联字典 reserve_list_reserve_type")
    @ExcelProperty("预约类型")
    private String reserveType;

    @Schema(description = "预约状态,关联字典 reserve_list_status")
    @ExcelProperty("预约状态")
    private String status;

    @Schema(description = "审核人 ID")
    @ExcelProperty("审核人 ID")
    private Long auditUserId;

    @Schema(description = "审核人名（关联 system_user.nickname）")
    @ExcelProperty("审核人名")
    private String auditUserName;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "完成时间")
    @ExcelProperty("完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "评价得分")
    @ExcelProperty("评价得分")
    private Integer score;

    @Schema(description = "审核备注")
    @ExcelProperty("审核备注")
    private String auditRemark;

    @Schema(description = "驳回理由")
    @ExcelProperty("驳回理由")
    private String rejectReason;

    @Schema(description = "评价内容")
    @ExcelProperty("评价内容")
    private String evaluateContent;

    @Schema(description = "备用字段 1")
    @ExcelProperty("备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    @ExcelProperty("备用字段 2")
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
