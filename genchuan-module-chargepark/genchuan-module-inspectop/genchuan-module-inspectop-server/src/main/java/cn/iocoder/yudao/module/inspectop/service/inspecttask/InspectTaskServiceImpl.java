package cn.iocoder.yudao.module.inspectop.service.inspecttask;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspecttask.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspecttask.InspectTaskDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.inspecttask.InspectTaskMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 巡检任务 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class InspectTaskServiceImpl implements InspectTaskService {

    @Resource
    private InspectTaskMapper inspectTaskMapper;

    @Override
    public Long createInspectTask(InspectTaskSaveReqVO createReqVO) {
        // 插入
        InspectTaskDO inspectTask = BeanUtils.toBean(createReqVO, InspectTaskDO.class);
        inspectTaskMapper.insert(inspectTask);

        // 返回
        return inspectTask.getId();
    }

    @Override
    public void updateInspectTask(InspectTaskSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectTaskExists(updateReqVO.getId());
        // 更新
        InspectTaskDO updateObj = BeanUtils.toBean(updateReqVO, InspectTaskDO.class);
        inspectTaskMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectTask(Long id) {
        // 校验存在
        validateInspectTaskExists(id);
        // 删除
        inspectTaskMapper.deleteById(id);
    }

    @Override
        public void deleteInspectTaskListByIds(List<Long> ids) {
        // 删除
        inspectTaskMapper.deleteByIds(ids);
        }


    private void validateInspectTaskExists(Long id) {
        if (inspectTaskMapper.selectById(id) == null) {
            throw exception(INSPECT_TASK_NOT_EXISTS);
        }
    }

    @Override
    public InspectTaskDO getInspectTask(Long id) {
        return inspectTaskMapper.selectById(id);
    }

    /**
     * 获取巡检任务分页（带关联查询）
     * 通过关联 inspect_plan 表查询计划名称
     * 通过关联 inspect_user 表查询人员姓名
     *
     * @param pageReqVO 分页查询参数
     * @return 包含计划名称和人员姓名的分页结果
     */
    @Override
    public PageResult<InspectTaskRespVO> getInspectTaskPage(InspectTaskPageReqVO pageReqVO) {
        // 创建分页对象
        Page<InspectTaskRespVO> mpPage = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 调用Mapper的关联查询方法
        Page<InspectTaskRespVO> resultPage = inspectTaskMapper.selectPageWithJoin(mpPage, pageReqVO);

        // 直接构造PageResult
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDispatchInspectTask(InspectTaskBatchDispatchReqVO batchDispatchReqVO) {
        // 1. 校验参数
        List<Long> ids = batchDispatchReqVO.getIds();
        Long userId = batchDispatchReqVO.getUserId();

        if (CollUtil.isEmpty(ids)) {
            return; // 如果ID列表为空，直接返回
        }

        // 2. 校验所有任务是否存在
        List<InspectTaskDO> taskList = inspectTaskMapper.selectListByIds(ids);
        if (taskList.size() != ids.size()) {
            // 如果查询到的任务数量与传入的ID数量不一致，说明有任务不存在
            throw exception(INSPECT_TASK_NOT_EXISTS);
        }

        // 3. 设置派发时间（当前时间）
        LocalDateTime dispatchTime = LocalDateTime.now();

        // 4. 批量更新巡检人员ID和派发时间
        LambdaUpdateWrapper<InspectTaskDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(InspectTaskDO::getUserId, userId)
                .set(InspectTaskDO::getDispatchTime, dispatchTime)
                .in(InspectTaskDO::getId, ids);

        inspectTaskMapper.update(null, updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void claimInspectTask(InspectTaskClaimReqVO claimReqVO) {
        // 1. 校验任务是否存在
        Long id = claimReqVO.getId();
        validateInspectTaskExists(id);

        // 2. 获取当前时间
        LocalDateTime claimTime = LocalDateTime.now();

        // 3. 更新任务状态为"处理中"（字典值3）和认领时间
        LambdaUpdateWrapper<InspectTaskDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(InspectTaskDO::getStatus, "3")  // 状态更新为处理中
                .set(InspectTaskDO::getClaimTime, claimTime)  // 设置认领时间
                .eq(InspectTaskDO::getId, id);

        inspectTaskMapper.update(null, updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInspectTaskProgress(InspectTaskUpdateProgressReqVO updateProgressReqVO) {
        // 1. 校验任务是否存在
        Long id = updateProgressReqVO.getId();
        validateInspectTaskExists(id);

        // 2. 获取进度值
        Integer progress = updateProgressReqVO.getProgress();

        // 3. 校验进度值范围（0-100）
        if (progress < 0 || progress > 100) {
            throw new IllegalArgumentException("进度值必须在0-100之间");
        }

        // 4. 更新进度
        LambdaUpdateWrapper<InspectTaskDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(InspectTaskDO::getProgress, progress)
                .eq(InspectTaskDO::getId, id);

        inspectTaskMapper.update(null, updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transferInspectTask(InspectTaskTransferReqVO transferReqVO) {
        // 1. 校验参数
        Long id = transferReqVO.getId();
        Long targetUserId = transferReqVO.getTargetUserId();

        // 2. 校验任务是否存在
        validateInspectTaskExists(id);

        // 3. 获取当前时间（作为转派时间）
        LocalDateTime transferTime = LocalDateTime.now();

        // 4. 检查目标用户ID是否与当前任务用户ID相同（可选校验）
        InspectTaskDO task = inspectTaskMapper.selectById(id);
        if (task.getUserId() != null && task.getUserId().equals(targetUserId)) {
            // 如果目标用户与当前用户相同，可以选择跳过更新或抛出异常
            // 这里我们选择不抛出异常，但记录日志（实际项目中可添加日志）
            return;
        }

        // 5. 更新巡检人员ID和派发时间（转派时间）
        LambdaUpdateWrapper<InspectTaskDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(InspectTaskDO::getUserId, targetUserId)
                .set(InspectTaskDO::getDispatchTime, transferTime)  // 更新派发时间为转派时间
                .eq(InspectTaskDO::getId, id);

        int updateCount = inspectTaskMapper.update(null, updateWrapper);

        // 6. 验证更新是否成功
        if (updateCount == 0) {
            // 理论上不会发生，因为已经校验了任务存在
            throw exception(INSPECT_TASK_NOT_EXISTS);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void archiveInspectTask(InspectTaskArchiveReqVO archiveReqVO) {
        // 1. 校验任务是否存在
        Long id = archiveReqVO.getId();
        validateInspectTaskExists(id);

        // 2. 检查任务是否已归档（可选校验，避免重复操作）
        InspectTaskDO task = inspectTaskMapper.selectById(id);
        if (task.getIsArchive() != null && task.getIsArchive()) {
            // 如果任务已经归档，直接返回
            return;
        }

        // 3. 更新isArchive字段为true
        LambdaUpdateWrapper<InspectTaskDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(InspectTaskDO::getIsArchive, 1)  // 设置归档状态为1
                .eq(InspectTaskDO::getId, id);

        inspectTaskMapper.update(null, updateWrapper);
    }

    @Override
    public InspectTaskChartRespVO getInspectTaskChartData(String[] timeRange) {
        InspectTaskChartRespVO chartRespVO = new InspectTaskChartRespVO();

        // 1. 获取任务类型分布数据
        List<InspectTaskChartRespVO.TypeData> typeDataList = inspectTaskMapper.selectTaskTypeDistribution(timeRange);
        chartRespVO.setTypeData(typeDataList);

        // 2. 获取任务处理时效趋势数据
        List<InspectTaskChartRespVO.TrendData> trendDataList = inspectTaskMapper.selectTaskTrendData(timeRange);
        chartRespVO.setTrendData(trendDataList);

        // 3. 获取卡片统计数据
        InspectTaskChartRespVO.CardData cardData = inspectTaskMapper.selectTaskCardData();
        chartRespVO.setCardData(cardData);

        return chartRespVO;
    }



}