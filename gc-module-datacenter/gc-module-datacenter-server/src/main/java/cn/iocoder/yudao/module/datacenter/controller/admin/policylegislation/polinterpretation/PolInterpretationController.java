package cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.polinterpretation;

import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.polinterpretation.vo.PolInterpretationPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.polinterpretation.vo.PolInterpretationRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.polinterpretation.vo.PolInterpretationSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.polinterpretation.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.polinterpretation.PolInterpretationDO;
import cn.iocoder.yudao.module.datacenter.service.policylegislation.polinterpretation.PolInterpretationService;

@Tag(name = "管理后台 - 政策解读信息")
@RestController
@RequestMapping("/datacenter/pol-interpretation")
@Validated
public class PolInterpretationController {

    @Resource
    private PolInterpretationService polInterpretationService;

    @PostMapping("/create")
    @Operation(summary = "创建政策解读信息")
    @PreAuthorize("@ss.hasPermission('datacenter:pol-interpretation:create')")
    public CommonResult<Long> createPolInterpretation(@Valid @RequestBody PolInterpretationSaveReqVO createReqVO) {
        return success(polInterpretationService.createPolInterpretation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新政策解读信息")
    @PreAuthorize("@ss.hasPermission('datacenter:pol-interpretation:update')")
    public CommonResult<Boolean> updatePolInterpretation(@Valid @RequestBody PolInterpretationSaveReqVO updateReqVO) {
        polInterpretationService.updatePolInterpretation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除政策解读信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:pol-interpretation:delete')")
    public CommonResult<Boolean> deletePolInterpretation(@RequestParam("id") Long id) {
        polInterpretationService.deletePolInterpretation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得政策解读信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:pol-interpretation:query')")
    public CommonResult<PolInterpretationRespVO> getPolInterpretation(@RequestParam("id") Long id) {
        PolInterpretationDO polInterpretation = polInterpretationService.getPolInterpretation(id);
        return success(BeanUtils.toBean(polInterpretation, PolInterpretationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得政策解读信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:pol-interpretation:query')")
    public CommonResult<PageResult<PolInterpretationRespVO>> getPolInterpretationPage(@Valid PolInterpretationPageReqVO pageReqVO) {
        PageResult<PolInterpretationDO> pageResult = polInterpretationService.getPolInterpretationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PolInterpretationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出政策解读信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:pol-interpretation:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPolInterpretationExcel(@Valid PolInterpretationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PolInterpretationDO> list = polInterpretationService.getPolInterpretationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "政策解读信息.xls", "数据", PolInterpretationRespVO.class,
                        BeanUtils.toBean(list, PolInterpretationRespVO.class));
    }

}