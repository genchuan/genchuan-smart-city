package cn.iocoder.yudao.module.envirhealth.service.vehicle.vehicle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.VehicleController;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicle.VehicleOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicle.VehiclePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicle.VehicleSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.VehicleDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.detail.VehicleDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle.VehicleMapper;
import cn.iocoder.yudao.module.envirhealth.util.codegenerator.vehicle.VehicleCodeGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.VEHICLE_NOT_EXISTS;

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

    @Resource
    private VehicleCodeGenerator codeGenerator;

    @Override
    public Long createVehicle(VehicleSaveReqVO createReqVO) {
        // 插入
        VehicleDO vehicle = BeanUtils.toBean(createReqVO, VehicleDO.class);

        vehicle.setId(null);
        vehicle.setSysVehicleId(codeGenerator.generateVehicleId());

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

    @Override
    public List<VehicleOptionVO> getVehicleOptions() {

        List<VehicleDO> list;
        list = vehicleMapper.selectList(
                new LambdaQueryWrapperX<VehicleDO>()
                        .eq(VehicleDO::getDeleted, 0)
                        .orderByDesc(VehicleDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, vehicleDO -> {
            VehicleOptionVO vo = new VehicleOptionVO();
            vo.setLabel(vehicleDO.getLicensePlate());
            vo.setValue(vehicleDO.getSysVehicleId());
            return vo;
        });
    }
}