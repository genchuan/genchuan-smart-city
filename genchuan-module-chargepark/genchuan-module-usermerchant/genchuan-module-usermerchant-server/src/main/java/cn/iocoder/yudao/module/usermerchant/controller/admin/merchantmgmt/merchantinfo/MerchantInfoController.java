package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantinfo;

import cn.idev.excel.EasyExcel;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;

import java.net.URLEncoder;
import java.time.LocalDateTime;
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

import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantinfo.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantinfo.MerchantInfoDO;
import cn.iocoder.yudao.module.usermerchant.service.merchantmgmt.merchantinfo.MerchantInfoService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "管理后台 - 商户信息")
@RestController
@RequestMapping("/usermerchant/merchant-info")
@Validated
public class MerchantInfoController {

    @Resource
    private MerchantInfoService merchantInfoService;

    @GetMapping("/page")
    @Operation(summary = "获得商户信息分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-info:query')")
    public CommonResult<PageResult<MerchantInfoPageRespVO>> getMerchantInfoPage(@Valid MerchantInfoPageReqVO pageReqVO) {
        PageResult<MerchantInfoDO> pageResult = merchantInfoService.getMerchantInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MerchantInfoPageRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建商户信息")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-info:create')")
    public CommonResult<Boolean> createMerchantInfo(@Valid @RequestBody MerchantInfoCreateReqVO createReqVO) {
        return success(merchantInfoService.createMerchantInfo(createReqVO));
    }

    @PostMapping("/import")
    @Operation(summary = "导入商户信息")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    })
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-info:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<Boolean> importExcel(@RequestParam("file") MultipartFile file,
                                             @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<MerchantInfoImportExcelVO> list = ExcelUtils.read(file, MerchantInfoImportExcelVO.class);
        return success(merchantInfoService.importInfos(list, updateSupport));
    }

    @GetMapping("/template")
    @Operation(summary = "下载商户信息导入模板")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-info:import')")
    public void downloadImportTemplate(HttpServletResponse response) throws IOException {
//        List<MerchantInfoImportExcelVO> emptyList = Collections.emptyList();
//        ExcelUtils.write(response, "商户信息导入模板.xlsx", "商户信息", MerchantInfoImportExcelVO.class, emptyList);
        // 构造一条示例数据（仅填充业务字段）
        MerchantInfoImportExcelVO example = MerchantInfoImportExcelVO.builder()
                .name("示例商户")
                .contact("张三")
                .phone("13800138000")
                .merchantType("充电商户")
                .address("示例地址某某路1号")
                .registerTime(LocalDateTime.now())
                .remark("示例备注")
                .build();
        List<MerchantInfoImportExcelVO> exampleList = Collections.singletonList(example);

        // 使用 EasyExcel 原生方式导出，以便支持表头样式（如果 ExcelUtils 不支持样式，则需直接使用 EasyExcel）
        // 方式一：如果 ExcelUtils.write 已封装，需确认其是否支持注解样式；若不支持，则用原生 EasyExcel。
        // 下面给出原生 EasyExcel 写法（确保项目中已引入 easyexcel 依赖）。
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("商户信息导入模板.xlsx", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName);

        EasyExcel.write(response.getOutputStream(), MerchantInfoImportExcelVO.class)
                .sheet("商户信息")
                .doWrite(exampleList);
    }

    @GetMapping("/export")
    @Operation(summary = "导出商户信息")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMerchantInfoExcel(@Valid MerchantInfoPageReqVO pageReqVO,
                                        HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MerchantInfoDO> list = merchantInfoService.getMerchantInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "商户信息.xls", "数据", MerchantInfoExportRespVO.class,
                BeanUtils.toBean(list, MerchantInfoExportRespVO.class));
    }

    @PutMapping("/approve")
    @Operation(summary = "审核通过")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-info:approve')")
    public CommonResult<Boolean> approve(@Valid @RequestBody MerchantInfoSaveReqVO reqVO) {
        merchantInfoService.batchUpdatePlateAuth(reqVO,1);
        return success(true);
    }

    @PutMapping("/reject")
    @Operation(summary = "审核驳回")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-info:reject')")
    public CommonResult<Boolean> reject(@Valid @RequestBody MerchantInfoSaveReqVO reqVO) {
        merchantInfoService.batchUpdatePlateAuth(reqVO,0);
        return success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用商户")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-info:disable')")
    public CommonResult<Boolean> disableMerchantInfo(@Valid @RequestBody MerchantInfoDisableReqVO reqVO) {
        merchantInfoService.updateMerchantStatus(reqVO.getIds(), "禁用");
        return success(true);
    }

    @PutMapping("/enable")
    @Operation(summary = "启用商户")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-info:enable')")
    public CommonResult<Boolean> enableMerchantInfo(@Valid @RequestBody MerchantInfoEnableReqVO reqVO) {
        merchantInfoService.updateMerchantStatus(reqVO.getIds(), "正常");
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得商户信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-info:query')")
    public CommonResult<MerchantInfoPageRespVO> getMerchantInfo(@RequestParam("id") Long id) {
        MerchantInfoDO merchantInfo = merchantInfoService.getMerchantInfo(id);
        return success(BeanUtils.toBean(merchantInfo, MerchantInfoPageRespVO.class));
    }

    @PutMapping("/update")
    @Operation(summary = "更新商户信息")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-info:update')")
    public CommonResult<Boolean> updateMerchantInfo(@Valid @RequestBody MerchantInfoUpdateReqVO updateReqVO) {
        merchantInfoService.updateMerchantInfo(updateReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "商户信息统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-info:query')")
    public CommonResult<MerchantInfoChartRespVO> getMerchantInfoChart(@Valid MerchantInfoChartReqVO chartReqVO) {
        return success(merchantInfoService.getMerchantInfoChart(chartReqVO));
    }

//    @DeleteMapping("/delete")
//    @Operation(summary = "删除商户信息")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-info:delete')")
//    public CommonResult<Boolean> deleteMerchantInfo(@RequestParam("id") Long id) {
//        merchantInfoService.deleteMerchantInfo(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除商户信息")
//                @PreAuthorize("@ss.hasPermission('usermerchant:merchant-info:delete')")
//    public CommonResult<Boolean> deleteMerchantInfoList(@RequestParam("ids") List<Long> ids) {
//        merchantInfoService.deleteMerchantInfoListByIds(ids);
//        return success(true);
//    }

}