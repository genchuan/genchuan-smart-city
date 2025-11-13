package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnarch.vo.EarlyWarnArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnarch.vo.EarlyWarnArchRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnarch.vo.EarlyWarnArchSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnarch.EarlyWarnArchDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.disposal.earlywarnarch.EarlyWarnArchService;

@Tag(name = "管理后台 - 预警告警处置归档")
@RestController
@RequestMapping("/datacenter/early-warn-arch")
@Validated
public class EarlyWarnArchController {

    @Resource
    private EarlyWarnArchService earlyWarnArchService;

    @PostMapping("/create")
    @Operation(summary = "创建预警告警处置归档")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-arch:create')")
    public CommonResult<Long> createEarlyWarnArch(@Valid @RequestBody EarlyWarnArchSaveReqVO createReqVO) {
        return success(earlyWarnArchService.createEarlyWarnArch(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警告警处置归档")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-arch:update')")
    public CommonResult<Boolean> updateEarlyWarnArch(@Valid @RequestBody EarlyWarnArchSaveReqVO updateReqVO) {
        earlyWarnArchService.updateEarlyWarnArch(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警告警处置归档")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-arch:delete')")
    public CommonResult<Boolean> deleteEarlyWarnArch(@RequestParam("id") Long id) {
        earlyWarnArchService.deleteEarlyWarnArch(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警告警处置归档")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-arch:query')")
    public CommonResult<EarlyWarnArchRespVO> getEarlyWarnArch(@RequestParam("id") Long id) {
        EarlyWarnArchDO earlyWarnArch = earlyWarnArchService.getEarlyWarnArch(id);
        return success(BeanUtils.toBean(earlyWarnArch, EarlyWarnArchRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警告警处置归档分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-arch:query')")
    public CommonResult<PageResult<EarlyWarnArchRespVO>> getEarlyWarnArchPage(@Valid EarlyWarnArchPageReqVO pageReqVO) {
        PageResult<EarlyWarnArchDO> pageResult = earlyWarnArchService.getEarlyWarnArchPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnArchRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警告警处置归档 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-arch:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnArchExcel(@Valid EarlyWarnArchPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnArchDO> list = earlyWarnArchService.getEarlyWarnArchPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警告警处置归档.xls", "数据", EarlyWarnArchRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnArchRespVO.class));
    }

}