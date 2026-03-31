package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletComplaintDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletComplaintDetailDO;
import cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletcomplaint.ToiletComplaintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "环境卫生管理 - 公厕投诉")
@RestController
@RequestMapping("/envirhealth/toilet-complaint")
@Validated
public class ToiletComplaintController {

    @Resource
    private ToiletComplaintService toiletComplaintService;

    @PostMapping("/create")
    @Operation(summary = "创建公厕投诉")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-complaint:create')")
    public CommonResult<Long> createToiletComplaint(@Valid @RequestBody ToiletComplaintSaveReqVO createReqVO) {
        return success(toiletComplaintService.createToiletComplaint(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新公厕投诉")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-complaint:update')")
    public CommonResult<Boolean> updateToiletComplaint(@Valid @RequestBody ToiletComplaintSaveReqVO updateReqVO) {
        toiletComplaintService.updateToiletComplaint(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除公厕投诉")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-complaint:delete')")
    public CommonResult<Boolean> deleteToiletComplaint(@RequestParam("id") Long id) {
        toiletComplaintService.deleteToiletComplaint(id);
        return success(true);
    }

    @DeleteMapping("/delete-batch")
    @Operation(summary = "批量删除公厕投诉")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('health:toilet-complaint:delete')")
    public CommonResult<Boolean> deleteToiletComplaintBatch(@RequestBody List<Long> ids) {
        toiletComplaintService.deleteToiletComplaintBatch(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得公厕投诉")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-complaint:query')")
    public CommonResult<ToiletComplaintRespVO> getToiletComplaint(@RequestParam("id") Long id) {
        ToiletComplaintDO toiletComplaint = toiletComplaintService.getToiletComplaint(id);
        return success(BeanUtils.toBean(toiletComplaint, ToiletComplaintRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得公厕投诉分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-complaint:query')")
    public CommonResult<PageResult<ToiletComplaintRespVO>> getToiletComplaintPage(@Valid ToiletComplaintPageReqVO pageReqVO) {
        PageResult<ToiletComplaintDO> pageResult = toiletComplaintService.getToiletComplaintPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ToiletComplaintRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出公厕投诉 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-complaint:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportToiletComplaintExcel(@Valid ToiletComplaintPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ToiletComplaintDO> list = toiletComplaintService.getToiletComplaintPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "公厕投诉.xls", "数据", ToiletComplaintRespVO.class,
                        BeanUtils.toBean(list, ToiletComplaintRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得公厕投诉详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-complaint:query')")
    public CommonResult<PageResult<ToiletComplaintDetailDO>> getToiletComplaintDetailPage(
            @Valid ToiletComplaintPageReqVO pageReqVO) {
        PageResult<ToiletComplaintDetailDO> pageResult =
                toiletComplaintService.getToiletComplaintDetailPage(pageReqVO);

        return success(pageResult);
    }

   /* @PostMapping("/upload-photos")
    @Operation(summary = "上传多张整改图片")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-complaint:update')")
    public CommonResult<List<String>> uploadPhotos(
            @RequestParam("id") Long id,
            @RequestParam("files") MultipartFile[] files) {
        List<String> photoUrls = toiletComplaintService.uploadPhotos(id, Arrays.asList(files));
        return success(photoUrls);
    }

    @GetMapping("/photos/{id}")
    @Operation(summary = "获取整改图片列表")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-complaint:query')")
    public CommonResult<List<String>> getPhotos(@PathVariable Long id) {
        List<String> photos = toiletComplaintService.getPhotos(id);
        return success(photos);
    }

    @DeleteMapping("/photo")
    @Operation(summary = "删除整改图片")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-complaint:update')")
    public CommonResult<Boolean> deletePhoto(
            @RequestParam("id") Long id,
            @RequestParam("photoUrl") String photoUrl) {
        toiletComplaintService.deletePhoto(id, photoUrl);
        return success(true);
    }*/

    @PostMapping("/batch-handle")
    @Operation(summary = "批量处理公厕投诉")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-complaint:update')")
    public CommonResult<Boolean> batchHandle(@Valid @RequestBody ToiletComplaintBatchHandleReqVO reqVO) {
        toiletComplaintService.batchUpdateDispatchStatus(reqVO);
        return success(true);
    }

    @GetMapping("/chart/pending")
    @Operation(summary = "卡片/圆环图/柱状图统计(待处置)")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-complaint:query')")
    public CommonResult<ToiletComplaintPendingRespVO> getPending() {
        return success(toiletComplaintService.getPending());
    }
}