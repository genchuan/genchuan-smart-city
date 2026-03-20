package cn.iocoder.yudao.module.kitchen.controller.admin.aiyu;

import lombok.Data;

@Data
public class UnsubscribeReqVO {
    private String accessToken;        // 必填：鉴权令牌
    private String enterpriseUser;     // 可选：企业主
    private String deviceCode;         // 必填：设备编码
    private String alertTypes;         // 必填：告警类型列表，如 "5,7,13"
}
