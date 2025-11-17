package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectspecexecrec;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectspecexecrec.vo.InspectSpecExecRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectspecexecrec.vo.InspectSpecExecRecRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectspecexecrec.vo.InspectSpecExecRecSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.record.inspectspecexecrec.InspectSpecExecRecDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.record.inspectspecexecrec.InspectSpecExecRecService;

@Tag(name = "管理后台 - 专项巡查执行记录")
@RestController
@RequestMapping("/datacenter/inspect-spec-exec-rec")
@Validated
public class InspectSpecExecRecController {

    @Resource
    private InspectSpecExecRecService inspectSpecExecRecService;

    @PostMapping("/create")
    @Operation(summary = "创建专项巡查执行记录")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-spec-exec-rec:create')")
    public CommonResult<Long> createInspectSpecExecRec(@Valid @RequestBody InspectSpecExecRecSaveReqVO createReqVO) {
        return success(inspectSpecExecRecService.createInspectSpecExecRec(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新专项巡查执行记录")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-spec-exec-rec:update')")
    public CommonResult<Boolean> updateInspectSpecExecRec(@Valid @RequestBody InspectSpecExecRecSaveReqVO updateReqVO) {
        inspectSpecExecRecService.updateInspectSpecExecRec(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除专项巡查执行记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-spec-exec-rec:delete')")
    public CommonResult<Boolean> deleteInspectSpecExecRec(@RequestParam("id") Long id) {
        inspectSpecExecRecService.deleteInspectSpecExecRec(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得专项巡查执行记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-spec-exec-rec:query')")
    public CommonResult<InspectSpecExecRecRespVO> getInspectSpecExecRec(@RequestParam("id") Long id) {
        InspectSpecExecRecDO inspectSpecExecRec = inspectSpecExecRecService.getInspectSpecExecRec(id);
        return success(BeanUtils.toBean(inspectSpecExecRec, InspectSpecExecRecRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得专项巡查执行记录分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-spec-exec-rec:query')")
    public CommonResult<PageResult<InspectSpecExecRecRespVO>> getInspectSpecExecRecPage(@Valid InspectSpecExecRecPageReqVO pageReqVO) {
        PageResult<InspectSpecExecRecDO> pageResult = inspectSpecExecRecService.getInspectSpecExecRecPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectSpecExecRecRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出专项巡查执行记录 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-spec-exec-rec:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectSpecExecRecExcel(@Valid InspectSpecExecRecPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectSpecExecRecDO> list = inspectSpecExecRecService.getInspectSpecExecRecPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "专项巡查执行记录.xls", "数据", InspectSpecExecRecRespVO.class,
                        BeanUtils.toBean(list, InspectSpecExecRecRespVO.class));
    }

}