package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngmatter;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngmatter.vo.DomDivMngMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngmatter.vo.DomDivMngMatterRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngmatter.vo.DomDivMngMatterSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivmngmatter.DomDivMngMatterDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivmngmatter.DomDivMngMatterService;

@Tag(name = "管理后台 - 管理事项分域")
@RestController
@RequestMapping("/datacenter/dom-div-mng-matter")
@Validated
public class DomDivMngMatterController {

    @Resource
    private DomDivMngMatterService domDivMngMatterService;

    @PostMapping("/create")
    @Operation(summary = "创建管理事项分域")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mng-matter:create')")
    public CommonResult<Long> createDomDivMngMatter(@Valid @RequestBody DomDivMngMatterSaveReqVO createReqVO) {
        return success(domDivMngMatterService.createDomDivMngMatter(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理事项分域")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mng-matter:update')")
    public CommonResult<Boolean> updateDomDivMngMatter(@Valid @RequestBody DomDivMngMatterSaveReqVO updateReqVO) {
        domDivMngMatterService.updateDomDivMngMatter(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理事项分域")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mng-matter:delete')")
    public CommonResult<Boolean> deleteDomDivMngMatter(@RequestParam("id") Long id) {
        domDivMngMatterService.deleteDomDivMngMatter(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理事项分域")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mng-matter:query')")
    public CommonResult<DomDivMngMatterRespVO> getDomDivMngMatter(@RequestParam("id") Long id) {
        DomDivMngMatterDO domDivMngMatter = domDivMngMatterService.getDomDivMngMatter(id);
        return success(BeanUtils.toBean(domDivMngMatter, DomDivMngMatterRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理事项分域分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mng-matter:query')")
    public CommonResult<PageResult<DomDivMngMatterRespVO>> getDomDivMngMatterPage(@Valid DomDivMngMatterPageReqVO pageReqVO) {
        PageResult<DomDivMngMatterDO> pageResult = domDivMngMatterService.getDomDivMngMatterPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomDivMngMatterRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理事项分域 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mng-matter:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomDivMngMatterExcel(@Valid DomDivMngMatterPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomDivMngMatterDO> list = domDivMngMatterService.getDomDivMngMatterPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理事项分域.xls", "数据", DomDivMngMatterRespVO.class,
                        BeanUtils.toBean(list, DomDivMngMatterRespVO.class));
    }

}