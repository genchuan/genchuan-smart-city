package cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 管理后台 - 处罚复审 按月柱状图 统一响应 VO
 */
@Schema(description = "处罚复审 - 按月新增统计 响应VO")
@Data
public class PunishReviewBarResp {

    @Schema(description = "月度统计数据列表")
    private List<PunishReviewBarItemResp> list;

}
