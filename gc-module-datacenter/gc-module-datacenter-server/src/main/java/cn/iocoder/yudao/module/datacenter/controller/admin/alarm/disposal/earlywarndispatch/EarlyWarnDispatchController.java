package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarndispatch;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarndispatch.vo.EarlyWarnDispatchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarndispatch.vo.EarlyWarnDispatchRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarndispatch.vo.EarlyWarnDispatchSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarndispatch.EarlyWarnDispatchDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.disposal.earlywarndispatch.EarlyWarnDispatchService;

@Tag(name = "管理后台 - 预警告警派单")
@RestController
@RequestMapping("/datacenter/early-warn-dispatch")
@Validated
public class EarlyWarnDispatchController {

    @Resource
    private EarlyWarnDispatchService earlyWarnDispatchService;

    @PostMapping("/create")
    @Operation(summary = "创建预警告警派单")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-dispatch:create')")
    public CommonResult<Long> createEarlyWarnDispatch(@Valid @RequestBody EarlyWarnDispatchSaveReqVO createReqVO) {
        return success(earlyWarnDispatchService.createEarlyWarnDispatch(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警告警派单")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-dispatch:update')")
    public CommonResult<Boolean> updateEarlyWarnDispatch(@Valid @RequestBody EarlyWarnDispatchSaveReqVO updateReqVO) {
        earlyWarnDispatchService.updateEarlyWarnDispatch(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警告警派单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-dispatch:delete')")
    public CommonResult<Boolean> deleteEarlyWarnDispatch(@RequestParam("id") Long id) {
        earlyWarnDispatchService.deleteEarlyWarnDispatch(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警告警派单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-dispatch:query')")
    public CommonResult<EarlyWarnDispatchRespVO> getEarlyWarnDispatch(@RequestParam("id") Long id) {
        EarlyWarnDispatchDO earlyWarnDispatch = earlyWarnDispatchService.getEarlyWarnDispatch(id);
        return success(BeanUtils.toBean(earlyWarnDispatch, EarlyWarnDispatchRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警告警派单分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-dispatch:query')")
    public CommonResult<PageResult<EarlyWarnDispatchRespVO>> getEarlyWarnDispatchPage(@Valid EarlyWarnDispatchPageReqVO pageReqVO) {
        PageResult<EarlyWarnDispatchDO> pageResult = earlyWarnDispatchService.getEarlyWarnDispatchPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnDispatchRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警告警派单 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-dispatch:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnDispatchExcel(@Valid EarlyWarnDispatchPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnDispatchDO> list = earlyWarnDispatchService.getEarlyWarnDispatchPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警告警派单.xls", "数据", EarlyWarnDispatchRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnDispatchRespVO.class));
    }

}