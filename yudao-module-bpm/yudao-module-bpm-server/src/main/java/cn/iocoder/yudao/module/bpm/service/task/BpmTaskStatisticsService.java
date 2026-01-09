package cn.iocoder.yudao.module.bpm.service.task;

import cn.iocoder.yudao.module.bpm.controller.admin.task.vo.statistics.BpmProcessNameStatisticsRespVO;

public interface BpmTaskStatisticsService {

    /**
     * 获取流程名称统计饼图数据
     *
     * @return 流程名称统计信息
     */
    BpmProcessNameStatisticsRespVO getProcessNamePieData();
}