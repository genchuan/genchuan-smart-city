package cn.iocoder.yudao.module.waterdetection.controller.admin.positionresponsibility;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.positionresponsibility.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.positionresponsibility.PositionResponsibilityDO;
import cn.iocoder.yudao.module.waterdetection.service.positionresponsibility.PositionResponsibilityService;

@Tag(name = "管理后台 - 岗位职责划分管理")
@RestController
@RequestMapping("/waterdetection/position-responsibility")
@Validated
public class PositionResponsibilityController {

    @Resource
    private PositionResponsibilityService positionResponsibilityService;

    @PostMapping("/create")
    @Operation(summary = "创建岗位职责划分管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:position-responsibility:create')")
    public CommonResult<Long> createPositionResponsibility(@Valid @RequestBody PositionResponsibilitySaveReqVO createReqVO) {
        return success(positionResponsibilityService.createPositionResponsibility(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新岗位职责划分管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:position-responsibility:update')")
    public CommonResult<Boolean> updatePositionResponsibility(@Valid @RequestBody PositionResponsibilitySaveReqVO updateReqVO) {
        positionResponsibilityService.updatePositionResponsibility(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除岗位职责划分管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:position-responsibility:delete')")
    public CommonResult<Boolean> deletePositionResponsibility(@RequestParam("id") Long id) {
        positionResponsibilityService.deletePositionResponsibility(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得岗位职责划分管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:position-responsibility:query')")
    public CommonResult<PositionResponsibilityRespVO> getPositionResponsibility(@RequestParam("id") Long id) {
        PositionResponsibilityDO positionResponsibility = positionResponsibilityService.getPositionResponsibility(id);
        return success(BeanUtils.toBean(positionResponsibility, PositionResponsibilityRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得岗位职责划分管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:position-responsibility:query')")
    public CommonResult<PageResult<PositionResponsibilityRespVO>> getPositionResponsibilityPage(@Valid PositionResponsibilityPageReqVO pageReqVO) {
        PageResult<PositionResponsibilityDO> pageResult = positionResponsibilityService.getPositionResponsibilityPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PositionResponsibilityRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出岗位职责划分管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:position-responsibility:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPositionResponsibilityExcel(@Valid PositionResponsibilityPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PositionResponsibilityDO> list = positionResponsibilityService.getPositionResponsibilityPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "岗位职责划分管理.xls", "数据", PositionResponsibilityRespVO.class,
                        BeanUtils.toBean(list, PositionResponsibilityRespVO.class));
    }

}