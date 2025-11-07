package cn.iocoder.yudao.module.datacenter.dal.mysql.resschedmanage.emerdevicecat;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdevicecat.vo.EmerDeviceCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerdevicecat.EmerDeviceCatDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 救援设备分类配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EmerDeviceCatMapper extends BaseMapperX<EmerDeviceCatDO> {

    default PageResult<EmerDeviceCatDO> selectPage(EmerDeviceCatPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmerDeviceCatDO>()
                .eqIfPresent(EmerDeviceCatDO::getDeviceCatId, reqVO.getDeviceCatId())
                .eqIfPresent(EmerDeviceCatDO::getParentCatId, reqVO.getParentCatId())
                .eqIfPresent(EmerDeviceCatDO::getCatLevel, reqVO.getCatLevel())
                .eqIfPresent(EmerDeviceCatDO::getCatCode, reqVO.getCatCode())
                .likeIfPresent(EmerDeviceCatDO::getCatName, reqVO.getCatName())
                .eqIfPresent(EmerDeviceCatDO::getCatDesc, reqVO.getCatDesc())
                .eqIfPresent(EmerDeviceCatDO::getApplyRegionCode, reqVO.getApplyRegionCode())
                .likeIfPresent(EmerDeviceCatDO::getApplyRegionName, reqVO.getApplyRegionName())
                .orderByDesc(EmerDeviceCatDO::getId));
    }

}