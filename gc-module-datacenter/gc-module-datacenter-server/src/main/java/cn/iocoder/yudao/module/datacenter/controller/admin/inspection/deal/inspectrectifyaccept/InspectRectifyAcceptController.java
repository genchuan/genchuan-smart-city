package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifyaccept;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifyaccept.vo.InspectRectifyAcceptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifyaccept.vo.InspectRectifyAcceptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifyaccept.vo.InspectRectifyAcceptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.deal.inspectrectifyaccept.InspectRectifyAcceptDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.deal.inspectrectifyaccept.InspectRectifyAcceptService;

@Tag(name = "管理后台 - 巡查巡检整改结果验收")
@RestController
@RequestMapping("/datacenter/inspect-rectify-accept")
@Validated
public class InspectRectifyAcceptController {

    @Resource
    private InspectRectifyAcceptService inspectRectifyAcceptService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检整改结果验收")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-accept:create')")
    public CommonResult<Long> createInspectRectifyAccept(@Valid @RequestBody InspectRectifyAcceptSaveReqVO createReqVO) {
        return success(inspectRectifyAcceptService.createInspectRectifyAccept(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检整改结果验收")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-accept:update')")
    public CommonResult<Boolean> updateInspectRectifyAccept(@Valid @RequestBody InspectRectifyAcceptSaveReqVO updateReqVO) {
        inspectRectifyAcceptService.updateInspectRectifyAccept(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检整改结果验收")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-accept:delete')")
    public CommonResult<Boolean> deleteInspectRectifyAccept(@RequestParam("id") Long id) {
        inspectRectifyAcceptService.deleteInspectRectifyAccept(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检整改结果验收")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-accept:query')")
    public CommonResult<InspectRectifyAcceptRespVO> getInspectRectifyAccept(@RequestParam("id") Long id) {
        InspectRectifyAcceptDO inspectRectifyAccept = inspectRectifyAcceptService.getInspectRectifyAccept(id);
        return success(BeanUtils.toBean(inspectRectifyAccept, InspectRectifyAcceptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检整改结果验收分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-accept:query')")
    public CommonResult<PageResult<InspectRectifyAcceptRespVO>> getInspectRectifyAcceptPage(@Valid InspectRectifyAcceptPageReqVO pageReqVO) {
        PageResult<InspectRectifyAcceptDO> pageResult = inspectRectifyAcceptService.getInspectRectifyAcceptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectRectifyAcceptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检整改结果验收 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-accept:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectRectifyAcceptExcel(@Valid InspectRectifyAcceptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectRectifyAcceptDO> list = inspectRectifyAcceptService.getInspectRectifyAcceptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检整改结果验收.xls", "数据", InspectRectifyAcceptRespVO.class,
                        BeanUtils.toBean(list, InspectRectifyAcceptRespVO.class));
    }

}