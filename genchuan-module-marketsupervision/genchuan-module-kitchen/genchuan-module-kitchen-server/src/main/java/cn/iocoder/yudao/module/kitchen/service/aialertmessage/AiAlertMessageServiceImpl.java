package cn.iocoder.yudao.module.kitchen.service.aialertmessage;

import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.AiAlertMessagePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.AiAlertMessageSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.aialertmessage.AiAlertMessageDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.aialertmessage.AiAlertMessageMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.kitchen.enums.ErrorCodeConstants.*;

/**
 * AI告警消息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AiAlertMessageServiceImpl implements AiAlertMessageService {

    @Resource
    private AiAlertMessageMapper aiAlertMessageMapper;

    @Override
    public Long createAiAlertMessage(AiAlertMessageSaveReqVO createReqVO) {
        // 插入
        AiAlertMessageDO aiAlertMessage = BeanUtils.toBean(createReqVO, AiAlertMessageDO.class);
        aiAlertMessageMapper.insert(aiAlertMessage);
        // 返回
        return aiAlertMessage.getId();
    }

    @Override
    public void updateAiAlertMessage(AiAlertMessageSaveReqVO updateReqVO) {
        // 校验存在
        validateAiAlertMessageExists(updateReqVO.getId());
        // 更新
        AiAlertMessageDO updateObj = BeanUtils.toBean(updateReqVO, AiAlertMessageDO.class);
        aiAlertMessageMapper.updateById(updateObj);
    }

    @Override
    public void deleteAiAlertMessage(Long id) {
        // 校验存在
        validateAiAlertMessageExists(id);
        // 删除
        aiAlertMessageMapper.deleteById(id);
    }

    private void validateAiAlertMessageExists(Long id) {
        if (aiAlertMessageMapper.selectById(id) == null) {
            throw exception(AI_ALERT_MESSAGE_NOT_EXISTS);
        }
    }

    @Override
    public AiAlertMessageDO getAiAlertMessage(Long id) {
        return aiAlertMessageMapper.selectById(id);
    }

    @Override
    public PageResult<AiAlertMessageDO> getAiAlertMessagePage(AiAlertMessagePageReqVO pageReqVO) {
        return aiAlertMessageMapper.selectPage(pageReqVO);
    }

}
