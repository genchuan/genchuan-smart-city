package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntype;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntype.vo.EarlyWarnTypePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntype.vo.EarlyWarnTypeRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntype.vo.EarlyWarnTypeSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarntype.EarlyWarnTypeDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarntype.EarlyWarnTypeService;

@Tag(name = "管理后台 - 预警告警类型维度统计")
@RestController
@RequestMapping("/datacenter/early-warn-type")
@Validated
public class EarlyWarnTypeController {

    @Resource
    private EarlyWarnTypeService earlyWarnTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建预警告警类型维度统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-type:create')")
    public CommonResult<Long> createEarlyWarnType(@Valid @RequestBody EarlyWarnTypeSaveReqVO createReqVO) {
        return success(earlyWarnTypeService.createEarlyWarnType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警告警类型维度统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-type:update')")
    public CommonResult<Boolean> updateEarlyWarnType(@Valid @RequestBody EarlyWarnTypeSaveReqVO updateReqVO) {
        earlyWarnTypeService.updateEarlyWarnType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警告警类型维度统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-type:delete')")
    public CommonResult<Boolean> deleteEarlyWarnType(@RequestParam("id") Long id) {
        earlyWarnTypeService.deleteEarlyWarnType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警告警类型维度统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-type:query')")
    public CommonResult<EarlyWarnTypeRespVO> getEarlyWarnType(@RequestParam("id") Long id) {
        EarlyWarnTypeDO earlyWarnType = earlyWarnTypeService.getEarlyWarnType(id);
        return success(BeanUtils.toBean(earlyWarnType, EarlyWarnTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警告警类型维度统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-type:query')")
    public CommonResult<PageResult<EarlyWarnTypeRespVO>> getEarlyWarnTypePage(@Valid EarlyWarnTypePageReqVO pageReqVO) {
        PageResult<EarlyWarnTypeDO> pageResult = earlyWarnTypeService.getEarlyWarnTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警告警类型维度统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnTypeExcel(@Valid EarlyWarnTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnTypeDO> list = earlyWarnTypeService.getEarlyWarnTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警告警类型维度统计.xls", "数据", EarlyWarnTypeRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnTypeRespVO.class));
    }

}