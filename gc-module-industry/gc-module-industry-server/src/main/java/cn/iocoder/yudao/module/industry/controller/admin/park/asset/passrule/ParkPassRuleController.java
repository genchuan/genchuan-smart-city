package cn.iocoder.yudao.module.industry.controller.admin.park.asset.passrule;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.passrule.vo.ParkPassRulePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.passrule.vo.ParkPassRuleRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.passrule.vo.ParkPassRuleSaveReqVO;
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

import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.passrule.ParkPassRuleDO;
import cn.iocoder.yudao.module.industry.service.park.asset.passrule.ParkPassRuleService;

@Tag(name = "管理后台 - 通行规则")
@RestController
@RequestMapping("/industry/park-pass-rule")
@Validated
public class ParkPassRuleController {

    @Resource
    private ParkPassRuleService parkPassRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建通行规则")
    @PreAuthorize("@ss.hasPermission('industry:park-pass-rule:create')")
    public CommonResult<Long> createParkPassRule(@Valid @RequestBody ParkPassRuleSaveReqVO createReqVO) {
        return success(parkPassRuleService.createParkPassRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新通行规则")
    @PreAuthorize("@ss.hasPermission('industry:park-pass-rule:update')")
    public CommonResult<Boolean> updateParkPassRule(@Valid @RequestBody ParkPassRuleSaveReqVO updateReqVO) {
        parkPassRuleService.updateParkPassRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除通行规则")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-pass-rule:delete')")
    public CommonResult<Boolean> deleteParkPassRule(@RequestParam("id") Long id) {
        parkPassRuleService.deleteParkPassRule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得通行规则")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-pass-rule:query')")
    public CommonResult<ParkPassRuleRespVO> getParkPassRule(@RequestParam("id") Long id) {
        ParkPassRuleDO parkPassRule = parkPassRuleService.getParkPassRule(id);
        return success(BeanUtils.toBean(parkPassRule, ParkPassRuleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得通行规则分页")
    @PreAuthorize("@ss.hasPermission('industry:park-pass-rule:query')")
    public CommonResult<PageResult<ParkPassRuleRespVO>> getParkPassRulePage(@Valid ParkPassRulePageReqVO pageReqVO) {
        PageResult<ParkPassRuleDO> pageResult = parkPassRuleService.getParkPassRulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkPassRuleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出通行规则 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-pass-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkPassRuleExcel(@Valid ParkPassRulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkPassRuleDO> list = parkPassRuleService.getParkPassRulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "通行规则.xls", "数据", ParkPassRuleRespVO.class,
                        BeanUtils.toBean(list, ParkPassRuleRespVO.class));
    }

}