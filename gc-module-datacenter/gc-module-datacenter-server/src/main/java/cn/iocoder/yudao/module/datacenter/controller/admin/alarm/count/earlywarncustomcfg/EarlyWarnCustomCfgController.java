package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarncustomcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarncustomcfg.vo.EarlyWarnCustomCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarncustomcfg.vo.EarlyWarnCustomCfgRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarncustomcfg.vo.EarlyWarnCustomCfgSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarncustomcfg.EarlyWarnCustomCfgDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarncustomcfg.EarlyWarnCustomCfgService;

@Tag(name = "管理后台 - 预警告警自定义统计配置")
@RestController
@RequestMapping("/datacenter/early-warn-custom-cfg")
@Validated
public class EarlyWarnCustomCfgController {

    @Resource
    private EarlyWarnCustomCfgService earlyWarnCustomCfgService;

    @PostMapping("/create")
    @Operation(summary = "创建预警告警自定义统计配置")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-custom-cfg:create')")
    public CommonResult<Long> createEarlyWarnCustomCfg(@Valid @RequestBody EarlyWarnCustomCfgSaveReqVO createReqVO) {
        return success(earlyWarnCustomCfgService.createEarlyWarnCustomCfg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警告警自定义统计配置")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-custom-cfg:update')")
    public CommonResult<Boolean> updateEarlyWarnCustomCfg(@Valid @RequestBody EarlyWarnCustomCfgSaveReqVO updateReqVO) {
        earlyWarnCustomCfgService.updateEarlyWarnCustomCfg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警告警自定义统计配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-custom-cfg:delete')")
    public CommonResult<Boolean> deleteEarlyWarnCustomCfg(@RequestParam("id") Long id) {
        earlyWarnCustomCfgService.deleteEarlyWarnCustomCfg(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警告警自定义统计配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-custom-cfg:query')")
    public CommonResult<EarlyWarnCustomCfgRespVO> getEarlyWarnCustomCfg(@RequestParam("id") Long id) {
        EarlyWarnCustomCfgDO earlyWarnCustomCfg = earlyWarnCustomCfgService.getEarlyWarnCustomCfg(id);
        return success(BeanUtils.toBean(earlyWarnCustomCfg, EarlyWarnCustomCfgRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警告警自定义统计配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-custom-cfg:query')")
    public CommonResult<PageResult<EarlyWarnCustomCfgRespVO>> getEarlyWarnCustomCfgPage(@Valid EarlyWarnCustomCfgPageReqVO pageReqVO) {
        PageResult<EarlyWarnCustomCfgDO> pageResult = earlyWarnCustomCfgService.getEarlyWarnCustomCfgPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnCustomCfgRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警告警自定义统计配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-custom-cfg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnCustomCfgExcel(@Valid EarlyWarnCustomCfgPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnCustomCfgDO> list = earlyWarnCustomCfgService.getEarlyWarnCustomCfgPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警告警自定义统计配置.xls", "数据", EarlyWarnCustomCfgRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnCustomCfgRespVO.class));
    }

}