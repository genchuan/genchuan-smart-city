package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderperiod;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderescape.vo.ParkOrderEscapePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderperiod.vo.ParkOrderPeriodPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderperiod.vo.ParkOrderPeriodRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderperiod.vo.ParkOrderPeriodSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderperiod.ParkOrderPeriodDO;
import cn.iocoder.yudao.module.industry.service.park.order.parkorderperiod.ParkOrderPeriodService;
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



@Tag(name = "停车管理-订单 - 期卡订单")
@RestController
@RequestMapping("/industry/park-order-period")
@Validated
public class ParkOrderPeriodController {

    @Resource
    private ParkOrderPeriodService parkOrderPeriodService;

    @PostMapping("/create")
    @Operation(summary = "创建期卡订单")
    @PreAuthorize("@ss.hasPermission('industry:park-order-period:create')")
    public CommonResult<Long> createParkOrderPeriod(@Valid @RequestBody ParkOrderPeriodSaveReqVO createReqVO) {
        return success(parkOrderPeriodService.createParkOrderPeriod(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新期卡订单")
    @PreAuthorize("@ss.hasPermission('industry:park-order-period:update')")
    public CommonResult<Boolean> updateParkOrderPeriod(@Valid @RequestBody ParkOrderPeriodSaveReqVO updateReqVO) {
        parkOrderPeriodService.updateParkOrderPeriod(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除期卡订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-order-period:delete')")
    public CommonResult<Boolean> deleteParkOrderPeriod(@RequestParam("id") Long id) {
        parkOrderPeriodService.deleteParkOrderPeriod(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得期卡订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-order-period:query')")
    public CommonResult<ParkOrderPeriodRespVO> getParkOrderPeriod(@RequestParam("id") Long id) {
        ParkOrderPeriodDO parkOrderPeriod = parkOrderPeriodService.getParkOrderPeriod(id);
        return success(BeanUtils.toBean(parkOrderPeriod, ParkOrderPeriodRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得期卡订单分页")
    @PreAuthorize("@ss.hasPermission('industry:park-order-period:query')")
    public CommonResult<PageResult<ParkOrderPeriodRespVO>> getParkOrderPeriodPage(@Valid ParkOrderPeriodPageReqVO pageReqVO) {
        PageResult<ParkOrderPeriodDO> pageResult = parkOrderPeriodService.getParkOrderPeriodPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkOrderPeriodRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出期卡订单 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-order-period:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkOrderPeriodExcel(@Valid ParkOrderPeriodPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkOrderPeriodDO> list = parkOrderPeriodService.getParkOrderPeriodPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "期卡订单.xls", "数据", ParkOrderPeriodRespVO.class,
                        BeanUtils.toBean(list, ParkOrderPeriodRespVO.class));
    }

}
