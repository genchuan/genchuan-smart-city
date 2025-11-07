package cn.iocoder.yudao.module.datacenter.dal.mysql.emerplan.emerplancat;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplancat.vo.EmerPlanCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.emerplan.emerplancat.EmerPlanCatDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预案分类配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EmerPlanCatMapper extends BaseMapperX<EmerPlanCatDO> {

    default PageResult<EmerPlanCatDO> selectPage(EmerPlanCatPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmerPlanCatDO>()
                .eqIfPresent(EmerPlanCatDO::getPlanCatId, reqVO.getPlanCatId())
                .eqIfPresent(EmerPlanCatDO::getParentCatId, reqVO.getParentCatId())
                .eqIfPresent(EmerPlanCatDO::getCatLevel, reqVO.getCatLevel())
                .eqIfPresent(EmerPlanCatDO::getCatCode, reqVO.getCatCode())
                .likeIfPresent(EmerPlanCatDO::getCatName, reqVO.getCatName())
                .eqIfPresent(EmerPlanCatDO::getCatDesc, reqVO.getCatDesc())
                .eqIfPresent(EmerPlanCatDO::getApplyRegionCode, reqVO.getApplyRegionCode())
                .likeIfPresent(EmerPlanCatDO::getApplyRegionName, reqVO.getApplyRegionName())
                .orderByDesc(EmerPlanCatDO::getId));
    }

}