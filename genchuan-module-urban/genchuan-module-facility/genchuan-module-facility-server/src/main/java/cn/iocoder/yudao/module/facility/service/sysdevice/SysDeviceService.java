package cn.iocoder.yudao.module.facility.service.sysdevice;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.facility.controller.admin.sysdevice.vo.SysDevicePageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.sysdevice.vo.SysDeviceSaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysdevice.SysDeviceDO;
import jakarta.validation.Valid;

/**
 * 设备 Service 接口
 *
 * @author 亘川智城
 */
public interface SysDeviceService {

    /**
     * 创建设备
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSysDevice(@Valid SysDeviceSaveReqVO createReqVO);

    /**
     * 更新设备
     *
     * @param updateReqVO 更新信息
     */
    void updateSysDevice(@Valid SysDeviceSaveReqVO updateReqVO);

    /**
     * 删除设备
     *
     * @param id 编号
     */
    void deleteSysDevice(Long id);

    /**
     * 获得设备
     *
     * @param id 编号
     * @return 设备
     */
    SysDeviceDO getSysDevice(Long id);

    /**
     * 获得设备分页
     *
     * @param pageReqVO 分页查询
     * @return 设备分页
     */
    PageResult<SysDeviceDO> getSysDevicePage(SysDevicePageReqVO pageReqVO);

}
