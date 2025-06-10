package cn.iocoder.yudao.module.smartcity.service.eventanalysis;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventanalysis.vo.EventAnalysisPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventanalysis.vo.EventAnalysisSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventanalysis.EventAnalysisDO;
import jakarta.validation.Valid;

/**
 * 事件关联分析 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface EventAnalysisService {

    /**
     * 创建事件关联分析
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEventAnalysis(@Valid EventAnalysisSaveReqVO createReqVO);

    /**
     * 更新事件关联分析
     *
     * @param updateReqVO 更新信息
     */
    void updateEventAnalysis(@Valid EventAnalysisSaveReqVO updateReqVO);

    /**
     * 删除事件关联分析
     *
     * @param id 编号
     */
    void deleteEventAnalysis(Long id);

    /**
     * 获得事件关联分析
     *
     * @param id 编号
     * @return 事件关联分析
     */
    EventAnalysisDO getEventAnalysis(Long id);

    /**
     * 获得事件关联分析分页
     *
     * @param pageReqVO 分页查询
     * @return 事件关联分析分页
     */
    PageResult<EventAnalysisDO> getEventAnalysisPage(EventAnalysisPageReqVO pageReqVO);

}