package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectemerexecrec;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectemerexecrec.vo.InspectEmerExecRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectemerexecrec.vo.InspectEmerExecRecRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectemerexecrec.vo.InspectEmerExecRecSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.record.inspectemerexecrec.InspectEmerExecRecDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.record.inspectemerexecrec.InspectEmerExecRecService;

@Tag(name = "管理后台 - 应急巡查执行记录")
@RestController
@RequestMapping("/datacenter/inspect-emer-exec-rec")
@Validated
public class InspectEmerExecRecController {

    @Resource
    private InspectEmerExecRecService inspectEmerExecRecService;

    @PostMapping("/create")
    @Operation(summary = "创建应急巡查执行记录")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-emer-exec-rec:create')")
    public CommonResult<Long> createInspectEmerExecRec(@Valid @RequestBody InspectEmerExecRecSaveReqVO createReqVO) {
        return success(inspectEmerExecRecService.createInspectEmerExecRec(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新应急巡查执行记录")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-emer-exec-rec:update')")
    public CommonResult<Boolean> updateInspectEmerExecRec(@Valid @RequestBody InspectEmerExecRecSaveReqVO updateReqVO) {
        inspectEmerExecRecService.updateInspectEmerExecRec(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除应急巡查执行记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-emer-exec-rec:delete')")
    public CommonResult<Boolean> deleteInspectEmerExecRec(@RequestParam("id") Long id) {
        inspectEmerExecRecService.deleteInspectEmerExecRec(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得应急巡查执行记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-emer-exec-rec:query')")
    public CommonResult<InspectEmerExecRecRespVO> getInspectEmerExecRec(@RequestParam("id") Long id) {
        InspectEmerExecRecDO inspectEmerExecRec = inspectEmerExecRecService.getInspectEmerExecRec(id);
        return success(BeanUtils.toBean(inspectEmerExecRec, InspectEmerExecRecRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得应急巡查执行记录分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-emer-exec-rec:query')")
    public CommonResult<PageResult<InspectEmerExecRecRespVO>> getInspectEmerExecRecPage(@Valid InspectEmerExecRecPageReqVO pageReqVO) {
        PageResult<InspectEmerExecRecDO> pageResult = inspectEmerExecRecService.getInspectEmerExecRecPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectEmerExecRecRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出应急巡查执行记录 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-emer-exec-rec:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectEmerExecRecExcel(@Valid InspectEmerExecRecPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectEmerExecRecDO> list = inspectEmerExecRecService.getInspectEmerExecRecPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "应急巡查执行记录.xls", "数据", InspectEmerExecRecRespVO.class,
                        BeanUtils.toBean(list, InspectEmerExecRecRespVO.class));
    }

}