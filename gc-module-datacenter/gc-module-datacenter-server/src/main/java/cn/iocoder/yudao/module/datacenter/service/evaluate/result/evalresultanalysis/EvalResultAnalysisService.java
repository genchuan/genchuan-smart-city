package cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultanalysis;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultanalysis.vo.EvalResultAnalysisPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultanalysis.vo.EvalResultAnalysisSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultanalysis.EvalResultAnalysisDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 评价结果分析 Service 接口
 *
 * @author zcq
 */
public interface EvalResultAnalysisService {

    /**
     * 创建评价结果分析
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalResultAnalysis(@Valid EvalResultAnalysisSaveReqVO createReqVO);

    /**
     * 更新评价结果分析
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalResultAnalysis(@Valid EvalResultAnalysisSaveReqVO updateReqVO);

    /**
     * 删除评价结果分析
     *
     * @param id 编号
     */
    void deleteEvalResultAnalysis(Long id);

    /**
     * 获得评价结果分析
     *
     * @param id 编号
     * @return 评价结果分析
     */
    EvalResultAnalysisDO getEvalResultAnalysis(Long id);

    /**
     * 获得评价结果分析分页
     *
     * @param pageReqVO 分页查询
     * @return 评价结果分析分页
     */
    PageResult<EvalResultAnalysisDO> getEvalResultAnalysisPage(EvalResultAnalysisPageReqVO pageReqVO);

}