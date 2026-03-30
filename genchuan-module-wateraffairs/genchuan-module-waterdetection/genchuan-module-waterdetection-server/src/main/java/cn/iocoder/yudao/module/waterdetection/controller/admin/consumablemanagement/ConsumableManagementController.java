package cn.iocoder.yudao.module.waterdetection.controller.admin.consumablemanagement;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.consumablemanagement.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.consumablemanagement.ConsumableManagementDO;
import cn.iocoder.yudao.module.waterdetection.service.consumablemanagement.ConsumableManagementService;

@Tag(name = "管理后台 - 耗材库存与更换管理")
@RestController
@RequestMapping("/waterdetection/consumable-management")
@Validated
public class ConsumableManagementController {

    @Resource
    private ConsumableManagementService consumableManagementService;

    @PostMapping("/create")
    @Operation(summary = "创建耗材库存与更换管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:consumable-management:create')")
    public CommonResult<Long> createConsumableManagement(@Valid @RequestBody ConsumableManagementSaveReqVO createReqVO) {
        return success(consumableManagementService.createConsumableManagement(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新耗材库存与更换管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:consumable-management:update')")
    public CommonResult<Boolean> updateConsumableManagement(@Valid @RequestBody ConsumableManagementSaveReqVO updateReqVO) {
        consumableManagementService.updateConsumableManagement(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除耗材库存与更换管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:consumable-management:delete')")
    public CommonResult<Boolean> deleteConsumableManagement(@RequestParam("id") Long id) {
        consumableManagementService.deleteConsumableManagement(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得耗材库存与更换管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:consumable-management:query')")
    public CommonResult<ConsumableManagementRespVO> getConsumableManagement(@RequestParam("id") Long id) {
        ConsumableManagementDO consumableManagement = consumableManagementService.getConsumableManagement(id);
        return success(BeanUtils.toBean(consumableManagement, ConsumableManagementRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得耗材库存与更换管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:consumable-management:query')")
    public CommonResult<PageResult<ConsumableManagementRespVO>> getConsumableManagementPage(@Valid ConsumableManagementPageReqVO pageReqVO) {
        PageResult<ConsumableManagementDO> pageResult = consumableManagementService.getConsumableManagementPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ConsumableManagementRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出耗材库存与更换管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:consumable-management:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportConsumableManagementExcel(@Valid ConsumableManagementPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ConsumableManagementDO> list = consumableManagementService.getConsumableManagementPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "耗材库存与更换管理.xls", "数据", ConsumableManagementRespVO.class,
                        BeanUtils.toBean(list, ConsumableManagementRespVO.class));
    }

}