package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.ChargeParkMapChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.ChargeParkMapNavigateReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.ChargeParkMapPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.ChargeParkMapReserveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.ChargeParkMapRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.ChargeParkMapDO;
import cn.iocoder.yudao.module.chargepark.carservice.framework.utils.UserNameInjector;
import cn.iocoder.yudao.module.chargepark.carservice.service.carguide.ChargeParkMapService;
import cn.iocoder.yudao.module.chargepark.carservice.service.decision.ServiceOpReportService;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "车辆引导 - 充停地图")
@RestController
@RequestMapping("/carservice/charge-park-map")
@Validated
public class ChargeParkMapController {

    @Resource
    private ChargeParkMapService chargeParkMapService;

    @Resource
    private ServiceOpReportService serviceOpReportService;

    @Resource
    private AdminUserApi adminUserApi;

    @Value("${carservice.navigate.map-url-template}")
    private String mapUrlTemplate;

    @Value("${carservice.navigate.reserve-url-template}")
    private String reserveUrlTemplate;

    @GetMapping("/page")
    @Operation(summary = "筛选/刷新 充停地图查询")
    @PreAuthorize("@ss.hasPermission('carservice:charge-park-map:query')")
    public CommonResult<PageResult<ChargeParkMapRespVO>> getChargeParkMapPage(@Valid ChargeParkMapPageReqVO pageReqVO) {
        PageResult<ChargeParkMapDO> pageResult = chargeParkMapService.getChargeParkMapPage(pageReqVO);
        PageResult<ChargeParkMapRespVO> respPage = BeanUtils.toBean(pageResult, ChargeParkMapRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/get")
    @Operation(summary = "详情 - 充停地图查询")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('carservice:charge-park-map:query')")
    public CommonResult<ChargeParkMapRespVO> getChargeParkMap(@RequestParam("id") Long id) {
        ChargeParkMapDO chargeParkMap = chargeParkMapService.getChargeParkMap(id);
        ChargeParkMapRespVO respVO = BeanUtils.toBean(chargeParkMap, ChargeParkMapRespVO.class);
        if (respVO != null) {
            injectUserNames(List.of(respVO));
        }
        return success(respVO);
    }

    @GetMapping("/navigate")
    @Operation(summary = "导航 - 跳转外部地图")
    @PreAuthorize("@ss.hasPermission('carservice:charge-park-map:navigate')")
    public CommonResult<String> navigateChargeParkMap(@Valid ChargeParkMapNavigateReqVO reqVO) {
        ChargeParkMapDO record = chargeParkMapService.getChargeParkMap(reqVO.getId());
        if (record == null) {
            return success(null);
        }
        // TODO 等 stationresource 模块开 RPC 后,按 targetId 查目标场站/车位真实坐标
        // 现阶段暂用 queryLocation 作为导航终点,契约已对齐文档
        String to = record.getQueryLocation() == null ? "" : record.getQueryLocation();
        return success(mapUrlTemplate.replace("{to}", to));
    }

    @GetMapping("/reserve")
    @Operation(summary = "预订 - 跳转预约页面")
    @PreAuthorize("@ss.hasPermission('carservice:charge-park-map:reserve')")
    public CommonResult<String> reserveChargeParkMap(@Valid ChargeParkMapReserveReqVO reqVO) {
        // 校验记录存在（审计 + 防越权访问他人的查询记录）
        ChargeParkMapDO record = chargeParkMapService.getChargeParkMap(reqVO.getId());
        if (record == null) {
            return success(null);
        }
        // 按文档要求拼 URL：?stationId=xxx[&spaceId=yyy]
        StringBuilder params = new StringBuilder();
        if (reqVO.getStationId() != null) {
            params.append("stationId=").append(reqVO.getStationId());
        }
        if (reqVO.getSpaceId() != null) {
            if (params.length() > 0) {
                params.append("&");
            }
            params.append("spaceId=").append(reqVO.getSpaceId());
        }
        return success(reserveUrlTemplate.replace("{params}", params.toString()));
    }

    @GetMapping("/chart")
    @Operation(summary = "充停地图统计图表 - 地图+热力图+卡片")
    @PreAuthorize("@ss.hasPermission('carservice:charge-park-map:query')")
    public CommonResult<ChargeParkMapChartRespVO> getChargeParkMapChart(@RequestParam(value = "startTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(value = "endTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        return success(serviceOpReportService.chartChargeParkMap(startTime, endTime));
    }

    @GetMapping("/chart-drill-heat")
    @Operation(summary = "区域车位使用统计(热力图钻取) - 同 page 接口")
    @PreAuthorize("@ss.hasPermission('carservice:charge-park-map:query')")
    public CommonResult<PageResult<ChargeParkMapRespVO>> drillChargeParkMapHeat(@Valid ChargeParkMapPageReqVO pageReqVO) {
        PageResult<ChargeParkMapDO> pageResult = chargeParkMapService.getChargeParkMapPage(pageReqVO);
        PageResult<ChargeParkMapRespVO> respPage = BeanUtils.toBean(pageResult, ChargeParkMapRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    private void injectUserNames(List<ChargeParkMapRespVO> list) {
        UserNameInjector.inject(list, adminUserApi,
                UserNameInjector.field(ChargeParkMapRespVO::getUserId, ChargeParkMapRespVO::setUserName));
    }

}
