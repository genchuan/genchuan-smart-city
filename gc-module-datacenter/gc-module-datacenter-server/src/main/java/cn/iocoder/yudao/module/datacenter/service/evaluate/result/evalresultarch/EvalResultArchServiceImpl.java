package cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultarch.vo.EvalResultArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultarch.vo.EvalResultArchSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultarch.EvalResultArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.result.evalresultarch.EvalResultArchMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 结果存档管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalResultArchServiceImpl implements EvalResultArchService {

    @Resource
    private EvalResultArchMapper evalResultArchMapper;

    @Override
    public Long createEvalResultArch(EvalResultArchSaveReqVO createReqVO) {
        // 插入
        EvalResultArchDO evalResultArch = BeanUtils.toBean(createReqVO, EvalResultArchDO.class);
        evalResultArchMapper.insert(evalResultArch);
        // 返回
        return evalResultArch.getId();
    }

    @Override
    public void updateEvalResultArch(EvalResultArchSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalResultArchExists(updateReqVO.getId());
        // 更新
        EvalResultArchDO updateObj = BeanUtils.toBean(updateReqVO, EvalResultArchDO.class);
        evalResultArchMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalResultArch(Long id) {
        // 校验存在
        validateEvalResultArchExists(id);
        // 删除
        evalResultArchMapper.deleteById(id);
    }

    private void validateEvalResultArchExists(Long id) {
        if (evalResultArchMapper.selectById(id) == null) {
            throw exception(EVAL_RESULT_ARCH_NOT_EXISTS);
        }
    }

    @Override
    public EvalResultArchDO getEvalResultArch(Long id) {
        return evalResultArchMapper.selectById(id);
    }

    @Override
    public PageResult<EvalResultArchDO> getEvalResultArchPage(EvalResultArchPageReqVO pageReqVO) {
        return evalResultArchMapper.selectPage(pageReqVO);
    }

}