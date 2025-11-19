package cn.iocoder.yudao.module.datacenter.service.inspection.result.inspectresultfeedback;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultfeedback.vo.InspectResultFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultfeedback.vo.InspectResultFeedbackSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.result.inspectresultfeedback.InspectResultFeedbackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.result.inspectresultfeedback.InspectResultFeedbackMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检结果反馈 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectResultFeedbackServiceImpl implements InspectResultFeedbackService {

    @Resource
    private InspectResultFeedbackMapper inspectResultFeedbackMapper;

    @Override
    public Long createInspectResultFeedback(InspectResultFeedbackSaveReqVO createReqVO) {
        // 插入
        InspectResultFeedbackDO inspectResultFeedback = BeanUtils.toBean(createReqVO, InspectResultFeedbackDO.class);
        inspectResultFeedbackMapper.insert(inspectResultFeedback);
        // 返回
        return inspectResultFeedback.getId();
    }

    @Override
    public void updateInspectResultFeedback(InspectResultFeedbackSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectResultFeedbackExists(updateReqVO.getId());
        // 更新
        InspectResultFeedbackDO updateObj = BeanUtils.toBean(updateReqVO, InspectResultFeedbackDO.class);
        inspectResultFeedbackMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectResultFeedback(Long id) {
        // 校验存在
        validateInspectResultFeedbackExists(id);
        // 删除
        inspectResultFeedbackMapper.deleteById(id);
    }

    private void validateInspectResultFeedbackExists(Long id) {
        if (inspectResultFeedbackMapper.selectById(id) == null) {
            throw exception(INSPECT_RESULT_FEEDBACK_NOT_EXISTS);
        }
    }

    @Override
    public InspectResultFeedbackDO getInspectResultFeedback(Long id) {
        return inspectResultFeedbackMapper.selectById(id);
    }

    @Override
    public PageResult<InspectResultFeedbackDO> getInspectResultFeedbackPage(InspectResultFeedbackPageReqVO pageReqVO) {
        return inspectResultFeedbackMapper.selectPage(pageReqVO);
    }

}