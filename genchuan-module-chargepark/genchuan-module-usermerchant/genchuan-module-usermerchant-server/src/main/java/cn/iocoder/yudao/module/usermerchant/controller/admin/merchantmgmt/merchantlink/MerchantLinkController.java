package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantlink;

import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.UserCarApproveReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.UserCarChartReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.UserCarChartRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.UserCarRejectReqVO;
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

import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantlink.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantlink.MerchantLinkDO;
import cn.iocoder.yudao.module.usermerchant.service.merchantmgmt.merchantlink.MerchantLinkService;

@Tag(name = "管理后台 - 商户对接")
@RestController
@RequestMapping("/usermerchant/merchant-link")
@Validated
public class MerchantLinkController {

    @Resource
    private MerchantLinkService merchantLinkService;

    @GetMapping("/page")
    @Operation(summary = "获得商户对接分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-link:query')")
    public CommonResult<PageResult<MerchantLinkPageRespVO>> getMerchantLinkPage(@Valid MerchantLinkPageReqVO pageReqVO) {
        PageResult<MerchantLinkDO> pageResult = merchantLinkService.getMerchantLinkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MerchantLinkPageRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建商户对接")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-link:create')")
    public CommonResult<Boolean> createMerchantLink(@Valid @RequestBody MerchantLinkCreateReqVO createReqVO) {
        return success(merchantLinkService.createMerchantLink(createReqVO));
    }

    @PostMapping("/save")
    @Operation(summary = "保存商户对接")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-link:save')")
    public CommonResult<Boolean> saveMerchantLink(@Valid @RequestBody MerchantLinkSaveReqVO saveReqVO) {
        return success(merchantLinkService.saveMerchantLink(saveReqVO));
    }

    @PutMapping("/link")
    @Operation(summary = "对接")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-link:link')")
    public CommonResult<Boolean> updateUserCar(@Valid @RequestBody MerchantLinkLinkReqVO reqVO) {
        merchantLinkService.linkMerchantLink(reqVO,"已对接");
        return success(true);
    }

    @PutMapping("/unlink")
    @Operation(summary = "断开")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-link:unlink')")
    public CommonResult<Boolean> updateUserCar(@Valid @RequestBody MerchantLinkUnlinkReqVO reqVO) {
        merchantLinkService.linkMerchantLink(reqVO,"未对接");
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得商户对接")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-link:query')")
    public CommonResult<MerchantLinkPageRespVO> getMerchantLink(@RequestParam("id") Long id) {
        MerchantLinkDO merchantLink = merchantLinkService.getMerchantLink(id);
        return success(BeanUtils.toBean(merchantLink, MerchantLinkPageRespVO.class));
    }

    @PutMapping("/update")
    @Operation(summary = "更新商户对接")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-link:update')")
    public CommonResult<Boolean> updateMerchantLink(@Valid @RequestBody MerchantLinkSaveReqVO updateReqVO) {
        merchantLinkService.updateMerchantLink(updateReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "商户对接统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-carmerchant-link:query')")
    public CommonResult<MerchantLinkChartRespVO> getMerchantLinkChart(@Valid MerchantLinkChartReqVO chartReqVO) {
        return success(merchantLinkService.getMerchantLinkChart(chartReqVO));
    }

//    @DeleteMapping("/delete")
//    @Operation(summary = "删除商户对接")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-link:delete')")
//    public CommonResult<Boolean> deleteMerchantLink(@RequestParam("id") Long id) {
//        merchantLinkService.deleteMerchantLink(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除商户对接")
//                @PreAuthorize("@ss.hasPermission('usermerchant:merchant-link:delete')")
//    public CommonResult<Boolean> deleteMerchantLinkList(@RequestParam("ids") List<Long> ids) {
//        merchantLinkService.deleteMerchantLinkListByIds(ids);
//        return success(true);
//    }
//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出商户对接 Excel")
//    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-link:export')")
//    @ApiAccessLog(operateType = EXPORT)
//    public void exportMerchantLinkExcel(@Valid MerchantLinkPageReqVO pageReqVO,
//              HttpServletResponse response) throws IOException {
//        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<MerchantLinkDO> list = merchantLinkService.getMerchantLinkPage(pageReqVO).getList();
//        // 导出 Excel
//        ExcelUtils.write(response, "商户对接.xls", "数据", MerchantLinkPageRespVO.class,
//                        BeanUtils.toBean(list, MerchantLinkPageRespVO.class));
//    }

}