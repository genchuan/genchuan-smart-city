package cn.iocoder.yudao.module.kitchen.service.sysdevice;

import java.util.*;

import cn.iocoder.yudao.module.kitchen.controller.admin.sysdevice.vo.SysDevicePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.sysdevice.vo.SysDeviceSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.sysdevice.SysDeviceDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 设备信息 Service 接口
 *
 * @author 亘川智城
 */
public interface SysDeviceService {

    /**
     * 创建设备信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSysDevice(@Valid SysDeviceSaveReqVO createReqVO);

    /**
     * 更新设备信息
     *
     * @param updateReqVO 更新信息
     */
    void updateSysDevice(@Valid SysDeviceSaveReqVO updateReqVO);

    /**
     * 删除设备信息
     *
     * @param id 编号
     */
    void deleteSysDevice(Long id);

    /**
    * 批量删除设备信息
    *
    * @param ids 编号
    */
    void deleteSysDeviceListByIds(List<Long> ids);

    /**
     * 获得设备信息
     *
     * @param id 编号
     * @return 设备信息
     */
    SysDeviceDO getSysDevice(Long id);

    /**
     * 获得设备信息分页
     *
     * @param pageReqVO 分页查询
     * @return 设备信息分页
     */
    PageResult<SysDeviceDO> getSysDevicePage(SysDevicePageReqVO pageReqVO);

}
