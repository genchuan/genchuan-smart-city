package cn.iocoder.yudao.module.studentmgmt.controller.admin.parentreply.vo;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartTrendVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 家长回复标记已读 Request VO")
@Data
public class ParentReplyChartRespVO {

    @Schema(description = "总回复数", example = "1782")
    private Integer totalReplyCount;
    @Schema(description = "未读回复数", example = "1782")
    private Integer unreadReplyCount;
    @Schema(description = "平均回复时长", example = "1782")
    private String avgReplyDuration;
    @Schema(description = "回复完成率", example = "1782")
    private BigDecimal replyFinishRate;
    @Schema(description = "近一周回复趋势")
    private List<ChartTrendVO> recentWeekReplyTrend;

}