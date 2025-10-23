package cn.iocoder.yudao.module.datacenter.controller.admin.mnggriddiv;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.mnggriddiv.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mnggriddiv.MngGridDivDO;
import cn.iocoder.yudao.module.datacenter.service.mnggriddiv.MngGridDivService;

@Tag(name = "管理后台 - 管理网格划分")
@RestController
@RequestMapping("/datacenter/mng-grid-div")
@Validated
public class MngGridDivController {

    @Resource
    private MngGridDivService mngGridDivService;

    @PostMapping("/create")
    @Operation(summary = "创建管理网格划分")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:create')")
    public CommonResult<Long> createMngGridDiv(@Valid @RequestBody MngGridDivSaveReqVO createReqVO) {
        return success(mngGridDivService.createMngGridDiv(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理网格划分")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:update')")
    public CommonResult<Boolean> updateMngGridDiv(@Valid @RequestBody MngGridDivSaveReqVO updateReqVO) {
        mngGridDivService.updateMngGridDiv(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理网格划分")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:delete')")
    public CommonResult<Boolean> deleteMngGridDiv(@RequestParam("id") Long id) {
        mngGridDivService.deleteMngGridDiv(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理网格划分")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:query')")
    public CommonResult<MngGridDivRespVO> getMngGridDiv(@RequestParam("id") Long id) {
        MngGridDivDO mngGridDiv = mngGridDivService.getMngGridDiv(id);
        return success(BeanUtils.toBean(mngGridDiv, MngGridDivRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理网格划分分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:query')")
    public CommonResult<PageResult<MngGridDivRespVO>> getMngGridDivPage(@Valid MngGridDivPageReqVO pageReqVO) {
        PageResult<MngGridDivDO> pageResult = mngGridDivService.getMngGridDivPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MngGridDivRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理网格划分 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMngGridDivExcel(@Valid MngGridDivPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MngGridDivDO> list = mngGridDivService.getMngGridDivPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理网格划分.xls", "数据", MngGridDivRespVO.class,
                        BeanUtils.toBean(list, MngGridDivRespVO.class));
    }

}