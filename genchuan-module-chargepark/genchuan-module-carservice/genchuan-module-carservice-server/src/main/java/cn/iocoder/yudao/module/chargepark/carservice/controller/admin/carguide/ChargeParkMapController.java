package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.ChargeParkMapChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.ChargeParkMapPageReqVO;
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
    @Parameter(name = "id", description = "充停地图记录 ID", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:charge-park-map:navigate')")
    public CommonResult<String> navigateChargeParkMap(@RequestParam("id") Long id) {
        ChargeParkMapDO record = chargeParkMapService.getChargeParkMap(id);
        if (record == null) {
            return success(null);
        }
        String to = URLEncoder.encode(record.getQueryLocation() == null ? "" : record.getQueryLocation(),
                StandardCharsets.UTF_8);
        return success(mapUrlTemplate.replace("{to}", to));
    }

    @GetMapping("/reserve")
    @Operation(summary = "预订 - 跳转预约页面")
    @Parameter(name = "id", description = "充停地图记录 ID", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:charge-park-map:reserve')")
    public CommonResult<String> reserveChargeParkMap(@RequestParam("id") Long id) {
        ChargeParkMapDO record = chargeParkMapService.getChargeParkMap(id);
        if (record == null) {
            return success(null);
        }
        String location = URLEncoder.encode(record.getQueryLocation() == null ? "" : record.getQueryLocation(),
                StandardCharsets.UTF_8);
        return success(reserveUrlTemplate.replace("{queryLocation}", location));
    }

    @GetMapping("/chart")
    @Operation(summary = "充停地图统计图表 - 地图+热力图+卡片")
    @PreAuthorize("@ss.hasPermission('carservice:charge-park-map:query')")
    public CommonResult<ChargeParkMapChartRespVO> getChargeParkMapChart() {
        return success(serviceOpReportService.chartChargeParkMap());
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
