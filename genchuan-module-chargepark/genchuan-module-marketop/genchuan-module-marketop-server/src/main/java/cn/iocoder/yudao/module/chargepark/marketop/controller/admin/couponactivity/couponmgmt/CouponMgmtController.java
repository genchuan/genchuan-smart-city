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
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
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

        couponMgmtService.send(reqVO.getId(), reqVO.getReceiverId());
        return CommonResult.success(true);
    }

    @PutMapping("/verify")
    @Operation(summary = "核销优惠券")
    @PreAuthorize("@ss.hasPermission('marketop:coupon-mgmt:verify')")
    public CommonResult<Boolean> verify(@Valid @RequestBody IdReq req) {
        couponMgmtService.verify(req.getId());
        return CommonResult.success(true);
    }

    @PutMapping("/resend")
    @Operation(summary = "重新发放优惠券")
    @PreAuthorize("@ss.hasPermission('marketop:coupon-mgmt:send')")
    public CommonResult<Boolean> resend(@Valid @RequestBody CouponMgmtResendReqVO reqVO) {
        couponMgmtService.resend(reqVO.getId(), reqVO.getReceiverId(), reqVO.getNewValidTime());
        return CommonResult.success(true);
    }

    @GetMapping("/get-import-template")
    @Operation(summary = "获得导入优惠券模板")
    public void importTemplate(HttpServletResponse response) throws IOException {
        List<CouponMgmtImportExcelVO> list = Arrays.asList(
                CouponMgmtImportExcelVO.builder().name("新用户满减券").type("满减")
                        .amount(new java.math.BigDecimal("20.00")).useCondition("满100元可用")
                        .stationIds("1,2,3").description("新用户注册发放的满减优惠券").build(),
                CouponMgmtImportExcelVO.builder().name("节假日折扣券").type("折扣")
                        .amount(new java.math.BigDecimal("15.00")).useCondition("满50元可用")
                        .stationIds("1,2").description("节假日活动折扣券").build()
        );
        ExcelUtils.write(response, "优惠券导入模板.xls", "优惠券列表", CouponMgmtImportExcelVO.class, list);
    }

    @PostMapping("/import")
    @Operation(summary = "导入优惠券")
    @PreAuthorize("@ss.hasPermission('marketop:coupon-mgmt:import')")
    public CommonResult<Boolean> importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        List<CouponMgmtImportExcelVO> list = ExcelUtils.read(file, CouponMgmtImportExcelVO.class);
        couponMgmtService.importCouponMgmtList(list);
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
    public CommonResult<CouponMgmtChartRespVO> getChart(
            @RequestParam(value = "startTime", required = false) Long startTime,
            @RequestParam(value = "endTime", required = false) Long endTime,
            @RequestParam(value = "stationId", required = false) Long stationId) {
        return CommonResult.success(couponMgmtService.getChart(startTime, endTime, stationId));
    }

    @Data
    public static class IdReq {
        @NotNull(message = "id不能为空")
        private Long id;
    }

}
