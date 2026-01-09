package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreservation;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreservation.vo.ParkReservationPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreservation.vo.ParkReservationRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreservation.vo.ParkReservationSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkreservation.ParkReservationDO;
import cn.iocoder.yudao.module.industry.service.park.pay.parkreservation.ParkReservationService;
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



@Tag(name = "停车管理-预约服务")
@RestController
@RequestMapping("/industry/park-reservation")
@Validated
public class ParkReservationController {

    @Resource
    private ParkReservationService parkReservationService;

    @PostMapping("/create")
    @Operation(summary = "创建停车预约服务")
    @PreAuthorize("@ss.hasPermission('industry:park-reservation:create')")
    public CommonResult<Long> createParkReservation(@Valid @RequestBody ParkReservationSaveReqVO createReqVO) {
        return success(parkReservationService.createParkReservation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新停车预约服务")
    @PreAuthorize("@ss.hasPermission('industry:park-reservation:update')")
    public CommonResult<Boolean> updateParkReservation(@Valid @RequestBody ParkReservationSaveReqVO updateReqVO) {
        parkReservationService.updateParkReservation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除停车预约服务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-reservation:delete')")
    public CommonResult<Boolean> deleteParkReservation(@RequestParam("id") Long id) {
        parkReservationService.deleteParkReservation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得停车预约服务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-reservation:query')")
    public CommonResult<ParkReservationRespVO> getParkReservation(@RequestParam("id") Long id) {
        ParkReservationDO parkReservation = parkReservationService.getParkReservation(id);
        return success(BeanUtils.toBean(parkReservation, ParkReservationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得停车预约服务分页")
    @PreAuthorize("@ss.hasPermission('industry:park-reservation:query')")
    public CommonResult<PageResult<ParkReservationRespVO>> getParkReservationPage(@Valid ParkReservationPageReqVO pageReqVO) {
        PageResult<ParkReservationDO> pageResult = parkReservationService.getParkReservationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkReservationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出停车预约服务 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-reservation:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkReservationExcel(@Valid ParkReservationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkReservationDO> list = parkReservationService.getParkReservationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "停车预约服务.xls", "数据", ParkReservationRespVO.class,
                        BeanUtils.toBean(list, ParkReservationRespVO.class));
    }

}
