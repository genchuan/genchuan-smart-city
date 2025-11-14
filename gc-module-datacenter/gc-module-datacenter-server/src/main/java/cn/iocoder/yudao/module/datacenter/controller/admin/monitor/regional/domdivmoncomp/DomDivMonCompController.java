package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmoncomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmoncomp.vo.DomDivMonCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmoncomp.vo.DomDivMonCompRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmoncomp.vo.DomDivMonCompSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivmoncomp.DomDivMonCompDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivmoncomp.DomDivMonCompService;

@Tag(name = "管理后台 - 监测部件分域")
@RestController
@RequestMapping("/datacenter/dom-div-mon-comp")
@Validated
public class DomDivMonCompController {

    @Resource
    private DomDivMonCompService domDivMonCompService;

    @PostMapping("/create")
    @Operation(summary = "创建监测部件分域")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mon-comp:create')")
    public CommonResult<Long> createDomDivMonComp(@Valid @RequestBody DomDivMonCompSaveReqVO createReqVO) {
        return success(domDivMonCompService.createDomDivMonComp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测部件分域")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mon-comp:update')")
    public CommonResult<Boolean> updateDomDivMonComp(@Valid @RequestBody DomDivMonCompSaveReqVO updateReqVO) {
        domDivMonCompService.updateDomDivMonComp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测部件分域")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mon-comp:delete')")
    public CommonResult<Boolean> deleteDomDivMonComp(@RequestParam("id") Long id) {
        domDivMonCompService.deleteDomDivMonComp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测部件分域")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mon-comp:query')")
    public CommonResult<DomDivMonCompRespVO> getDomDivMonComp(@RequestParam("id") Long id) {
        DomDivMonCompDO domDivMonComp = domDivMonCompService.getDomDivMonComp(id);
        return success(BeanUtils.toBean(domDivMonComp, DomDivMonCompRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测部件分域分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mon-comp:query')")
    public CommonResult<PageResult<DomDivMonCompRespVO>> getDomDivMonCompPage(@Valid DomDivMonCompPageReqVO pageReqVO) {
        PageResult<DomDivMonCompDO> pageResult = domDivMonCompService.getDomDivMonCompPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomDivMonCompRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测部件分域 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mon-comp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomDivMonCompExcel(@Valid DomDivMonCompPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomDivMonCompDO> list = domDivMonCompService.getDomDivMonCompPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测部件分域.xls", "数据", DomDivMonCompRespVO.class,
                        BeanUtils.toBean(list, DomDivMonCompRespVO.class));
    }

}