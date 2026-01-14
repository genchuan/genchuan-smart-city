package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkinduction;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkinduction.vo.ParkInductionPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkinduction.vo.ParkInductionRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkinduction.vo.ParkInductionSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkinduction.ParkInductionDO;
import cn.iocoder.yudao.module.industry.service.park.vas.parkinduction.ParkInductionService;
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


@Tag(name = "管理后台 - 停车诱导配置")
@RestController
@RequestMapping("/industry/park-induction")
@Validated
public class ParkInductionController {

    @Resource
    private ParkInductionService parkInductionService;

    @PostMapping("/create")
    @Operation(summary = "创建停车诱导配置")
    @PreAuthorize("@ss.hasPermission('industry:park-induction:create')")
    public CommonResult<Long> createParkInduction(@Valid @RequestBody ParkInductionSaveReqVO createReqVO) {
        return success(parkInductionService.createParkInduction(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新停车诱导配置")
    @PreAuthorize("@ss.hasPermission('industry:park-induction:update')")
    public CommonResult<Boolean> updateParkInduction(@Valid @RequestBody ParkInductionSaveReqVO updateReqVO) {
        parkInductionService.updateParkInduction(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除停车诱导配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-induction:delete')")
    public CommonResult<Boolean> deleteParkInduction(@RequestParam("id") Long id) {
        parkInductionService.deleteParkInduction(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得停车诱导配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-induction:query')")
    public CommonResult<ParkInductionRespVO> getParkInduction(@RequestParam("id") Long id) {
        ParkInductionDO parkInduction = parkInductionService.getParkInduction(id);
        return success(BeanUtils.toBean(parkInduction, ParkInductionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得停车诱导配置分页")
    @PreAuthorize("@ss.hasPermission('industry:park-induction:query')")
    public CommonResult<PageResult<ParkInductionRespVO>> getParkInductionPage(@Valid ParkInductionPageReqVO pageReqVO) {
        PageResult<ParkInductionDO> pageResult = parkInductionService.getParkInductionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkInductionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出停车诱导配置 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-induction:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkInductionExcel(@Valid ParkInductionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkInductionDO> list = parkInductionService.getParkInductionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "停车诱导配置.xls", "数据", ParkInductionRespVO.class,
                        BeanUtils.toBean(list, ParkInductionRespVO.class));
    }

}
