package cn.iocoder.yudao.module.smartcity.service.eventstatisticalanalysis;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventstatisticalanalysis.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventstatisticalanalysis.EventStatisticalAnalysisDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.eventstatisticalanalysis.EventStatisticalAnalysisMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 事件统计分析 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class EventStatisticalAnalysisServiceImpl implements EventStatisticalAnalysisService {

    @Resource
    private EventStatisticalAnalysisMapper eventStatisticalAnalysisMapper;

    @Override
    public Long createEventStatisticalAnalysis(EventStatisticalAnalysisSaveReqVO createReqVO) {
        // 插入
        EventStatisticalAnalysisDO eventStatisticalAnalysis = BeanUtils.toBean(createReqVO, EventStatisticalAnalysisDO.class);
        eventStatisticalAnalysisMapper.insert(eventStatisticalAnalysis);
        // 返回
        return eventStatisticalAnalysis.getId();
    }

    @Override
    public void updateEventStatisticalAnalysis(EventStatisticalAnalysisSaveReqVO updateReqVO) {
        // 校验存在
        validateEventStatisticalAnalysisExists(updateReqVO.getId());
        // 更新
        EventStatisticalAnalysisDO updateObj = BeanUtils.toBean(updateReqVO, EventStatisticalAnalysisDO.class);
        eventStatisticalAnalysisMapper.updateById(updateObj);
    }

    @Override
    public void deleteEventStatisticalAnalysis(Long id) {
        // 校验存在
        validateEventStatisticalAnalysisExists(id);
        // 删除
        eventStatisticalAnalysisMapper.deleteById(id);
    }

    private void validateEventStatisticalAnalysisExists(Long id) {
        if (eventStatisticalAnalysisMapper.selectById(id) == null) {
            throw exception(EVENT_STATISTICAL_ANALYSIS_NOT_EXISTS);
        }
    }

    @Override
    public EventStatisticalAnalysisDO getEventStatisticalAnalysis(Long id) {
        return eventStatisticalAnalysisMapper.selectById(id);
    }

    @Override
    public PageResult<EventStatisticalAnalysisDO> getEventStatisticalAnalysisPage(EventStatisticalAnalysisPageReqVO pageReqVO) {
        return eventStatisticalAnalysisMapper.selectPage(pageReqVO);
    }

}