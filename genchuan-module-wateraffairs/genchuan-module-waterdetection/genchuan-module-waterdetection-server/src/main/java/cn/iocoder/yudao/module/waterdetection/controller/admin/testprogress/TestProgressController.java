package cn.iocoder.yudao.module.waterdetection.controller.admin.testprogress;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.testprogress.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testprogress.TestProgressDO;
import cn.iocoder.yudao.module.waterdetection.service.testprogress.TestProgressService;

@Tag(name = "管理后台 - 检测进度跟踪")
@RestController
@RequestMapping("/waterdetection/test-progress")
@Validated
public class TestProgressController {

    @Resource
    private TestProgressService testProgressService;

    @PostMapping("/create")
    @Operation(summary = "创建检测进度跟踪")
    @PreAuthorize("@ss.hasPermission('waterdetection:test-progress:create')")
    public CommonResult<Long> createTestProgress(@Valid @RequestBody TestProgressSaveReqVO createReqVO) {
        return success(testProgressService.createTestProgress(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新检测进度跟踪")
    @PreAuthorize("@ss.hasPermission('waterdetection:test-progress:update')")
    public CommonResult<Boolean> updateTestProgress(@Valid @RequestBody TestProgressSaveReqVO updateReqVO) {
        testProgressService.updateTestProgress(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除检测进度跟踪")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:test-progress:delete')")
    public CommonResult<Boolean> deleteTestProgress(@RequestParam("id") Long id) {
        testProgressService.deleteTestProgress(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得检测进度跟踪")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:test-progress:query')")
    public CommonResult<TestProgressRespVO> getTestProgress(@RequestParam("id") Long id) {
        TestProgressDO testProgress = testProgressService.getTestProgress(id);
        return success(BeanUtils.toBean(testProgress, TestProgressRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得检测进度跟踪分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:test-progress:query')")
    public CommonResult<PageResult<TestProgressRespVO>> getTestProgressPage(@Valid TestProgressPageReqVO pageReqVO) {
        PageResult<TestProgressDO> pageResult = testProgressService.getTestProgressPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TestProgressRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出检测进度跟踪 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:test-progress:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTestProgressExcel(@Valid TestProgressPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TestProgressDO> list = testProgressService.getTestProgressPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "检测进度跟踪.xls", "数据", TestProgressRespVO.class,
                        BeanUtils.toBean(list, TestProgressRespVO.class));
    }

}