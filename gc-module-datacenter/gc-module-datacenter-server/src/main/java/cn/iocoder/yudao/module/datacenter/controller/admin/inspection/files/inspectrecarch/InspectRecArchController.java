package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrecarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrecarch.vo.InspectRecArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrecarch.vo.InspectRecArchRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrecarch.vo.InspectRecArchSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectrecarch.InspectRecArchDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.files.inspectrecarch.InspectRecArchService;

@Tag(name = "管理后台 - 巡查巡检记录档案")
@RestController
@RequestMapping("/datacenter/inspect-rec-arch")
@Validated
public class InspectRecArchController {

    @Resource
    private InspectRecArchService inspectRecArchService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检记录档案")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rec-arch:create')")
    public CommonResult<Long> createInspectRecArch(@Valid @RequestBody InspectRecArchSaveReqVO createReqVO) {
        return success(inspectRecArchService.createInspectRecArch(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检记录档案")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rec-arch:update')")
    public CommonResult<Boolean> updateInspectRecArch(@Valid @RequestBody InspectRecArchSaveReqVO updateReqVO) {
        inspectRecArchService.updateInspectRecArch(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检记录档案")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rec-arch:delete')")
    public CommonResult<Boolean> deleteInspectRecArch(@RequestParam("id") Long id) {
        inspectRecArchService.deleteInspectRecArch(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检记录档案")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rec-arch:query')")
    public CommonResult<InspectRecArchRespVO> getInspectRecArch(@RequestParam("id") Long id) {
        InspectRecArchDO inspectRecArch = inspectRecArchService.getInspectRecArch(id);
        return success(BeanUtils.toBean(inspectRecArch, InspectRecArchRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检记录档案分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rec-arch:query')")
    public CommonResult<PageResult<InspectRecArchRespVO>> getInspectRecArchPage(@Valid InspectRecArchPageReqVO pageReqVO) {
        PageResult<InspectRecArchDO> pageResult = inspectRecArchService.getInspectRecArchPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectRecArchRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检记录档案 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rec-arch:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectRecArchExcel(@Valid InspectRecArchPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectRecArchDO> list = inspectRecArchService.getInspectRecArchPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检记录档案.xls", "数据", InspectRecArchRespVO.class,
                        BeanUtils.toBean(list, InspectRecArchRespVO.class));
    }

}