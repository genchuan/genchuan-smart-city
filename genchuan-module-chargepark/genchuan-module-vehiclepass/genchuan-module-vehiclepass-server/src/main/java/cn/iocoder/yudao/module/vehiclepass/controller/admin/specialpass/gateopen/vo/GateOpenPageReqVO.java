package cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo;

import lombok.*;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 开闸管理分页 Request VO")
@Data
public class GateOpenPageReqVO extends PageParam {

    @Schema(description = "场站ID，关联场站表", example = "5128")
    private Long stationId;

    @Schema(description = "场站名称，支持模糊查询")
    private String stationName;

    @Schema(description = "开闸原因：紧急通行 / 故障处理 / 其他，关联字典gate_open_open_reason", example = "不对")
    private String openReason;

    @Schema(description = "申请人ID，关联system_user用户表", example = "12006")
    private Long applyUserId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "申请时间，时间范围", example = "2026-04-20 00:00:00,2026-04-23 23:59:59")
    private LocalDateTime[] applyTime;

    @Schema(description = "状态：待审批 / 已通过 / 已驳回 / 已执行，关联字典gate_open_status", example = "1")
    private String status;

    @Schema(description = "审批人ID，关联system_user用户表", example = "30477")
    private Long auditUserId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "审批时间")
    private LocalDateTime[] auditTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "执行时间")
    private LocalDateTime[] executeTime;

    @Schema(description = "驳回理由", example = "不喜欢")
    private String rejectReason;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime[] createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    private LocalDateTime[] updateTime;

}