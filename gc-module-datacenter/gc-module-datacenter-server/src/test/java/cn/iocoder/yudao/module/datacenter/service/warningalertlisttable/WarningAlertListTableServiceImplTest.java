package cn.iocoder.yudao.module.datacenter.service.warningalertlisttable;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.*;

class WarningAlertListTableServiceImplTest {

    WarningAlertListTableService warningAlertListTableService;
    @Test
    void createWarningAlertListTable() {
        Long result = warningAlertListTableService.createWarningAlertListTable(100L);
//        assertThat(result, startsWith("j1:"));
    }
}