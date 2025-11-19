package cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultaudit;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultaudit.vo.EvalResultAuditPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultaudit.vo.EvalResultAuditSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultaudit.EvalResultAuditDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 结果审核 Service 接口
 *
 * @author zcq
 */
public interface EvalResultAuditService {

    /**
     * 创建结果审核
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalResultAudit(@Valid EvalResultAuditSaveReqVO createReqVO);

    /**
     * 更新结果审核
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalResultAudit(@Valid EvalResultAuditSaveReqVO updateReqVO);

    /**
     * 删除结果审核
     *
     * @param id 编号
     */
    void deleteEvalResultAudit(Long id);

    /**
     * 获得结果审核
     *
     * @param id 编号
     * @return 结果审核
     */
    EvalResultAuditDO getEvalResultAudit(Long id);

    /**
     * 获得结果审核分页
     *
     * @param pageReqVO 分页查询
     * @return 结果审核分页
     */
    PageResult<EvalResultAuditDO> getEvalResultAuditPage(EvalResultAuditPageReqVO pageReqVO);

}