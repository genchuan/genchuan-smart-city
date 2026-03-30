package cn.iocoder.yudao.module.facility.controller.admin.sysarchive;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.facility.controller.admin.sysarchive.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysarchive.SysArchiveDO;
import cn.iocoder.yudao.module.facility.service.sysarchive.SysArchiveService;
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


@Tag(name = "管理后台 - 归档")
@RestController
@RequestMapping("/facility/sys-archive")
@Validated
public class SysArchiveController {

    @Resource
    private SysArchiveService sysArchiveService;


    @PostMapping("/create")
    @Operation(summary = "创建归档")
    @PreAuthorize("@ss.hasPermission('facility:sys-archive:create')")
    public CommonResult<Long> createSysArchive(@Valid @RequestBody SysArchiveSaveReqVO createReqVO) {
        return success(sysArchiveService.createSysArchive(createReqVO));
    }

    //可用4512
    @GetMapping("/download-archive-files")
    @Operation(summary = "下载归档资料")
    @PreAuthorize("@ss.hasPermission('facility:sys-archive:download-archive-files')")
    @ApiAccessLog(operateType = EXPORT)
    public void downloadArchiveFiles(DownloadArchiveFilesReqVO reqVO,
                                     HttpServletResponse response) throws IOException {
        sysArchiveService.downloadArchiveFiles(reqVO, response);
        // 关键：不要返回任何对象
    }

    @GetMapping("/flow-records")
    @Operation(summary = "查看全流程记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('facility:sys-archive:query')")
    public CommonResult<List<FlowRecordRespVO>> getWorkOrderFlowRecords(Long id){
        List<FlowRecordRespVO> list = sysArchiveService.getWorkOrderFlowRecords(id);
        return success(list);
    }

    @PutMapping("/update")
    @Operation(summary = "更新归档")
    @PreAuthorize("@ss.hasPermission('facility:sys-archive:update')")
    public CommonResult<Boolean> updateSysArchive(@Valid @RequestBody SysArchiveUpdateReqVO updateReqVO) {
        sysArchiveService.updateSysArchive(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除归档")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('facility:sys-archive:delete')")
    public CommonResult<Boolean> deleteSysArchive(@RequestParam("id") Long id) {
        sysArchiveService.deleteSysArchive(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得归档")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('facility:sys-archive:query')")
    public CommonResult<SysArchiveRespVO> getSysArchive(@RequestParam("id") Long id) {
        SysArchiveDO sysArchive = sysArchiveService.getSysArchive(id);
        return success(BeanUtils.toBean(sysArchive, SysArchiveRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得归档分页")
    @PreAuthorize("@ss.hasPermission('facility:sys-archive:query')")
    public CommonResult<PageResult<SysArchiveRespVO>> getSysArchivePage(@Valid SysArchivePageReqVO pageReqVO) {
        PageResult<SysArchiveDO> pageResult = sysArchiveService.getSysArchivePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SysArchiveRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出归档 Excel")
    @PreAuthorize("@ss.hasPermission('facility:sys-archive:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSysArchiveExcel(@Valid SysArchivePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SysArchiveDO> list = sysArchiveService.getSysArchivePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "归档.xls", "数据", SysArchiveRespVO.class,
                        BeanUtils.toBean(list, SysArchiveRespVO.class));
    }

}
