package cn.iocoder.yudao.module.waterdetection.controller.admin.waterhydrologyparam;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.waterhydrologyparam.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterhydrologyparam.WaterHydrologyParamDO;
import cn.iocoder.yudao.module.waterdetection.service.waterhydrologyparam.WaterHydrologyParamService;

@Tag(name = "管理后台 - 水源水文参数管理")
@RestController
@RequestMapping("/waterdetection/water-hydrology-param")
@Validated
public class WaterHydrologyParamController {

    @Resource
    private WaterHydrologyParamService waterHydrologyParamService;

    @PostMapping("/create")
    @Operation(summary = "创建水源水文参数管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-hydrology-param:create')")
    public CommonResult<Long> createWaterHydrologyParam(@Valid @RequestBody WaterHydrologyParamSaveReqVO createReqVO) {
        return success(waterHydrologyParamService.createWaterHydrologyParam(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新水源水文参数管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-hydrology-param:update')")
    public CommonResult<Boolean> updateWaterHydrologyParam(@Valid @RequestBody WaterHydrologyParamSaveReqVO updateReqVO) {
        waterHydrologyParamService.updateWaterHydrologyParam(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除水源水文参数管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:water-hydrology-param:delete')")
    public CommonResult<Boolean> deleteWaterHydrologyParam(@RequestParam("id") Long id) {
        waterHydrologyParamService.deleteWaterHydrologyParam(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得水源水文参数管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-hydrology-param:query')")
    public CommonResult<WaterHydrologyParamRespVO> getWaterHydrologyParam(@RequestParam("id") Long id) {
        WaterHydrologyParamDO waterHydrologyParam = waterHydrologyParamService.getWaterHydrologyParam(id);
        return success(BeanUtils.toBean(waterHydrologyParam, WaterHydrologyParamRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得水源水文参数管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-hydrology-param:query')")
    public CommonResult<PageResult<WaterHydrologyParamRespVO>> getWaterHydrologyParamPage(@Valid WaterHydrologyParamPageReqVO pageReqVO) {
        PageResult<WaterHydrologyParamDO> pageResult = waterHydrologyParamService.getWaterHydrologyParamPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WaterHydrologyParamRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出水源水文参数管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-hydrology-param:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWaterHydrologyParamExcel(@Valid WaterHydrologyParamPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WaterHydrologyParamDO> list = waterHydrologyParamService.getWaterHydrologyParamPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "水源水文参数管理.xls", "数据", WaterHydrologyParamRespVO.class,
                        BeanUtils.toBean(list, WaterHydrologyParamRespVO.class));
    }

}