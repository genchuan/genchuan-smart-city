package cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsanitation;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsanitation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.environmentalsanitation.EnvironmentalSanitationDO;
import cn.iocoder.yudao.module.smartcity.service.environmentalsanitation.EnvironmentalSanitationService;

@Tag(name = "管理后台 - 市容环卫")
@RestController
@RequestMapping("/smartcity/environmental-sanitation")
@Validated
public class EnvironmentalSanitationController {

    @Resource
    private EnvironmentalSanitationService environmentalSanitationService;

    @PostMapping("/create")
    @Operation(summary = "创建市容环卫")
    @PreAuthorize("@ss.hasPermission('smartcity:environmental-sanitation:create')")
    public CommonResult<Long> createEnvironmentalSanitation(@Valid @RequestBody EnvironmentalSanitationSaveReqVO createReqVO) {
        return success(environmentalSanitationService.createEnvironmentalSanitation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新市容环卫")
    @PreAuthorize("@ss.hasPermission('smartcity:environmental-sanitation:update')")
    public CommonResult<Boolean> updateEnvironmentalSanitation(@Valid @RequestBody EnvironmentalSanitationSaveReqVO updateReqVO) {
        environmentalSanitationService.updateEnvironmentalSanitation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除市容环卫")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:environmental-sanitation:delete')")
    public CommonResult<Boolean> deleteEnvironmentalSanitation(@RequestParam("id") Long id) {
        environmentalSanitationService.deleteEnvironmentalSanitation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得市容环卫")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:environmental-sanitation:query')")
    public CommonResult<EnvironmentalSanitationRespVO> getEnvironmentalSanitation(@RequestParam("id") Long id) {
        EnvironmentalSanitationDO environmentalSanitation = environmentalSanitationService.getEnvironmentalSanitation(id);
        return success(BeanUtils.toBean(environmentalSanitation, EnvironmentalSanitationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得市容环卫分页")
    @PreAuthorize("@ss.hasPermission('smartcity:environmental-sanitation:query')")
    public CommonResult<PageResult<EnvironmentalSanitationRespVO>> getEnvironmentalSanitationPage(@Valid EnvironmentalSanitationPageReqVO pageReqVO) {
        PageResult<EnvironmentalSanitationDO> pageResult = environmentalSanitationService.getEnvironmentalSanitationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EnvironmentalSanitationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出市容环卫 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:environmental-sanitation:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEnvironmentalSanitationExcel(@Valid EnvironmentalSanitationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EnvironmentalSanitationDO> list = environmentalSanitationService.getEnvironmentalSanitationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "市容环卫.xls", "数据", EnvironmentalSanitationRespVO.class,
                        BeanUtils.toBean(list, EnvironmentalSanitationRespVO.class));
    }

}