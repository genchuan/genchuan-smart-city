package cn.iocoder.yudao.module.park.dal.mysql.park.statrpt.chargeabnormal;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.statrpt.chargeabnormal.vo.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.statrpt.chargeabnormal.ChargeAbnormalDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 收费异常 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ChargeAbnormalMapper extends BaseMapperX<ChargeAbnormalDO> {

    default PageResult<ChargeAbnormalDO> selectPage(ChargeAbnormalPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChargeAbnormalDO>()
                .eqIfPresent(ChargeAbnormalDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(ChargeAbnormalDO::getCarNumber, reqVO.getCarNumber())
                .eqIfPresent(ChargeAbnormalDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(ChargeAbnormalDO::getLotId, reqVO.getLotId())
                .betweenIfPresent(ChargeAbnormalDO::getAbnormalTime, reqVO.getAbnormalTime())
                .eqIfPresent(ChargeAbnormalDO::getAbnormalAmount, reqVO.getAbnormalAmount())
                .eqIfPresent(ChargeAbnormalDO::getAbnormalType, reqVO.getAbnormalType())
                .eqIfPresent(ChargeAbnormalDO::getAbnormalReason, reqVO.getAbnormalReason())
                .eqIfPresent(ChargeAbnormalDO::getDisposalStatus, reqVO.getDisposalStatus())
                .eqIfPresent(ChargeAbnormalDO::getDisposalResult, reqVO.getDisposalResult())
                .eqIfPresent(ChargeAbnormalDO::getDisposalBy, reqVO.getDisposalBy())
                .betweenIfPresent(ChargeAbnormalDO::getDisposalTime, reqVO.getDisposalTime())
                .eqIfPresent(ChargeAbnormalDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(ChargeAbnormalDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ChargeAbnormalDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ChargeAbnormalDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ChargeAbnormalDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ChargeAbnormalDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ChargeAbnormalDO::getId));
    }

    /**
     * 收费异常统计报表
     *
     * @param reqVO 查询条件
     * @return 统计结果 Map
     */
    Map<String, Object> selectStatReport(StatReportReqVO reqVO);

    List<TrendPointVO> selectAbnormalAmountTrend(StatReportReqVO reqVO);

    List<TrendPointVO> selectAbnormalOrderCount(StatReportReqVO reqVO);

    /**
     * 按异常原因分组统计笔数
     */
    List<StatDistributionRespVO> selectStatBySortField(StatReportReqVO reqVO);

    List<StatRegionRespVO> selectStatByRegion(StatReportReqVO reqVO);

    List<ChargeAbnormalDO> selectPageByCondition(StatReportReqVO reqVO);

    Long selectCountByCondition(StatReportReqVO reqVO);
}
