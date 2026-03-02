package cn.iocoder.yudao.module.envirhealth.service.vehicle.maintenancetype;

import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.maintenancetype.MaintenanceTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.maintenancetype.MaintenanceTypeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.MaintenanceTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle.MaintenanceTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 维护类型字典表【通用复用】 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MaintenanceTypeServiceImpl implements MaintenanceTypeService {

    @Resource
    private MaintenanceTypeMapper maintenanceTypeMapper;

    @Override
    public Long createMaintenanceType(MaintenanceTypeSaveReqVO createReqVO) {
        // 插入
        MaintenanceTypeDO maintenanceType = BeanUtils.toBean(createReqVO, MaintenanceTypeDO.class);
        maintenanceTypeMapper.insert(maintenanceType);
        // 返回
        return maintenanceType.getId();
    }

    @Override
    public void updateMaintenanceType(MaintenanceTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateMaintenanceTypeExists(updateReqVO.getId());
        // 更新
        MaintenanceTypeDO updateObj = BeanUtils.toBean(updateReqVO, MaintenanceTypeDO.class);
        maintenanceTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteMaintenanceType(Long id) {
        // 校验存在
        validateMaintenanceTypeExists(id);
        // 删除
        maintenanceTypeMapper.deleteById(id);
    }

    private void validateMaintenanceTypeExists(Long id) {
        if (maintenanceTypeMapper.selectById(id) == null) {
            throw exception(MAINTENANCE_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public MaintenanceTypeDO getMaintenanceType(Long id) {
        return maintenanceTypeMapper.selectById(id);
    }

    @Override
    public PageResult<MaintenanceTypeDO> getMaintenanceTypePage(MaintenanceTypePageReqVO pageReqVO) {
        return maintenanceTypeMapper.selectPage(pageReqVO);
    }

}