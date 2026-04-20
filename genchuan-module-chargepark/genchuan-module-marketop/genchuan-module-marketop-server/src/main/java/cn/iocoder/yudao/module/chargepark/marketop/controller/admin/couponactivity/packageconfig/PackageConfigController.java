package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.PackageConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.packageconfig.PackageConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "管理后台 - 券包配置")
@RestController
@RequestMapping("/marketop/package-config")
public class PackageConfigController {

    @Resource
    private PackageConfigService packageConfigService;

    @GetMapping("/page")
    @Operation(summary = "获得券包配置分页")
    @PreAuthorize("@ss.hasPermission('marketop:package-config:query')")
    public CommonResult<PageResult<PackageConfigRespVO>> getPage(PackageConfigPageReqVO reqVO) {
        PageResult<PackageConfigDO> pageResult = packageConfigService.getPage(reqVO);
        return CommonResult.success(BeanUtils.toBean(pageResult, PackageConfigRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得券包配置详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:package-config:query')")
    public CommonResult<PackageConfigRespVO> get(@RequestParam("id") Long id) {
        PackageConfigDO packageConfig = packageConfigService.get(id);
        return CommonResult.success(BeanUtils.toBean(packageConfig, PackageConfigRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建券包配置")
    @PreAuthorize("@ss.hasPermission('marketop:package-config:create')")
    public CommonResult<Long> create(@Valid @RequestBody PackageConfigCreateReqVO reqVO) {
        return CommonResult.success(packageConfigService.create(reqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新券包配置")
    @PreAuthorize("@ss.hasPermission('marketop:package-config:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody PackageConfigUpdateReqVO reqVO) {
        packageConfigService.update(reqVO);
        return CommonResult.success(true);
    }

    @PutMapping("/enable")
    @Operation(summary = "生效券包配置")
    @PreAuthorize("@ss.hasPermission('marketop:package-config:update')")
    public CommonResult<Boolean> enable(@RequestParam("id") Long id) {
        packageConfigService.enable(id);
        return CommonResult.success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "停用券包配置")
    @PreAuthorize("@ss.hasPermission('marketop:package-config:update')")
    public CommonResult<Boolean> disable(@RequestParam("id") Long id) {
        packageConfigService.disable(id);
        return CommonResult.success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "券包配置图表统计")
    @PreAuthorize("@ss.hasPermission('marketop:package-config:query')")
    public CommonResult<PackageConfigChartRespVO> getChart(@RequestParam(value = "timeRange", required = false) String timeRange) {
        return CommonResult.success(packageConfigService.getChart(timeRange));
    }

}
