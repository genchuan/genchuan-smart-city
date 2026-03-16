/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.river;

import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.cleaningtype.CleaningTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.cleaningtype.CleaningTypeRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.cleaningtype.CleaningTypeSaveReqVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.CleaningTypeDO;
import cn.iocoder.yudao.module.envirhealth.service.river.cleaningtype.CleaningTypeService;

@Tag(name = "管理后台 - 保洁类型字典表")
@RestController
@RequestMapping("/envirhealth/cleaning-type")
@Validated
public class CleaningTypeController {

    @Resource
    private CleaningTypeService cleaningTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建保洁类型字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-type:create')")
    public CommonResult<Long> createCleaningType(@Valid @RequestBody CleaningTypeSaveReqVO createReqVO) {
        return success(cleaningTypeService.createCleaningType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新保洁类型字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-type:update')")
    public CommonResult<Boolean> updateCleaningType(@Valid @RequestBody CleaningTypeSaveReqVO updateReqVO) {
        cleaningTypeService.updateCleaningType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除保洁类型字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-type:delete')")
    public CommonResult<Boolean> deleteCleaningType(@RequestParam("id") Long id) {
        cleaningTypeService.deleteCleaningType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得保洁类型字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-type:query')")
    public CommonResult<CleaningTypeRespVO> getCleaningType(@RequestParam("id") Long id) {
        CleaningTypeDO cleaningType = cleaningTypeService.getCleaningType(id);
        return success(BeanUtils.toBean(cleaningType, CleaningTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得保洁类型字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-type:query')")
    public CommonResult<PageResult<CleaningTypeRespVO>> getCleaningTypePage(@Valid CleaningTypePageReqVO pageReqVO) {
        PageResult<CleaningTypeDO> pageResult = cleaningTypeService.getCleaningTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CleaningTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出保洁类型字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCleaningTypeExcel(@Valid CleaningTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CleaningTypeDO> list = cleaningTypeService.getCleaningTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "保洁类型字典表.xls", "数据", CleaningTypeRespVO.class,
                        BeanUtils.toBean(list, CleaningTypeRespVO.class));
    }

}*/
