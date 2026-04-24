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
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inspectmgmt.inspecttask.CheckTaskDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.inspectmgmt.inspecttask.CheckTaskMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;



import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.TASK_NOT_EXISTS;

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
    public void batchDispatch(InspectTaskBatchDispatchReqVO reqVO) {
        // 批量更新派发状态
        for (Long id : reqVO.getIds()) {
            CheckTaskDO updateObj = new CheckTaskDO();
            updateObj.setId(id);
            updateObj.setExecuteUserId(reqVO.getExecuteUserId());
            updateObj.setStatus("待认领");
            updateObj.setDispatchTime(LocalDateTime.now());
            taskMapper.updateById(updateObj);
        }
    }

    @Override
    public void dispatch(InspectTaskDispatchReqVO reqVO) {
        // 校验存在
        validateTaskExists(reqVO.getId());
        // 更新派发
        CheckTaskDO updateObj = new CheckTaskDO();
        updateObj.setId(reqVO.getId());
        updateObj.setExecuteUserId(reqVO.getExecuteUserId());
        updateObj.setStatus("待认领");
        updateObj.setDispatchTime(LocalDateTime.now());
        taskMapper.updateById(updateObj);
    }

    @Override
    public void claim(Long id) {
        // 校验存在
        validateTaskExists(id);
        // 更新认领状态
        CheckTaskDO updateObj = new CheckTaskDO();
        updateObj.setId(id);
        updateObj.setStatus("处理中");
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
        // 校验存在
        validateTaskExists(reqVO.getId());
        // 更新转派信息
        CheckTaskDO updateObj = new CheckTaskDO();
        updateObj.setId(reqVO.getId());
        updateObj.setExecuteUserId(reqVO.getTargetUserId());
        updateObj.setTransferReason(reqVO.getTransferReason());
        taskMapper.updateById(updateObj);
    }

    @Override
    public void archive(Long id) {
        // 校验存在
        validateTaskExists(id);
        // 更新归档状态
        CheckTaskDO updateObj = new CheckTaskDO();
        updateObj.setId(id);
        updateObj.setStatus("已完成");
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

}