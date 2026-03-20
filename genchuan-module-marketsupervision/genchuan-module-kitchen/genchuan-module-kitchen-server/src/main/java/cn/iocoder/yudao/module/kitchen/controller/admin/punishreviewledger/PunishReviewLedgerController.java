package cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger;

import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.PunishReviewLedgerPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.PunishReviewLedgerRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.PunishReviewLedgerSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.add.AddPunishReviewLedgerReq;

import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.cancel.CancelReqVO;

import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.issue.IssueReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.punishreviewledger.PunishReviewLedgerDO;
import cn.iocoder.yudao.module.kitchen.service.punishreviewledger.PunishReviewLedgerService;
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


@Tag(name = "管理后台 - 处罚通知书复审台账")
@RestController
@RequestMapping("/kitchen/punish-review-ledger")
@Validated
public class PunishReviewLedgerController {

    @Resource
    private PunishReviewLedgerService punishReviewLedgerService;

    /**
     * 下发处罚通知书
     */
    @PostMapping("/review-issue")
    @PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:review-issue')")
    @Operation(summary = "下发处罚通知书操作")
    public CommonResult<Long> reviewIssue(@Valid @RequestBody IssueReqVO reqVO) {
        Long punishNoticeId = punishReviewLedgerService.reviewIssue(reqVO);
        return success(punishNoticeId);
    }
    /**
     * 撤销处罚复审台账
     */
    @PostMapping("/review-cancel")
    @Operation(summary = "撤销-处罚复审台账操作")
    @PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:review-cancel')")
    public CommonResult<Long> reviewCancel(@Valid @RequestBody CancelReqVO reqVO) {
        Long id = punishReviewLedgerService.reviewCancel(reqVO);
        return success(id);
    }
    @PostMapping("/add")
    @Operation(summary = "创建处罚通知书复审台账（精简入参，自动补全）")
    @PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:create')")
    public CommonResult<Long> addPunishReviewLedger(@Valid @RequestBody AddPunishReviewLedgerReq reqVO) {
        Long id = punishReviewLedgerService.addPunishReviewLedger(reqVO);
        return success(id);
    }
    @PostMapping("/create")
    @Operation(summary = "（勿用）创建处罚通知书复审台账")
    @PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:create')")
    public CommonResult<Long> createPunishReviewLedger(@Valid @RequestBody PunishReviewLedgerSaveReqVO createReqVO) {
        return success(punishReviewLedgerService.createPunishReviewLedger(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新处罚通知书复审台账")
    @PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:update')")
    public CommonResult<Boolean> updatePunishReviewLedger(@Valid @RequestBody PunishReviewLedgerSaveReqVO updateReqVO) {
        punishReviewLedgerService.updatePunishReviewLedger(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除处罚通知书复审台账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:delete')")
    public CommonResult<Boolean> deletePunishReviewLedger(@RequestParam("id") Long id) {
        punishReviewLedgerService.deletePunishReviewLedger(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得处罚通知书复审台账")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:query')")
    public CommonResult<PunishReviewLedgerRespVO> getPunishReviewLedger(@RequestParam("id") Long id) {
        PunishReviewLedgerDO punishReviewLedger = punishReviewLedgerService.getPunishReviewLedger(id);
        return success(BeanUtils.toBean(punishReviewLedger, PunishReviewLedgerRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得处罚通知书复审台账分页")
    @PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:query')")
    public CommonResult<PageResult<PunishReviewLedgerRespVO>> getPunishReviewLedgerPage(@Valid PunishReviewLedgerPageReqVO pageReqVO) {
        PageResult<PunishReviewLedgerDO> pageResult = punishReviewLedgerService.getPunishReviewLedgerPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PunishReviewLedgerRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出处罚通知书复审台账 Excel")
    @PreAuthorize("@ss.hasPermission('kitchen:punish-review-ledger:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPunishReviewLedgerExcel(@Valid PunishReviewLedgerPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PunishReviewLedgerDO> list = punishReviewLedgerService.getPunishReviewLedgerPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "处罚通知书复审台账.xls", "数据", PunishReviewLedgerRespVO.class,
                        BeanUtils.toBean(list, PunishReviewLedgerRespVO.class));
    }

}
