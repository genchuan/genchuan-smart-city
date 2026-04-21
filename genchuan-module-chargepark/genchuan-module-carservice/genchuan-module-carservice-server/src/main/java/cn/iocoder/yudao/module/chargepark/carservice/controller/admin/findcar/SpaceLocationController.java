package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.SpaceLocationChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.SpaceLocationNavigateReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.SpaceLocationPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.SpaceLocationRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.findcar.SpaceLocationDO;
import cn.iocoder.yudao.module.chargepark.carservice.framework.utils.UserNameInjector;
import cn.iocoder.yudao.module.chargepark.carservice.service.decision.ServiceOpReportService;
import cn.iocoder.yudao.module.chargepark.carservice.service.findcar.SpaceLocationService;
import cn.iocoder.yudao.module.inspectop.api.space.SpaceMonitorApi;
import cn.iocoder.yudao.module.inspectop.api.space.dto.SpaceMonitorRespDTO;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Slf4j
@Tag(name = "反向寻车 - 车位定位")
@RestController
@RequestMapping("/carservice/space-location")
@Validated
public class SpaceLocationController {

    @Resource
    private SpaceLocationService spaceLocationService;

    @Resource
    private ServiceOpReportService serviceOpReportService;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private SpaceMonitorApi spaceMonitorApi;

    @Value("${carservice.navigate.map-url-template}")
    private String mapUrlTemplate;

    @GetMapping("/page")
    @Operation(summary = "搜索/筛选 车位定位")
    @PreAuthorize("@ss.hasPermission('carservice:space-location:query')")
    public CommonResult<PageResult<SpaceLocationRespVO>> getSpaceLocationPage(@Valid SpaceLocationPageReqVO pageReqVO) {
        PageResult<SpaceLocationDO> pageResult = spaceLocationService.getSpaceLocationPage(pageReqVO);
        PageResult<SpaceLocationRespVO> respPage = BeanUtils.toBean(pageResult, SpaceLocationRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/get")
    @Operation(summary = "详情 - 车位定位")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('carservice:space-location:query')")
    public CommonResult<SpaceLocationRespVO> getSpaceLocation(@RequestParam("id") Long id) {
        SpaceLocationDO spaceLocation = spaceLocationService.getSpaceLocation(id);
        SpaceLocationRespVO respVO = BeanUtils.toBean(spaceLocation, SpaceLocationRespVO.class);
        if (respVO != null) {
            injectUserNames(List.of(respVO));
        }
        return success(respVO);
    }

    @GetMapping("/navigate")
    @Operation(summary = "导航 - 跳转到车位位置")
    @PreAuthorize("@ss.hasPermission('carservice:space-location:navigate')")
    public CommonResult<String> navigateSpaceLocation(@Valid SpaceLocationNavigateReqVO reqVO) {
        SpaceLocationDO record = spaceLocationService.getSpaceLocation(reqVO.getId());
        if (record == null) {
            return success(null);
        }
        // 按 spaceId 从 inspectop 模块 space_monitor 拿真实车位坐标;下游异常或拿不到就 null,不拼半成品 URL
        String to = null;
        try {
            CommonResult<SpaceMonitorRespDTO> result = spaceMonitorApi.getLatestBySpaceId(reqVO.getSpaceId());
            SpaceMonitorRespDTO space = result == null ? null : result.getData();
            if (space != null && space.getLongitude() != null && space.getLatitude() != null) {
                to = space.getLongitude().toPlainString() + "," + space.getLatitude().toPlainString();
            }
        } catch (Exception ex) {
            log.warn("[navigate] inspectop RPC 失败 spaceId={}", reqVO.getSpaceId(), ex);
        }
        if (to == null) {
            return success(null);
        }
        return success(mapUrlTemplate.replace("{to}", to));
    }

    @GetMapping("/chart")
    @Operation(summary = "车位定位统计图表 - 地图+卡片")
    @PreAuthorize("@ss.hasPermission('carservice:space-location:query')")
    public CommonResult<SpaceLocationChartRespVO> getSpaceLocationChart(@RequestParam(value = "startTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(value = "endTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        return success(serviceOpReportService.chartSpaceLocation(startTime, endTime));
    }

    private void injectUserNames(List<SpaceLocationRespVO> list) {
        UserNameInjector.inject(list, adminUserApi,
                UserNameInjector.field(SpaceLocationRespVO::getUserId, SpaceLocationRespVO::setUserName));
    }

}
