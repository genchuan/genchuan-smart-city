package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmngcomp.vo.EarlyWarnMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmngcomp.vo.EarlyWarnMngCompRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmngcomp.vo.EarlyWarnMngCompSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmngcomp.EarlyWarnMngCompDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnmngcomp.EarlyWarnMngCompService;

@Tag(name = "管理后台 - 按管理部件预警告警统计")
@RestController
@RequestMapping("/datacenter/early-warn-mng-comp")
@Validated
public class EarlyWarnMngCompController {

    @Resource
    private EarlyWarnMngCompService earlyWarnMngCompService;

    @PostMapping("/create")
    @Operation(summary = "创建按管理部件预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mng-comp:create')")
    public CommonResult<Long> createEarlyWarnMngComp(@Valid @RequestBody EarlyWarnMngCompSaveReqVO createReqVO) {
        return success(earlyWarnMngCompService.createEarlyWarnMngComp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按管理部件预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mng-comp:update')")
    public CommonResult<Boolean> updateEarlyWarnMngComp(@Valid @RequestBody EarlyWarnMngCompSaveReqVO updateReqVO) {
        earlyWarnMngCompService.updateEarlyWarnMngComp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按管理部件预警告警统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mng-comp:delete')")
    public CommonResult<Boolean> deleteEarlyWarnMngComp(@RequestParam("id") Long id) {
        earlyWarnMngCompService.deleteEarlyWarnMngComp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按管理部件预警告警统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mng-comp:query')")
    public CommonResult<EarlyWarnMngCompRespVO> getEarlyWarnMngComp(@RequestParam("id") Long id) {
        EarlyWarnMngCompDO earlyWarnMngComp = earlyWarnMngCompService.getEarlyWarnMngComp(id);
        return success(BeanUtils.toBean(earlyWarnMngComp, EarlyWarnMngCompRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按管理部件预警告警统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mng-comp:query')")
    public CommonResult<PageResult<EarlyWarnMngCompRespVO>> getEarlyWarnMngCompPage(@Valid EarlyWarnMngCompPageReqVO pageReqVO) {
        PageResult<EarlyWarnMngCompDO> pageResult = earlyWarnMngCompService.getEarlyWarnMngCompPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnMngCompRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出按管理部件预警告警统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mng-comp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnMngCompExcel(@Valid EarlyWarnMngCompPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnMngCompDO> list = earlyWarnMngCompService.getEarlyWarnMngCompPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "按管理部件预警告警统计.xls", "数据", EarlyWarnMngCompRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnMngCompRespVO.class));
    }

}