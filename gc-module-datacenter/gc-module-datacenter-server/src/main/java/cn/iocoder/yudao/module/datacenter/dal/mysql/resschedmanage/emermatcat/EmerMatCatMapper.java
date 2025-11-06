package cn.iocoder.yudao.module.datacenter.dal.mysql.resschedmanage.emermatcat;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatcat.vo.EmerMatCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emermatcat.EmerMatCatDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 应急物资分类配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EmerMatCatMapper extends BaseMapperX<EmerMatCatDO> {

    default PageResult<EmerMatCatDO> selectPage(EmerMatCatPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmerMatCatDO>()
                .eqIfPresent(EmerMatCatDO::getMatCatId, reqVO.getMatCatId())
                .eqIfPresent(EmerMatCatDO::getParentCatId, reqVO.getParentCatId())
                .eqIfPresent(EmerMatCatDO::getCatLevel, reqVO.getCatLevel())
                .eqIfPresent(EmerMatCatDO::getCatCode, reqVO.getCatCode())
                .likeIfPresent(EmerMatCatDO::getCatName, reqVO.getCatName())
                .eqIfPresent(EmerMatCatDO::getCatDesc, reqVO.getCatDesc())
                .eqIfPresent(EmerMatCatDO::getApplyRegionCode, reqVO.getApplyRegionCode())
                .likeIfPresent(EmerMatCatDO::getApplyRegionName, reqVO.getApplyRegionName())
                .orderByDesc(EmerMatCatDO::getId));
    }

}