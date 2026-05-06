package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberpoint;

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

import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberpoint.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberpoint.MemberPointDO;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.memberpoint.MemberPointService;

@Tag(name = "管理后台 - 会员积分")
@RestController
@RequestMapping("/usermerchant/member-point")
@Validated
public class MemberPointController {

    @Resource
    private MemberPointService memberPointService;

    @PostMapping("/create")
    @Operation(summary = "创建会员积分")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-point:create')")
    public CommonResult<Long> createMemberPoint(@Valid @RequestBody MemberPointSaveReqVO createReqVO) {
        return success(memberPointService.createMemberPoint(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会员积分")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-point:update')")
    public CommonResult<Boolean> updateMemberPoint(@Valid @RequestBody MemberPointSaveReqVO updateReqVO) {
        memberPointService.updateMemberPoint(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除会员积分")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('usermerchant:member-point:delete')")
    public CommonResult<Boolean> deleteMemberPoint(@RequestParam("id") Long id) {
        memberPointService.deleteMemberPoint(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除会员积分")
                @PreAuthorize("@ss.hasPermission('usermerchant:member-point:delete')")
    public CommonResult<Boolean> deleteMemberPointList(@RequestParam("ids") List<Long> ids) {
        memberPointService.deleteMemberPointListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得会员积分")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-point:query')")
    public CommonResult<MemberPointRespVO> getMemberPoint(@RequestParam("id") Long id) {
        MemberPointDO memberPoint = memberPointService.getMemberPoint(id);
        return success(BeanUtils.toBean(memberPoint, MemberPointRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得会员积分分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-point:query')")
    public CommonResult<PageResult<MemberPointRespVO>> getMemberPointPage(@Valid MemberPointPageReqVO pageReqVO) {
        PageResult<MemberPointDO> pageResult = memberPointService.getMemberPointPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MemberPointRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出会员积分 Excel")
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

}