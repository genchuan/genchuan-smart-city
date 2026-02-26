package cn.iocoder.yudao.module.envir.service.vehicletype;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.vehicletype.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.vehicletype.VehicleTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.vehicletype.VehicleTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 车辆类型字典 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class VehicleTypeServiceImpl implements VehicleTypeService {

    @Resource
    private VehicleTypeMapper vehicleTypeMapper;

    @Override
    public Long createVehicleType(VehicleTypeSaveReqVO createReqVO) {
        // 插入
        VehicleTypeDO vehicleType = BeanUtils.toBean(createReqVO, VehicleTypeDO.class);
        vehicleTypeMapper.insert(vehicleType);
        // 返回
        return vehicleType.getId();
    }

    @Override
    public void updateVehicleType(VehicleTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateVehicleTypeExists(updateReqVO.getId());
        // 更新
        VehicleTypeDO updateObj = BeanUtils.toBean(updateReqVO, VehicleTypeDO.class);
        vehicleTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteVehicleType(Long id) {
        // 校验存在
        validateVehicleTypeExists(id);
        // 删除
        vehicleTypeMapper.deleteById(id);
    }

    private void validateVehicleTypeExists(Long id) {
        if (vehicleTypeMapper.selectById(id) == null) {
            throw exception(VEHICLE_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public VehicleTypeDO getVehicleType(Long id) {
        return vehicleTypeMapper.selectById(id);
    }

    @Override
    public PageResult<VehicleTypeDO> getVehicleTypePage(VehicleTypePageReqVO pageReqVO) {
        return vehicleTypeMapper.selectPage(pageReqVO);
    }

}