package cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultanalysis;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultanalysis.vo.EvalResultAnalysisPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultanalysis.vo.EvalResultAnalysisSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultanalysis.EvalResultAnalysisDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.result.evalresultanalysis.EvalResultAnalysisMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 评价结果分析 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalResultAnalysisServiceImpl implements EvalResultAnalysisService {

    @Resource
    private EvalResultAnalysisMapper evalResultAnalysisMapper;

    @Override
    public Long createEvalResultAnalysis(EvalResultAnalysisSaveReqVO createReqVO) {
        // 插入
        EvalResultAnalysisDO evalResultAnalysis = BeanUtils.toBean(createReqVO, EvalResultAnalysisDO.class);
        evalResultAnalysisMapper.insert(evalResultAnalysis);
        // 返回
        return evalResultAnalysis.getId();
    }

    @Override
    public void updateEvalResultAnalysis(EvalResultAnalysisSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalResultAnalysisExists(updateReqVO.getId());
        // 更新
        EvalResultAnalysisDO updateObj = BeanUtils.toBean(updateReqVO, EvalResultAnalysisDO.class);
        evalResultAnalysisMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalResultAnalysis(Long id) {
        // 校验存在
        validateEvalResultAnalysisExists(id);
        // 删除
        evalResultAnalysisMapper.deleteById(id);
    }

    private void validateEvalResultAnalysisExists(Long id) {
        if (evalResultAnalysisMapper.selectById(id) == null) {
            throw exception(EVAL_RESULT_ANALYSIS_NOT_EXISTS);
        }
    }

    @Override
    public EvalResultAnalysisDO getEvalResultAnalysis(Long id) {
        return evalResultAnalysisMapper.selectById(id);
    }

    @Override
    public PageResult<EvalResultAnalysisDO> getEvalResultAnalysisPage(EvalResultAnalysisPageReqVO pageReqVO) {
        return evalResultAnalysisMapper.selectPage(pageReqVO);
    }

}