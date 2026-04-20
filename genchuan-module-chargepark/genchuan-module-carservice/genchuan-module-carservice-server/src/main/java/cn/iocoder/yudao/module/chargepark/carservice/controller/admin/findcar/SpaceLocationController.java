package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.SpaceLocationChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.SpaceLocationPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.SpaceLocationRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.findcar.SpaceLocationDO;
import cn.iocoder.yudao.module.chargepark.carservice.framework.utils.UserNameInjector;
import cn.iocoder.yudao.module.chargepark.carservice.service.decision.ServiceOpReportService;
import cn.iocoder.yudao.module.chargepark.carservice.service.findcar.SpaceLocationService;
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

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

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
    @Parameter(name = "id", description = "车位定位记录 ID", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:space-location:navigate')")
    public CommonResult<String> navigateSpaceLocation(@RequestParam("id") Long id) {
        SpaceLocationDO record = spaceLocationService.getSpaceLocation(id);
        if (record == null) {
            return success(null);
        }
        // 用 plate_no 或 space_id 作为目标定位关键字
        String to = "spaceId:" + (record.getSpaceId() == null ? "" : record.getSpaceId());
        return success(mapUrlTemplate.replace("{to}", to));
    }

    @GetMapping("/chart")
    @Operation(summary = "车位定位统计图表 - 地图+卡片")
    @PreAuthorize("@ss.hasPermission('carservice:space-location:query')")
    public CommonResult<SpaceLocationChartRespVO> getSpaceLocationChart() {
        return success(serviceOpReportService.chartSpaceLocation());
    }

    private void injectUserNames(List<SpaceLocationRespVO> list) {
        UserNameInjector.inject(list, adminUserApi,
                UserNameInjector.field(SpaceLocationRespVO::getUserId, SpaceLocationRespVO::setUserName));
    }

}
