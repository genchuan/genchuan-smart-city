package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnlevel;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnlevel.vo.EarlyWarnLevelPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnlevel.vo.EarlyWarnLevelRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnlevel.vo.EarlyWarnLevelSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarnlevel.EarlyWarnLevelDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarnlevel.EarlyWarnLevelService;

@Tag(name = "管理后台 - 预警告警等级维度统计")
@RestController
@RequestMapping("/datacenter/early-warn-level")
@Validated
public class EarlyWarnLevelController {

    @Resource
    private EarlyWarnLevelService earlyWarnLevelService;

    @PostMapping("/create")
    @Operation(summary = "创建预警告警等级维度统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-level:create')")
    public CommonResult<Long> createEarlyWarnLevel(@Valid @RequestBody EarlyWarnLevelSaveReqVO createReqVO) {
        return success(earlyWarnLevelService.createEarlyWarnLevel(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警告警等级维度统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-level:update')")
    public CommonResult<Boolean> updateEarlyWarnLevel(@Valid @RequestBody EarlyWarnLevelSaveReqVO updateReqVO) {
        earlyWarnLevelService.updateEarlyWarnLevel(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警告警等级维度统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-level:delete')")
    public CommonResult<Boolean> deleteEarlyWarnLevel(@RequestParam("id") Long id) {
        earlyWarnLevelService.deleteEarlyWarnLevel(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警告警等级维度统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-level:query')")
    public CommonResult<EarlyWarnLevelRespVO> getEarlyWarnLevel(@RequestParam("id") Long id) {
        EarlyWarnLevelDO earlyWarnLevel = earlyWarnLevelService.getEarlyWarnLevel(id);
        return success(BeanUtils.toBean(earlyWarnLevel, EarlyWarnLevelRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警告警等级维度统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-level:query')")
    public CommonResult<PageResult<EarlyWarnLevelRespVO>> getEarlyWarnLevelPage(@Valid EarlyWarnLevelPageReqVO pageReqVO) {
        PageResult<EarlyWarnLevelDO> pageResult = earlyWarnLevelService.getEarlyWarnLevelPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnLevelRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警告警等级维度统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-level:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnLevelExcel(@Valid EarlyWarnLevelPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnLevelDO> list = earlyWarnLevelService.getEarlyWarnLevelPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警告警等级维度统计.xls", "数据", EarlyWarnLevelRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnLevelRespVO.class));
    }

}