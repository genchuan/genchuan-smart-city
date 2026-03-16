package cn.iocoder.yudao.module.evaluate.controller.admin.sys.cycletype;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.cycletype.vo.CycleTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.cycletype.vo.CycleTypeRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.cycletype.vo.CycleTypeSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.cycletype.CycleTypeDO;
import cn.iocoder.yudao.module.evaluate.service.cycletype.CycleTypeService;
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

@Tag(name = "管理后台 - 周期类型字典")
@RestController
@RequestMapping("/evaluate/cycle-type")
@Validated
public class CycleTypeController {

    @Resource
    private CycleTypeService cycleTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建周期类型字典")
    @PreAuthorize("@ss.hasPermission('evaluate:cycle-type:create')")
    public CommonResult<Long> createCycleType(@Valid @RequestBody CycleTypeSaveReqVO createReqVO) {
        return success(cycleTypeService.createCycleType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新周期类型字典")
    @PreAuthorize("@ss.hasPermission('evaluate:cycle-type:update')")
    public CommonResult<Boolean> updateCycleType(@Valid @RequestBody CycleTypeSaveReqVO updateReqVO) {
        cycleTypeService.updateCycleType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除周期类型字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:cycle-type:delete')")
    public CommonResult<Boolean> deleteCycleType(@RequestParam("id") Long id) {
        cycleTypeService.deleteCycleType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得周期类型字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:cycle-type:query')")
    public CommonResult<CycleTypeRespVO> getCycleType(@RequestParam("id") Long id) {
        CycleTypeDO cycleType = cycleTypeService.getCycleType(id);
        return success(BeanUtils.toBean(cycleType, CycleTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得周期类型字典分页")
    @PreAuthorize("@ss.hasPermission('evaluate:cycle-type:query')")
    public CommonResult<PageResult<CycleTypeRespVO>> getCycleTypePage(@Valid CycleTypePageReqVO pageReqVO) {
        PageResult<CycleTypeDO> pageResult = cycleTypeService.getCycleTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CycleTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出周期类型字典 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:cycle-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCycleTypeExcel(@Valid CycleTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CycleTypeDO> list = cycleTypeService.getCycleTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "周期类型字典.xls", "数据", CycleTypeRespVO.class,
                        BeanUtils.toBean(list, CycleTypeRespVO.class));
    }

}