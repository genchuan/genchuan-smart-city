package cn.iocoder.yudao.module.waterdetection.controller.admin.meteruserrelation;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.meteruserrelation.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.meteruserrelation.MeterUserRelationDO;
import cn.iocoder.yudao.module.waterdetection.service.meteruserrelation.MeterUserRelationService;

@Tag(name = "管理后台 - 户表关联及变更管理")
@RestController
@RequestMapping("/waterdetection/meter-user-relation")
@Validated
public class MeterUserRelationController {

    @Resource
    private MeterUserRelationService meterUserRelationService;

    @PostMapping("/create")
    @Operation(summary = "创建户表关联及变更管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:meter-user-relation:create')")
    public CommonResult<Long> createMeterUserRelation(@Valid @RequestBody MeterUserRelationSaveReqVO createReqVO) {
        return success(meterUserRelationService.createMeterUserRelation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新户表关联及变更管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:meter-user-relation:update')")
    public CommonResult<Boolean> updateMeterUserRelation(@Valid @RequestBody MeterUserRelationSaveReqVO updateReqVO) {
        meterUserRelationService.updateMeterUserRelation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除户表关联及变更管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:meter-user-relation:delete')")
    public CommonResult<Boolean> deleteMeterUserRelation(@RequestParam("id") Long id) {
        meterUserRelationService.deleteMeterUserRelation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得户表关联及变更管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:meter-user-relation:query')")
    public CommonResult<MeterUserRelationRespVO> getMeterUserRelation(@RequestParam("id") Long id) {
        MeterUserRelationDO meterUserRelation = meterUserRelationService.getMeterUserRelation(id);
        return success(BeanUtils.toBean(meterUserRelation, MeterUserRelationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得户表关联及变更管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:meter-user-relation:query')")
    public CommonResult<PageResult<MeterUserRelationRespVO>> getMeterUserRelationPage(@Valid MeterUserRelationPageReqVO pageReqVO) {
        PageResult<MeterUserRelationDO> pageResult = meterUserRelationService.getMeterUserRelationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MeterUserRelationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出户表关联及变更管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:meter-user-relation:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMeterUserRelationExcel(@Valid MeterUserRelationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MeterUserRelationDO> list = meterUserRelationService.getMeterUserRelationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "户表关联及变更管理.xls", "数据", MeterUserRelationRespVO.class,
                        BeanUtils.toBean(list, MeterUserRelationRespVO.class));
    }

}