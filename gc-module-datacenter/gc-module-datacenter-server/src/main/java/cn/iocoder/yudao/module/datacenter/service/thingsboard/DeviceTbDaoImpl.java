package cn.iocoder.yudao.module.datacenter.service.thingsboard;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.vo.DevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.Dao.DeviceTbDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thingsboard.rest.client.RestClient;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.DeviceInfo;
import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.kv.AttributeKvEntry;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceTbDaoImpl implements DeviceTbDao {
    @Value("${thingsboard.url:http://127.0.0.1:8080/}")
    private String url;
    @Value("${thingsboard.username:test}")
    private String username;
    @Value("${thingsboard.password:test}")
    private String password;

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
        client.login(username, password);
        List<AttributeKvEntry> attributeKvEntryList =
                client.getAttributeKvEntries(DeviceId.fromString(id), client.getAttributeKeys(DeviceId.fromString(id)));

        try{
            if (!attributeKvEntryList.isEmpty()) {
                return attributeKvEntryList;
            }
            return null;
        }finally {
            client.logout();
            client.close();
        }
    }
}
