package cn.iocoder.yudao.module.facility.controller.admin.syswarn;

import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnSaveReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnUpdateReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.syswarn.SysWarnDO;
import cn.iocoder.yudao.module.facility.service.syswarn.SysWarnService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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


@Tag(name = "管理后台 - 通用预警")
@RestController
@RequestMapping("/facility/sys-warn")
@Validated
public class SysWarnController {


    @Resource
    private SysWarnService sysWarnService;

    @PostMapping("/create")
    @Operation(summary = "创建通用预警")
    @PreAuthorize("@ss.hasPermission('facility:sys-warn:create')")
    public CommonResult<Long> createSysWarn(@Valid @RequestBody SysWarnSaveReqVO createReqVO) {
        return success(sysWarnService.createSysWarn(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新通用预警")
    @PreAuthorize("@ss.hasPermission('facility:sys-warn:update')")
    public CommonResult<Boolean> updateSysWarn(@Valid @RequestBody SysWarnUpdateReqVO updateReqVO) {
        sysWarnService.updateSysWarn(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除通用预警")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('facility:sys-warn:delete')")
    public CommonResult<Boolean> deleteSysWarn(@RequestParam("id") Long id) {
        sysWarnService.deleteSysWarn(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得通用预警")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('facility:sys-warn:query')")
    public CommonResult<SysWarnRespVO> getSysWarn(@RequestParam("id") Long id) {
        SysWarnDO sysWarn = sysWarnService.getSysWarn(id);
        return success(BeanUtils.toBean(sysWarn, SysWarnRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得通用预警分页")
    @PreAuthorize("@ss.hasPermission('facility:sys-warn:query')")
    public CommonResult<PageResult<SysWarnRespVO>> getSysWarnPage(@Valid SysWarnPageReqVO pageReqVO) {
//        PageResult<SysWarnDO> pageResult = sysWarnService.getSysWarnPage(pageReqVO);
//        return success(BeanUtils.toBean(pageResult, SysWarnRespVO.class));
        PageResult<SysWarnRespVO> result = sysWarnService.getSysWarnPage(pageReqVO);
        return success(result);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出通用预警 Excel")
    @PreAuthorize("@ss.hasPermission('facility:sys-warn:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSysWarnExcel(@Valid SysWarnPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SysWarnRespVO> list = sysWarnService.getSysWarnPage(pageReqVO).getList();

        List<SysWarnDO> SysWarnDOList = BeanUtils.toBean(list, SysWarnDO.class);
        // 导出 Excel
        ExcelUtils.write(response, "通用预警.xls", "数据", SysWarnRespVO.class,
                        BeanUtils.toBean(SysWarnDOList, SysWarnRespVO.class));
    }

}
