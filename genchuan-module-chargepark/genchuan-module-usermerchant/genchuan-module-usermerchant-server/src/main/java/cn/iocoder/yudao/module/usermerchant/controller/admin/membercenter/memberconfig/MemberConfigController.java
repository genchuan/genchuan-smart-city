package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo.UserInfoChartReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo.UserInfoChartRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo.UserInfoDisableReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo.UserInfoEnableReqVO;
import cn.iocoder.yudao.module.usermerchant.convert.membercenter.memberconfig.MemberConfigConvert;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberconfig.MemberConfigDO;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.memberconfig.MemberConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 会员设置")
@RestController
@RequestMapping("/member/config")
@Validated
public class MemberConfigController {

    @Resource
    private MemberConfigService memberConfigService;

    @GetMapping("/page")
    @Operation(summary = "获得会员配置分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:query')")
    public CommonResult<PageResult<MemberConfigPageRespVO>> getMemberConfigPage(@Valid MemberConfigPageReqVO pageReqVO) {
        PageResult<MemberConfigDO> pageResult = memberConfigService.getMemberConfigPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MemberConfigPageRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建会员配置")
    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:create')")
    public CommonResult<Long> createMemberConfig(@Valid @RequestBody MemberConfigSaveReqVO createReqVO) {
        return success(memberConfigService.createMemberConfig(createReqVO));
    }

    @PutMapping("/save") //需要修改，逻辑有问题
    @Operation(summary = "保存会员配置")
    @PreAuthorize("@ss.hasPermission('member:config:save')")
    public CommonResult<Boolean> saveConfig(@Valid @RequestBody MemberConfigSaveReqVO saveReqVO) {
        memberConfigService.saveConfig(saveReqVO);
        return success(true);
    }

    @PutMapping("/enable")
    @Operation(summary = "会员配置生效")
    @PreAuthorize("@ss.hasPermission('member:config:enable')")
    public CommonResult<Boolean> enableMemberConfig(@Valid @RequestBody MemberConfigStatusReqVO reqVO) {
        memberConfigService.updateConfigStatus(reqVO.getIds(), "生效");
        return success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "会员配置禁用")
    @PreAuthorize("@ss.hasPermission('member:config:disable')")
    public CommonResult<Boolean> disableMemberConfig(@Valid @RequestBody MemberConfigStatusReqVO reqVO) {
        memberConfigService.updateConfigStatus(reqVO.getIds(), "失效");
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得会员配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('member:config:query')")
    public CommonResult<MemberConfigRespVO> getMemberConfig(@RequestParam("id") Long id) {
        MemberConfigDO memberConfig = memberConfigService.getMemberConfig(id);
        return success(BeanUtils.toBean(memberConfig, MemberConfigRespVO.class));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会员配置")
    @PreAuthorize("@ss.hasPermission('member:config:update')")
    public CommonResult<Boolean> updateMemberConfig(@Valid @RequestBody MemberConfigUpdateReqVO updateReqVO) {
        memberConfigService.updateConfig(updateReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "会员配置统计")
    @PreAuthorize("@ss.hasPermission('member:config:query')")
    public CommonResult<MemberConfigChartRespVO> getMemberConfigChart(@Valid MemberConfigChartReqVO chartReqVO) {
        return success(memberConfigService.getMemberConfigChart(chartReqVO));
    }

//    @DeleteMapping("/delete")
//    @Operation(summary = "删除会员配置")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:delete')")
//    public CommonResult<Boolean> deleteMemberConfig(@RequestParam("id") Long id) {
//        memberConfigService.deleteMemberConfig(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除会员配置")
//    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:delete')")
//    public CommonResult<Boolean> deleteMemberConfigList(@RequestParam("ids") List<Long> ids) {
//        memberConfigService.deleteMemberConfigListByIds(ids);
//        return success(true);
//    }
//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出会员配置 Excel")
//    @PreAuthorize("@ss.hasPermission('usermerchant:member-config:export')")
//    @ApiAccessLog(operateType = EXPORT)
//    public void exportMemberConfigExcel(@Valid MemberConfigPageReqVO pageReqVO,
//                                        HttpServletResponse response) throws IOException {
//        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<MemberConfigDO> list = memberConfigService.getMemberConfigPage(pageReqVO).getList();
//        // 导出 Excel
//        ExcelUtils.write(response, "会员配置.xls", "数据", MemberConfigRespVO.class,
//                BeanUtils.toBean(list, MemberConfigRespVO.class));
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得会员配置")
//    @PreAuthorize("@ss.hasPermission('member:config:query')")
//    public CommonResult<MemberConfigRespVO> getConfig() {
//        MemberConfigDO config = memberConfigService.getConfig();
//        return success(MemberConfigConvert.INSTANCE.convert(config));
//    }

}
