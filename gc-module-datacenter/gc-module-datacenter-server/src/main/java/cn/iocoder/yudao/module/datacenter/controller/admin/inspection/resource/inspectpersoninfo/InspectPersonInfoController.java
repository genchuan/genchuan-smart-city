package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectpersoninfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectpersoninfo.vo.InspectPersonInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectpersoninfo.vo.InspectPersonInfoRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectpersoninfo.vo.InspectPersonInfoSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.resource.inspectpersoninfo.InspectPersonInfoDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.resource.inspectpersoninfo.InspectPersonInfoService;

@Tag(name = "管理后台 - 巡查巡检人员信息")
@RestController
@RequestMapping("/datacenter/inspect-person-info")
@Validated
public class InspectPersonInfoController {

    @Resource
    private InspectPersonInfoService inspectPersonInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检人员信息")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-person-info:create')")
    public CommonResult<Long> createInspectPersonInfo(@Valid @RequestBody InspectPersonInfoSaveReqVO createReqVO) {
        return success(inspectPersonInfoService.createInspectPersonInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检人员信息")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-person-info:update')")
    public CommonResult<Boolean> updateInspectPersonInfo(@Valid @RequestBody InspectPersonInfoSaveReqVO updateReqVO) {
        inspectPersonInfoService.updateInspectPersonInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检人员信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-person-info:delete')")
    public CommonResult<Boolean> deleteInspectPersonInfo(@RequestParam("id") Long id) {
        inspectPersonInfoService.deleteInspectPersonInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检人员信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-person-info:query')")
    public CommonResult<InspectPersonInfoRespVO> getInspectPersonInfo(@RequestParam("id") Long id) {
        InspectPersonInfoDO inspectPersonInfo = inspectPersonInfoService.getInspectPersonInfo(id);
        return success(BeanUtils.toBean(inspectPersonInfo, InspectPersonInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检人员信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-person-info:query')")
    public CommonResult<PageResult<InspectPersonInfoRespVO>> getInspectPersonInfoPage(@Valid InspectPersonInfoPageReqVO pageReqVO) {
        PageResult<InspectPersonInfoDO> pageResult = inspectPersonInfoService.getInspectPersonInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectPersonInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检人员信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-person-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectPersonInfoExcel(@Valid InspectPersonInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectPersonInfoDO> list = inspectPersonInfoService.getInspectPersonInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检人员信息.xls", "数据", InspectPersonInfoRespVO.class,
                        BeanUtils.toBean(list, InspectPersonInfoRespVO.class));
    }

}