package cn.iocoder.yudao.module.smartcampus.controller.admin.archive;

import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.smartcampus.enums.ArchiveProcessStatusEnum;
import cn.iocoder.yudao.module.smartcampus.enums.ArchiveStatusEnum;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUser;

import cn.iocoder.yudao.module.smartcampus.controller.admin.archive.vo.*;
import cn.iocoder.yudao.module.smartcampus.dal.dataobject.archive.ArchiveDO;
import cn.iocoder.yudao.module.smartcampus.service.archive.ArchiveService;

@Tag(name = "智慧校园管理后台 - 学生学籍档案")
@RestController
@RequestMapping("/smartcampus/student-archive")
@Validated
public class ArchiveController {

    @Resource
    private ArchiveService archiveService;

    @PostMapping("/create")
    @Operation(summary = "创建学生学籍档案")
    @PreAuthorize("@ss.hasPermission('smartcampus:student-archive:create')")
    public CommonResult<Long> createArchive(@Valid @RequestBody ArchiveSaveReqVO createReqVO) {
        return success(archiveService.createArchive(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新学生学籍档案")
    @PreAuthorize("@ss.hasPermission('smartcampus:student-archive:update')")
    public CommonResult<Boolean> updateArchive(@Valid @RequestBody ArchiveSaveReqVO updateReqVO) {
        archiveService.updateArchive(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除学生学籍档案")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcampus:student-archive:delete')")
    public CommonResult<Boolean> deleteArchive(@RequestParam("id") Long id) {
        archiveService.deleteArchive(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除学生学籍档案")
                @PreAuthorize("@ss.hasPermission('smartcampus:student-archive:delete')")
    public CommonResult<Boolean> deleteArchiveList(@RequestParam("ids") List<Long> ids) {
        archiveService.deleteArchiveListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得学生学籍档案")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcampus:student-archive:query')")
    public CommonResult<ArchiveRespVO> getArchive(@RequestParam("id") Long id) {
        ArchiveDO archive = archiveService.getArchive(id);
        return success(BeanUtils.toBean(archive, ArchiveRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得学生学籍档案分页")
    @PreAuthorize("@ss.hasPermission('smartcampus:student-archive:query')")
    public CommonResult<PageResult<ArchiveRespVO>> getArchivePage(@Valid ArchivePageReqVO pageReqVO) {
        PageResult<ArchiveDO> pageResult = archiveService.getArchivePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ArchiveRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出学生学籍档案 Excel")
    @PreAuthorize("@ss.hasPermission('smartcampus:archive:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportArchiveExcel(@Valid ArchivePageReqVO pageReqVO,
                                   HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ArchiveDO> list = archiveService.getArchivePage(pageReqVO).getList();

        // 转换为导出 VO，并将数字编码转为中文标签
        List<ArchiveRespVO> exportList = list.stream().map(archiveDO -> {
            ArchiveRespVO vo = BeanUtils.toBean(archiveDO, ArchiveRespVO.class);

            String processStatusLabel = ArchiveProcessStatusEnum.getNameByKey(archiveDO.getProcessStatus());
            String statusLabel = ArchiveStatusEnum.getNameByKey(archiveDO.getStatus());
            vo.setProcessStatus(processStatusLabel);
            vo.setStatus(statusLabel);

            return vo;
        }).collect(Collectors.toList());

        // 导出 Excel
        ExcelUtils.write(response, "学生学籍档案.xls", "数据", ArchiveRespVO.class, exportList);
    }

    @PutMapping("/audit")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "审核档案")
    @PreAuthorize("@ss.hasPermission('smartcampus:student-archive:audit')")
    public CommonResult<Boolean> audit(@Valid ArchiveAuditReqVO reqVO) {
        LoginUser loginUser = getLoginUser();
        boolean isSuccess = archiveService.audit(reqVO, loginUser);
        return success(isSuccess);
    }

    @PutMapping("/maintain")
    @Operation(summary = "维护学籍状态")
    @PreAuthorize("@ss.hasPermission('smartcampus:student-archive:maintain')")
    public CommonResult<Boolean> maintain(
            @Valid @RequestBody ArchiveMaintainReqVO reqVO) {
        LoginUser loginUser = getLoginUser();
        boolean success = archiveService.maintain(reqVO, loginUser);
        return success(success);
    }


}