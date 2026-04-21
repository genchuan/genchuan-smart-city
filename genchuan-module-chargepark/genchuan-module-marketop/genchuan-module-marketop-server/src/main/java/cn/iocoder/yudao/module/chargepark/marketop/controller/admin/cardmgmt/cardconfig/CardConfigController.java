package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.cardconfig.CardConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "管理后台 - 卡种配置")
@RestController
@RequestMapping("/marketop/card-config")
public class CardConfigController {

    @Resource
    private CardConfigService cardConfigService;

    @GetMapping("/page")
    @Operation(summary = "获得卡种配置分页")
    @PreAuthorize("@ss.hasPermission('marketop:card-config:query')")
    public CommonResult<PageResult<CardConfigRespVO>> getPage(CardConfigPageReqVO reqVO) {
        PageResult<CardConfigDO> pageResult = cardConfigService.getPage(reqVO);
        return CommonResult.success(BeanUtils.toBean(pageResult, CardConfigRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得卡种配置详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:card-config:query')")
    public CommonResult<CardConfigRespVO> get(@RequestParam("id") Long id) {
        CardConfigDO cardConfig = cardConfigService.get(id);
        return CommonResult.success(BeanUtils.toBean(cardConfig, CardConfigRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建卡种配置")
    @PreAuthorize("@ss.hasPermission('marketop:card-config:create')")
    public CommonResult<Long> create(@Valid @RequestBody CardConfigCreateReqVO reqVO) {
        return CommonResult.success(cardConfigService.create(reqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新卡种配置")
    @PreAuthorize("@ss.hasPermission('marketop:card-config:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody CardConfigUpdateReqVO reqVO) {
        cardConfigService.update(reqVO);
        return CommonResult.success(true);
    }

    @PutMapping("/enable")
    @Operation(summary = "生效卡种配置")
    @PreAuthorize("@ss.hasPermission('marketop:card-config:update')")
    public CommonResult<Boolean> enable(@RequestParam("id") Long id) {
        cardConfigService.enable(id);
        return CommonResult.success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "停用卡种配置")
    @PreAuthorize("@ss.hasPermission('marketop:card-config:update')")
    public CommonResult<Boolean> disable(@RequestParam("id") Long id) {
        cardConfigService.disable(id);
        return CommonResult.success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "卡种配置图表统计")
    @PreAuthorize("@ss.hasPermission('marketop:card-config:query')")
    public CommonResult<CardConfigChartRespVO> getChart(@RequestParam(value = "timeRange", required = false) String timeRange) {
        return CommonResult.success(cardConfigService.getChart(timeRange));
    }

}
