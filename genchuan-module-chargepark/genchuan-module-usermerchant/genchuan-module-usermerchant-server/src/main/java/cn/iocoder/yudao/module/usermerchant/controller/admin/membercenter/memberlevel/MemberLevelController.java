package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberlevel.MemberLevelDO;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.memberlevel.MemberLevelService;

@Tag(name = "管理后台 - 会员等级")
@RestController
@RequestMapping("/usermerchant/member-level")
@Validated
public class MemberLevelController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Resource
    private MemberLevelService memberLevelService;

    @GetMapping("/page")
    @Operation(summary = "获得会员等级分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-level:query')")
    public CommonResult<PageResult<MemberLevelRespVO>> getMemberLevelPage(@Valid MemberLevelPageReqVO pageReqVO) {
        PageResult<MemberLevelDO> pageResult = memberLevelService.getMemberLevelPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MemberLevelRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建会员等级")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-level:create')")
    public CommonResult<Long> createMemberLevel(@Valid @RequestBody MemberLevelSaveReqVO createReqVO) {
        return success(memberLevelService.createMemberLevel(createReqVO));
    }

    @PostMapping("/save")
    @Operation(summary = "保存会员等级")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-level:save')")
    public CommonResult<Boolean> saveMemberLevel(@Valid @RequestBody MemberLevelSaveReqVO saveReqVO) {
        return success(memberLevelService.saveMemberLevel(saveReqVO));
    }

    @PutMapping("/enable")
    @Operation(summary = "启用会员等级")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-level:enable')")
    public CommonResult<Boolean> enableMemberLevel(@Valid @RequestBody MemberLevelStatusReqVO reqVO) {
        memberLevelService.updateLevelStatus(reqVO.getIds(), 1);
        return success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用会员等级")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-level:disable')")
    public CommonResult<Boolean> disableMemberLevel(@Valid @RequestBody MemberLevelStatusReqVO reqVO) {
        memberLevelService.updateLevelStatus(reqVO.getIds(), 0);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得会员等级")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-level:query')")
    public CommonResult<MemberLevelRespVO> getMemberLevel(@RequestParam("id") Long id) {
        MemberLevelDO memberLevel = memberLevelService.getMemberLevel(id);
        return success(BeanUtils.toBean(memberLevel, MemberLevelRespVO.class));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会员等级")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-level:update')")
    public CommonResult<Boolean> updateMemberLevel(@Valid @RequestBody MemberLevelSaveReqVO updateReqVO) {
        memberLevelService.updateMemberLevel(updateReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "会员等级统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-level:query')")
    public CommonResult<MemberLevelChartRespVO> getChart(@RequestParam(required = false) String timeRange) {
        // 1. 柱状图：每个等级的用户数（关联 member_user.level_id）
        String distributionSql = """
        SELECT ml.name AS level, COUNT(mu.id) AS count
        FROM member_level ml
        LEFT JOIN member_user mu ON mu.level_id = ml.id AND mu.deleted = 0
        WHERE ml.deleted = 0
        GROUP BY ml.id, ml.name, ml.level_value
        ORDER BY ml.level_value ASC
        """;
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(distributionSql);
        List<MemberLevelChartRespVO.LevelUserDistributionVO> distribution = rows.stream()
                .map(row -> {
                    MemberLevelChartRespVO.LevelUserDistributionVO vo = new MemberLevelChartRespVO.LevelUserDistributionVO();
                    vo.setLevel((String) row.get("level"));
                    vo.setCount(((Number) row.get("count")).intValue());
                    return vo;
                }).collect(Collectors.toList());

        // 2. 等级总数
        String levelCountSql = "SELECT COUNT(*) FROM member_level WHERE deleted = 0";
        Long levelCount = jdbcTemplate.queryForObject(levelCountSql, Long.class);

        // 3. 等级升级率 = (总会员数 - 最低等级会员数) / 总会员数
        // 先查出最低等级（level_value 最小的等级）
        String lowestLevelSql = "SELECT id FROM member_level WHERE deleted = 0 ORDER BY level_value ASC LIMIT 1";
        Long lowestLevelId = jdbcTemplate.queryForObject(lowestLevelSql, Long.class);
        String totalUserSql = "SELECT COUNT(*) FROM member_user WHERE deleted = 0";
        String lowestUserSql = "SELECT COUNT(*) FROM member_user WHERE deleted = 0 AND level_id = ?";
        Long totalUser = jdbcTemplate.queryForObject(totalUserSql, Long.class);
        Long lowestUser = jdbcTemplate.queryForObject(lowestUserSql, Long.class, lowestLevelId);

        BigDecimal upgradeRate = BigDecimal.ZERO;
        if (totalUser != null && totalUser > 0) {
            long upgradedUser = totalUser - (lowestUser != null ? lowestUser : 0);
            upgradeRate = BigDecimal.valueOf(upgradedUser).divide(BigDecimal.valueOf(totalUser), 4, RoundingMode.HALF_UP);
        }

        // 组装响应
        MemberLevelChartRespVO respVO = new MemberLevelChartRespVO();
        respVO.setLevelUserDistribution(distribution);
        respVO.setLevelCount(levelCount != null ? levelCount.intValue() : 0);
        respVO.setLevelUpgradeRate(upgradeRate);
        return CommonResult.success(respVO);
    }

//    @DeleteMapping("/delete")
//    @Operation(summary = "删除会员等级")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:member-level:delete')")
//    public CommonResult<Boolean> deleteMemberLevel(@RequestParam("id") Long id) {
//        memberLevelService.deleteMemberLevel(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除会员等级")
//                @PreAuthorize("@ss.hasPermission('usermerchant:member-level:delete')")
//    public CommonResult<Boolean> deleteMemberLevelList(@RequestParam("ids") List<Long> ids) {
//        memberLevelService.deleteMemberLevelListByIds(ids);
//        return success(true);
//    }
//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出会员等级 Excel")
//    @PreAuthorize("@ss.hasPermission('usermerchant:member-level:export')")
//    @ApiAccessLog(operateType = EXPORT)
//    public void exportMemberLevelExcel(@Valid MemberLevelPageReqVO pageReqVO,
//              HttpServletResponse response) throws IOException {
//        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<MemberLevelDO> list = memberLevelService.getMemberLevelPage(pageReqVO).getList();
//        // 导出 Excel
//        ExcelUtils.write(response, "会员等级.xls", "数据", MemberLevelRespVO.class,
//                        BeanUtils.toBean(list, MemberLevelRespVO.class));
//    }

}