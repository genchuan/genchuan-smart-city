package cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultrpt.vo.EvalResultRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultrpt.vo.EvalResultRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultrpt.EvalResultRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.result.evalresultrpt.EvalResultRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 结果报表管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalResultRptServiceImpl implements EvalResultRptService {

    @Resource
    private EvalResultRptMapper evalResultRptMapper;

    @Override
    public Long createEvalResultRpt(EvalResultRptSaveReqVO createReqVO) {
        // 插入
        EvalResultRptDO evalResultRpt = BeanUtils.toBean(createReqVO, EvalResultRptDO.class);
        evalResultRptMapper.insert(evalResultRpt);
        // 返回
        return evalResultRpt.getId();
    }

    @Override
    public void updateEvalResultRpt(EvalResultRptSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalResultRptExists(updateReqVO.getId());
        // 更新
        EvalResultRptDO updateObj = BeanUtils.toBean(updateReqVO, EvalResultRptDO.class);
        evalResultRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalResultRpt(Long id) {
        // 校验存在
        validateEvalResultRptExists(id);
        // 删除
        evalResultRptMapper.deleteById(id);
    }

    private void validateEvalResultRptExists(Long id) {
        if (evalResultRptMapper.selectById(id) == null) {
            throw exception(EVAL_RESULT_RPT_NOT_EXISTS);
        }
    }

    @Override
    public EvalResultRptDO getEvalResultRpt(Long id) {
        return evalResultRptMapper.selectById(id);
    }

    @Override
    public PageResult<EvalResultRptDO> getEvalResultRptPage(EvalResultRptPageReqVO pageReqVO) {
        return evalResultRptMapper.selectPage(pageReqVO);
    }

}