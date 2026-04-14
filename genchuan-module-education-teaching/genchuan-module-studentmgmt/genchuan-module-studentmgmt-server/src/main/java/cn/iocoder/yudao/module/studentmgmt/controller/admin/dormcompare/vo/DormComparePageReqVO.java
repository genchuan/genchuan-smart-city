package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcompare.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 宿舍评比分页 Request VO")
@Data
public class DormComparePageReqVO extends PageParam {

    @Schema(description = "宿舍 ID", example = "8217")
    private Long dormId;

    @Schema(description = "宿舍号")
    private String dormNum;

    @Schema(description = "评比周期：周/月/学期")
    private String cycle;

    @Schema(description = "得分")
    private BigDecimal score;

    @Schema(description = "排名")
    private Integer rankNo;

    @Schema(description = "打分人")
    private String scoreUser;

    @Schema(description = "汇总时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] sumTime;

    @Schema(description = "推送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] pushTime;

    @Schema(description = "状态：打分中/已汇总", example = "1")
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