package cn.iocoder.yudao.module.industry.controller.admin.park.pay.statparkpay;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.statparkpay.vo.StatParkPayGenReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.statparkpay.vo.StatParkPayPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.statparkpay.vo.StatParkPayRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.statparkpay.vo.StatParkPaySaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.statparkpay.StatParkPayDO;
import cn.iocoder.yudao.module.industry.service.park.pay.statparkpay.StatParkPayService;
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



@Tag(name = "停车管理-缴费统计")
@RestController
@RequestMapping("/industry/stat-park-pay")
@Validated
public class StatParkPayController {

    @Resource
    private StatParkPayService statParkPayService;

    @PostMapping("/create")
    @Operation(summary = "创建缴费统计")
    @PreAuthorize("@ss.hasPermission('industry:stat-park-pay:create')")
    public CommonResult<Long> createStatParkPay(@Valid @RequestBody StatParkPayGenReqVO createReqVO) {
        return success(statParkPayService.createStatParkPay(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新缴费统计")
    @PreAuthorize("@ss.hasPermission('industry:stat-park-pay:update')")
    public CommonResult<Boolean> updateStatParkPay(@Valid @RequestBody StatParkPaySaveReqVO updateReqVO) {
        statParkPayService.updateStatParkPay(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除缴费统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:stat-park-pay:delete')")
    public CommonResult<Boolean> deleteStatParkPay(@RequestParam("id") Long id) {
        statParkPayService.deleteStatParkPay(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得缴费统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:stat-park-pay:query')")
    public CommonResult<StatParkPayRespVO> getStatParkPay(@RequestParam("id") Long id) {
        StatParkPayDO statParkPay = statParkPayService.getStatParkPay(id);
        return success(BeanUtils.toBean(statParkPay, StatParkPayRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得缴费统计分页")
    @PreAuthorize("@ss.hasPermission('industry:stat-park-pay:query')")
    public CommonResult<PageResult<StatParkPayRespVO>> getStatParkPayPage(@Valid StatParkPayPageReqVO pageReqVO) {
        PageResult<StatParkPayDO> pageResult = statParkPayService.getStatParkPayPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StatParkPayRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出缴费统计 Excel")
    @PreAuthorize("@ss.hasPermission('industry:stat-park-pay:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStatParkPayExcel(@Valid StatParkPayPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StatParkPayDO> list = statParkPayService.getStatParkPayPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "缴费统计.xls", "数据", StatParkPayRespVO.class,
                        BeanUtils.toBean(list, StatParkPayRespVO.class));
    }

}
