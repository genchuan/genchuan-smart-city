package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectacceptarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectacceptarch.vo.InspectAcceptArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectacceptarch.vo.InspectAcceptArchRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectacceptarch.vo.InspectAcceptArchSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectacceptarch.InspectAcceptArchDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.files.inspectacceptarch.InspectAcceptArchService;

@Tag(name = "管理后台 - 巡查巡检验收档案")
@RestController
@RequestMapping("/datacenter/inspect-accept-arch")
@Validated
public class InspectAcceptArchController {

    @Resource
    private InspectAcceptArchService inspectAcceptArchService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检验收档案")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-accept-arch:create')")
    public CommonResult<Long> createInspectAcceptArch(@Valid @RequestBody InspectAcceptArchSaveReqVO createReqVO) {
        return success(inspectAcceptArchService.createInspectAcceptArch(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检验收档案")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-accept-arch:update')")
    public CommonResult<Boolean> updateInspectAcceptArch(@Valid @RequestBody InspectAcceptArchSaveReqVO updateReqVO) {
        inspectAcceptArchService.updateInspectAcceptArch(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检验收档案")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-accept-arch:delete')")
    public CommonResult<Boolean> deleteInspectAcceptArch(@RequestParam("id") Long id) {
        inspectAcceptArchService.deleteInspectAcceptArch(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检验收档案")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-accept-arch:query')")
    public CommonResult<InspectAcceptArchRespVO> getInspectAcceptArch(@RequestParam("id") Long id) {
        InspectAcceptArchDO inspectAcceptArch = inspectAcceptArchService.getInspectAcceptArch(id);
        return success(BeanUtils.toBean(inspectAcceptArch, InspectAcceptArchRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检验收档案分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-accept-arch:query')")
    public CommonResult<PageResult<InspectAcceptArchRespVO>> getInspectAcceptArchPage(@Valid InspectAcceptArchPageReqVO pageReqVO) {
        PageResult<InspectAcceptArchDO> pageResult = inspectAcceptArchService.getInspectAcceptArchPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectAcceptArchRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检验收档案 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-accept-arch:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectAcceptArchExcel(@Valid InspectAcceptArchPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectAcceptArchDO> list = inspectAcceptArchService.getInspectAcceptArchPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检验收档案.xls", "数据", InspectAcceptArchRespVO.class,
                        BeanUtils.toBean(list, InspectAcceptArchRespVO.class));
    }

}