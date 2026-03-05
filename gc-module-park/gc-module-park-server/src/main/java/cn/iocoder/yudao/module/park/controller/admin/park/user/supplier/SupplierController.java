package cn.iocoder.yudao.module.park.controller.admin.park.user.supplier;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.supplier.vo.SupplierPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.supplier.vo.SupplierRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.supplier.vo.SupplierSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.supplier.SupplierDO;
import cn.iocoder.yudao.module.park.service.park.user.supplier.SupplierService;
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


@Tag(name = "管理后台 - 供应商")
@RestController
@RequestMapping("/park/supplier")
@Validated
public class SupplierController {

    @Resource
    private SupplierService supplierService;

    @PostMapping("/create")
    @Operation(summary = "创建供应商")
    @PreAuthorize("@ss.hasPermission('park:supplier:create')")
    public CommonResult<Long> createSupplier(@Valid @RequestBody SupplierSaveReqVO createReqVO) {
        return success(supplierService.createSupplier(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新供应商")
    @PreAuthorize("@ss.hasPermission('park:supplier:update')")
    public CommonResult<Boolean> updateSupplier(@Valid @RequestBody SupplierSaveReqVO updateReqVO) {
        supplierService.updateSupplier(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除供应商")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:supplier:delete')")
    public CommonResult<Boolean> deleteSupplier(@RequestParam("id") Long id) {
        supplierService.deleteSupplier(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得供应商")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:supplier:query')")
    public CommonResult<SupplierRespVO> getSupplier(@RequestParam("id") Long id) {
        SupplierDO supplier = supplierService.getSupplier(id);
        return success(BeanUtils.toBean(supplier, SupplierRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得供应商分页")
    @PreAuthorize("@ss.hasPermission('park:supplier:query')")
    public CommonResult<PageResult<SupplierRespVO>> getSupplierPage(@Valid SupplierPageReqVO pageReqVO) {
        PageResult<SupplierDO> pageResult = supplierService.getSupplierPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SupplierRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出供应商 Excel")
    @PreAuthorize("@ss.hasPermission('park:supplier:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSupplierExcel(@Valid SupplierPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SupplierDO> list = supplierService.getSupplierPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "供应商.xls", "数据", SupplierRespVO.class,
                        BeanUtils.toBean(list, SupplierRespVO.class));
    }

}
