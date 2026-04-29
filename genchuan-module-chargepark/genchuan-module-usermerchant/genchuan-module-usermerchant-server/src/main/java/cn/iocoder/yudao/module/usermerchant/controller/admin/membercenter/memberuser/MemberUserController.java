package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberuser;

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

import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberuser.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberuser.MemberUserDO;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.memberuser.MemberUserService;

@Tag(name = "管理后台 - 会员用户")
@RestController
@RequestMapping("/usermerchant/member-user")
@Validated
public class MemberUserController {

    @Resource
    private MemberUserService memberUserService;

    @PostMapping("/create")
    @Operation(summary = "创建会员用户")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-user:create')")
    public CommonResult<Long> createMemberUser(@Valid @RequestBody MemberUserSaveReqVO createReqVO) {
        return success(memberUserService.createMemberUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会员用户")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-user:update')")
    public CommonResult<Boolean> updateMemberUser(@Valid @RequestBody MemberUserSaveReqVO updateReqVO) {
        memberUserService.updateMemberUser(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除会员用户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('usermerchant:member-user:delete')")
    public CommonResult<Boolean> deleteMemberUser(@RequestParam("id") Long id) {
        memberUserService.deleteMemberUser(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除会员用户")
                @PreAuthorize("@ss.hasPermission('usermerchant:member-user:delete')")
    public CommonResult<Boolean> deleteMemberUserList(@RequestParam("ids") List<Long> ids) {
        memberUserService.deleteMemberUserListByIds(ids);
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

    @GetMapping("/page")
    @Operation(summary = "获得会员用户分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-user:query')")
    public CommonResult<PageResult<MemberUserRespVO>> getMemberUserPage(@Valid MemberUserPageReqVO pageReqVO) {
        PageResult<MemberUserDO> pageResult = memberUserService.getMemberUserPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MemberUserRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出会员用户 Excel")
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

}