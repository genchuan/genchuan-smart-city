package cn.iocoder.yudao.module.data.dal.mysql.code;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.controller.admin.code.vo.GeoCodePageReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.code.GeoCodeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 地理编码 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface GeoCodeMapper extends BaseMapperX<GeoCodeDO> {

    default PageResult<GeoCodeDO> selectPage(GeoCodePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GeoCodeDO>()
                .likeIfPresent(GeoCodeDO::getCode, reqVO.getCode())
                .likeIfPresent(GeoCodeDO::getLocationName, reqVO.getLocationName())
                .eqIfPresent(GeoCodeDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(GeoCodeDO::getLayerTypeId, reqVO.getLayerTypeId())
                .eqIfPresent(GeoCodeDO::getBeidouGridCode, reqVO.getBeidouGridCode())
                .eqIfPresent(GeoCodeDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(GeoCodeDO::getLatitude, reqVO.getLatitude())
                .eqIfPresent(GeoCodeDO::getAdminCode, reqVO.getAdminCode())
                .eqIfPresent(GeoCodeDO::getUniqueCode, reqVO.getUniqueCode())
                .eqIfPresent(GeoCodeDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(GeoCodeDO::getCheckResultId, reqVO.getCheckResultId())
                .eqIfPresent(GeoCodeDO::getRuleEnableFlag, reqVO.getRuleEnableFlag())
                .eqIfPresent(GeoCodeDO::getRuleAuditStatusId, reqVO.getRuleAuditStatusId())
                .eqIfPresent(GeoCodeDO::getParentGeoCodeId, reqVO.getParentGeoCodeId())
                .eqIfPresent(GeoCodeDO::getChangeLog, reqVO.getChangeLog())
                .eqIfPresent(GeoCodeDO::getCoordVerifyFlag, reqVO.getCoordVerifyFlag())
                .eqIfPresent(GeoCodeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(GeoCodeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(GeoCodeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(GeoCodeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(GeoCodeDO::getExtCommon4, reqVO.getExtCommon4())
                .eqIfPresent(GeoCodeDO::getExtCommon5, reqVO.getExtCommon5())
                .eqIfPresent(GeoCodeDO::getExtCommon6, reqVO.getExtCommon6())
                .betweenIfPresent(GeoCodeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GeoCodeDO::getId));
    }

    default List<GeoCodeDO> selectList() {
        return selectList(new LambdaQueryWrapperX<GeoCodeDO>()
                .orderByAsc(GeoCodeDO::getId));
    }

}