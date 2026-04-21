package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigChartReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigIdReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.RuleConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.ruleconfig.RuleConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "管理后台 - 规则配置")
@RestController
@RequestMapping("/marketop/rule-config")
public class RuleConfigController {

    @Resource
    private RuleConfigService ruleConfigService;

    @GetMapping("/page")
    @Operation(summary = "获得规则配置分页")
    @PreAuthorize("@ss.hasPermission('marketop:rule-config:query')")
    public CommonResult<PageResult<RuleConfigRespVO>> getPage(RuleConfigPageReqVO reqVO) {
        PageResult<RuleConfigDO> pageResult = ruleConfigService.getPage(reqVO);
        return CommonResult.success(BeanUtils.toBean(pageResult, RuleConfigRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得规则配置详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:rule-config:query')")
    public CommonResult<RuleConfigRespVO> get(@RequestParam("id") Long id) {
        RuleConfigDO ruleConfig = ruleConfigService.get(id);
        return CommonResult.success(BeanUtils.toBean(ruleConfig, RuleConfigRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建规则配置")
    @PreAuthorize("@ss.hasPermission('marketop:rule-config:create')")
    public CommonResult<Long> create(@Valid @RequestBody RuleConfigCreateReqVO reqVO) {
        return CommonResult.success(ruleConfigService.create(reqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新规则配置")
    @PreAuthorize("@ss.hasPermission('marketop:rule-config:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody RuleConfigUpdateReqVO reqVO) {
        ruleConfigService.update(reqVO);
        return CommonResult.success(true);
    }

    @PutMapping("/activate")
    @Operation(summary = "生效规则配置")
    @PreAuthorize("@ss.hasPermission('marketop:rule-config:update')")
    public CommonResult<Boolean> activate(@Valid @RequestBody RuleConfigIdReqVO  reqVO) {
        Long id = reqVO.getId();
        ruleConfigService.enable(id);
        return CommonResult.success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用规则配置")
    @PreAuthorize("@ss.hasPermission('marketop:rule-config:update')")
    public CommonResult<Boolean> disable(@Valid @RequestBody RuleConfigIdReqVO  reqVO) {
        Long id = reqVO.getId();
        ruleConfigService.disable(id);
        return CommonResult.success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "规则配置图表统计")
    @PreAuthorize("@ss.hasPermission('marketop:rule-config:query')")
    public CommonResult<RuleConfigChartRespVO> getChart(RuleConfigChartReqVO reqVO) {
        return CommonResult.success(ruleConfigService.getChart(reqVO));
    }

}
