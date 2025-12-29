package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkarrearsrecovery;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkarrearsrecovery.vo.ParkArrearsRecoveryPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkarrearsrecovery.vo.ParkArrearsRecoveryRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkarrearsrecovery.vo.ParkArrearsRecoverySaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkarrearsrecovery.ParkArrearsRecoveryDO;
import cn.iocoder.yudao.module.industry.service.park.pay.parkarrearsrecovery.ParkArrearsRecoveryService;
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



@Tag(name = "停车管理-欠费追缴")
@RestController
@RequestMapping("/industry/park-arrears-recovery")
@Validated
public class ParkArrearsRecoveryController {

    @Resource
    private ParkArrearsRecoveryService parkArrearsRecoveryService;

    @PostMapping("/create")
    @Operation(summary = "创建欠费追缴")
    @PreAuthorize("@ss.hasPermission('industry:park-arrears-recovery:create')")
    public CommonResult<Long> createParkArrearsRecovery(@Valid @RequestBody ParkArrearsRecoverySaveReqVO createReqVO) {
        return success(parkArrearsRecoveryService.createParkArrearsRecovery(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新欠费追缴")
    @PreAuthorize("@ss.hasPermission('industry:park-arrears-recovery:update')")
    public CommonResult<Boolean> updateParkArrearsRecovery(@Valid @RequestBody ParkArrearsRecoverySaveReqVO updateReqVO) {
        parkArrearsRecoveryService.updateParkArrearsRecovery(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除欠费追缴")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-arrears-recovery:delete')")
    public CommonResult<Boolean> deleteParkArrearsRecovery(@RequestParam("id") Long id) {
        parkArrearsRecoveryService.deleteParkArrearsRecovery(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得欠费追缴")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-arrears-recovery:query')")
    public CommonResult<ParkArrearsRecoveryRespVO> getParkArrearsRecovery(@RequestParam("id") Long id) {
        ParkArrearsRecoveryDO parkArrearsRecovery = parkArrearsRecoveryService.getParkArrearsRecovery(id);
        return success(BeanUtils.toBean(parkArrearsRecovery, ParkArrearsRecoveryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得欠费追缴分页")
    @PreAuthorize("@ss.hasPermission('industry:park-arrears-recovery:query')")
    public CommonResult<PageResult<ParkArrearsRecoveryRespVO>> getParkArrearsRecoveryPage(@Valid ParkArrearsRecoveryPageReqVO pageReqVO) {
        PageResult<ParkArrearsRecoveryDO> pageResult = parkArrearsRecoveryService.getParkArrearsRecoveryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkArrearsRecoveryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出欠费追缴 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-arrears-recovery:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkArrearsRecoveryExcel(@Valid ParkArrearsRecoveryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkArrearsRecoveryDO> list = parkArrearsRecoveryService.getParkArrearsRecoveryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "欠费追缴.xls", "数据", ParkArrearsRecoveryRespVO.class,
                        BeanUtils.toBean(list, ParkArrearsRecoveryRespVO.class));
    }

}
