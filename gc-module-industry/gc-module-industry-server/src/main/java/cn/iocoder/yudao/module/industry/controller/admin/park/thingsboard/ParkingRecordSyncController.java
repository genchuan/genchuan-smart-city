package cn.iocoder.yudao.module.industry.controller.admin.park.thingsboard;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.service.park.thingsboard.ParkingRecordSyncService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 停车记录同步")
@RestController
@RequestMapping("/industry/parking-record-sync")
@Validated
@Slf4j
public class ParkingRecordSyncController {

    @Resource
    private ParkingRecordSyncService parkingRecordSyncService;

    @PostMapping("/sync")
    @Operation(summary = "手动同步停车记录")
    public CommonResult<Boolean> syncParkingRecords() {
        log.info("手动触发停车记录同步");
        try {
            parkingRecordSyncService.syncParkingRecords();
            return success(true);
        } catch (Exception e) {
            log.error("手动同步停车记录失败: {}", e.getMessage(), e);
            throw e;
        }
    }
}