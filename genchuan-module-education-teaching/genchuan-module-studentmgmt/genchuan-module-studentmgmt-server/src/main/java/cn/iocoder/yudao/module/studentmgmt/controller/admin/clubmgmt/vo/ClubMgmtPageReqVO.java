package cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 社团管理分页 Request VO")
@Data
public class ClubMgmtPageReqVO extends PageParam {

    @Schema(description = "社团名称", example = "赵六")
    private String clubName;

    @Schema(description = "社团类型：文体/学术/志愿/其他", example = "1")
    private String clubType;

    @Schema(description = "学生 ID", example = "15619")
    private Long studentId;

    @Schema(description = "入团申请时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] applyTime;

    @Schema(description = "审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "建档时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] archiveTime;

    @Schema(description = "场馆申请状态：无/待申请/已通过", example = "2")
    private String venueApplyStatus;

    @Schema(description = "状态：待审核/已通过/已建档", example = "1")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}