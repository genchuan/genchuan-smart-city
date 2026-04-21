package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.CouponMgmtDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.couponmgmt.CouponMgmtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "管理后台 - 优惠券")
@RestController
@RequestMapping("/marketop/coupon-mgmt")
public class CouponMgmtController {

    @Resource
    private CouponMgmtService couponMgmtService;

    @GetMapping("/page")
    @Operation(summary = "获得优惠券分页")
    @PreAuthorize("@ss.hasPermission('marketop:coupon-mgmt:query')")
    public CommonResult<PageResult<CouponMgmtRespVO>> getPage(CouponMgmtPageReqVO reqVO) {
        PageResult<CouponMgmtDO> pageResult = couponMgmtService.getPage(reqVO);
        return CommonResult.success(BeanUtils.toBean(pageResult, CouponMgmtRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得优惠券详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:coupon-mgmt:query')")
    public CommonResult<CouponMgmtRespVO> get(@RequestParam("id") Long id) {
        CouponMgmtDO couponMgmt = couponMgmtService.get(id);
        return CommonResult.success(BeanUtils.toBean(couponMgmt, CouponMgmtRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建优惠券")
    @PreAuthorize("@ss.hasPermission('marketop:coupon-mgmt:create')")
    public CommonResult<Long> create(@Valid @RequestBody CouponMgmtCreateReqVO reqVO) {
        return CommonResult.success(couponMgmtService.create(reqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新优惠券")
    @PreAuthorize("@ss.hasPermission('marketop:coupon-mgmt:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody CouponMgmtUpdateReqVO reqVO) {
        couponMgmtService.update(reqVO);
        return CommonResult.success(true);
    }

    @PutMapping("/send")
    @Operation(summary = "发放优惠券")
    @PreAuthorize("@ss.hasPermission('marketop:coupon-mgmt:send')")
    public CommonResult<Boolean> send(@Valid @RequestBody CouponMgmtSendReqVO reqVO) {
        couponMgmtService.send(reqVO.getId(), reqVO.getUserIds());
        return CommonResult.success(true);
    }

    @PutMapping("/verify")
    @Operation(summary = "核销优惠券")
    @PreAuthorize("@ss.hasPermission('marketop:coupon-mgmt:verify')")
    public CommonResult<Boolean> verify(@RequestParam("id") Long id) {
        couponMgmtService.verify(id);
        return CommonResult.success(true);
    }

    @PutMapping("/resend")
    @Operation(summary = "重新发放优惠券")
    @PreAuthorize("@ss.hasPermission('marketop:coupon-mgmt:send')")
    public CommonResult<Boolean> resend(@RequestParam("id") Long id) {
        couponMgmtService.resend(id);
        return CommonResult.success(true);
    }

    @PostMapping("/import")
    @Operation(summary = "导入优惠券")
    @PreAuthorize("@ss.hasPermission('marketop:coupon-mgmt:import')")
    public CommonResult<Boolean> importExcel() {
        // TODO: 实现导入逻辑
        return CommonResult.success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出优惠券")
    @PreAuthorize("@ss.hasPermission('marketop:coupon-mgmt:query')")
    public void export(CouponMgmtPageReqVO reqVO, HttpServletResponse response) throws IOException {
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<CouponMgmtDO> pageResult = couponMgmtService.getPage(reqVO);
        List<CouponMgmtRespVO> list = BeanUtils.toBean(pageResult.getList(), CouponMgmtRespVO.class);
        ExcelUtils.write(response, "优惠券.xlsx", "数据", CouponMgmtRespVO.class, list);
    }

    @GetMapping("/chart")
    @Operation(summary = "优惠券图表统计")
    @PreAuthorize("@ss.hasPermission('marketop:coupon-mgmt:query')")
    public CommonResult<CouponMgmtChartRespVO> getChart(@RequestParam(value = "timeRange", required = false) String timeRange) {
        return CommonResult.success(couponMgmtService.getChart(timeRange));
    }

}
