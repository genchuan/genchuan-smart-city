package cn.iocoder.yudao.module.kitchen.controller.admin.aiyu;


import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class HttpClientUtil {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /** Form表单提交POST请求 */
    public static CommonResult<?> postForm(String url, Object paramObj) {
        try {
            // 将 VO 转为 Map
            Map<String, Object> paramMap = objectMapper.convertValue(paramObj, Map.class);
            MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
            paramMap.forEach((k, v) -> form.add(k, v.toString()));

            // 发送POST请求
            String response = restTemplate.postForObject(url, form, String.class);

            // TODO: 如果返回data为RSA加密，需要在这里解密
            // response = RSAUtil.decrypt(response, privateKey);

            return objectMapper.readValue(response, CommonResult.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult();
        }
    }
}
