package cn.iocoder.yudao.module.envirhealth.service.vehicle.vehicletype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicletype.VehicleTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicletype.VehicleTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.VehicleStatusDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.VehicleTypeDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle.VehicleTypeMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.VEHICLE_TYPE_NOT_EXISTS;

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

    @Override
    public List<OptionVO> getVehicleTypeOptions() {

        List<VehicleTypeDO> list;
        list = vehicleTypeMapper.selectList(
                new LambdaQueryWrapperX<VehicleTypeDO>()
                        .eq(VehicleTypeDO::getDeleted, 0)
                        .orderByDesc(VehicleTypeDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, vehicleTypeDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(vehicleTypeDO.getName());
            vo.setValue(vehicleTypeDO.getSysVehicleTypeId());
            return vo;
        });
    }
}