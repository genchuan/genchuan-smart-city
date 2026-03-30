package cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.archiverecord;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.archiverecord.vo.ArchiveRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.archiverecord.vo.ArchiveRecordRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.archiverecord.vo.ArchiveRecordSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.archiverecord.ArchiveRecordDO;
import cn.iocoder.yudao.module.evaluate.service.archiverecord.ArchiveRecordService;
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

@Tag(name = "管理后台 - 评价结果存档")
@RestController
@RequestMapping("/evaluate/archive-record")
@Validated
public class ArchiveRecordController {

    @Resource
    private ArchiveRecordService archiveRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建评价结果存档")
    @PreAuthorize("@ss.hasPermission('evaluate:archive-record:create')")
    public CommonResult<Long> createArchiveRecord(@Valid @RequestBody ArchiveRecordSaveReqVO createReqVO) {
        return success(archiveRecordService.createArchiveRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价结果存档")
    @PreAuthorize("@ss.hasPermission('evaluate:archive-record:update')")
    public CommonResult<Boolean> updateArchiveRecord(@Valid @RequestBody ArchiveRecordSaveReqVO updateReqVO) {
        archiveRecordService.updateArchiveRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价结果存档")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:archive-record:delete')")
    public CommonResult<Boolean> deleteArchiveRecord(@RequestParam("id") Long id) {
        archiveRecordService.deleteArchiveRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价结果存档")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:archive-record:query')")
    public CommonResult<ArchiveRecordRespVO> getArchiveRecord(@RequestParam("id") Long id) {
        ArchiveRecordDO archiveRecord = archiveRecordService.getArchiveRecord(id);
        return success(BeanUtils.toBean(archiveRecord, ArchiveRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价结果存档分页")
    @PreAuthorize("@ss.hasPermission('evaluate:archive-record:query')")
    public CommonResult<PageResult<ArchiveRecordRespVO>> getArchiveRecordPage(@Valid ArchiveRecordPageReqVO pageReqVO) {
        PageResult<ArchiveRecordDO> pageResult = archiveRecordService.getArchiveRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ArchiveRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价结果存档 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:archive-record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportArchiveRecordExcel(@Valid ArchiveRecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ArchiveRecordDO> list = archiveRecordService.getArchiveRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价结果存档.xls", "数据", ArchiveRecordRespVO.class,
                        BeanUtils.toBean(list, ArchiveRecordRespVO.class));
    }

}