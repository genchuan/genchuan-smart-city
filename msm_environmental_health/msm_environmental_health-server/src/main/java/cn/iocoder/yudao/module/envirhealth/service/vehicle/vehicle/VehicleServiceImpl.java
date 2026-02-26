package cn.iocoder.yudao.module.envirhealth.service.vehicle.vehicle;

import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicle.VehiclePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicle.VehicleSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.detail.VehicleDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.VehicleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle.VehicleMapper;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 车辆 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class VehicleServiceImpl implements VehicleService {

    @Resource
    private VehicleMapper vehicleMapper;

    @Override
    public Long createVehicle(VehicleSaveReqVO createReqVO) {
        // 插入
        VehicleDO vehicle = BeanUtils.toBean(createReqVO, VehicleDO.class);
        vehicleMapper.insert(vehicle);
        // 返回
        return vehicle.getId();
    }

    @Override
    public void updateVehicle(VehicleSaveReqVO updateReqVO) {
        // 校验存在
        validateVehicleExists(updateReqVO.getId());
        // 更新
        VehicleDO updateObj = BeanUtils.toBean(updateReqVO, VehicleDO.class);
        vehicleMapper.updateById(updateObj);
    }

    @Override
    public void deleteVehicle(Long id) {
        // 校验存在
        validateVehicleExists(id);
        // 删除
        vehicleMapper.deleteById(id);
    }

    private void validateVehicleExists(Long id) {
        if (vehicleMapper.selectById(id) == null) {
            throw exception(VEHICLE_NOT_EXISTS);
        }
    }

    @Override
    public VehicleDO getVehicle(Long id) {
        return vehicleMapper.selectById(id);
    }

    @Override
    public PageResult<VehicleDO> getVehiclePage(VehiclePageReqVO pageReqVO) {
        return vehicleMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<VehicleDetailDO> getVehicleDetailPage(VehiclePageReqVO pageReqVO) {
        Long total = vehicleMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<VehicleDetailDO> list = vehicleMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }
}