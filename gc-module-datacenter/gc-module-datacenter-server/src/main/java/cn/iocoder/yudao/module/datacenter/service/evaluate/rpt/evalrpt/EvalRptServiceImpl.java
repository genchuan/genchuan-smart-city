package cn.iocoder.yudao.module.datacenter.service.evaluate.rpt.evalrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpt.vo.EvalRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpt.vo.EvalRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rpt.evalrpt.EvalRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.rpt.evalrpt.EvalRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 评价报告 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalRptServiceImpl implements EvalRptService {

    @Resource
    private EvalRptMapper evalRptMapper;

    @Override
    public Long createEvalRpt(EvalRptSaveReqVO createReqVO) {
        // 插入
        EvalRptDO evalRpt = BeanUtils.toBean(createReqVO, EvalRptDO.class);
        evalRptMapper.insert(evalRpt);
        // 返回
        return evalRpt.getId();
    }

    @Override
    public void updateEvalRpt(EvalRptSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalRptExists(updateReqVO.getId());
        // 更新
        EvalRptDO updateObj = BeanUtils.toBean(updateReqVO, EvalRptDO.class);
        evalRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalRpt(Long id) {
        // 校验存在
        validateEvalRptExists(id);
        // 删除
        evalRptMapper.deleteById(id);
    }

    private void validateEvalRptExists(Long id) {
        if (evalRptMapper.selectById(id) == null) {
            throw exception(EVAL_RPT_NOT_EXISTS);
        }
    }

    @Override
    public EvalRptDO getEvalRpt(Long id) {
        return evalRptMapper.selectById(id);
    }

    @Override
    public PageResult<EvalRptDO> getEvalRptPage(EvalRptPageReqVO pageReqVO) {
        return evalRptMapper.selectPage(pageReqVO);
    }

}