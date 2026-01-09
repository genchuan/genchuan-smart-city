package cn.iocoder.yudao.module.industry.controller.admin.park.guidance.parkguidance;

import cn.iocoder.yudao.module.industry.controller.admin.park.guidance.parkguidance.vo.ParkGuidancePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.guidance.parkguidance.vo.ParkGuidanceRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.guidance.parkguidance.vo.ParkGuidanceSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.guidance.parkguidance.ParkGuidanceDO;
import cn.iocoder.yudao.module.industry.service.park.guidance.parkguidance.ParkGuidanceService;
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



@Tag(name = "停车管理-诱导服务")
@RestController
@RequestMapping("/industry/park-guidance")
@Validated
public class ParkGuidanceController {

    @Resource
    private ParkGuidanceService parkGuidanceService;

    @PostMapping("/create")
    @Operation(summary = "创建停车诱导服务")
    @PreAuthorize("@ss.hasPermission('industry:park-guidance:create')")
    public CommonResult<Long> createParkGuidance(@Valid @RequestBody ParkGuidanceSaveReqVO createReqVO) {
        return success(parkGuidanceService.createParkGuidance(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新停车诱导服务")
    @PreAuthorize("@ss.hasPermission('industry:park-guidance:update')")
    public CommonResult<Boolean> updateParkGuidance(@Valid @RequestBody ParkGuidanceSaveReqVO updateReqVO) {
        parkGuidanceService.updateParkGuidance(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除停车诱导服务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-guidance:delete')")
    public CommonResult<Boolean> deleteParkGuidance(@RequestParam("id") Long id) {
        parkGuidanceService.deleteParkGuidance(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得停车诱导服务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-guidance:query')")
    public CommonResult<ParkGuidanceRespVO> getParkGuidance(@RequestParam("id") Long id) {
        ParkGuidanceDO parkGuidance = parkGuidanceService.getParkGuidance(id);
        return success(BeanUtils.toBean(parkGuidance, ParkGuidanceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得停车诱导服务分页")
    @PreAuthorize("@ss.hasPermission('industry:park-guidance:query')")
    public CommonResult<PageResult<ParkGuidanceRespVO>> getParkGuidancePage(@Valid ParkGuidancePageReqVO pageReqVO) {
        PageResult<ParkGuidanceDO> pageResult = parkGuidanceService.getParkGuidancePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkGuidanceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出停车诱导服务 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-guidance:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkGuidanceExcel(@Valid ParkGuidancePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkGuidanceDO> list = parkGuidanceService.getParkGuidancePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "停车诱导服务.xls", "数据", ParkGuidanceRespVO.class,
                        BeanUtils.toBean(list, ParkGuidanceRespVO.class));
    }

}
