package cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.specbusinessguide;

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.specbusinessguide.vo.SpecBusinessGuidePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.specbusinessguide.vo.SpecBusinessGuideRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.specbusinessguide.vo.SpecBusinessGuideSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.specbusinessguide.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.specbusinessguide.SpecBusinessGuideDO;
import cn.iocoder.yudao.module.datacenter.service.operationguide.specbusinessguide.SpecBusinessGuideService;

@Tag(name = "管理后台 - 专项业务指南")
@RestController
@RequestMapping("/datacenter/spec-business-guide")
@Validated
public class SpecBusinessGuideController {

    @Resource
    private SpecBusinessGuideService specBusinessGuideService;

    @PostMapping("/create")
    @Operation(summary = "创建专项业务指南")
    @PreAuthorize("@ss.hasPermission('datacenter:spec-business-guide:create')")
    public CommonResult<Long> createSpecBusinessGuide(@Valid @RequestBody SpecBusinessGuideSaveReqVO createReqVO) {
        return success(specBusinessGuideService.createSpecBusinessGuide(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新专项业务指南")
    @PreAuthorize("@ss.hasPermission('datacenter:spec-business-guide:update')")
    public CommonResult<Boolean> updateSpecBusinessGuide(@Valid @RequestBody SpecBusinessGuideSaveReqVO updateReqVO) {
        specBusinessGuideService.updateSpecBusinessGuide(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除专项业务指南")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:spec-business-guide:delete')")
    public CommonResult<Boolean> deleteSpecBusinessGuide(@RequestParam("id") Long id) {
        specBusinessGuideService.deleteSpecBusinessGuide(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得专项业务指南")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:spec-business-guide:query')")
    public CommonResult<SpecBusinessGuideRespVO> getSpecBusinessGuide(@RequestParam("id") Long id) {
        SpecBusinessGuideDO specBusinessGuide = specBusinessGuideService.getSpecBusinessGuide(id);
        return success(BeanUtils.toBean(specBusinessGuide, SpecBusinessGuideRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得专项业务指南分页")
    @PreAuthorize("@ss.hasPermission('datacenter:spec-business-guide:query')")
    public CommonResult<PageResult<SpecBusinessGuideRespVO>> getSpecBusinessGuidePage(@Valid SpecBusinessGuidePageReqVO pageReqVO) {
        PageResult<SpecBusinessGuideDO> pageResult = specBusinessGuideService.getSpecBusinessGuidePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SpecBusinessGuideRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出专项业务指南 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:spec-business-guide:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSpecBusinessGuideExcel(@Valid SpecBusinessGuidePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SpecBusinessGuideDO> list = specBusinessGuideService.getSpecBusinessGuidePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "专项业务指南.xls", "数据", SpecBusinessGuideRespVO.class,
                        BeanUtils.toBean(list, SpecBusinessGuideRespVO.class));
    }

}