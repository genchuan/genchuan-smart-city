package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.StockControlDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface StockControlMapper extends BaseMapperX<StockControlDO> {

    default PageResult<StockControlDO> selectPage(StockControlPageReqVO reqVO) {
        LambdaQueryWrapperX<StockControlDO> wrapper = new LambdaQueryWrapperX<StockControlDO>()
                .eqIfPresent(StockControlDO::getCardId, reqVO.getCardId())
                .eqIfPresent(StockControlDO::getStatus, reqVO.getStatus())
                .eqIfPresent(StockControlDO::getWarnStatus, reqVO.getWarnStatus())
                .orderByDesc(StockControlDO::getId);
        if (reqVO.getStartTime() != null && reqVO.getEndTime() != null) {
            wrapper.between(StockControlDO::getCreateTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getStartTime()), java.time.ZoneId.systemDefault()),
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEndTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getStartTime() != null) {
            wrapper.ge(StockControlDO::getCreateTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getStartTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getEndTime() != null) {
            wrapper.le(StockControlDO::getCreateTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEndTime()), java.time.ZoneId.systemDefault()));
        }
        return selectPage(reqVO, wrapper);
    }

    List<StockControlDO> selectListByTimeRange(@Param("startTime") LocalDateTime startTime,
                                                @Param("endTime") LocalDateTime endTime,
                                                @Param("stationId") Long stationId);

    List<StockControlChartRespVO.DistributionItem> selectDistributionByType(@Param("startTime") LocalDateTime startTime,
                                                                             @Param("endTime") LocalDateTime endTime,
                                                                             @Param("stationId") Long stationId);

}
