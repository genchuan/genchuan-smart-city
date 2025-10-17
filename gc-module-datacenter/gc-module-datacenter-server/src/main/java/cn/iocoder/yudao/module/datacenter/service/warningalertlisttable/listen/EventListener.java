package cn.iocoder.yudao.module.datacenter.service.warningalertlisttable.listen;

import cn.iocoder.yudao.module.bpm.api.event.BpmProcessInstanceStatusEvent;
import cn.iocoder.yudao.module.bpm.api.event.BpmProcessInstanceStatusEventListener;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.warningalertlisttable.WarningAlertListTableDO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.warningalertlisttable.WarningAlertListTableMapper;
import cn.iocoder.yudao.module.datacenter.service.warningalertlisttable.WarningAlertListTableService;
import cn.iocoder.yudao.module.datacenter.service.warningalertlisttable.WarningAlertListTableServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

//import static jdk.internal.org.jline.utils.Status.getStatus;

@Component
public class EventListener extends BpmProcessInstanceStatusEventListener {
    @Resource
    private WarningAlertListTableMapper warningAlertListTableMapper;
    @Override
    protected String getProcessDefinitionKey() {

        return WarningAlertListTableServiceImpl.PROCESS_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceStatusEvent event) {
        WarningAlertListTableDO warningAlert = warningAlertListTableMapper.selectById(Long.parseLong(event.getBusinessKey()));
        warningAlert.setStatus(Byte.parseByte(String.valueOf(event.getStatus())));
        warningAlertListTableMapper.updateById(warningAlert);
    }
}
