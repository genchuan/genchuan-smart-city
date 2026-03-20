package cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo.RectifyNoticePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo.RectifyNoticeRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo.RectifyNoticeSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo.RectifyNoticeUpdateReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifynotice.RectifyNoticeDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifyreview.RectifyReviewDO;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.pdf.PdfGenerator;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.verify.VerifyUtil;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.procom.aop.sysope.SysOpeLog;
import cn.iocoder.yudao.module.kitchen.service.rectifynotice.RectifyNoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 整改通知书")
@RestController
@RequestMapping("/kitchen/rectify-notice")
@Validated
public class RectifyNoticeController {

    @Resource
    private RectifyNoticeService rectifyNoticeService;

    @GetMapping("/download-pdf")
    @Operation(summary = "下载整改通知书 PDF")
    @SysOpeLog
    public ResponseEntity<byte[]> downloadRectifyNoticePdf(@RequestParam("rectifyNoticeId") Long rectifyNoticeId) throws IOException {
        ResponseEntity<byte[]> byteResult = rectifyNoticeService.downloadRectifyNoticePdf(rectifyNoticeId);

        return byteResult;
    }
    @PostMapping("/create")
    @Operation(summary = "创建整改通知书")
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-notice:create')")
    @SysOpeLog
    public CommonResult<Long> createRectifyNotice(@Valid @RequestBody RectifyNoticeSaveReqVO createReqVO) {
        return success(rectifyNoticeService.createRectifyNotice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新整改通知书")
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-notice:update')")
    @SysOpeLog
    public CommonResult<Boolean> updateRectifyNotice(@Valid @RequestBody RectifyNoticeUpdateReqVO updateReqVO) {
        rectifyNoticeService.updateRectifyNotice(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除整改通知书")
    @Parameter(name = "id", description = "编号", required = true)
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-notice:delete')")
    @SysOpeLog
    public CommonResult<Boolean> deleteRectifyNotice(@RequestParam("id") Long id) {
        rectifyNoticeService.deleteRectifyNotice(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得整改通知书")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-notice:query')")
    @SysOpeLog
    public CommonResult<RectifyNoticeRespVO> getRectifyNotice(@RequestParam("id") Long id) {
        RectifyNoticeDO rectifyNotice = rectifyNoticeService.getRectifyNotice(id);
        return success(BeanUtils.toBean(rectifyNotice, RectifyNoticeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得整改通知书分页")
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-notice:query')")
    @SysOpeLog
    public CommonResult<PageResult<RectifyNoticeRespVO>> getRectifyNoticePage(@Valid RectifyNoticePageReqVO pageReqVO) {
        PageResult<RectifyNoticeDO> pageResult = rectifyNoticeService.getRectifyNoticePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RectifyNoticeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出整改通知书 Excel")
    //@PreAuthorize("@ss.hasPermission('kitchen:rectify-notice:export')")
    @ApiAccessLog(operateType = EXPORT)
    @SysOpeLog
    public void exportRectifyNoticeExcel(@Valid RectifyNoticePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RectifyNoticeDO> list = rectifyNoticeService.getRectifyNoticePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "整改通知书.xls", "数据", RectifyNoticeRespVO.class,
                        BeanUtils.toBean(list, RectifyNoticeRespVO.class));
    }

}
