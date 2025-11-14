package cn.iocoder.yudao.module.datacenter.service.monitor.comprehensiveReport.compareanalysisrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.compareanalysisrpt.vo.CompareAnalysisRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.compareanalysisrpt.vo.CompareAnalysisRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.compareanalysisrpt.CompareAnalysisRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.comprehensiveReport.compareanalysisrpt.CompareAnalysisRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 综合对比分析报表 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class CompareAnalysisRptServiceImpl implements CompareAnalysisRptService {

    @Resource
    private CompareAnalysisRptMapper compareAnalysisRptMapper;

    @Override
    public Long createCompareAnalysisRpt(CompareAnalysisRptSaveReqVO createReqVO) {
        // 插入
        CompareAnalysisRptDO compareAnalysisRpt = BeanUtils.toBean(createReqVO, CompareAnalysisRptDO.class);
        compareAnalysisRptMapper.insert(compareAnalysisRpt);
        // 返回
        return compareAnalysisRpt.getId();
    }

    @Override
    public void updateCompareAnalysisRpt(CompareAnalysisRptSaveReqVO updateReqVO) {
        // 校验存在
        validateCompareAnalysisRptExists(updateReqVO.getId());
        // 更新
        CompareAnalysisRptDO updateObj = BeanUtils.toBean(updateReqVO, CompareAnalysisRptDO.class);
        compareAnalysisRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteCompareAnalysisRpt(Long id) {
        // 校验存在
        validateCompareAnalysisRptExists(id);
        // 删除
        compareAnalysisRptMapper.deleteById(id);
    }

    private void validateCompareAnalysisRptExists(Long id) {
        if (compareAnalysisRptMapper.selectById(id) == null) {
            throw exception(COMPARE_ANALYSIS_RPT_NOT_EXISTS);
        }
    }

    @Override
    public CompareAnalysisRptDO getCompareAnalysisRpt(Long id) {
        return compareAnalysisRptMapper.selectById(id);
    }

    @Override
    public PageResult<CompareAnalysisRptDO> getCompareAnalysisRptPage(CompareAnalysisRptPageReqVO pageReqVO) {
        return compareAnalysisRptMapper.selectPage(pageReqVO);
    }

}