package cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcoderule;

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcoderule.vo.MonEvtCodeRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcoderule.vo.MonEvtCodeRuleRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcoderule.vo.MonEvtCodeRuleSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcoderule.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtcoderule.MonEvtCodeRuleDO;
import cn.iocoder.yudao.module.datacenter.service.monevtcfg.monevtcoderule.MonEvtCodeRuleService;

@Tag(name = "管理后台 - 监测事件标识码规则")
@RestController
@RequestMapping("/datacenter/mon-evt-code-rule")
@Validated
public class MonEvtCodeRuleController {

    @Resource
    private MonEvtCodeRuleService monEvtCodeRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建监测事件标识码规则")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-code-rule:create')")
    public CommonResult<Long> createMonEvtCodeRule(@Valid @RequestBody MonEvtCodeRuleSaveReqVO createReqVO) {
        return success(monEvtCodeRuleService.createMonEvtCodeRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测事件标识码规则")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-code-rule:update')")
    public CommonResult<Boolean> updateMonEvtCodeRule(@Valid @RequestBody MonEvtCodeRuleSaveReqVO updateReqVO) {
        monEvtCodeRuleService.updateMonEvtCodeRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测事件标识码规则")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-code-rule:delete')")
    public CommonResult<Boolean> deleteMonEvtCodeRule(@RequestParam("id") Long id) {
        monEvtCodeRuleService.deleteMonEvtCodeRule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测事件标识码规则")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-code-rule:query')")
    public CommonResult<MonEvtCodeRuleRespVO> getMonEvtCodeRule(@RequestParam("id") Long id) {
        MonEvtCodeRuleDO monEvtCodeRule = monEvtCodeRuleService.getMonEvtCodeRule(id);
        return success(BeanUtils.toBean(monEvtCodeRule, MonEvtCodeRuleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测事件标识码规则分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-code-rule:query')")
    public CommonResult<PageResult<MonEvtCodeRuleRespVO>> getMonEvtCodeRulePage(@Valid MonEvtCodeRulePageReqVO pageReqVO) {
        PageResult<MonEvtCodeRuleDO> pageResult = monEvtCodeRuleService.getMonEvtCodeRulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonEvtCodeRuleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测事件标识码规则 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-code-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonEvtCodeRuleExcel(@Valid MonEvtCodeRulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonEvtCodeRuleDO> list = monEvtCodeRuleService.getMonEvtCodeRulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测事件标识码规则.xls", "数据", MonEvtCodeRuleRespVO.class,
                        BeanUtils.toBean(list, MonEvtCodeRuleRespVO.class));
    }

}