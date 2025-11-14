package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngcomp.vo.DomDivMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngcomp.vo.DomDivMngCompRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngcomp.vo.DomDivMngCompSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivmngcomp.DomDivMngCompDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivmngcomp.DomDivMngCompService;

@Tag(name = "管理后台 - 管理部件分域")
@RestController
@RequestMapping("/datacenter/dom-div-mng-comp")
@Validated
public class DomDivMngCompController {

    @Resource
    private DomDivMngCompService domDivMngCompService;

    @PostMapping("/create")
    @Operation(summary = "创建管理部件分域")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mng-comp:create')")
    public CommonResult<Long> createDomDivMngComp(@Valid @RequestBody DomDivMngCompSaveReqVO createReqVO) {
        return success(domDivMngCompService.createDomDivMngComp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理部件分域")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mng-comp:update')")
    public CommonResult<Boolean> updateDomDivMngComp(@Valid @RequestBody DomDivMngCompSaveReqVO updateReqVO) {
        domDivMngCompService.updateDomDivMngComp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理部件分域")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mng-comp:delete')")
    public CommonResult<Boolean> deleteDomDivMngComp(@RequestParam("id") Long id) {
        domDivMngCompService.deleteDomDivMngComp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理部件分域")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mng-comp:query')")
    public CommonResult<DomDivMngCompRespVO> getDomDivMngComp(@RequestParam("id") Long id) {
        DomDivMngCompDO domDivMngComp = domDivMngCompService.getDomDivMngComp(id);
        return success(BeanUtils.toBean(domDivMngComp, DomDivMngCompRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理部件分域分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mng-comp:query')")
    public CommonResult<PageResult<DomDivMngCompRespVO>> getDomDivMngCompPage(@Valid DomDivMngCompPageReqVO pageReqVO) {
        PageResult<DomDivMngCompDO> pageResult = domDivMngCompService.getDomDivMngCompPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomDivMngCompRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理部件分域 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mng-comp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomDivMngCompExcel(@Valid DomDivMngCompPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomDivMngCompDO> list = domDivMngCompService.getDomDivMngCompPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理部件分域.xls", "数据", DomDivMngCompRespVO.class,
                        BeanUtils.toBean(list, DomDivMngCompRespVO.class));
    }

}