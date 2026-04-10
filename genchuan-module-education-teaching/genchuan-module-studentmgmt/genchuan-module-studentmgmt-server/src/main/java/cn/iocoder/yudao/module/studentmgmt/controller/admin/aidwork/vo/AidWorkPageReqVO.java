package cn.iocoder.yudao.module.studentmgmt.controller.admin.aidwork.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 奖助勤贷分页 Request VO")
@Data
public class AidWorkPageReqVO extends PageParam {

    @Schema(description = "学生 ID", example = "10682")
    private Long studentId;

    @Schema(description = "资助类型：奖学金/助学金/助学贷款/勤工俭学", example = "1")
    private String aidType;

    @Schema(description = "申请金额")
    private BigDecimal applyAmount;

    @Schema(description = "申报时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] applyTime;

    @Schema(description = "审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "流程状态：跟进中/已完成", example = "2")
    private String processStatus;

    @Schema(description = "状态：待审核/已通过/已完成", example = "2")
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