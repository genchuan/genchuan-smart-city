package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.cardconfig.CardConfigService;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import cn.iocoder.yudao.module.stationresource.api.station.StationInfoApi;
import cn.iocoder.yudao.module.stationresource.api.station.dto.StationInfoRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Tag(name = "管理后台 - 卡种配置")
@RestController
@RequestMapping("/marketop/card-config")
public class CardConfigController {

    @Resource
    private CardConfigService cardConfigService;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private StationInfoApi stationInfoApi;

    @GetMapping("/page")
    @Operation(summary = "获得卡种配置分页")
    @PreAuthorize("@ss.hasPermission('marketop:card-config:query')")
    public CommonResult<PageResult<CardConfigRespVO>> getPage(CardConfigPageReqVO reqVO) {
        PageResult<CardConfigDO> pageResult = cardConfigService.getPage(reqVO);
        PageResult<CardConfigRespVO> bean = BeanUtils.toBean(pageResult, CardConfigRespVO.class);
        injectNames(bean.getList());
        return CommonResult.success(bean);
    }

    @GetMapping("/get")
    @Operation(summary = "获得卡种配置详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:card-config:query')")
    public CommonResult<CardConfigRespVO> get(@RequestParam("id") Long id) {
        CardConfigDO cardConfig = cardConfigService.get(id);
        CardConfigRespVO respVO = BeanUtils.toBean(cardConfig, CardConfigRespVO.class);
        injectNames(Collections.singletonList(respVO));
        return CommonResult.success(respVO);
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
    public CommonResult<Boolean> enable(@RequestBody CardConfigEnableReqVO reqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        cardConfigService.enable(reqVO.getId(), userId);
        return CommonResult.success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "停用卡种配置")
    @PreAuthorize("@ss.hasPermission('marketop:card-config:update')")
    public CommonResult<Boolean> disable(@RequestBody CardConfigDisableReqVO reqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        cardConfigService.disable(reqVO.getId(), userId);
        return CommonResult.success(true);
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获取卡种配置精简列表")
    public CommonResult<List<CardConfigSimpleRespVO>> getSimpleList() {
        List<CardConfigDO> list = cardConfigService.getSimpleList();
        return CommonResult.success(BeanUtils.toBean(list, CardConfigSimpleRespVO.class));
    }

    @GetMapping("/chart")
    @Operation(summary = "卡种配置图表统计")
    @PreAuthorize("@ss.hasPermission('marketop:card-config:query')")
    public CommonResult<CardConfigChartRespVO> getChart() {
        return CommonResult.success(cardConfigService.getChart());
    }

    private void injectNames(List<CardConfigRespVO> list) {
        if (list == null || list.isEmpty()) return;
        Set<Long> userIds = new HashSet<>();
        Set<Long> stationIds = new HashSet<>();
        for (var item : list) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                Long id = safeParseLong(item.getCreator());
                if (id != null) userIds.add(id);
            }
            if (item.getAuditorId() != null) {
                userIds.add(item.getAuditorId());
            }
            if (StrUtil.isNotBlank(item.getStationId())) {
                Arrays.stream(item.getStationId().split(","))
                        .filter(StrUtil::isNotBlank).map(String::trim)
                        .map(this::safeParseLong)
                        .filter(Objects::nonNull)
                        .forEach(stationIds::add);
            }
        }
        if (!userIds.isEmpty()) {
            Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);
            for (var item : list) {
                if (StrUtil.isNotBlank(item.getCreator())) {
                    AdminUserRespDTO user = userMap.get(safeParseLong(item.getCreator()));
                    if (user != null) item.setCreatorName(user.getNickname());
                }
                if (item.getAuditorId() != null) {
                    AdminUserRespDTO user = userMap.get(item.getAuditorId());
                    if (user != null) item.setAuditorName(user.getNickname());
                }
            }
        }
        if (!stationIds.isEmpty()) {
            Map<Long, StationInfoRespDTO> stationMap = stationInfoApi.getStationMap(stationIds);
            for (var item : list) {
                if (StrUtil.isNotBlank(item.getStationId())) {
                    String names = Arrays.stream(item.getStationId().split(","))
                            .filter(StrUtil::isNotBlank).map(String::trim)
                            .map(s -> {
                                Long id = safeParseLong(s);
                                if (id == null) return null;
                                StationInfoRespDTO station = stationMap.get(id);
                                return station != null ? station.getName() : null;
                            })
                            .filter(Objects::nonNull)
                            .collect(Collectors.joining(","));
                    item.setStationName(names);
                }
            }
        }
    }

    private Long safeParseLong(String s) {
        if (s == null) return null;
        try {
            return Long.valueOf(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
