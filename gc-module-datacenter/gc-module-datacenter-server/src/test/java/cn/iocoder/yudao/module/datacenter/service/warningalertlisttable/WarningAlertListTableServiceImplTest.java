package cn.iocoder.yudao.module.datacenter.service.warningalertlisttable;

import cn.iocoder.yudao.module.datacenter.service.alarm.warningalertlisttable.WarningAlertListTableService;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

class WarningAlertListTableServiceImplTest {

    WarningAlertListTableService warningAlertListTableService;
    @Test
    void createWarningAlertListTable() {
        Long result = warningAlertListTableService.createWarningAlertListTable(100L);
//        assertThat(result, startsWith("j1:"));
    }
}