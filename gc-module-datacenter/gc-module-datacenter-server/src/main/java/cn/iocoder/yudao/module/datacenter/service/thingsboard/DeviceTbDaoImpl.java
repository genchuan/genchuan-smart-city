package cn.iocoder.yudao.module.datacenter.service.thingsboard;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.vo.DevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.Dao.DeviceTbDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thingsboard.rest.client.RestClient;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;

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
}
