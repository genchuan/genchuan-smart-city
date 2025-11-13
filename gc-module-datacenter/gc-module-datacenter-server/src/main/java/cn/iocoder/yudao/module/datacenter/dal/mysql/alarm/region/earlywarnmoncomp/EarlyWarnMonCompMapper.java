package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.region.earlywarnmoncomp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmoncomp.vo.EarlyWarnMonCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmoncomp.EarlyWarnMonCompDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按监测部件预警告警统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnMonCompMapper extends BaseMapperX<EarlyWarnMonCompDO> {

    default PageResult<EarlyWarnMonCompDO> selectPage(EarlyWarnMonCompPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnMonCompDO>()
                .eqIfPresent(EarlyWarnMonCompDO::getWarnMonCompStatId, reqVO.getWarnMonCompStatId())
                .eqIfPresent(EarlyWarnMonCompDO::getStatCycle, reqVO.getStatCycle())
                .eqIfPresent(EarlyWarnMonCompDO::getMonMajorId, reqVO.getMonMajorId())
                .likeIfPresent(EarlyWarnMonCompDO::getMonMajorName, reqVO.getMonMajorName())
                .eqIfPresent(EarlyWarnMonCompDO::getMonMinorId, reqVO.getMonMinorId())
                .likeIfPresent(EarlyWarnMonCompDO::getMonMinorName, reqVO.getMonMinorName())
                .eqIfPresent(EarlyWarnMonCompDO::getTotalWarnCount, reqVO.getTotalWarnCount())
                .eqIfPresent(EarlyWarnMonCompDO::getMainTriggerReason, reqVO.getMainTriggerReason())
                .eqIfPresent(EarlyWarnMonCompDO::getDeviceCount, reqVO.getDeviceCount())
                .eqIfPresent(EarlyWarnMonCompDO::getRecoveredCount, reqVO.getRecoveredCount())
                .eqIfPresent(EarlyWarnMonCompDO::getRecoveryRate, reqVO.getRecoveryRate())
                .eqIfPresent(EarlyWarnMonCompDO::getStatUserId, reqVO.getStatUserId())
                .betweenIfPresent(EarlyWarnMonCompDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(EarlyWarnMonCompDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnMonCompDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnMonCompDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnMonCompDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnMonCompDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnMonCompDO::getId));
    }

}