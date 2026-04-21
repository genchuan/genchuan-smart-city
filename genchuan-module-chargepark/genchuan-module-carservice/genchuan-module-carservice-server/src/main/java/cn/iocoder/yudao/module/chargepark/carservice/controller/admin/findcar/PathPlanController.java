package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.PathPlanChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.PathPlanPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.PathPlanRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.findcar.PathPlanDO;
import cn.iocoder.yudao.module.chargepark.carservice.framework.utils.UserNameInjector;
import cn.iocoder.yudao.module.chargepark.carservice.service.decision.ServiceOpReportService;
import cn.iocoder.yudao.module.chargepark.carservice.service.findcar.PathPlanService;
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

@Tag(name = "反向寻车 - 路径规划")
@RestController
@RequestMapping("/carservice/path-plan")
@Validated
public class PathPlanController {

    @Resource
    private PathPlanService pathPlanService;

    @Resource
    private ServiceOpReportService serviceOpReportService;

    @Resource
    private AdminUserApi adminUserApi;

    @Value("${carservice.navigate.map-path-url-template}")
    private String mapUrlTemplate;

    @GetMapping("/page")
    @Operation(summary = "搜索/筛选 路径规划")
    @PreAuthorize("@ss.hasPermission('carservice:path-plan:query')")
    public CommonResult<PageResult<PathPlanRespVO>> getPathPlanPage(@Valid PathPlanPageReqVO pageReqVO) {
        PageResult<PathPlanDO> pageResult = pathPlanService.getPathPlanPage(pageReqVO);
        PageResult<PathPlanRespVO> respPage = BeanUtils.toBean(pageResult, PathPlanRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/get")
    @Operation(summary = "详情 - 路径规划")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('carservice:path-plan:query')")
    public CommonResult<PathPlanRespVO> getPathPlan(@RequestParam("id") Long id) {
        PathPlanDO pathPlan = pathPlanService.getPathPlan(id);
        PathPlanRespVO respVO = BeanUtils.toBean(pathPlan, PathPlanRespVO.class);
        if (respVO != null) {
            injectUserNames(List.of(respVO));
        }
        return success(respVO);
    }

    @GetMapping("/navigate")
    @Operation(summary = "导航 - 按规划路径在地图打开")
    @Parameter(name = "id", description = "路径规划记录 ID", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:path-plan:navigate')")
    public CommonResult<String> navigatePathPlan(@RequestParam("id") Long id) {
        PathPlanDO record = pathPlanService.getPathPlan(id);
        if (record == null) {
            return success(null);
        }
        // 文档要求 URL 格式: ?from=<起点坐标>&to=<终点坐标>
        String from = record.getStartLocation() == null ? "" : record.getStartLocation();
        String to = record.getEndLocation() == null ? "" : record.getEndLocation();
        return success(mapUrlTemplate.replace("{to}", to).replace("{from}", from));
    }

    @GetMapping("/chart")
    @Operation(summary = "路径规划统计图表 - 地图+卡片")
    @PreAuthorize("@ss.hasPermission('carservice:path-plan:query')")
    public CommonResult<PathPlanChartRespVO> getPathPlanChart(@RequestParam(value = "startTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(value = "endTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        return success(serviceOpReportService.chartPathPlan(startTime, endTime));
    }

    private void injectUserNames(List<PathPlanRespVO> list) {
        UserNameInjector.inject(list, adminUserApi,
                UserNameInjector.field(PathPlanRespVO::getUserId, PathPlanRespVO::setUserName));
    }

}
