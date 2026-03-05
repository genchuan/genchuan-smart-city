package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkcoupon;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkcoupon.vo.ParkCouponPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkcoupon.vo.ParkCouponRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkcoupon.vo.ParkCouponSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkcoupon.ParkCouponDO;
import cn.iocoder.yudao.module.industry.service.park.discount.parkcoupon.ParkCouponService;
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


@Tag(name = "漳州停车管理-收费优惠域 - 优惠券")
@RestController
@RequestMapping("/industry/park-coupon")
@Validated
public class ParkCouponController {

    @Resource
    private ParkCouponService parkCouponService;

    @PostMapping("/create")
    @Operation(summary = "创建优惠券")
    @PreAuthorize("@ss.hasPermission('industry:park-coupon:create')")
    public CommonResult<Long> createParkCoupon(@Valid @RequestBody ParkCouponSaveReqVO createReqVO) {
        return success(parkCouponService.createParkCoupon(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新优惠券")
    @PreAuthorize("@ss.hasPermission('industry:park-coupon:update')")
    public CommonResult<Boolean> updateParkCoupon(@Valid @RequestBody ParkCouponSaveReqVO updateReqVO) {
        parkCouponService.updateParkCoupon(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除优惠券")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-coupon:delete')")
    public CommonResult<Boolean> deleteParkCoupon(@RequestParam("id") Long id) {
        parkCouponService.deleteParkCoupon(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得优惠券")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-coupon:query')")
    public CommonResult<ParkCouponRespVO> getParkCoupon(@RequestParam("id") Long id) {
        ParkCouponDO parkCoupon = parkCouponService.getParkCoupon(id);
        return success(BeanUtils.toBean(parkCoupon, ParkCouponRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得优惠券分页")
    @PreAuthorize("@ss.hasPermission('industry:park-coupon:query')")
    public CommonResult<PageResult<ParkCouponRespVO>> getParkCouponPage(@Valid ParkCouponPageReqVO pageReqVO) {
        PageResult<ParkCouponDO> pageResult = parkCouponService.getParkCouponPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkCouponRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出优惠券 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-coupon:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkCouponExcel(@Valid ParkCouponPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkCouponDO> list = parkCouponService.getParkCouponPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "优惠券.xls", "数据", ParkCouponRespVO.class,
                        BeanUtils.toBean(list, ParkCouponRespVO.class));
    }

}
