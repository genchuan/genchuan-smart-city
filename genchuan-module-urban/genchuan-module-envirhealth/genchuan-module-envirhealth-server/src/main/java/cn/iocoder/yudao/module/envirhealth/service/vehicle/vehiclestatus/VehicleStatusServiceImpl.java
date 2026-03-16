package cn.iocoder.yudao.module.envirhealth.service.vehicle.vehiclestatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehiclestatus.VehicleStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehiclestatus.VehicleStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.VehicleStatusDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle.VehicleStatusMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.VEHICLE_STATUS_NOT_EXISTS;

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