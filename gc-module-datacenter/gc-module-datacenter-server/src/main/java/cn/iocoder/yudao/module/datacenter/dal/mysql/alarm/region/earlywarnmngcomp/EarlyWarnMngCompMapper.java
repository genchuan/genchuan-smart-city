package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.region.earlywarnmngcomp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmngcomp.vo.EarlyWarnMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmngcomp.EarlyWarnMngCompDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按管理部件预警告警统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnMngCompMapper extends BaseMapperX<EarlyWarnMngCompDO> {

    default PageResult<EarlyWarnMngCompDO> selectPage(EarlyWarnMngCompPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnMngCompDO>()
                .eqIfPresent(EarlyWarnMngCompDO::getWarnMngCompStatId, reqVO.getWarnMngCompStatId())
                .eqIfPresent(EarlyWarnMngCompDO::getStatCycle, reqVO.getStatCycle())
                .eqIfPresent(EarlyWarnMngCompDO::getCompMajorId, reqVO.getCompMajorId())
                .likeIfPresent(EarlyWarnMngCompDO::getCompMajorName, reqVO.getCompMajorName())
                .eqIfPresent(EarlyWarnMngCompDO::getCompMinorId, reqVO.getCompMinorId())
                .likeIfPresent(EarlyWarnMngCompDO::getCompMinorName, reqVO.getCompMinorName())
                .eqIfPresent(EarlyWarnMngCompDO::getTotalWarnCount, reqVO.getTotalWarnCount())
                .eqIfPresent(EarlyWarnMngCompDO::getMainFaultType, reqVO.getMainFaultType())
                .eqIfPresent(EarlyWarnMngCompDO::getMainRegionCode, reqVO.getMainRegionCode())
                .likeIfPresent(EarlyWarnMngCompDO::getMainRegionName, reqVO.getMainRegionName())
                .eqIfPresent(EarlyWarnMngCompDO::getCompletedCount, reqVO.getCompletedCount())
                .eqIfPresent(EarlyWarnMngCompDO::getHandleRate, reqVO.getHandleRate())
                .eqIfPresent(EarlyWarnMngCompDO::getStatUserId, reqVO.getStatUserId())
                .betweenIfPresent(EarlyWarnMngCompDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(EarlyWarnMngCompDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnMngCompDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnMngCompDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnMngCompDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnMngCompDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnMngCompDO::getId));
    }

}