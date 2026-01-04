package cn.iocoder.yudao.module.datacenter.service.thingsboard.device;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.DevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.device.Dao.DeviceTbDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thingsboard.rest.client.RestClient;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.DeviceInfo;
import org.thingsboard.server.common.data.alarm.Alarm;
import org.thingsboard.server.common.data.alarm.AlarmInfo;
import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.common.data.kv.AttributeKvEntry;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.common.data.page.TimePageLink;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeviceTbDaoImpl implements DeviceTbDao {
    @Value("${thingsboard.url:http://127.0.0.1:8080/}")
    private String url;
    @Value("${thingsboard.username:test}")
    private String username;
    @Value("${thingsboard.password:test}")
    private String password;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public PageResult<Device> getDevicePage(DevicePageReqVO pageReqVO) {
        PageResult<Device> devicePageResult = new PageResult<>();
        RestClient client = new RestClient(url);
        try {
            client.login(username, password);

            // 使用新的API
            PageLink pageLink = new PageLink(pageReqVO.getPageSize(), pageReqVO.getPageNo() - 1);
            PageData<DeviceInfo> tenantDevices = getAllDevices(pageLink, client);

            // 转换为Device对象（如果需要保持原有返回类型）
            List<Device> deviceList = tenantDevices.getData().stream()
                    .map(this::convertDeviceInfoToDevice)
                    .collect(Collectors.toList());

            devicePageResult.setList(deviceList);
            devicePageResult.setTotal(tenantDevices.getTotalElements());

            return devicePageResult;
        } finally {
            client.logout();
            client.close();
        }
    }

    @Override
    public Device getDeviceById(String id) {
        RestClient client = new RestClient(url);
        client.login(username, password);
        Optional<DeviceInfo> deviceInfo = client.getDeviceInfoById(DeviceId.fromString(id));
        Optional<Device> device = client.getDeviceById(DeviceId.fromString(id));
        try{
            return device.orElse(null);
        }finally {
            client.logout();
            client.close();
        }
    }

    @Override
    public DeviceInfo getDeviceInfoById(String id) {
        RestClient client = new RestClient(url);
        client.login(username, password);


        //详情
        Optional<DeviceInfo> deviceInfoOptional = client.getDeviceInfoById(DeviceId.fromString(id));


        try{
            if (deviceInfoOptional.isPresent()) {
                DeviceInfo deviceInfo = deviceInfoOptional.get();
                client.getAttributeKvEntries(DeviceId.fromString(id),client.getAttributeKeys(DeviceId.fromString(id)));
                //属性
                client.getDeviceProfileInfoById(deviceInfo.getDeviceProfileId());
                //calculatedFields
                //client.getDeviceCredentialsByDeviceId(deviceInfo.getId());
                //alarm v2

                //事件
                return deviceInfo;
            }
            return null;
        }finally {
            client.logout();
            client.close();
        }
    }

    @Override
    public List<AttributeKvEntry> getAttributeKvEntries(String id) {
        RestClient client = new RestClient(url);
        try {
            client.login(username, password);
            List<String> attributeKeys = client.getAttributeKeys(DeviceId.fromString(id));

            if (attributeKeys == null || attributeKeys.isEmpty()) {
                return new ArrayList<>();
            }

            List<AttributeKvEntry> attributeKvEntryList =
                    client.getAttributeKvEntries(DeviceId.fromString(id), attributeKeys);

            return attributeKvEntryList != null ? attributeKvEntryList : new ArrayList<>();
        } finally {
            client.logout();
            client.close();
        }
    }

    @Override
    public PageData<AlarmInfo> getAlarms(TimePageLink pageLink) {
        RestClient client = new RestClient(url);
        try {
            client.login(username, password);
            return getAllAlarms(pageLink, client);            // 调用 /api/alarms
        } finally {
            client.logout();
            client.close();
        }
    }

    @Override
    public DeviceInfo getDeviceInfo(String deviceId) {
        RestClient client = new RestClient(url);
        try {
            client.login(username, password);

            // 使用新的API获取设备详情
            String deviceUrl = url + "api/tenant/deviceInfos/" + deviceId;

            String token = client.getToken();
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Authorization", "Bearer " + token);
            headers.set("Content-Type", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<DeviceInfo> response = restTemplate.exchange(
                    deviceUrl,
                    org.springframework.http.HttpMethod.GET,
                    entity,
                    DeviceInfo.class
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("获取设备详情失败: " + e.getMessage(), e);
        } finally {
            client.logout();
            client.close();
        }
    }

    @Override
    public PageData<DeviceInfo> getAllDevices(PageLink pageLink) {
        RestClient client = new RestClient(url);
        try {
            client.login(username, password);
            return getAllDevices(pageLink, client);
        } finally {
            client.logout();
            client.close();
        }
    }

    private PageData<DeviceInfo> getAllDevices(PageLink pageLink, RestClient client) {
        try {
            // 构建新的API URL
            String devicesUrl = url + "api/tenant/deviceInfos?pageSize=" + pageLink.getPageSize() +
                    "&page=" + pageLink.getPage() +
                    "&sortProperty=createdTime&sortOrder=DESC";

            String token = client.getToken();
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Authorization", "Bearer " + token);
            headers.set("Content-Type", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<PageData<DeviceInfo>> response = restTemplate.exchange(
                    devicesUrl,
                    org.springframework.http.HttpMethod.GET,
                    entity,
                    new org.springframework.core.ParameterizedTypeReference<PageData<DeviceInfo>>() {}
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("获取设备列表失败", e);
        }
    }

    private PageData<AlarmInfo> getAllAlarms(TimePageLink pageLink, RestClient client) {
        try {
            // 获取认证token
            String token = client.getToken();

            // 构建请求URL
            String alarmsUrl = url + "api/alarms?pageSize=" + pageLink.getPageSize() + "&page=" + pageLink.getPage();

            // 创建请求头
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.set("X-Authorization", "Bearer " + token);
            headers.set("Content-Type", "application/json");

            // 创建请求实体
            org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(headers);

            // 发送请求
            org.springframework.http.ResponseEntity<PageData<AlarmInfo>> response = restTemplate.exchange(
                    alarmsUrl,
                    org.springframework.http.HttpMethod.GET,
                    entity,
                    new org.springframework.core.ParameterizedTypeReference<PageData<AlarmInfo>>() {}
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("获取告警列表失败", e);
        }
    }

    private Device convertDeviceInfoToDevice(DeviceInfo deviceInfo) {
        Device device = new Device();
        device.setId(deviceInfo.getId());
        device.setCreatedTime(deviceInfo.getCreatedTime());
        device.setTenantId(deviceInfo.getTenantId());
        device.setCustomerId(deviceInfo.getCustomerId());
        device.setName(deviceInfo.getName());
        device.setType(deviceInfo.getType());
        device.setLabel(deviceInfo.getLabel());
        device.setDeviceProfileId(deviceInfo.getDeviceProfileId());
        device.setAdditionalInfo(deviceInfo.getAdditionalInfo());
        return device;
    }


}
