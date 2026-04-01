package cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger;


import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.PunishReviewLedgerPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.PunishReviewLedgerRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.PunishReviewLedgerSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.add.AddPunishReviewLedgerReq;

import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.cancel.CancelReqVO;

import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.issue.IssueReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.upload.UploadFileReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.upload.UploadFileRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.RectifyReviewLedgerPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.RectifyReviewLedgerRespVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.punishreviewledger.PunishReviewLedgerDO;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.codeutils.CodeQueryUtils;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.procom.aop.sysope.SysOpeLog;
import cn.iocoder.yudao.module.kitchen.service.punishreviewledger.PunishReviewLedgerService;
import org.springframework.http.ResponseEntity;
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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import org.springframework.web.multipart.MultipartFile;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;


@Tag(name = "管理后台 - 处罚通知书复审台账")
@RestController
@RequestMapping("/kitchen/punish-review-ledger")
@Validated
public class PunishReviewLedgerController {

    @Resource
    private PunishReviewLedgerService punishReviewLedgerService;

    @GetMapping("/export-excel")
    @Operation(summary = "导出 Excel")
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:export-excel')")
    @ApiAccessLog(operateType = EXPORT)
    @SysOpeLog(operObject = "处罚复审台账",operType = "批量导出Excel")
    public void exportRoadArchiveExcel(@Valid PunishReviewLedgerPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        // 0. 配置
        String inputFileName = "导出Excel文件_";

        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PunishReviewLedgerRespVO> list = punishReviewLedgerService.getPunishReviewLedgerPage(pageReqVO).getList();

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
        ExcelUtils.write(response, "归档.xls", "数据", PunishReviewLedgerRespVO.class,
                BeanUtils.toBean(list, PunishReviewLedgerRespVO.class));
    }
    @GetMapping("/download-notice-pdf-batch")
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:download-notice-pdf-batch')")
    @Operation(summary = "批量下载整改通知书PDF")
    @SysOpeLog(operObject = "处罚复审台账",operType = "批量下载PDF")
    public ResponseEntity<byte[]> downloadRectifyNoticePdfBatch(@RequestParam("punishNoticeIds") List<Long> punishNoticeIds) throws IOException {
        return punishReviewLedgerService.downloadRectifyNoticePdfBatch(punishNoticeIds);
    }
    @PostMapping("/upload-file")
    @Operation(summary = "上传资料")
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:upload-file')")
    @SysOpeLog(operType = "上传证据资料",operObject = "处罚复审台账")
    public CommonResult<UploadFileRespVO> uploadEvidenceFile(
            @RequestPart("file") MultipartFile file,
            @Valid @ModelAttribute UploadFileReqVO reqVO) {
        UploadFileRespVO respVO = punishReviewLedgerService.uploadEvidenceFile(reqVO,file);
        return success(respVO);
    }
    /**
     * 下发处罚通知书
     */
    @PostMapping("/review-issue")
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:review-issue')")
    @Operation(summary = "下发处罚通知书操作")
    @SysOpeLog(operObject = "处罚复审台账",operType = "下发")
    public CommonResult<Long> reviewIssue(@Valid @RequestBody IssueReqVO reqVO) {
        Long punishNoticeId = punishReviewLedgerService.reviewIssue(reqVO);
        return success(punishNoticeId);
    }
    /**
     * 撤销处罚复审台账
     */
    @PostMapping("/review-cancel")
    @Operation(summary = "撤销-处罚复审台账操作")
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:review-cancel')")
    @SysOpeLog(operObject = "处罚复审台账")
    public CommonResult<Long> reviewCancel(@Valid @RequestBody CancelReqVO reqVO) {
        Long id = punishReviewLedgerService.reviewCancel(reqVO);
        return success(id);
    }
    @PostMapping("/add")
    @Operation(summary = "创建处罚通知书复审台账（精简入参，自动补全）")
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:create')")
    @SysOpeLog
    public CommonResult<Long> addPunishReviewLedger(@Valid @RequestBody AddPunishReviewLedgerReq reqVO) {
        Long id = punishReviewLedgerService.addPunishReviewLedger(reqVO);
        return success(id);
    }
    @PostMapping("/create")
    @Operation(summary = "（勿用）创建处罚通知书复审台账")
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:create')")
    @SysOpeLog
    public CommonResult<Long> createPunishReviewLedger(@Valid @RequestBody PunishReviewLedgerSaveReqVO createReqVO) {
        return success(punishReviewLedgerService.createPunishReviewLedger(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新处罚通知书复审台账")
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:update')")
    @SysOpeLog
    public CommonResult<Boolean> updatePunishReviewLedger(@Valid @RequestBody PunishReviewLedgerSaveReqVO updateReqVO) {
        punishReviewLedgerService.updatePunishReviewLedger(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除处罚通知书复审台账")
    @Parameter(name = "id", description = "编号", required = true)
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:delete')")
    @SysOpeLog
    public CommonResult<Boolean> deletePunishReviewLedger(@RequestParam("id") Long id) {
        punishReviewLedgerService.deletePunishReviewLedger(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得处罚通知书复审台账")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:query')")
    @SysOpeLog
    public CommonResult<PunishReviewLedgerRespVO> getPunishReviewLedger(@RequestParam("id") Long id) {
        PunishReviewLedgerDO punishReviewLedger = punishReviewLedgerService.getPunishReviewLedger(id);
        return success(BeanUtils.toBean(punishReviewLedger, PunishReviewLedgerRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得处罚通知书复审台账分页")
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:query')")
    @SysOpeLog
    public CommonResult<PageResult<PunishReviewLedgerRespVO>> getPunishReviewLedgerPage(@Valid PunishReviewLedgerPageReqVO pageReqVO) {
        PageResult<PunishReviewLedgerRespVO> pageResult = punishReviewLedgerService.getPunishReviewLedgerPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PunishReviewLedgerRespVO.class));
    }

//    @GetMapping("/export-excel")
//    @Operation(summary = "（勿用）导出处罚通知书复审台账 Excel")
//    //@PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:export')")
//    @ApiAccessLog(operateType = EXPORT)
//    @SysOpeLog
//    public void exportPunishReviewLedgerExcel(@Valid PunishReviewLedgerPageReqVO pageReqVO,
//              HttpServletResponse response) throws IOException {
//        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<PunishReviewLedgerRespVO> list = punishReviewLedgerService.getPunishReviewLedgerPage(pageReqVO).getList();
//        // 导出 Excel
//        ExcelUtils.write(response, "处罚通知书复审台账.xls", "数据", PunishReviewLedgerRespVO.class,
//                        BeanUtils.toBean(list, PunishReviewLedgerRespVO.class));
//    }

}
