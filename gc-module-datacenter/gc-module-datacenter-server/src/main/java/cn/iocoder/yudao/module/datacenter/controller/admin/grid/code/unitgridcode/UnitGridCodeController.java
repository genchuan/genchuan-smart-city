package cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.unitgridcode;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.unitgridcode.vo.UnitGridCodePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.unitgridcode.vo.UnitGridCodeRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.unitgridcode.vo.UnitGridCodeSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.code.unitgridcode.UnitGridCodeDO;
import cn.iocoder.yudao.module.datacenter.service.grid.code.unitgridcode.UnitGridCodeService;

@Tag(name = "管理后台 - 单元网格编码")
@RestController
@RequestMapping("/datacenter/unit-grid-code")
@Validated
public class UnitGridCodeController {

    @Resource
    private UnitGridCodeService unitGridCodeService;

    @PostMapping("/create")
    @Operation(summary = "创建单元网格编码")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-code:create')")
    public CommonResult<Long> createUnitGridCode(@Valid @RequestBody UnitGridCodeSaveReqVO createReqVO) {
        return success(unitGridCodeService.createUnitGridCode(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新单元网格编码")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-code:update')")
    public CommonResult<Boolean> updateUnitGridCode(@Valid @RequestBody UnitGridCodeSaveReqVO updateReqVO) {
        unitGridCodeService.updateUnitGridCode(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除单元网格编码")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-code:delete')")
    public CommonResult<Boolean> deleteUnitGridCode(@RequestParam("id") Long id) {
        unitGridCodeService.deleteUnitGridCode(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得单元网格编码")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-code:query')")
    public CommonResult<UnitGridCodeRespVO> getUnitGridCode(@RequestParam("id") Long id) {
        UnitGridCodeDO unitGridCode = unitGridCodeService.getUnitGridCode(id);
        return success(BeanUtils.toBean(unitGridCode, UnitGridCodeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得单元网格编码分页")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-code:query')")
    public CommonResult<PageResult<UnitGridCodeRespVO>> getUnitGridCodePage(@Valid UnitGridCodePageReqVO pageReqVO) {
        PageResult<UnitGridCodeDO> pageResult = unitGridCodeService.getUnitGridCodePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UnitGridCodeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出单元网格编码 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-code:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUnitGridCodeExcel(@Valid UnitGridCodePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UnitGridCodeDO> list = unitGridCodeService.getUnitGridCodePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "单元网格编码.xls", "数据", UnitGridCodeRespVO.class,
                        BeanUtils.toBean(list, UnitGridCodeRespVO.class));
    }

    @PostMapping("/generate-by-unit-grid")
    @Operation(summary = "根据单元网格ID生成编码")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-code:create')")
    public CommonResult<Long> generateCodeByUnitGrid(
            @RequestParam("unitGridId") @NotEmpty(message = "单元网格ID不能为空") String unitGridId) {
        Long id = unitGridCodeService.generateCodeByUnitGrid(unitGridId);
        return success(id);
    }

    @PostMapping("/validate-code-unique")
    @Operation(summary = "校验编码唯一性")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-code:query')")
    public CommonResult<Boolean> validateCodeUnique(
            @RequestParam("unitGridCode") @NotEmpty(message = "编码不能为空") String unitGridCode,
            @RequestParam(value = "excludeId", required = false) Long excludeId) {
        Boolean isUnique = unitGridCodeService.validateCodeUnique(unitGridCode, excludeId);
        return success(isUnique);
    }

    @PutMapping("/invalidate")
    @Operation(summary = "作废编码")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-code:update')")
    public CommonResult<Boolean> invalidateCode(
            @RequestParam("id") @NotNull(message = "编码ID不能为空") Long id,
            @RequestParam("reason") @NotEmpty(message = "作废原因不能为空") String reason) {
        unitGridCodeService.invalidateCode(id, reason);
        return success(true);
    }

    @GetMapping("/get-by-code")
    @Operation(summary = "根据编码查询")
    @Parameter(name = "unitGridCode", description = "单元网格编码", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-code:query')")
    public CommonResult<UnitGridCodeRespVO> getByCode(@RequestParam("unitGridCode") String unitGridCode) {
        UnitGridCodeDO codeDO = unitGridCodeService.getByCode(unitGridCode);
        return success(BeanUtils.toBean(codeDO, UnitGridCodeRespVO.class));
    }

    @GetMapping("/get-by-unit-grid-id")
    @Operation(summary = "根据单元网格ID查询")
    @Parameter(name = "unitGridId", description = "单元网格ID", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-code:query')")
    public CommonResult<UnitGridCodeRespVO> getByUnitGridId(@RequestParam("unitGridId") String unitGridId) {
        UnitGridCodeDO unitGridCode = unitGridCodeService.getByUnitGridId(unitGridId);
        return success(BeanUtils.toBean(unitGridCode, UnitGridCodeRespVO.class));
    }

    @GetMapping("/list-by-area")
    @Operation(summary = "根据行政区划查询编码列表")
    @Parameter(name = "areaFullCode", description = "行政区划完整代码", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-code:query')")
    public CommonResult<List<UnitGridCodeRespVO>> getListByArea(@RequestParam("areaFullCode") String areaFullCode) {
        List<UnitGridCodeDO> list = unitGridCodeService.getListByArea(areaFullCode);
        return success(BeanUtils.toBean(list, UnitGridCodeRespVO.class));
    }

}