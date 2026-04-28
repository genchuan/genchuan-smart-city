package cn.iocoder.yudao.module.studentmgmt.controller.admin.staymgmt;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.staymgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.staymgmt.StayMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.service.staymgmt.StayMgmtService;

@Tag(name = "学生管理后台 - 留宿管理")
@RestController
@RequestMapping("/studentmgmt/stay-mgmt")
@Validated
public class StayMgmtController {

    @Resource
    private StayMgmtService stayMgmtService;

    @PostMapping("/create")
    @Operation(summary = "创建留宿管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:stay-mgmt:create')")
    public CommonResult<Long> createStayMgmt(@Valid @RequestBody StayMgmtSaveReqVO createReqVO) {
        return success(stayMgmtService.createStayMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新留宿管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:stay-mgmt:update')")
    public CommonResult<Boolean> updateStayMgmt(@Valid @RequestBody StayMgmtSaveReqVO updateReqVO) {
        stayMgmtService.updateStayMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除留宿管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:stay-mgmt:delete')")
    public CommonResult<Boolean> deleteStayMgmt(@RequestParam("id") Long id) {
        stayMgmtService.deleteStayMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除留宿管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:stay-mgmt:delete')")
    public CommonResult<Boolean> deleteStayMgmtList(@RequestParam("ids") List<Long> ids) {
        stayMgmtService.deleteStayMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得留宿管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:stay-mgmt:query')")
    public CommonResult<StayMgmtRespVO> getStayMgmt(@RequestParam("id") Long id) {
        StayMgmtDO stayMgmt = stayMgmtService.getStayMgmt(id);
        return success(BeanUtils.toBean(stayMgmt, StayMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得留宿管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:stay-mgmt:query')")
    public CommonResult<PageResult<StayMgmtRespVO>> getStayMgmtPage(@Valid StayMgmtPageReqVO pageReqVO) {
        PageResult<StayMgmtDO> pageResult = stayMgmtService.getStayMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StayMgmtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出留宿管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:stay-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStayMgmtExcel(@Valid StayMgmtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StayMgmtDO> list = stayMgmtService.getStayMgmtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "留宿管理.xls", "数据", StayMgmtRespVO.class,
                        BeanUtils.toBean(list, StayMgmtRespVO.class));
    }

    @PutMapping("/confirm")
    @Operation(summary = "确认")
    @PreAuthorize("@ss.hasPermission('studentmgmt:stay-mgmt:confirm')")
    public CommonResult<Boolean> confirm(@Valid @RequestBody StayMgmtConfirmReqVO reqVO) {
        Boolean isSuccess = stayMgmtService.confirm(reqVO);
        return success(isSuccess);
    }
    @PutMapping("/audit")
    @Operation(summary = "确认")
    @PreAuthorize("@ss.hasPermission('studentmgmt:stay-mgmt:audit')")
    public CommonResult<Boolean> audit(@Valid @RequestBody StayMgmtConfirmReqVO reqVO) {
        Boolean isSuccess = stayMgmtService.audit(reqVO);
        return success(isSuccess);
    }
    @GetMapping("/chart")
    @Operation(summary = "周末留宿统计看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:stay-mgmt:chart')")
    public CommonResult<StayMgmtChartRespVO> chart(@Valid StayMgmtChartReqVO reqVO) {
        StayMgmtChartRespVO vo = stayMgmtService.chart(reqVO);
        return success(vo);
    }
    @GetMapping("/stayCount")
    @Operation(summary = "各班级留宿人数统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:stay-mgmt:stayCount')")
    public CommonResult<StayMgmtStayCountRespVO> stayCount(@Valid StayMgmtStayCountReqVO reqVO) {
        StayMgmtStayCountRespVO vo = stayMgmtService.stayCount(reqVO);
        return success(vo);
    }

}