/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetype;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetype.vo.GarbageTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetype.vo.GarbageTypeRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetype.vo.GarbageTypeSaveReqVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetype.GarbageTypeDO;
import cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbagetype.GarbageTypeService;

@Tag(name = "环境卫生管理 - 垃圾品类字典")
@RestController
@RequestMapping("/health/garbage-type")
@Validated
public class GarbageTypeController {

    @Resource
    private GarbageTypeService garbageTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建垃圾品类字典")
    @PreAuthorize("@ss.hasPermission('health:garbage-type:create')")
    public CommonResult<Long> createGarbageType(@Valid @RequestBody GarbageTypeSaveReqVO createReqVO) {
        return success(garbageTypeService.createGarbageType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新垃圾品类字典")
    @PreAuthorize("@ss.hasPermission('health:garbage-type:update')")
    public CommonResult<Boolean> updateGarbageType(@Valid @RequestBody GarbageTypeSaveReqVO updateReqVO) {
        garbageTypeService.updateGarbageType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除垃圾品类字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('health:garbage-type:delete')")
    public CommonResult<Boolean> deleteGarbageType(@RequestParam("id") Long id) {
        garbageTypeService.deleteGarbageType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得垃圾品类字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('health:garbage-type:query')")
    public CommonResult<GarbageTypeRespVO> getGarbageType(@RequestParam("id") Long id) {
        GarbageTypeDO garbageType = garbageTypeService.getGarbageType(id);
        return success(BeanUtils.toBean(garbageType, GarbageTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得垃圾品类字典分页")
    @PreAuthorize("@ss.hasPermission('health:garbage-type:query')")
    public CommonResult<PageResult<GarbageTypeRespVO>> getGarbageTypePage(@Valid GarbageTypePageReqVO pageReqVO) {
        PageResult<GarbageTypeDO> pageResult = garbageTypeService.getGarbageTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GarbageTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出垃圾品类字典 Excel")
    @PreAuthorize("@ss.hasPermission('health:garbage-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGarbageTypeExcel(@Valid GarbageTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GarbageTypeDO> list = garbageTypeService.getGarbageTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "垃圾品类字典.xls", "数据", GarbageTypeRespVO.class,
                        BeanUtils.toBean(list, GarbageTypeRespVO.class));
    }

}*/
