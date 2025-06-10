package cn.iocoder.yudao.module.smartcity.service.vehicleabnormality;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.vehicleabnormality.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.vehicleabnormality.VehicleAbnormalityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.vehicleabnormality.VehicleAbnormalityMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 车辆异常 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class VehicleAbnormalityServiceImpl implements VehicleAbnormalityService {

    @Resource
    private VehicleAbnormalityMapper vehicleAbnormalityMapper;

    @Override
    public Long createVehicleAbnormality(VehicleAbnormalitySaveReqVO createReqVO) {
        // 插入
        VehicleAbnormalityDO vehicleAbnormality = BeanUtils.toBean(createReqVO, VehicleAbnormalityDO.class);
        vehicleAbnormalityMapper.insert(vehicleAbnormality);
        // 返回
        return vehicleAbnormality.getId();
    }

    @Override
    public void updateVehicleAbnormality(VehicleAbnormalitySaveReqVO updateReqVO) {
        // 校验存在
        validateVehicleAbnormalityExists(updateReqVO.getId());
        // 更新
        VehicleAbnormalityDO updateObj = BeanUtils.toBean(updateReqVO, VehicleAbnormalityDO.class);
        vehicleAbnormalityMapper.updateById(updateObj);
    }

    @Override
    public void deleteVehicleAbnormality(Long id) {
        // 校验存在
        validateVehicleAbnormalityExists(id);
        // 删除
        vehicleAbnormalityMapper.deleteById(id);
    }

    private void validateVehicleAbnormalityExists(Long id) {
        if (vehicleAbnormalityMapper.selectById(id) == null) {
            throw exception(VEHICLE_ABNORMALITY_NOT_EXISTS);
        }
    }

    @Override
    public VehicleAbnormalityDO getVehicleAbnormality(Long id) {
        return vehicleAbnormalityMapper.selectById(id);
    }

    @Override
    public PageResult<VehicleAbnormalityDO> getVehicleAbnormalityPage(VehicleAbnormalityPageReqVO pageReqVO) {
        return vehicleAbnormalityMapper.selectPage(pageReqVO);
    }

}