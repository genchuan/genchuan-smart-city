package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectdailyexecrec;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectdailyexecrec.vo.InspectDailyExecRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectdailyexecrec.vo.InspectDailyExecRecRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectdailyexecrec.vo.InspectDailyExecRecSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.record.inspectdailyexecrec.InspectDailyExecRecDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.record.inspectdailyexecrec.InspectDailyExecRecService;

@Tag(name = "管理后台 - 日常巡查执行记录")
@RestController
@RequestMapping("/datacenter/inspect-daily-exec-rec")
@Validated
public class InspectDailyExecRecController {

    @Resource
    private InspectDailyExecRecService inspectDailyExecRecService;

    @PostMapping("/create")
    @Operation(summary = "创建日常巡查执行记录")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-exec-rec:create')")
    public CommonResult<Long> createInspectDailyExecRec(@Valid @RequestBody InspectDailyExecRecSaveReqVO createReqVO) {
        return success(inspectDailyExecRecService.createInspectDailyExecRec(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新日常巡查执行记录")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-exec-rec:update')")
    public CommonResult<Boolean> updateInspectDailyExecRec(@Valid @RequestBody InspectDailyExecRecSaveReqVO updateReqVO) {
        inspectDailyExecRecService.updateInspectDailyExecRec(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除日常巡查执行记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-exec-rec:delete')")
    public CommonResult<Boolean> deleteInspectDailyExecRec(@RequestParam("id") Long id) {
        inspectDailyExecRecService.deleteInspectDailyExecRec(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得日常巡查执行记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-exec-rec:query')")
    public CommonResult<InspectDailyExecRecRespVO> getInspectDailyExecRec(@RequestParam("id") Long id) {
        InspectDailyExecRecDO inspectDailyExecRec = inspectDailyExecRecService.getInspectDailyExecRec(id);
        return success(BeanUtils.toBean(inspectDailyExecRec, InspectDailyExecRecRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得日常巡查执行记录分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-exec-rec:query')")
    public CommonResult<PageResult<InspectDailyExecRecRespVO>> getInspectDailyExecRecPage(@Valid InspectDailyExecRecPageReqVO pageReqVO) {
        PageResult<InspectDailyExecRecDO> pageResult = inspectDailyExecRecService.getInspectDailyExecRecPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectDailyExecRecRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出日常巡查执行记录 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-exec-rec:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectDailyExecRecExcel(@Valid InspectDailyExecRecPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectDailyExecRecDO> list = inspectDailyExecRecService.getInspectDailyExecRecPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "日常巡查执行记录.xls", "数据", InspectDailyExecRecRespVO.class,
                        BeanUtils.toBean(list, InspectDailyExecRecRespVO.class));
    }

}