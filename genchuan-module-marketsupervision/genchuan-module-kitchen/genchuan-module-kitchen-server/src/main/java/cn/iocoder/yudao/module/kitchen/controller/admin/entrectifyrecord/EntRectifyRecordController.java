package cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord;

import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.EntRectifyRecordPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.EntRectifyRecordRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.EntRectifyRecordSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.add.AddEntRectifyRecordReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.review.ReviewApproveReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.review.ReviewRejectReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.upload.UploadFileReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.upload.UploadFileRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.upload.UploadEvidenceFileReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.upload.UploadEvidenceFileRespVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.entrectifyrecord.EntRectifyRecordDO;
import cn.iocoder.yudao.module.kitchen.service.entrectifyrecord.EntRectifyRecordService;
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
import org.springframework.web.multipart.MultipartFile;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;


@Tag(name = "管理后台 - 企业整改记录")
@RestController
@RequestMapping("/kitchen/ent-rectify-record")
@Validated
public class EntRectifyRecordController {

    @Resource
    private EntRectifyRecordService entRectifyRecordService;

    @PostMapping("/review-reject")
    @Operation(summary = "审核不通过")
    //@PreAuthorize("@ss.hasPermission('kitchen:ent-rectify-record:review-reject')")
    public CommonResult<Boolean> reviewReject(
            @Valid @RequestBody ReviewRejectReq reqVO) {
        System.out.println("cs2026-03-24 09:03:49:req"+reqVO);
        Boolean flag = entRectifyRecordService.reviewReject(reqVO);
        return success(flag);
    }
    @PostMapping("/review-approve")
    @Operation(summary = "审核通过")
    //@PreAuthorize("@ss.hasPermission('kitchen:ent-rectify-record:review-approve')")
    public CommonResult<Boolean> reviewApprove(
            @Valid @RequestBody ReviewApproveReq reqVO) {
        Boolean flag = entRectifyRecordService.reviewApprove(reqVO);
        return success(flag);
    }
    @PostMapping("/upload-file")
    @Operation(summary = "上传资料")
    //@PreAuthorize("@ss.hasPermission('kitchen:ent-rectify-record:upload-file')")
    public CommonResult<UploadFileRespVO> uploadEvidenceFile(
            @RequestPart("file") MultipartFile file,
            @Valid @ModelAttribute UploadFileReqVO reqVO) {
        UploadFileRespVO respVO = entRectifyRecordService.uploadEvidenceFile(reqVO,file);
        return success(respVO);
    }
    @PostMapping("/add")
    @Operation(summary = "新增-企业整改记录[送达整改通知书]")
    //@PreAuthorize("@ss.hasPermission('kitchen:ent-rectify-record:create')")
    public CommonResult<Long> addEntRectifyRecord(@Valid @RequestBody AddEntRectifyRecordReqVO createReqVO) {
        Long id = entRectifyRecordService.addEntRectifyRecord(createReqVO);
        return success(id);
    }
    @PostMapping("/create")
    @Operation(summary = "（勿用）创建企业整改记录")
    //@PreAuthorize("@ss.hasPermission('kitchen:ent-rectify-record:create')")
    public CommonResult<Long> createEntRectifyRecord(@Valid @RequestBody EntRectifyRecordSaveReqVO createReqVO) {
        return success(entRectifyRecordService.createEntRectifyRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新企业整改记录")
    //@PreAuthorize("@ss.hasPermission('kitchen:ent-rectify-record:update')")
    public CommonResult<Boolean> updateEntRectifyRecord(@Valid @RequestBody EntRectifyRecordSaveReqVO updateReqVO) {
        entRectifyRecordService.updateEntRectifyRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除企业整改记录")
    @Parameter(name = "id", description = "编号", required = true)
    //@PreAuthorize("@ss.hasPermission('kitchen:ent-rectify-record:delete')")
    public CommonResult<Boolean> deleteEntRectifyRecord(@RequestParam("id") Long id) {
        entRectifyRecordService.deleteEntRectifyRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得企业整改记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('kitchen:ent-rectify-record:query')")
    public CommonResult<EntRectifyRecordRespVO> getEntRectifyRecord(@RequestParam("id") Long id) {
        EntRectifyRecordDO entRectifyRecord = entRectifyRecordService.getEntRectifyRecord(id);
        return success(BeanUtils.toBean(entRectifyRecord, EntRectifyRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得企业整改记录分页")
    //@PreAuthorize("@ss.hasPermission('kitchen:ent-rectify-record:query')")
    public CommonResult<PageResult<EntRectifyRecordRespVO>> getEntRectifyRecordPage(@Valid EntRectifyRecordPageReqVO pageReqVO) {
        PageResult<EntRectifyRecordDO> pageResult = entRectifyRecordService.getEntRectifyRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EntRectifyRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出企业整改记录 Excel")
    //@PreAuthorize("@ss.hasPermission('kitchen:ent-rectify-record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEntRectifyRecordExcel(@Valid EntRectifyRecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EntRectifyRecordDO> list = entRectifyRecordService.getEntRectifyRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "企业整改记录.xls", "数据", EntRectifyRecordRespVO.class,
                        BeanUtils.toBean(list, EntRectifyRecordRespVO.class));
    }

}
