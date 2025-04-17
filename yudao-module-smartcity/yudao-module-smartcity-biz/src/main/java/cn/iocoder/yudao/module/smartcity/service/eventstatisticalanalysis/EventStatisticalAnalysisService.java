package cn.iocoder.yudao.module.smartcity.service.eventstatisticalanalysis;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventstatisticalanalysis.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventstatisticalanalysis.EventStatisticalAnalysisDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 事件统计分析 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface EventStatisticalAnalysisService {

    /**
     * 创建事件统计分析
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEventStatisticalAnalysis(@Valid EventStatisticalAnalysisSaveReqVO createReqVO);

    /**
     * 更新事件统计分析
     *
     * @param updateReqVO 更新信息
     */
    void updateEventStatisticalAnalysis(@Valid EventStatisticalAnalysisSaveReqVO updateReqVO);

    /**
     * 删除事件统计分析
     *
     * @param id 编号
     */
    void deleteEventStatisticalAnalysis(Long id);

    /**
     * 获得事件统计分析
     *
     * @param id 编号
     * @return 事件统计分析
     */
    EventStatisticalAnalysisDO getEventStatisticalAnalysis(Long id);

    /**
     * 获得事件统计分析分页
     *
     * @param pageReqVO 分页查询
     * @return 事件统计分析分页
     */
    PageResult<EventStatisticalAnalysisDO> getEventStatisticalAnalysisPage(EventStatisticalAnalysisPageReqVO pageReqVO);

}