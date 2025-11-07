package cn.iocoder.yudao.module.smartcity.controller.admin.experienceinformationinput;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.experienceinformationinput.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.experienceinformationinput.ExperienceInformationInputDO;
import cn.iocoder.yudao.module.smartcity.service.experienceinformationinput.ExperienceInformationInputService;

@Tag(name = "管理后台 - 经验信息录入")
@RestController
@RequestMapping("/smartcity/experience-information-input")
@Validated
public class ExperienceInformationInputController {

    @Resource
    private ExperienceInformationInputService experienceInformationInputService;

    @PostMapping("/create")
    @Operation(summary = "创建经验信息录入")
    @PreAuthorize("@ss.hasPermission('smartcity:experience-information-input:create')")
    public CommonResult<Long> createExperienceInformationInput(@Valid @RequestBody ExperienceInformationInputSaveReqVO createReqVO) {
        return success(experienceInformationInputService.createExperienceInformationInput(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新经验信息录入")
    @PreAuthorize("@ss.hasPermission('smartcity:experience-information-input:update')")
    public CommonResult<Boolean> updateExperienceInformationInput(@Valid @RequestBody ExperienceInformationInputSaveReqVO updateReqVO) {
        experienceInformationInputService.updateExperienceInformationInput(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除经验信息录入")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:experience-information-input:delete')")
    public CommonResult<Boolean> deleteExperienceInformationInput(@RequestParam("id") Long id) {
        experienceInformationInputService.deleteExperienceInformationInput(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得经验信息录入")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:experience-information-input:query')")
    public CommonResult<ExperienceInformationInputRespVO> getExperienceInformationInput(@RequestParam("id") Long id) {
        ExperienceInformationInputDO experienceInformationInput = experienceInformationInputService.getExperienceInformationInput(id);
        return success(BeanUtils.toBean(experienceInformationInput, ExperienceInformationInputRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得经验信息录入分页")
    @PreAuthorize("@ss.hasPermission('smartcity:experience-information-input:query')")
    public CommonResult<PageResult<ExperienceInformationInputRespVO>> getExperienceInformationInputPage(@Valid ExperienceInformationInputPageReqVO pageReqVO) {
        PageResult<ExperienceInformationInputDO> pageResult = experienceInformationInputService.getExperienceInformationInputPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ExperienceInformationInputRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出经验信息录入 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:experience-information-input:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExperienceInformationInputExcel(@Valid ExperienceInformationInputPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ExperienceInformationInputDO> list = experienceInformationInputService.getExperienceInformationInputPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "经验信息录入.xls", "数据", ExperienceInformationInputRespVO.class,
                        BeanUtils.toBean(list, ExperienceInformationInputRespVO.class));
    }

}