package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.region.earlywarnappscene;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnappscene.vo.EarlyWarnAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnappscene.EarlyWarnAppSceneDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按应用场景预警告警统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnAppSceneMapper extends BaseMapperX<EarlyWarnAppSceneDO> {

    default PageResult<EarlyWarnAppSceneDO> selectPage(EarlyWarnAppScenePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnAppSceneDO>()
                .eqIfPresent(EarlyWarnAppSceneDO::getWarnAppSceneStatId, reqVO.getWarnAppSceneStatId())
                .eqIfPresent(EarlyWarnAppSceneDO::getStatCycle, reqVO.getStatCycle())
                .eqIfPresent(EarlyWarnAppSceneDO::getSceneMajorId, reqVO.getSceneMajorId())
                .likeIfPresent(EarlyWarnAppSceneDO::getSceneMajorName, reqVO.getSceneMajorName())
                .eqIfPresent(EarlyWarnAppSceneDO::getSceneMinorId, reqVO.getSceneMinorId())
                .likeIfPresent(EarlyWarnAppSceneDO::getSceneMinorName, reqVO.getSceneMinorName())
                .eqIfPresent(EarlyWarnAppSceneDO::getTotalWarnCount, reqVO.getTotalWarnCount())
                .eqIfPresent(EarlyWarnAppSceneDO::getMainWarnType, reqVO.getMainWarnType())
                .eqIfPresent(EarlyWarnAppSceneDO::getCompletedCount, reqVO.getCompletedCount())
                .eqIfPresent(EarlyWarnAppSceneDO::getSuccessRate, reqVO.getSuccessRate())
                .eqIfPresent(EarlyWarnAppSceneDO::getGridCount, reqVO.getGridCount())
                .eqIfPresent(EarlyWarnAppSceneDO::getStatUserId, reqVO.getStatUserId())
                .betweenIfPresent(EarlyWarnAppSceneDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(EarlyWarnAppSceneDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnAppSceneDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnAppSceneDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnAppSceneDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnAppSceneDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnAppSceneDO::getId));
    }

}