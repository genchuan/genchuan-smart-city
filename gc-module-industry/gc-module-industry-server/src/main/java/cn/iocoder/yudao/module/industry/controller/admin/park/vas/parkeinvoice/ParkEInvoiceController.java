package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkeinvoice;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkeinvoice.vo.ParkEInvoicePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkeinvoice.vo.ParkEInvoiceRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkeinvoice.vo.ParkEInvoiceSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkeinvoice.ParkEInvoiceDO;
import cn.iocoder.yudao.module.industry.service.park.vas.parkeinvoice.ParkEInvoiceService;
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


@Tag(name = "管理后台 - 电子发票")
@RestController
@RequestMapping("/industry/park-E-invoice")
@Validated
public class ParkEInvoiceController {

    @Resource
    private ParkEInvoiceService parkEInvoiceService;

    @PostMapping("/create")
    @Operation(summary = "创建电子发票")
    @PreAuthorize("@ss.hasPermission('industry:park-E-invoice:create')")
    public CommonResult<Long> createParkEInvoice(@Valid @RequestBody ParkEInvoiceSaveReqVO createReqVO) {
        return success(parkEInvoiceService.createParkEInvoice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新电子发票")
    @PreAuthorize("@ss.hasPermission('industry:park-E-invoice:update')")
    public CommonResult<Boolean> updateParkEInvoice(@Valid @RequestBody ParkEInvoiceSaveReqVO updateReqVO) {
        parkEInvoiceService.updateParkEInvoice(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除电子发票")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-E-invoice:delete')")
    public CommonResult<Boolean> deleteParkEInvoice(@RequestParam("id") Long id) {
        parkEInvoiceService.deleteParkEInvoice(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得电子发票")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-E-invoice:query')")
    public CommonResult<ParkEInvoiceRespVO> getParkEInvoice(@RequestParam("id") Long id) {
        ParkEInvoiceDO parkEInvoice = parkEInvoiceService.getParkEInvoice(id);
        return success(BeanUtils.toBean(parkEInvoice, ParkEInvoiceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得电子发票分页")
    @PreAuthorize("@ss.hasPermission('industry:park-E-invoice:query')")
    public CommonResult<PageResult<ParkEInvoiceRespVO>> getParkEInvoicePage(@Valid ParkEInvoicePageReqVO pageReqVO) {
        PageResult<ParkEInvoiceDO> pageResult = parkEInvoiceService.getParkEInvoicePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkEInvoiceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出电子发票 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-E-invoice:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkEInvoiceExcel(@Valid ParkEInvoicePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkEInvoiceDO> list = parkEInvoiceService.getParkEInvoicePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "电子发票.xls", "数据", ParkEInvoiceRespVO.class,
                        BeanUtils.toBean(list, ParkEInvoiceRespVO.class));
    }

}
