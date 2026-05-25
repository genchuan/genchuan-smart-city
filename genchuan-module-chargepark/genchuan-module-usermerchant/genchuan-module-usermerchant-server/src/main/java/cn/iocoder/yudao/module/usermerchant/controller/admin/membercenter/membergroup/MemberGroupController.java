package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membergroup;

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
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membergroup.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membergroup.MemberGroupDO;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.membergroup.MemberGroupService;

@Tag(name = "管理后台 - 会员分组")
@RestController
@RequestMapping("/usermerchant/member-group")
@Validated
public class MemberGroupController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Resource
    private MemberGroupService memberGroupService;

    @GetMapping("/page")
    @Operation(summary = "获得会员分组分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-group:query')")
    public CommonResult<PageResult<MemberGroupRespVO>> getMemberGroupPage(@Valid MemberGroupPageReqVO pageReqVO) {
        PageResult<MemberGroupDO> pageResult = memberGroupService.getMemberGroupPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MemberGroupRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建会员分组")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-group:create')")
    public CommonResult<Long> createMemberGroup(@Valid @RequestBody MemberGroupSaveReqVO createReqVO) {
        return success(memberGroupService.createMemberGroup(createReqVO));
    }

    @PostMapping("/save")
    @Operation(summary = "保存会员分组")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-group:save')")
    public CommonResult<Boolean> saveMemberGroup(@Valid @RequestBody MemberGroupSaveReqVO saveReqVO) {
        return success(memberGroupService.saveMemberGroup(saveReqVO));
    }

    @PutMapping("/enable")
    @Operation(summary = "启用会员分组")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-group:enable')")
    public CommonResult<Boolean> enableMemberGroup(@Valid @RequestBody MemberGroupStatusReqVO reqVO) {
        memberGroupService.updateGroupStatus(reqVO.getIds(), 1);
        return success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用会员分组")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-group:disable')")
    public CommonResult<Boolean> disableMemberGroup(@Valid @RequestBody MemberGroupStatusReqVO reqVO) {
        memberGroupService.updateGroupStatus(reqVO.getIds(), 0);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得会员分组")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-group:query')")
    public CommonResult<MemberGroupRespVO> getMemberGroup(@RequestParam("id") Long id) {
        MemberGroupDO memberGroup = memberGroupService.getMemberGroup(id);
        return success(BeanUtils.toBean(memberGroup, MemberGroupRespVO.class));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会员分组")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-group:update')")
    public CommonResult<Boolean> updateMemberGroup(@Valid @RequestBody MemberGroupSaveReqVO updateReqVO) {
        memberGroupService.updateMemberGroup(updateReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "会员分组统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-group:query')")
    public CommonResult<MemberGroupChartRespVO> getChart(@RequestParam(required = false) String timeRange) {
        // 1. 饼图：每个分组的用户数
        String distributionSql = """
        SELECT mg.name AS `group`, COUNT(mu.id) AS count
        FROM member_group mg
        LEFT JOIN member_user mu ON mu.group_id = mg.id AND mu.deleted = 0
        WHERE mg.deleted = 0
        GROUP BY mg.id, mg.name
        ORDER BY count DESC
        """;
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(distributionSql);
        List<MemberGroupChartRespVO.GroupUserDistributionVO> distribution = rows.stream()
                .map(row -> {
                    MemberGroupChartRespVO.GroupUserDistributionVO vo = new MemberGroupChartRespVO.GroupUserDistributionVO();
                    vo.setGroup((String) row.get("group"));
                    vo.setCount(((Number) row.get("count")).intValue());
                    return vo;
                }).collect(Collectors.toList());

        // 2. 分组总数
        String groupCountSql = "SELECT COUNT(*) FROM member_group WHERE deleted = 0";
        Long groupCount = jdbcTemplate.queryForObject(groupCountSql, Long.class);

        // 3. 分组用户数：至少有一个分组的用户数（group_id 不为空）
        String userCountSql = "SELECT COUNT(*) FROM member_user WHERE deleted = 0 AND group_id IS NOT NULL";
        Long groupUserCount = jdbcTemplate.queryForObject(userCountSql, Long.class);

        MemberGroupChartRespVO respVO = new MemberGroupChartRespVO();
        respVO.setGroupUserDistribution(distribution);
        respVO.setGroupCount(groupCount != null ? groupCount.intValue() : 0);
        respVO.setGroupUserCount(groupUserCount != null ? groupUserCount.intValue() : 0);
        return CommonResult.success(respVO);
    }

//    @DeleteMapping("/delete")
//    @Operation(summary = "删除会员分组")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:member-group:delete')")
//    public CommonResult<Boolean> deleteMemberGroup(@RequestParam("id") Long id) {
//        memberGroupService.deleteMemberGroup(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除会员分组")
//                @PreAuthorize("@ss.hasPermission('usermerchant:member-group:delete')")
//    public CommonResult<Boolean> deleteMemberGroupList(@RequestParam("ids") List<Long> ids) {
//        memberGroupService.deleteMemberGroupListByIds(ids);
//        return success(true);
//    }
//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出会员分组 Excel")
//    @PreAuthorize("@ss.hasPermission('usermerchant:member-group:export')")
//    @ApiAccessLog(operateType = EXPORT)
//    public void exportMemberGroupExcel(@Valid MemberGroupPageReqVO pageReqVO,
//              HttpServletResponse response) throws IOException {
//        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<MemberGroupDO> list = memberGroupService.getMemberGroupPage(pageReqVO).getList();
//        // 导出 Excel
//        ExcelUtils.write(response, "会员分组.xls", "数据", MemberGroupRespVO.class,
//                        BeanUtils.toBean(list, MemberGroupRespVO.class));
//    }

}