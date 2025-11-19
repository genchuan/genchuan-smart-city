package cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultpush;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpush.vo.EvalResultPushPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpush.vo.EvalResultPushSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultpush.EvalResultPushDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.result.evalresultpush.EvalResultPushMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 结果关联推送 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalResultPushServiceImpl implements EvalResultPushService {

    @Resource
    private EvalResultPushMapper evalResultPushMapper;

    @Override
    public Long createEvalResultPush(EvalResultPushSaveReqVO createReqVO) {
        // 插入
        EvalResultPushDO evalResultPush = BeanUtils.toBean(createReqVO, EvalResultPushDO.class);
        evalResultPushMapper.insert(evalResultPush);
        // 返回
        return evalResultPush.getId();
    }

    @Override
    public void updateEvalResultPush(EvalResultPushSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalResultPushExists(updateReqVO.getId());
        // 更新
        EvalResultPushDO updateObj = BeanUtils.toBean(updateReqVO, EvalResultPushDO.class);
        evalResultPushMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalResultPush(Long id) {
        // 校验存在
        validateEvalResultPushExists(id);
        // 删除
        evalResultPushMapper.deleteById(id);
    }

    private void validateEvalResultPushExists(Long id) {
        if (evalResultPushMapper.selectById(id) == null) {
            throw exception(EVAL_RESULT_PUSH_NOT_EXISTS);
        }
    }

    @Override
    public EvalResultPushDO getEvalResultPush(Long id) {
        return evalResultPushMapper.selectById(id);
    }

    @Override
    public PageResult<EvalResultPushDO> getEvalResultPushPage(EvalResultPushPageReqVO pageReqVO) {
        return evalResultPushMapper.selectPage(pageReqVO);
    }

}