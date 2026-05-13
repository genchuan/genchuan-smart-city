package cn.iocoder.yudao.module.studentmgmt.controller.admin.checkin;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.checkin.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.checkin.CheckInDO;
import cn.iocoder.yudao.module.studentmgmt.service.checkin.CheckInService;

@Tag(name = "管理后台 - 报到管理")
@RestController
@RequestMapping("/studentmgmt/check-in")
@Validated
public class CheckInController {

    @Resource
    private CheckInService checkInService;

    @PostMapping("/create")
    @Operation(summary = "创建报到管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:check-in:create')")
    public CommonResult<Long> createCheckIn(@Valid @RequestBody CheckInSaveReqVO createReqVO) {
        return success(checkInService.createCheckIn(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新报到管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:check-in:update')")
    public CommonResult<Boolean> updateCheckIn(@Valid @RequestBody CheckInSaveReqVO updateReqVO) {
        checkInService.updateCheckIn(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除报到管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:check-in:delete')")
    public CommonResult<Boolean> deleteCheckIn(@RequestParam("id") Long id) {
        checkInService.deleteCheckIn(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除报到管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:check-in:delete')")
    public CommonResult<Boolean> deleteCheckInList(@RequestParam("ids") List<Long> ids) {
        checkInService.deleteCheckInListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得报到管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:check-in:query')")
    public CommonResult<CheckInRespVO> getCheckIn(@RequestParam("id") Long id) {
        CheckInDO checkIn = checkInService.getCheckIn(id);
        return success(BeanUtils.toBean(checkIn, CheckInRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得报到管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:check-in:query')")
    public CommonResult<PageResult<CheckInRespVO>> getCheckInPage(@Valid CheckInPageReqVO pageReqVO) {
        PageResult<CheckInDO> pageResult = checkInService.getCheckInPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CheckInRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出报到管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:check-in:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCheckInExcel(@Valid CheckInPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CheckInDO> list = checkInService.getCheckInPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "报到管理.xls", "数据", CheckInRespVO.class,
                        BeanUtils.toBean(list, CheckInRespVO.class));
    }

    @PutMapping("/supply")
    @Operation(summary = "补充")
    @PreAuthorize("@ss.hasPermission('studentmgmt:check-in:supply')")
    public CommonResult<Boolean> supply(@Valid @RequestBody CheckInSupplyReqVO reqVO) {
        return success(checkInService.supply(reqVO));
    }

    @PutMapping("/confirm")
    @Operation(summary = "确认")
    @PreAuthorize("@ss.hasPermission('studentmgmt:check-in:confirm')")
    public CommonResult<Boolean> confirm(@Valid @RequestBody CheckInConfirmReqVO reqVO) {
        return success(checkInService.confirm(reqVO));
    }

    @PutMapping("/audit")
    @Operation(summary = "审核")
    @PreAuthorize("@ss.hasPermission('studentmgmt:check-in:audit')")
    public CommonResult<Boolean> audit(@Valid @RequestBody CheckInConfirmReqVO reqVO) {
        return success(checkInService.audit(reqVO));
    }

    @GetMapping("/chart")
    @Operation(summary = "新生报到进度看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:check-in:chart')")
    public CommonResult<CheckInChartRespVO> chart(@Valid CheckInChartReqVO reqVO) {
        return success(checkInService.chart(reqVO));
    }

    @GetMapping("/checkinIndex")
    @Operation(summary = "新生报到进度看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:check-in:query')")
    public CommonResult<CheckInChartIndexRespVO> checkinIndex(@Valid CheckInChartReqVO reqVO) {
        return success(checkInService.checkinIndex(reqVO));
    }

}