package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnhandleeff;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnhandleeff.vo.EarlyWarnHandleEffPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnhandleeff.vo.EarlyWarnHandleEffRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnhandleeff.vo.EarlyWarnHandleEffSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarnhandleeff.EarlyWarnHandleEffDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarnhandleeff.EarlyWarnHandleEffService;

@Tag(name = "管理后台 - 预警告警处置效率统计")
@RestController
@RequestMapping("/datacenter/early-warn-handle-eff")
@Validated
public class EarlyWarnHandleEffController {

    @Resource
    private EarlyWarnHandleEffService earlyWarnHandleEffService;

    @PostMapping("/create")
    @Operation(summary = "创建预警告警处置效率统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-eff:create')")
    public CommonResult<Long> createEarlyWarnHandleEff(@Valid @RequestBody EarlyWarnHandleEffSaveReqVO createReqVO) {
        return success(earlyWarnHandleEffService.createEarlyWarnHandleEff(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警告警处置效率统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-eff:update')")
    public CommonResult<Boolean> updateEarlyWarnHandleEff(@Valid @RequestBody EarlyWarnHandleEffSaveReqVO updateReqVO) {
        earlyWarnHandleEffService.updateEarlyWarnHandleEff(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警告警处置效率统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-eff:delete')")
    public CommonResult<Boolean> deleteEarlyWarnHandleEff(@RequestParam("id") Long id) {
        earlyWarnHandleEffService.deleteEarlyWarnHandleEff(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警告警处置效率统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-eff:query')")
    public CommonResult<EarlyWarnHandleEffRespVO> getEarlyWarnHandleEff(@RequestParam("id") Long id) {
        EarlyWarnHandleEffDO earlyWarnHandleEff = earlyWarnHandleEffService.getEarlyWarnHandleEff(id);
        return success(BeanUtils.toBean(earlyWarnHandleEff, EarlyWarnHandleEffRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警告警处置效率统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-eff:query')")
    public CommonResult<PageResult<EarlyWarnHandleEffRespVO>> getEarlyWarnHandleEffPage(@Valid EarlyWarnHandleEffPageReqVO pageReqVO) {
        PageResult<EarlyWarnHandleEffDO> pageResult = earlyWarnHandleEffService.getEarlyWarnHandleEffPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnHandleEffRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警告警处置效率统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-eff:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnHandleEffExcel(@Valid EarlyWarnHandleEffPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnHandleEffDO> list = earlyWarnHandleEffService.getEarlyWarnHandleEffPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警告警处置效率统计.xls", "数据", EarlyWarnHandleEffRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnHandleEffRespVO.class));
    }

}