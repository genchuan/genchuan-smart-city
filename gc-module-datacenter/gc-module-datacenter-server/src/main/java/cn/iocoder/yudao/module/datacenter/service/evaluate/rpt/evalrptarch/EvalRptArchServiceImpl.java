package cn.iocoder.yudao.module.datacenter.service.evaluate.rpt.evalrptarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrptarch.vo.EvalRptArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrptarch.vo.EvalRptArchSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rpt.evalrptarch.EvalRptArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.rpt.evalrptarch.EvalRptArchMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 报告归档导出 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalRptArchServiceImpl implements EvalRptArchService {

    @Resource
    private EvalRptArchMapper evalRptArchMapper;

    @Override
    public Long createEvalRptArch(EvalRptArchSaveReqVO createReqVO) {
        // 插入
        EvalRptArchDO evalRptArch = BeanUtils.toBean(createReqVO, EvalRptArchDO.class);
        evalRptArchMapper.insert(evalRptArch);
        // 返回
        return evalRptArch.getId();
    }

    @Override
    public void updateEvalRptArch(EvalRptArchSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalRptArchExists(updateReqVO.getId());
        // 更新
        EvalRptArchDO updateObj = BeanUtils.toBean(updateReqVO, EvalRptArchDO.class);
        evalRptArchMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalRptArch(Long id) {
        // 校验存在
        validateEvalRptArchExists(id);
        // 删除
        evalRptArchMapper.deleteById(id);
    }

    private void validateEvalRptArchExists(Long id) {
        if (evalRptArchMapper.selectById(id) == null) {
            throw exception(EVAL_RPT_ARCH_NOT_EXISTS);
        }
    }

    @Override
    public EvalRptArchDO getEvalRptArch(Long id) {
        return evalRptArchMapper.selectById(id);
    }

    @Override
    public PageResult<EvalRptArchDO> getEvalRptArchPage(EvalRptArchPageReqVO pageReqVO) {
        return evalRptArchMapper.selectPage(pageReqVO);
    }

}