package cn.iocoder.yudao.module.park.controller.admin.park.order.settlement;

import cn.iocoder.yudao.module.park.controller.admin.park.order.settlement.vo.SettlementPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.settlement.vo.SettlementRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.settlement.vo.SettlementSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.settlement.SettlementDO;
import cn.iocoder.yudao.module.park.service.park.order.settlement.SettlementService;
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


@Tag(name = "管理后台 - 分账结算")
@RestController
@RequestMapping("/park/settlement")
@Validated
public class SettlementController {

    @Resource
    private SettlementService settlementService;

    @PostMapping("/create")
    @Operation(summary = "创建分账结算")
    @PreAuthorize("@ss.hasPermission('park:settlement:create')")
    public CommonResult<Long> createSettlement(@Valid @RequestBody SettlementSaveReqVO createReqVO) {
        return success(settlementService.createSettlement(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新分账结算")
    @PreAuthorize("@ss.hasPermission('park:settlement:update')")
    public CommonResult<Boolean> updateSettlement(@Valid @RequestBody SettlementSaveReqVO updateReqVO) {
        settlementService.updateSettlement(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除分账结算")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:settlement:delete')")
    public CommonResult<Boolean> deleteSettlement(@RequestParam("id") Long id) {
        settlementService.deleteSettlement(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得分账结算")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:settlement:query')")
    public CommonResult<SettlementRespVO> getSettlement(@RequestParam("id") Long id) {
        SettlementDO settlement = settlementService.getSettlement(id);
        return success(BeanUtils.toBean(settlement, SettlementRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得分账结算分页")
    @PreAuthorize("@ss.hasPermission('park:settlement:query')")
    public CommonResult<PageResult<SettlementRespVO>> getSettlementPage(@Valid SettlementPageReqVO pageReqVO) {
        PageResult<SettlementDO> pageResult = settlementService.getSettlementPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SettlementRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出分账结算 Excel")
    @PreAuthorize("@ss.hasPermission('park:settlement:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSettlementExcel(@Valid SettlementPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SettlementDO> list = settlementService.getSettlementPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "分账结算.xls", "数据", SettlementRespVO.class,
                        BeanUtils.toBean(list, SettlementRespVO.class));
    }

}
