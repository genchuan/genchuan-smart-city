package cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.selectedfieldstatusmap;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.selectedfieldstatusmap.vo.SelectedFieldStatusMapPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.selectedfieldstatusmap.SelectedFieldStatusMapDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 场景字段状态映射 Mapper
 *
 * @author lxs
 */
@Mapper
public interface SelectedFieldStatusMapMapper extends BaseMapperX<SelectedFieldStatusMapDO> {

    default PageResult<SelectedFieldStatusMapDO> selectPage(SelectedFieldStatusMapPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SelectedFieldStatusMapDO>()
                .eqIfPresent(SelectedFieldStatusMapDO::getSceneCode, reqVO.getSceneCode())
                .eqIfPresent(SelectedFieldStatusMapDO::getFieldCode, reqVO.getFieldCode())
                .eqIfPresent(SelectedFieldStatusMapDO::getRawValue, reqVO.getRawValue())
                .eqIfPresent(SelectedFieldStatusMapDO::getDisplayText, reqVO.getDisplayText())
                .betweenIfPresent(SelectedFieldStatusMapDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SelectedFieldStatusMapDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(SelectedFieldStatusMapDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(SelectedFieldStatusMapDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SelectedFieldStatusMapDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(SelectedFieldStatusMapDO::getId));
    }

    /**
     * 根据 sceneCode 查询字段状态映射
     */
    default List<SelectedFieldStatusMapDO> selectBySceneCode(String sceneCode) {
        return selectList(new LambdaQueryWrapperX<SelectedFieldStatusMapDO>()
                .eq(SelectedFieldStatusMapDO::getSceneCode, sceneCode)
                .orderByAsc(SelectedFieldStatusMapDO::getId));
    }

    /**
     * 根据 sceneCode 删除字段状态映射
     */
    default void deleteBySceneCode(String sceneCode) {
        delete(new LambdaQueryWrapperX<SelectedFieldStatusMapDO>()
                .eq(SelectedFieldStatusMapDO::getSceneCode, sceneCode));
    }
}
