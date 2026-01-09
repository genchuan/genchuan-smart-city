package cn.iocoder.yudao.module.industry.controller.admin.park.order.parksettlement;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parksettlement.vo.ParkSettlementPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parksettlement.vo.ParkSettlementRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parksettlement.vo.ParkSettlementSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parksettlement.ParkSettlementDO;
import cn.iocoder.yudao.module.industry.service.park.order.parksettlement.ParkSettlementService;
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



@Tag(name = "管理后台 - 分账结算表")
@RestController
@RequestMapping("/industry/park-settlement")
@Validated
public class ParkSettlementController {

    @Resource
    private ParkSettlementService parkSettlementService;

    @PostMapping("/create")
    @Operation(summary = "创建分账结算表")
    @PreAuthorize("@ss.hasPermission('industry:park-settlement:create')")
    public CommonResult<Long> createParkSettlement(@Valid @RequestBody ParkSettlementSaveReqVO createReqVO) {
        return success(parkSettlementService.createParkSettlement(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新分账结算表")
    @PreAuthorize("@ss.hasPermission('industry:park-settlement:update')")
    public CommonResult<Boolean> updateParkSettlement(@Valid @RequestBody ParkSettlementSaveReqVO updateReqVO) {
        parkSettlementService.updateParkSettlement(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除分账结算表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-settlement:delete')")
    public CommonResult<Boolean> deleteParkSettlement(@RequestParam("id") Long id) {
        parkSettlementService.deleteParkSettlement(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得分账结算表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-settlement:query')")
    public CommonResult<ParkSettlementRespVO> getParkSettlement(@RequestParam("id") Long id) {
        ParkSettlementDO parkSettlement = parkSettlementService.getParkSettlement(id);
        return success(BeanUtils.toBean(parkSettlement, ParkSettlementRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得分账结算表分页")
    @PreAuthorize("@ss.hasPermission('industry:park-settlement:query')")
    public CommonResult<PageResult<ParkSettlementRespVO>> getParkSettlementPage(@Valid ParkSettlementPageReqVO pageReqVO) {
        PageResult<ParkSettlementDO> pageResult = parkSettlementService.getParkSettlementPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkSettlementRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出分账结算表 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-settlement:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkSettlementExcel(@Valid ParkSettlementPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkSettlementDO> list = parkSettlementService.getParkSettlementPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "分账结算表.xls", "数据", ParkSettlementRespVO.class,
                        BeanUtils.toBean(list, ParkSettlementRespVO.class));
    }

}
