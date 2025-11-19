package cn.iocoder.yudao.module.datacenter.service.evaluate.review.evalappeal;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalappeal.vo.EvalAppealPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalappeal.vo.EvalAppealSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.review.evalappeal.EvalAppealDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.review.evalappeal.EvalAppealMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 申诉管理 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class EvalAppealServiceImpl implements EvalAppealService {

    @Resource
    private EvalAppealMapper evalAppealMapper;

    @Override
    public Long createEvalAppeal(EvalAppealSaveReqVO createReqVO) {
        // 插入
        EvalAppealDO evalAppeal = BeanUtils.toBean(createReqVO, EvalAppealDO.class);
        evalAppealMapper.insert(evalAppeal);
        // 返回
        return evalAppeal.getId();
    }

    @Override
    public void updateEvalAppeal(EvalAppealSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalAppealExists(updateReqVO.getId());
        // 更新
        EvalAppealDO updateObj = BeanUtils.toBean(updateReqVO, EvalAppealDO.class);
        evalAppealMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalAppeal(Long id) {
        // 校验存在
        validateEvalAppealExists(id);
        // 删除
        evalAppealMapper.deleteById(id);
    }

    private void validateEvalAppealExists(Long id) {
        if (evalAppealMapper.selectById(id) == null) {
            throw exception(EVAL_APPEAL_NOT_EXISTS);
        }
    }

    @Override
    public EvalAppealDO getEvalAppeal(Long id) {
        return evalAppealMapper.selectById(id);
    }

    @Override
    public PageResult<EvalAppealDO> getEvalAppealPage(EvalAppealPageReqVO pageReqVO) {
        return evalAppealMapper.selectPage(pageReqVO);
    }

}