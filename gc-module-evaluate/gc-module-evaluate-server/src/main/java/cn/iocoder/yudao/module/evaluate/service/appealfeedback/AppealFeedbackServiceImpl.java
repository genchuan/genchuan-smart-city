package cn.iocoder.yudao.module.evaluate.service.appealfeedback;

import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealfeedback.vo.AppealFeedbackPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealfeedback.vo.AppealFeedbackSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.appealfeedback.AppealFeedbackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.appealfeedback.AppealFeedbackMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

/**
 * 申诉反馈 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AppealFeedbackServiceImpl implements AppealFeedbackService {

    @Resource
    private AppealFeedbackMapper appealFeedbackMapper;

    @Override
    public Long createAppealFeedback(AppealFeedbackSaveReqVO createReqVO) {
        // 插入
        AppealFeedbackDO appealFeedback = BeanUtils.toBean(createReqVO, AppealFeedbackDO.class);
        appealFeedbackMapper.insert(appealFeedback);
        // 返回
        return appealFeedback.getId();
    }

    @Override
    public void updateAppealFeedback(AppealFeedbackSaveReqVO updateReqVO) {
        // 校验存在
        validateAppealFeedbackExists(updateReqVO.getId());
        // 更新
        AppealFeedbackDO updateObj = BeanUtils.toBean(updateReqVO, AppealFeedbackDO.class);
        appealFeedbackMapper.updateById(updateObj);
    }

    @Override
    public void deleteAppealFeedback(Long id) {
        // 校验存在
        validateAppealFeedbackExists(id);
        // 删除
        appealFeedbackMapper.deleteById(id);
    }

    private void validateAppealFeedbackExists(Long id) {
        if (appealFeedbackMapper.selectById(id) == null) {
            throw exception(APPEAL_FEEDBACK_NOT_EXISTS);
        }
    }

    @Override
    public AppealFeedbackDO getAppealFeedback(Long id) {
        return appealFeedbackMapper.selectById(id);
    }

    @Override
    public PageResult<AppealFeedbackDO> getAppealFeedbackPage(AppealFeedbackPageReqVO pageReqVO) {
        return appealFeedbackMapper.selectPage(pageReqVO);
    }

}