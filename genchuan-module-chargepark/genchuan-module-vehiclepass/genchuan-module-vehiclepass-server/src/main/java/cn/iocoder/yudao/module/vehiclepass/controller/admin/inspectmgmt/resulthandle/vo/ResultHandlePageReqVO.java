package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 结果处置分页 Request VO")
@Data
public class ResultHandlePageReqVO extends PageParam {

    @Schema(description = "关联任务ID，关联稽查任务表inspect_task", example = "3993")
    private Long taskId;

    @Schema(description = "违规类型：违规通行/欠费逃费/其他，关联字典result_handle_violation_type", example = "1")
    private String violationType;

    @Schema(description = "处置方式：补缴费用/限制入场/警告/其他，关联字典result_handle_handle_method")
    private String handleMethod;

    @Schema(description = "状态：待审核/待处置/已完成/已驳回，关联字典result_handle_status", example = "1")
    private String status;

    @Schema(description = "片区ID，关联片区表", example = "16056")
    private Long areaId;

    @Schema(description = "处置人ID，关联芋道用户表system_user", example = "15936")
    private Long handleUserId;

    @Schema(description = "处置时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] handleTime;

    @Schema(description = "处置时间，时间戳格式")
    private String[] handleTimeNew;

    @Schema(description = "整改状态：未整改/已整改，关联字典result_handle_rectify_status", example = "2")
    private String rectifyStatus;

    @Schema(description = "驳回理由", example = "不好")
    private String rejectReason;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者，创建人账号/姓名")
    private String creator;

    @Schema(description = "更新者，更新人账号/姓名")
    private String updater;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}