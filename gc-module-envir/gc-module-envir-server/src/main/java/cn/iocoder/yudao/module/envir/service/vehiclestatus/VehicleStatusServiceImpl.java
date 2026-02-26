package cn.iocoder.yudao.module.envir.service.vehiclestatus;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.vehiclestatus.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.vehiclestatus.VehicleStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.vehiclestatus.VehicleStatusMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 车辆状态字典 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class VehicleStatusServiceImpl implements VehicleStatusService {

    @Resource
    private VehicleStatusMapper vehicleStatusMapper;

    @Override
    public Long createVehicleStatus(VehicleStatusSaveReqVO createReqVO) {
        // 插入
        VehicleStatusDO vehicleStatus = BeanUtils.toBean(createReqVO, VehicleStatusDO.class);
        vehicleStatusMapper.insert(vehicleStatus);
        // 返回
        return vehicleStatus.getId();
    }

    @Override
    public void updateVehicleStatus(VehicleStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateVehicleStatusExists(updateReqVO.getId());
        // 更新
        VehicleStatusDO updateObj = BeanUtils.toBean(updateReqVO, VehicleStatusDO.class);
        vehicleStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteVehicleStatus(Long id) {
        // 校验存在
        validateVehicleStatusExists(id);
        // 删除
        vehicleStatusMapper.deleteById(id);
    }

    private void validateVehicleStatusExists(Long id) {
        if (vehicleStatusMapper.selectById(id) == null) {
            throw exception(VEHICLE_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public VehicleStatusDO getVehicleStatus(Long id) {
        return vehicleStatusMapper.selectById(id);
    }

    @Override
    public PageResult<VehicleStatusDO> getVehicleStatusPage(VehicleStatusPageReqVO pageReqVO) {
        return vehicleStatusMapper.selectPage(pageReqVO);
    }

}