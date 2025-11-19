package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectledgerarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectledgerarch.vo.InspectLedgerArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectledgerarch.vo.InspectLedgerArchRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectledgerarch.vo.InspectLedgerArchSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectledgerarch.InspectLedgerArchDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.files.inspectledgerarch.InspectLedgerArchService;

@Tag(name = "管理后台 - 巡查巡检台账档案")
@RestController
@RequestMapping("/datacenter/inspect-ledger-arch")
@Validated
public class InspectLedgerArchController {

    @Resource
    private InspectLedgerArchService inspectLedgerArchService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检台账档案")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-ledger-arch:create')")
    public CommonResult<Long> createInspectLedgerArch(@Valid @RequestBody InspectLedgerArchSaveReqVO createReqVO) {
        return success(inspectLedgerArchService.createInspectLedgerArch(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检台账档案")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-ledger-arch:update')")
    public CommonResult<Boolean> updateInspectLedgerArch(@Valid @RequestBody InspectLedgerArchSaveReqVO updateReqVO) {
        inspectLedgerArchService.updateInspectLedgerArch(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检台账档案")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-ledger-arch:delete')")
    public CommonResult<Boolean> deleteInspectLedgerArch(@RequestParam("id") Long id) {
        inspectLedgerArchService.deleteInspectLedgerArch(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检台账档案")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-ledger-arch:query')")
    public CommonResult<InspectLedgerArchRespVO> getInspectLedgerArch(@RequestParam("id") Long id) {
        InspectLedgerArchDO inspectLedgerArch = inspectLedgerArchService.getInspectLedgerArch(id);
        return success(BeanUtils.toBean(inspectLedgerArch, InspectLedgerArchRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检台账档案分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-ledger-arch:query')")
    public CommonResult<PageResult<InspectLedgerArchRespVO>> getInspectLedgerArchPage(@Valid InspectLedgerArchPageReqVO pageReqVO) {
        PageResult<InspectLedgerArchDO> pageResult = inspectLedgerArchService.getInspectLedgerArchPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectLedgerArchRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检台账档案 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-ledger-arch:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectLedgerArchExcel(@Valid InspectLedgerArchPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectLedgerArchDO> list = inspectLedgerArchService.getInspectLedgerArchPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检台账档案.xls", "数据", InspectLedgerArchRespVO.class,
                        BeanUtils.toBean(list, InspectLedgerArchRespVO.class));
    }

}