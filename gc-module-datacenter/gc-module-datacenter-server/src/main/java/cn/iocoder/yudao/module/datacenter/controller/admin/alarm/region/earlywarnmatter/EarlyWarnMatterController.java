package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmatter;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmatter.vo.EarlyWarnMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmatter.vo.EarlyWarnMatterRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmatter.vo.EarlyWarnMatterSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmatter.EarlyWarnMatterDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnmatter.EarlyWarnMatterService;

@Tag(name = "管理后台 - 按管理事项预警告警统计")
@RestController
@RequestMapping("/datacenter/early-warn-matter")
@Validated
public class EarlyWarnMatterController {

    @Resource
    private EarlyWarnMatterService earlyWarnMatterService;

    @PostMapping("/create")
    @Operation(summary = "创建按管理事项预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-matter:create')")
    public CommonResult<Long> createEarlyWarnMatter(@Valid @RequestBody EarlyWarnMatterSaveReqVO createReqVO) {
        return success(earlyWarnMatterService.createEarlyWarnMatter(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按管理事项预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-matter:update')")
    public CommonResult<Boolean> updateEarlyWarnMatter(@Valid @RequestBody EarlyWarnMatterSaveReqVO updateReqVO) {
        earlyWarnMatterService.updateEarlyWarnMatter(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按管理事项预警告警统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-matter:delete')")
    public CommonResult<Boolean> deleteEarlyWarnMatter(@RequestParam("id") Long id) {
        earlyWarnMatterService.deleteEarlyWarnMatter(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按管理事项预警告警统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-matter:query')")
    public CommonResult<EarlyWarnMatterRespVO> getEarlyWarnMatter(@RequestParam("id") Long id) {
        EarlyWarnMatterDO earlyWarnMatter = earlyWarnMatterService.getEarlyWarnMatter(id);
        return success(BeanUtils.toBean(earlyWarnMatter, EarlyWarnMatterRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按管理事项预警告警统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-matter:query')")
    public CommonResult<PageResult<EarlyWarnMatterRespVO>> getEarlyWarnMatterPage(@Valid EarlyWarnMatterPageReqVO pageReqVO) {
        PageResult<EarlyWarnMatterDO> pageResult = earlyWarnMatterService.getEarlyWarnMatterPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnMatterRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出按管理事项预警告警统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-matter:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnMatterExcel(@Valid EarlyWarnMatterPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnMatterDO> list = earlyWarnMatterService.getEarlyWarnMatterPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "按管理事项预警告警统计.xls", "数据", EarlyWarnMatterRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnMatterRespVO.class));
    }

}