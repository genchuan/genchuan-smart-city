/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.river;

import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.monitortype.MonitorTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.monitortype.MonitorTypeRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.monitortype.MonitorTypeSaveReqVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.MonitorTypeDO;
import cn.iocoder.yudao.module.envirhealth.service.river.monitortype.MonitorTypeService;

@Tag(name = "管理后台 - 监测类型字典表")
@RestController
@RequestMapping("/envirhealth/monitor-type")
@Validated
public class MonitorTypeController {

    @Resource
    private MonitorTypeService monitorTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建监测类型字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:monitor-type:create')")
    public CommonResult<Long> createMonitorType(@Valid @RequestBody MonitorTypeSaveReqVO createReqVO) {
        return success(monitorTypeService.createMonitorType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测类型字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:monitor-type:update')")
    public CommonResult<Boolean> updateMonitorType(@Valid @RequestBody MonitorTypeSaveReqVO updateReqVO) {
        monitorTypeService.updateMonitorType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测类型字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:monitor-type:delete')")
    public CommonResult<Boolean> deleteMonitorType(@RequestParam("id") Long id) {
        monitorTypeService.deleteMonitorType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测类型字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:monitor-type:query')")
    public CommonResult<MonitorTypeRespVO> getMonitorType(@RequestParam("id") Long id) {
        MonitorTypeDO monitorType = monitorTypeService.getMonitorType(id);
        return success(BeanUtils.toBean(monitorType, MonitorTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测类型字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:monitor-type:query')")
    public CommonResult<PageResult<MonitorTypeRespVO>> getMonitorTypePage(@Valid MonitorTypePageReqVO pageReqVO) {
        PageResult<MonitorTypeDO> pageResult = monitorTypeService.getMonitorTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonitorTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测类型字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:monitor-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonitorTypeExcel(@Valid MonitorTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonitorTypeDO> list = monitorTypeService.getMonitorTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测类型字典表.xls", "数据", MonitorTypeRespVO.class,
                        BeanUtils.toBean(list, MonitorTypeRespVO.class));
    }

}*/
