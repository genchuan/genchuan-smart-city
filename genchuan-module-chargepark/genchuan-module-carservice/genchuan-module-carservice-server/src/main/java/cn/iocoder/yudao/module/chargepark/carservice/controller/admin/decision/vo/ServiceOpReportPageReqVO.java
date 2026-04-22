package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 服务运营报表分页 Request VO。客户文档架构:基于现有业务表动态生成报表,不建专表;" +
        "一次分页返回 [statTime 起~止] 区间内,按 timeScale 切分的多个时间窗报表。" +
        "例 timeScale=月 + statTime=[2025-01-01,2025-04-14] → 返 4 条(2025-01/02/03/04 各一条)")
@Data
public class ServiceOpReportPageReqVO extends PageParam {

    @Schema(description = "报表类型(日报/周报/月报/季报/半年报/年报/自定义报表)。" +
            "精确查询。不传默认不过滤(但会回显此值到响应的 reportType 字段)",
            example = "月报",
            allowableValues = {"日报", "周报", "月报", "季报", "半年报", "年报", "自定义报表"})
    private String reportType;

    @Schema(description = "时间尺度(日/周/月/季/半年/年)。决定报表的窗口切分粒度。" +
            "不传默认 月",
            example = "月",
            allowableValues = {"日", "周", "月", "季", "半年", "年"})
    private String timeScale;

    @Schema(description = "统计时间范围(长度 2 的数组:[起始时间, 结束时间])。" +
            "前端示例:axios.get(url,{params:{statTime:[start,end]}}) — 不要 JSON.stringify," +
            "最终 HTTP 是两次同名 query:?statTime=start&statTime=end。" +
            "不传默认使用 [当前时间 - 1 年, 当前时间]",
            example = "[\"2025-01-01 00:00:00\", \"2025-04-14 23:59:59\"]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statTime;

}
