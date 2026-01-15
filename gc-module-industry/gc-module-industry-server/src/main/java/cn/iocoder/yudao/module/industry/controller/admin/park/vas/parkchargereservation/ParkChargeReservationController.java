package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkchargereservation;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkchargereservation.vo.ParkChargeReservationPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkchargereservation.vo.ParkChargeReservationRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkchargereservation.vo.ParkChargeReservationSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkchargereservation.ParkChargeReservationDO;
import cn.iocoder.yudao.module.industry.service.park.vas.parkchargereservation.ParkChargeReservationService;
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


@Tag(name = "管理后台 - 充电预约")
@RestController
@RequestMapping("/industry/park-charge-reservation")
@Validated
public class ParkChargeReservationController {

    @Resource
    private ParkChargeReservationService parkChargeReservationService;

    @PostMapping("/create")
    @Operation(summary = "创建充电预约")
    @PreAuthorize("@ss.hasPermission('industry:park-charge-reservation:create')")
    public CommonResult<Long> createParkChargeReservation(@Valid @RequestBody ParkChargeReservationSaveReqVO createReqVO) {
        return success(parkChargeReservationService.createParkChargeReservation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新充电预约")
    @PreAuthorize("@ss.hasPermission('industry:park-charge-reservation:update')")
    public CommonResult<Boolean> updateParkChargeReservation(@Valid @RequestBody ParkChargeReservationSaveReqVO updateReqVO) {
        parkChargeReservationService.updateParkChargeReservation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除充电预约")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-charge-reservation:delete')")
    public CommonResult<Boolean> deleteParkChargeReservation(@RequestParam("id") Long id) {
        parkChargeReservationService.deleteParkChargeReservation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得充电预约")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-charge-reservation:query')")
    public CommonResult<ParkChargeReservationRespVO> getParkChargeReservation(@RequestParam("id") Long id) {
        ParkChargeReservationDO parkChargeReservation = parkChargeReservationService.getParkChargeReservation(id);
        return success(BeanUtils.toBean(parkChargeReservation, ParkChargeReservationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得充电预约分页")
    @PreAuthorize("@ss.hasPermission('industry:park-charge-reservation:query')")
    public CommonResult<PageResult<ParkChargeReservationRespVO>> getParkChargeReservationPage(@Valid ParkChargeReservationPageReqVO pageReqVO) {
        PageResult<ParkChargeReservationDO> pageResult = parkChargeReservationService.getParkChargeReservationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkChargeReservationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出充电预约 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-charge-reservation:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkChargeReservationExcel(@Valid ParkChargeReservationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkChargeReservationDO> list = parkChargeReservationService.getParkChargeReservationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "充电预约.xls", "数据", ParkChargeReservationRespVO.class,
                        BeanUtils.toBean(list, ParkChargeReservationRespVO.class));
    }

}
