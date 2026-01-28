package cn.iocoder.yudao.module.park.controller.admin.park.user.merchantpermission;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.merchantpermission.vo.MerchantPermissionPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.merchantpermission.vo.MerchantPermissionRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.merchantpermission.vo.MerchantPermissionSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.merchantpermission.MerchantPermissionDO;
import cn.iocoder.yudao.module.park.service.park.user.merchantpermission.MerchantPermissionService;
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


@Tag(name = "管理后台 - 商户权限")
@RestController
@RequestMapping("/park/merchant-permission")
@Validated
public class MerchantPermissionController {

    @Resource
    private MerchantPermissionService merchantPermissionService;

    @PostMapping("/create")
    @Operation(summary = "创建商户权限")
    @PreAuthorize("@ss.hasPermission('park:merchant-permission:create')")
    public CommonResult<Long> createMerchantPermission(@Valid @RequestBody MerchantPermissionSaveReqVO createReqVO) {
        return success(merchantPermissionService.createMerchantPermission(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新商户权限")
    @PreAuthorize("@ss.hasPermission('park:merchant-permission:update')")
    public CommonResult<Boolean> updateMerchantPermission(@Valid @RequestBody MerchantPermissionSaveReqVO updateReqVO) {
        merchantPermissionService.updateMerchantPermission(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除商户权限")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:merchant-permission:delete')")
    public CommonResult<Boolean> deleteMerchantPermission(@RequestParam("id") Long id) {
        merchantPermissionService.deleteMerchantPermission(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得商户权限")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:merchant-permission:query')")
    public CommonResult<MerchantPermissionRespVO> getMerchantPermission(@RequestParam("id") Long id) {
        MerchantPermissionDO merchantPermission = merchantPermissionService.getMerchantPermission(id);
        return success(BeanUtils.toBean(merchantPermission, MerchantPermissionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得商户权限分页")
    @PreAuthorize("@ss.hasPermission('park:merchant-permission:query')")
    public CommonResult<PageResult<MerchantPermissionRespVO>> getMerchantPermissionPage(@Valid MerchantPermissionPageReqVO pageReqVO) {
        PageResult<MerchantPermissionDO> pageResult = merchantPermissionService.getMerchantPermissionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MerchantPermissionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出商户权限 Excel")
    @PreAuthorize("@ss.hasPermission('park:merchant-permission:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMerchantPermissionExcel(@Valid MerchantPermissionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MerchantPermissionDO> list = merchantPermissionService.getMerchantPermissionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "商户权限.xls", "数据", MerchantPermissionRespVO.class,
                        BeanUtils.toBean(list, MerchantPermissionRespVO.class));
    }

}
