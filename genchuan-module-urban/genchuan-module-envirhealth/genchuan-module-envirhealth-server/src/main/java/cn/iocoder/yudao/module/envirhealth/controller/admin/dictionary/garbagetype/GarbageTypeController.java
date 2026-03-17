package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.garbagetype;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.garbagetype.vo.GarbageTypeOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.garbagetype.vo.GarbageTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.garbagetype.vo.GarbageTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.garbagetype.vo.GarbageTypeRespVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.garbagetype.GarbageTypeDO;
import cn.iocoder.yudao.module.envirhealth.service.dictionary.garbagetype.GarbageTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "字典表 - 垃圾品类")
@RestController
@RequestMapping("/envirhealth/garbage-type")
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

    /**
     * 获得垃圾品类字典下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得垃圾品类字典(下拉框)")
    @PreAuthorize("@ss.hasPermission('health:garbage-type:query')")
    public CommonResult<List<GarbageTypeOptionVO>> getGarbageTypeOptions() {
        return success(garbageTypeService.getGarbageTypeOptions());
    }
}
