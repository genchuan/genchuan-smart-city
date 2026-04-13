package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad;

import cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo.*;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.appearance.service.outdoorad.OutdoorAdService;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 户外广告 - 全部")
@RestController
@RequestMapping("/appearance/outdoor-ad-spvs")
@Validated
public class OutdoorAdController {

    @Resource
    private OutdoorAdService outdoorAdService;

    @GetMapping("/page")
    @Operation(summary = "户外广告信息分页查询")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:query')")
    public CommonResult<PageResult<OutdoorAdPageRespVO>> page(@Valid OutdoorAdPageReqVO pageReqVO) {
        PageResult<OutdoorAdPageRespVO> pageResult = outdoorAdService.getOutdoorAdPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/get")
    @Operation(summary = "户外广告信息单条详情")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:get')")
    public CommonResult<OutdoorAdGetRespVO> get(@Valid OutdoorAdGetReqVO reqVO) {
        OutdoorAdGetRespVO respVO = outdoorAdService.getOutdoorAd(reqVO.getId());
        return success(respVO);
    }

    @PostMapping("/add")
    @Operation(summary = "户外广告信息新增")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:add')")
    public CommonResult<OutdoorAdAddRespVO> add(@Valid @RequestBody OutdoorAdAddReqVO addReqVO) {
        return success(outdoorAdService.addOutdoorAd(addReqVO));
    }

    @PostMapping("/edit")
    @Operation(summary = "户外广告信息编辑")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:edit')")
    public CommonResult<Boolean> edit(@Valid @RequestBody OutdoorAdEditReqVO editReqVO) {
        outdoorAdService.editOutdoorAd(editReqVO);
        return success(true);
    }

    @PostMapping("/remove")
    @Operation(summary = "户外广告信息删除")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:remove')")
    public CommonResult<Boolean> remove(@Valid @RequestBody OutdoorAdRemoveReqVO removeReqVO) {
        outdoorAdService.removeOutdoorAds(removeReqVO);
        return success(true);
    }

    @GetMapping("/order/page")
    @Operation(summary = "整改工单分页查询")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-order:query')")
    public CommonResult<PageResult<OutdoorAdOrderPageRespVO>> orderPage(@Valid OutdoorAdOrderPageReqVO pageReqVO) {
        PageResult<OutdoorAdOrderPageRespVO> pageResult = outdoorAdService.getOrderPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/order/get")
    @Operation(summary = "整改工单单条详情")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-order:get')")
    public CommonResult<OutdoorAdOrderGetRespVO> orderGet(@Valid OutdoorAdOrderGetReqVO reqVO) {
        OutdoorAdOrderGetRespVO respVO = outdoorAdService.getOrderDetail(reqVO.getId());
        return success(respVO);
    }



}