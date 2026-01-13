package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeetemp;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeetemp.vo.ParkFeeTempPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeetemp.vo.ParkFeeTempRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeetemp.vo.ParkFeeTempSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkfeetemp.ParkFeeTempDO;
import cn.iocoder.yudao.module.industry.service.park.discount.parkfeetemp.ParkFeeTempService;
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


@Tag(name = "漳州停车管理-收费优惠域 - 临停收费规则")
@RestController
@RequestMapping("/industry/park-fee-temp")
@Validated
public class ParkFeeTempController {

    @Resource
    private ParkFeeTempService parkFeeTempService;

    @PostMapping("/create")
    @Operation(summary = "创建临停收费规则")
    @PreAuthorize("@ss.hasPermission('industry:park-fee-temp:create')")
    public CommonResult<Long> createParkFeeTemp(@Valid @RequestBody ParkFeeTempSaveReqVO createReqVO) {
        return success(parkFeeTempService.createParkFeeTemp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新临停收费规则")
    @PreAuthorize("@ss.hasPermission('industry:park-fee-temp:update')")
    public CommonResult<Boolean> updateParkFeeTemp(@Valid @RequestBody ParkFeeTempSaveReqVO updateReqVO) {
        parkFeeTempService.updateParkFeeTemp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除临停收费规则")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-fee-temp:delete')")
    public CommonResult<Boolean> deleteParkFeeTemp(@RequestParam("id") Long id) {
        parkFeeTempService.deleteParkFeeTemp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得临停收费规则")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-fee-temp:query')")
    public CommonResult<ParkFeeTempRespVO> getParkFeeTemp(@RequestParam("id") Long id) {
        ParkFeeTempDO parkFeeTemp = parkFeeTempService.getParkFeeTemp(id);
        return success(BeanUtils.toBean(parkFeeTemp, ParkFeeTempRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得临停收费规则分页")
    @PreAuthorize("@ss.hasPermission('industry:park-fee-temp:query')")
    public CommonResult<PageResult<ParkFeeTempRespVO>> getParkFeeTempPage(@Valid ParkFeeTempPageReqVO pageReqVO) {
        PageResult<ParkFeeTempDO> pageResult = parkFeeTempService.getParkFeeTempPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkFeeTempRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出临停收费规则 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-fee-temp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkFeeTempExcel(@Valid ParkFeeTempPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkFeeTempDO> list = parkFeeTempService.getParkFeeTempPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "临停收费规则.xls", "数据", ParkFeeTempRespVO.class,
                        BeanUtils.toBean(list, ParkFeeTempRespVO.class));
    }

}
