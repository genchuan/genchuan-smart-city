package cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle;

import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.workstatus.WorkStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.workstatus.WorkStatusRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.workstatus.WorkStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.WorkStatusDO;
import cn.iocoder.yudao.module.envirhealth.service.vehicle.workstatus.WorkStatusService;

@Tag(name = "字典表 - 作业状态")
@RestController
@RequestMapping("/envirhealth/work-status")
@Validated
public class WorkStatusController {

    @Resource
    private WorkStatusService workStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建作业状态字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:work-status:create')")
    public CommonResult<Long> createWorkStatus(@Valid @RequestBody WorkStatusSaveReqVO createReqVO) {
        return success(workStatusService.createWorkStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新作业状态字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:work-status:update')")
    public CommonResult<Boolean> updateWorkStatus(@Valid @RequestBody WorkStatusSaveReqVO updateReqVO) {
        workStatusService.updateWorkStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除作业状态字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:work-status:delete')")
    public CommonResult<Boolean> deleteWorkStatus(@RequestParam("id") Long id) {
        workStatusService.deleteWorkStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得作业状态字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:work-status:query')")
    public CommonResult<WorkStatusRespVO> getWorkStatus(@RequestParam("id") Long id) {
        WorkStatusDO workStatus = workStatusService.getWorkStatus(id);
        return success(BeanUtils.toBean(workStatus, WorkStatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得作业状态字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:work-status:query')")
    public CommonResult<PageResult<WorkStatusRespVO>> getWorkStatusPage(@Valid WorkStatusPageReqVO pageReqVO) {
        PageResult<WorkStatusDO> pageResult = workStatusService.getWorkStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WorkStatusRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出作业状态字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:work-status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWorkStatusExcel(@Valid WorkStatusPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WorkStatusDO> list = workStatusService.getWorkStatusPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "作业状态字典表.xls", "数据", WorkStatusRespVO.class,
                        BeanUtils.toBean(list, WorkStatusRespVO.class));
    }

    /**
     * 获得作业状态字典下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得作业状态字典(下拉框)")
    @PreAuthorize("@ss.hasPermission('envirhealth:work-status:query')")
    public CommonResult<List<OptionVO>> getWorkStatusOptions() {
        return success(workStatusService.getWorkStatusOptions());
    }
}
