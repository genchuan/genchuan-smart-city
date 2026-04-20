package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 话术管理统计图表 Response VO")
@Data
public class WordingMgmtChartRespVO {

    @Schema(description = "话术类型占比数据(饼图渲染)")
    private List<Map<String, Object>> typeCountList;

    @Schema(description = "生效话术数")
    private Integer enableWordingCount;

    /**
     * 匹配率 = 话术匹配成功数 / 总咨询数。
     *
     * 占位字段:当前返回 0,因为话术匹配统计需要客服会话日志数据源(未接入)。
     * 待客服会话模块上线后接入对应统计。
     */
    @Schema(description = "匹配率,匹配成功 / 总咨询。占位字段,当前返回 0,待客服会话模块接入后启用")
    private BigDecimal matchRate;

}
