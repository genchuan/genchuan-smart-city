package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm.TransferAlarmPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm.TransferAlarmRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm.TransferAlarmSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail.TransferAlarmDetailDO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferAlarmDO;
import cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transferalarm.TransferAlarmService;

@Tag(name = "环境卫生管理 - 转运站预警")
@RestController
@RequestMapping("/envirhealth/transfer-alarm")
@Validated
public class TransferAlarmController {

    @Resource
    private TransferAlarmService transferAlarmService;

    @PostMapping("/create")
    @Operation(summary = "创建转运站预警")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-alarm:create')")
    public CommonResult<Long> createTransferAlarm(@Valid @RequestBody TransferAlarmSaveReqVO createReqVO) {
        return success(transferAlarmService.createTransferAlarm(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新转运站预警")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-alarm:update')")
    public CommonResult<Boolean> updateTransferAlarm(@Valid @RequestBody TransferAlarmSaveReqVO updateReqVO) {
        transferAlarmService.updateTransferAlarm(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除转运站预警")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-alarm:delete')")
    public CommonResult<Boolean> deleteTransferAlarm(@RequestParam("id") Long id) {
        transferAlarmService.deleteTransferAlarm(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得转运站预警")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-alarm:query')")
    public CommonResult<TransferAlarmRespVO> getTransferAlarm(@RequestParam("id") Long id) {
        TransferAlarmDO transferAlarm = transferAlarmService.getTransferAlarm(id);
        return success(BeanUtils.toBean(transferAlarm, TransferAlarmRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得转运站预警分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-alarm:query')")
    public CommonResult<PageResult<TransferAlarmRespVO>> getTransferAlarmPage(@Valid TransferAlarmPageReqVO pageReqVO) {
        PageResult<TransferAlarmDO> pageResult = transferAlarmService.getTransferAlarmPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TransferAlarmRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出转运站预警 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-alarm:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTransferAlarmExcel(@Valid TransferAlarmPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TransferAlarmDO> list = transferAlarmService.getTransferAlarmPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "转运站预警.xls", "数据", TransferAlarmRespVO.class,
                        BeanUtils.toBean(list, TransferAlarmRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得转运站预警详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-alarm:query')")
    public CommonResult<PageResult<TransferAlarmDetailDO>> getPublicToiletDetailPage(
            @Valid TransferAlarmPageReqVO pageReqVO) {
        PageResult<TransferAlarmDetailDO> pageResult =
                transferAlarmService.getTransferAlarmDetailPage(pageReqVO);

        return success(pageResult);
    }
}