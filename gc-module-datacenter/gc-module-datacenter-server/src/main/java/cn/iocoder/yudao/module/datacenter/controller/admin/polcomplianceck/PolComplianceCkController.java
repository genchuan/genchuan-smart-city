package cn.iocoder.yudao.module.datacenter.controller.admin.polcomplianceck;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.polcomplianceck.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.polcomplianceck.PolComplianceCkDO;
import cn.iocoder.yudao.module.datacenter.service.polcomplianceck.PolComplianceCkService;

@Tag(name = "管理后台 - 政策合规校验")
@RestController
@RequestMapping("/datacenter/pol-compliance-ck")
@Validated
public class PolComplianceCkController {

    @Resource
    private PolComplianceCkService polComplianceCkService;

    @PostMapping("/create")
    @Operation(summary = "创建政策合规校验")
    @PreAuthorize("@ss.hasPermission('datacenter:pol-compliance-ck:create')")
    public CommonResult<Long> createPolComplianceCk(@Valid @RequestBody PolComplianceCkSaveReqVO createReqVO) {
        return success(polComplianceCkService.createPolComplianceCk(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新政策合规校验")
    @PreAuthorize("@ss.hasPermission('datacenter:pol-compliance-ck:update')")
    public CommonResult<Boolean> updatePolComplianceCk(@Valid @RequestBody PolComplianceCkSaveReqVO updateReqVO) {
        polComplianceCkService.updatePolComplianceCk(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除政策合规校验")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:pol-compliance-ck:delete')")
    public CommonResult<Boolean> deletePolComplianceCk(@RequestParam("id") Long id) {
        polComplianceCkService.deletePolComplianceCk(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得政策合规校验")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:pol-compliance-ck:query')")
    public CommonResult<PolComplianceCkRespVO> getPolComplianceCk(@RequestParam("id") Long id) {
        PolComplianceCkDO polComplianceCk = polComplianceCkService.getPolComplianceCk(id);
        return success(BeanUtils.toBean(polComplianceCk, PolComplianceCkRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得政策合规校验分页")
    @PreAuthorize("@ss.hasPermission('datacenter:pol-compliance-ck:query')")
    public CommonResult<PageResult<PolComplianceCkRespVO>> getPolComplianceCkPage(@Valid PolComplianceCkPageReqVO pageReqVO) {
        PageResult<PolComplianceCkDO> pageResult = polComplianceCkService.getPolComplianceCkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PolComplianceCkRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出政策合规校验 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:pol-compliance-ck:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPolComplianceCkExcel(@Valid PolComplianceCkPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PolComplianceCkDO> list = polComplianceCkService.getPolComplianceCkPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "政策合规校验.xls", "数据", PolComplianceCkRespVO.class,
                        BeanUtils.toBean(list, PolComplianceCkRespVO.class));
    }

}