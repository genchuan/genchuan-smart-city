package cn.iocoder.yudao.module.smartcity.controller.admin.inspectionobject;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionobject.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionobject.InspectionObjectDO;
import cn.iocoder.yudao.module.smartcity.service.inspectionobject.InspectionObjectService;

@Tag(name = "管理后台 - 双随机行政检查")
@RestController
@RequestMapping("/smartcity/inspection-object")
@Validated
public class InspectionObjectController {

    @Resource
    private InspectionObjectService inspectionObjectService;

    @PostMapping("/create")
    @Operation(summary = "创建双随机行政检查")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-object:create')")
    public CommonResult<Long> createInspectionObject(@Valid @RequestBody InspectionObjectSaveReqVO createReqVO) {
        return success(inspectionObjectService.createInspectionObject(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新双随机行政检查")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-object:update')")
    public CommonResult<Boolean> updateInspectionObject(@Valid @RequestBody InspectionObjectSaveReqVO updateReqVO) {
        inspectionObjectService.updateInspectionObject(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除双随机行政检查")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-object:delete')")
    public CommonResult<Boolean> deleteInspectionObject(@RequestParam("id") Long id) {
        inspectionObjectService.deleteInspectionObject(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得双随机行政检查")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-object:query')")
    public CommonResult<InspectionObjectRespVO> getInspectionObject(@RequestParam("id") Long id) {
        InspectionObjectDO inspectionObject = inspectionObjectService.getInspectionObject(id);
        return success(BeanUtils.toBean(inspectionObject, InspectionObjectRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得双随机行政检查分页")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-object:query')")
    public CommonResult<PageResult<InspectionObjectRespVO>> getInspectionObjectPage(@Valid InspectionObjectPageReqVO pageReqVO) {
        PageResult<InspectionObjectDO> pageResult = inspectionObjectService.getInspectionObjectPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectionObjectRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出双随机行政检查 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-object:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectionObjectExcel(@Valid InspectionObjectPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectionObjectDO> list = inspectionObjectService.getInspectionObjectPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "双随机行政检查.xls", "数据", InspectionObjectRespVO.class,
                        BeanUtils.toBean(list, InspectionObjectRespVO.class));
    }

}