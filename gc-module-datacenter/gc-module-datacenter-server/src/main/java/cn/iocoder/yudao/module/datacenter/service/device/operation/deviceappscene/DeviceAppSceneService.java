package cn.iocoder.yudao.module.datacenter.service.device.operation.deviceappscene;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceappscene.vo.DeviceAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceappscene.vo.DeviceAppSceneSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.deviceappscene.DeviceAppSceneDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备关联应用场景 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceAppSceneService {

    /**
     * 创建设备关联应用场景
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceAppScene(@Valid DeviceAppSceneSaveReqVO createReqVO);

    /**
     * 更新设备关联应用场景
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceAppScene(@Valid DeviceAppSceneSaveReqVO updateReqVO);

    /**
     * 删除设备关联应用场景
     *
     * @param id 编号
     */
    void deleteDeviceAppScene(Long id);

    /**
     * 获得设备关联应用场景
     *
     * @param id 编号
     * @return 设备关联应用场景
     */
    DeviceAppSceneDO getDeviceAppScene(Long id);

    /**
     * 获得设备关联应用场景分页
     *
     * @param pageReqVO 分页查询
     * @return 设备关联应用场景分页
     */
    PageResult<DeviceAppSceneDO> getDeviceAppScenePage(DeviceAppScenePageReqVO pageReqVO);

}