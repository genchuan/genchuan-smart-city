package cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.bedmgmt.BedMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.service.bedmgmt.BedMgmtService;

@Tag(name = "学生管理后台 - 床位管理")
@RestController
@RequestMapping("/studentmgmt/bed-mgmt")
@Validated
public class BedMgmtController {

    @Resource
    private BedMgmtService bedMgmtService;

    @PostMapping("/create")
    @Operation(summary = "创建床位管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:create')")
    public CommonResult<Long> createBedMgmt(@Valid @RequestBody BedMgmtSaveReqVO createReqVO) {
        return success(bedMgmtService.createBedMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新床位管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:update')")
    public CommonResult<Boolean> updateBedMgmt(@Valid @RequestBody BedMgmtSaveReqVO updateReqVO) {
        bedMgmtService.updateBedMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除床位管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:delete')")
    public CommonResult<Boolean> deleteBedMgmt(@RequestParam("id") Long id) {
        bedMgmtService.deleteBedMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除床位管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:delete')")
    public CommonResult<Boolean> deleteBedMgmtList(@RequestParam("ids") List<Long> ids) {
        bedMgmtService.deleteBedMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得床位管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:query')")
    public CommonResult<BedMgmtRespVO> getBedMgmt(@RequestParam("id") Long id) {
        BedMgmtDO bedMgmt = bedMgmtService.getBedMgmt(id);
        return success(BeanUtils.toBean(bedMgmt, BedMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得床位管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:query')")
    public CommonResult<PageResult<BedMgmtRespVO>> getBedMgmtPage(@Valid BedMgmtPageReqVO pageReqVO) {
        PageResult<BedMgmtDO> pageResult = bedMgmtService.getBedMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BedMgmtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出床位管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBedMgmtExcel(@Valid BedMgmtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BedMgmtDO> list = bedMgmtService.getBedMgmtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "床位管理.xls", "数据", BedMgmtRespVO.class,
                        BeanUtils.toBean(list, BedMgmtRespVO.class));
    }

}