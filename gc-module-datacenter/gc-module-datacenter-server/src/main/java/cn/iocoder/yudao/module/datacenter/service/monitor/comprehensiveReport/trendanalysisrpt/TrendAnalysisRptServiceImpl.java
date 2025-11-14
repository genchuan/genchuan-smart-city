package cn.iocoder.yudao.module.datacenter.service.monitor.comprehensiveReport.trendanalysisrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.trendanalysisrpt.vo.TrendAnalysisRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.trendanalysisrpt.vo.TrendAnalysisRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.trendanalysisrpt.TrendAnalysisRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.comprehensiveReport.trendanalysisrpt.TrendAnalysisRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 综合趋势分析报表 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class TrendAnalysisRptServiceImpl implements TrendAnalysisRptService {

    @Resource
    private TrendAnalysisRptMapper trendAnalysisRptMapper;

    @Override
    public Long createTrendAnalysisRpt(TrendAnalysisRptSaveReqVO createReqVO) {
        // 插入
        TrendAnalysisRptDO trendAnalysisRpt = BeanUtils.toBean(createReqVO, TrendAnalysisRptDO.class);
        trendAnalysisRptMapper.insert(trendAnalysisRpt);
        // 返回
        return trendAnalysisRpt.getId();
    }

    @Override
    public void updateTrendAnalysisRpt(TrendAnalysisRptSaveReqVO updateReqVO) {
        // 校验存在
        validateTrendAnalysisRptExists(updateReqVO.getId());
        // 更新
        TrendAnalysisRptDO updateObj = BeanUtils.toBean(updateReqVO, TrendAnalysisRptDO.class);
        trendAnalysisRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteTrendAnalysisRpt(Long id) {
        // 校验存在
        validateTrendAnalysisRptExists(id);
        // 删除
        trendAnalysisRptMapper.deleteById(id);
    }

    private void validateTrendAnalysisRptExists(Long id) {
        if (trendAnalysisRptMapper.selectById(id) == null) {
            throw exception(TREND_ANALYSIS_RPT_NOT_EXISTS);
        }
    }

    @Override
    public TrendAnalysisRptDO getTrendAnalysisRpt(Long id) {
        return trendAnalysisRptMapper.selectById(id);
    }

    @Override
    public PageResult<TrendAnalysisRptDO> getTrendAnalysisRptPage(TrendAnalysisRptPageReqVO pageReqVO) {
        return trendAnalysisRptMapper.selectPage(pageReqVO);
    }

}