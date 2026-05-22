package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.ChartHelper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.TimeRangeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberconfig.MemberConfigDO;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.memberconfig.MemberConfigService;

@Tag(name = "管理后台 - 会员配置")
@RestController
@RequestMapping("/usermerchant/member-config")
@Validated
public class MemberConfigController {

    @Autowired
    private ChartHelper chartHelper;

    @Resource
    private MemberConfigService memberConfigService;

    @GetMapping("/page")
    @Operation(summary = "获得会员配置分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:query')")
    public CommonResult<PageResult<MemberConfigRespVO>> getMemberConfigPage(@Valid MemberConfigPageReqVO pageReqVO) {
        PageResult<MemberConfigDO> pageResult = memberConfigService.getMemberConfigPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MemberConfigRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建会员配置")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:create')")
    public CommonResult<Long> createMemberConfig(@Valid @RequestBody MemberConfigSaveReqVO createReqVO) {
        return success(memberConfigService.createMemberConfig(createReqVO));
    }

    @PostMapping("/save")
    @Operation(summary = "保存会员配置")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:save')")
    public CommonResult<Boolean> saveMemberConfig(@Valid @RequestBody MemberConfigSaveReqVO saveReqVO) {
        return success(memberConfigService.saveMemberConfig(saveReqVO));
    }

    @PutMapping("/enable")
    @Operation(summary = "启用会员配置")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:enable')")
    public CommonResult<Boolean> enableMemberConfig(@Valid @RequestBody MemberConfigStatusReqVO reqVO) {
        memberConfigService.updateConfigStatus(reqVO.getIds(), 1);
        return success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用会员配置")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:disable')")
    public CommonResult<Boolean> disableMemberConfig(@Valid @RequestBody MemberConfigStatusReqVO reqVO) {
        memberConfigService.updateConfigStatus(reqVO.getIds(), 0);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得会员配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:query')")
    public CommonResult<MemberConfigRespVO> getMemberConfig(@RequestParam("id") Long id) {
        MemberConfigDO memberConfig = memberConfigService.getMemberConfig(id);
        return success(BeanUtils.toBean(memberConfig, MemberConfigRespVO.class));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会员配置")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:update')")
    public CommonResult<Boolean> updateMemberConfig(@Valid @RequestBody MemberConfigSaveReqVO updateReqVO) {
        memberConfigService.updateMemberConfig(updateReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "会员配置统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:query')")
    public CommonResult<MemberConfigChartRespVO> getChart(@RequestParam(required = false) String timeRange) {
        // 1. 解析时间范围，若为空或解析失败则默认全量查询（start=null, end=null, granularity="day"）
        TimeRangeParser.TimeRangeParsed parsed;
        if (StrUtil.isBlank(timeRange)) {
            // 前端未传时间范围 → 全量数据，无时间筛选，粒度默认 day
            parsed = new TimeRangeParser.TimeRangeParsed(null, null, "day");
        } else {
            parsed = TimeRangeParser.parse(timeRange);
            if (parsed == null) {
                // 传入的时间范围格式非法 → 同样按全量处理（或根据需求返回空数据，这里按全量）
                parsed = new TimeRangeParser.TimeRangeParsed(null, null, "day");
            }
        }

        LocalDateTime start = parsed.getStart();
        LocalDateTime end = parsed.getEnd();

        // 2. 饼图：按 config_type 分组统计
        ChartHelper.ChartQuery pieQuery = ChartHelper.ChartQuery.builder()
                .tableName("member_config")
                .groupField("config_type")
                .extraWhere("deleted = 0")
                .dateField("create_time")
                .start(start)
                .end(end)
                .build();
        List<ChartHelper.ChartDataVO> pieData = chartHelper.queryPieOrBar(pieQuery);
        List<MemberConfigChartRespVO.ConfigTypeDistributionVO> distribution = pieData.stream()
                .map(d -> {
                    MemberConfigChartRespVO.ConfigTypeDistributionVO vo = new MemberConfigChartRespVO.ConfigTypeDistributionVO();
                    vo.setType(d.getName());
                    vo.setCount(d.getValue().intValue());
                    return vo;
                }).collect(Collectors.toList());

        // 3. 生效配置数卡片
        ChartHelper.ChartQuery countQuery = ChartHelper.ChartQuery.builder()
                .tableName("member_config")
                .extraWhere("status = 1 AND deleted = 0")
                .dateField("create_time")
                .start(start)
                .end(end)
                .build();
        long effectCount = chartHelper.queryTotalCount(countQuery);

        // 4. 会员匹配率（分子分母自定义SQL，根据你的业务规则实现）
        // 注意：这里也需要考虑时间范围（匹配用户的创建时间或配置生效时间等），根据实际业务调整
        String numeratorSql = "SELECT COUNT(DISTINCT id) FROM member_user WHERE deleted = 0"; // TODO: 根据匹配规则填写
        String denominatorSql = "SELECT COUNT(*) FROM member_user WHERE deleted = 0";
        BigDecimal matchRate = chartHelper.queryRate(numeratorSql, denominatorSql);

        // 5. 组装响应
        MemberConfigChartRespVO respVO = new MemberConfigChartRespVO();
        respVO.setConfigTypeDistribution(distribution);
        respVO.setEffectConfigCount((int) effectCount);
        respVO.setMemberMatchRate(matchRate);
        return CommonResult.success(respVO);
    }

//    @DeleteMapping("/delete")
//    @Operation(summary = "删除会员配置")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:delete')")
//    public CommonResult<Boolean> deleteMemberConfig(@RequestParam("id") Long id) {
//        memberConfigService.deleteMemberConfig(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除会员配置")
//                @PreAuthorize("@ss.hasPermission('usermerchant:member-config:delete')")
//    public CommonResult<Boolean> deleteMemberConfigList(@RequestParam("ids") List<Long> ids) {
//        memberConfigService.deleteMemberConfigListByIds(ids);
//        return success(true);
//    }
//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出会员配置 Excel")
//    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:export')")
//    @ApiAccessLog(operateType = EXPORT)
//    public void exportMemberConfigExcel(@Valid MemberConfigPageReqVO pageReqVO,
//              HttpServletResponse response) throws IOException {
//        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<MemberConfigDO> list = memberConfigService.getMemberConfigPage(pageReqVO).getList();
//        // 导出 Excel
//        ExcelUtils.write(response, "会员配置.xls", "数据", MemberConfigRespVO.class,
//                        BeanUtils.toBean(list, MemberConfigRespVO.class));
//    }

}