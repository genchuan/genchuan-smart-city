package cn.iocoder.yudao.module.studentmgmt.controller.admin.fundsystem;

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
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUser;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserNickname;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.fundsystem.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.fundsystem.FundSystemDO;
import cn.iocoder.yudao.module.studentmgmt.service.fundsystem.FundSystemService;

@Tag(name = "学生管理后台 - 资助系统")
@RestController
@RequestMapping("/studentmgmt/fund-system")
@Validated
public class FundSystemController {

    @Resource
    private FundSystemService fundSystemService;

    @PostMapping("/create")
    @Operation(summary = "创建资助系统")
    @PreAuthorize("@ss.hasPermission('studentmgmt:fund-system:create')")
    public CommonResult<Long> createFundSystem(@Valid @RequestBody FundSystemSaveReqVO createReqVO) {
        return success(fundSystemService.createFundSystem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资助系统")
    @PreAuthorize("@ss.hasPermission('studentmgmt:fund-system:update')")
    public CommonResult<Boolean> updateFundSystem(@Valid @RequestBody FundSystemSaveReqVO updateReqVO) {
        fundSystemService.updateFundSystem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资助系统")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:fund-system:delete')")
    public CommonResult<Boolean> deleteFundSystem(@RequestParam("id") Long id) {
        fundSystemService.deleteFundSystem(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除资助系统")
                @PreAuthorize("@ss.hasPermission('studentmgmt:fund-system:delete')")
    public CommonResult<Boolean> deleteFundSystemList(@RequestParam("ids") List<Long> ids) {
        fundSystemService.deleteFundSystemListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资助系统")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:fund-system:query')")
    public CommonResult<FundSystemRespVO> getFundSystem(@RequestParam("id") Long id) {
        FundSystemDO fundSystem = fundSystemService.getFundSystem(id);
        return success(BeanUtils.toBean(fundSystem, FundSystemRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资助系统分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:fund-system:query')")
    public CommonResult<PageResult<FundSystemRespVO>> getFundSystemPage(@Valid FundSystemPageReqVO pageReqVO) {
        PageResult<FundSystemDO> pageResult = fundSystemService.getFundSystemPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, FundSystemRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资助系统 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:fund-system:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFundSystemExcel(@Valid FundSystemPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FundSystemDO> list = fundSystemService.getFundSystemPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资助系统.xls", "数据", FundSystemRespVO.class,
                        BeanUtils.toBean(list, FundSystemRespVO.class));
    }

    @PutMapping("/audit")
    @Operation(summary = "审核资助系统")
    @PreAuthorize("@ss.hasPermission('studentmgmt:fund-system:audit')")
    public CommonResult<Boolean> audit(@Valid @RequestBody FundSystemAuditReqVO reqVO) {
        boolean isSuccess = fundSystemService.audit(reqVO);
        return success(isSuccess);
    }

    @PutMapping("/chart")
    @Operation(summary = "资助信息统计看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:fund-system:query')")
    public CommonResult<FundSystemChartRespVO> chart(@Valid @RequestBody FundSystemChartReqVO reqVO) {
        FundSystemChartRespVO dashboardVO = fundSystemService.chart(reqVO);
        return success(dashboardVO);
    }

    @PutMapping("/fundCount")
    @Operation(summary = "各年级资助人数 / 类型分布统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:fund-system:query')")
    public CommonResult<FundSystemFundCountRespVO> fundCount(@Valid @RequestBody FundSystemFundCountReqVO reqVO) {
        FundSystemFundCountRespVO dashboardVO = fundSystemService.fundCount(reqVO);
        return success(dashboardVO);
    }


}