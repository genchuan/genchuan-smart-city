package cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.mnggridrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.mnggridrpt.vo.MngGridRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.mnggridrpt.vo.MngGridRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.mnggridrpt.vo.MngGridRptSaveReqVO;
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

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.rpt.mnggridrpt.MngGridRptDO;
import cn.iocoder.yudao.module.datacenter.service.grid.rpt.mnggridrpt.MngGridRptService;

@Tag(name = "管理后台 - 管理网格统计")
@RestController
@RequestMapping("/datacenter/mng-grid-rpt")
@Validated
public class MngGridRptController {

    @Resource
    private MngGridRptService mngGridRptService;

    @PostMapping("/create")
    @Operation(summary = "创建管理网格统计")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-rpt:create')")
    public CommonResult<Long> createMngGridRpt(@Valid @RequestBody MngGridRptSaveReqVO createReqVO) {
        return success(mngGridRptService.createMngGridRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理网格统计")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-rpt:update')")
    public CommonResult<Boolean> updateMngGridRpt(@Valid @RequestBody MngGridRptSaveReqVO updateReqVO) {
        mngGridRptService.updateMngGridRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理网格统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-rpt:delete')")
    public CommonResult<Boolean> deleteMngGridRpt(@RequestParam("id") Long id) {
        mngGridRptService.deleteMngGridRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理网格统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-rpt:query')")
    public CommonResult<MngGridRptRespVO> getMngGridRpt(@RequestParam("id") Long id) {
        MngGridRptDO mngGridRpt = mngGridRptService.getMngGridRpt(id);
        return success(BeanUtils.toBean(mngGridRpt, MngGridRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理网格统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-rpt:query')")
    public CommonResult<PageResult<MngGridRptRespVO>> getMngGridRptPage(@Valid MngGridRptPageReqVO pageReqVO) {
        PageResult<MngGridRptDO> pageResult = mngGridRptService.getMngGridRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MngGridRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理网格统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMngGridRptExcel(@Valid MngGridRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MngGridRptDO> list = mngGridRptService.getMngGridRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理网格统计.xls", "数据", MngGridRptRespVO.class,
                        BeanUtils.toBean(list, MngGridRptRespVO.class));
    }

}