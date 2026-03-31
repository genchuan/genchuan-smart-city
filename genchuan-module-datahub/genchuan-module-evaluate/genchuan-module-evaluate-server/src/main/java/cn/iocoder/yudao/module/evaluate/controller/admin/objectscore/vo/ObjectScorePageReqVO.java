package cn.iocoder.yudao.module.evaluate.controller.admin.objectscore.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 公司得分分页 Request VO")
@Data
public class ObjectScorePageReqVO extends PageParam {

    @Schema(description = "对象ID (关联eval_object.id)", example = "20118")
    private Long objectId;

    @Schema(description = "体系ID (关联eval_index_system.id)", example = "7259")
    private Long systemId;

    @Schema(description = "巡检人ID(关联sys_user.id)", example = "17976")
    private Long userId;

    @Schema(description = "总得分")
    private Long score;

    @Schema(description = "状态: 1：待审核中，2：审核通过，3：不用审核", example = "1")
    private String status;

    @Schema(description = "评价说明")
    private String details;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

    @Schema(description = "变更日志")
    private String changeLog;

}