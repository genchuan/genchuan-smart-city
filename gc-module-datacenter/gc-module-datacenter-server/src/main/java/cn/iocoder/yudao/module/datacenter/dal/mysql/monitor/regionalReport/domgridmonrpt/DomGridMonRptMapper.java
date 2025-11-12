package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.domgridmonrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domgridmonrpt.vo.DomGridMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domgridmonrpt.DomGridMonRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 网格分域监测报表 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomGridMonRptMapper extends BaseMapperX<DomGridMonRptDO> {

    default PageResult<DomGridMonRptDO> selectPage(DomGridMonRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomGridMonRptDO>()
                .eqIfPresent(DomGridMonRptDO::getRptId, reqVO.getRptId())
                .eqIfPresent(DomGridMonRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(DomGridMonRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(DomGridMonRptDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(DomGridMonRptDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(DomGridMonRptDO::getGridType, reqVO.getGridType())
                .eqIfPresent(DomGridMonRptDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomGridMonRptDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomGridMonRptDO::getGridCode, reqVO.getGridCode())
                .likeIfPresent(DomGridMonRptDO::getGridUserName, reqVO.getGridUserName())
                .eqIfPresent(DomGridMonRptDO::getMonPtCount, reqVO.getMonPtCount())
                .eqIfPresent(DomGridMonRptDO::getDeviceOnlineRate, reqVO.getDeviceOnlineRate())
                .eqIfPresent(DomGridMonRptDO::getEvtTotalCount, reqVO.getEvtTotalCount())
                .eqIfPresent(DomGridMonRptDO::getEvtCompleteRate, reqVO.getEvtCompleteRate())
                .betweenIfPresent(DomGridMonRptDO::getRptCreateTime, reqVO.getRptCreateTime())
                .eqIfPresent(DomGridMonRptDO::getRptCreateUser, reqVO.getRptCreateUser())
                .eqIfPresent(DomGridMonRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(DomGridMonRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomGridMonRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomGridMonRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomGridMonRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomGridMonRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomGridMonRptDO::getId));
    }

}