package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.traceability.risksourcearch;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risksourcearch.vo.RiskSourceArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.risksourcearch.RiskSourceArchDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 风险源档案 Mapper
 *
 * @author zcq
 */
@Mapper
public interface RiskSourceArchMapper extends BaseMapperX<RiskSourceArchDO> {

    default PageResult<RiskSourceArchDO> selectPage(RiskSourceArchPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RiskSourceArchDO>()
                .eqIfPresent(RiskSourceArchDO::getRiskSourceId, reqVO.getRiskSourceId())
                .eqIfPresent(RiskSourceArchDO::getRiskSourceNo, reqVO.getRiskSourceNo())
                .likeIfPresent(RiskSourceArchDO::getRiskSourceName, reqVO.getRiskSourceName())
                .eqIfPresent(RiskSourceArchDO::getRiskSourceType, reqVO.getRiskSourceType())
                .eqIfPresent(RiskSourceArchDO::getRiskLevel, reqVO.getRiskLevel())
                .eqIfPresent(RiskSourceArchDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(RiskSourceArchDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(RiskSourceArchDO::getLocationDesc, reqVO.getLocationDesc())
                .eqIfPresent(RiskSourceArchDO::getAssetId, reqVO.getAssetId())
                .likeIfPresent(RiskSourceArchDO::getAssetName, reqVO.getAssetName())
                .eqIfPresent(RiskSourceArchDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(RiskSourceArchDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(RiskSourceArchDO::getHistoryWarnCount, reqVO.getHistoryWarnCount())
                .betweenIfPresent(RiskSourceArchDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(RiskSourceArchDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(RiskSourceArchDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(RiskSourceArchDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RiskSourceArchDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(RiskSourceArchDO::getId));
    }

}