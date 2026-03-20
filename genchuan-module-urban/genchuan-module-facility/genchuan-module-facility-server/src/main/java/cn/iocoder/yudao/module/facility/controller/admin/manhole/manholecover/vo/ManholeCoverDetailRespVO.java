package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover.vo;

import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.ManholeMonitorStatsRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.ManholeMonitorVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.disposalorder.DisposalOrderDO;
import groovy.transform.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 窨井盖设施详情 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ManholeCoverDetailRespVO extends ManholeMonitorVO {


    @Schema(description = "近 24 小时监测统计数据")
    private ManholeMonitorStatsRespVO manholeMonitorStatsRespVO;

    private List<DisposalOrderDO> disposalOrderDOList;

}
