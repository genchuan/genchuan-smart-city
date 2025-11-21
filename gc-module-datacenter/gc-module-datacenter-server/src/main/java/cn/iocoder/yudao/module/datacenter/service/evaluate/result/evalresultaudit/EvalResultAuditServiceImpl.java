package cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultaudit;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultaudit.vo.EvalResultAuditPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultaudit.vo.EvalResultAuditSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultaudit.EvalResultAuditDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.result.evalresultaudit.EvalResultAuditMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 结果审核 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalResultAuditServiceImpl implements EvalResultAuditService {

    @Resource
    private EvalResultAuditMapper evalResultAuditMapper;

    @Override
    public Long createEvalResultAudit(EvalResultAuditSaveReqVO createReqVO) {
        // 插入
        EvalResultAuditDO evalResultAudit = BeanUtils.toBean(createReqVO, EvalResultAuditDO.class);
        evalResultAuditMapper.insert(evalResultAudit);
        // 返回
        return evalResultAudit.getId();
    }

    @Override
    public void updateEvalResultAudit(EvalResultAuditSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalResultAuditExists(updateReqVO.getId());
        // 更新
        EvalResultAuditDO updateObj = BeanUtils.toBean(updateReqVO, EvalResultAuditDO.class);
        evalResultAuditMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalResultAudit(Long id) {
        // 校验存在
        validateEvalResultAuditExists(id);
        // 删除
        evalResultAuditMapper.deleteById(id);
    }

    private void validateEvalResultAuditExists(Long id) {
        if (evalResultAuditMapper.selectById(id) == null) {
            throw exception(EVAL_RESULT_AUDIT_NOT_EXISTS);
        }
    }

    @Override
    public EvalResultAuditDO getEvalResultAudit(Long id) {
        return evalResultAuditMapper.selectById(id);
    }

    @Override
    public PageResult<EvalResultAuditDO> getEvalResultAuditPage(EvalResultAuditPageReqVO pageReqVO) {
        return evalResultAuditMapper.selectPage(pageReqVO);
    }

}