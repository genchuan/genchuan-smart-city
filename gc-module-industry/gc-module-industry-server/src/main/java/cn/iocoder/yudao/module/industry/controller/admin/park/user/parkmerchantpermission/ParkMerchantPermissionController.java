package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchantpermission;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchantpermission.vo.ParkMerchantPermissionPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchantpermission.vo.ParkMerchantPermissionRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchantpermission.vo.ParkMerchantPermissionSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmerchantpermission.ParkMerchantPermissionDO;
import cn.iocoder.yudao.module.industry.service.park.user.parkmerchantpermission.ParkMerchantPermissionService;
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


@Tag(name = "管理后台 - 商户权限")
@RestController
@RequestMapping("/industry/park-merchant-permission")
@Validated
public class ParkMerchantPermissionController {

    @Resource
    private ParkMerchantPermissionService parkMerchantPermissionService;

    @PostMapping("/create")
    @Operation(summary = "创建商户权限")
    @PreAuthorize("@ss.hasPermission('industry:park-merchant-permission:create')")
    public CommonResult<Long> createParkMerchantPermission(@Valid @RequestBody ParkMerchantPermissionSaveReqVO createReqVO) {
        return success(parkMerchantPermissionService.createParkMerchantPermission(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新商户权限")
    @PreAuthorize("@ss.hasPermission('industry:park-merchant-permission:update')")
    public CommonResult<Boolean> updateParkMerchantPermission(@Valid @RequestBody ParkMerchantPermissionSaveReqVO updateReqVO) {
        parkMerchantPermissionService.updateParkMerchantPermission(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除商户权限")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-merchant-permission:delete')")
    public CommonResult<Boolean> deleteParkMerchantPermission(@RequestParam("id") Long id) {
        parkMerchantPermissionService.deleteParkMerchantPermission(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得商户权限")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-merchant-permission:query')")
    public CommonResult<ParkMerchantPermissionRespVO> getParkMerchantPermission(@RequestParam("id") Long id) {
        ParkMerchantPermissionDO parkMerchantPermission = parkMerchantPermissionService.getParkMerchantPermission(id);
        return success(BeanUtils.toBean(parkMerchantPermission, ParkMerchantPermissionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得商户权限分页")
    @PreAuthorize("@ss.hasPermission('industry:park-merchant-permission:query')")
    public CommonResult<PageResult<ParkMerchantPermissionRespVO>> getParkMerchantPermissionPage(@Valid ParkMerchantPermissionPageReqVO pageReqVO) {
        PageResult<ParkMerchantPermissionDO> pageResult = parkMerchantPermissionService.getParkMerchantPermissionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkMerchantPermissionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出商户权限 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-merchant-permission:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkMerchantPermissionExcel(@Valid ParkMerchantPermissionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkMerchantPermissionDO> list = parkMerchantPermissionService.getParkMerchantPermissionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "商户权限.xls", "数据", ParkMerchantPermissionRespVO.class,
                        BeanUtils.toBean(list, ParkMerchantPermissionRespVO.class));
    }

}
