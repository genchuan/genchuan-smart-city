package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcoderule;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcoderule.vo.MonCompCodeRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcoderule.vo.MonCompCodeRuleRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcoderule.vo.MonCompCodeRuleSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcoderule.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompcoderule.MonCompCodeRuleDO;
import cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncompcoderule.MonCompCodeRuleService;

@Tag(name = "管理后台 - 监测部件标识码规则")
@RestController
@RequestMapping("/datacenter/mon-comp-code-rule")
@Validated
public class MonCompCodeRuleController {

    @Resource
    private MonCompCodeRuleService monCompCodeRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建监测部件标识码规则")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-code-rule:create')")
    public CommonResult<Long> createMonCompCodeRule(@Valid @RequestBody MonCompCodeRuleSaveReqVO createReqVO) {
        return success(monCompCodeRuleService.createMonCompCodeRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测部件标识码规则")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-code-rule:update')")
    public CommonResult<Boolean> updateMonCompCodeRule(@Valid @RequestBody MonCompCodeRuleSaveReqVO updateReqVO) {
        monCompCodeRuleService.updateMonCompCodeRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测部件标识码规则")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-code-rule:delete')")
    public CommonResult<Boolean> deleteMonCompCodeRule(@RequestParam("id") Long id) {
        monCompCodeRuleService.deleteMonCompCodeRule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测部件标识码规则")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-code-rule:query')")
    public CommonResult<MonCompCodeRuleRespVO> getMonCompCodeRule(@RequestParam("id") Long id) {
        MonCompCodeRuleDO monCompCodeRule = monCompCodeRuleService.getMonCompCodeRule(id);
        return success(BeanUtils.toBean(monCompCodeRule, MonCompCodeRuleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测部件标识码规则分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-code-rule:query')")
    public CommonResult<PageResult<MonCompCodeRuleRespVO>> getMonCompCodeRulePage(@Valid MonCompCodeRulePageReqVO pageReqVO) {
        PageResult<MonCompCodeRuleDO> pageResult = monCompCodeRuleService.getMonCompCodeRulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonCompCodeRuleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测部件标识码规则 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-code-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonCompCodeRuleExcel(@Valid MonCompCodeRulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonCompCodeRuleDO> list = monCompCodeRuleService.getMonCompCodeRulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测部件标识码规则.xls", "数据", MonCompCodeRuleRespVO.class,
                        BeanUtils.toBean(list, MonCompCodeRuleRespVO.class));
    }

}