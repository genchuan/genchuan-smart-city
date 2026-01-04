package cn.iocoder.yudao.module.datacenter.service.thingsboard.device;


import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.AlarmRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.DevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.DeviceSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.DeviceInfo;
import org.thingsboard.server.common.data.kv.AttributeKvEntry;

/**
 * 设备 Service 接口
 *
 * @author 芋道源码
 */
public interface DeviceService {

    /**
     * 创建设备
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    String createDevice(@Valid DeviceSaveReqVO createReqVO);

    /**
     * 更新设备
     *
     * @param updateReqVO 更新信息
     */
    void updateDevice(@Valid DeviceSaveReqVO updateReqVO);

    /**
     * 删除设备
     *
     * @param id 编号
     */
    void deleteDevice(String id);

    /**
     * 批量删除设备
     *
     * @param ids 编号
     */
    void deleteDeviceListByIds(List<String> ids);

    /**
     * 获得设备
     *
     * @param id 编号
     * @return 设备
     */
    DeviceInfo getDevice(String id);

    /**
     * 获得设备分页
     *
     * @param pageReqVO 分页查询
     * @return 设备分页
     */
    PageResult<Device> getDevicePage(DevicePageReqVO pageReqVO);

    public List<AttributeKvEntry> getAttributeKvEntries(String id);

    /**
     * 获取告警列表（带分页）
     */
    PageResult<AlarmRespVO> getAlarmPage(Integer pageSize, Integer page);

    /**
     * 同步ThingsBoard设备到本地数据库
     * @return 同步结果信息
     */
    Map<String, Object> syncDevicesFromThingsBoard();

    /**
     * 获取设备分页（包含完整信息）
     * @param pageSize 每页大小
     * @param page 页码
     * @return 设备分页数据
     */
    PageResult<DeviceInfo> getDevicePageWithDetails(Integer pageSize, Integer page);
}