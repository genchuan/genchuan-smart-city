package cn.iocoder.yudao.module.waterdetection.controller.admin.testingagency;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.testingagency.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testingagency.TestingAgencyDO;
import cn.iocoder.yudao.module.waterdetection.service.testingagency.TestingAgencyService;

@Tag(name = "管理后台 - 检测机构资质管理")
@RestController
@RequestMapping("/waterdetection/testing-agency")
@Validated
public class TestingAgencyController {

    @Resource
    private TestingAgencyService testingAgencyService;

    @PostMapping("/create")
    @Operation(summary = "创建检测机构资质管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-agency:create')")
    public CommonResult<Long> createTestingAgency(@Valid @RequestBody TestingAgencySaveReqVO createReqVO) {
        return success(testingAgencyService.createTestingAgency(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新检测机构资质管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-agency:update')")
    public CommonResult<Boolean> updateTestingAgency(@Valid @RequestBody TestingAgencySaveReqVO updateReqVO) {
        testingAgencyService.updateTestingAgency(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除检测机构资质管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-agency:delete')")
    public CommonResult<Boolean> deleteTestingAgency(@RequestParam("id") Long id) {
        testingAgencyService.deleteTestingAgency(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得检测机构资质管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-agency:query')")
    public CommonResult<TestingAgencyRespVO> getTestingAgency(@RequestParam("id") Long id) {
        TestingAgencyDO testingAgency = testingAgencyService.getTestingAgency(id);
        return success(BeanUtils.toBean(testingAgency, TestingAgencyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得检测机构资质管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-agency:query')")
    public CommonResult<PageResult<TestingAgencyRespVO>> getTestingAgencyPage(@Valid TestingAgencyPageReqVO pageReqVO) {
        PageResult<TestingAgencyDO> pageResult = testingAgencyService.getTestingAgencyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TestingAgencyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出检测机构资质管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-agency:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTestingAgencyExcel(@Valid TestingAgencyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TestingAgencyDO> list = testingAgencyService.getTestingAgencyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "检测机构资质管理.xls", "数据", TestingAgencyRespVO.class,
                        BeanUtils.toBean(list, TestingAgencyRespVO.class));
    }

}