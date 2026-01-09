package cn.iocoder.yudao.module.smartcity.service.patroltaskmanagement;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.patroltaskmanagement.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.patroltaskmanagement.PatrolTaskManagementDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.patroltaskmanagement.PatrolTaskManagementMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 巡査任务管理 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class PatrolTaskManagementServiceImpl implements PatrolTaskManagementService {

    @Resource
    private PatrolTaskManagementMapper patrolTaskManagementMapper;

    @Override
    public Long createPatrolTaskManagement(PatrolTaskManagementSaveReqVO createReqVO) {
        // 插入
        PatrolTaskManagementDO patrolTaskManagement = BeanUtils.toBean(createReqVO, PatrolTaskManagementDO.class);
        patrolTaskManagementMapper.insert(patrolTaskManagement);
        // 返回
        return patrolTaskManagement.getId();
    }

    @Override
    public void updatePatrolTaskManagement(PatrolTaskManagementSaveReqVO updateReqVO) {
        // 校验存在
        validatePatrolTaskManagementExists(updateReqVO.getId());
        // 更新
        PatrolTaskManagementDO updateObj = BeanUtils.toBean(updateReqVO, PatrolTaskManagementDO.class);
        patrolTaskManagementMapper.updateById(updateObj);
    }

    @Override
    public void deletePatrolTaskManagement(Long id) {
        // 校验存在
        validatePatrolTaskManagementExists(id);
        // 删除
        patrolTaskManagementMapper.deleteById(id);
    }

    private void validatePatrolTaskManagementExists(Long id) {
        if (patrolTaskManagementMapper.selectById(id) == null) {
            throw exception(PATROL_TASK_MANAGEMENT_NOT_EXISTS);
        }
    }

    @Override
    public PatrolTaskManagementDO getPatrolTaskManagement(Long id) {
        return patrolTaskManagementMapper.selectById(id);
    }

    @Override
    public PageResult<PatrolTaskManagementDO> getPatrolTaskManagementPage(PatrolTaskManagementPageReqVO pageReqVO) {
        return patrolTaskManagementMapper.selectPage(pageReqVO);
    }

}