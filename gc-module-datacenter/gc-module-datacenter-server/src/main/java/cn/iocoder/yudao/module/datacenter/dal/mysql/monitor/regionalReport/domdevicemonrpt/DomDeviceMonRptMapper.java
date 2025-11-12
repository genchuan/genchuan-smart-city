package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.domdevicemonrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domdevicemonrpt.vo.DomDeviceMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domdevicemonrpt.DomDeviceMonRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备分域监测报表 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomDeviceMonRptMapper extends BaseMapperX<DomDeviceMonRptDO> {

    default PageResult<DomDeviceMonRptDO> selectPage(DomDeviceMonRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomDeviceMonRptDO>()
                .eqIfPresent(DomDeviceMonRptDO::getRptId, reqVO.getRptId())
                .eqIfPresent(DomDeviceMonRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(DomDeviceMonRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(DomDeviceMonRptDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(DomDeviceMonRptDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(DomDeviceMonRptDO::getDeviceMajorId, reqVO.getDeviceMajorId())
                .likeIfPresent(DomDeviceMonRptDO::getDeviceMajorName, reqVO.getDeviceMajorName())
                .eqIfPresent(DomDeviceMonRptDO::getDeviceMinorId, reqVO.getDeviceMinorId())
                .likeIfPresent(DomDeviceMonRptDO::getDeviceMinorName, reqVO.getDeviceMinorName())
                .eqIfPresent(DomDeviceMonRptDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomDeviceMonRptDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomDeviceMonRptDO::getTotalDeviceCount, reqVO.getTotalDeviceCount())
                .eqIfPresent(DomDeviceMonRptDO::getDeviceOnlineRate, reqVO.getDeviceOnlineRate())
                .eqIfPresent(DomDeviceMonRptDO::getTotalUploadFreq, reqVO.getTotalUploadFreq())
                .eqIfPresent(DomDeviceMonRptDO::getUploadQualifiedRate, reqVO.getUploadQualifiedRate())
                .eqIfPresent(DomDeviceMonRptDO::getDeviceAbnCount, reqVO.getDeviceAbnCount())
                .eqIfPresent(DomDeviceMonRptDO::getFirmwareUpdatedCount, reqVO.getFirmwareUpdatedCount())
                .eqIfPresent(DomDeviceMonRptDO::getFirmwareUpdateRate, reqVO.getFirmwareUpdateRate())
                .betweenIfPresent(DomDeviceMonRptDO::getRptCreateTime, reqVO.getRptCreateTime())
                .eqIfPresent(DomDeviceMonRptDO::getRptCreateUser, reqVO.getRptCreateUser())
                .eqIfPresent(DomDeviceMonRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(DomDeviceMonRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomDeviceMonRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomDeviceMonRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomDeviceMonRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomDeviceMonRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomDeviceMonRptDO::getId));
    }

}