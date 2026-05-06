package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membersign;

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

import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membersign.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membersign.MemberSignDO;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.membersign.MemberSignService;

@Tag(name = "管理后台 - 会员签到")
@RestController
@RequestMapping("/usermerchant/member-sign")
@Validated
public class MemberSignController {

    @Resource
    private MemberSignService memberSignService;

    @PostMapping("/create")
    @Operation(summary = "创建会员签到")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-sign:create')")
    public CommonResult<Long> createMemberSign(@Valid @RequestBody MemberSignSaveReqVO createReqVO) {
        return success(memberSignService.createMemberSign(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会员签到")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-sign:update')")
    public CommonResult<Boolean> updateMemberSign(@Valid @RequestBody MemberSignSaveReqVO updateReqVO) {
        memberSignService.updateMemberSign(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除会员签到")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('usermerchant:member-sign:delete')")
    public CommonResult<Boolean> deleteMemberSign(@RequestParam("id") Long id) {
        memberSignService.deleteMemberSign(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除会员签到")
                @PreAuthorize("@ss.hasPermission('usermerchant:member-sign:delete')")
    public CommonResult<Boolean> deleteMemberSignList(@RequestParam("ids") List<Long> ids) {
        memberSignService.deleteMemberSignListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得会员签到")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-sign:query')")
    public CommonResult<MemberSignRespVO> getMemberSign(@RequestParam("id") Long id) {
        MemberSignDO memberSign = memberSignService.getMemberSign(id);
        return success(BeanUtils.toBean(memberSign, MemberSignRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得会员签到分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-sign:query')")
    public CommonResult<PageResult<MemberSignRespVO>> getMemberSignPage(@Valid MemberSignPageReqVO pageReqVO) {
        PageResult<MemberSignDO> pageResult = memberSignService.getMemberSignPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MemberSignRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出会员签到 Excel")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-sign:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMemberSignExcel(@Valid MemberSignPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MemberSignDO> list = memberSignService.getMemberSignPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "会员签到.xls", "数据", MemberSignRespVO.class,
                        BeanUtils.toBean(list, MemberSignRespVO.class));
    }

}