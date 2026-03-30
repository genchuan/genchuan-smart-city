package cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.timeaccessrule;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.timeaccessrule.vo.TimeAccessRulePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.timeaccessrule.vo.TimeAccessRuleRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.timeaccessrule.vo.TimeAccessRuleSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.timeaccessrule.TimeAccessRuleDO;
import cn.iocoder.yudao.module.evaluate.service.timeaccessrule.TimeAccessRuleService;
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

@Tag(name = "管理后台 - 实时接入规则")
@RestController
@RequestMapping("/evaluate/time-access-rule")
@Validated
public class TimeAccessRuleController {

    @Resource
    private TimeAccessRuleService timeAccessRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建实时接入规则")
    @PreAuthorize("@ss.hasPermission('evaluate:time-access-rule:create')")
    public CommonResult<Long> createTimeAccessRule(@Valid @RequestBody TimeAccessRuleSaveReqVO createReqVO) {
        return success(timeAccessRuleService.createTimeAccessRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新实时接入规则")
    @PreAuthorize("@ss.hasPermission('evaluate:time-access-rule:update')")
    public CommonResult<Boolean> updateTimeAccessRule(@Valid @RequestBody TimeAccessRuleSaveReqVO updateReqVO) {
        timeAccessRuleService.updateTimeAccessRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除实时接入规则")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:time-access-rule:delete')")
    public CommonResult<Boolean> deleteTimeAccessRule(@RequestParam("id") Long id) {
        timeAccessRuleService.deleteTimeAccessRule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得实时接入规则")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:time-access-rule:query')")
    public CommonResult<TimeAccessRuleRespVO> getTimeAccessRule(@RequestParam("id") Long id) {
        TimeAccessRuleDO timeAccessRule = timeAccessRuleService.getTimeAccessRule(id);
        return success(BeanUtils.toBean(timeAccessRule, TimeAccessRuleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得实时接入规则分页")
    @PreAuthorize("@ss.hasPermission('evaluate:time-access-rule:query')")
    public CommonResult<PageResult<TimeAccessRuleRespVO>> getTimeAccessRulePage(@Valid TimeAccessRulePageReqVO pageReqVO) {
        PageResult<TimeAccessRuleDO> pageResult = timeAccessRuleService.getTimeAccessRulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TimeAccessRuleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出实时接入规则 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:time-access-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTimeAccessRuleExcel(@Valid TimeAccessRulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TimeAccessRuleDO> list = timeAccessRuleService.getTimeAccessRulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "实时接入规则.xls", "数据", TimeAccessRuleRespVO.class,
                        BeanUtils.toBean(list, TimeAccessRuleRespVO.class));
    }

}