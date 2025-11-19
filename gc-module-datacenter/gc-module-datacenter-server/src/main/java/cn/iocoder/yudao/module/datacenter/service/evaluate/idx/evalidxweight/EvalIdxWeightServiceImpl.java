package cn.iocoder.yudao.module.datacenter.service.evaluate.idx.evalidxweight;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxweight.vo.EvalIdxWeightPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxweight.vo.EvalIdxWeightSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxweight.EvalIdxWeightDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.idx.evalidxweight.EvalIdxWeightMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 指标权重管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalIdxWeightServiceImpl implements EvalIdxWeightService {

    @Resource
    private EvalIdxWeightMapper evalIdxWeightMapper;

    @Override
    public Long createEvalIdxWeight(EvalIdxWeightSaveReqVO createReqVO) {
        // 插入
        EvalIdxWeightDO evalIdxWeight = BeanUtils.toBean(createReqVO, EvalIdxWeightDO.class);
        evalIdxWeightMapper.insert(evalIdxWeight);
        // 返回
        return evalIdxWeight.getId();
    }

    @Override
    public void updateEvalIdxWeight(EvalIdxWeightSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalIdxWeightExists(updateReqVO.getId());
        // 更新
        EvalIdxWeightDO updateObj = BeanUtils.toBean(updateReqVO, EvalIdxWeightDO.class);
        evalIdxWeightMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalIdxWeight(Long id) {
        // 校验存在
        validateEvalIdxWeightExists(id);
        // 删除
        evalIdxWeightMapper.deleteById(id);
    }

    private void validateEvalIdxWeightExists(Long id) {
        if (evalIdxWeightMapper.selectById(id) == null) {
            throw exception(EVAL_IDX_WEIGHT_NOT_EXISTS);
        }
    }

    @Override
    public EvalIdxWeightDO getEvalIdxWeight(Long id) {
        return evalIdxWeightMapper.selectById(id);
    }

    @Override
    public PageResult<EvalIdxWeightDO> getEvalIdxWeightPage(EvalIdxWeightPageReqVO pageReqVO) {
        return evalIdxWeightMapper.selectPage(pageReqVO);
    }

}