package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.dommoncompmonrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommoncompmonrpt.vo.DomMonCompMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommoncompmonrpt.DomMonCompMonRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测部件分域监测报表 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomMonCompMonRptMapper extends BaseMapperX<DomMonCompMonRptDO> {

    default PageResult<DomMonCompMonRptDO> selectPage(DomMonCompMonRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomMonCompMonRptDO>()
                .eqIfPresent(DomMonCompMonRptDO::getRptId, reqVO.getRptId())
                .eqIfPresent(DomMonCompMonRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(DomMonCompMonRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(DomMonCompMonRptDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(DomMonCompMonRptDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(DomMonCompMonRptDO::getMonCompMajorId, reqVO.getMonCompMajorId())
                .likeIfPresent(DomMonCompMonRptDO::getMonCompMajorName, reqVO.getMonCompMajorName())
                .eqIfPresent(DomMonCompMonRptDO::getMonCompMinorId, reqVO.getMonCompMinorId())
                .likeIfPresent(DomMonCompMonRptDO::getMonCompMinorName, reqVO.getMonCompMinorName())
                .eqIfPresent(DomMonCompMonRptDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomMonCompMonRptDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomMonCompMonRptDO::getTotalDeviceCount, reqVO.getTotalDeviceCount())
                .eqIfPresent(DomMonCompMonRptDO::getDeviceOnlineRate, reqVO.getDeviceOnlineRate())
                .eqIfPresent(DomMonCompMonRptDO::getTotalUploadCount, reqVO.getTotalUploadCount())
                .eqIfPresent(DomMonCompMonRptDO::getUploadQualifiedRate, reqVO.getUploadQualifiedRate())
                .eqIfPresent(DomMonCompMonRptDO::getFaultEvtCount, reqVO.getFaultEvtCount())
                .eqIfPresent(DomMonCompMonRptDO::getCalibrateCount, reqVO.getCalibrateCount())
                .betweenIfPresent(DomMonCompMonRptDO::getRptCreateTime, reqVO.getRptCreateTime())
                .eqIfPresent(DomMonCompMonRptDO::getRptCreateUser, reqVO.getRptCreateUser())
                .eqIfPresent(DomMonCompMonRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(DomMonCompMonRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomMonCompMonRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomMonCompMonRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomMonCompMonRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomMonCompMonRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomMonCompMonRptDO::getId));
    }

}