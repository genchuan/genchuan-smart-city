package cn.iocoder.yudao.module.studentmgmt.controller.admin.comparemgmt.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评比管理分页 Request VO")
@Data
public class CompareMgmtPageReqVO extends PageParam {

    @Schema(description = "班级", example = "王五")
    private String className;

    @Schema(description = "评比周期：周/月/学期")
    private String cycle;

    @Schema(description = "总得分")
    private BigDecimal totalScore;

    @Schema(description = "排名")
    private Integer rankNo;

    @Schema(description = "授予称号", example = "赵六")
    private String awardName;

    @Schema(description = "授予时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] awardTime;

    @Schema(description = "打分人")
    private String scoreUser;

    @Schema(description = "状态：打分中/已汇总", example = "2")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}