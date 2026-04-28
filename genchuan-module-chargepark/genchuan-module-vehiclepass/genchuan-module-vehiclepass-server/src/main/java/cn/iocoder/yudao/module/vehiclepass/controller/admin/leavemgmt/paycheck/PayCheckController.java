package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo.PayCheckPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo.PayCheckRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo.PayCheckSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo.PayCheckReleaseReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo.PayCheckRemindReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo.PayCheckChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo.PayCheckChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.leavemgmt.paycheck.PayCheckDO;
import cn.iocoder.yudao.module.vehiclepass.service.leavemgmt.paycheck.PayCheckService;
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


@Tag(name = "管理后台 - 缴费核验")
@RestController
@RequestMapping("/vehiclepass/pay-check")
@Validated
public class PayCheckController {

    @Resource
    private PayCheckService checkService;

    @PostMapping("/create")
    @Operation(summary = "创建缴费核验")
    @PreAuthorize("@ss.hasPermission('pay:check:create')")
    public CommonResult<Long> createCheck(@Valid @RequestBody PayCheckSaveReqVO createReqVO) {
        return success(checkService.createCheck(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新缴费核验")
    @PreAuthorize("@ss.hasPermission('pay:check:update')")
    public CommonResult<Boolean> updateCheck(@Valid @RequestBody PayCheckSaveReqVO updateReqVO) {
        checkService.updateCheck(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除缴费核验")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pay:check:delete')")
    public CommonResult<Boolean> deleteCheck(@RequestParam("id") Long id) {
        checkService.deleteCheck(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除缴费核验")
    @PreAuthorize("@ss.hasPermission('pay:check:delete')")
    public CommonResult<Boolean> deleteCheckList(@RequestParam("ids") List<Long> ids) {
        checkService.deleteCheckListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得缴费核验")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pay:check:query')")
    public CommonResult<PayCheckRespVO> getCheck(@RequestParam("id") Long id) {
        PayCheckDO check = checkService.getCheck(id);
        return success(BeanUtils.toBean(check, PayCheckRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得缴费核验分页")
    @PreAuthorize("@ss.hasPermission('pay:check:query')")
    public CommonResult<PageResult<PayCheckRespVO>> getCheckPage(@Valid PayCheckPageReqVO pageReqVO) {
        return success(checkService.getCheckPageWithJoin(pageReqVO));
    }

    @PutMapping("/release")
    @Operation(summary = "放行缴费核验")
    @PreAuthorize("@ss.hasPermission('vehiclepass:pay-check:release')")
    public CommonResult<Boolean> releaseCheck(@Valid @RequestBody PayCheckReleaseReqVO reqVO) {
        checkService.releaseCheck(reqVO.getId());
        return success(true);
    }

    @PutMapping("/remind")
    @Operation(summary = "催缴缴费核验")
    @PreAuthorize("@ss.hasPermission('vehiclepass:pay-check:remind')")
    public CommonResult<Boolean> remindCheck(@Valid @RequestBody PayCheckRemindReqVO reqVO) {
        checkService.remindCheck(reqVO.getId());
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "缴费核验统计")
    @PreAuthorize("@ss.hasPermission('vehiclepass:pay-check:chart')")
    public CommonResult<PayCheckChartRespVO> getChart(@Valid PayCheckChartReqVO reqVO) {
        return success(checkService.getChart(reqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出缴费核验 Excel")
    @PreAuthorize("@ss.hasPermission('pay:check:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCheckExcel(@Valid PayCheckPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<PayCheckRespVO> pageResult = checkService.getCheckPageWithJoin(pageReqVO);
        ExcelUtils.write(response, "缴费核验.xls", "数据", PayCheckRespVO.class, pageResult.getList());
    }

}