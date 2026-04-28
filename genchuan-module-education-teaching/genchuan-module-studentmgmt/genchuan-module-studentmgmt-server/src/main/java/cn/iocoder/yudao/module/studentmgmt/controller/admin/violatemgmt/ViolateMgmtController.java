package cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt;

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
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.violatemgmt.ViolateMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.service.violatemgmt.ViolateMgmtService;

@Tag(name = "学生管理后台 - 违纪管理")
@RestController
@RequestMapping("/studentmgmt/violate-mgmt")
@Validated
public class ViolateMgmtController {

    @Resource
    private ViolateMgmtService violateMgmtService;

    @PostMapping("/create")
    @Operation(summary = "创建违纪管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:create')")
//    @OperateLog(type = CREATE)
    public CommonResult<Long> createViolateMgmt(@Valid @RequestBody ViolateMgmtSaveReqVO createReqVO) {
        return success(violateMgmtService.createViolateMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新违纪管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:update')")
    public CommonResult<Boolean> updateViolateMgmt(@Valid @RequestBody ViolateMgmtSaveReqVO updateReqVO) {
        violateMgmtService.updateViolateMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除违纪管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:delete')")
    public CommonResult<Boolean> deleteViolateMgmt(@RequestParam("id") Long id) {
        violateMgmtService.deleteViolateMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除违纪管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:delete')")
    public CommonResult<Boolean> deleteViolateMgmtList(@RequestParam("ids") List<Long> ids) {
        violateMgmtService.deleteViolateMgmtListByIds(ids);
        return success(true);
    }


    @GetMapping("/get")
    @Operation(summary = "获得违纪管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:query')")
    public CommonResult<ViolateMgmtRespVO> getViolateMgmt(@RequestParam("id") Long id) {

        ViolateMgmtDO violateMgmt = violateMgmtService.getViolateMgmt(id);
        return success(BeanUtils.toBean(violateMgmt, ViolateMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得违纪管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:query')")
    public CommonResult<PageResult<ViolateMgmtPageRespVO>> getViolateMgmtPage(@Valid ViolateMgmtPageReqVO pageReqVO) {
        PageResult<ViolateMgmtPageRespVO> pageResult = violateMgmtService.getViolateMgmtPageVo(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ViolateMgmtPageRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出违纪管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportViolateMgmtExcel(@Valid ViolateMgmtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ViolateMgmtDO> list = violateMgmtService.getViolateMgmtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "违纪管理.xls", "数据", ViolateMgmtRespVO.class,
                        BeanUtils.toBean(list, ViolateMgmtRespVO.class));
    }


    @PutMapping("/audit")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "审批违纪管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:audit')")
    public CommonResult<Boolean> audit(@RequestParam("ids") List<Long> ids) {
        // 获取当前用户
        Long userId = getLoginUserId();
        boolean isSuccess = violateMgmtService.auditViolateMgmtListByIds(ids, userId);
        return success(isSuccess);
    }

    @PutMapping("/push")
    @Operation(summary = "推送违纪管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:push')")
    public CommonResult<Boolean> push(@RequestParam("id") Long id) {
        // 获取当前用户
        Long userId = getLoginUserId();
        Boolean isSuccess = violateMgmtService.push(id, userId);
        return success(isSuccess);
    }


    @PutMapping("/warn")
    @Operation(summary = "预警违纪管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:warn')")
    public CommonResult<Boolean> warn(@RequestParam("id") Long id) {
        // 获取当前用户
        Long userId = getLoginUserId();
        Boolean isSuccess = violateMgmtService.warn(id, userId);
        return success(isSuccess);
    }


    @GetMapping("/chart")
    @Operation(summary = "学生违纪预警看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-info:query')")
    public CommonResult<ViolateDashboardVO> chart(@Valid ViolateChartReqVO reqVO) {
        ViolateDashboardVO dashboardVO = violateMgmtService.chart(reqVO);
        return success(dashboardVO);
    }

    @GetMapping("/chart/violateCount")
    @Operation(summary = "各班级违纪次数 / 类型分布统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-info:query')")
    public CommonResult<ViolateCountDashboardVO> violateCount(@Valid ViolateChartReqVO reqVO) {
        ViolateCountDashboardVO dashboardVO = violateMgmtService.violateCount(reqVO);
        return success(dashboardVO);
    }

    @GetMapping("/chart/warnIndex")
    @Operation(summary = "各班级违纪次数 / 类型分布统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-info:query')")
    public CommonResult<List<ViolateWarnIndexRespVO>> warnIndex(@Valid ViolateWarnIndexReqVO reqVO) {
        List<ViolateWarnIndexRespVO> list = violateMgmtService.warnIndex(reqVO);
        return success(list);
    }


}