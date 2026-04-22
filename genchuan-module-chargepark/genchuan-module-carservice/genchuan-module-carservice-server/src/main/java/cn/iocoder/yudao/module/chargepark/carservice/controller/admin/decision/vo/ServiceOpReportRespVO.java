package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 服务运营报表响应 VO(单条报表记录)
 *
 * 动态生成,不对应任何 DB 表。每次分页调用按 timeScale 切分出多个报表,每条对应一个时间窗口聚合。
 */
@Schema(description = "服务运营报表 RespVO")
@Data
public class ServiceOpReportRespVO {

    @Schema(description = "虚拟报表 ID(由 reportType+statPeriod 稳定 hash 得到,不对应 DB 持久化记录)",
            example = "2025031234567")
    private Long id;

    @Schema(description = "报表类型", example = "月报")
    private String reportType;

    @Schema(description = "时间尺度", example = "月")
    private String timeScale;

    @Schema(description = "统计周期(如 2025-03 / 2025-W15 / 2025-Q1 / 2025-H1 / 2025)", example = "2025-03")
    private String statPeriod;

    @Schema(description = "生成时间(此刻动态生成)")
    private LocalDateTime createTime;

    @Schema(description = "救援完成率 = 已完成救援数 / 本期救援总数", example = "0.89")
    private BigDecimal rescueFinishRate;

    @Schema(description = "预约成功率 = (已生效+已完成) 预约数 / 本期预约总数", example = "0.95")
    private BigDecimal reserveSuccessRate;

    @Schema(description = "投诉处理率 = (建议+申诉+调解) 已完成数 / 三者总数", example = "0.92")
    private BigDecimal complaintHandleRate;

    @Schema(description = "同比分析数据(vs 去年同期)。key 为指标名,value 为差值(当前-去年同期),正=增长/负=下降",
            example = "{\"rescueFinishRate\":0.05,\"reserveSuccessRate\":0.03}")
    @JsonProperty("同比Data")
    private Map<String, BigDecimal> yoyData;

    @Schema(description = "环比分析数据(vs 上一周期)。key 同上,value 为当前-上期差值",
            example = "{\"rescueFinishRate\":0.02,\"reserveSuccessRate\":0.01}")
    @JsonProperty("环比Data")
    private Map<String, BigDecimal> qoqData;

    @Schema(description = "创建者", example = "system")
    private String creator;

}
