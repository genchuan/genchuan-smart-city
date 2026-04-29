package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel;

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

import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberlevel.MemberLevelDO;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.memberlevel.MemberLevelService;

@Tag(name = "管理后台 - 会员等级")
@RestController
@RequestMapping("/usermerchant/member-level")
@Validated
public class MemberLevelController {

    @Resource
    private MemberLevelService memberLevelService;

    @PostMapping("/create")
    @Operation(summary = "创建会员等级")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-level:create')")
    public CommonResult<Long> createMemberLevel(@Valid @RequestBody MemberLevelSaveReqVO createReqVO) {
        return success(memberLevelService.createMemberLevel(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会员等级")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-level:update')")
    public CommonResult<Boolean> updateMemberLevel(@Valid @RequestBody MemberLevelSaveReqVO updateReqVO) {
        memberLevelService.updateMemberLevel(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除会员等级")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('usermerchant:member-level:delete')")
    public CommonResult<Boolean> deleteMemberLevel(@RequestParam("id") Long id) {
        memberLevelService.deleteMemberLevel(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除会员等级")
                @PreAuthorize("@ss.hasPermission('usermerchant:member-level:delete')")
    public CommonResult<Boolean> deleteMemberLevelList(@RequestParam("ids") List<Long> ids) {
        memberLevelService.deleteMemberLevelListByIds(ids);
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

    @GetMapping("/page")
    @Operation(summary = "获得会员等级分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-level:query')")
    public CommonResult<PageResult<MemberLevelRespVO>> getMemberLevelPage(@Valid MemberLevelPageReqVO pageReqVO) {
        PageResult<MemberLevelDO> pageResult = memberLevelService.getMemberLevelPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MemberLevelRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出会员等级 Excel")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-level:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMemberLevelExcel(@Valid MemberLevelPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MemberLevelDO> list = memberLevelService.getMemberLevelPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "会员等级.xls", "数据", MemberLevelRespVO.class,
                        BeanUtils.toBean(list, MemberLevelRespVO.class));
    }

}