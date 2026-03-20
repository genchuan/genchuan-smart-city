package cn.iocoder.yudao.module.kitchen.controller.admin.aiyu;


import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import lombok.Data;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
@RequestMapping("/kitchen/aiScene")
public class AiSceneMessageController {

    @Resource
    private AiSceneMessageService aiSceneMessageService;

    /** 订阅AI场景告警消息 */
    @PostMapping("/subscribe")
    public CommonResult<?> subscribe(@RequestBody SubscribeReqVO reqVO) {
        return aiSceneMessageService.subscribe(reqVO);
    }

    /** 退订AI场景告警消息 */
    @PostMapping("/unsubscribe")
    public CommonResult<?> unsubscribe(@RequestBody UnsubscribeReqVO reqVO) {
        return aiSceneMessageService.unsubscribe(reqVO);
    }

    /** 查询订阅信息 */
    @PostMapping("/query")
    public CommonResult<?> query(@RequestBody QuerySubscribeReqVO reqVO) {
        return aiSceneMessageService.querySubscription(reqVO);
    }

    /** 刷新告警图片/视频下载地址 */
    @PostMapping("/refreshSrcUrl")
    public CommonResult<?> refreshSrcUrl(@RequestBody RefreshSrcUrlReqVO reqVO) {
        return aiSceneMessageService.refreshSrcUrl(reqVO);
    }


    private static final String TOKEN_URL = "https://vcp.21cn.com/open/oauth/getAccessToken";

    /**
     * 获取能力开放平台 accessToken（企业主无感知方式）
     */
    @GetMapping("/getAccessToken")
    public AccessTokenEntity getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // 设置请求体
        String body = "grantType=vcp_189";

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        // 发送POST请求
        ResponseEntity<AccessTokenResponse> response = restTemplate.postForEntity(
                TOKEN_URL,
                request,
                AccessTokenResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            AccessTokenResponse resp = response.getBody();
            if (resp.getCode() == 0) {
                return resp.getData(); // 返回token信息
            } else {
                throw new RuntimeException("获取accessToken失败：" + resp.getMsg());
            }
        }
        throw new RuntimeException("调用accessToken接口失败");
    }

    // 对应返回JSON对象
    @Data
    static class AccessTokenResponse {
        private Integer code;
        private String msg;
        private AccessTokenEntity data;
    }

    // accessToken对象
    @Data
    static class AccessTokenEntity {
        private String accessToken;
        private String refreshToken;
        private Integer expiresIn;
        private Integer refreshExpiresIn;
    }
}
