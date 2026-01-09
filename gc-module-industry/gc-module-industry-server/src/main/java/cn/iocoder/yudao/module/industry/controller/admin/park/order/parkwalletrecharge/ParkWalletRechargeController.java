package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkwalletrecharge;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkwalletrecharge.vo.ParkWalletRechargePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkwalletrecharge.vo.ParkWalletRechargeRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkwalletrecharge.vo.ParkWalletRechargeSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkwalletrecharge.ParkWalletRechargeDO;
import cn.iocoder.yudao.module.industry.service.park.order.parkwalletrecharge.ParkWalletRechargeService;
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



@Tag(name = "管理后台 - 钱包充值")
@RestController
@RequestMapping("/industry/park-wallet-recharge")
@Validated
public class ParkWalletRechargeController {

    @Resource
    private ParkWalletRechargeService parkWalletRechargeService;

    @PostMapping("/create")
    @Operation(summary = "创建钱包充值")
    @PreAuthorize("@ss.hasPermission('industry:park-wallet-recharge:create')")
    public CommonResult<Long> createParkWalletRecharge(@Valid @RequestBody ParkWalletRechargeSaveReqVO createReqVO) {
        return success(parkWalletRechargeService.createParkWalletRecharge(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新钱包充值")
    @PreAuthorize("@ss.hasPermission('industry:park-wallet-recharge:update')")
    public CommonResult<Boolean> updateParkWalletRecharge(@Valid @RequestBody ParkWalletRechargeSaveReqVO updateReqVO) {
        parkWalletRechargeService.updateParkWalletRecharge(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除钱包充值")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-wallet-recharge:delete')")
    public CommonResult<Boolean> deleteParkWalletRecharge(@RequestParam("id") Long id) {
        parkWalletRechargeService.deleteParkWalletRecharge(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得钱包充值")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-wallet-recharge:query')")
    public CommonResult<ParkWalletRechargeRespVO> getParkWalletRecharge(@RequestParam("id") Long id) {
        ParkWalletRechargeDO parkWalletRecharge = parkWalletRechargeService.getParkWalletRecharge(id);
        return success(BeanUtils.toBean(parkWalletRecharge, ParkWalletRechargeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得钱包充值分页")
    @PreAuthorize("@ss.hasPermission('industry:park-wallet-recharge:query')")
    public CommonResult<PageResult<ParkWalletRechargeRespVO>> getParkWalletRechargePage(@Valid ParkWalletRechargePageReqVO pageReqVO) {
        PageResult<ParkWalletRechargeDO> pageResult = parkWalletRechargeService.getParkWalletRechargePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkWalletRechargeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出钱包充值 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-wallet-recharge:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkWalletRechargeExcel(@Valid ParkWalletRechargePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkWalletRechargeDO> list = parkWalletRechargeService.getParkWalletRechargePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "钱包充值.xls", "数据", ParkWalletRechargeRespVO.class,
                        BeanUtils.toBean(list, ParkWalletRechargeRespVO.class));
    }

}
