package cn.iocoder.yudao.module.gc.controller.admin.alarminformation;

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

import cn.iocoder.yudao.module.gc.controller.admin.alarminformation.vo.*;
import cn.iocoder.yudao.module.gc.dal.dataobject.alarminformation.AlarmInformationDO;
import cn.iocoder.yudao.module.gc.service.alarminformation.AlarmInformationService;

@Tag(name = "管理后台 - 预警信息")
@RestController
@RequestMapping("/gc/alarm-information")
@Validated
public class AlarmInformationController {

    @Resource
    private AlarmInformationService alarmInformationService;

    @PostMapping("/create")
    @Operation(summary = "创建预警信息")
    @PreAuthorize("@ss.hasPermission('gc:alarm-information:create')")
    public CommonResult<String> createAlarmInformation(@Valid @RequestBody AlarmInformationSaveReqVO createReqVO) {
        return success(alarmInformationService.createAlarmInformation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警信息")
    @PreAuthorize("@ss.hasPermission('gc:alarm-information:update')")
    public CommonResult<Boolean> updateAlarmInformation(@Valid @RequestBody AlarmInformationSaveReqVO updateReqVO) {
        alarmInformationService.updateAlarmInformation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('gc:alarm-information:delete')")
    public CommonResult<Boolean> deleteAlarmInformation(@RequestParam("id") String id) {
        alarmInformationService.deleteAlarmInformation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('gc:alarm-information:query')")
    public CommonResult<AlarmInformationRespVO> getAlarmInformation(@RequestParam("id") String id) {
        AlarmInformationDO alarmInformation = alarmInformationService.getAlarmInformation(id);
        return success(BeanUtils.toBean(alarmInformation, AlarmInformationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警信息分页")
    @PreAuthorize("@ss.hasPermission('gc:alarm-information:query')")
    public CommonResult<PageResult<AlarmInformationRespVO>> getAlarmInformationPage(@Valid AlarmInformationPageReqVO pageReqVO) {
        PageResult<AlarmInformationDO> pageResult = alarmInformationService.getAlarmInformationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AlarmInformationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警信息 Excel")
    @PreAuthorize("@ss.hasPermission('gc:alarm-information:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAlarmInformationExcel(@Valid AlarmInformationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AlarmInformationDO> list = alarmInformationService.getAlarmInformationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警信息.xls", "数据", AlarmInformationRespVO.class,
                        BeanUtils.toBean(list, AlarmInformationRespVO.class));
    }

}