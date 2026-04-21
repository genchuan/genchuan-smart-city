package cn.iocoder.yudao.module.studentmgmt.controller.admin.behaviormgmt;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo.MentalMgmtChartRespVO;
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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.behaviormgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.behaviormgmt.BehaviorMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.service.behaviormgmt.BehaviorMgmtService;

@Tag(name = "学生管理后台 - 行为管理")
@RestController
@RequestMapping("/studentmgmt/behavior-mgmt")
@Validated
public class BehaviorMgmtController {

    @Resource
    private BehaviorMgmtService behaviorMgmtService;

    @PostMapping("/create")
    @Operation(summary = "创建行为管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:behavior-mgmt:create')")
    public CommonResult<Long> createBehaviorMgmt(@Valid @RequestBody BehaviorMgmtSaveReqVO createReqVO) {
        return success(behaviorMgmtService.createBehaviorMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新行为管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:behavior-mgmt:update')")
    public CommonResult<Boolean> updateBehaviorMgmt(@Valid @RequestBody BehaviorMgmtSaveReqVO updateReqVO) {
        behaviorMgmtService.updateBehaviorMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除行为管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:behavior-mgmt:delete')")
    public CommonResult<Boolean> deleteBehaviorMgmt(@RequestParam("id") Long id) {
        behaviorMgmtService.deleteBehaviorMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除行为管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:behavior-mgmt:delete')")
    public CommonResult<Boolean> deleteBehaviorMgmtList(@RequestParam("ids") List<Long> ids) {
        behaviorMgmtService.deleteBehaviorMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得行为管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:behavior-mgmt:query')")
    public CommonResult<BehaviorMgmtRespVO> getBehaviorMgmt(@RequestParam("id") Long id) {
        BehaviorMgmtDO behaviorMgmt = behaviorMgmtService.getBehaviorMgmt(id);
        return success(BeanUtils.toBean(behaviorMgmt, BehaviorMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得行为管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:behavior-mgmt:query')")
    public CommonResult<PageResult<BehaviorMgmtRespVO>> getBehaviorMgmtPage(@Valid BehaviorMgmtPageReqVO pageReqVO) {
        PageResult<BehaviorMgmtDO> pageResult = behaviorMgmtService.getBehaviorMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BehaviorMgmtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出行为管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:behavior-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBehaviorMgmtExcel(@Valid BehaviorMgmtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BehaviorMgmtDO> list = behaviorMgmtService.getBehaviorMgmtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "行为管理.xls", "数据", BehaviorMgmtRespVO.class,
                        BeanUtils.toBean(list, BehaviorMgmtRespVO.class));
    }


    @PutMapping("/audit")
    @Operation(summary = "更新行为管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:behavior-mgmt:update')")
    public CommonResult<Boolean> audit(@Valid @RequestBody BehaviorMgmtAuditReqVO reqVO) {
        boolean isSuccess = behaviorMgmtService.audit(reqVO);
        return success(isSuccess);
    }

    @PutMapping("/cancel")
    @Operation(summary = "取消行为管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:behavior-mgmt:update')")
    public CommonResult<Boolean> cancel(@Valid @RequestBody BehaviorMgmtCancelReqVO reqVO) {
        boolean isSuccess = behaviorMgmtService.cancel(reqVO);
        return success(isSuccess);
    }

    @GetMapping("/chart")
    @Operation(summary = "学生行为看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:behavior-mgmt:update')")
    public CommonResult<BehaviorMgmtChartRespVO> chart(@Valid @RequestBody BehaviorMgmtChartReqVO reqVO) {
        BehaviorMgmtChartRespVO dashboardVO = behaviorMgmtService.chart(reqVO);
        return success(dashboardVO);
    }


    @GetMapping("/attendanceCount")
    @Operation(summary = "各班级请假次数 / 考勤异常人数统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:behavior-mgmt:update')")
    public CommonResult<BehaviorMgmtAttendanceCountRespVO> attendanceCount(@Valid @RequestBody BehaviorMgmtAttendanceCountReqVO reqVO) {
        BehaviorMgmtAttendanceCountRespVO dashboardVO = behaviorMgmtService.attendanceCount(reqVO);
        return success(dashboardVO);
    }


}