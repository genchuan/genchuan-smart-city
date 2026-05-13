package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.carddrill.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 周期报表图表卡片钻取-核销率 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class CycleReportChartCardDrillVerifyRateReqVO extends PageParam {
}
