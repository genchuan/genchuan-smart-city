package cn.iocoder.yudao.module.evaluate.controller.admin.sys.ruletype;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.ruletype.vo.RuleTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.ruletype.vo.RuleTypeRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.ruletype.vo.RuleTypeSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.sys.ruletype.RuleTypeDO;
import cn.iocoder.yudao.module.evaluate.service.ruletype.RuleTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 规则类型字典")
@RestController
@RequestMapping("/evaluate/rule-type")
@Validated
public class RuleTypeController {

    @Resource
    private RuleTypeService ruleTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建规则类型字典")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-type:create')")
    public CommonResult<Long> createRuleType(@Valid @RequestBody RuleTypeSaveReqVO createReqVO) {
        return success(ruleTypeService.createRuleType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新规则类型字典")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-type:update')")
    public CommonResult<Boolean> updateRuleType(@Valid @RequestBody RuleTypeSaveReqVO updateReqVO) {
        ruleTypeService.updateRuleType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除规则类型字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:rule-type:delete')")
    public CommonResult<Boolean> deleteRuleType(@RequestParam("id") Long id) {
        ruleTypeService.deleteRuleType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得规则类型字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-type:query')")
    public CommonResult<RuleTypeRespVO> getRuleType(@RequestParam("id") Long id) {
        RuleTypeDO ruleType = ruleTypeService.getRuleType(id);
        return success(BeanUtils.toBean(ruleType, RuleTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得规则类型字典分页")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-type:query')")
    public CommonResult<PageResult<RuleTypeRespVO>> getRuleTypePage(@Valid RuleTypePageReqVO pageReqVO) {
        PageResult<RuleTypeDO> pageResult = ruleTypeService.getRuleTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RuleTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出规则类型字典 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRuleTypeExcel(@Valid RuleTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RuleTypeDO> list = ruleTypeService.getRuleTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "规则类型字典.xls", "数据", RuleTypeRespVO.class,
                        BeanUtils.toBean(list, RuleTypeRespVO.class));
    }

}