package cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.field;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.field.vo.SceneFieldPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.field.SceneFieldDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 场景字段 Mapper
 *
 * @author lxs
 */
@Mapper
public interface SceneFieldMapper extends BaseMapperX<SceneFieldDO> {

    default PageResult<SceneFieldDO> selectPage(SceneFieldPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SceneFieldDO>()
                .eqIfPresent(SceneFieldDO::getSceneCode, reqVO.getSceneCode())
                .eqIfPresent(SceneFieldDO::getFieldCode, reqVO.getFieldCode())
                .eqIfPresent(SceneFieldDO::getLabel, reqVO.getLabel())
                .eqIfPresent(SceneFieldDO::getType, reqVO.getType())
                .orderByDesc(SceneFieldDO::getId));
    }

    /**
     * 根据 sceneCode 查询场景字段列表
     */
    default List<SceneFieldDO> selectBySceneCode(String sceneCode) {
        return selectList(new LambdaQueryWrapperX<SceneFieldDO>()
                .eq(SceneFieldDO::getSceneCode, sceneCode)
                .orderByAsc(SceneFieldDO::getId));
    }



    /**
     * 根据 sceneCode 删除场景字段
     */
    default void deleteBySceneCode(String sceneCode) {
        delete(new LambdaQueryWrapperX<SceneFieldDO>()
                .eq(SceneFieldDO::getSceneCode, sceneCode));
    }
}
