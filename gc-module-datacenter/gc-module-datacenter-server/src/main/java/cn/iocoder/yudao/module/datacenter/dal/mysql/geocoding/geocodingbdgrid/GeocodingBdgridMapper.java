package cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingbdgrid;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingbdgrid.vo.GeocodingBdgridPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingbdgrid.GeocodingBdgridDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 北斗网格位置码配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GeocodingBdgridMapper extends BaseMapperX<GeocodingBdgridDO> {

    default PageResult<GeocodingBdgridDO> selectPage(GeocodingBdgridPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GeocodingBdgridDO>()
                .eqIfPresent(GeocodingBdgridDO::getBdGridConfigId, reqVO.getBdGridConfigId())
                .eqIfPresent(GeocodingBdgridDO::getGridLevel, reqVO.getGridLevel())
                .eqIfPresent(GeocodingBdgridDO::getCodeLength, reqVO.getCodeLength())
                .likeIfPresent(GeocodingBdgridDO::getAccuracyDesc, reqVO.getAccuracyDesc())
                .eqIfPresent(GeocodingBdgridDO::getEnableStatus, reqVO.getEnableStatus())
                .likeIfPresent(GeocodingBdgridDO::getConfigUser, reqVO.getConfigUser())
                .betweenIfPresent(GeocodingBdgridDO::getConfigTime, reqVO.getConfigTime())
                .likeIfPresent(GeocodingBdgridDO::getRemark, reqVO.getRemark())
                .likeIfPresent(GeocodingBdgridDO::getExtCategory1, reqVO.getExtCategory1())
                .likeIfPresent(GeocodingBdgridDO::getExtCategory2, reqVO.getExtCategory2())
                .likeIfPresent(GeocodingBdgridDO::getExtCategory3, reqVO.getExtCategory3())
                .betweenIfPresent(GeocodingBdgridDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GeocodingBdgridDO::getId));
    }

}