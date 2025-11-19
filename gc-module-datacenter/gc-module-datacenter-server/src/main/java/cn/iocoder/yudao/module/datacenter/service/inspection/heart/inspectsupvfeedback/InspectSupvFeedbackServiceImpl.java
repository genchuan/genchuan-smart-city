package cn.iocoder.yudao.module.datacenter.service.inspection.heart.inspectsupvfeedback;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvfeedback.vo.InspectSupvFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvfeedback.vo.InspectSupvFeedbackSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.heart.inspectsupvfeedback.InspectSupvFeedbackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.heart.inspectsupvfeedback.InspectSupvFeedbackMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检督办结果反馈 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectSupvFeedbackServiceImpl implements InspectSupvFeedbackService {

    @Resource
    private InspectSupvFeedbackMapper inspectSupvFeedbackMapper;

    @Override
    public Long createInspectSupvFeedback(InspectSupvFeedbackSaveReqVO createReqVO) {
        // 插入
        InspectSupvFeedbackDO inspectSupvFeedback = BeanUtils.toBean(createReqVO, InspectSupvFeedbackDO.class);
        inspectSupvFeedbackMapper.insert(inspectSupvFeedback);
        // 返回
        return inspectSupvFeedback.getId();
    }

    @Override
    public void updateInspectSupvFeedback(InspectSupvFeedbackSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectSupvFeedbackExists(updateReqVO.getId());
        // 更新
        InspectSupvFeedbackDO updateObj = BeanUtils.toBean(updateReqVO, InspectSupvFeedbackDO.class);
        inspectSupvFeedbackMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectSupvFeedback(Long id) {
        // 校验存在
        validateInspectSupvFeedbackExists(id);
        // 删除
        inspectSupvFeedbackMapper.deleteById(id);
    }

    private void validateInspectSupvFeedbackExists(Long id) {
        if (inspectSupvFeedbackMapper.selectById(id) == null) {
            throw exception(INSPECT_SUPV_FEEDBACK_NOT_EXISTS);
        }
    }

    @Override
    public InspectSupvFeedbackDO getInspectSupvFeedback(Long id) {
        return inspectSupvFeedbackMapper.selectById(id);
    }

    @Override
    public PageResult<InspectSupvFeedbackDO> getInspectSupvFeedbackPage(InspectSupvFeedbackPageReqVO pageReqVO) {
        return inspectSupvFeedbackMapper.selectPage(pageReqVO);
    }

}