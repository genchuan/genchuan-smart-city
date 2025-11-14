package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivdevice;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivdevice.vo.DomDivDevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivdevice.vo.DomDivDeviceSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivdevice.DomDivDeviceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备分域 Service 接口
 *
 * @author zcq
 */
public interface DomDivDeviceService {

    /**
     * 创建设备分域
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomDivDevice(@Valid DomDivDeviceSaveReqVO createReqVO);

    /**
     * 更新设备分域
     *
     * @param updateReqVO 更新信息
     */
    void updateDomDivDevice(@Valid DomDivDeviceSaveReqVO updateReqVO);

    /**
     * 删除设备分域
     *
     * @param id 编号
     */
    void deleteDomDivDevice(Long id);

    /**
     * 获得设备分域
     *
     * @param id 编号
     * @return 设备分域
     */
    DomDivDeviceDO getDomDivDevice(Long id);

    /**
     * 获得设备分域分页
     *
     * @param pageReqVO 分页查询
     * @return 设备分域分页
     */
    PageResult<DomDivDeviceDO> getDomDivDevicePage(DomDivDevicePageReqVO pageReqVO);

}