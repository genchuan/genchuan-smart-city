package cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.scenestatusicon;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenestatusicon.vo.SceneStatusIconPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.scenestatusicon.SceneStatusIconDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 场景状态字段图标配置 Mapper
 *
 * @author lxs
 */
@Mapper
public interface SceneStatusIconMapper extends BaseMapperX<SceneStatusIconDO> {

    default PageResult<SceneStatusIconDO> selectPage(SceneStatusIconPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SceneStatusIconDO>()
                .eqIfPresent(SceneStatusIconDO::getSceneCode, reqVO.getSceneCode())
                .eqIfPresent(SceneStatusIconDO::getFieldCode, reqVO.getFieldCode())
                .eqIfPresent(SceneStatusIconDO::getRawValue, reqVO.getRawValue())
                .eqIfPresent(SceneStatusIconDO::getIconUrl, reqVO.getIconUrl())
                .eqIfPresent(SceneStatusIconDO::getLegendText, reqVO.getLegendText())
                .betweenIfPresent(SceneStatusIconDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SceneStatusIconDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(SceneStatusIconDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(SceneStatusIconDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SceneStatusIconDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(SceneStatusIconDO::getId));
    }

    /**
     * 根据 sceneCode 查询状态字段图标配置
     */
    default List<SceneStatusIconDO> selectBySceneCode(String sceneCode) {
        return selectList(new LambdaQueryWrapperX<SceneStatusIconDO>()
                .eq(SceneStatusIconDO::getSceneCode, sceneCode)
                .orderByAsc(SceneStatusIconDO::getId));
    }

    /**
     * 根据 sceneCode 删除状态字段图标配置
     */
    default void deleteBySceneCode(String sceneCode) {
        delete(new LambdaQueryWrapperX<SceneStatusIconDO>()
                .eq(SceneStatusIconDO::getSceneCode, sceneCode));
    }
}
