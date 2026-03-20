package cn.iocoder.yudao.module.kitchen.controller.admin.aiyu;

import lombok.Data;

@Data
public class QuerySubscribeReqVO {
    private String accessToken;        // 必填：鉴权令牌
    private String enterpriseUser;     // 可选：企业主
    private String parentUser;         // 可选：父账号（enterpriseUser是子账号时必填）
    private String alertTypes;         // 可选：告警类型，如 "5,7,13"
    private String deviceCode;         // 可选：设备编码
}
