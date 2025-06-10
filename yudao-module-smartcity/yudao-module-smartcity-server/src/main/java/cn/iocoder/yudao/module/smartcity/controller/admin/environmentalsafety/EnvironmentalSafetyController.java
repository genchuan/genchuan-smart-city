package cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsafety;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsafety.vo.EnvironmentalSafetyPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsafety.vo.EnvironmentalSafetyRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsafety.vo.EnvironmentalSafetySaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.environmentalsafety.EnvironmentalSafetyDO;
import cn.iocoder.yudao.module.smartcity.service.environmentalsafety.EnvironmentalSafetyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 环卫设施安全")
@RestController
@RequestMapping("/smartcity/environmental-safety")
@Validated
public class EnvironmentalSafetyController {

    @Resource
    private EnvironmentalSafetyService environmentalSafetyService;

    @PostMapping("/create")
    @Operation(summary = "创建环卫设施安全")
    @PreAuthorize("@ss.hasPermission('smartcity:environmental-safety:create')")
    public CommonResult<Long> createEnvironmentalSafety(@Valid @RequestBody EnvironmentalSafetySaveReqVO createReqVO) {
        return success(environmentalSafetyService.createEnvironmentalSafety(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新环卫设施安全")
    @PreAuthorize("@ss.hasPermission('smartcity:environmental-safety:update')")
    public CommonResult<Boolean> updateEnvironmentalSafety(@Valid @RequestBody EnvironmentalSafetySaveReqVO updateReqVO) {
        environmentalSafetyService.updateEnvironmentalSafety(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除环卫设施安全")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:environmental-safety:delete')")
    public CommonResult<Boolean> deleteEnvironmentalSafety(@RequestParam("id") Long id) {
        environmentalSafetyService.deleteEnvironmentalSafety(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得环卫设施安全")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:environmental-safety:query')")
    public CommonResult<EnvironmentalSafetyRespVO> getEnvironmentalSafety(@RequestParam("id") Long id) {
        EnvironmentalSafetyDO environmentalSafety = environmentalSafetyService.getEnvironmentalSafety(id);
        return success(BeanUtils.toBean(environmentalSafety, EnvironmentalSafetyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得环卫设施安全分页")
    @PreAuthorize("@ss.hasPermission('smartcity:environmental-safety:query')")
    public CommonResult<PageResult<EnvironmentalSafetyRespVO>> getEnvironmentalSafetyPage(@Valid EnvironmentalSafetyPageReqVO pageReqVO) {
        PageResult<EnvironmentalSafetyDO> pageResult = environmentalSafetyService.getEnvironmentalSafetyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EnvironmentalSafetyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出环卫设施安全 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:environmental-safety:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEnvironmentalSafetyExcel(@Valid EnvironmentalSafetyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EnvironmentalSafetyDO> list = environmentalSafetyService.getEnvironmentalSafetyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "环卫设施安全.xls", "数据", EnvironmentalSafetyRespVO.class,
                        BeanUtils.toBean(list, EnvironmentalSafetyRespVO.class));
    }

}