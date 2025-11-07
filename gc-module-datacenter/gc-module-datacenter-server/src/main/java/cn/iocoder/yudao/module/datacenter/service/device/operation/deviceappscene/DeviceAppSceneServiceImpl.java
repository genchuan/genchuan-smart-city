package cn.iocoder.yudao.module.datacenter.service.device.operation.deviceappscene;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceappscene.vo.DeviceAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceappscene.vo.DeviceAppSceneSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.deviceappscene.DeviceAppSceneDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.operation.deviceappscene.DeviceAppSceneMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备关联应用场景 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceAppSceneServiceImpl implements DeviceAppSceneService {

    @Resource
    private DeviceAppSceneMapper deviceAppSceneMapper;

    @Override
    public Long createDeviceAppScene(DeviceAppSceneSaveReqVO createReqVO) {
        // 插入
        DeviceAppSceneDO deviceAppScene = BeanUtils.toBean(createReqVO, DeviceAppSceneDO.class);
        deviceAppSceneMapper.insert(deviceAppScene);
        // 返回
        return deviceAppScene.getId();
    }

    @Override
    public void updateDeviceAppScene(DeviceAppSceneSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceAppSceneExists(updateReqVO.getId());
        // 更新
        DeviceAppSceneDO updateObj = BeanUtils.toBean(updateReqVO, DeviceAppSceneDO.class);
        deviceAppSceneMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceAppScene(Long id) {
        // 校验存在
        validateDeviceAppSceneExists(id);
        // 删除
        deviceAppSceneMapper.deleteById(id);
    }

    private void validateDeviceAppSceneExists(Long id) {
        if (deviceAppSceneMapper.selectById(id) == null) {
            throw exception(DEVICE_APP_SCENE_NOT_EXISTS);
        }
    }

    @Override
    public DeviceAppSceneDO getDeviceAppScene(Long id) {
        return deviceAppSceneMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceAppSceneDO> getDeviceAppScenePage(DeviceAppScenePageReqVO pageReqVO) {
        return deviceAppSceneMapper.selectPage(pageReqVO);
    }

}