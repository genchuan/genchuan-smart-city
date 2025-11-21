package cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.localregulation;

import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.localregulation.vo.LocalRegulationPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.localregulation.vo.LocalRegulationRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.localregulation.vo.LocalRegulationSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.localregulation.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.localregulation.LocalRegulationDO;
import cn.iocoder.yudao.module.datacenter.service.policylegislation.localregulation.LocalRegulationService;

@Tag(name = "管理后台 - 地方法规信息")
@RestController
@RequestMapping("/datacenter/local-regulation")
@Validated
public class LocalRegulationController {

    @Resource
    private LocalRegulationService localRegulationService;

    @PostMapping("/create")
    @Operation(summary = "创建地方法规信息")
    @PreAuthorize("@ss.hasPermission('datacenter:local-regulation:create')")
    public CommonResult<Long> createLocalRegulation(@Valid @RequestBody LocalRegulationSaveReqVO createReqVO) {
        return success(localRegulationService.createLocalRegulation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新地方法规信息")
    @PreAuthorize("@ss.hasPermission('datacenter:local-regulation:update')")
    public CommonResult<Boolean> updateLocalRegulation(@Valid @RequestBody LocalRegulationSaveReqVO updateReqVO) {
        localRegulationService.updateLocalRegulation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除地方法规信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:local-regulation:delete')")
    public CommonResult<Boolean> deleteLocalRegulation(@RequestParam("id") Long id) {
        localRegulationService.deleteLocalRegulation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得地方法规信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:local-regulation:query')")
    public CommonResult<LocalRegulationRespVO> getLocalRegulation(@RequestParam("id") Long id) {
        LocalRegulationDO localRegulation = localRegulationService.getLocalRegulation(id);
        return success(BeanUtils.toBean(localRegulation, LocalRegulationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得地方法规信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:local-regulation:query')")
    public CommonResult<PageResult<LocalRegulationRespVO>> getLocalRegulationPage(@Valid LocalRegulationPageReqVO pageReqVO) {
        PageResult<LocalRegulationDO> pageResult = localRegulationService.getLocalRegulationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, LocalRegulationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出地方法规信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:local-regulation:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportLocalRegulationExcel(@Valid LocalRegulationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<LocalRegulationDO> list = localRegulationService.getLocalRegulationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "地方法规信息.xls", "数据", LocalRegulationRespVO.class,
                        BeanUtils.toBean(list, LocalRegulationRespVO.class));
    }

}