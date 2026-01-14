package cn.iocoder.yudao.module.industry.service.park.thingsboard;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.thingsboard.rest.client.RestClient;

import jakarta.annotation.Resource;
import java.util.*;

/**
 * 从Thingsboard获取停车记录遥测数据的 Service 实现类
 *
 * @author zhucongquan
 */
@Service
public class ParkingRecordTbService {

    @Value("${thingsboard.url:http://127.0.0.1:8080/}")
    private String thingsboardUrl; // Thingsboard服务器地址，默认值可配置

    @Value("${thingsboard.username:test}")
    private String username; // Thingsboard用户名

    @Value("${thingsboard.password:test}")
    private String password; // Thingsboard密码

    @Resource
    private RestTemplate restTemplate; // 注入RestTemplate，用于HTTP请求

    /**
     * 获取指定设备的遥测key列表
     * 接口URL示例: http://192.168.8.67:8080/api/plugins/telemetry/DEVICE/50501110-ea16-11f0-a23c-6b31e613548a/keys/timeseries
     *
     * @param deviceId 设备ID，如"50501110-ea16-11f0-a23c-6b31e613548a"
     * @return 遥测key的列表，例如["timestamp", "entranceNo", ...]
     */
    public List<String> getTelemetryKeys(String deviceId) {
        RestClient client = new RestClient(thingsboardUrl);
        try {
            // 登录Thingsboard获取认证token
            client.login(username, password);
            String token = client.getToken();

            // 构建请求URL和头部
            String keysUrl = thingsboardUrl + "api/plugins/telemetry/DEVICE/" + deviceId + "/keys/timeseries";
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Authorization", "Bearer " + token); // 设置认证token
            headers.set("Content-Type", "application/json");

            // 创建HTTP实体
            HttpEntity<String> entity = new HttpEntity<>(headers);
            // 发送GET请求，返回类型为List（对应JSON数组）
            ResponseEntity<List> response = restTemplate.exchange(
                    keysUrl,
                    HttpMethod.GET,
                    entity,
                    List.class
            );

            // 返回遥测key列表，强制转换为List<String>
            return (List<String>) response.getBody();
        } catch (Exception e) {
            // 异常处理，抛出运行时异常
            throw new RuntimeException("获取遥测key失败: " + e.getMessage(), e);
        } finally {
            // 确保登出和关闭客户端
            client.logout();
            client.close();
        }
    }

    /**
     * 获取指定设备的遥测值
     * 接口URL示例: http://192.168.8.67:8080/api/plugins/telemetry/DEVICE/50501110-ea16-11f0-a23c-6b31e613548a/values/timeseries?keys=timestamp,entranceNo,...&useStrictDataTypes=false
     *
     * @param deviceId 设备ID
     * @param keys 遥测key列表，从getTelemetryKeys方法获取
     * @return 遥测值的Map，key为字段名（如"timestamp"），value为对应的值（取每个key的第一个数据点的value）
     */
    public Map<String, Object> getTelemetryValues(String deviceId, List<String> keys) {
        RestClient client = new RestClient(thingsboardUrl);
        try {
            client.login(username, password);
            String token = client.getToken();

            // 将key列表转换为逗号分隔的字符串，作为URL参数
            String keysParam = String.join(",", keys);
            String valuesUrl = thingsboardUrl + "api/plugins/telemetry/DEVICE/" + deviceId +
                    "/values/timeseries?keys=" + keysParam + "&useStrictDataTypes=false";

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Authorization", "Bearer " + token);
            headers.set("Content-Type", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            // 发送GET请求，返回类型为Map（对应JSON对象）
            ResponseEntity<Map> response = restTemplate.exchange(
                    valuesUrl,
                    HttpMethod.GET,
                    entity,
                    Map.class
            );

            Map<String, Object> rawData = response.getBody(); // 原始数据，结构为Map<String, List<Map<String, Object>>>
            Map<String, Object> result = new HashMap<>(); // 用于存储处理后的值

            // 遍历原始数据，提取每个key的第一个数据点的value
            for (Map.Entry<String, Object> entry : rawData.entrySet()) {
                String key = entry.getKey();
                Object valueList = entry.getValue();
                if (valueList instanceof List) {
                    List<?> list = (List<?>) valueList;
                    if (!list.isEmpty()) {
                        Object firstElement = list.get(0); // 取第一个数据点
                        if (firstElement instanceof Map) {
                            Map<?, ?> dataPoint = (Map<?, ?>) firstElement;
                            Object value = dataPoint.get("value"); // 提取"value"字段
                            result.put(key, value);
                        }
                    }
                }
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("获取遥测值失败: " + e.getMessage(), e);
        } finally {
            client.logout();
            client.close();
        }
    }
}