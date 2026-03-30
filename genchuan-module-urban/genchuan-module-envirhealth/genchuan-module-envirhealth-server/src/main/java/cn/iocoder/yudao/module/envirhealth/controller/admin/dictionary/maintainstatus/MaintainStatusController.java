/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.maintainstatus;

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

import cn.iocoder.yudao.module.envirhealth.controller.admin.maintainstatus.vo.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.maintainstatus.MaintainStatusDO;
import cn.iocoder.yudao.module.envirhealth.service.maintainstatus.MaintainStatusService;

@Tag(name = "环境卫生管理 - 维护状态字典表")
@RestController
@RequestMapping("/envirhealth/maintain-status")
@Validated
public class MaintainStatusController {

    @Resource
    private MaintainStatusService maintainStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建维护状态字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:maintain-status:create')")
    public CommonResult<Long> createMaintainStatus(@Valid @RequestBody MaintainStatusSaveReqVO createReqVO) {
        return success(maintainStatusService.createMaintainStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新维护状态字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:maintain-status:update')")
    public CommonResult<Boolean> updateMaintainStatus(@Valid @RequestBody MaintainStatusSaveReqVO updateReqVO) {
        maintainStatusService.updateMaintainStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除维护状态字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:maintain-status:delete')")
    public CommonResult<Boolean> deleteMaintainStatus(@RequestParam("id") Long id) {
        maintainStatusService.deleteMaintainStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得维护状态字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:maintain-status:query')")
    public CommonResult<MaintainStatusRespVO> getMaintainStatus(@RequestParam("id") Long id) {
        MaintainStatusDO maintainStatus = maintainStatusService.getMaintainStatus(id);
        return success(BeanUtils.toBean(maintainStatus, MaintainStatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得维护状态字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:maintain-status:query')")
    public CommonResult<PageResult<MaintainStatusRespVO>> getMaintainStatusPage(@Valid MaintainStatusPageReqVO pageReqVO) {
        PageResult<MaintainStatusDO> pageResult = maintainStatusService.getMaintainStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MaintainStatusRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出维护状态字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:maintain-status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMaintainStatusExcel(@Valid MaintainStatusPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MaintainStatusDO> list = maintainStatusService.getMaintainStatusPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "维护状态字典表.xls", "数据", MaintainStatusRespVO.class,
                        BeanUtils.toBean(list, MaintainStatusRespVO.class));
    }

}*/
