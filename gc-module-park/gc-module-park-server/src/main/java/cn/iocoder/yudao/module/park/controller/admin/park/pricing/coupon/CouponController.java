package cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.coupon.CouponDO;
import cn.iocoder.yudao.module.park.service.park.pricing.coupon.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "漳州停车管理后台 - 优惠券")
@RestController
@RequestMapping("/park/coupon")
@Validated
public class CouponController {

    @Resource
    private CouponService couponService;


    // 优惠券优惠金额预览（只做校验 + 试算，不落库、不加锁）（取代calculateDiscount）
    @PostMapping("/preview-discount")
    @Operation(summary = "（暂时不用）优惠券优惠金额预览")
    @PreAuthorize("@ss.hasPermission('park:coupon:preview-discount')")
    public CommonResult<PreviewCouponDiscountRespVO> previewCouponDiscount(
            @RequestBody PreviewCouponDiscountReqVO req) {

        PreviewCouponDiscountRespVO respVO =
                couponService.previewCouponDiscount(req);

        return success(respVO);
    }

    //优惠计算（只负责用优惠规则计算，不负责校验该优惠券是否生效）TODO 可后续把校验提取为公共方法，计算优惠也用。
    @PostMapping("/calculate-discount")
    @Operation(summary = "优惠计算")
    @PreAuthorize("@ss.hasPermission('park:coupon:calculate-discount')")
    public CommonResult<CalculateDiscountRespVO> calculateDiscount(@RequestBody CalculateDiscountReqVO req){
        CalculateDiscountRespVO respVO = couponService.calculateDiscount(req);
        return success(respVO);
    }
    @PostMapping("/pay-available-list")
    @Operation(summary = "获取支付可用优惠券列表")
    @PreAuthorize("@ss.hasPermission('park:coupon:pay-available-list')")
    public CommonResult<PageResult<CouponRespVO>> listPayAvailableCoupon(@RequestBody ListPayAvailableCouponReqVO req){
        PageResult<CouponDO> pageResult = couponService.listPayAvailableCoupon(req);
        return success(BeanUtils.toBean(pageResult, CouponRespVO.class));
    }


    @PostMapping("/create")
    @Operation(summary = "创建优惠券")
    @PreAuthorize("@ss.hasPermission('park:coupon:create')")
    public CommonResult<Long> createCoupon(@Valid @RequestBody CouponSaveReqVO createReqVO) {
        return success(couponService.createCoupon(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新优惠券")
    @PreAuthorize("@ss.hasPermission('park:coupon:update')")
    public CommonResult<Boolean> updateCoupon(@Valid @RequestBody CouponSaveReqVO updateReqVO) {
        couponService.updateCoupon(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除优惠券")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:coupon:delete')")
    public CommonResult<Boolean> deleteCoupon(@RequestParam("id") Long id) {
        couponService.deleteCoupon(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得优惠券")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:coupon:query')")
    public CommonResult<CouponRespVO> getCoupon(@RequestParam("id") Long id) {
        CouponDO coupon = couponService.getCoupon(id);
        return success(BeanUtils.toBean(coupon, CouponRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得优惠券分页")
    @PreAuthorize("@ss.hasPermission('park:coupon:query')")
    public CommonResult<PageResult<CouponRespVO>> getCouponPage(@Valid CouponPageReqVO pageReqVO) {
        PageResult<CouponDO> pageResult = couponService.getCouponPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CouponRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出优惠券 Excel")
    @PreAuthorize("@ss.hasPermission('park:coupon:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCouponExcel(@Valid CouponPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CouponDO> list = couponService.getCouponPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "优惠券.xls", "数据", CouponRespVO.class,
                        BeanUtils.toBean(list, CouponRespVO.class));
    }

}
