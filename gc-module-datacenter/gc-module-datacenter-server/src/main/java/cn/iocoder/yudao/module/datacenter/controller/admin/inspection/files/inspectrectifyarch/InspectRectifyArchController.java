package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrectifyarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrectifyarch.vo.InspectRectifyArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrectifyarch.vo.InspectRectifyArchRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrectifyarch.vo.InspectRectifyArchSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectrectifyarch.InspectRectifyArchDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.files.inspectrectifyarch.InspectRectifyArchService;

@Tag(name = "管理后台 - 巡查巡检整改档案")
@RestController
@RequestMapping("/datacenter/inspect-rectify-arch")
@Validated
public class InspectRectifyArchController {

    @Resource
    private InspectRectifyArchService inspectRectifyArchService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检整改档案")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-arch:create')")
    public CommonResult<Long> createInspectRectifyArch(@Valid @RequestBody InspectRectifyArchSaveReqVO createReqVO) {
        return success(inspectRectifyArchService.createInspectRectifyArch(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检整改档案")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-arch:update')")
    public CommonResult<Boolean> updateInspectRectifyArch(@Valid @RequestBody InspectRectifyArchSaveReqVO updateReqVO) {
        inspectRectifyArchService.updateInspectRectifyArch(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检整改档案")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-arch:delete')")
    public CommonResult<Boolean> deleteInspectRectifyArch(@RequestParam("id") Long id) {
        inspectRectifyArchService.deleteInspectRectifyArch(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检整改档案")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-arch:query')")
    public CommonResult<InspectRectifyArchRespVO> getInspectRectifyArch(@RequestParam("id") Long id) {
        InspectRectifyArchDO inspectRectifyArch = inspectRectifyArchService.getInspectRectifyArch(id);
        return success(BeanUtils.toBean(inspectRectifyArch, InspectRectifyArchRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检整改档案分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-arch:query')")
    public CommonResult<PageResult<InspectRectifyArchRespVO>> getInspectRectifyArchPage(@Valid InspectRectifyArchPageReqVO pageReqVO) {
        PageResult<InspectRectifyArchDO> pageResult = inspectRectifyArchService.getInspectRectifyArchPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectRectifyArchRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检整改档案 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-arch:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectRectifyArchExcel(@Valid InspectRectifyArchPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectRectifyArchDO> list = inspectRectifyArchService.getInspectRectifyArchPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检整改档案.xls", "数据", InspectRectifyArchRespVO.class,
                        BeanUtils.toBean(list, InspectRectifyArchRespVO.class));
    }

}