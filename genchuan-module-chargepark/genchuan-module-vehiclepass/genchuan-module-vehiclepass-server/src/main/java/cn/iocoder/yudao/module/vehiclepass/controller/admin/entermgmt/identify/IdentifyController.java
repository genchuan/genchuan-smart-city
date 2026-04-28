package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.identify;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.identify.vo.*;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.identify.IdentifyDO;
import cn.iocoder.yudao.module.vehiclepass.service.entermgmt.identify.IdentifyService;
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


@Tag(name = "管理后台 - 车牌识别")
@RestController
@RequestMapping("/vehiclepass/plate-identify")
@Validated
public class IdentifyController {

    @Resource
    private IdentifyService identifyService;

//    @PostMapping("/create")
//    @Operation(summary = "创建车牌识别")
//    @PreAuthorize("@ss.hasPermission('plate:identify:create')")
//    public CommonResult<Long> createIdentify(@Valid @RequestBody IdentifySaveReqVO createReqVO) {
//        return success(identifyService.createIdentify(createReqVO));
//    }
    @PostMapping("/create")
    @Operation(summary = "手动录入车牌识别")
    @PreAuthorize("@ss.hasPermission('vehiclepass:plate-identify:create')")
    public CommonResult<Boolean> createIdentify(@Validated @RequestBody IdentifyCreateReqVO reqVO) {
        return CommonResult.success(identifyService.createIdentify(reqVO));
    }


    @PutMapping("/update")
    @Operation(summary = "更新车牌识别")
    @PreAuthorize("@ss.hasPermission('plate:identify:update')")
    public CommonResult<Boolean> updateIdentify(@Valid @RequestBody IdentifySaveReqVO updateReqVO) {
        identifyService.updateIdentify(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车牌识别")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('plate:identify:delete')")
    public CommonResult<Boolean> deleteIdentify(@RequestParam("id") Long id) {
        identifyService.deleteIdentify(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除车牌识别")
    @PreAuthorize("@ss.hasPermission('plate:identify:delete')")
    public CommonResult<Boolean> deleteIdentifyList(@RequestParam("ids") List<Long> ids) {
        identifyService.deleteIdentifyListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车牌识别")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('plate:identify:query')")
    public CommonResult<IdentifyRespVO> getIdentify(@RequestParam("id") Long id) {
        IdentifyDO identify = identifyService.getIdentify(id);
        return success(BeanUtils.toBean(identify, IdentifyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车牌识别分页")
    @PreAuthorize("@ss.hasPermission('plate:identify:query')")
    public CommonResult<PageResult<IdentifyRespVO>> getIdentifyPage(@Valid IdentifyPageReqVO pageReqVO) {
        PageResult<IdentifyRespVO> pageResult = identifyService.getIdentifyPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车牌识别 Excel")
    @PreAuthorize("@ss.hasPermission('plate:identify:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportIdentifyExcel(@Valid IdentifyPageReqVO pageReqVO,
                                    HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<IdentifyRespVO> pageResult = identifyService.getIdentifyPage(pageReqVO);
        ExcelUtils.write(response, "车牌识别.xls", "数据", IdentifyRespVO.class, pageResult.getList());
    }

    @PutMapping("/correct")
    @Operation(summary = "修正车牌识别记录")
    @PreAuthorize("@ss.hasPermission('vehiclepass:plate-identify:correct')")
    public CommonResult<Boolean> correctIdentify(@Validated @RequestBody IdentifyCorrectReqVO reqVO) {
        return CommonResult.success(identifyService.correctIdentify(reqVO));
    }


    @GetMapping("/chart")
    @Operation(summary = "车牌识别统计(折线图+柱状图+卡片)")
    @PreAuthorize("@ss.hasPermission('vehiclepass:plate-identify:chart')")
    public CommonResult<PlateIdentifyChartRespVO> getPlateIdentifyChart(PlateIdentifyChartReqVO reqVO) {
        return CommonResult.success(identifyService.getIdentifyChartData(reqVO));
    }


}