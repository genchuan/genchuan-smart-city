package cn.iocoder.yudao.module.smartcity.controller.admin.pipegallerysafety;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.pipegallerysafety.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.pipegallerysafety.PipeGallerySafetyDO;
import cn.iocoder.yudao.module.smartcity.service.pipegallerysafety.PipeGallerySafetyService;

@Tag(name = "管理后台 - 管廊安全")
@RestController
@RequestMapping("/smartcity/pipe-gallery-safety")
@Validated
public class PipeGallerySafetyController {

    @Resource
    private PipeGallerySafetyService pipeGallerySafetyService;

    @PostMapping("/create")
    @Operation(summary = "创建管廊安全")
    @PreAuthorize("@ss.hasPermission('smartcity:pipe-gallery-safety:create')")
    public CommonResult<Long> createPipeGallerySafety(@Valid @RequestBody PipeGallerySafetySaveReqVO createReqVO) {
        return success(pipeGallerySafetyService.createPipeGallerySafety(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管廊安全")
    @PreAuthorize("@ss.hasPermission('smartcity:pipe-gallery-safety:update')")
    public CommonResult<Boolean> updatePipeGallerySafety(@Valid @RequestBody PipeGallerySafetySaveReqVO updateReqVO) {
        pipeGallerySafetyService.updatePipeGallerySafety(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管廊安全")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:pipe-gallery-safety:delete')")
    public CommonResult<Boolean> deletePipeGallerySafety(@RequestParam("id") Long id) {
        pipeGallerySafetyService.deletePipeGallerySafety(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管廊安全")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:pipe-gallery-safety:query')")
    public CommonResult<PipeGallerySafetyRespVO> getPipeGallerySafety(@RequestParam("id") Long id) {
        PipeGallerySafetyDO pipeGallerySafety = pipeGallerySafetyService.getPipeGallerySafety(id);
        return success(BeanUtils.toBean(pipeGallerySafety, PipeGallerySafetyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管廊安全分页")
    @PreAuthorize("@ss.hasPermission('smartcity:pipe-gallery-safety:query')")
    public CommonResult<PageResult<PipeGallerySafetyRespVO>> getPipeGallerySafetyPage(@Valid PipeGallerySafetyPageReqVO pageReqVO) {
        PageResult<PipeGallerySafetyDO> pageResult = pipeGallerySafetyService.getPipeGallerySafetyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PipeGallerySafetyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管廊安全 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:pipe-gallery-safety:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPipeGallerySafetyExcel(@Valid PipeGallerySafetyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PipeGallerySafetyDO> list = pipeGallerySafetyService.getPipeGallerySafetyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管廊安全.xls", "数据", PipeGallerySafetyRespVO.class,
                        BeanUtils.toBean(list, PipeGallerySafetyRespVO.class));
    }

}