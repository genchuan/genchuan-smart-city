package cn.iocoder.yudao.module.smartcity.dal.mysql.municipalpublicutilities;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.municipalpublicutilities.MunicipalPublicUtilitiesDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.municipalpublicutilities.vo.*;

/**
 * 市政公用 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface MunicipalPublicUtilitiesMapper extends BaseMapperX<MunicipalPublicUtilitiesDO> {

    default PageResult<MunicipalPublicUtilitiesDO> selectPage(MunicipalPublicUtilitiesPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MunicipalPublicUtilitiesDO>()
                .eqIfPresent(MunicipalPublicUtilitiesDO::getNameMunicipalFacilities, reqVO.getNameMunicipalFacilities())
                .eqIfPresent(MunicipalPublicUtilitiesDO::getFacilityType, reqVO.getFacilityType())
                .likeIfPresent(MunicipalPublicUtilitiesDO::getFacilityLocation, reqVO.getFacilityLocation())
                .betweenIfPresent(MunicipalPublicUtilitiesDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MunicipalPublicUtilitiesDO::getId));
    }

}