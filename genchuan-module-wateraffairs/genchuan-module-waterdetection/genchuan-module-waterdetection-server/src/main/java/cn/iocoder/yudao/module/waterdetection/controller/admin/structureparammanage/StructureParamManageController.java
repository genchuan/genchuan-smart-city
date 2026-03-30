package cn.iocoder.yudao.module.waterdetection.controller.admin.structureparammanage;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.structureparammanage.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.structureparammanage.StructureParamManageDO;
import cn.iocoder.yudao.module.waterdetection.service.structureparammanage.StructureParamManageService;

@Tag(name = "管理后台 - 构建筑物参数管理")
@RestController
@RequestMapping("/waterdetection/structure-param-manage")
@Validated
public class StructureParamManageController {

    @Resource
    private StructureParamManageService structureParamManageService;

    @PostMapping("/create")
    @Operation(summary = "创建构建筑物参数管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:structure-param-manage:create')")
    public CommonResult<Long> createStructureParamManage(@Valid @RequestBody StructureParamManageSaveReqVO createReqVO) {
        return success(structureParamManageService.createStructureParamManage(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新构建筑物参数管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:structure-param-manage:update')")
    public CommonResult<Boolean> updateStructureParamManage(@Valid @RequestBody StructureParamManageSaveReqVO updateReqVO) {
        structureParamManageService.updateStructureParamManage(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除构建筑物参数管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:structure-param-manage:delete')")
    public CommonResult<Boolean> deleteStructureParamManage(@RequestParam("id") Long id) {
        structureParamManageService.deleteStructureParamManage(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得构建筑物参数管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:structure-param-manage:query')")
    public CommonResult<StructureParamManageRespVO> getStructureParamManage(@RequestParam("id") Long id) {
        StructureParamManageDO structureParamManage = structureParamManageService.getStructureParamManage(id);
        return success(BeanUtils.toBean(structureParamManage, StructureParamManageRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得构建筑物参数管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:structure-param-manage:query')")
    public CommonResult<PageResult<StructureParamManageRespVO>> getStructureParamManagePage(@Valid StructureParamManagePageReqVO pageReqVO) {
        PageResult<StructureParamManageDO> pageResult = structureParamManageService.getStructureParamManagePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StructureParamManageRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出构建筑物参数管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:structure-param-manage:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStructureParamManageExcel(@Valid StructureParamManagePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StructureParamManageDO> list = structureParamManageService.getStructureParamManagePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "构建筑物参数管理.xls", "数据", StructureParamManageRespVO.class,
                        BeanUtils.toBean(list, StructureParamManageRespVO.class));
    }

}