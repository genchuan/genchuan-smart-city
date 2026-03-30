package cn.iocoder.yudao.module.waterdetection.controller.admin.testresult;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.testresult.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testresult.TestResultDO;
import cn.iocoder.yudao.module.waterdetection.service.testresult.TestResultService;

@Tag(name = "管理后台 - 检测结果录入")
@RestController
@RequestMapping("/waterdetection/test-result")
@Validated
public class TestResultController {

    @Resource
    private TestResultService testResultService;

    @PostMapping("/create")
    @Operation(summary = "创建检测结果录入")
    @PreAuthorize("@ss.hasPermission('waterdetection:test-result:create')")
    public CommonResult<Long> createTestResult(@Valid @RequestBody TestResultSaveReqVO createReqVO) {
        return success(testResultService.createTestResult(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新检测结果录入")
    @PreAuthorize("@ss.hasPermission('waterdetection:test-result:update')")
    public CommonResult<Boolean> updateTestResult(@Valid @RequestBody TestResultSaveReqVO updateReqVO) {
        testResultService.updateTestResult(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除检测结果录入")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:test-result:delete')")
    public CommonResult<Boolean> deleteTestResult(@RequestParam("id") Long id) {
        testResultService.deleteTestResult(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得检测结果录入")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:test-result:query')")
    public CommonResult<TestResultRespVO> getTestResult(@RequestParam("id") Long id) {
        TestResultDO testResult = testResultService.getTestResult(id);
        return success(BeanUtils.toBean(testResult, TestResultRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得检测结果录入分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:test-result:query')")
    public CommonResult<PageResult<TestResultRespVO>> getTestResultPage(@Valid TestResultPageReqVO pageReqVO) {
        PageResult<TestResultDO> pageResult = testResultService.getTestResultPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TestResultRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出检测结果录入 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:test-result:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTestResultExcel(@Valid TestResultPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TestResultDO> list = testResultService.getTestResultPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "检测结果录入.xls", "数据", TestResultRespVO.class,
                        BeanUtils.toBean(list, TestResultRespVO.class));
    }

}