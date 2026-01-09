package cn.iocoder.yudao.module.smartcity.service.vehicleinformation;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.vehicleinformation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.vehicleinformation.VehicleInformationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.vehicleinformation.VehicleInformationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 车辆信息 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class VehicleInformationServiceImpl implements VehicleInformationService {

    @Resource
    private VehicleInformationMapper vehicleInformationMapper;

    @Override
    public Long createVehicleInformation(VehicleInformationSaveReqVO createReqVO) {
        // 插入
        VehicleInformationDO vehicleInformation = BeanUtils.toBean(createReqVO, VehicleInformationDO.class);
        vehicleInformationMapper.insert(vehicleInformation);
        // 返回
        return vehicleInformation.getId();
    }

    @Override
    public void updateVehicleInformation(VehicleInformationSaveReqVO updateReqVO) {
        // 校验存在
        validateVehicleInformationExists(updateReqVO.getId());
        // 更新
        VehicleInformationDO updateObj = BeanUtils.toBean(updateReqVO, VehicleInformationDO.class);
        vehicleInformationMapper.updateById(updateObj);
    }

    @Override
    public void deleteVehicleInformation(Long id) {
        // 校验存在
        validateVehicleInformationExists(id);
        // 删除
        vehicleInformationMapper.deleteById(id);
    }

    private void validateVehicleInformationExists(Long id) {
        if (vehicleInformationMapper.selectById(id) == null) {
            throw exception(VEHICLE_INFORMATION_NOT_EXISTS);
        }
    }

    @Override
    public VehicleInformationDO getVehicleInformation(Long id) {
        return vehicleInformationMapper.selectById(id);
    }

    @Override
    public PageResult<VehicleInformationDO> getVehicleInformationPage(VehicleInformationPageReqVO pageReqVO) {
        return vehicleInformationMapper.selectPage(pageReqVO);
    }

}