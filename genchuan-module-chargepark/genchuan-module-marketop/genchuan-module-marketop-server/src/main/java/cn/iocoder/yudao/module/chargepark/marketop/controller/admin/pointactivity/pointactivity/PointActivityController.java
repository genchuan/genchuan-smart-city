package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityExportExcelVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityImportExcelVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointActivityDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.pointactivity.PointActivityService;
import cn.iocoder.yudao.module.stationresource.api.station.StationInfoApi;
import cn.iocoder.yudao.module.stationresource.api.station.dto.StationInfoRespDTO;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Tag(name = "管理后台 - 积分活动列表")
@RestController
@RequestMapping("/marketop/point-activity")
public class PointActivityController {

    @Resource
    private PointActivityService pointActivityService;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private StationInfoApi stationInfoApi;

@GetMapping("/page")
    @Operation(summary = "获得积分活动分页")
    @PreAuthorize("@ss.hasPermission('marketop:point-activity:query')")
    public CommonResult<PageResult<PointActivityRespVO>> getPage(PointActivityPageReqVO reqVO) {
        // 如果没有传startTime和endTime，但传了date，则用date转换
        if (reqVO.getStartTime() == null && reqVO.getEndTime() == null && StrUtil.isNotBlank(reqVO.getDate())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(reqVO.getDate(), formatter);
            LocalDateTime startDateTime = localDate.atStartOfDay();
            LocalDateTime endDateTime = localDate.atTime(LocalTime.MAX);
            reqVO.setStartTime(startDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            reqVO.setEndTime(endDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        }
        PageResult<PointActivityDO> pageResult = pointActivityService.getPage(reqVO);
        PageResult<PointActivityRespVO> bean = BeanUtils.toBean(pageResult, PointActivityRespVO.class);
        injectUserNames(bean.getList());
        return CommonResult.success(bean);
    }

    @GetMapping("/get")
    @Operation(summary = "获得积分活动详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:point-activity:query')")
    public CommonResult<PointActivityRespVO> get(@RequestParam("id") Long id) {
        PointActivityDO pointActivity = pointActivityService.get(id);
        PointActivityRespVO respVO = BeanUtils.toBean(pointActivity, PointActivityRespVO.class);
        injectUserNames(Collections.singletonList(respVO));
        return CommonResult.success(respVO);
    }

    @PostMapping("/create")
    @Operation(summary = "创建积分活动")
    @PreAuthorize("@ss.hasPermission('marketop:point-activity:create')")
    public CommonResult<Long> create(@Valid @RequestBody PointActivityCreateReqVO reqVO) {
        return CommonResult.success(pointActivityService.create(reqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新积分活动")
    @PreAuthorize("@ss.hasPermission('marketop:point-activity:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody PointActivityUpdateReqVO reqVO) {
        pointActivityService.update(reqVO);
        return CommonResult.success(true);
    }

    @PutMapping("/activate")
    @Operation(summary = "生效积分活动")
    @PreAuthorize("@ss.hasPermission('marketop:point-activity:activate')")
    public CommonResult<Boolean> activate(@RequestParam("id") Long id) {
        pointActivityService.activate(id);
        return CommonResult.success(true);
    }

    @PutMapping("/enable")
    @Operation(summary = "生效积分活动")
    @PreAuthorize("@ss.hasPermission('marketop:point-activity:update')")
    public CommonResult<Boolean> enable(@RequestParam("id") Long id) {
        pointActivityService.enable(id);
        return CommonResult.success(true);
    }

    @PutMapping("/pause")
    @Operation(summary = "暂停积分活动")
    @PreAuthorize("@ss.hasPermission('marketop:point-activity:update')")
    public CommonResult<Boolean> pause(@RequestParam("id") Long id) {
        pointActivityService.pause(id);
        return CommonResult.success(true);
    }

    @GetMapping("/get-import-template")
    @Operation(summary = "获得导入积分活动模板")
    public void importTemplate(HttpServletResponse response) throws IOException {
        List<PointActivityImportExcelVO> list = Arrays.asList(
                PointActivityImportExcelVO.builder().name("新用户注册赠分").type("1").startTime("2024-01-01 00:00:00").endTime("2024-12-31 23:59:59").rule("注册即送100积分").description("新年活动").stationIds("1,2").build(),
                PointActivityImportExcelVO.builder().name("消费返积分").type("2").startTime("2024-01-01 00:00:00").endTime("2024-06-30 23:59:59").rule("消费1元返1积分").description("消费返积分活动").stationIds("1").build()
        );
        ExcelUtils.write(response, "积分活动导入模板.xls", "积分活动列表", PointActivityImportExcelVO.class, list);
    }

    @PostMapping("/import")
    @Operation(summary = "导入积分活动")
    @PreAuthorize("@ss.hasPermission('marketop:point-activity:import')")
    public CommonResult<Boolean> importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        List<PointActivityImportExcelVO> list = ExcelUtils.read(file, PointActivityImportExcelVO.class);
        pointActivityService.importPointActivityList(list);
        return CommonResult.success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出积分活动")
    @PreAuthorize("@ss.hasPermission('marketop:point-activity:query')")
    public void export(PointActivityPageReqVO reqVO, HttpServletResponse response) throws IOException {
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<PointActivityDO> pageResult = pointActivityService.getPage(reqVO);
        List<PointActivityExportExcelVO> list = BeanUtils.toBean(pageResult.getList(), PointActivityExportExcelVO.class);
        ExcelUtils.write(response, "积分活动.xlsx", "数据", PointActivityExportExcelVO.class, list);
    }

    @GetMapping("/chart")
    @Operation(summary = "积分活动图表统计")
    @PreAuthorize("@ss.hasPermission('marketop:point-activity:query')")
    public CommonResult<PointActivityChartRespVO> getChart() {
        return CommonResult.success(pointActivityService.getChart());
    }

    private void injectUserNames(List<PointActivityRespVO> list) {
        if (list == null || list.isEmpty()) return;
        // 收集所有需要查询的用户ID
        Set<Long> userIds = new HashSet<>();
        Set<Long> stationIds = new HashSet<>();
        for (var item : list) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                userIds.add(Long.valueOf(item.getCreator()));
            }
            if (item.getAuditorId() != null) {
                userIds.add(item.getAuditorId());
            }
            if (StrUtil.isNotBlank(item.getStationIds())) {
                Arrays.stream(item.getStationIds().split(","))
                        .filter(StrUtil::isNotBlank).map(String::trim).map(Long::valueOf)
                        .forEach(stationIds::add);
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
        // 翻译场站名称
        if (!stationIds.isEmpty()) {
            Map<Long, StationInfoRespDTO> stationMap = stationInfoApi.getStationMap(stationIds);
            for (var item : list) {
                if (StrUtil.isNotBlank(item.getStationIds())) {
                    String names = Arrays.stream(item.getStationIds().split(","))
                            .filter(StrUtil::isNotBlank).map(String::trim)
                            .map(id -> {
                                StationInfoRespDTO s = stationMap.get(Long.valueOf(id));
                                return s != null ? s.getName() : null;
                            })
                            .filter(Objects::nonNull)
                            .collect(Collectors.joining(","));
                    item.setStationNames(names);
                }
            }
        }
    }

}
