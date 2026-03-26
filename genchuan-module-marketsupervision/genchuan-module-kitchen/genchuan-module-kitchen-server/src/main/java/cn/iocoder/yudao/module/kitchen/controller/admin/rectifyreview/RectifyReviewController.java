package cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.*;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.add.AddRectifyReviewReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.add.AddRectifyReviewReqVO2;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.cancel.CancelReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.issue.IssueReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.upload.UploadEvidenceFileReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.upload.UploadEvidenceFileRespVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifyreview.RectifyReviewDO;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.pdf.PdfGenerator;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.verify.VerifyUtil;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.procom.aop.sysope.SysOpeLog;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.procom.aop.sysope.SysOpeModule;
import cn.iocoder.yudao.module.kitchen.service.rectifyreview.RectifyReviewService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 整改通知书复审台账")
@RestController
@RequestMapping("/kitchen/rectify-review")
@Validated
public class RectifyReviewController {

    @Resource
    private RectifyReviewService rectifyReviewService;



    @GetMapping("/download-notice-pdf-batch")
    @Operation(summary = "批量下载整改通知书PDF")
    @SysOpeLog(operObject = "整改复审台账")
    public ResponseEntity<byte[]> downloadRectifyNoticePdfBatch(@RequestParam("rectifyNoticeIds") List<Long> rectifyNoticeIds) throws IOException {
        return rectifyReviewService.downloadRectifyNoticePdfBatch(rectifyNoticeIds);
    }

    //新增.即预警页面的“生成复审台账”
    @PostMapping("/review-add2")
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-review:review-add')")
    @Operation(summary = "23新增2-整改通知复审记录操作")
    @SysOpeLog(operObject = "整改复审台账")
    public CommonResult<Long> reviewAdd2(@Valid @RequestBody AddRectifyReviewReqVO2 reqVO) {
        Long id = rectifyReviewService.reviewAdd2(reqVO);
        return success(id);
    }
    @PostMapping("/upload-evidence-file")
    @Operation(summary = "上传证据资料")
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-review:upload-evidence-file')")
    @SysOpeLog
    public CommonResult<UploadEvidenceFileRespVO> uploadEvidenceFile(
            @RequestPart("file") MultipartFile file,
            @Valid @ModelAttribute UploadEvidenceFileReqVO reqVO) {
        UploadEvidenceFileRespVO respVO = rectifyReviewService.uploadEvidenceFile(reqVO,file);
        return success(respVO);
    }

    //新增.即预警页面的“生成复审台账”
    @PostMapping("/review-add")
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-review:review-add')")
    @Operation(summary = "（勿用）新增-整改通知复审记录操作")
    @SysOpeLog
    public CommonResult<Long> reviewAdd(@Valid @RequestBody AddRectifyReviewReqVO reqVO) {
        Long id = rectifyReviewService.reviewAdd(reqVO);
        return success(id);
    }

    //撤销审核
    @PostMapping("/review-cancel")
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-review:review-cancel')")
    @Operation(summary = "撤销-整改通知书操作")
    @SysOpeLog
    public CommonResult<Long> reviewCancel(@Valid @RequestBody CancelReqVO reqVO) {
        Long id = rectifyReviewService.reviewCancel(reqVO);
        return success(id);
    }

    //下发整改通知书，返回增改通知书表的记录
    @PostMapping("/review-issue2")
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-review:review-issue')")
    @Operation(summary = "23下发整改通知书操作2")
    @SysOpeLog
    public CommonResult<Long> reviewIssue2(@Valid @RequestBody IssueReqVO reqVO) {
        Long rectifyNoticeId = rectifyReviewService.reviewIssue2(reqVO);
        return success(rectifyNoticeId);
    }
    //下发整改通知书，返回增改通知书表的记录
    @PostMapping("/review-issue")
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-review:review-issue')")
    @Operation(summary = "（勿用）下发整改通知书操作")
    @SysOpeLog
    public CommonResult<Long> reviewIssue(@Valid @RequestBody IssueReqVO reqVO) {
        Long rectifyNoticeId = rectifyReviewService.reviewIssue(reqVO);
        return success(rectifyNoticeId);
    }


    @Operation(summary = "批量查看整改复审台账证据分页")
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-review:batch-view-evidence')")
    @PostMapping("/batch-view-evidence")
    @SysOpeLog
    public CommonResult<PageResult<RectifyEvidenceVO>> getBatchEvidence(
            @Valid @RequestBody BatchEvidenceRequestVO reqVO) {

        // 调用 Service 获取分页数据
        PageResult<RectifyEvidenceVO> pageResult = rectifyReviewService.getBatchEvidence(reqVO);

        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出 Excel")
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-review:export')")
    @ApiAccessLog(operateType = EXPORT)
    @SysOpeLog(operObject = "整改复审台账",operType = "批量操作")
    public void exportRoadArchiveExcel(@Valid RectifyReviewLedgerPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
//        // 假设前端传的是逗号分隔的字符串
//        if (pageReqVO.getIdList() != null && pageReqVO.getIdList().size() == 1) {
//            String s = pageReqVO.getIdList().get(0).toString();
//            if (s.contains(",")) {
//                List<Long> list = Arrays.stream(s.split(","))
//                        .map(Long::parseLong)
//                        .collect(Collectors.toList());
//                pageReqVO.setIdList(list);
//            }
//        }
        // 0. 配置
        String inputFileName = "导出Excel文件_";

        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RectifyReviewLedgerRespVO> list = rectifyReviewService.getRectifyReviewLedgerPage(pageReqVO).getList();

        // 1、强制设置响应头，确保浏览器触发下载
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        // 2、动态生成文件名，带上当前日期
        String dateStr = java.time.LocalDate.now().toString(); // 例如 "2026-03-10"
        String fileOriginName = inputFileName + dateStr + ".xls";
        String fileName = URLEncoder.encode(fileOriginName, StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8","");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);

        // 3、调用 ExcelUtils.write（保持原方法不改）
        ExcelUtils.write(response, "归档.xls", "数据", RectifyReviewLedgerRespVO.class,
                BeanUtils.toBean(list, RectifyReviewLedgerRespVO.class));
    }

    @GetMapping("/ledger-page")
    @Operation(summary = "分页-整改通知书复审台账表")
//    @PreAuthorize("@ss.hasPermission('kitchen:rectify-review:query')")
//    @SysOpeLog(operType = "审核", operObject = "企业整改记录", operDesc = "整改审核通过")
    @SysOpeLog
    public CommonResult<PageResult<RectifyReviewLedgerRespVO>> getRectifyReviewLedgerPage(
            @Valid RectifyReviewLedgerPageReqVO reqVO) {

        PageResult<RectifyReviewLedgerRespVO> pageResult =
                rectifyReviewService.getRectifyReviewLedgerPage(reqVO);

        return success(pageResult);
    }
    @PostMapping("/create")
    @Operation(summary = "（勿用）创建整改通知书复审台账")
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-review:create')")
    @SysOpeLog
    public CommonResult<Long> createRectifyReview(@Valid @RequestBody RectifyReviewSaveReqVO createReqVO) {
        return success(rectifyReviewService.createRectifyReview(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新整改通知书复审台账")
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-review:update')")
    @SysOpeLog
    public CommonResult<Boolean> updateRectifyReview(@Valid @RequestBody RectifyReviewSaveReqVO updateReqVO) {
        rectifyReviewService.updateRectifyReview(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除整改通知书复审台账")
    @Parameter(name = "id", description = "编号", required = true)
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-review:delete')")
    @SysOpeLog
    public CommonResult<Boolean> deleteRectifyReview(@RequestParam("id") Long id) {
        rectifyReviewService.deleteRectifyReview(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得整改通知书复审台账")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-review:query')")
    @SysOpeLog
    public CommonResult<RectifyReviewRespVO> getRectifyReview(@RequestParam("id") Long id) {
        RectifyReviewDO rectifyReview = rectifyReviewService.getRectifyReview(id);
        return success(BeanUtils.toBean(rectifyReview, RectifyReviewRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "(勿用）获得整改通知书复审台账分页")
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-review:query')")
    @SysOpeLog
    public CommonResult<PageResult<RectifyReviewRespVO>> getRectifyReviewPage(@Valid RectifyReviewPageReqVO pageReqVO) {
        PageResult<RectifyReviewDO> pageResult = rectifyReviewService.getRectifyReviewPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RectifyReviewRespVO.class));
    }

//    @GetMapping("/export-excel")
//    @Operation(summary = "导出整改通知书复审台账 Excel")
//    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-review:export')")
//    @ApiAccessLog(operateType = EXPORT)
//    public void exportRectifyReviewExcel(@Valid RectifyReviewPageReqVO pageReqVO,
//              HttpServletResponse response) throws IOException {
//        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<RectifyReviewDO> list = rectifyReviewService.getRectifyReviewPage(pageReqVO).getList();
//        // 导出 Excel
//        ExcelUtils.write(response, "整改通知书复审台账.xls", "数据", RectifyReviewRespVO.class,
//                        BeanUtils.toBean(list, RectifyReviewRespVO.class));
//    }

}
