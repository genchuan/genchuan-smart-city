package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivadmin;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivadmin.vo.DomDivAdminPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivadmin.DomDivAdminDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 行政区划分域 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomDivAdminMapper extends BaseMapperX<DomDivAdminDO> {

    default PageResult<DomDivAdminDO> selectPage(DomDivAdminPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomDivAdminDO>()
                .eqIfPresent(DomDivAdminDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomDivAdminDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomDivAdminDO::getDomCode, reqVO.getDomCode())
                .eqIfPresent(DomDivAdminDO::getAdminLevel, reqVO.getAdminLevel())
                .eqIfPresent(DomDivAdminDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(DomDivAdminDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(DomDivAdminDO::getRelPtCount, reqVO.getRelPtCount())
                .eqIfPresent(DomDivAdminDO::getRelDeviceCount, reqVO.getRelDeviceCount())
                .eqIfPresent(DomDivAdminDO::getMngrId, reqVO.getMngrId())
                .likeIfPresent(DomDivAdminDO::getMngrName, reqVO.getMngrName())
                .eqIfPresent(DomDivAdminDO::getDomStatus, reqVO.getDomStatus())
                .eqIfPresent(DomDivAdminDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(DomDivAdminDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(DomDivAdminDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomDivAdminDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomDivAdminDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomDivAdminDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomDivAdminDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomDivAdminDO::getId));
    }

}