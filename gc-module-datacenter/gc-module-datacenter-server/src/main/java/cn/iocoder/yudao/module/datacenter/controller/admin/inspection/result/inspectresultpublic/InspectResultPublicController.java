package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultpublic;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultpublic.vo.InspectResultPublicPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultpublic.vo.InspectResultPublicRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultpublic.vo.InspectResultPublicSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.result.inspectresultpublic.InspectResultPublicDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.result.inspectresultpublic.InspectResultPublicService;

@Tag(name = "管理后台 - 巡查巡检结果公示")
@RestController
@RequestMapping("/datacenter/inspect-result-public")
@Validated
public class InspectResultPublicController {

    @Resource
    private InspectResultPublicService inspectResultPublicService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检结果公示")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-public:create')")
    public CommonResult<Long> createInspectResultPublic(@Valid @RequestBody InspectResultPublicSaveReqVO createReqVO) {
        return success(inspectResultPublicService.createInspectResultPublic(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检结果公示")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-public:update')")
    public CommonResult<Boolean> updateInspectResultPublic(@Valid @RequestBody InspectResultPublicSaveReqVO updateReqVO) {
        inspectResultPublicService.updateInspectResultPublic(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检结果公示")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-public:delete')")
    public CommonResult<Boolean> deleteInspectResultPublic(@RequestParam("id") Long id) {
        inspectResultPublicService.deleteInspectResultPublic(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检结果公示")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-public:query')")
    public CommonResult<InspectResultPublicRespVO> getInspectResultPublic(@RequestParam("id") Long id) {
        InspectResultPublicDO inspectResultPublic = inspectResultPublicService.getInspectResultPublic(id);
        return success(BeanUtils.toBean(inspectResultPublic, InspectResultPublicRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检结果公示分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-public:query')")
    public CommonResult<PageResult<InspectResultPublicRespVO>> getInspectResultPublicPage(@Valid InspectResultPublicPageReqVO pageReqVO) {
        PageResult<InspectResultPublicDO> pageResult = inspectResultPublicService.getInspectResultPublicPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectResultPublicRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检结果公示 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-public:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectResultPublicExcel(@Valid InspectResultPublicPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectResultPublicDO> list = inspectResultPublicService.getInspectResultPublicPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检结果公示.xls", "数据", InspectResultPublicRespVO.class,
                        BeanUtils.toBean(list, InspectResultPublicRespVO.class));
    }

}