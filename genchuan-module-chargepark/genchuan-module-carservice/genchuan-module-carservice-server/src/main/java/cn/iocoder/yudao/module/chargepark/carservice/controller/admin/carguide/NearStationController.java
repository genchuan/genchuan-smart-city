package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.NearStationDO;
import cn.iocoder.yudao.module.chargepark.carservice.framework.utils.UserNameInjector;
import cn.iocoder.yudao.module.chargepark.carservice.service.carguide.NearStationService;
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

    @GetMapping("/navigate")
    @Operation(summary = "导航 - 跳转到周边场站位置")
    @Parameter(name = "id", description = "周边场站记录 ID", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:near-station:navigate')")
    public CommonResult<String> navigateNearStation(@RequestParam("id") Long id) {
        NearStationDO record = nearStationService.getNearStation(id);
        if (record == null) {
            return success(null);
        }
        String to = URLEncoder.encode(record.getQueryLocation() == null ? "" : record.getQueryLocation(),
                StandardCharsets.UTF_8);
        return success(mapUrlTemplate.replace("{to}", to));
    }

    @GetMapping("/reserve")
    @Operation(summary = "预订 - 跳转预约页面")
    @Parameter(name = "id", description = "周边场站记录 ID", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:near-station:reserve')")
    public CommonResult<String> reserveNearStation(@RequestParam("id") Long id) {
        NearStationDO record = nearStationService.getNearStation(id);
        if (record == null) {
            return success(null);
        }
        String location = URLEncoder.encode(record.getQueryLocation() == null ? "" : record.getQueryLocation(),
                StandardCharsets.UTF_8);
        return success(reserveUrlTemplate.replace("{queryLocation}", location));
    }

    @GetMapping("/chart")
    @Operation(summary = "周边场站统计图表 - 地图+柱状图+卡片")
    @PreAuthorize("@ss.hasPermission('carservice:near-station:query')")
    public CommonResult<NearStationChartRespVO> getNearStationChart() {
        return success(serviceOpReportService.chartNearStation());
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
