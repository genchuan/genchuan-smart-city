package cn.iocoder.yudao.module.datacenter.service.device.operation.devicecatmng;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicecatmng.vo.DeviceCatMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicecatmng.vo.DeviceCatMngSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicecatmng.DeviceCatMngDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.operation.devicecatmng.DeviceCatMngMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备分类管理 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceCatMngServiceImpl implements DeviceCatMngService {

    @Resource
    private DeviceCatMngMapper deviceCatMngMapper;

    @Override
    public Long createDeviceCatMng(DeviceCatMngSaveReqVO createReqVO) {
        // 插入
        DeviceCatMngDO deviceCatMng = BeanUtils.toBean(createReqVO, DeviceCatMngDO.class);
        deviceCatMngMapper.insert(deviceCatMng);
        // 返回
        return deviceCatMng.getId();
    }

    @Override
    public void updateDeviceCatMng(DeviceCatMngSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceCatMngExists(updateReqVO.getId());
        // 更新
        DeviceCatMngDO updateObj = BeanUtils.toBean(updateReqVO, DeviceCatMngDO.class);
        deviceCatMngMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceCatMng(Long id) {
        // 校验存在
        validateDeviceCatMngExists(id);
        // 删除
        deviceCatMngMapper.deleteById(id);
    }

    private void validateDeviceCatMngExists(Long id) {
        if (deviceCatMngMapper.selectById(id) == null) {
            throw exception(DEVICE_CAT_MNG_NOT_EXISTS);
        }
    }

    @Override
    public DeviceCatMngDO getDeviceCatMng(Long id) {
        return deviceCatMngMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceCatMngDO> getDeviceCatMngPage(DeviceCatMngPageReqVO pageReqVO) {
        return deviceCatMngMapper.selectPage(pageReqVO);
    }

}