package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.count.earlywarnarea;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnarea.vo.EarlyWarnAreaPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarnarea.EarlyWarnAreaDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警告警区域维度统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnAreaMapper extends BaseMapperX<EarlyWarnAreaDO> {

    default PageResult<EarlyWarnAreaDO> selectPage(EarlyWarnAreaPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnAreaDO>()
                .eqIfPresent(EarlyWarnAreaDO::getRegionStatId, reqVO.getRegionStatId())
                .eqIfPresent(EarlyWarnAreaDO::getStatCycle, reqVO.getStatCycle())
                .eqIfPresent(EarlyWarnAreaDO::getAdminLevel, reqVO.getAdminLevel())
                .eqIfPresent(EarlyWarnAreaDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(EarlyWarnAreaDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(EarlyWarnAreaDO::getTotalWarnCount, reqVO.getTotalWarnCount())
                .eqIfPresent(EarlyWarnAreaDO::getCriticalCount, reqVO.getCriticalCount())
                .eqIfPresent(EarlyWarnAreaDO::getCompletedCount, reqVO.getCompletedCount())
                .eqIfPresent(EarlyWarnAreaDO::getHandleRate, reqVO.getHandleRate())
                .eqIfPresent(EarlyWarnAreaDO::getMainWarnType, reqVO.getMainWarnType())
                .eqIfPresent(EarlyWarnAreaDO::getStatUserId, reqVO.getStatUserId())
                .betweenIfPresent(EarlyWarnAreaDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(EarlyWarnAreaDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnAreaDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnAreaDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnAreaDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnAreaDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnAreaDO::getId));
    }

}