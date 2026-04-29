package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membergroup;

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

import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membergroup.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membergroup.MemberGroupDO;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.membergroup.MemberGroupService;

@Tag(name = "管理后台 - 会员分组")
@RestController
@RequestMapping("/usermerchant/member-group")
@Validated
public class MemberGroupController {

    @Resource
    private MemberGroupService memberGroupService;

    @PostMapping("/create")
    @Operation(summary = "创建会员分组")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-group:create')")
    public CommonResult<Long> createMemberGroup(@Valid @RequestBody MemberGroupSaveReqVO createReqVO) {
        return success(memberGroupService.createMemberGroup(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会员分组")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-group:update')")
    public CommonResult<Boolean> updateMemberGroup(@Valid @RequestBody MemberGroupSaveReqVO updateReqVO) {
        memberGroupService.updateMemberGroup(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除会员分组")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('usermerchant:member-group:delete')")
    public CommonResult<Boolean> deleteMemberGroup(@RequestParam("id") Long id) {
        memberGroupService.deleteMemberGroup(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除会员分组")
                @PreAuthorize("@ss.hasPermission('usermerchant:member-group:delete')")
    public CommonResult<Boolean> deleteMemberGroupList(@RequestParam("ids") List<Long> ids) {
        memberGroupService.deleteMemberGroupListByIds(ids);
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

    @GetMapping("/page")
    @Operation(summary = "获得会员分组分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-group:query')")
    public CommonResult<PageResult<MemberGroupRespVO>> getMemberGroupPage(@Valid MemberGroupPageReqVO pageReqVO) {
        PageResult<MemberGroupDO> pageResult = memberGroupService.getMemberGroupPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MemberGroupRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出会员分组 Excel")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-group:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMemberGroupExcel(@Valid MemberGroupPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MemberGroupDO> list = memberGroupService.getMemberGroupPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "会员分组.xls", "数据", MemberGroupRespVO.class,
                        BeanUtils.toBean(list, MemberGroupRespVO.class));
    }

}