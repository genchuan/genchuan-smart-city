package cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.generaloperguide;

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.generaloperguide.vo.GeneralOperGuidePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.generaloperguide.vo.GeneralOperGuideRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.generaloperguide.vo.GeneralOperGuideSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.generaloperguide.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.generaloperguide.GeneralOperGuideDO;
import cn.iocoder.yudao.module.datacenter.service.operationguide.generaloperguide.GeneralOperGuideService;

@Tag(name = "管理后台 - 通用操作指南")
@RestController
@RequestMapping("/datacenter/general-oper-guide")
@Validated
public class GeneralOperGuideController {

    @Resource
    private GeneralOperGuideService generalOperGuideService;

    @PostMapping("/create")
    @Operation(summary = "创建通用操作指南")
    @PreAuthorize("@ss.hasPermission('datacenter:general-oper-guide:create')")
    public CommonResult<Long> createGeneralOperGuide(@Valid @RequestBody GeneralOperGuideSaveReqVO createReqVO) {
        return success(generalOperGuideService.createGeneralOperGuide(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新通用操作指南")
    @PreAuthorize("@ss.hasPermission('datacenter:general-oper-guide:update')")
    public CommonResult<Boolean> updateGeneralOperGuide(@Valid @RequestBody GeneralOperGuideSaveReqVO updateReqVO) {
        generalOperGuideService.updateGeneralOperGuide(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除通用操作指南")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:general-oper-guide:delete')")
    public CommonResult<Boolean> deleteGeneralOperGuide(@RequestParam("id") Long id) {
        generalOperGuideService.deleteGeneralOperGuide(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得通用操作指南")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:general-oper-guide:query')")
    public CommonResult<GeneralOperGuideRespVO> getGeneralOperGuide(@RequestParam("id") Long id) {
        GeneralOperGuideDO generalOperGuide = generalOperGuideService.getGeneralOperGuide(id);
        return success(BeanUtils.toBean(generalOperGuide, GeneralOperGuideRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得通用操作指南分页")
    @PreAuthorize("@ss.hasPermission('datacenter:general-oper-guide:query')")
    public CommonResult<PageResult<GeneralOperGuideRespVO>> getGeneralOperGuidePage(@Valid GeneralOperGuidePageReqVO pageReqVO) {
        PageResult<GeneralOperGuideDO> pageResult = generalOperGuideService.getGeneralOperGuidePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GeneralOperGuideRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出通用操作指南 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:general-oper-guide:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGeneralOperGuideExcel(@Valid GeneralOperGuidePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GeneralOperGuideDO> list = generalOperGuideService.getGeneralOperGuidePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "通用操作指南.xls", "数据", GeneralOperGuideRespVO.class,
                        BeanUtils.toBean(list, GeneralOperGuideRespVO.class));
    }

}