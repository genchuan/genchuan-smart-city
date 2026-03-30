package cn.iocoder.yudao.module.kitchen.controller.admin.aiyu;

import lombok.Data;

@Data
public class RefreshSrcUrlReqVO {
    private String accessToken;        // 必填：鉴权令牌
    private String enterpriseUser;     // 可选：企业主
    private String srcToken;           // 必填：需要刷新文件的 token
}
