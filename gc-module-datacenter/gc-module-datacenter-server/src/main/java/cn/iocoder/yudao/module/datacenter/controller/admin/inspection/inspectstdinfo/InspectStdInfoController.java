package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.inspectstdinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.inspectstdinfo.vo.InspectStdInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.inspectstdinfo.vo.InspectStdInfoRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.inspectstdinfo.vo.InspectStdInfoSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.inspectstdinfo.InspectStdInfoDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.inspectstdinfo.InspectStdInfoService;

@Tag(name = "管理后台 - 巡查巡检标准信息")
@RestController
@RequestMapping("/datacenter/inspect-std-info")
@Validated
public class InspectStdInfoController {

    @Resource
    private InspectStdInfoService inspectStdInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检标准信息")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-std-info:create')")
    public CommonResult<Long> createInspectStdInfo(@Valid @RequestBody InspectStdInfoSaveReqVO createReqVO) {
        return success(inspectStdInfoService.createInspectStdInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检标准信息")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-std-info:update')")
    public CommonResult<Boolean> updateInspectStdInfo(@Valid @RequestBody InspectStdInfoSaveReqVO updateReqVO) {
        inspectStdInfoService.updateInspectStdInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检标准信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-std-info:delete')")
    public CommonResult<Boolean> deleteInspectStdInfo(@RequestParam("id") Long id) {
        inspectStdInfoService.deleteInspectStdInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检标准信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-std-info:query')")
    public CommonResult<InspectStdInfoRespVO> getInspectStdInfo(@RequestParam("id") Long id) {
        InspectStdInfoDO inspectStdInfo = inspectStdInfoService.getInspectStdInfo(id);
        return success(BeanUtils.toBean(inspectStdInfo, InspectStdInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检标准信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-std-info:query')")
    public CommonResult<PageResult<InspectStdInfoRespVO>> getInspectStdInfoPage(@Valid InspectStdInfoPageReqVO pageReqVO) {
        PageResult<InspectStdInfoDO> pageResult = inspectStdInfoService.getInspectStdInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectStdInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检标准信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-std-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectStdInfoExcel(@Valid InspectStdInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectStdInfoDO> list = inspectStdInfoService.getInspectStdInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检标准信息.xls", "数据", InspectStdInfoRespVO.class,
                        BeanUtils.toBean(list, InspectStdInfoRespVO.class));
    }

}