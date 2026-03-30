package cn.iocoder.yudao.module.kitchen.controller.admin.lawreviewledger;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.lawreviewledger.vo.LawReviewLedgerPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.lawreviewledger.vo.LawReviewLedgerRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.lawreviewledger.vo.LawReviewLedgerSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.lawreviewledger.LawReviewLedgerDO;
import cn.iocoder.yudao.module.kitchen.service.lawreviewledger.LawReviewLedgerService;
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


@Tag(name = "管理后台 - 执法复审总台账")
@RestController
@RequestMapping("/kitchen/law-review-ledger")
@Validated
public class LawReviewLedgerController {

    @Resource
    private LawReviewLedgerService lawReviewLedgerService;

    @PostMapping("/create")
    @Operation(summary = "创建执法复审总台账")
    // //@PreAuthorize("@ss.hasPermission('kitchen:law-review-ledger:create')")
    public CommonResult<Long> createLawReviewLedger(@Valid @RequestBody LawReviewLedgerSaveReqVO createReqVO) {
        return success(lawReviewLedgerService.createLawReviewLedger(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新执法复审总台账")
    //@PreAuthorize("@ss.hasPermission('kitchen:law-review-ledger:update')")
    public CommonResult<Boolean> updateLawReviewLedger(@Valid @RequestBody LawReviewLedgerSaveReqVO updateReqVO) {
        lawReviewLedgerService.updateLawReviewLedger(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除执法复审总台账")
    @Parameter(name = "id", description = "编号", required = true)
    //@PreAuthorize("@ss.hasPermission('kitchen:law-review-ledger:delete')")
    public CommonResult<Boolean> deleteLawReviewLedger(@RequestParam("id") Long id) {
        lawReviewLedgerService.deleteLawReviewLedger(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得执法复审总台账")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('kitchen:law-review-ledger:query')")
    public CommonResult<LawReviewLedgerRespVO> getLawReviewLedger(@RequestParam("id") Long id) {
        LawReviewLedgerDO lawReviewLedger = lawReviewLedgerService.getLawReviewLedger(id);
        return success(BeanUtils.toBean(lawReviewLedger, LawReviewLedgerRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得执法复审总台账分页")
    //@PreAuthorize("@ss.hasPermission('kitchen:law-review-ledger:query')")
    public CommonResult<PageResult<LawReviewLedgerRespVO>> getLawReviewLedgerPage(@Valid LawReviewLedgerPageReqVO pageReqVO) {
        PageResult<LawReviewLedgerDO> pageResult = lawReviewLedgerService.getLawReviewLedgerPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, LawReviewLedgerRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出执法复审总台账 Excel")
    //@PreAuthorize("@ss.hasPermission('kitchen:law-review-ledger:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportLawReviewLedgerExcel(@Valid LawReviewLedgerPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<LawReviewLedgerDO> list = lawReviewLedgerService.getLawReviewLedgerPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "执法复审总台账.xls", "数据", LawReviewLedgerRespVO.class,
                        BeanUtils.toBean(list, LawReviewLedgerRespVO.class));
    }

}
