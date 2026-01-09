package cn.iocoder.yudao.module.smartcity.controller.admin.componentinformation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.componentinformation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.componentinformation.ComponentInformationDO;
import cn.iocoder.yudao.module.smartcity.service.componentinformation.ComponentInformationService;

@Tag(name = "管理后台 - 部件信息")
@RestController
@RequestMapping("/smartcity/component-information")
@Validated
public class ComponentInformationController {

    @Resource
    private ComponentInformationService componentInformationService;

    @PostMapping("/create")
    @Operation(summary = "创建部件信息")
    @PreAuthorize("@ss.hasPermission('smartcity:component-information:create')")
    public CommonResult<Long> createComponentInformation(@Valid @RequestBody ComponentInformationSaveReqVO createReqVO) {
        return success(componentInformationService.createComponentInformation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新部件信息")
    @PreAuthorize("@ss.hasPermission('smartcity:component-information:update')")
    public CommonResult<Boolean> updateComponentInformation(@Valid @RequestBody ComponentInformationSaveReqVO updateReqVO) {
        componentInformationService.updateComponentInformation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除部件信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:component-information:delete')")
    public CommonResult<Boolean> deleteComponentInformation(@RequestParam("id") Long id) {
        componentInformationService.deleteComponentInformation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得部件信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:component-information:query')")
    public CommonResult<ComponentInformationRespVO> getComponentInformation(@RequestParam("id") Long id) {
        ComponentInformationDO componentInformation = componentInformationService.getComponentInformation(id);
        return success(BeanUtils.toBean(componentInformation, ComponentInformationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得部件信息分页")
    @PreAuthorize("@ss.hasPermission('smartcity:component-information:query')")
    public CommonResult<PageResult<ComponentInformationRespVO>> getComponentInformationPage(@Valid ComponentInformationPageReqVO pageReqVO) {
        PageResult<ComponentInformationDO> pageResult = componentInformationService.getComponentInformationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ComponentInformationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出部件信息 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:component-information:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportComponentInformationExcel(@Valid ComponentInformationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ComponentInformationDO> list = componentInformationService.getComponentInformationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "部件信息.xls", "数据", ComponentInformationRespVO.class,
                        BeanUtils.toBean(list, ComponentInformationRespVO.class));
    }

}