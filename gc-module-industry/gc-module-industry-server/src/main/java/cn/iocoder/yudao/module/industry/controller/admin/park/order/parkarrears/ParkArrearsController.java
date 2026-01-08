package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkarrears;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkarrears.vo.ParkArrearsPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkarrears.vo.ParkArrearsRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkarrears.vo.ParkArrearsSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkarrears.ParkArrearsDO;
import cn.iocoder.yudao.module.industry.service.park.order.parkarrears.ParkArrearsService;
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


@Tag(name = "管理后台 - 欠费记录")
@RestController
@RequestMapping("/industry/park-arrears")
@Validated
public class ParkArrearsController {

    @Resource
    private ParkArrearsService parkArrearsService;

    @PostMapping("/create")
    @Operation(summary = "创建欠费记录")
    @PreAuthorize("@ss.hasPermission('industry:park-arrears:create')")
    public CommonResult<Long> createParkArrears(@Valid @RequestBody ParkArrearsSaveReqVO createReqVO) {
        return success(parkArrearsService.createParkArrears(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新欠费记录")
    @PreAuthorize("@ss.hasPermission('industry:park-arrears:update')")
    public CommonResult<Boolean> updateParkArrears(@Valid @RequestBody ParkArrearsSaveReqVO updateReqVO) {
        parkArrearsService.updateParkArrears(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除欠费记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-arrears:delete')")
    public CommonResult<Boolean> deleteParkArrears(@RequestParam("id") Long id) {
        parkArrearsService.deleteParkArrears(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得欠费记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-arrears:query')")
    public CommonResult<ParkArrearsRespVO> getParkArrears(@RequestParam("id") Long id) {
        ParkArrearsDO parkArrears = parkArrearsService.getParkArrears(id);
        return success(BeanUtils.toBean(parkArrears, ParkArrearsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得欠费记录分页")
    @PreAuthorize("@ss.hasPermission('industry:park-arrears:query')")
    public CommonResult<PageResult<ParkArrearsRespVO>> getParkArrearsPage(@Valid ParkArrearsPageReqVO pageReqVO) {
        PageResult<ParkArrearsDO> pageResult = parkArrearsService.getParkArrearsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkArrearsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出欠费记录 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-arrears:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkArrearsExcel(@Valid ParkArrearsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkArrearsDO> list = parkArrearsService.getParkArrearsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "欠费记录.xls", "数据", ParkArrearsRespVO.class,
                        BeanUtils.toBean(list, ParkArrearsRespVO.class));
    }

}
