package cn.iocoder.yudao.module.datacenter.service.commanddispatch.dispatcheffectfeedback;

import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatcheffectfeedback.vo.DispatchEffectFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatcheffectfeedback.vo.DispatchEffectFeedbackSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.dispatcheffectfeedback.DispatchEffectFeedbackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.commanddispatch.dispatcheffectfeedback.DispatchEffectFeedbackMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 调度效果反馈 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class DispatchEffectFeedbackServiceImpl implements DispatchEffectFeedbackService {

    @Resource
    private DispatchEffectFeedbackMapper dispatchEffectFeedbackMapper;

    @Override
    public Long createDispatchEffectFeedback(DispatchEffectFeedbackSaveReqVO createReqVO) {
        // 插入
        DispatchEffectFeedbackDO dispatchEffectFeedback = BeanUtils.toBean(createReqVO, DispatchEffectFeedbackDO.class);
        dispatchEffectFeedbackMapper.insert(dispatchEffectFeedback);
        // 返回
        return dispatchEffectFeedback.getId();
    }

    @Override
    public void updateDispatchEffectFeedback(DispatchEffectFeedbackSaveReqVO updateReqVO) {
        // 校验存在
        validateDispatchEffectFeedbackExists(updateReqVO.getId());
        // 更新
        DispatchEffectFeedbackDO updateObj = BeanUtils.toBean(updateReqVO, DispatchEffectFeedbackDO.class);
        dispatchEffectFeedbackMapper.updateById(updateObj);
    }

    @Override
    public void deleteDispatchEffectFeedback(Long id) {
        // 校验存在
        validateDispatchEffectFeedbackExists(id);
        // 删除
        dispatchEffectFeedbackMapper.deleteById(id);
    }

    private void validateDispatchEffectFeedbackExists(Long id) {
        if (dispatchEffectFeedbackMapper.selectById(id) == null) {
            throw exception(DISPATCH_EFFECT_FEEDBACK_NOT_EXISTS);
        }
    }

    @Override
    public DispatchEffectFeedbackDO getDispatchEffectFeedback(Long id) {
        return dispatchEffectFeedbackMapper.selectById(id);
    }

    @Override
    public PageResult<DispatchEffectFeedbackDO> getDispatchEffectFeedbackPage(DispatchEffectFeedbackPageReqVO pageReqVO) {
        return dispatchEffectFeedbackMapper.selectPage(pageReqVO);
    }

}