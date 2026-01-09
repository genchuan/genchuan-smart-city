package cn.iocoder.yudao.module.smartcity.service.maintenancepersonnel;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenancepersonnel.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenancepersonnel.MaintenancePersonnelDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.maintenancepersonnel.MaintenancePersonnelMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 养护人员 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class MaintenancePersonnelServiceImpl implements MaintenancePersonnelService {

    @Resource
    private MaintenancePersonnelMapper maintenancePersonnelMapper;

    @Override
    public Long createMaintenancePersonnel(MaintenancePersonnelSaveReqVO createReqVO) {
        // 插入
        MaintenancePersonnelDO maintenancePersonnel = BeanUtils.toBean(createReqVO, MaintenancePersonnelDO.class);
        maintenancePersonnelMapper.insert(maintenancePersonnel);
        // 返回
        return maintenancePersonnel.getId();
    }

    @Override
    public void updateMaintenancePersonnel(MaintenancePersonnelSaveReqVO updateReqVO) {
        // 校验存在
        validateMaintenancePersonnelExists(updateReqVO.getId());
        // 更新
        MaintenancePersonnelDO updateObj = BeanUtils.toBean(updateReqVO, MaintenancePersonnelDO.class);
        maintenancePersonnelMapper.updateById(updateObj);
    }

    @Override
    public void deleteMaintenancePersonnel(Long id) {
        // 校验存在
        validateMaintenancePersonnelExists(id);
        // 删除
        maintenancePersonnelMapper.deleteById(id);
    }

    private void validateMaintenancePersonnelExists(Long id) {
        if (maintenancePersonnelMapper.selectById(id) == null) {
            throw exception(MAINTENANCE_PERSONNEL_NOT_EXISTS);
        }
    }

    @Override
    public MaintenancePersonnelDO getMaintenancePersonnel(Long id) {
        return maintenancePersonnelMapper.selectById(id);
    }

    @Override
    public PageResult<MaintenancePersonnelDO> getMaintenancePersonnelPage(MaintenancePersonnelPageReqVO pageReqVO) {
        return maintenancePersonnelMapper.selectPage(pageReqVO);
    }

}