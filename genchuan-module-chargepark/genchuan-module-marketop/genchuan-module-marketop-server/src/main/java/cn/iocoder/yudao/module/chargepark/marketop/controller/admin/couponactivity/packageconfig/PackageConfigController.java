package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.CouponMgmtDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.PackageConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.couponmgmt.CouponMgmtService;
import cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.packageconfig.PackageConfigService;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Tag(name = "管理后台 - 券包配置")
@RestController
@RequestMapping("/marketop/package-config")
public class PackageConfigController {

    @Resource
    private PackageConfigService packageConfigService;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private CouponMgmtService couponMgmtService;

    @GetMapping("/page")
    @Operation(summary = "获得券包配置分页")
    @PreAuthorize("@ss.hasPermission('marketop:package-config:query')")
    public CommonResult<PageResult<PackageConfigRespVO>> getPage(PackageConfigPageReqVO reqVO) {
        PageResult<PackageConfigDO> pageResult = packageConfigService.getPage(reqVO);
        PageResult<PackageConfigRespVO> bean = BeanUtils.toBean(pageResult, PackageConfigRespVO.class);
        injectUserNames(bean.getList());
        return CommonResult.success(bean);
    }

    @GetMapping("/get")
    @Operation(summary = "获得券包配置详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:package-config:query')")
    public CommonResult<PackageConfigRespVO> get(@RequestParam("id") Long id) {
        PackageConfigDO packageConfig = packageConfigService.get(id);
        PackageConfigRespVO respVO = BeanUtils.toBean(packageConfig, PackageConfigRespVO.class);
        injectUserNames(Collections.singletonList(respVO));
        return CommonResult.success(respVO);
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

    @PutMapping("/activate")
    @Operation(summary = "生效券包配置")
    @PreAuthorize("@ss.hasPermission('marketop:package-config:update')")
    public CommonResult<Boolean> enable(@Valid @RequestBody IdReq req) {
        packageConfigService.enable(req.getId());
        return CommonResult.success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "停用券包配置")
    @PreAuthorize("@ss.hasPermission('marketop:package-config:update')")
    public CommonResult<Boolean> disable(@Valid @RequestBody IdReq req) {
        packageConfigService.disable(req.getId());
        return CommonResult.success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "券包配置图表统计")
    @PreAuthorize("@ss.hasPermission('marketop:package-config:query')")
    public CommonResult<PackageConfigChartRespVO> getChart() {
        return CommonResult.success(packageConfigService.getChart());
    }

    @Data
    public static class IdReq {
        @NotNull(message = "id不能为空")
        private Long id;
    }

    private void injectUserNames(List<PackageConfigRespVO> list) {
        if (list == null || list.isEmpty()) return;
        Set<Long> userIds = new HashSet<>();
        Set<Long> couponIds = new HashSet<>();
        for (var item : list) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                userIds.add(Long.valueOf(item.getCreator()));
            }
            if (item.getAuditorId() != null) {
                userIds.add(item.getAuditorId());
            }
            if (StrUtil.isNotBlank(item.getCouponIds())) {
                Arrays.stream(item.getCouponIds().split(","))
                        .filter(StrUtil::isNotBlank).map(String::trim).map(Long::valueOf)
                        .forEach(couponIds::add);
            }
        }
        // 翻译用户名称
        if (!userIds.isEmpty()) {
            Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);
            for (var item : list) {
                if (StrUtil.isNotBlank(item.getCreator())) {
                    AdminUserRespDTO user = userMap.get(Long.valueOf(item.getCreator()));
                    if (user != null) item.setCreatorName(user.getNickname());
                }
                if (item.getAuditorId() != null) {
                    AdminUserRespDTO user = userMap.get(item.getAuditorId());
                    if (user != null) item.setAuditorName(user.getNickname());
                }
            }
        }
        // 翻译优惠券名称
        if (!couponIds.isEmpty()) {
            Map<Long, String> couponNameMap = new HashMap<>();
            for (Long couponId : couponIds) {
                CouponMgmtDO coupon = couponMgmtService.get(couponId);
                if (coupon != null) couponNameMap.put(couponId, coupon.getName());
            }
            for (var item : list) {
                if (StrUtil.isNotBlank(item.getCouponIds())) {
                    String names = Arrays.stream(item.getCouponIds().split(","))
                            .filter(StrUtil::isNotBlank).map(String::trim)
                            .map(id -> couponNameMap.get(Long.valueOf(id)))
                            .filter(Objects::nonNull)
                            .collect(Collectors.joining(","));
                    item.setCouponNames(names);
                }
            }
        }
    }

}
