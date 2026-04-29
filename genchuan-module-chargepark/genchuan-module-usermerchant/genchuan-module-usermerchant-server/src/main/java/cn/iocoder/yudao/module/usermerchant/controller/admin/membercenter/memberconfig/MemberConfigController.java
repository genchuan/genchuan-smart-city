package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig;

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

import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberconfig.MemberConfigDO;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.memberconfig.MemberConfigService;

@Tag(name = "管理后台 - 会员配置")
@RestController
@RequestMapping("/usermerchant/member-config")
@Validated
public class MemberConfigController {

    @Resource
    private MemberConfigService memberConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建会员配置")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:create')")
    public CommonResult<Long> createMemberConfig(@Valid @RequestBody MemberConfigSaveReqVO createReqVO) {
        return success(memberConfigService.createMemberConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会员配置")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:update')")
    public CommonResult<Boolean> updateMemberConfig(@Valid @RequestBody MemberConfigSaveReqVO updateReqVO) {
        memberConfigService.updateMemberConfig(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除会员配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:delete')")
    public CommonResult<Boolean> deleteMemberConfig(@RequestParam("id") Long id) {
        memberConfigService.deleteMemberConfig(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除会员配置")
                @PreAuthorize("@ss.hasPermission('usermerchant:member-config:delete')")
    public CommonResult<Boolean> deleteMemberConfigList(@RequestParam("ids") List<Long> ids) {
        memberConfigService.deleteMemberConfigListByIds(ids);
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

    @GetMapping("/page")
    @Operation(summary = "获得会员配置分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:query')")
    public CommonResult<PageResult<MemberConfigRespVO>> getMemberConfigPage(@Valid MemberConfigPageReqVO pageReqVO) {
        PageResult<MemberConfigDO> pageResult = memberConfigService.getMemberConfigPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MemberConfigRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出会员配置 Excel")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMemberConfigExcel(@Valid MemberConfigPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MemberConfigDO> list = memberConfigService.getMemberConfigPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "会员配置.xls", "数据", MemberConfigRespVO.class,
                        BeanUtils.toBean(list, MemberConfigRespVO.class));
    }

}