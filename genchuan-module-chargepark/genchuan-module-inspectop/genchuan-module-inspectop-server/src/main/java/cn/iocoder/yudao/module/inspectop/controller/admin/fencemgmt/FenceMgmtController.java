package cn.iocoder.yudao.module.inspectop.controller.admin.fencemgmt;

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

import cn.iocoder.yudao.module.inspectop.controller.admin.fencemgmt.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.fencemgmt.FenceMgmtDO;
import cn.iocoder.yudao.module.inspectop.service.fencemgmt.FenceMgmtService;

@Tag(name = "管理后台 - 电子围栏")
@RestController
@RequestMapping("/inspectop/fence-mgmt")
@Validated
public class FenceMgmtController {

    @Resource
    private FenceMgmtService fenceMgmtService;

    @PostMapping("/create")
    @Operation(summary = "创建电子围栏")
    @PreAuthorize("@ss.hasPermission('inspectop:fence-mgmt:create')")
    public CommonResult<Long> createFenceMgmt(@Valid @RequestBody FenceMgmtSaveReqVO createReqVO) {
        return success(fenceMgmtService.createFenceMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新电子围栏")
    @PreAuthorize("@ss.hasPermission('inspectop:fence-mgmt:update')")
    public CommonResult<Boolean> updateFenceMgmt(@Valid @RequestBody FenceMgmtSaveReqVO updateReqVO) {
        fenceMgmtService.updateFenceMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除电子围栏")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:fence-mgmt:delete')")
    public CommonResult<Boolean> deleteFenceMgmt(@RequestParam("id") Long id) {
        fenceMgmtService.deleteFenceMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除电子围栏")
                @PreAuthorize("@ss.hasPermission('inspectop:fence-mgmt:delete')")
    public CommonResult<Boolean> deleteFenceMgmtList(@RequestParam("ids") List<Long> ids) {
        fenceMgmtService.deleteFenceMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得电子围栏")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:fence-mgmt:query')")
    public CommonResult<FenceMgmtRespVO> getFenceMgmt(@RequestParam("id") Long id) {
        FenceMgmtDO fenceMgmt = fenceMgmtService.getFenceMgmt(id);
        return success(BeanUtils.toBean(fenceMgmt, FenceMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得电子围栏分页")
    @PreAuthorize("@ss.hasPermission('inspectop:fence-mgmt:query')")
    public CommonResult<PageResult<FenceMgmtRespVO>> getFenceMgmtPage(@Valid FenceMgmtPageReqVO pageReqVO) {
        PageResult<FenceMgmtDO> pageResult = fenceMgmtService.getFenceMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, FenceMgmtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出电子围栏 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:fence-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFenceMgmtExcel(@Valid FenceMgmtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FenceMgmtDO> list = fenceMgmtService.getFenceMgmtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "电子围栏.xls", "数据", FenceMgmtRespVO.class,
                        BeanUtils.toBean(list, FenceMgmtRespVO.class));
    }

}