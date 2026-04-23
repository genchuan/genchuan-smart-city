package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 周期报表详情 Response VO(含 detailData 明细)")
@Data
@EqualsAndHashCode(callSuper = true)
public class CycleReportDetailRespVO extends CycleReportRespVO {

    @Schema(description = "报表明细数据,包含 rescueDetail/reserveDetail/complaintDetail/findCarDetail/spacePushDetail/wordingDetail")
    private Map<String, List<Map<String, Object>>> detailData;

}
