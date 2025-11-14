package cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysisdevice;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisdevice.vo.AnalysisDevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisdevice.AnalysisDeviceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按设备分域分析研判统计 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AnalysisDeviceMapper extends BaseMapperX<AnalysisDeviceDO> {

    default PageResult<AnalysisDeviceDO> selectPage(AnalysisDevicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AnalysisDeviceDO>()
                .eqIfPresent(AnalysisDeviceDO::getStatAnalysisId, reqVO.getStatAnalysisId())
                .eqIfPresent(AnalysisDeviceDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(AnalysisDeviceDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(AnalysisDeviceDO::getDeviceMajorId, reqVO.getDeviceMajorId())
                .likeIfPresent(AnalysisDeviceDO::getDeviceMajorName, reqVO.getDeviceMajorName())
                .eqIfPresent(AnalysisDeviceDO::getDeviceMinorId, reqVO.getDeviceMinorId())
                .likeIfPresent(AnalysisDeviceDO::getDeviceMinorName, reqVO.getDeviceMinorName())
                .eqIfPresent(AnalysisDeviceDO::getTotalDeviceCount, reqVO.getTotalDeviceCount())
                .eqIfPresent(AnalysisDeviceDO::getDeviceOnlineRate, reqVO.getDeviceOnlineRate())
                .eqIfPresent(AnalysisDeviceDO::getDataUploadFreq, reqVO.getDataUploadFreq())
                .eqIfPresent(AnalysisDeviceDO::getDeviceFaultRate, reqVO.getDeviceFaultRate())
                .eqIfPresent(AnalysisDeviceDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(AnalysisDeviceDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(AnalysisDeviceDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AnalysisDeviceDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AnalysisDeviceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AnalysisDeviceDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(AnalysisDeviceDO::getId));
    }

}