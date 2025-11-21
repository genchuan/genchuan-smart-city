package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmoncomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmoncomp.vo.EarlyWarnMonCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmoncomp.vo.EarlyWarnMonCompRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmoncomp.vo.EarlyWarnMonCompSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmoncomp.EarlyWarnMonCompDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnmoncomp.EarlyWarnMonCompService;

@Tag(name = "管理后台 - 按监测部件预警告警统计")
@RestController
@RequestMapping("/datacenter/early-warn-mon-comp")
@Validated
public class EarlyWarnMonCompController {

    @Resource
    private EarlyWarnMonCompService earlyWarnMonCompService;

    @PostMapping("/create")
    @Operation(summary = "创建按监测部件预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mon-comp:create')")
    public CommonResult<Long> createEarlyWarnMonComp(@Valid @RequestBody EarlyWarnMonCompSaveReqVO createReqVO) {
        return success(earlyWarnMonCompService.createEarlyWarnMonComp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按监测部件预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mon-comp:update')")
    public CommonResult<Boolean> updateEarlyWarnMonComp(@Valid @RequestBody EarlyWarnMonCompSaveReqVO updateReqVO) {
        earlyWarnMonCompService.updateEarlyWarnMonComp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按监测部件预警告警统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mon-comp:delete')")
    public CommonResult<Boolean> deleteEarlyWarnMonComp(@RequestParam("id") Long id) {
        earlyWarnMonCompService.deleteEarlyWarnMonComp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按监测部件预警告警统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mon-comp:query')")
    public CommonResult<EarlyWarnMonCompRespVO> getEarlyWarnMonComp(@RequestParam("id") Long id) {
        EarlyWarnMonCompDO earlyWarnMonComp = earlyWarnMonCompService.getEarlyWarnMonComp(id);
        return success(BeanUtils.toBean(earlyWarnMonComp, EarlyWarnMonCompRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按监测部件预警告警统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mon-comp:query')")
    public CommonResult<PageResult<EarlyWarnMonCompRespVO>> getEarlyWarnMonCompPage(@Valid EarlyWarnMonCompPageReqVO pageReqVO) {
        PageResult<EarlyWarnMonCompDO> pageResult = earlyWarnMonCompService.getEarlyWarnMonCompPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnMonCompRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出按监测部件预警告警统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mon-comp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnMonCompExcel(@Valid EarlyWarnMonCompPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnMonCompDO> list = earlyWarnMonCompService.getEarlyWarnMonCompPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "按监测部件预警告警统计.xls", "数据", EarlyWarnMonCompRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnMonCompRespVO.class));
    }

}