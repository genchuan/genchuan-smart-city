package cn.iocoder.yudao.module.smartcity.controller.admin.dynamicinformationinput;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.dynamicinformationinput.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.dynamicinformationinput.DynamicInformationInputDO;
import cn.iocoder.yudao.module.smartcity.service.dynamicinformationinput.DynamicInformationInputService;

@Tag(name = "管理后台 - 动态信息录入")
@RestController
@RequestMapping("/smartcity/dynamic-information-input")
@Validated
public class DynamicInformationInputController {

    @Resource
    private DynamicInformationInputService dynamicInformationInputService;

    @PostMapping("/create")
    @Operation(summary = "创建动态信息录入")
    @PreAuthorize("@ss.hasPermission('smartcity:dynamic-information-input:create')")
    public CommonResult<Long> createDynamicInformationInput(@Valid @RequestBody DynamicInformationInputSaveReqVO createReqVO) {
        return success(dynamicInformationInputService.createDynamicInformationInput(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新动态信息录入")
    @PreAuthorize("@ss.hasPermission('smartcity:dynamic-information-input:update')")
    public CommonResult<Boolean> updateDynamicInformationInput(@Valid @RequestBody DynamicInformationInputSaveReqVO updateReqVO) {
        dynamicInformationInputService.updateDynamicInformationInput(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除动态信息录入")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:dynamic-information-input:delete')")
    public CommonResult<Boolean> deleteDynamicInformationInput(@RequestParam("id") Long id) {
        dynamicInformationInputService.deleteDynamicInformationInput(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得动态信息录入")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:dynamic-information-input:query')")
    public CommonResult<DynamicInformationInputRespVO> getDynamicInformationInput(@RequestParam("id") Long id) {
        DynamicInformationInputDO dynamicInformationInput = dynamicInformationInputService.getDynamicInformationInput(id);
        return success(BeanUtils.toBean(dynamicInformationInput, DynamicInformationInputRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得动态信息录入分页")
    @PreAuthorize("@ss.hasPermission('smartcity:dynamic-information-input:query')")
    public CommonResult<PageResult<DynamicInformationInputRespVO>> getDynamicInformationInputPage(@Valid DynamicInformationInputPageReqVO pageReqVO) {
        PageResult<DynamicInformationInputDO> pageResult = dynamicInformationInputService.getDynamicInformationInputPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DynamicInformationInputRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出动态信息录入 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:dynamic-information-input:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDynamicInformationInputExcel(@Valid DynamicInformationInputPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DynamicInformationInputDO> list = dynamicInformationInputService.getDynamicInformationInputPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "动态信息录入.xls", "数据", DynamicInformationInputRespVO.class,
                        BeanUtils.toBean(list, DynamicInformationInputRespVO.class));
    }

}