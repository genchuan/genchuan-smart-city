package cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice;

import cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.PunishNoticePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.PunishNoticeRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.PunishNoticeSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.add.AddPunishNoticeReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.template.DraftPunishNoticeReq;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.punishnotice.PunishNoticeDO;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.procom.aop.sysope.SysOpeLog;
import cn.iocoder.yudao.module.kitchen.service.punishnotice.PunishNoticeService;
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


@Tag(name = "管理后台 - 处罚通知书")
@RestController
@RequestMapping("/kitchen/punish-notice")
@Validated
public class PunishNoticeController {

    @Resource
    private PunishNoticeService punishNoticeService;

    @GetMapping("/download-pdf")
    @Operation(summary = "下载整改通知书 PDF")
    @SysOpeLog
    public ResponseEntity<byte[]> downloadPunishNoticePdf(@RequestParam("punishNoticeId") Long punishNoticeId) throws IOException {
        ResponseEntity<byte[]> byteResult = punishNoticeService.downloadRectifyNoticePdf(punishNoticeId);

        return byteResult;
    }

    //草拟通知书
    @PostMapping("/draft")
    @Operation(summary = "草拟通知书（HTML）")
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-notice:draft')")
    @SysOpeLog
    public CommonResult<String> draftPunishNotice(@Valid @RequestBody DraftPunishNoticeReq reqVO) {
        // 调用 Service 生成草稿 HTML
        String draftHtml = punishNoticeService.generatePunishNoticeDraft(reqVO);
        return success(draftHtml);
    }
    // 新增（区分系统默认 create）
    @PostMapping("/add")
    @Operation(summary = "新增处罚通知书（精简入参，自动补全）")
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-notice:create')")
    @SysOpeLog
    public CommonResult<Long> addPunishNotice(@Valid @RequestBody AddPunishNoticeReq reqVO) {
        Long id = punishNoticeService.addPunishNotice(reqVO);
        return success(id);
    }
    @PostMapping("/create")
    @Operation(summary = "（勿用）创建处罚通知书")
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-notice:create')")
    @SysOpeLog
    public CommonResult<Long> createPunishNotice(@Valid @RequestBody PunishNoticeSaveReqVO createReqVO) {
        return success(punishNoticeService.createPunishNotice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新处罚通知书")
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-notice:update')")
    @SysOpeLog
    public CommonResult<Boolean> updatePunishNotice(@Valid @RequestBody PunishNoticeSaveReqVO updateReqVO) {
        punishNoticeService.updatePunishNotice(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除处罚通知书")
    @Parameter(name = "id", description = "编号", required = true)
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-notice:delete')")
    @SysOpeLog
    public CommonResult<Boolean> deletePunishNotice(@RequestParam("id") Long id) {
        punishNoticeService.deletePunishNotice(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得处罚通知书")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-notice:query')")
    @SysOpeLog
    public CommonResult<PunishNoticeRespVO> getPunishNotice(@RequestParam("id") Long id) {
        PunishNoticeDO punishNotice = punishNoticeService.getPunishNotice(id);
        return success(BeanUtils.toBean(punishNotice, PunishNoticeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得处罚通知书分页")
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-notice:query')")
    @SysOpeLog
    public CommonResult<PageResult<PunishNoticeRespVO>> getPunishNoticePage(@Valid PunishNoticePageReqVO pageReqVO) {
        PageResult<PunishNoticeDO> pageResult = punishNoticeService.getPunishNoticePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PunishNoticeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出处罚通知书 Excel")
    //@PreAuthorize("@ss.hasPermission('kitchen:punish-notice:export')")
    @ApiAccessLog(operateType = EXPORT)
    @SysOpeLog
    public void exportPunishNoticeExcel(@Valid PunishNoticePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PunishNoticeDO> list = punishNoticeService.getPunishNoticePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "处罚通知书.xls", "数据", PunishNoticeRespVO.class,
                        BeanUtils.toBean(list, PunishNoticeRespVO.class));
    }

}
