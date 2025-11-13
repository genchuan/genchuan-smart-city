package cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerresstatus;

import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerresstatus.vo.EmerResStatusPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerresstatus.vo.EmerResStatusRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerresstatus.vo.EmerResStatusSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerresstatus.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.emerplan.emerresstatus.EmerResStatusDO;
import cn.iocoder.yudao.module.datacenter.service.emerplan.emerresstatus.EmerResStatusService;

@Tag(name = "管理后台 - 资源状态统计")
@RestController
@RequestMapping("/datacenter/emer-res-status")
@Validated
public class EmerResStatusController {

    @Resource
    private EmerResStatusService emerResStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建资源状态统计")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-res-status:create')")
    public CommonResult<Long> createEmerResStatus(@Valid @RequestBody EmerResStatusSaveReqVO createReqVO) {
        return success(emerResStatusService.createEmerResStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资源状态统计")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-res-status:update')")
    public CommonResult<Boolean> updateEmerResStatus(@Valid @RequestBody EmerResStatusSaveReqVO updateReqVO) {
        emerResStatusService.updateEmerResStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资源状态统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:emer-res-status:delete')")
    public CommonResult<Boolean> deleteEmerResStatus(@RequestParam("id") Long id) {
        emerResStatusService.deleteEmerResStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资源状态统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-res-status:query')")
    public CommonResult<EmerResStatusRespVO> getEmerResStatus(@RequestParam("id") Long id) {
        EmerResStatusDO emerResStatus = emerResStatusService.getEmerResStatus(id);
        return success(BeanUtils.toBean(emerResStatus, EmerResStatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资源状态统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-res-status:query')")
    public CommonResult<PageResult<EmerResStatusRespVO>> getEmerResStatusPage(@Valid EmerResStatusPageReqVO pageReqVO) {
        PageResult<EmerResStatusDO> pageResult = emerResStatusService.getEmerResStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EmerResStatusRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资源状态统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-res-status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEmerResStatusExcel(@Valid EmerResStatusPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EmerResStatusDO> list = emerResStatusService.getEmerResStatusPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资源状态统计.xls", "数据", EmerResStatusRespVO.class,
                        BeanUtils.toBean(list, EmerResStatusRespVO.class));
    }

}