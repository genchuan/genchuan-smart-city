package cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.base;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.base.vo.UniversalScenePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.base.UniversalSceneDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 通用场景表，一级和二级场景 Mapper
 *
 * @author lxs
 */
@Mapper
public interface UniversalSceneMapper extends BaseMapperX<UniversalSceneDO> {

    default PageResult<UniversalSceneDO> selectPage(UniversalScenePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UniversalSceneDO>()
                .eqIfPresent(UniversalSceneDO::getSceneCode, reqVO.getSceneCode())
                .eqIfPresent(UniversalSceneDO::getParentId, reqVO.getParentId())
                .eqIfPresent(UniversalSceneDO::getLevel, reqVO.getLevel())
                .eqIfPresent(UniversalSceneDO::getDescription, reqVO.getDescription())
                .eqIfPresent(UniversalSceneDO::getLabel, reqVO.getLabel())
                .eqIfPresent(UniversalSceneDO::getValue, reqVO.getValue())
                .betweenIfPresent(UniversalSceneDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(UniversalSceneDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(UniversalSceneDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(UniversalSceneDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(UniversalSceneDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(UniversalSceneDO::getId));
    }

}
