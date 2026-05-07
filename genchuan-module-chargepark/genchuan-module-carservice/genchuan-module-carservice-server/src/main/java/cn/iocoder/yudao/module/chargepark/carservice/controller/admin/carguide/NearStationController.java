package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationNavigateReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationNearbyListRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationReserveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationResultRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.NearStationDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.NearStationResultDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide.NearStationResultMapper;
import cn.iocoder.yudao.module.chargepark.carservice.framework.utils.UserNameInjector;
import cn.iocoder.yudao.module.chargepark.carservice.service.carguide.NearStationService;
import cn.iocoder.yudao.module.chargepark.carservice.service.decision.ServiceOpReportService;
import cn.iocoder.yudao.module.stationresource.api.station.StationInfoApi;
import cn.iocoder.yudao.module.stationresource.api.station.dto.StationInfoRespDTO;
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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Slf4j
@Tag(name = "车辆引导 - 周边场站")
@RestController
@RequestMapping("/carservice/near-station")
@Validated
public class NearStationController {

    @Resource
    private NearStationService nearStationService;

    @Resource
    private ServiceOpReportService serviceOpReportService;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private StationInfoApi stationInfoApi;

    @Resource
    private NearStationResultMapper nearStationResultMapper;

    @Value("${carservice.navigate.map-url-template}")
    private String mapUrlTemplate;

    @Value("${carservice.navigate.reserve-url-template}")
    private String reserveUrlTemplate;

    @GetMapping("/page")
    @Operation(summary = "筛选/刷新 周边场站查询")
    @PreAuthorize("@ss.hasPermission('carservice:near-station:query')")
    public CommonResult<PageResult<NearStationRespVO>> getNearStationPage(@Valid NearStationPageReqVO pageReqVO) {
        PageResult<NearStationDO> pageResult = nearStationService.getNearStationPage(pageReqVO);
        PageResult<NearStationRespVO> respPage = BeanUtils.toBean(pageResult, NearStationRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/get")
    @Operation(summary = "详情 - 周边场站查询")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('carservice:near-station:query')")
    public CommonResult<NearStationRespVO> getNearStation(@RequestParam("id") Long id) {
        NearStationDO nearStation = nearStationService.getNearStation(id);
        NearStationRespVO respVO = BeanUtils.toBean(nearStation, NearStationRespVO.class);
        if (respVO != null) {
            injectUserNames(List.of(respVO));
        }
        return success(respVO);
    }

    @GetMapping("/nearby-stations")
    @Operation(summary = "按查询记录 id 列出附近场站（用作导航/预订的场站选择源）")
    @Parameter(name = "id", description = "near_station 查询记录 ID", required = true, example = "21")
    @Parameter(name = "radiusKm", description = "半径(km),不传默认 5", example = "5")
    @PreAuthorize("@ss.hasPermission('carservice:near-station:query')")
    public CommonResult<NearStationNearbyListRespVO> listNearbyStations(
            @RequestParam("id") Long id,
            @RequestParam(value = "radiusKm", required = false) Double radiusKm) {
        return success(nearStationService.listNearbyStations(id, radiusKm));
    }

    @GetMapping("/navigate")
    @Operation(summary = "导航 - 跳转到周边场站位置")
    @PreAuthorize("@ss.hasPermission('carservice:near-station:navigate')")
    public CommonResult<String> navigateNearStation(@Valid NearStationNavigateReqVO reqVO) {
        NearStationDO record = nearStationService.getNearStation(reqVO.getId());
        if (record == null) {
            return success(null);
        }
        // 按 stationId 从 stationresource 拿真实场站坐标;下游异常或拿不到就 null,不构造半成品 URL
        String to = resolveCoord(reqVO.getStationId(), record.getQueryLocation());
        if (to == null) {
            return success(null);
        }
        return success(mapUrlTemplate.replace("{to}", to));
    }

    /** 返回可直接拼到地图 URL 的 "lon,lat",拿不到返回 null */
    private String resolveCoord(Long stationId, String queryLocation) {
        try {
            CommonResult<StationInfoRespDTO> r = stationInfoApi.getStation(stationId);
            StationInfoRespDTO station = r == null ? null : r.getData();
            if (station != null && station.getLon() != null && station.getLat() != null) {
                return station.getLon().toPlainString() + "," + station.getLat().toPlainString();
            }
        } catch (Exception ex) {
            log.warn("[navigate] stationresource RPC 失败 stationId={}", stationId, ex);
        }
        if (queryLocation != null) {
            String[] parts = queryLocation.split(",");
            if (parts.length == 2) {
                try {
                    Double.parseDouble(parts[0].trim());
                    Double.parseDouble(parts[1].trim());
                    return queryLocation;
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return null;
    }

    @GetMapping("/reserve")
    @Operation(summary = "预订 - 跳转预约页面")
    @PreAuthorize("@ss.hasPermission('carservice:near-station:reserve')")
    public CommonResult<String> reserveNearStation(@Valid NearStationReserveReqVO reqVO) {
        // 校验记录存在（审计 + 防越权）
        NearStationDO record = nearStationService.getNearStation(reqVO.getId());
        if (record == null) {
            return success(null);
        }
        // 按文档要求拼 URL：?stationId=xxx
        String params = "stationId=" + reqVO.getStationId();
        return success(reserveUrlTemplate.replace("{params}", params));
    }

    @GetMapping("/chart")
    @Operation(summary = "周边场站统计图表 - 地图+柱状图+卡片")
    @PreAuthorize("@ss.hasPermission('carservice:near-station:query')")
    public CommonResult<NearStationChartRespVO> getNearStationChart(
            @RequestParam(value = "startTime", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(value = "endTime", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime,
            @RequestParam(value = "lon", required = false) Double lon,
            @RequestParam(value = "lat", required = false) Double lat) {
        return success(serviceOpReportService.chartNearStation(startTime, endTime, lon, lat));
    }

    @GetMapping("/result")
    @Operation(summary = "查询某次记录返回的场站明细列表(快照,关联 near_station_result)")
    @Parameter(name = "nearStationId", description = "near_station.id", required = true, example = "47")
    @Parameter(name = "onlyHasEmpty", description = "是否仅返回有空位的场站,默认 false")
    @PreAuthorize("@ss.hasPermission('carservice:near-station:query')")
    public CommonResult<List<NearStationResultRespVO>> getNearStationResult(
            @RequestParam("nearStationId") Long nearStationId,
            @RequestParam(value = "onlyHasEmpty", required = false, defaultValue = "false") Boolean onlyHasEmpty) {
        List<NearStationResultDO> list = nearStationResultMapper.selectByNearStationId(nearStationId);
        if (Boolean.TRUE.equals(onlyHasEmpty)) {
            list = list.stream().filter(r -> Boolean.TRUE.equals(r.getHasEmpty())).collect(java.util.stream.Collectors.toList());
        }
        return success(BeanUtils.toBean(list, NearStationResultRespVO.class));
    }

    @GetMapping("/chart-drill-bar")
    @Operation(summary = "各距离区间场站统计(柱状图钻取) - 同 page 接口")
    @PreAuthorize("@ss.hasPermission('carservice:near-station:query')")
    public CommonResult<PageResult<NearStationRespVO>> drillNearStationBar(@Valid NearStationPageReqVO pageReqVO) {
        PageResult<NearStationDO> pageResult = nearStationService.getNearStationPage(pageReqVO);
        PageResult<NearStationRespVO> respPage = BeanUtils.toBean(pageResult, NearStationRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    private void injectUserNames(List<NearStationRespVO> list) {
        UserNameInjector.inject(list, adminUserApi,
                UserNameInjector.field(NearStationRespVO::getUserId, NearStationRespVO::setUserName));
    }

}
