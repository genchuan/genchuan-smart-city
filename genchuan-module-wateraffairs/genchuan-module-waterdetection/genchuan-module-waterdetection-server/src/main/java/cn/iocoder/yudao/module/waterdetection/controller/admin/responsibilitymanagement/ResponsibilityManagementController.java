package cn.iocoder.yudao.module.waterdetection.controller.admin.responsibilitymanagement;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.responsibilitymanagement.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.responsibilitymanagement.ResponsibilityManagementDO;
import cn.iocoder.yudao.module.waterdetection.service.responsibilitymanagement.ResponsibilityManagementService;

@Tag(name = "管理后台 - 责任单位及责任人管理")
@RestController
@RequestMapping("/waterdetection/responsibility-management")
@Validated
public class ResponsibilityManagementController {

    @Resource
    private ResponsibilityManagementService responsibilityManagementService;

    @PostMapping("/create")
    @Operation(summary = "创建责任单位及责任人管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:responsibility-management:create')")
    public CommonResult<Long> createResponsibilityManagement(@Valid @RequestBody ResponsibilityManagementSaveReqVO createReqVO) {
        return success(responsibilityManagementService.createResponsibilityManagement(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新责任单位及责任人管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:responsibility-management:update')")
    public CommonResult<Boolean> updateResponsibilityManagement(@Valid @RequestBody ResponsibilityManagementSaveReqVO updateReqVO) {
        responsibilityManagementService.updateResponsibilityManagement(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除责任单位及责任人管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:responsibility-management:delete')")
    public CommonResult<Boolean> deleteResponsibilityManagement(@RequestParam("id") Long id) {
        responsibilityManagementService.deleteResponsibilityManagement(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得责任单位及责任人管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:responsibility-management:query')")
    public CommonResult<ResponsibilityManagementRespVO> getResponsibilityManagement(@RequestParam("id") Long id) {
        ResponsibilityManagementDO responsibilityManagement = responsibilityManagementService.getResponsibilityManagement(id);
        return success(BeanUtils.toBean(responsibilityManagement, ResponsibilityManagementRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得责任单位及责任人管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:responsibility-management:query')")
    public CommonResult<PageResult<ResponsibilityManagementRespVO>> getResponsibilityManagementPage(@Valid ResponsibilityManagementPageReqVO pageReqVO) {
        PageResult<ResponsibilityManagementDO> pageResult = responsibilityManagementService.getResponsibilityManagementPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ResponsibilityManagementRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出责任单位及责任人管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:responsibility-management:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportResponsibilityManagementExcel(@Valid ResponsibilityManagementPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ResponsibilityManagementDO> list = responsibilityManagementService.getResponsibilityManagementPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "责任单位及责任人管理.xls", "数据", ResponsibilityManagementRespVO.class,
                        BeanUtils.toBean(list, ResponsibilityManagementRespVO.class));
    }

}