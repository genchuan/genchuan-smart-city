package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membertag;

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
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membertag.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membertag.MemberTagDO;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.membertag.MemberTagService;

@Tag(name = "管理后台 - 会员标签")
@RestController
@RequestMapping("/usermerchant/member-tag")
@Validated
public class MemberTagController {

    @Resource
    private MemberTagService memberTagService;

    @PostMapping("/create")
    @Operation(summary = "创建会员标签")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-tag:create')")
    public CommonResult<Long> createMemberTag(@Valid @RequestBody MemberTagSaveReqVO createReqVO) {
        return success(memberTagService.createMemberTag(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会员标签")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-tag:update')")
    public CommonResult<Boolean> updateMemberTag(@Valid @RequestBody MemberTagSaveReqVO updateReqVO) {
        memberTagService.updateMemberTag(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除会员标签")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('usermerchant:member-tag:delete')")
    public CommonResult<Boolean> deleteMemberTag(@RequestParam("id") Long id) {
        memberTagService.deleteMemberTag(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除会员标签")
                @PreAuthorize("@ss.hasPermission('usermerchant:member-tag:delete')")
    public CommonResult<Boolean> deleteMemberTagList(@RequestParam("ids") List<Long> ids) {
        memberTagService.deleteMemberTagListByIds(ids);
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

    @GetMapping("/page")
    @Operation(summary = "获得会员标签分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-tag:query')")
    public CommonResult<PageResult<MemberTagRespVO>> getMemberTagPage(@Valid MemberTagPageReqVO pageReqVO) {
        PageResult<MemberTagDO> pageResult = memberTagService.getMemberTagPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MemberTagRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出会员标签 Excel")
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

}