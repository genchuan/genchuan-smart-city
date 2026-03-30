package cn.iocoder.yudao.module.waterdetection.controller.admin.pollutionsourcearchive;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.pollutionsourcearchive.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.pollutionsourcearchive.PollutionSourceArchiveDO;
import cn.iocoder.yudao.module.waterdetection.service.pollutionsourcearchive.PollutionSourceArchiveService;

@Tag(name = "管理后台 - 周边污染源档案管理")
@RestController
@RequestMapping("/waterdetection/pollution-source-archive")
@Validated
public class PollutionSourceArchiveController {

    @Resource
    private PollutionSourceArchiveService pollutionSourceArchiveService;

    @PostMapping("/create")
    @Operation(summary = "创建周边污染源档案管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:pollution-source-archive:create')")
    public CommonResult<Long> createPollutionSourceArchive(@Valid @RequestBody PollutionSourceArchiveSaveReqVO createReqVO) {
        return success(pollutionSourceArchiveService.createPollutionSourceArchive(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新周边污染源档案管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:pollution-source-archive:update')")
    public CommonResult<Boolean> updatePollutionSourceArchive(@Valid @RequestBody PollutionSourceArchiveSaveReqVO updateReqVO) {
        pollutionSourceArchiveService.updatePollutionSourceArchive(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除周边污染源档案管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:pollution-source-archive:delete')")
    public CommonResult<Boolean> deletePollutionSourceArchive(@RequestParam("id") Long id) {
        pollutionSourceArchiveService.deletePollutionSourceArchive(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得周边污染源档案管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:pollution-source-archive:query')")
    public CommonResult<PollutionSourceArchiveRespVO> getPollutionSourceArchive(@RequestParam("id") Long id) {
        PollutionSourceArchiveDO pollutionSourceArchive = pollutionSourceArchiveService.getPollutionSourceArchive(id);
        return success(BeanUtils.toBean(pollutionSourceArchive, PollutionSourceArchiveRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得周边污染源档案管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:pollution-source-archive:query')")
    public CommonResult<PageResult<PollutionSourceArchiveRespVO>> getPollutionSourceArchivePage(@Valid PollutionSourceArchivePageReqVO pageReqVO) {
        PageResult<PollutionSourceArchiveDO> pageResult = pollutionSourceArchiveService.getPollutionSourceArchivePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PollutionSourceArchiveRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出周边污染源档案管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:pollution-source-archive:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPollutionSourceArchiveExcel(@Valid PollutionSourceArchivePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PollutionSourceArchiveDO> list = pollutionSourceArchiveService.getPollutionSourceArchivePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "周边污染源档案管理.xls", "数据", PollutionSourceArchiveRespVO.class,
                        BeanUtils.toBean(list, PollutionSourceArchiveRespVO.class));
    }

}