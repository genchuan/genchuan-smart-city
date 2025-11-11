package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivadmin;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivadmin.vo.DomDivAdminPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivadmin.vo.DomDivAdminRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivadmin.vo.DomDivAdminSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivadmin.DomDivAdminDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivadmin.DomDivAdminService;

@Tag(name = "管理后台 - 行政区划分域")
@RestController
@RequestMapping("/datacenter/dom-div-admin")
@Validated
public class DomDivAdminController {

    @Resource
    private DomDivAdminService domDivAdminService;

    @PostMapping("/create")
    @Operation(summary = "创建行政区划分域")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-admin:create')")
    public CommonResult<Long> createDomDivAdmin(@Valid @RequestBody DomDivAdminSaveReqVO createReqVO) {
        return success(domDivAdminService.createDomDivAdmin(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新行政区划分域")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-admin:update')")
    public CommonResult<Boolean> updateDomDivAdmin(@Valid @RequestBody DomDivAdminSaveReqVO updateReqVO) {
        domDivAdminService.updateDomDivAdmin(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除行政区划分域")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-admin:delete')")
    public CommonResult<Boolean> deleteDomDivAdmin(@RequestParam("id") Long id) {
        domDivAdminService.deleteDomDivAdmin(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得行政区划分域")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-admin:query')")
    public CommonResult<DomDivAdminRespVO> getDomDivAdmin(@RequestParam("id") Long id) {
        DomDivAdminDO domDivAdmin = domDivAdminService.getDomDivAdmin(id);
        return success(BeanUtils.toBean(domDivAdmin, DomDivAdminRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得行政区划分域分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-admin:query')")
    public CommonResult<PageResult<DomDivAdminRespVO>> getDomDivAdminPage(@Valid DomDivAdminPageReqVO pageReqVO) {
        PageResult<DomDivAdminDO> pageResult = domDivAdminService.getDomDivAdminPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomDivAdminRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出行政区划分域 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-admin:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomDivAdminExcel(@Valid DomDivAdminPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomDivAdminDO> list = domDivAdminService.getDomDivAdminPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "行政区划分域.xls", "数据", DomDivAdminRespVO.class,
                        BeanUtils.toBean(list, DomDivAdminRespVO.class));
    }

}