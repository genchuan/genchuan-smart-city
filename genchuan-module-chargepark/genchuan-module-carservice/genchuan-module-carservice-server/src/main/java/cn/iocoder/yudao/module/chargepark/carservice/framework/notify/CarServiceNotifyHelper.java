package cn.iocoder.yudao.module.chargepark.carservice.framework.notify;

import cn.iocoder.yudao.module.system.api.notify.NotifyMessageSendApi;
import cn.iocoder.yudao.module.system.api.notify.dto.NotifySendSingleToUserReqDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * carservice 模块的站内信通用 helper
 *
 * 所有发通知的需求统一走这里，方便后续替换通知渠道、统一日志格式
 */
@Slf4j
@Component
public class CarServiceNotifyHelper {

    @Resource
    private NotifyMessageSendApi notifyMessageSendApi;

    /**
     * 给指定 admin 用户发站内信，失败仅记日志不抛异常（避免阻塞业务主流程）
     *
     * @param userId       接收人用户 ID
     * @param templateCode system_notify_template.code，需提前预置
     * @param params       模板参数 map
     */
    public void sendToUser(Long userId, String templateCode, Map<String, Object> params) {
        if (userId == null || templateCode == null) {
            return;
        }
        try {
            NotifySendSingleToUserReqDTO req = new NotifySendSingleToUserReqDTO();
            req.setUserId(userId);
            req.setTemplateCode(templateCode);
            req.setTemplateParams(params == null ? new HashMap<>() : params);
            notifyMessageSendApi.sendSingleMessageToAdmin(req);
        } catch (Exception ex) {
            log.warn("[carservice-notify] 发送站内信失败 userId={} templateCode={} cause={}",
                    userId, templateCode, ex.getMessage());
        }
    }

    /** 单参数便捷方法 */
    public void sendToUser(Long userId, String templateCode, String paramKey, Object paramValue) {
        sendToUser(userId, templateCode, paramKey == null ? null : Collections.singletonMap(paramKey, paramValue));
    }

}
