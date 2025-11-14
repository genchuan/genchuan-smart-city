package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.dommonevtmonrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommonevtmonrpt.vo.DomMonEvtMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommonevtmonrpt.DomMonEvtMonRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测事件分域监测报表 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomMonEvtMonRptMapper extends BaseMapperX<DomMonEvtMonRptDO> {

    default PageResult<DomMonEvtMonRptDO> selectPage(DomMonEvtMonRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomMonEvtMonRptDO>()
                .eqIfPresent(DomMonEvtMonRptDO::getRptId, reqVO.getRptId())
                .eqIfPresent(DomMonEvtMonRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(DomMonEvtMonRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(DomMonEvtMonRptDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(DomMonEvtMonRptDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(DomMonEvtMonRptDO::getMonEvtMajorId, reqVO.getMonEvtMajorId())
                .likeIfPresent(DomMonEvtMonRptDO::getMonEvtMajorName, reqVO.getMonEvtMajorName())
                .eqIfPresent(DomMonEvtMonRptDO::getMonEvtMinorId, reqVO.getMonEvtMinorId())
                .likeIfPresent(DomMonEvtMonRptDO::getMonEvtMinorName, reqVO.getMonEvtMinorName())
                .eqIfPresent(DomMonEvtMonRptDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomMonEvtMonRptDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomMonEvtMonRptDO::getTotalEvtCount, reqVO.getTotalEvtCount())
                .eqIfPresent(DomMonEvtMonRptDO::getHighIncidenceCode, reqVO.getHighIncidenceCode())
                .likeIfPresent(DomMonEvtMonRptDO::getHighIncidenceName, reqVO.getHighIncidenceName())
                .eqIfPresent(DomMonEvtMonRptDO::getHighIncidenceRatio, reqVO.getHighIncidenceRatio())
                .eqIfPresent(DomMonEvtMonRptDO::getEvtCompleteRate, reqVO.getEvtCompleteRate())
                .eqIfPresent(DomMonEvtMonRptDO::getAvgRespEndure, reqVO.getAvgRespEndure())
                .betweenIfPresent(DomMonEvtMonRptDO::getRptCreateTime, reqVO.getRptCreateTime())
                .eqIfPresent(DomMonEvtMonRptDO::getRptCreateUser, reqVO.getRptCreateUser())
                .eqIfPresent(DomMonEvtMonRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(DomMonEvtMonRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomMonEvtMonRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomMonEvtMonRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomMonEvtMonRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomMonEvtMonRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomMonEvtMonRptDO::getId));
    }

}