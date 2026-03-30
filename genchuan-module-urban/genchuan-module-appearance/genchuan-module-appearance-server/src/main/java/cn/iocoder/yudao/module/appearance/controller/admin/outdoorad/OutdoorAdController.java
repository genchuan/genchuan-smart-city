package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad;

import cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo.*;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad.OutdoorAdDO;
import cn.iocoder.yudao.module.appearance.service.outdoorad.OutdoorAdService;

@Tag(name = "管理后台 - 户外广告 - 全部")
@RestController
@RequestMapping("/appearance/outdoor-ad-spvs")
@Validated
public class OutdoorAdController {

    @Resource
    private OutdoorAdService outdoorAdService;

    @GetMapping("/page")
    @Operation(summary = "获得户外广告分页")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:query')")
    public CommonResult<PageResult<OutdoorAdPageRespVO>> getOutdoorAdPage( @Valid OutdoorAdPageReqVO pageReqVO) {
        PageResult<OutdoorAdDO> pageResult = outdoorAdService.getOutdoorAdPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OutdoorAdPageRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得户外广告")
    @Parameter(name = "id", description = "编号", required = true, example = "6")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:query')")
    public CommonResult<OutdoorAdGetRespVO> getOutdoorAd( @Valid OutdoorAdGetReqVO getReqVO) {
        OutdoorAdDO outdoorAd = outdoorAdService.getOutdoorAd(getReqVO);
        return success(BeanUtils.toBean(outdoorAd, OutdoorAdGetRespVO.class));
    }

    @PostMapping("/add")
    @Operation(summary = "新增户外广告")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:add')")
    public CommonResult<OutdoorAdAddRespVO> addOutdoorAd(@Valid OutdoorAdAddReqVO addReqVO) {
        OutdoorAdDO outdoorAd = outdoorAdService.addOutdoorAd(addReqVO);
        return success(BeanUtils.toBean(outdoorAd, OutdoorAdAddRespVO.class));
    }

    @PostMapping("/edit")
    @Operation(summary = "更新户外广告")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:edit')")
    public CommonResult<Boolean> editOutdoorAd(@Valid OutdoorAdEditReqVO editReqVO) {
        return success(outdoorAdService.editOutdoorAd(editReqVO));
    }
}