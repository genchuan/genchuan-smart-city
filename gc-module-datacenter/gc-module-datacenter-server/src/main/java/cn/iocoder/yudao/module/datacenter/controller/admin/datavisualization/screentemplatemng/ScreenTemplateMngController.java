package cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.screentemplatemng;

import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.screentemplatemng.vo.ScreenTemplateMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.screentemplatemng.vo.ScreenTemplateMngRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.screentemplatemng.vo.ScreenTemplateMngSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.screentemplatemng.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.datavisualization.screentemplatemng.ScreenTemplateMngDO;
import cn.iocoder.yudao.module.datacenter.service.datavisualization.screentemplatemng.ScreenTemplateMngService;

@Tag(name = "管理后台 - 大屏模板管理")
@RestController
@RequestMapping("/datacenter/screen-template-mng")
@Validated
public class ScreenTemplateMngController {

    @Resource
    private ScreenTemplateMngService screenTemplateMngService;

    @PostMapping("/create")
    @Operation(summary = "创建大屏模板管理")
    @PreAuthorize("@ss.hasPermission('datacenter:screen-template-mng:create')")
    public CommonResult<Long> createScreenTemplateMng(@Valid @RequestBody ScreenTemplateMngSaveReqVO createReqVO) {
        return success(screenTemplateMngService.createScreenTemplateMng(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新大屏模板管理")
    @PreAuthorize("@ss.hasPermission('datacenter:screen-template-mng:update')")
    public CommonResult<Boolean> updateScreenTemplateMng(@Valid @RequestBody ScreenTemplateMngSaveReqVO updateReqVO) {
        screenTemplateMngService.updateScreenTemplateMng(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除大屏模板管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:screen-template-mng:delete')")
    public CommonResult<Boolean> deleteScreenTemplateMng(@RequestParam("id") Long id) {
        screenTemplateMngService.deleteScreenTemplateMng(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得大屏模板管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:screen-template-mng:query')")
    public CommonResult<ScreenTemplateMngRespVO> getScreenTemplateMng(@RequestParam("id") Long id) {
        ScreenTemplateMngDO screenTemplateMng = screenTemplateMngService.getScreenTemplateMng(id);
        return success(BeanUtils.toBean(screenTemplateMng, ScreenTemplateMngRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得大屏模板管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:screen-template-mng:query')")
    public CommonResult<PageResult<ScreenTemplateMngRespVO>> getScreenTemplateMngPage(@Valid ScreenTemplateMngPageReqVO pageReqVO) {
        PageResult<ScreenTemplateMngDO> pageResult = screenTemplateMngService.getScreenTemplateMngPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ScreenTemplateMngRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出大屏模板管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:screen-template-mng:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportScreenTemplateMngExcel(@Valid ScreenTemplateMngPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ScreenTemplateMngDO> list = screenTemplateMngService.getScreenTemplateMngPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "大屏模板管理.xls", "数据", ScreenTemplateMngRespVO.class,
                        BeanUtils.toBean(list, ScreenTemplateMngRespVO.class));
    }

}