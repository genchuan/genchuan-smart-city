package cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenCreateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenApproveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenRejectReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenExecuteReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenReapplyReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.specialpass.gateopen.GateOpenDO;
import cn.iocoder.yudao.module.vehiclepass.service.specialpass.gateopen.GateOpenService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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


@Tag(name = "管理后台 - 开闸管理")
@RestController
@RequestMapping("/vehiclepass/gate-open")
@Validated
public class GateOpenController {

    @Resource
    private GateOpenService openService;

    @PostMapping("/create")
    @Operation(summary = "创建开闸管理")
    @PreAuthorize("@ss.hasPermission('gate:open:create')")
    public CommonResult<Long> createOpen(@Valid @RequestBody GateOpenSaveReqVO createReqVO) {
        return success(openService.createOpen(createReqVO));
    }

    @PostMapping("/create-apply")
    @Operation(summary = "新增申请")
    @PreAuthorize("@ss.hasPermission('vehiclepass:gate-open:create')")
    public CommonResult<Long> createOpenApply(@Valid @RequestBody GateOpenCreateReqVO createReqVO) {
        return success(openService.createOpenApply(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新开闸管理")
    @PreAuthorize("@ss.hasPermission('gate:open:update')")
    public CommonResult<Boolean> updateOpen(@Valid @RequestBody GateOpenSaveReqVO updateReqVO) {
        openService.updateOpen(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除开闸管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('gate:open:delete')")
    public CommonResult<Boolean> deleteOpen(@RequestParam("id") Long id) {
        openService.deleteOpen(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除开闸管理")
    @PreAuthorize("@ss.hasPermission('gate:open:delete')")
    public CommonResult<Boolean> deleteOpenList(@RequestParam("ids") List<Long> ids) {
        openService.deleteOpenListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得开闸管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('gate:open:query')")
    public CommonResult<GateOpenRespVO> getOpen(@RequestParam("id") Long id) {
        GateOpenDO open = openService.getOpen(id);
        return success(BeanUtils.toBean(open, GateOpenRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得开闸管理分页")
    @PreAuthorize("@ss.hasPermission('gate:open:query')")
    public CommonResult<PageResult<GateOpenRespVO>> getOpenPage(@Valid GateOpenPageReqVO pageReqVO) {
        return success(openService.getOpenPageWithJoin(pageReqVO));
    }

    @GetMapping("/export")
    @Operation(summary = "导出开闸管理 Excel")
    @PreAuthorize("@ss.hasPermission('gate:open:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOpenExcel(@Valid GateOpenPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<GateOpenRespVO> pageResult = openService.getOpenPageWithJoin(pageReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "开闸管理.xls", "数据", GateOpenRespVO.class,
                pageResult.getList());
    }

    @PutMapping("/approve")
    @Operation(summary = "通过审批")
    @PreAuthorize("@ss.hasPermission('vehiclepass:gate-open:approve')")
    public CommonResult<Boolean> approve(@Valid @RequestBody GateOpenApproveReqVO reqVO) {
        openService.approve(reqVO);
        return success(true);
    }

    @PutMapping("/reject")
    @Operation(summary = "驳回")
    @PreAuthorize("@ss.hasPermission('vehiclepass:gate-open:reject')")
    public CommonResult<Boolean> reject(@Valid @RequestBody GateOpenRejectReqVO reqVO) {
        openService.reject(reqVO);
        return success(true);
    }

    @PutMapping("/execute")
    @Operation(summary = "执行开闸")
    @PreAuthorize("@ss.hasPermission('vehiclepass:gate-open:execute')")
    public CommonResult<Boolean> execute(@Valid @RequestBody GateOpenExecuteReqVO reqVO) {
        openService.execute(reqVO);
        return success(true);
    }

    @PutMapping("/reapply")
    @Operation(summary = "重新申请")
    @PreAuthorize("@ss.hasPermission('vehiclepass:gate-open:reapply')")
    public CommonResult<Boolean> reapply(@Valid @RequestBody GateOpenReapplyReqVO reqVO) {
        openService.reapply(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获取开闸管理统计")
    @PreAuthorize("@ss.hasPermission('vehiclepass:gate-open:chart')")
    public CommonResult<GateOpenChartRespVO> getChart(@Valid GateOpenChartReqVO reqVO) {
        return success(openService.getChart(reqVO));
    }

}