package cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrule;

import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrule.vo.ParkPointsRulePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrule.vo.ParkPointsRuleRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrule.vo.ParkPointsRuleSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parkpointsrule.ParkPointsRuleDO;
import cn.iocoder.yudao.module.industry.service.park.marketing.parkpointsrule.ParkPointsRuleService;
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


@Tag(name = "管理后台 - 积分规则")
@RestController
@RequestMapping("/industry/park-points-rule")
@Validated
public class ParkPointsRuleController {

    @Resource
    private ParkPointsRuleService parkPointsRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建积分规则")
    @PreAuthorize("@ss.hasPermission('industry:park-points-rule:create')")
    public CommonResult<Long> createParkPointsRule(@Valid @RequestBody ParkPointsRuleSaveReqVO createReqVO) {
        return success(parkPointsRuleService.createParkPointsRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新积分规则")
    @PreAuthorize("@ss.hasPermission('industry:park-points-rule:update')")
    public CommonResult<Boolean> updateParkPointsRule(@Valid @RequestBody ParkPointsRuleSaveReqVO updateReqVO) {
        parkPointsRuleService.updateParkPointsRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除积分规则")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-points-rule:delete')")
    public CommonResult<Boolean> deleteParkPointsRule(@RequestParam("id") Long id) {
        parkPointsRuleService.deleteParkPointsRule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得积分规则")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-points-rule:query')")
    public CommonResult<ParkPointsRuleRespVO> getParkPointsRule(@RequestParam("id") Long id) {
        ParkPointsRuleDO parkPointsRule = parkPointsRuleService.getParkPointsRule(id);
        return success(BeanUtils.toBean(parkPointsRule, ParkPointsRuleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得积分规则分页")
    @PreAuthorize("@ss.hasPermission('industry:park-points-rule:query')")
    public CommonResult<PageResult<ParkPointsRuleRespVO>> getParkPointsRulePage(@Valid ParkPointsRulePageReqVO pageReqVO) {
        PageResult<ParkPointsRuleDO> pageResult = parkPointsRuleService.getParkPointsRulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkPointsRuleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出积分规则 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-points-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkPointsRuleExcel(@Valid ParkPointsRulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkPointsRuleDO> list = parkPointsRuleService.getParkPointsRulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "积分规则.xls", "数据", ParkPointsRuleRespVO.class,
                        BeanUtils.toBean(list, ParkPointsRuleRespVO.class));
    }

}
