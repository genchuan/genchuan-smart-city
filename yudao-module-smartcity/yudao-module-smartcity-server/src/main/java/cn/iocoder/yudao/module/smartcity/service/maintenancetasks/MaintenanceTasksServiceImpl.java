package cn.iocoder.yudao.module.smartcity.service.maintenancetasks;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenancetasks.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenancetasks.MaintenanceTasksDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.maintenancetasks.MaintenanceTasksMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 养护任务 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class MaintenanceTasksServiceImpl implements MaintenanceTasksService {

    @Resource
    private MaintenanceTasksMapper maintenanceTasksMapper;

    @Override
    public Long createMaintenanceTasks(MaintenanceTasksSaveReqVO createReqVO) {
        // 插入
        MaintenanceTasksDO maintenanceTasks = BeanUtils.toBean(createReqVO, MaintenanceTasksDO.class);
        maintenanceTasksMapper.insert(maintenanceTasks);
        // 返回
        return maintenanceTasks.getId();
    }

    @Override
    public void updateMaintenanceTasks(MaintenanceTasksSaveReqVO updateReqVO) {
        // 校验存在
        validateMaintenanceTasksExists(updateReqVO.getId());
        // 更新
        MaintenanceTasksDO updateObj = BeanUtils.toBean(updateReqVO, MaintenanceTasksDO.class);
        maintenanceTasksMapper.updateById(updateObj);
    }

    @Override
    public void deleteMaintenanceTasks(Long id) {
        // 校验存在
        validateMaintenanceTasksExists(id);
        // 删除
        maintenanceTasksMapper.deleteById(id);
    }

    private void validateMaintenanceTasksExists(Long id) {
        if (maintenanceTasksMapper.selectById(id) == null) {
            throw exception(MAINTENANCE_TASKS_NOT_EXISTS);
        }
    }

    @Override
    public MaintenanceTasksDO getMaintenanceTasks(Long id) {
        return maintenanceTasksMapper.selectById(id);
    }

    @Override
    public PageResult<MaintenanceTasksDO> getMaintenanceTasksPage(MaintenanceTasksPageReqVO pageReqVO) {
        return maintenanceTasksMapper.selectPage(pageReqVO);
    }

}