package cn.iocoder.yudao.module.datacenter.controller.admin.alarmlist;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.alarmlist.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarmlist.AlarmListDO;
import cn.iocoder.yudao.module.datacenter.service.alarmlist.AlarmListService;

@Tag(name = "管理后台 - 预警告警列")
@RestController
@RequestMapping("/datacenter/alarm-list")
@Validated
public class AlarmListController {

    @Resource
    private AlarmListService alarmListService;

    @PostMapping("/create")
    @Operation(summary = "创建预警告警列")
    @PreAuthorize("@ss.hasPermission('datacenter:alarm-list:create')")
    public CommonResult<Long> createAlarmList(@Valid @RequestBody AlarmListSaveReqVO createReqVO) {
        return success(alarmListService.createAlarmList(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警告警列")
    @PreAuthorize("@ss.hasPermission('datacenter:alarm-list:update')")
    public CommonResult<Boolean> updateAlarmList(@Valid @RequestBody AlarmListSaveReqVO updateReqVO) {
        alarmListService.updateAlarmList(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警告警列")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:alarm-list:delete')")
    public CommonResult<Boolean> deleteAlarmList(@RequestParam("id") Long id) {
        alarmListService.deleteAlarmList(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警告警列")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:alarm-list:query')")
    public CommonResult<AlarmListRespVO> getAlarmList(@RequestParam("id") Long id) {
        AlarmListDO alarmList = alarmListService.getAlarmList(id);
        return success(BeanUtils.toBean(alarmList, AlarmListRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警告警列分页")
    @PreAuthorize("@ss.hasPermission('datacenter:alarm-list:query')")
    public CommonResult<PageResult<AlarmListRespVO>> getAlarmListPage(@Valid AlarmListPageReqVO pageReqVO) {
        PageResult<AlarmListDO> pageResult = alarmListService.getAlarmListPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AlarmListRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警告警列 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:alarm-list:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAlarmListExcel(@Valid AlarmListPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AlarmListDO> list = alarmListService.getAlarmListPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警告警列.xls", "数据", AlarmListRespVO.class,
                        BeanUtils.toBean(list, AlarmListRespVO.class));
    }

}