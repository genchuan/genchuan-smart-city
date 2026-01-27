package cn.iocoder.yudao.module.park.controller.admin.park.user.merchant;

import cn.iocoder.yudao.module.park.controller.admin.park.user.merchant.vo.MerchantPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.merchant.vo.MerchantRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.merchant.vo.MerchantSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.merchant.MerchantDO;
import cn.iocoder.yudao.module.park.service.park.user.merchant.MerchantService;
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


@Tag(name = "管理后台 - 商户")
@RestController
@RequestMapping("/park/merchant")
@Validated
public class MerchantController {

    @Resource
    private MerchantService merchantService;

    @PostMapping("/create")
    @Operation(summary = "创建商户")
    @PreAuthorize("@ss.hasPermission('park:merchant:create')")
    public CommonResult<Long> createMerchant(@Valid @RequestBody MerchantSaveReqVO createReqVO) {
        return success(merchantService.createMerchant(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新商户")
    @PreAuthorize("@ss.hasPermission('park:merchant:update')")
    public CommonResult<Boolean> updateMerchant(@Valid @RequestBody MerchantSaveReqVO updateReqVO) {
        merchantService.updateMerchant(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除商户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:merchant:delete')")
    public CommonResult<Boolean> deleteMerchant(@RequestParam("id") Long id) {
        merchantService.deleteMerchant(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得商户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:merchant:query')")
    public CommonResult<MerchantRespVO> getMerchant(@RequestParam("id") Long id) {
        MerchantDO merchant = merchantService.getMerchant(id);
        return success(BeanUtils.toBean(merchant, MerchantRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得商户分页")
    @PreAuthorize("@ss.hasPermission('park:merchant:query')")
    public CommonResult<PageResult<MerchantRespVO>> getMerchantPage(@Valid MerchantPageReqVO pageReqVO) {
        PageResult<MerchantDO> pageResult = merchantService.getMerchantPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MerchantRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出商户 Excel")
    @PreAuthorize("@ss.hasPermission('park:merchant:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMerchantExcel(@Valid MerchantPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MerchantDO> list = merchantService.getMerchantPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "商户.xls", "数据", MerchantRespVO.class,
                        BeanUtils.toBean(list, MerchantRespVO.class));
    }

}
