package cn.iocoder.yudao.module.bpm.service.task;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.bpm.controller.admin.task.vo.statistics.BpmProcessNameStatisticsRespVO;
import cn.iocoder.yudao.module.bpm.controller.admin.task.vo.task.BpmTaskPageReqVO;
import cn.iocoder.yudao.module.bpm.controller.admin.task.vo.task.BpmTaskRespVO;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BpmTaskStatisticsServiceImpl implements BpmTaskStatisticsService {

    @Resource
    private BpmTaskService taskService;
    @Resource
    private BpmProcessInstanceService processInstanceService;
    @Resource
    private AdminUserApi adminUserApi;
    @Resource
    private DeptApi deptApi;

    @Override
    public BpmProcessNameStatisticsRespVO getProcessNamePieData() {
        // 1. 获取所有历史任务数据
        BpmTaskPageReqVO pageReqVO = new BpmTaskPageReqVO();
        pageReqVO.setPageSize(Integer.MAX_VALUE);
        pageReqVO.setPageNo(1);

        // 修复：使用正确的返回类型
        PageResult<HistoricTaskInstance> pageResult = taskService.getTaskPage(null, pageReqVO);
        List<HistoricTaskInstance> taskList = pageResult.getList();

        if (CollUtil.isEmpty(taskList)) {
            return new BpmProcessNameStatisticsRespVO();
        }

        // 2. 获取流程实例信息用于获取流程名称
        Map<String, HistoricProcessInstance> processInstanceMap = processInstanceService.getHistoricProcessInstanceMap(
                CollUtil.newHashSet(taskList.stream().map(HistoricTaskInstance::getProcessInstanceId).toList()));

        // 3. 统计流程名称出现次数
        Map<String, Long> processNameCountMap = new HashMap<>();

        for (HistoricTaskInstance task : taskList) {
            HistoricProcessInstance processInstance = processInstanceMap.get(task.getProcessInstanceId());
            if (processInstance != null && processInstance.getName() != null) {
                String processName = processInstance.getName();
                processNameCountMap.put(processName, processNameCountMap.getOrDefault(processName, 0L) + 1);
            }
        }

        // 4. 转换为饼图数据格式
        List<BpmProcessNameStatisticsRespVO.PieData> pieDataList = processNameCountMap.entrySet().stream()
                .map(entry -> new BpmProcessNameStatisticsRespVO.PieData(entry.getValue(), entry.getKey()))
                .toList();

        // 5. 构建返回对象
        BpmProcessNameStatisticsRespVO respVO = new BpmProcessNameStatisticsRespVO();
        respVO.setPieData(pieDataList);
        // 修复：使用Long类型
        respVO.setTotal((long) taskList.size());

        return respVO;
    }
}