package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.region.earlywarnasset;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnasset.vo.EarlyWarnAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnasset.EarlyWarnAssetDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按资产分域预警告警统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnAssetMapper extends BaseMapperX<EarlyWarnAssetDO> {

    default PageResult<EarlyWarnAssetDO> selectPage(EarlyWarnAssetPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnAssetDO>()
                .eqIfPresent(EarlyWarnAssetDO::getWarnAssetStatId, reqVO.getWarnAssetStatId())
                .eqIfPresent(EarlyWarnAssetDO::getStatCycle, reqVO.getStatCycle())
                .eqIfPresent(EarlyWarnAssetDO::getAssetMajorId, reqVO.getAssetMajorId())
                .likeIfPresent(EarlyWarnAssetDO::getAssetMajorName, reqVO.getAssetMajorName())
                .eqIfPresent(EarlyWarnAssetDO::getAssetMinorId, reqVO.getAssetMinorId())
                .likeIfPresent(EarlyWarnAssetDO::getAssetMinorName, reqVO.getAssetMinorName())
                .eqIfPresent(EarlyWarnAssetDO::getTotalWarnCount, reqVO.getTotalWarnCount())
                .eqIfPresent(EarlyWarnAssetDO::getMainFaultType, reqVO.getMainFaultType())
                .eqIfPresent(EarlyWarnAssetDO::getAssetCount, reqVO.getAssetCount())
                .eqIfPresent(EarlyWarnAssetDO::getMntCost, reqVO.getMntCost())
                .eqIfPresent(EarlyWarnAssetDO::getRepairedAssetCount, reqVO.getRepairedAssetCount())
                .eqIfPresent(EarlyWarnAssetDO::getRepairRate, reqVO.getRepairRate())
                .eqIfPresent(EarlyWarnAssetDO::getStatUserId, reqVO.getStatUserId())
                .betweenIfPresent(EarlyWarnAssetDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(EarlyWarnAssetDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnAssetDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnAssetDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnAssetDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnAssetDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnAssetDO::getId));
    }

}