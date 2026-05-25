package cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupcar;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.ChartHelper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.TimeRangeParser;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupcar.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.groupclient.groupcar.GroupCarDO;
import cn.iocoder.yudao.module.usermerchant.service.groupclient.groupcar.GroupCarService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "管理后台 - 集团车辆")
@RestController
@RequestMapping("/usermerchant/group-car")
@Validated
public class GroupCarController {

    @Autowired
    private ChartHelper chartHelper;

    @Resource
    private GroupCarService groupCarService;

    @GetMapping("/page")
    @Operation(summary = "获得集团车辆分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:query')")
    public CommonResult<PageResult<GroupCarPageRespVO>> getGroupCarPage(@Valid GroupCarPageReqVO pageReqVO) {
        PageResult<GroupCarDO> pageResult = groupCarService.getGroupCarPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GroupCarPageRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建集团车辆")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:create')")
    public CommonResult<Boolean> createGroupCar(@Valid @RequestBody GroupCarSaveReqVO createReqVO) {
        return success(groupCarService.createGroupCar(createReqVO));
    }

    @PostMapping("/import")
    @Operation(summary = "导入集团车辆")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    })
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<Boolean> importExcel(@RequestParam("file") MultipartFile file,
                                             @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<GroupCarImportExcelVO> list = ExcelUtils.read(file, GroupCarImportExcelVO.class);
        return success(groupCarService.importGroups(list, updateSupport));
    }

    @GetMapping("/template")
    @Operation(summary = "下载集团车辆导入模板")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:import')")
    public void downloadImportTemplate(HttpServletResponse response) throws IOException {
        // 构造一条示例数据（必填字段均已填写）
        GroupCarImportExcelVO example = GroupCarImportExcelVO.builder()
                .name("示例集团")
                .plateNo("京A12345")
                .plateColor("蓝")
                .carType("轿车")
                .bindTime(LocalDateTime.now()) // 绑定时间示例为当前时间
                .remark("示例备注")
                .build();
        List<GroupCarImportExcelVO> exampleList = Collections.singletonList(example);
        ExcelUtils.write(response, "集团车辆导入模板.xlsx", "集团车辆", GroupCarImportExcelVO.class, exampleList);
    }

    @GetMapping("/export")
    @Operation(summary = "导出集团车辆")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGroupCarExcel(@Valid GroupCarPageReqVO pageReqVO,
                                    HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GroupCarDO> list = groupCarService.getGroupCarPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "集团车辆.xls", "数据", GroupCarExportRespVO.class,
                BeanUtils.toBean(list, GroupCarExportRespVO.class));
    }

    @PutMapping("/approve")
    @Operation(summary = "审核通过")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:approve')")
    public CommonResult<Boolean> approveGroupCar(@Valid @RequestBody GroupCarAuditReqVO reqVO) {
        reqVO.setStatus("已绑定");
        groupCarService.auditGroupCar(reqVO);
        return success(true);
    }

    @PutMapping("/reject")
    @Operation(summary = "审核驳回")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:reject')")
    public CommonResult<Boolean> rejectGroupCar(@Valid @RequestBody GroupCarAuditReqVO reqVO) {
        reqVO.setStatus("已驳回");
        groupCarService.auditGroupCar(reqVO);
        return success(true);
    }

    @PutMapping("/unbind")
    @Operation(summary = "车辆解绑")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:unbind')")
    public CommonResult<Boolean> unbindGroupCar(@Valid @RequestBody GroupCarAuditReqVO reqVO) {
        reqVO.setStatus("已解绑");
        groupCarService.auditGroupCar(reqVO);
        return success(true);
    }

    @PutMapping("/rebind")
    @Operation(summary = "车辆重绑")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:rebind')")
    public CommonResult<Boolean> rebindGroupCar(@Valid @RequestBody GroupCarAuditReqVO reqVO) {
        reqVO.setStatus("待审核");
        groupCarService.auditGroupCar(reqVO);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得集团车辆")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:query')")
    public CommonResult<GroupCarPageRespVO> getGroupCar(@RequestParam("id") Long id) {
        GroupCarDO groupCar = groupCarService.getGroupCar(id);
        return success(BeanUtils.toBean(groupCar, GroupCarPageRespVO.class));
    }

    @PutMapping("/update")
    @Operation(summary = "更新集团车辆")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:update')")
    public CommonResult<Boolean> updateGroupCar(@Valid @RequestBody GroupCarUpdateReqVO updateReqVO) {
        groupCarService.updateGroupCar(updateReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "集团车辆统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:query')")
    public CommonResult<GroupCarChartRespVO> getChart(@RequestParam(required = false) String timeRange) {
        // 1. 解析时间范围
        TimeRangeParser.TimeRangeParsed parsed;
        if (StrUtil.isBlank(timeRange)) {
            parsed = new TimeRangeParser.TimeRangeParsed(null, null, "day");
        } else {
            parsed = TimeRangeParser.parse(timeRange);
            if (parsed == null) {
                parsed = new TimeRangeParser.TimeRangeParsed(null, null, "day");
            }
        }
        LocalDateTime start = parsed.getStart();
        LocalDateTime end = parsed.getEnd();

        // 2. 柱状图：按 car_type 分组统计所有车辆（不分状态）
        ChartHelper.ChartQuery barQuery = ChartHelper.ChartQuery.builder()
                .tableName("group_car")
                .groupField("car_type")
                .extraWhere("deleted = 0")
                .dateField("create_time")
                .start(start)
                .end(end)
                .build();
        List<ChartHelper.ChartDataVO> barData = chartHelper.queryPieOrBar(barQuery);
        List<GroupCarChartRespVO.CarTypeDistributionVO> distribution = barData.stream()
                .map(d -> {
                    GroupCarChartRespVO.CarTypeDistributionVO vo = new GroupCarChartRespVO.CarTypeDistributionVO();
                    vo.setType(d.getName());
                    vo.setCount(d.getValue().intValue());
                    return vo;
                }).collect(Collectors.toList());

        // 3. 绑定车辆数：只统计 status = '已绑定' 的车辆
        ChartHelper.ChartQuery bindCountQuery = ChartHelper.ChartQuery.builder()
                .tableName("group_car")
                .extraWhere("deleted = 0 AND status = '已绑定'")
                .dateField("create_time")
                .start(start)
                .end(end)
                .build();
        long bindCarCount = chartHelper.queryTotalCount(bindCountQuery);

        // 4. 审核通过率：分子 = 已绑定数，分母 = 待绑定 + 已绑定（排除已解绑）
        String numeratorSql = "SELECT COUNT(*) FROM group_car WHERE deleted = 0 AND status = '已绑定'";
        String denominatorSql = "SELECT COUNT(*) FROM group_car WHERE deleted = 0 AND status IN ('待绑定', '已绑定')";
        // 添加时间范围条件
        if (start != null) {
            String startStr = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            numeratorSql += " AND create_time >= '" + startStr + "'";
            denominatorSql += " AND create_time >= '" + startStr + "'";
        }
        if (end != null) {
            String endStr = end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            numeratorSql += " AND create_time <= '" + endStr + "'";
            denominatorSql += " AND create_time <= '" + endStr + "'";
        }
        BigDecimal auditPassRate = chartHelper.queryRate(numeratorSql, denominatorSql);

        // 5. 组装响应
        GroupCarChartRespVO respVO = new GroupCarChartRespVO();
        respVO.setCarTypeDistribution(distribution);
        respVO.setBindCarCount((int) bindCarCount);
        respVO.setAuditPassRate(auditPassRate);
        return CommonResult.success(respVO);
    }
//————————————————————

//    @DeleteMapping("/delete")
//    @Operation(summary = "删除集团车辆")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:delete')")
//    public CommonResult<Boolean> deleteGroupCar(@RequestParam("id") Long id) {
//        groupCarService.deleteGroupCar(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除集团车辆")
//                @PreAuthorize("@ss.hasPermission('usermerchant:group-car:delete')")
//    public CommonResult<Boolean> deleteGroupCarList(@RequestParam("ids") List<Long> ids) {
//        groupCarService.deleteGroupCarListByIds(ids);
//        return success(true);
//    }

}