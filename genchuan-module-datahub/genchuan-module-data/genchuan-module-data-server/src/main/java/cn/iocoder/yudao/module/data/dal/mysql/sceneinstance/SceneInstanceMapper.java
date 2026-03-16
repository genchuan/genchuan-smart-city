package cn.iocoder.yudao.module.data.dal.mysql.sceneinstance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.controller.admin.sceneinstance.vo.SceneInstancePageReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.sceneinstance.SceneInstanceDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 应用场景实例 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface SceneInstanceMapper extends BaseMapperX<SceneInstanceDO> {

    default PageResult<SceneInstanceDO> selectPage(SceneInstancePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SceneInstanceDO>()
                .likeIfPresent(SceneInstanceDO::getSceneName, reqVO.getSceneName())
                .eqIfPresent(SceneInstanceDO::getSceneCode, reqVO.getSceneCode())
                .eqIfPresent(SceneInstanceDO::getCategoryId, reqVO.getCategoryId())
                .likeIfPresent(SceneInstanceDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(SceneInstanceDO::getGridIds, reqVO.getGridIds())
                .likeIfPresent(SceneInstanceDO::getGridName, reqVO.getGridName())
                .eqIfPresent(SceneInstanceDO::getFacilityIds, reqVO.getFacilityIds())
                .eqIfPresent(SceneInstanceDO::getFacilities, reqVO.getFacilities())
                .eqIfPresent(SceneInstanceDO::getMonitorIds, reqVO.getMonitorIds())
                .likeIfPresent(SceneInstanceDO::getMonitorName, reqVO.getMonitorName())
                .eqIfPresent(SceneInstanceDO::getEventTypeIds, reqVO.getEventTypeIds())
                .eqIfPresent(SceneInstanceDO::getEventType, reqVO.getEventType())
                .eqIfPresent(SceneInstanceDO::getAssetIds, reqVO.getAssetIds())
                .eqIfPresent(SceneInstanceDO::getManager, reqVO.getManager())
                .eqIfPresent(SceneInstanceDO::getProcess, reqVO.getProcess())
                .eqIfPresent(SceneInstanceDO::getStatus, reqVO.getStatus())
                .eqIfPresent(SceneInstanceDO::getConfigTriggerFlag, reqVO.getConfigTriggerFlag())
                .eqIfPresent(SceneInstanceDO::getPartCount, reqVO.getPartCount())
                .eqIfPresent(SceneInstanceDO::getEventCount, reqVO.getEventCount())
                .betweenIfPresent(SceneInstanceDO::getStatusTime, reqVO.getStatusTime())
                .eqIfPresent(SceneInstanceDO::getRunLog, reqVO.getRunLog())
                .eqIfPresent(SceneInstanceDO::getRemark, reqVO.getRemark())
                .eqIfPresent(SceneInstanceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SceneInstanceDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(SceneInstanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SceneInstanceDO::getId));
    }

    // 新增方法：根据分类ID列表分页查询
    default PageResult<SceneInstanceDO> selectPageByCategoryIds(SceneInstancePageReqVO reqVO, List<String> categoryIds) {
        LambdaQueryWrapperX<SceneInstanceDO> queryWrapper = new LambdaQueryWrapperX<SceneInstanceDO>()
                .likeIfPresent(SceneInstanceDO::getSceneName, reqVO.getSceneName())
                .eqIfPresent(SceneInstanceDO::getSceneCode, reqVO.getSceneCode())
                .eqIfPresent(SceneInstanceDO::getCategoryId, reqVO.getCategoryId())
                .likeIfPresent(SceneInstanceDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(SceneInstanceDO::getGridIds, reqVO.getGridIds())
                .likeIfPresent(SceneInstanceDO::getGridName, reqVO.getGridName())
                .eqIfPresent(SceneInstanceDO::getFacilityIds, reqVO.getFacilityIds())
                .eqIfPresent(SceneInstanceDO::getFacilities, reqVO.getFacilities())
                .eqIfPresent(SceneInstanceDO::getMonitorIds, reqVO.getMonitorIds())
                .likeIfPresent(SceneInstanceDO::getMonitorName, reqVO.getMonitorName())
                .eqIfPresent(SceneInstanceDO::getEventTypeIds, reqVO.getEventTypeIds())
                .eqIfPresent(SceneInstanceDO::getEventType, reqVO.getEventType())
                .eqIfPresent(SceneInstanceDO::getAssetIds, reqVO.getAssetIds())
                .eqIfPresent(SceneInstanceDO::getManager, reqVO.getManager())
                .eqIfPresent(SceneInstanceDO::getProcess, reqVO.getProcess())
                .eqIfPresent(SceneInstanceDO::getStatus, reqVO.getStatus())
                .eqIfPresent(SceneInstanceDO::getConfigTriggerFlag, reqVO.getConfigTriggerFlag())
                .eqIfPresent(SceneInstanceDO::getPartCount, reqVO.getPartCount())
                .eqIfPresent(SceneInstanceDO::getEventCount, reqVO.getEventCount())
                .betweenIfPresent(SceneInstanceDO::getStatusTime, reqVO.getStatusTime())
                .eqIfPresent(SceneInstanceDO::getRunLog, reqVO.getRunLog())
                .eqIfPresent(SceneInstanceDO::getRemark, reqVO.getRemark())
                .eqIfPresent(SceneInstanceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SceneInstanceDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(SceneInstanceDO::getCreateTime, reqVO.getCreateTime())
                .in(SceneInstanceDO::getCategoryId, categoryIds)  // 关键：按分类ID列表查询
                .orderByDesc(SceneInstanceDO::getId);

        return selectPage(reqVO, queryWrapper);
    }

}