package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkchargefee;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkchargefee.vo.ParkChargeFeePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkchargefee.vo.ParkChargeFeeRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkchargefee.vo.ParkChargeFeeSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkchargefee.ParkChargeFeeDO;
import cn.iocoder.yudao.module.industry.service.park.discount.parkchargefee.ParkChargeFeeService;
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


@Tag(name = "漳州停车管理-收费优惠域 - 充电收费")
@RestController
@RequestMapping("/industry/park-charge-fee")
@Validated
public class ParkChargeFeeController {

    @Resource
    private ParkChargeFeeService parkChargeFeeService;

    @PostMapping("/create")
    @Operation(summary = "创建充电收费")
    @PreAuthorize("@ss.hasPermission('industry:park-charge-fee:create')")
    public CommonResult<Long> createParkChargeFee(@Valid @RequestBody ParkChargeFeeSaveReqVO createReqVO) {
        return success(parkChargeFeeService.createParkChargeFee(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新充电收费")
    @PreAuthorize("@ss.hasPermission('industry:park-charge-fee:update')")
    public CommonResult<Boolean> updateParkChargeFee(@Valid @RequestBody ParkChargeFeeSaveReqVO updateReqVO) {
        parkChargeFeeService.updateParkChargeFee(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除充电收费")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-charge-fee:delete')")
    public CommonResult<Boolean> deleteParkChargeFee(@RequestParam("id") Long id) {
        parkChargeFeeService.deleteParkChargeFee(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得充电收费")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-charge-fee:query')")
    public CommonResult<ParkChargeFeeRespVO> getParkChargeFee(@RequestParam("id") Long id) {
        ParkChargeFeeDO parkChargeFee = parkChargeFeeService.getParkChargeFee(id);
        return success(BeanUtils.toBean(parkChargeFee, ParkChargeFeeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得充电收费分页")
    @PreAuthorize("@ss.hasPermission('industry:park-charge-fee:query')")
    public CommonResult<PageResult<ParkChargeFeeRespVO>> getParkChargeFeePage(@Valid ParkChargeFeePageReqVO pageReqVO) {
        PageResult<ParkChargeFeeDO> pageResult = parkChargeFeeService.getParkChargeFeePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkChargeFeeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出充电收费 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-charge-fee:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkChargeFeeExcel(@Valid ParkChargeFeePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkChargeFeeDO> list = parkChargeFeeService.getParkChargeFeePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "充电收费.xls", "数据", ParkChargeFeeRespVO.class,
                        BeanUtils.toBean(list, ParkChargeFeeRespVO.class));
    }

}
