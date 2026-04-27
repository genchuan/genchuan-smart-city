package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantsendcoupon;

import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth.vo.PlateAuthChartReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth.vo.PlateAuthChartRespVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantsendcoupon.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantsendcoupon.MerchantSendCouponDO;
import cn.iocoder.yudao.module.usermerchant.service.merchantmgmt.merchantsendcoupon.MerchantSendCouponService;

@Tag(name = "管理后台 - 商户发券")
@RestController
@RequestMapping("/usermerchant/merchant-send-coupon")
@Validated
public class MerchantSendCouponController {

    @Resource
    private MerchantSendCouponService merchantSendCouponService;

    @GetMapping("/page")
    @Operation(summary = "获得商户发券分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-send-coupon:query')")
    public CommonResult<PageResult<MerchantSendCouponRespVO>> getMerchantSendCouponPage(@Valid MerchantSendCouponPageReqVO pageReqVO) {
        PageResult<MerchantSendCouponDO> pageResult = merchantSendCouponService.getMerchantSendCouponPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MerchantSendCouponRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出商户发券")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-send-coupon:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMerchantSendCouponExcel(@Valid MerchantSendCouponPageReqVO pageReqVO,
                                              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MerchantSendCouponDO> list = merchantSendCouponService.getMerchantSendCouponPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "商户发券.xls", "数据", MerchantSendCouponRespVO.class,
                BeanUtils.toBean(list, MerchantSendCouponRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得商户发券")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-send-coupon:query')")
    public CommonResult<MerchantSendCouponRespVO> getMerchantSendCoupon(@RequestParam("id") Long id) {
        MerchantSendCouponDO merchantSendCoupon = merchantSendCouponService.getMerchantSendCoupon(id);
        return success(BeanUtils.toBean(merchantSendCoupon, MerchantSendCouponRespVO.class));
    }

    @GetMapping("/chart")
    @Operation(summary = "商户发券统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-send-coupon:query')")
    public CommonResult<MerchantSendCouponChartRespVO> getMerchantSendCouponChart(@Valid MerchantSendCouponChartReqVO chartReqVO) {
        return success(merchantSendCouponService.getMerchantSendCouponChart(chartReqVO));
    }

//    --------------------

//    @PostMapping("/create")
//    @Operation(summary = "创建商户发券")
//    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-send-coupon:create')")
//    public CommonResult<Long> createMerchantSendCoupon(@Valid @RequestBody MerchantSendCouponSaveReqVO createReqVO) {
//        return success(merchantSendCouponService.createMerchantSendCoupon(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新商户发券")
//    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-send-coupon:update')")
//    public CommonResult<Boolean> updateMerchantSendCoupon(@Valid @RequestBody MerchantSendCouponSaveReqVO updateReqVO) {
//        merchantSendCouponService.updateMerchantSendCoupon(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除商户发券")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-send-coupon:delete')")
//    public CommonResult<Boolean> deleteMerchantSendCoupon(@RequestParam("id") Long id) {
//        merchantSendCouponService.deleteMerchantSendCoupon(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除商户发券")
//                @PreAuthorize("@ss.hasPermission('usermerchant:merchant-send-coupon:delete')")
//    public CommonResult<Boolean> deleteMerchantSendCouponList(@RequestParam("ids") List<Long> ids) {
//        merchantSendCouponService.deleteMerchantSendCouponListByIds(ids);
//        return success(true);
//    }

}