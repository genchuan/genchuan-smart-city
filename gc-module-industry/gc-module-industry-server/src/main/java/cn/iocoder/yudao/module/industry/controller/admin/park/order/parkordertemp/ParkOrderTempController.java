package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkordertemp;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkordertemp.vo.ParkOrderTempPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkordertemp.vo.ParkOrderTempRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkordertemp.vo.ParkOrderTempSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkordertemp.ParkOrderTempDO;
import cn.iocoder.yudao.module.industry.service.park.order.parkordertemp.ParkOrderTempService;
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



@Tag(name = "漳州停车管理 - 临停订单")
@RestController
@RequestMapping("/industry/park-order-temp")
@Validated
public class ParkOrderTempController {

    @Resource
    private ParkOrderTempService parkOrderTempService;

    @PostMapping("/create")
    @Operation(summary = "创建临停订单")
    @PreAuthorize("@ss.hasPermission('industry:park-order-temp:create')")
    public CommonResult<Long> createParkOrderTemp(@Valid @RequestBody ParkOrderTempSaveReqVO createReqVO) {
        return success(parkOrderTempService.createParkOrderTemp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新临停订单")
    @PreAuthorize("@ss.hasPermission('industry:park-order-temp:update')")
    public CommonResult<Boolean> updateParkOrderTemp(@Valid @RequestBody ParkOrderTempSaveReqVO updateReqVO) {
        parkOrderTempService.updateParkOrderTemp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除临停订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-order-temp:delete')")
    public CommonResult<Boolean> deleteParkOrderTemp(@RequestParam("id") Long id) {
        parkOrderTempService.deleteParkOrderTemp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得临停订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-order-temp:query')")
    public CommonResult<ParkOrderTempRespVO> getParkOrderTemp(@RequestParam("id") Long id) {
        ParkOrderTempDO parkOrderTemp = parkOrderTempService.getParkOrderTemp(id);
        return success(BeanUtils.toBean(parkOrderTemp, ParkOrderTempRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得临停订单分页")
    @PreAuthorize("@ss.hasPermission('industry:park-order-temp:query')")
    public CommonResult<PageResult<ParkOrderTempRespVO>> getParkOrderTempPage(@Valid ParkOrderTempPageReqVO pageReqVO) {
        PageResult<ParkOrderTempDO> pageResult = parkOrderTempService.getParkOrderTempPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkOrderTempRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出临停订单 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-order-temp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkOrderTempExcel(@Valid ParkOrderTempPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkOrderTempDO> list = parkOrderTempService.getParkOrderTempPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "临停订单.xls", "数据", ParkOrderTempRespVO.class,
                        BeanUtils.toBean(list, ParkOrderTempRespVO.class));
    }

}
