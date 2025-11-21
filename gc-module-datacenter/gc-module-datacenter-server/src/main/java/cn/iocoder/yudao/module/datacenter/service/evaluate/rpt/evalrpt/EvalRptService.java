package cn.iocoder.yudao.module.datacenter.service.evaluate.rpt.evalrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpt.vo.EvalRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpt.vo.EvalRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rpt.evalrpt.EvalRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 评价报告 Service 接口
 *
 * @author zcq
 */
public interface EvalRptService {

    /**
     * 创建评价报告
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalRpt(@Valid EvalRptSaveReqVO createReqVO);

    /**
     * 更新评价报告
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalRpt(@Valid EvalRptSaveReqVO updateReqVO);

    /**
     * 删除评价报告
     *
     * @param id 编号
     */
    void deleteEvalRpt(Long id);

    /**
     * 获得评价报告
     *
     * @param id 编号
     * @return 评价报告
     */
    EvalRptDO getEvalRpt(Long id);

    /**
     * 获得评价报告分页
     *
     * @param pageReqVO 分页查询
     * @return 评价报告分页
     */
    PageResult<EvalRptDO> getEvalRptPage(EvalRptPageReqVO pageReqVO);

}