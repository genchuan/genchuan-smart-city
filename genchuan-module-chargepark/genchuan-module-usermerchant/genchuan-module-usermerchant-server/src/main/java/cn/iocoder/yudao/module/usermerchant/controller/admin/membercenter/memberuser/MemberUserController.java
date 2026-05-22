package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberuser;

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

import java.time.LocalDateTime;
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

import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberuser.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberuser.MemberUserDO;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.memberuser.MemberUserService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "管理后台 - 会员用户")
@RestController
@RequestMapping("/usermerchant/member-user")
@Validated
public class MemberUserController {

    @Autowired
    private ChartHelper chartHelper;

    @Resource
    private MemberUserService memberUserService;

    @GetMapping("/page")
    @Operation(summary = "获得会员用户分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-user:query')")
    public CommonResult<PageResult<MemberUserRespVO>> getMemberUserPage(@Valid MemberUserPageReqVO pageReqVO) {
        PageResult<MemberUserDO> pageResult = memberUserService.getMemberUserPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MemberUserRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建会员用户")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-user:create')")
    public CommonResult<Long> createMemberUser(@Valid @RequestBody MemberUserSaveReqVO createReqVO) {
        return success(memberUserService.createMemberUser(createReqVO));
    }

    @PostMapping("/import")
    @Operation(summary = "导入会员用户")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    })
    @PreAuthorize("@ss.hasPermission('usermerchant:member-user:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<Boolean> importExcel(@RequestParam("file") MultipartFile file,
                                             @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<MemberUserImportExcelVO> list = ExcelUtils.read(file, MemberUserImportExcelVO.class);
        return success(memberUserService.importUsers(list, updateSupport));
    }

    @GetMapping("/template")
    @Operation(summary = "下载会员用户导入模板")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-user:import')")
    public void downloadImportTemplate(HttpServletResponse response) throws IOException {
        List<MemberUserImportExcelVO> emptyList = Collections.emptyList();
        ExcelUtils.write(response, "会员用户导入模板.xlsx", "会员用户", MemberUserImportExcelVO.class, emptyList);
    }

    @GetMapping("/export")
    @Operation(summary = "导出会员用户")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-user:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMemberUserExcel(@Valid MemberUserPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MemberUserDO> list = memberUserService.getMemberUserPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "会员用户.xls", "数据", MemberUserRespVO.class,
                BeanUtils.toBean(list, MemberUserRespVO.class));
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用会员配置")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-user:disable')")
    public CommonResult<Boolean> disableMemberUser(@Valid @RequestBody MemberUserStatusReqVO reqVO) {
        memberUserService.updateUserStatus(reqVO.getIds(), 0);
        return success(true);
    }

    @PutMapping("/enable")
    @Operation(summary = "启用会员配置")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-user:enable')")
    public CommonResult<Boolean> enableMemberUser(@Valid @RequestBody MemberUserStatusReqVO reqVO) {
        memberUserService.updateUserStatus(reqVO.getIds(), 1);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得会员用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-user:query')")
    public CommonResult<MemberUserRespVO> getMemberUser(@RequestParam("id") Long id) {
        MemberUserDO memberUser = memberUserService.getMemberUser(id);
        return success(BeanUtils.toBean(memberUser, MemberUserRespVO.class));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会员用户")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-user:update')")
    public CommonResult<Boolean> updateMemberUser(@Valid @RequestBody MemberUserSaveReqVO updateReqVO) {
        memberUserService.updateMemberUser(updateReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "会员统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-user:query')")
    public CommonResult<MemberUserChartRespVO> getChart(@RequestParam(required = false) String timeRange) {
        // 1. 解析时间范围
        TimeRangeParser.TimeRangeParsed parsed = TimeRangeParser.parseOrDefault(timeRange);

        // 1. 折线图: 按时间聚合会员增长
        ChartHelper.ChartQuery lineQuery = ChartHelper.ChartQuery.builder()
                .tableName("member_user")
                .dateField("create_time")
                .aggregate(ChartHelper.AggregateType.COUNT)
                .start(parsed.getStart())
                .end(parsed.getEnd())
                .granularity(parsed.getGranularity())
                .build();
        List<ChartHelper.ChartDataVO> trendData = chartHelper.queryLineChart(lineQuery);
        List<MemberUserChartRespVO.MemberGrowthTrendVO> growthTrend = trendData.stream()
                .map(d -> {
                    MemberUserChartRespVO.MemberGrowthTrendVO vo = new MemberUserChartRespVO.MemberGrowthTrendVO();
                    vo.setDate(d.getName());
                    vo.setCount(d.getValue().intValue());
                    return vo;
                }).collect(Collectors.toList());

        // 2. 总会员数
        ChartHelper.ChartQuery totalQuery = ChartHelper.ChartQuery.builder()
                .tableName("member_user")
                .build();
        long totalCount = chartHelper.queryTotalCount(totalQuery);

        // 3. 新增会员数: 近30天
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime thirtyDaysAgo = now.minusDays(30);
        ChartHelper.ChartQuery newQuery = ChartHelper.ChartQuery.builder()
                .tableName("member_user")
                .dateField("create_time")
                .start(thirtyDaysAgo)
                .end(now)  // 包含到当前时间
                .build();
        long newCount = chartHelper.queryTotalCount(newQuery);

        MemberUserChartRespVO respVO = new MemberUserChartRespVO();
        respVO.setMemberGrowthTrend(growthTrend);
        respVO.setTotalMemberCount((int) totalCount);
        respVO.setNewMemberCount((int) newCount);
        return CommonResult.success(respVO);
    }

//    @DeleteMapping("/delete")
//    @Operation(summary = "删除会员用户")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:member-user:delete')")
//    public CommonResult<Boolean> deleteMemberUser(@RequestParam("id") Long id) {
//        memberUserService.deleteMemberUser(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除会员用户")
//                @PreAuthorize("@ss.hasPermission('usermerchant:member-user:delete')")
//    public CommonResult<Boolean> deleteMemberUserList(@RequestParam("ids") List<Long> ids) {
//        memberUserService.deleteMemberUserListByIds(ids);
//        return success(true);
//    }

}