package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeestrategy;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeestrategy.vo.ParkFeeStrategyPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeestrategy.vo.ParkFeeStrategyRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeestrategy.vo.ParkFeeStrategySaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkfeestrategy.ParkFeeStrategyDO;
import cn.iocoder.yudao.module.industry.service.park.discount.parkfeestrategy.ParkFeeStrategyService;
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


@Tag(name = "漳州停车管理-收费优惠域 - 费率策略")
@RestController
@RequestMapping("/industry/park-fee-strategy")
@Validated
public class ParkFeeStrategyController {

    @Resource
    private ParkFeeStrategyService parkFeeStrategyService;

    @PostMapping("/create")
    @Operation(summary = "创建费率策略")
    @PreAuthorize("@ss.hasPermission('industry:park-fee-strategy:create')")
    public CommonResult<Long> createParkFeeStrategy(@Valid @RequestBody ParkFeeStrategySaveReqVO createReqVO) {
        return success(parkFeeStrategyService.createParkFeeStrategy(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新费率策略")
    @PreAuthorize("@ss.hasPermission('industry:park-fee-strategy:update')")
    public CommonResult<Boolean> updateParkFeeStrategy(@Valid @RequestBody ParkFeeStrategySaveReqVO updateReqVO) {
        parkFeeStrategyService.updateParkFeeStrategy(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除费率策略")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-fee-strategy:delete')")
    public CommonResult<Boolean> deleteParkFeeStrategy(@RequestParam("id") Long id) {
        parkFeeStrategyService.deleteParkFeeStrategy(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得费率策略")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-fee-strategy:query')")
    public CommonResult<ParkFeeStrategyRespVO> getParkFeeStrategy(@RequestParam("id") Long id) {
        ParkFeeStrategyDO parkFeeStrategy = parkFeeStrategyService.getParkFeeStrategy(id);
        return success(BeanUtils.toBean(parkFeeStrategy, ParkFeeStrategyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得费率策略分页")
    @PreAuthorize("@ss.hasPermission('industry:park-fee-strategy:query')")
    public CommonResult<PageResult<ParkFeeStrategyRespVO>> getParkFeeStrategyPage(@Valid ParkFeeStrategyPageReqVO pageReqVO) {
        PageResult<ParkFeeStrategyDO> pageResult = parkFeeStrategyService.getParkFeeStrategyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkFeeStrategyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出费率策略 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-fee-strategy:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkFeeStrategyExcel(@Valid ParkFeeStrategyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkFeeStrategyDO> list = parkFeeStrategyService.getParkFeeStrategyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "费率策略.xls", "数据", ParkFeeStrategyRespVO.class,
                        BeanUtils.toBean(list, ParkFeeStrategyRespVO.class));
    }

}
