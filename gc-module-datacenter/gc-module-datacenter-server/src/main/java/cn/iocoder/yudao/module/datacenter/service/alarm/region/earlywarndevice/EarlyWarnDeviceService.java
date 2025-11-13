package cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarndevice;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarndevice.vo.EarlyWarnDevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarndevice.vo.EarlyWarnDeviceSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarndevice.EarlyWarnDeviceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 按设备分域预警告警统计 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnDeviceService {

    /**
     * 创建按设备分域预警告警统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnDevice(@Valid EarlyWarnDeviceSaveReqVO createReqVO);

    /**
     * 更新按设备分域预警告警统计
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnDevice(@Valid EarlyWarnDeviceSaveReqVO updateReqVO);

    /**
     * 删除按设备分域预警告警统计
     *
     * @param id 编号
     */
    void deleteEarlyWarnDevice(Long id);

    /**
     * 获得按设备分域预警告警统计
     *
     * @param id 编号
     * @return 按设备分域预警告警统计
     */
    EarlyWarnDeviceDO getEarlyWarnDevice(Long id);

    /**
     * 获得按设备分域预警告警统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按设备分域预警告警统计分页
     */
    PageResult<EarlyWarnDeviceDO> getEarlyWarnDevicePage(EarlyWarnDevicePageReqVO pageReqVO);

}