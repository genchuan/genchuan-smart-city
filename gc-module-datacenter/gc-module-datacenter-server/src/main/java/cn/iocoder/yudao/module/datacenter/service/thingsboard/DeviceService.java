package cn.iocoder.yudao.module.datacenter.service.thingsboard;


import java.util.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.DeviceDO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.vo.DevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.vo.DeviceSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.DeviceInfo;
import org.thingsboard.server.common.data.id.EntityId;
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

}