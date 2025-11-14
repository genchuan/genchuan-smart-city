package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivgrid;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivgrid.vo.DomDivGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivgrid.DomDivGridDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 网格分域 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomDivGridMapper extends BaseMapperX<DomDivGridDO> {

    default PageResult<DomDivGridDO> selectPage(DomDivGridPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomDivGridDO>()
                .eqIfPresent(DomDivGridDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomDivGridDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomDivGridDO::getDomCode, reqVO.getDomCode())
                .eqIfPresent(DomDivGridDO::getGridType, reqVO.getGridType())
                .eqIfPresent(DomDivGridDO::getGridId, reqVO.getGridId())
                .eqIfPresent(DomDivGridDO::getGridCode, reqVO.getGridCode())
                .likeIfPresent(DomDivGridDO::getGridName, reqVO.getGridName())
                .eqIfPresent(DomDivGridDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(DomDivGridDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(DomDivGridDO::getRelPtCount, reqVO.getRelPtCount())
                .eqIfPresent(DomDivGridDO::getRelDeviceCount, reqVO.getRelDeviceCount())
                .eqIfPresent(DomDivGridDO::getGridUserId, reqVO.getGridUserId())
                .likeIfPresent(DomDivGridDO::getGridUserName, reqVO.getGridUserName())
                .eqIfPresent(DomDivGridDO::getDomStatus, reqVO.getDomStatus())
                .eqIfPresent(DomDivGridDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(DomDivGridDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(DomDivGridDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomDivGridDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomDivGridDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomDivGridDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomDivGridDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomDivGridDO::getId));
    }

}