package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberpoint;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.ChartHelper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.TimeRangeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;

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

import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberpoint.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberpoint.MemberPointDO;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.memberpoint.MemberPointService;

@Tag(name = "管理后台 - 会员积分")
@RestController
@RequestMapping("/usermerchant/member-point")
@Validated
public class MemberPointController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ChartHelper chartHelper;

    @Resource
    private MemberPointService memberPointService;

    @GetMapping("/page")
    @Operation(summary = "获得会员积分分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-point:query')")
    public CommonResult<PageResult<MemberPointRespVO>> getMemberPointPage(@Valid MemberPointPageReqVO pageReqVO) {
        PageResult<MemberPointDO> pageResult = memberPointService.getMemberPointPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MemberPointRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出会员积分")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-point:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMemberPointExcel(@Valid MemberPointPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MemberPointDO> list = memberPointService.getMemberPointPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "会员积分.xls", "数据", MemberPointRespVO.class,
                BeanUtils.toBean(list, MemberPointRespVO.class));
    }

    @PutMapping("/check")
    @Operation(summary = "核查异常积分记录")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-point:check')")
    public CommonResult<Boolean> check(@Valid @RequestBody MemberPointCheckReqVO reqVO) {
        memberPointService.checkPointRecord(reqVO);
        return CommonResult.success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得会员积分")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-point:query')")
    public CommonResult<MemberPointRespVO> getMemberPoint(@RequestParam("id") Long id) {
        MemberPointDO memberPoint = memberPointService.getMemberPoint(id);
        return success(BeanUtils.toBean(memberPoint, MemberPointRespVO.class));
    }

    @GetMapping("/chart")
    @Operation(summary = "会员积分统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-point:query')")
    public CommonResult<MemberPointChartRespVO> getChart(@RequestParam(required = false) String timeRange) {
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
        String granularity = parsed.getGranularity();

        // 2. 折线图：按时间粒度统计净变动积分（change_amount 的 SUM）
        ChartHelper.ChartQuery lineQuery = ChartHelper.ChartQuery.builder()
                .tableName("member_point")
                .dateField("create_time")
                .aggregate(ChartHelper.AggregateType.SUM)
                .sumField("change_amount")
                .start(start)
                .end(end)
                .granularity(granularity)
                .build();
        List<ChartHelper.ChartDataVO> trendData = chartHelper.queryLineChart(lineQuery);
        List<MemberPointChartRespVO.PointTrendVO> trend = trendData.stream()
                .map(d -> {
                    MemberPointChartRespVO.PointTrendVO vo = new MemberPointChartRespVO.PointTrendVO();
                    vo.setDate(d.getName());
                    vo.setCount(d.getValue().longValue());
                    return vo;
                }).collect(Collectors.toList());

        // 3. 总积分：所有用户当前积分之和（来自 member_user.point）
        String totalPointSql = "SELECT SUM(point) FROM member_user WHERE deleted = 0";
        Long totalPoint = jdbcTemplate.queryForObject(totalPointSql, Long.class);
        totalPoint = totalPoint != null ? totalPoint : 0L;

        // 4. 积分变动量：时间范围内的积分变动绝对值总和（所有变动的绝对值之和）
        String changeCountSql = "SELECT SUM(ABS(change_amount)) FROM member_point WHERE deleted = 0";
        if (start != null) {
            changeCountSql += " AND create_time >= '" + start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "'";
        }
        if (end != null) {
            changeCountSql += " AND create_time <= '" + end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "'";
        }
        Long pointChangeCount = jdbcTemplate.queryForObject(changeCountSql, Long.class);
        pointChangeCount = pointChangeCount != null ? pointChangeCount : 0L;

        // 5. 组装响应
        MemberPointChartRespVO respVO = new MemberPointChartRespVO();
        respVO.setPointTrend(trend);
        respVO.setTotalPoint(totalPoint);
        respVO.setPointChangeCount(pointChangeCount);
        return CommonResult.success(respVO);
    }

//    @PostMapping("/create")
//    @Operation(summary = "创建会员积分")
//    @PreAuthorize("@ss.hasPermission('usermerchant:member-point:create')")
//    public CommonResult<Long> createMemberPoint(@Valid @RequestBody MemberPointSaveReqVO createReqVO) {
//        return success(memberPointService.createMemberPoint(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新会员积分")
//    @PreAuthorize("@ss.hasPermission('usermerchant:member-point:update')")
//    public CommonResult<Boolean> updateMemberPoint(@Valid @RequestBody MemberPointSaveReqVO updateReqVO) {
//        memberPointService.updateMemberPoint(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除会员积分")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:member-point:delete')")
//    public CommonResult<Boolean> deleteMemberPoint(@RequestParam("id") Long id) {
//        memberPointService.deleteMemberPoint(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除会员积分")
//                @PreAuthorize("@ss.hasPermission('usermerchant:member-point:delete')")
//    public CommonResult<Boolean> deleteMemberPointList(@RequestParam("ids") List<Long> ids) {
//        memberPointService.deleteMemberPointListByIds(ids);
//        return success(true);
//    }

}