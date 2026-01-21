package cn.iocoder.yudao.module.park.controller.admin.park.statrpt.chargeabnormal;

import cn.iocoder.yudao.module.park.controller.admin.park.statrpt.chargeabnormal.vo.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.statrpt.chargeabnormal.ChargeAbnormalDO;
import cn.iocoder.yudao.module.park.service.park.statrpt.chargeabnormal.ChargeAbnormalService;
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


@Tag(name = "管理后台 - 收费异常")
@RestController
@RequestMapping("/park/charge-abnormal")
@Validated
public class ChargeAbnormalController {

    @Resource
    private ChargeAbnormalService chargeAbnormalService;

    @PostMapping("/stat-trend")
    @Operation(summary = "查询收入异常统计趋势")
    @PreAuthorize("@ss.hasPermission('park:charge-abnormal:stat-trend')")
    public CommonResult<TrendRespVO> statTrend(@Valid @RequestBody StatReportReqVO reqVO) {
        TrendRespVO respVO = chargeAbnormalService.statTrend(reqVO);
        return success(respVO);
    }
    @PostMapping("/stat-report")
    @Operation(summary = "查询收入异常报表")
    @PreAuthorize("@ss.hasPermission('park:charge-abnormal:stat-report')")
    public CommonResult<StatReportRespVO> statReport(@Valid @RequestBody StatReportReqVO reqVO) {
        StatReportRespVO respVO = chargeAbnormalService.statReport(reqVO);
        return success(respVO);
    }
    @PostMapping("/create")
    @Operation(summary = "创建收费异常")
    @PreAuthorize("@ss.hasPermission('park:charge-abnormal:create')")
    public CommonResult<Long> createChargeAbnormal(@Valid @RequestBody ChargeAbnormalSaveReqVO createReqVO) {
        return success(chargeAbnormalService.createChargeAbnormal(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新收费异常")
    @PreAuthorize("@ss.hasPermission('park:charge-abnormal:update')")
    public CommonResult<Boolean> updateChargeAbnormal(@Valid @RequestBody ChargeAbnormalSaveReqVO updateReqVO) {
        chargeAbnormalService.updateChargeAbnormal(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除收费异常")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:charge-abnormal:delete')")
    public CommonResult<Boolean> deleteChargeAbnormal(@RequestParam("id") Long id) {
        chargeAbnormalService.deleteChargeAbnormal(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得收费异常")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:charge-abnormal:query')")
    public CommonResult<ChargeAbnormalRespVO> getChargeAbnormal(@RequestParam("id") Long id) {
        ChargeAbnormalDO chargeAbnormal = chargeAbnormalService.getChargeAbnormal(id);
        return success(BeanUtils.toBean(chargeAbnormal, ChargeAbnormalRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得收费异常分页")
    @PreAuthorize("@ss.hasPermission('park:charge-abnormal:query')")
    public CommonResult<PageResult<ChargeAbnormalRespVO>> getChargeAbnormalPage(@Valid ChargeAbnormalPageReqVO pageReqVO) {
        PageResult<ChargeAbnormalDO> pageResult = chargeAbnormalService.getChargeAbnormalPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ChargeAbnormalRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出收费异常 Excel")
    @PreAuthorize("@ss.hasPermission('park:charge-abnormal:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportChargeAbnormalExcel(@Valid ChargeAbnormalPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ChargeAbnormalDO> list = chargeAbnormalService.getChargeAbnormalPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "收费异常.xls", "数据", ChargeAbnormalRespVO.class,
                        BeanUtils.toBean(list, ChargeAbnormalRespVO.class));
    }

}
