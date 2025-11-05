package cn.iocoder.yudao.module.datacenter.dal.mysql.emerplan.emerplanlib;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplanlib.vo.EmerPlanLibPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.emerplan.emerplanlib.EmerPlanLibDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预案库 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EmerPlanLibMapper extends BaseMapperX<EmerPlanLibDO> {

    default PageResult<EmerPlanLibDO> selectPage(EmerPlanLibPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmerPlanLibDO>()
                .eqIfPresent(EmerPlanLibDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(EmerPlanLibDO::getPlanNo, reqVO.getPlanNo())
                .likeIfPresent(EmerPlanLibDO::getPlanName, reqVO.getPlanName())
                .eqIfPresent(EmerPlanLibDO::getCatId, reqVO.getCatId())
                .likeIfPresent(EmerPlanLibDO::getCatName, reqVO.getCatName())
                .eqIfPresent(EmerPlanLibDO::getApplyRegionCode, reqVO.getApplyRegionCode())
                .likeIfPresent(EmerPlanLibDO::getApplyRegionName, reqVO.getApplyRegionName())
                .eqIfPresent(EmerPlanLibDO::getPlanVersion, reqVO.getPlanVersion())
                .betweenIfPresent(EmerPlanLibDO::getEffectiveTime, reqVO.getEffectiveTime())
                .betweenIfPresent(EmerPlanLibDO::getExpireTime, reqVO.getExpireTime())
                .eqIfPresent(EmerPlanLibDO::getPlanDocPath, reqVO.getPlanDocPath())
                .orderByDesc(EmerPlanLibDO::getId));
    }

}