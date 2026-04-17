package cn.iocoder.yudao.module.chargepark.carservice.service.carguide;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.SpacePushBatchPushReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.SpacePushPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.SpacePushSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.SpacePushDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide.SpacePushMapper;
import cn.iocoder.yudao.module.chargepark.carservice.enums.carguide.SpacePushStatusEnum;
import cn.iocoder.yudao.module.system.api.notify.NotifyMessageSendApi;
import cn.iocoder.yudao.module.system.api.notify.dto.NotifySendSingleToUserReqDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.SPACE_PUSH_NOT_EXISTS;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.SPACE_PUSH_STATUS_INVALID;

/**
 * 空位推送 Service 实现类
 *
 * @author carservice
 */
@Service
@Validated
public class SpacePushServiceImpl implements SpacePushService {

    /** 站内信模板 code（需在 system_notify_template 表中预置） */
    private static final String NOTIFY_TEMPLATE_CODE = "carservice_space_push";

    @Resource
    private SpacePushMapper spacePushMapper;

    @Resource
    private NotifyMessageSendApi notifyMessageSendApi;

    @Override
    public Long createSpacePush(SpacePushSaveReqVO createReqVO) {
        SpacePushDO spacePush = BeanUtils.toBean(createReqVO, SpacePushDO.class);
        if (spacePush.getStatus() == null || spacePush.getStatus().isEmpty()) {
            spacePush.setStatus(SpacePushStatusEnum.WAITING_PUSH.getLabel());
        }
        spacePushMapper.insert(spacePush);
        return spacePush.getId();
    }

    @Override
    public void updateSpacePush(SpacePushSaveReqVO updateReqVO) {
        validateSpacePushExists(updateReqVO.getId());
        SpacePushDO updateObj = BeanUtils.toBean(updateReqVO, SpacePushDO.class);
        spacePushMapper.updateById(updateObj);
    }

    @Override
    public void deleteSpacePush(Long id) {
        validateSpacePushExists(id);
        spacePushMapper.deleteById(id);
    }

    @Override
    public void deleteSpacePushListByIds(List<Long> ids) {
        spacePushMapper.deleteByIds(ids);
    }

    private void validateSpacePushExists(Long id) {
        if (spacePushMapper.selectById(id) == null) {
            throw exception(SPACE_PUSH_NOT_EXISTS);
        }
    }

    @Override
    public SpacePushDO getSpacePush(Long id) {
        return spacePushMapper.selectById(id);
    }

    @Override
    public PageResult<SpacePushDO> getSpacePushPage(SpacePushPageReqVO pageReqVO) {
        return spacePushMapper.selectPage(pageReqVO);
    }

    // ========== 业务操作 ==========

    @Override
    public void pushSpacePush(Long id) {
        SpacePushDO push = spacePushMapper.selectById(id);
        if (push == null) {
            throw exception(SPACE_PUSH_NOT_EXISTS);
        }
        if (!SpacePushStatusEnum.WAITING_PUSH.getLabel().equals(push.getStatus())) {
            throw exception(SPACE_PUSH_STATUS_INVALID);
        }
        // 调 NotifyMessageSendApi 发站内信
        String result = sendNotify(push);
        SpacePushDO update = new SpacePushDO();
        update.setId(id);
        update.setStatus(SpacePushStatusEnum.PUSHED.getLabel());
        update.setPushTime(LocalDateTime.now());
        update.setPushResult(result);
        spacePushMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchPushSpacePush(SpacePushBatchPushReqVO reqVO) {
        if (reqVO.getIds() == null || reqVO.getIds().isEmpty()) {
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        for (Long id : reqVO.getIds()) {
            SpacePushDO push = spacePushMapper.selectById(id);
            if (push == null) {
                throw exception(SPACE_PUSH_NOT_EXISTS);
            }
            if (!SpacePushStatusEnum.WAITING_PUSH.getLabel().equals(push.getStatus())) {
                throw exception(SPACE_PUSH_STATUS_INVALID);
            }
            String result = sendNotify(push);
            SpacePushDO update = new SpacePushDO();
            update.setId(id);
            update.setStatus(SpacePushStatusEnum.PUSHED.getLabel());
            update.setPushTime(now);
            update.setPushResult(result);
            spacePushMapper.updateById(update);
        }
    }

    /**
     * 发送站内信。失败不抛异常，仅记录"失败"结果（避免阻塞批量推送）
     */
    private String sendNotify(SpacePushDO push) {
        try {
            NotifySendSingleToUserReqDTO req = new NotifySendSingleToUserReqDTO();
            req.setUserId(push.getUserId());
            req.setTemplateCode(NOTIFY_TEMPLATE_CODE);
            Map<String, Object> params = new HashMap<>();
            params.put("stationId", push.getStationId());
            params.put("spaceInfo", push.getSpaceInfo());
            req.setTemplateParams(params);
            notifyMessageSendApi.sendSingleMessageToAdmin(req);
            return "成功";
        } catch (Exception ex) {
            return "失败";
        }
    }

}
