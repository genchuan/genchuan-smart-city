package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkpay;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkpay.vo.*;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkpay.ParkPayDO;
import cn.iocoder.yudao.module.industry.service.park.pay.parkpay.ParkPayService;
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



@Tag(name = "停车管理-缴费服务")
@RestController
@RequestMapping("/industry/park-pay")
@Validated
public class ParkPayController {

    @Resource
    private ParkPayService parkPayService;

    @PostMapping("/pay")
    @Operation(summary = "停车缴费支付")
    @PreAuthorize("@ss.hasPermission('industry:park-pay:pay')")
    public CommonResult<Boolean> pay(@Valid @RequestBody ParkPayPayReqVO reqVO) {
        parkPayService.pay(reqVO);
        return success(true);
    }

    @PostMapping("/create")
    @Operation(summary = "创建停车缴费服务")
    @PreAuthorize("@ss.hasPermission('industry:park-pay:create')")
    public CommonResult<Long> createParkPay(@Valid @RequestBody ParkPaySaveReqVO createReqVO) {
        return success(parkPayService.createParkPay(createReqVO));
    }

    //即入场
    @PostMapping("/add")
    @Operation(summary = "新增停车缴费服务（前端暂时不要用这个）")
    @PreAuthorize("@ss.hasPermission('industry:park-pay:add')")
    public CommonResult<Long> addParkPay(@Valid @RequestBody ParkPayAddReqVO addReqVO) {
        return success(parkPayService.addParkPay(addReqVO));
    }

    // 出场
    @PostMapping("/exit")
    @Operation(summary = "出场（前端暂时不要用这个）")
    @PreAuthorize("@ss.hasPermission('industry:park-pay:exit')")
    public CommonResult<Boolean> exit(@Valid @RequestBody ParkPayExitReqVO exitReqVO) {
        parkPayService.exitParkPay(exitReqVO);
        return success(true);
    }

    @PutMapping("/update")
    @Operation(summary = "更新停车缴费服务")
    @PreAuthorize("@ss.hasPermission('industry:park-pay:update')")
    public CommonResult<Boolean> updateParkPay(@Valid @RequestBody ParkPaySaveReqVO updateReqVO) {
        parkPayService.updateParkPay(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除停车缴费服务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-pay:delete')")
    public CommonResult<Boolean> deleteParkPay(@RequestParam("id") Long id) {
        parkPayService.deleteParkPay(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得停车缴费服务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-pay:query')")
    public CommonResult<ParkPayRespVO> getParkPay(@RequestParam("id") Long id) {
        ParkPayDO parkPay = parkPayService.getParkPay(id);
        return success(BeanUtils.toBean(parkPay, ParkPayRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得停车缴费服务分页")
    @PreAuthorize("@ss.hasPermission('industry:park-pay:query')")
    public CommonResult<PageResult<ParkPayRespVO>> getParkPayPage(@Valid ParkPayPageReqVO pageReqVO) {
        PageResult<ParkPayDO> pageResult = parkPayService.getParkPayPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkPayRespVO.class));
    }

    @GetMapping("/drill")
    @Operation(summary = "钻取停车缴费订单")
    @PreAuthorize("@ss.hasPermission('industry:park-pay:query')")
    public CommonResult<PageResult<ParkPayRespVO>> drillParkPay(@Valid ParkPayDrillReqVO drillReqVO) {
        // drillReqVO 包含 drill 条件，例如 region、status、startTime、endTime、pageNo、pageSize
        PageResult<ParkPayDO> pageResult = parkPayService.drillParkPay(drillReqVO);
        return success(BeanUtils.toBean(pageResult, ParkPayRespVO.class));
    }


    @GetMapping("/export-excel")
    @Operation(summary = "导出停车缴费服务 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-pay:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkPayExcel(@Valid ParkPayPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkPayDO> list = parkPayService.getParkPayPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "停车缴费服务.xls", "数据", ParkPayRespVO.class,
                        BeanUtils.toBean(list, ParkPayRespVO.class));
    }

}
