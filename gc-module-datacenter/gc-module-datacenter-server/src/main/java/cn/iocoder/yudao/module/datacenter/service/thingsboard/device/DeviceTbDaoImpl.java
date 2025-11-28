package cn.iocoder.yudao.module.datacenter.service.thingsboard.device;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.DevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.device.Dao.DeviceTbDao;
import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMethod;
import org.apache.hc.core5.http.HttpEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
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
        client.login(username, password);
        PageData<Device> tenantDevices;
        PageLink pageLink = new PageLink(pageReqVO.getPageSize(),pageReqVO.getPageNo()-1);
        tenantDevices = client.getTenantDevices("顺昌排口设备", pageLink);
        devicePageResult.setList(tenantDevices.getData());
        devicePageResult.setTotal(tenantDevices.getTotalElements());
        client.logout();
        client.close();
        return devicePageResult;
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
            ResponseEntity<PageData<AlarmInfo>> response = restTemplate.exchange(
                    alarmsUrl,
                    org.springframework.http.HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<PageData<AlarmInfo>>() {}
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("获取告警列表失败", e);
        }
    }


}
