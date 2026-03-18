package cn.iocoder.yudao.module.kitchen.controller.admin.aiyu;

import lombok.Data;

@Data
public class SubscribeReqVO {
    private String accessToken;        // 必填
    private String enterpriseUser;     // 可选
    private String deviceCode;         // 必填
    private String alertTypes;         // 必填, "5,7,13"
    private String callbackUrl;        // 必填, url encode后的推送地址
}
