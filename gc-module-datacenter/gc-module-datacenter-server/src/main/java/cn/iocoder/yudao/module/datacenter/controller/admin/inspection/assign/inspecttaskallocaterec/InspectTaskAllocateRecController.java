package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttaskallocaterec;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttaskallocaterec.vo.InspectTaskAllocateRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttaskallocaterec.vo.InspectTaskAllocateRecRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttaskallocaterec.vo.InspectTaskAllocateRecSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.assign.inspecttaskallocaterec.InspectTaskAllocateRecDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.assign.inspecttaskallocaterec.InspectTaskAllocateRecService;

@Tag(name = "管理后台 - 巡查巡检任务分配记录")
@RestController
@RequestMapping("/datacenter/inspect-task-allocate-rec")
@Validated
public class InspectTaskAllocateRecController {

    @Resource
    private InspectTaskAllocateRecService inspectTaskAllocateRecService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检任务分配记录")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-task-allocate-rec:create')")
    public CommonResult<Long> createInspectTaskAllocateRec(@Valid @RequestBody InspectTaskAllocateRecSaveReqVO createReqVO) {
        return success(inspectTaskAllocateRecService.createInspectTaskAllocateRec(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检任务分配记录")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-task-allocate-rec:update')")
    public CommonResult<Boolean> updateInspectTaskAllocateRec(@Valid @RequestBody InspectTaskAllocateRecSaveReqVO updateReqVO) {
        inspectTaskAllocateRecService.updateInspectTaskAllocateRec(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检任务分配记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-task-allocate-rec:delete')")
    public CommonResult<Boolean> deleteInspectTaskAllocateRec(@RequestParam("id") Long id) {
        inspectTaskAllocateRecService.deleteInspectTaskAllocateRec(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检任务分配记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-task-allocate-rec:query')")
    public CommonResult<InspectTaskAllocateRecRespVO> getInspectTaskAllocateRec(@RequestParam("id") Long id) {
        InspectTaskAllocateRecDO inspectTaskAllocateRec = inspectTaskAllocateRecService.getInspectTaskAllocateRec(id);
        return success(BeanUtils.toBean(inspectTaskAllocateRec, InspectTaskAllocateRecRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检任务分配记录分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-task-allocate-rec:query')")
    public CommonResult<PageResult<InspectTaskAllocateRecRespVO>> getInspectTaskAllocateRecPage(@Valid InspectTaskAllocateRecPageReqVO pageReqVO) {
        PageResult<InspectTaskAllocateRecDO> pageResult = inspectTaskAllocateRecService.getInspectTaskAllocateRecPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectTaskAllocateRecRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检任务分配记录 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-task-allocate-rec:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectTaskAllocateRecExcel(@Valid InspectTaskAllocateRecPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectTaskAllocateRecDO> list = inspectTaskAllocateRecService.getInspectTaskAllocateRecPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检任务分配记录.xls", "数据", InspectTaskAllocateRecRespVO.class,
                        BeanUtils.toBean(list, InspectTaskAllocateRecRespVO.class));
    }

}