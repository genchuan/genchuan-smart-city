package cn.iocoder.yudao.module.waterdetection.controller.admin.gb5749standard;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.gb5749standard.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.gb5749standard.Gb5749StandardDO;
import cn.iocoder.yudao.module.waterdetection.service.gb5749standard.Gb5749StandardService;

@Tag(name = "管理后台 - 《生活饮用水卫生标准》GB 5749-2022标准")
@RestController
@RequestMapping("/waterdetection/gb5749-standard")
@Validated
public class Gb5749StandardController {

    @Resource
    private Gb5749StandardService gb5749StandardService;

    @PostMapping("/create")
    @Operation(summary = "创建《生活饮用水卫生标准》GB 5749-2022标准")
    @PreAuthorize("@ss.hasPermission('waterdetection:gb5749-standard:create')")
    public CommonResult<Long> createGb5749Standard(@Valid @RequestBody Gb5749StandardSaveReqVO createReqVO) {
        return success(gb5749StandardService.createGb5749Standard(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新《生活饮用水卫生标准》GB 5749-2022标准")
    @PreAuthorize("@ss.hasPermission('waterdetection:gb5749-standard:update')")
    public CommonResult<Boolean> updateGb5749Standard(@Valid @RequestBody Gb5749StandardSaveReqVO updateReqVO) {
        gb5749StandardService.updateGb5749Standard(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除《生活饮用水卫生标准》GB 5749-2022标准")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:gb5749-standard:delete')")
    public CommonResult<Boolean> deleteGb5749Standard(@RequestParam("id") Long id) {
        gb5749StandardService.deleteGb5749Standard(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得《生活饮用水卫生标准》GB 5749-2022标准")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:gb5749-standard:query')")
    public CommonResult<Gb5749StandardRespVO> getGb5749Standard(@RequestParam("id") Long id) {
        Gb5749StandardDO gb5749Standard = gb5749StandardService.getGb5749Standard(id);
        return success(BeanUtils.toBean(gb5749Standard, Gb5749StandardRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得《生活饮用水卫生标准》GB 5749-2022标准分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:gb5749-standard:query')")
    public CommonResult<PageResult<Gb5749StandardRespVO>> getGb5749StandardPage(@Valid Gb5749StandardPageReqVO pageReqVO) {
        PageResult<Gb5749StandardDO> pageResult = gb5749StandardService.getGb5749StandardPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, Gb5749StandardRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出《生活饮用水卫生标准》GB 5749-2022标准 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:gb5749-standard:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGb5749StandardExcel(@Valid Gb5749StandardPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<Gb5749StandardDO> list = gb5749StandardService.getGb5749StandardPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "《生活饮用水卫生标准》GB 5749-2022标准.xls", "数据", Gb5749StandardRespVO.class,
                        BeanUtils.toBean(list, Gb5749StandardRespVO.class));
    }

}