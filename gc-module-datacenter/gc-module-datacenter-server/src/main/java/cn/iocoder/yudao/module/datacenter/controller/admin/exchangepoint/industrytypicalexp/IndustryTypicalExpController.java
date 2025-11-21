package cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.industrytypicalexp;

import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.industrytypicalexp.vo.IndustryTypicalExpPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.industrytypicalexp.vo.IndustryTypicalExpRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.industrytypicalexp.vo.IndustryTypicalExpSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.industrytypicalexp.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.exchangepoint.industrytypicalexp.IndustryTypicalExpDO;
import cn.iocoder.yudao.module.datacenter.service.exchangepoint.industrytypicalexp.IndustryTypicalExpService;

@Tag(name = "管理后台 - 行业典型经验")
@RestController
@RequestMapping("/datacenter/industry-typical-exp")
@Validated
public class IndustryTypicalExpController {

    @Resource
    private IndustryTypicalExpService industryTypicalExpService;

    @PostMapping("/create")
    @Operation(summary = "创建行业典型经验")
    @PreAuthorize("@ss.hasPermission('datacenter:industry-typical-exp:create')")
    public CommonResult<Long> createIndustryTypicalExp(@Valid @RequestBody IndustryTypicalExpSaveReqVO createReqVO) {
        return success(industryTypicalExpService.createIndustryTypicalExp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新行业典型经验")
    @PreAuthorize("@ss.hasPermission('datacenter:industry-typical-exp:update')")
    public CommonResult<Boolean> updateIndustryTypicalExp(@Valid @RequestBody IndustryTypicalExpSaveReqVO updateReqVO) {
        industryTypicalExpService.updateIndustryTypicalExp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除行业典型经验")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:industry-typical-exp:delete')")
    public CommonResult<Boolean> deleteIndustryTypicalExp(@RequestParam("id") Long id) {
        industryTypicalExpService.deleteIndustryTypicalExp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得行业典型经验")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:industry-typical-exp:query')")
    public CommonResult<IndustryTypicalExpRespVO> getIndustryTypicalExp(@RequestParam("id") Long id) {
        IndustryTypicalExpDO industryTypicalExp = industryTypicalExpService.getIndustryTypicalExp(id);
        return success(BeanUtils.toBean(industryTypicalExp, IndustryTypicalExpRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得行业典型经验分页")
    @PreAuthorize("@ss.hasPermission('datacenter:industry-typical-exp:query')")
    public CommonResult<PageResult<IndustryTypicalExpRespVO>> getIndustryTypicalExpPage(@Valid IndustryTypicalExpPageReqVO pageReqVO) {
        PageResult<IndustryTypicalExpDO> pageResult = industryTypicalExpService.getIndustryTypicalExpPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, IndustryTypicalExpRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出行业典型经验 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:industry-typical-exp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportIndustryTypicalExpExcel(@Valid IndustryTypicalExpPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<IndustryTypicalExpDO> list = industryTypicalExpService.getIndustryTypicalExpPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "行业典型经验.xls", "数据", IndustryTypicalExpRespVO.class,
                        BeanUtils.toBean(list, IndustryTypicalExpRespVO.class));
    }

}