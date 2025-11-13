package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.region.earlywarndevice;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarndevice.vo.EarlyWarnDevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarndevice.EarlyWarnDeviceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按设备分域预警告警统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnDeviceMapper extends BaseMapperX<EarlyWarnDeviceDO> {

    default PageResult<EarlyWarnDeviceDO> selectPage(EarlyWarnDevicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnDeviceDO>()
                .eqIfPresent(EarlyWarnDeviceDO::getWarnDeviceStatId, reqVO.getWarnDeviceStatId())
                .eqIfPresent(EarlyWarnDeviceDO::getStatCycle, reqVO.getStatCycle())
                .eqIfPresent(EarlyWarnDeviceDO::getDeviceMajorId, reqVO.getDeviceMajorId())
                .likeIfPresent(EarlyWarnDeviceDO::getDeviceMajorName, reqVO.getDeviceMajorName())
                .eqIfPresent(EarlyWarnDeviceDO::getDeviceModelId, reqVO.getDeviceModelId())
                .likeIfPresent(EarlyWarnDeviceDO::getDeviceModelName, reqVO.getDeviceModelName())
                .eqIfPresent(EarlyWarnDeviceDO::getTotalWarnCount, reqVO.getTotalWarnCount())
                .eqIfPresent(EarlyWarnDeviceDO::getMainFaultType, reqVO.getMainFaultType())
                .eqIfPresent(EarlyWarnDeviceDO::getDeviceCount, reqVO.getDeviceCount())
                .eqIfPresent(EarlyWarnDeviceDO::getOnlineRepairCount, reqVO.getOnlineRepairCount())
                .eqIfPresent(EarlyWarnDeviceDO::getOnlineRepairRate, reqVO.getOnlineRepairRate())
                .eqIfPresent(EarlyWarnDeviceDO::getStatUserId, reqVO.getStatUserId())
                .betweenIfPresent(EarlyWarnDeviceDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(EarlyWarnDeviceDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnDeviceDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnDeviceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnDeviceDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnDeviceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnDeviceDO::getId));
    }

}