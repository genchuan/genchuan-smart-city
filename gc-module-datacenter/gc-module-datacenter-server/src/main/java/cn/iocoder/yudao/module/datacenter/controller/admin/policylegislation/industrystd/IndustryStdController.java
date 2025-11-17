package cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.industrystd;

import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.industrystd.vo.IndustryStdPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.industrystd.vo.IndustryStdRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.industrystd.vo.IndustryStdSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.industrystd.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.industrystd.IndustryStdDO;
import cn.iocoder.yudao.module.datacenter.service.policylegislation.industrystd.IndustryStdService;

@Tag(name = "管理后台 - 行业规范信息")
@RestController
@RequestMapping("/datacenter/industry-std")
@Validated
public class IndustryStdController {

    @Resource
    private IndustryStdService industryStdService;

    @PostMapping("/create")
    @Operation(summary = "创建行业规范信息")
    @PreAuthorize("@ss.hasPermission('datacenter:industry-std:create')")
    public CommonResult<Long> createIndustryStd(@Valid @RequestBody IndustryStdSaveReqVO createReqVO) {
        return success(industryStdService.createIndustryStd(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新行业规范信息")
    @PreAuthorize("@ss.hasPermission('datacenter:industry-std:update')")
    public CommonResult<Boolean> updateIndustryStd(@Valid @RequestBody IndustryStdSaveReqVO updateReqVO) {
        industryStdService.updateIndustryStd(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除行业规范信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:industry-std:delete')")
    public CommonResult<Boolean> deleteIndustryStd(@RequestParam("id") Long id) {
        industryStdService.deleteIndustryStd(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得行业规范信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:industry-std:query')")
    public CommonResult<IndustryStdRespVO> getIndustryStd(@RequestParam("id") Long id) {
        IndustryStdDO industryStd = industryStdService.getIndustryStd(id);
        return success(BeanUtils.toBean(industryStd, IndustryStdRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得行业规范信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:industry-std:query')")
    public CommonResult<PageResult<IndustryStdRespVO>> getIndustryStdPage(@Valid IndustryStdPageReqVO pageReqVO) {
        PageResult<IndustryStdDO> pageResult = industryStdService.getIndustryStdPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, IndustryStdRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出行业规范信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:industry-std:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportIndustryStdExcel(@Valid IndustryStdPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<IndustryStdDO> list = industryStdService.getIndustryStdPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "行业规范信息.xls", "数据", IndustryStdRespVO.class,
                        BeanUtils.toBean(list, IndustryStdRespVO.class));
    }

}