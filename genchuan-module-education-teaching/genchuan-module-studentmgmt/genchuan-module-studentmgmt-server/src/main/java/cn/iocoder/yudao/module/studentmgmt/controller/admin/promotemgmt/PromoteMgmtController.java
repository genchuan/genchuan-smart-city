package cn.iocoder.yudao.module.studentmgmt.controller.admin.promotemgmt;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.promotemgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.promotemgmt.PromoteMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.service.promotemgmt.PromoteMgmtService;

@Tag(name = "学生管理后台 - 宣传管理")
@RestController
@RequestMapping("/studentmgmt/promote-mgmt")
@Validated
public class PromoteMgmtController {

    @Resource
    private PromoteMgmtService promoteMgmtService;

    @PostMapping("/create")
    @Operation(summary = "创建宣传管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:promote-mgmt:create')")
    public CommonResult<Long> createPromoteMgmt(@Valid @RequestBody PromoteMgmtSaveReqVO createReqVO) {
        return success(promoteMgmtService.createPromoteMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新宣传管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:promote-mgmt:update')")
    public CommonResult<Boolean> updatePromoteMgmt(@Valid @RequestBody PromoteMgmtSaveReqVO updateReqVO) {
        promoteMgmtService.updatePromoteMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除宣传管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:promote-mgmt:delete')")
    public CommonResult<Boolean> deletePromoteMgmt(@RequestParam("id") Long id) {
        promoteMgmtService.deletePromoteMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除宣传管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:promote-mgmt:delete')")
    public CommonResult<Boolean> deletePromoteMgmtList(@RequestParam("ids") List<Long> ids) {
        promoteMgmtService.deletePromoteMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得宣传管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:promote-mgmt:query')")
    public CommonResult<PromoteMgmtRespVO> getPromoteMgmt(@RequestParam("id") Long id) {
        PromoteMgmtDO promoteMgmt = promoteMgmtService.getPromoteMgmt(id);
        return success(BeanUtils.toBean(promoteMgmt, PromoteMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得宣传管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:promote-mgmt:query')")
    public CommonResult<PageResult<PromoteMgmtRespVO>> getPromoteMgmtPage(@Valid PromoteMgmtPageReqVO pageReqVO) {
        PageResult<PromoteMgmtDO> pageResult = promoteMgmtService.getPromoteMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PromoteMgmtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出宣传管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:promote-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPromoteMgmtExcel(@Valid PromoteMgmtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PromoteMgmtDO> list = promoteMgmtService.getPromoteMgmtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "宣传管理.xls", "数据", PromoteMgmtRespVO.class,
                        BeanUtils.toBean(list, PromoteMgmtRespVO.class));
    }

    @PutMapping("/execute")
    @Operation(summary = "执行")
    @PreAuthorize("@ss.hasPermission('studentmgmt:promote-mgmt:execute')")
    public CommonResult<Boolean> execute(@Valid @RequestBody PromoteMgmtExecuteReqVO reqVO) {
        return success(promoteMgmtService.execute(reqVO));
    }

    @GetMapping("/chart")
    @Operation(summary = "招生宣传统计看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:promote-mgmt:chart')")
    public CommonResult<PromoteMgmtChartRespVO> chart(@Valid PromoteMgmtChartReqVO reqVO) {
        return success(promoteMgmtService.chart(reqVO));
    }
    @GetMapping("/promoteCount")
    @Operation(summary = "各站点宣传人数统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:promote-mgmt:query')")
    public CommonResult<PromoteCountReqVO> promoteCount(@Valid PromoteMgmtChartReqVO reqVO) {
        return success(promoteMgmtService.promoteCount(reqVO));
    }

}