package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membertag;

import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupcar.vo.GroupCarImportExcelVO;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import io.swagger.v3.oas.annotations.Parameters;
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
import jakarta.servlet.http.*;

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
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membertag.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membertag.MemberTagDO;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.membertag.MemberTagService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "管理后台 - 会员标签")
@RestController
@RequestMapping("/usermerchant/member-tag")
@Validated
public class MemberTagController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Resource
    private MemberTagService memberTagService;

    @GetMapping("/page")
    @Operation(summary = "获得会员标签分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-tag:query')")
    public CommonResult<PageResult<MemberTagRespVO>> getMemberTagPage(@Valid MemberTagPageReqVO pageReqVO) {
        PageResult<MemberTagDO> pageResult = memberTagService.getMemberTagPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MemberTagRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建会员标签")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-tag:create')")
    public CommonResult<Long> createMemberTag(@Valid @RequestBody MemberTagSaveReqVO createReqVO) {
        return success(memberTagService.createMemberTag(createReqVO));
    }

    @PostMapping("/import")
    @Operation(summary = "导入会员标签")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    })
    @PreAuthorize("@ss.hasPermission('usermerchant:member-tag:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<Boolean> importExcel(@RequestParam("file") MultipartFile file,
                                             @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<MemberTagImportExcelVO> list = ExcelUtils.read(file, MemberTagImportExcelVO.class);
        return success(memberTagService.importUsers(list, updateSupport));
    }

    @GetMapping("/template")
    @Operation(summary = "下载会员标签导入模板")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-tag:import')")
    public void downloadImportTemplate(HttpServletResponse response) throws IOException {
        MemberTagImportExcelVO example = MemberTagImportExcelVO.builder()
                .name("示例标签")
                .description("示例描述")
                .status(1)
                .build();
        List<MemberTagImportExcelVO> exampleList = Collections.singletonList(example);
        ExcelUtils.write(response, "会员标签导入模板.xlsx", "会员标签", MemberTagImportExcelVO.class, exampleList);
    }

    @GetMapping("/export")
    @Operation(summary = "导出会员标签")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-tag:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMemberTagExcel(@Valid MemberTagPageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MemberTagDO> list = memberTagService.getMemberTagPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "会员标签.xls", "数据", MemberTagRespVO.class,
                BeanUtils.toBean(list, MemberTagRespVO.class));
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用会员标签")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-tag:disable')")
    public CommonResult<Boolean> disableMemberTag(@Valid @RequestBody MemberTagStatusReqVO reqVO) {
        memberTagService.updateTagStatus(reqVO.getIds(), 0);
        return success(true);
    }

    @PutMapping("/enable")
    @Operation(summary = "启用会员标签")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-tag:enable')")
    public CommonResult<Boolean> enableMemberTag(@Valid @RequestBody MemberTagStatusReqVO reqVO) {
        memberTagService.updateTagStatus(reqVO.getIds(), 1);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得会员标签")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-tag:query')")
    public CommonResult<MemberTagRespVO> getMemberTag(@RequestParam("id") Long id) {
        MemberTagDO memberTag = memberTagService.getMemberTag(id);
        return success(BeanUtils.toBean(memberTag, MemberTagRespVO.class));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会员标签")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-tag:update')")
    public CommonResult<Boolean> updateMemberTag(@Valid @RequestBody MemberTagSaveReqVO updateReqVO) {
        memberTagService.updateMemberTag(updateReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "会员标签统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-tag:query')")
    public CommonResult<MemberTagChartRespVO> getChart(@RequestParam(required = false) String timeRange) {
        // 1. 饼图：统计每个标签被多少用户绑定（使用 FIND_IN_SET 关联）
        String pieSql = """
        SELECT t.name AS type, COUNT(DISTINCT u.id) AS count
        FROM member_tag t
        LEFT JOIN member_user u ON FIND_IN_SET(t.id, u.tag_ids) > 0 AND u.deleted = 0
        WHERE t.deleted = 0
        GROUP BY t.id
        ORDER BY count DESC
        """;
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(pieSql);
        List<MemberTagChartRespVO.TagDistributionVO> distribution = rows.stream()
                .map(row -> {
                    MemberTagChartRespVO.TagDistributionVO vo = new MemberTagChartRespVO.TagDistributionVO();
                    vo.setType((String) row.get("type"));
                    vo.setCount(((Number) row.get("count")).intValue());
                    return vo;
                }).collect(Collectors.toList());

        // 2. 标签总数（未删除的标签）
        String tagCountSql = "SELECT COUNT(*) FROM member_tag WHERE deleted = 0";
        Long tagCount = jdbcTemplate.queryForObject(tagCountSql, Long.class);

        // 3. 标签用户数：至少绑定一个标签的用户数（tag_ids 不为空且不为空字符串）
        String userCountSql = "SELECT COUNT(*) FROM member_user WHERE deleted = 0 AND tag_ids IS NOT NULL AND tag_ids != ''";
        Long tagUserCount = jdbcTemplate.queryForObject(userCountSql, Long.class);

        // 4. 组装响应
        MemberTagChartRespVO respVO = new MemberTagChartRespVO();
        respVO.setTagDistribution(distribution);
        respVO.setTagCount(tagCount != null ? tagCount.intValue() : 0);
        respVO.setTagUserCount(tagUserCount != null ? tagUserCount.intValue() : 0);
        return CommonResult.success(respVO);
    }

//    @DeleteMapping("/delete")
//    @Operation(summary = "删除会员标签")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:member-tag:delete')")
//    public CommonResult<Boolean> deleteMemberTag(@RequestParam("id") Long id) {
//        memberTagService.deleteMemberTag(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除会员标签")
//                @PreAuthorize("@ss.hasPermission('usermerchant:member-tag:delete')")
//    public CommonResult<Boolean> deleteMemberTagList(@RequestParam("ids") List<Long> ids) {
//        memberTagService.deleteMemberTagListByIds(ids);
//        return success(true);
//    }

}