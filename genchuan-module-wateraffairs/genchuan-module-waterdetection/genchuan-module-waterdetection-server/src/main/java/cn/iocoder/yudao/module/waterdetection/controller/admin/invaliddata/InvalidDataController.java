package cn.iocoder.yudao.module.waterdetection.controller.admin.invaliddata;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.invaliddata.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.invaliddata.InvalidDataDO;
import cn.iocoder.yudao.module.waterdetection.service.invaliddata.InvalidDataService;

@Tag(name = "管理后台 - 不合格数据处理")
@RestController
@RequestMapping("/waterdetection/invalid-data")
@Validated
public class InvalidDataController {

    @Resource
    private InvalidDataService invalidDataService;

    @PostMapping("/create")
    @Operation(summary = "创建不合格数据处理")
    @PreAuthorize("@ss.hasPermission('waterdetection:invalid-data:create')")
    public CommonResult<Long> createInvalidData(@Valid @RequestBody InvalidDataSaveReqVO createReqVO) {
        return success(invalidDataService.createInvalidData(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新不合格数据处理")
    @PreAuthorize("@ss.hasPermission('waterdetection:invalid-data:update')")
    public CommonResult<Boolean> updateInvalidData(@Valid @RequestBody InvalidDataSaveReqVO updateReqVO) {
        invalidDataService.updateInvalidData(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除不合格数据处理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:invalid-data:delete')")
    public CommonResult<Boolean> deleteInvalidData(@RequestParam("id") Long id) {
        invalidDataService.deleteInvalidData(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得不合格数据处理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:invalid-data:query')")
    public CommonResult<InvalidDataRespVO> getInvalidData(@RequestParam("id") Long id) {
        InvalidDataDO invalidData = invalidDataService.getInvalidData(id);
        return success(BeanUtils.toBean(invalidData, InvalidDataRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得不合格数据处理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:invalid-data:query')")
    public CommonResult<PageResult<InvalidDataRespVO>> getInvalidDataPage(@Valid InvalidDataPageReqVO pageReqVO) {
        PageResult<InvalidDataDO> pageResult = invalidDataService.getInvalidDataPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InvalidDataRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出不合格数据处理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:invalid-data:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInvalidDataExcel(@Valid InvalidDataPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InvalidDataDO> list = invalidDataService.getInvalidDataPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "不合格数据处理.xls", "数据", InvalidDataRespVO.class,
                        BeanUtils.toBean(list, InvalidDataRespVO.class));
    }

}