package cn.iocoder.yudao.module.studentmgmt.controller.admin.coopenterprise;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.BaseChartReqVO;
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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.coopenterprise.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.coopenterprise.CoopEnterpriseDO;
import cn.iocoder.yudao.module.studentmgmt.service.coopenterprise.CoopEnterpriseService;

@Tag(name = "学生管理后台 - 校企合作")
@RestController
@RequestMapping("/studentmgmt/coop-enterprise")
@Validated
public class CoopEnterpriseController {

    @Resource
    private CoopEnterpriseService coopEnterpriseService;

    @PostMapping("/create")
    @Operation(summary = "创建校企合作")
    @PreAuthorize("@ss.hasPermission('studentmgmt:coop-enterprise:create')")
    public CommonResult<Long> createCoopEnterprise(@Valid @RequestBody CoopEnterpriseSaveReqVO createReqVO) {
        return success(coopEnterpriseService.createCoopEnterprise(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新校企合作")
    @PreAuthorize("@ss.hasPermission('studentmgmt:coop-enterprise:update')")
    public CommonResult<Boolean> updateCoopEnterprise(@Valid @RequestBody CoopEnterpriseSaveReqVO updateReqVO) {
        coopEnterpriseService.updateCoopEnterprise(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除校企合作")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:coop-enterprise:delete')")
    public CommonResult<Boolean> deleteCoopEnterprise(@RequestParam("id") Long id) {
        coopEnterpriseService.deleteCoopEnterprise(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除校企合作")
                @PreAuthorize("@ss.hasPermission('studentmgmt:coop-enterprise:delete')")
    public CommonResult<Boolean> deleteCoopEnterpriseList(@RequestParam("ids") List<Long> ids) {
        coopEnterpriseService.deleteCoopEnterpriseListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得校企合作")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:coop-enterprise:query')")
    public CommonResult<CoopEnterpriseRespVO> getCoopEnterprise(@RequestParam("id") Long id) {
        CoopEnterpriseDO coopEnterprise = coopEnterpriseService.getCoopEnterprise(id);
        return success(BeanUtils.toBean(coopEnterprise, CoopEnterpriseRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得校企合作分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:coop-enterprise:query')")
    public CommonResult<PageResult<CoopEnterpriseRespVO>> getCoopEnterprisePage(@Valid CoopEnterprisePageReqVO pageReqVO) {
        PageResult<CoopEnterpriseDO> pageResult = coopEnterpriseService.getCoopEnterprisePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CoopEnterpriseRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出校企合作 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:coop-enterprise:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCoopEnterpriseExcel(@Valid CoopEnterprisePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CoopEnterpriseDO> list = coopEnterpriseService.getCoopEnterprisePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "校企合作.xls", "数据", CoopEnterpriseRespVO.class,
                        BeanUtils.toBean(list, CoopEnterpriseRespVO.class));
    }


    @PutMapping("/maintain")
    @Operation(summary = "维护")
    @PreAuthorize("@ss.hasPermission('studentmgmt:coop-enterprise:maintain')")
    public CommonResult<Boolean> maintain(@Valid @RequestBody CoopEnterpriseMaintainReqVO updateReqVO) {
        return success(coopEnterpriseService.maintain(updateReqVO));
    }

    @GetMapping("/chart")
    @Operation(summary = "校企合作资源看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:coop-enterprise:chart')")
    public CommonResult<CoopEnterpriseChartRespVO> chart(@Valid CoopEnterpriseChartReqVO reqVO) {
        return success(coopEnterpriseService.chart(reqVO));
    }

    @GetMapping("/chart/enterpriseDistribution")
    @Operation(summary = "合作企业类型 / 系部分布统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:coop-enterprise:query')")
    public CommonResult<CoopEnterpriseDistributionRespVO> enterpriseDistribution(@Valid BaseChartReqVO reqVO) {
        return success(coopEnterpriseService.enterpriseDistribution(reqVO));
    }

}