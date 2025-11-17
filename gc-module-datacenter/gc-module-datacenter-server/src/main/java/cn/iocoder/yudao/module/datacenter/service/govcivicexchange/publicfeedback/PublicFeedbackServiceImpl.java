package cn.iocoder.yudao.module.datacenter.service.govcivicexchange.publicfeedback;

import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicfeedback.vo.PublicFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicfeedback.vo.PublicFeedbackSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publicfeedback.PublicFeedbackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.govcivicexchange.publicfeedback.PublicFeedbackMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 意见反馈 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PublicFeedbackServiceImpl implements PublicFeedbackService {

    @Resource
    private PublicFeedbackMapper publicFeedbackMapper;

    @Override
    public Long createPublicFeedback(PublicFeedbackSaveReqVO createReqVO) {
        // 插入
        PublicFeedbackDO publicFeedback = BeanUtils.toBean(createReqVO, PublicFeedbackDO.class);
        publicFeedbackMapper.insert(publicFeedback);
        // 返回
        return publicFeedback.getId();
    }

    @Override
    public void updatePublicFeedback(PublicFeedbackSaveReqVO updateReqVO) {
        // 校验存在
        validatePublicFeedbackExists(updateReqVO.getId());
        // 更新
        PublicFeedbackDO updateObj = BeanUtils.toBean(updateReqVO, PublicFeedbackDO.class);
        publicFeedbackMapper.updateById(updateObj);
    }

    @Override
    public void deletePublicFeedback(Long id) {
        // 校验存在
        validatePublicFeedbackExists(id);
        // 删除
        publicFeedbackMapper.deleteById(id);
    }

    private void validatePublicFeedbackExists(Long id) {
        if (publicFeedbackMapper.selectById(id) == null) {
            throw exception(PUBLIC_FEEDBACK_NOT_EXISTS);
        }
    }

    @Override
    public PublicFeedbackDO getPublicFeedback(Long id) {
        return publicFeedbackMapper.selectById(id);
    }

    @Override
    public PageResult<PublicFeedbackDO> getPublicFeedbackPage(PublicFeedbackPageReqVO pageReqVO) {
        return publicFeedbackMapper.selectPage(pageReqVO);
    }

}