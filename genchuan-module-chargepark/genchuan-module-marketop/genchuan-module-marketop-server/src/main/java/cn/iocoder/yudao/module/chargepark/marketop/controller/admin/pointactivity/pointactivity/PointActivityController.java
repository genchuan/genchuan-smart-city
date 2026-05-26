package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityExportExcelVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityImportExcelVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivitySimpleRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointActivityDO;
import cn.iocoder.yudao.module.chargepark.marketop.enums.PointActivityStatusEnum;
import cn.iocoder.yudao.module.chargepark.marketop.enums.PointActivityTypeEnum;
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
        reqVO.validateTimeRange();
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
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        pointActivityService.activate(id,userId);
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
                PointActivityImportExcelVO.builder().name("新用户注册赠分").type(PointActivityTypeEnum.CONSUME.getLabel()).startTime("2024-01-01 00:00:00").endTime("2024-12-31 23:59:59").rule("注册即送100积分").description("新年活动").stationIds("泉州万达旗舰充电站,仓山万达地下停车场").build(),
                PointActivityImportExcelVO.builder().name("消费返积分").type(PointActivityTypeEnum.INVITE.getLabel()).startTime("2024-01-01 00:00:00").endTime("2024-06-30 23:59:59").rule("消费1元返1积分").description("消费返积分活动").stationIds("泉州万达旗舰充电站").build()
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
        reqVO.validateTimeRange();
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<PointActivityDO> pageResult = pointActivityService.getPage(reqVO);
        List<PointActivityExportExcelVO> list = BeanUtils.toBean(pageResult.getList(), PointActivityExportExcelVO.class);

        // 场站名称翻译
        Set<Long> stationIdSet = new HashSet<>();
        for (var bean : list) {
            if (StrUtil.isNotBlank(bean.getStationIds())) {
                Arrays.stream(bean.getStationIds().split(","))
                        .filter(StrUtil::isNotBlank).map(String::trim)
                        .map(PointActivityController.this::safeParseLong)
                        .filter(Objects::nonNull)
                        .forEach(stationIdSet::add);
            }
        }
        Map<Long, StationInfoRespDTO> stationMap = stationIdSet.isEmpty()
                ? Collections.emptyMap() : stationInfoApi.getStationMap(stationIdSet);
        for (var bean : list) {
            if (StrUtil.isNotBlank(bean.getStationIds())) {
                String names = Arrays.stream(bean.getStationIds().split(","))
                        .filter(StrUtil::isNotBlank).map(String::trim)
                        .map(s -> {
                            Long id = safeParseLong(s);
                            if (id == null) return s;
                            StationInfoRespDTO station = stationMap.get(id);
                            return station != null ? station.getName() : s;
                        })
                        .collect(Collectors.joining(","));
                bean.setStationIds(names);
            }
        }
        ExcelUtils.write(response, "积分活动.xlsx", "数据", PointActivityExportExcelVO.class, list);
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获取积分活动精简列表")
    public CommonResult<List<PointActivitySimpleRespVO>> getSimpleList() {
        List<PointActivityDO> list = pointActivityService.getSimpleList();
        list = list.stream()
                .filter(item -> !PointActivityStatusEnum.ENDED.getValue().equals(item.getStatus()))
                .toList();
        return CommonResult.success(BeanUtils.toBean(list, PointActivitySimpleRespVO.class));
    }

    @GetMapping("/station-simple-list")
    @Operation(summary = "获取场站精简列表")
    public CommonResult<List<Map<String, Object>>> getStationSimpleList() {
        return CommonResult.success(pointActivityService.getStationSimpleList());
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
                Long id = safeParseLong(item.getCreator());
                if (id != null) userIds.add(id);
            }
            if (item.getAuditorId() != null) {
                userIds.add(item.getAuditorId());
            }
            if (StrUtil.isNotBlank(item.getStationIds())) {
                Arrays.stream(item.getStationIds().split(","))
                        .filter(StrUtil::isNotBlank).map(String::trim)
                        .map(s -> safeParseLong(s))
                        .filter(Objects::nonNull)
                        .forEach(stationIds::add);
            }
        }
        // 翻译用户名称
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
        // 翻译场站名称
        if (!stationIds.isEmpty()) {
            Map<Long, StationInfoRespDTO> stationMap = stationInfoApi.getStationMap(stationIds);
            for (var item : list) {
                if (StrUtil.isNotBlank(item.getStationIds())) {
                    String names = Arrays.stream(item.getStationIds().split(","))
                            .filter(StrUtil::isNotBlank).map(String::trim)
                            .map(s -> {
                                Long id = safeParseLong(s);
                                if (id == null) return null;
                                StationInfoRespDTO station = stationMap.get(id);
                                return station != null ? station.getName() : null;
                            })
                            .filter(Objects::nonNull)
                            .collect(Collectors.joining(","));
                    item.setStationNames(names);
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
