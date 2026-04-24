package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.ActivityConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.activityconfig.ActivityConfigService;
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

@Tag(name = "管理后台 - 活动配置")
@RestController
@RequestMapping("/marketop/activity-config")
public class ActivityConfigController {

    @Resource
    private ActivityConfigService activityConfigService;

    @Resource
    private AdminUserApi adminUserApi;

    @GetMapping("/page")
    @Operation(summary = "获得活动配置分页")
    @PreAuthorize("@ss.hasPermission('marketop:activity-config:query')")
    public CommonResult<PageResult<ActivityConfigRespVO>> getPage(ActivityConfigPageReqVO reqVO) {
        PageResult<ActivityConfigDO> pageResult = activityConfigService.getPage(reqVO);
        PageResult<ActivityConfigRespVO> bean = BeanUtils.toBean(pageResult, ActivityConfigRespVO.class);
        injectUserNames(bean.getList());
        return CommonResult.success(bean);
    }

    @GetMapping("/get")
    @Operation(summary = "获得活动配置详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:activity-config:query')")
    public CommonResult<ActivityConfigRespVO> get(@RequestParam("id") Long id) {
        ActivityConfigDO activityConfig = activityConfigService.get(id);
        ActivityConfigRespVO respVO = BeanUtils.toBean(activityConfig, ActivityConfigRespVO.class);
        injectUserNames(Collections.singletonList(respVO));
        return CommonResult.success(respVO);
    }

    @PostMapping("/create")
    @Operation(summary = "创建活动配置")
    @PreAuthorize("@ss.hasPermission('marketop:activity-config:create')")
    public CommonResult<Long> create(@Valid @RequestBody ActivityConfigCreateReqVO reqVO) {
        return CommonResult.success(activityConfigService.create(reqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新活动配置")
    @PreAuthorize("@ss.hasPermission('marketop:activity-config:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody ActivityConfigUpdateReqVO reqVO) {
        activityConfigService.update(reqVO);
        return CommonResult.success(true);
    }

    @PutMapping("/activate")
    @Operation(summary = "生效活动配置")
    @PreAuthorize("@ss.hasPermission('marketop:activity-config:update')")
    public CommonResult<Boolean> enable(@Valid @RequestBody IdReq req) {
        activityConfigService.enable(req.getId());
        return CommonResult.success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "停用活动配置")
    @PreAuthorize("@ss.hasPermission('marketop:activity-config:update')")
    public CommonResult<Boolean> disable(@Valid @RequestBody IdReq req) {
        activityConfigService.disable(req.getId());
        return CommonResult.success(true);
    }

    @Data
    public static class IdReq {
        @NotNull(message = "id不能为空")
        private Long id;
    }

    @GetMapping("/chart")
    @Operation(summary = "活动配置图表统计")
    @PreAuthorize("@ss.hasPermission('marketop:activity-config:query')")
    public CommonResult<ActivityConfigChartRespVO> getChart() {
        return CommonResult.success(activityConfigService.getChart());
    }

    private void injectUserNames(List<ActivityConfigRespVO> list) {
        // 收集所有需要查询的用户ID
        Set<Long> userIds = new HashSet<>();
        for (var item : list) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                userIds.add(Long.valueOf(item.getCreator()));
            }
            if (item.getAuditorId() != null) {
                userIds.add(item.getAuditorId());
            }
        }
        if (userIds.isEmpty()) return;
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

}
