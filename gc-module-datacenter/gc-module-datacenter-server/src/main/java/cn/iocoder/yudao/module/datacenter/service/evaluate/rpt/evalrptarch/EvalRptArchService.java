package cn.iocoder.yudao.module.datacenter.service.evaluate.rpt.evalrptarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrptarch.vo.EvalRptArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrptarch.vo.EvalRptArchSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rpt.evalrptarch.EvalRptArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 报告归档导出 Service 接口
 *
 * @author zcq
 */
public interface EvalRptArchService {

    /**
     * 创建报告归档导出
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalRptArch(@Valid EvalRptArchSaveReqVO createReqVO);

    /**
     * 更新报告归档导出
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalRptArch(@Valid EvalRptArchSaveReqVO updateReqVO);

    /**
     * 删除报告归档导出
     *
     * @param id 编号
     */
    void deleteEvalRptArch(Long id);

    /**
     * 获得报告归档导出
     *
     * @param id 编号
     * @return 报告归档导出
     */
    EvalRptArchDO getEvalRptArch(Long id);

    /**
     * 获得报告归档导出分页
     *
     * @param pageReqVO 分页查询
     * @return 报告归档导出分页
     */
    PageResult<EvalRptArchDO> getEvalRptArchPage(EvalRptArchPageReqVO pageReqVO);

}