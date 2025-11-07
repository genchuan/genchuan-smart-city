package cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.unitgridattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.unitgridattr.vo.UnitGridAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.unitgridattr.vo.UnitGridAttrRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.unitgridattr.vo.UnitGridAttrSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.unitgridattr.UnitGridAttrDO;
import cn.iocoder.yudao.module.datacenter.service.grid.data.unitgridattr.UnitGridAttrService;

@Tag(name = "管理后台 - 单元网格属性")
@RestController
@RequestMapping("/datacenter/unit-grid-attr")
@Validated
public class UnitGridAttrController {

    @Resource
    private UnitGridAttrService unitGridAttrService;

    @PostMapping("/create")
    @Operation(summary = "创建单元网格属性")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-attr:create')")
    public CommonResult<Long> createUnitGridAttr(@Valid @RequestBody UnitGridAttrSaveReqVO createReqVO) {
        return success(unitGridAttrService.createUnitGridAttr(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新单元网格属性")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-attr:update')")
    public CommonResult<Boolean> updateUnitGridAttr(@Valid @RequestBody UnitGridAttrSaveReqVO updateReqVO) {
        unitGridAttrService.updateUnitGridAttr(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除单元网格属性")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-attr:delete')")
    public CommonResult<Boolean> deleteUnitGridAttr(@RequestParam("id") Long id) {
        unitGridAttrService.deleteUnitGridAttr(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得单元网格属性")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-attr:query')")
    public CommonResult<UnitGridAttrRespVO> getUnitGridAttr(@RequestParam("id") Long id) {
        UnitGridAttrDO unitGridAttr = unitGridAttrService.getUnitGridAttr(id);
        return success(BeanUtils.toBean(unitGridAttr, UnitGridAttrRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得单元网格属性分页")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-attr:query')")
    public CommonResult<PageResult<UnitGridAttrRespVO>> getUnitGridAttrPage(@Valid UnitGridAttrPageReqVO pageReqVO) {
        PageResult<UnitGridAttrDO> pageResult = unitGridAttrService.getUnitGridAttrPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UnitGridAttrRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出单元网格属性 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-attr:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUnitGridAttrExcel(@Valid UnitGridAttrPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UnitGridAttrDO> list = unitGridAttrService.getUnitGridAttrPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "单元网格属性.xls", "数据", UnitGridAttrRespVO.class,
                        BeanUtils.toBean(list, UnitGridAttrRespVO.class));
    }

}