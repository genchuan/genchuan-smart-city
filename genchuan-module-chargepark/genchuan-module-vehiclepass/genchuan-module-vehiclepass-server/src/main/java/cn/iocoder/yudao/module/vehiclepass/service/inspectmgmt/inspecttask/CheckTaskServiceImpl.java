package cn.iocoder.yudao.module.vehiclepass.service.inspectmgmt.inspecttask;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.CheckTaskPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.CheckTaskSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.CheckTaskRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskBatchDispatchReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskDispatchReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskUpdateProgressReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskTransferReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.UserSimpleRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inspectmgmt.inspecttask.CheckTaskDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.common.UserInfoMapper;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.inspectmgmt.inspecttask.CheckTaskMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;



import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.TASK_NOT_EXISTS;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.TASK_STATUS_INVALID;
import static cn.iocoder.yudao.module.vehiclepass.constants.inspectmgmt.CheckTaskConstants.*;

/**
 * 稽查任务 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CheckTaskServiceImpl implements CheckTaskService {

    @Resource
    private CheckTaskMapper taskMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Long createTask(CheckTaskSaveReqVO createReqVO) {
        // 插入
        CheckTaskDO task = BeanUtils.toBean(createReqVO, CheckTaskDO.class);
        taskMapper.insert(task);

        // 返回
        return task.getId();
    }

    @Override
    public void updateTask(CheckTaskSaveReqVO updateReqVO) {
        // 校验存在
        validateTaskExists(updateReqVO.getId());
        // 更新
        CheckTaskDO updateObj = BeanUtils.toBean(updateReqVO, CheckTaskDO.class);
        taskMapper.updateById(updateObj);
    }

    @Override
    public void deleteTask(Long id) {
        // 校验存在
        validateTaskExists(id);
        // 删除
        taskMapper.deleteById(id);
    }

    @Override
    public void deleteTaskListByIds(List<Long> ids) {
        // 删除
        taskMapper.deleteByIds(ids);
    }


    private void validateTaskExists(Long id) {
        if (taskMapper.selectById(id) == null) {
            throw exception(TASK_NOT_EXISTS);
        }
    }

    @Override
    public CheckTaskDO getTask(Long id) {
        return taskMapper.selectById(id);
    }

    @Override
    public CheckTaskRespVO getTaskWithJoin(Long id) {
        return taskMapper.selectByIdJoin(id);
    }

    @Override
    public PageResult<CheckTaskDO> getTaskPage(CheckTaskPageReqVO pageReqVO) {
        return taskMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<CheckTaskRespVO> getTaskPageWithJoin(CheckTaskPageReqVO pageReqVO) {
        // 构建分页参数
        Page<CheckTaskRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        // 调用JOIN查询
        com.baomidou.mybatisplus.core.metadata.IPage<CheckTaskRespVO> pageResult = taskMapper.selectPageJoin(page, pageReqVO);
        // 转换为PageResult
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDispatch(InspectTaskBatchDispatchReqVO reqVO) {
        LocalDateTime now = LocalDateTime.now();
        List<CheckTaskDO> updateList = new ArrayList<>();
        for (Long id : reqVO.getIds()) {
            CheckTaskDO updateObj = new CheckTaskDO();
            updateObj.setId(id);
            updateObj.setExecuteUserId(reqVO.getExecuteUserId());
            updateObj.setStatus(STATUS_PENDING_CLAIM);
            updateObj.setDispatchTime(now);
            updateList.add(updateObj);
        }
        taskMapper.updateBatch(updateList);
    }

    @Override
    public void dispatch(InspectTaskDispatchReqVO reqVO) {
        // 校验存在
        validateTaskExists(reqVO.getId());
        // 更新派发
        CheckTaskDO updateObj = new CheckTaskDO();
        updateObj.setId(reqVO.getId());
        updateObj.setExecuteUserId(reqVO.getExecuteUserId());
        updateObj.setStatus(STATUS_PENDING_CLAIM);
        updateObj.setDispatchTime(LocalDateTime.now());
        taskMapper.updateById(updateObj);
    }

    @Override
    public void claim(Long id) {
        CheckTaskDO task = taskMapper.selectById(id);
        if (task == null) {
            throw exception(TASK_NOT_EXISTS);
        }
        if (!STATUS_PENDING_CLAIM.equals(task.getStatus())) {
            throw exception(TASK_STATUS_INVALID);
        }
        // 获取当前登录用户作为执行人
        Long loginUserId = getLoginUserId();
        CheckTaskDO updateObj = new CheckTaskDO();
        updateObj.setId(id);
        updateObj.setExecuteUserId(loginUserId);
        updateObj.setStatus(STATUS_PROCESSING);
        updateObj.setTaskProgress("进行中");
        taskMapper.updateById(updateObj);
    }

    @Override
    public void updateProgress(InspectTaskUpdateProgressReqVO reqVO) {
        // 校验存在
        validateTaskExists(reqVO.getId());
        // 更新任务进度
        CheckTaskDO updateObj = new CheckTaskDO();
        updateObj.setId(reqVO.getId());
        updateObj.setTaskProgress(reqVO.getTaskProgress());
        taskMapper.updateById(updateObj);
    }

    @Override
    public void transfer(InspectTaskTransferReqVO reqVO) {
        CheckTaskDO task = taskMapper.selectById(reqVO.getId());
        if (task == null) {
            throw exception(TASK_NOT_EXISTS);
        }
        if (!STATUS_PROCESSING.equals(task.getStatus())) {
            throw exception(TASK_STATUS_INVALID);
        }
        CheckTaskDO updateObj = new CheckTaskDO();
        updateObj.setId(reqVO.getId());
        updateObj.setExecuteUserId(reqVO.getTargetUserId());
        updateObj.setTransferReason(reqVO.getTransferReason());
        taskMapper.updateById(updateObj);
    }

    @Override
    public void archive(Long id) {
        CheckTaskDO task = taskMapper.selectById(id);
        if (task == null) {
            throw exception(TASK_NOT_EXISTS);
        }
        if (!STATUS_PROCESSING.equals(task.getStatus())) {
            throw exception(TASK_STATUS_INVALID);
        }
        CheckTaskDO updateObj = new CheckTaskDO();
        updateObj.setId(id);
        updateObj.setStatus(STATUS_COMPLETED);
        updateObj.setFinishTime(LocalDateTime.now());
        taskMapper.updateById(updateObj);
    }

    @Override
    public InspectTaskChartRespVO getChart(InspectTaskChartReqVO reqVO) {
        InspectTaskChartRespVO respVO = new InspectTaskChartRespVO();

        // 任务类型分布
        respVO.setTaskTypeCount(taskMapper.selectTaskTypeCount(reqVO));

        // 任务处理时效趋势
        respVO.setTaskHandleTrend(taskMapper.selectTaskHandleTrend(reqVO));

        // 卡片数据
        InspectTaskChartRespVO.CardData cardData = new InspectTaskChartRespVO.CardData();
        cardData.setWaitHandleTaskCount(taskMapper.selectWaitHandleTaskCount(reqVO));
        cardData.setFinishedTaskCount(taskMapper.selectFinishedTaskCount(reqVO));
        respVO.setCardData(cardData);

        return respVO;
    }

    @Override
    public List<UserSimpleRespVO> getUserSimpleList() {
        return userInfoMapper.selectUserSimpleList();
    }

}