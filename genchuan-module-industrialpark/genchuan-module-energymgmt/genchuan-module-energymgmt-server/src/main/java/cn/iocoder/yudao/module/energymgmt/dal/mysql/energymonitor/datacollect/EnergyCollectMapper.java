package cn.iocoder.yudao.module.energymgmt.dal.mysql.energymonitor.datacollect;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.energymgmt.dal.dataobject.energymonitor.datacollect.EnergyCollectDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.datacollect.vo.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 能耗采集 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EnergyCollectMapper extends BaseMapperX<EnergyCollectDO> {

    default PageResult<EnergyCollectDO> selectPage(EnergyCollectPageReqVO reqVO) {
        return null;
    }

    Long selectDeviceCount(LocalDateTime start, LocalDateTime end);

    Long selectNormalCount(LocalDateTime start, LocalDateTime end);

    Long selectExceptionCount(LocalDateTime start, LocalDateTime end);

    BigDecimal selectTotalEnergy(LocalDateTime start, LocalDateTime end);

    List<EnergyCollectChartRespVO.RealTimeTrendVO> selectRealTimeTrend(LocalDateTime start, LocalDateTime end, String granularity);

    List<EnergyCollectChartRespVO.PeriodTrendVO> selectPeriodTrend(LocalDateTime start, LocalDateTime end, String granularity);
}