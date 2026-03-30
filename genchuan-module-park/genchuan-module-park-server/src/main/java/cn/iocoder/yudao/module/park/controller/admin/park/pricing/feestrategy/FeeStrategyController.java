package cn.iocoder.yudao.module.park.controller.admin.park.pricing.feestrategy;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.feestrategy.vo.FeeStrategyPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.feestrategy.vo.FeeStrategyRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.feestrategy.vo.FeeStrategySaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.feestrategy.FeeStrategyDO;
import cn.iocoder.yudao.module.park.service.park.pricing.feestrategy.FeeStrategyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 费率策略")
@RestController
@RequestMapping("/park/fee-strategy")
@Validated
public class FeeStrategyController {

    @Resource
    private FeeStrategyService feeStrategyService;

    @PostMapping("/create")
    @Operation(summary = "创建费率策略")
    @PreAuthorize("@ss.hasPermission('park:fee-strategy:create')")
    public CommonResult<Long> createFeeStrategy(@Valid @RequestBody FeeStrategySaveReqVO createReqVO) {
        return success(feeStrategyService.createFeeStrategy(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新费率策略")
    @PreAuthorize("@ss.hasPermission('park:fee-strategy:update')")
    public CommonResult<Boolean> updateFeeStrategy(@Valid @RequestBody FeeStrategySaveReqVO updateReqVO) {
        feeStrategyService.updateFeeStrategy(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除费率策略")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:fee-strategy:delete')")
    public CommonResult<Boolean> deleteFeeStrategy(@RequestParam("id") Long id) {
        feeStrategyService.deleteFeeStrategy(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得费率策略")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:fee-strategy:query')")
    public CommonResult<FeeStrategyRespVO> getFeeStrategy(@RequestParam("id") Long id) {
        FeeStrategyDO feeStrategy = feeStrategyService.getFeeStrategy(id);
        return success(BeanUtils.toBean(feeStrategy, FeeStrategyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得费率策略分页")
    @PreAuthorize("@ss.hasPermission('park:fee-strategy:query')")
    public CommonResult<PageResult<FeeStrategyRespVO>> getFeeStrategyPage(@Valid FeeStrategyPageReqVO pageReqVO) {
        PageResult<FeeStrategyDO> pageResult = feeStrategyService.getFeeStrategyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, FeeStrategyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出费率策略 Excel")
    @PreAuthorize("@ss.hasPermission('park:fee-strategy:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFeeStrategyExcel(@Valid FeeStrategyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FeeStrategyDO> list = feeStrategyService.getFeeStrategyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "费率策略.xls", "数据", FeeStrategyRespVO.class,
                        BeanUtils.toBean(list, FeeStrategyRespVO.class));
    }

}
