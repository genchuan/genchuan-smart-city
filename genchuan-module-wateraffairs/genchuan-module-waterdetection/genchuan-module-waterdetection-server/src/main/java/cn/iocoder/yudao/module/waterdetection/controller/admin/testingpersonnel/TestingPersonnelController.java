package cn.iocoder.yudao.module.waterdetection.controller.admin.testingpersonnel;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.testingpersonnel.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testingpersonnel.TestingPersonnelDO;
import cn.iocoder.yudao.module.waterdetection.service.testingpersonnel.TestingPersonnelService;

@Tag(name = "管理后台 - 检测人员信息管理")
@RestController
@RequestMapping("/waterdetection/testing-personnel")
@Validated
public class TestingPersonnelController {

    @Resource
    private TestingPersonnelService testingPersonnelService;

    @PostMapping("/create")
    @Operation(summary = "创建检测人员信息管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-personnel:create')")
    public CommonResult<Long> createTestingPersonnel(@Valid @RequestBody TestingPersonnelSaveReqVO createReqVO) {
        return success(testingPersonnelService.createTestingPersonnel(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新检测人员信息管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-personnel:update')")
    public CommonResult<Boolean> updateTestingPersonnel(@Valid @RequestBody TestingPersonnelSaveReqVO updateReqVO) {
        testingPersonnelService.updateTestingPersonnel(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除检测人员信息管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-personnel:delete')")
    public CommonResult<Boolean> deleteTestingPersonnel(@RequestParam("id") Long id) {
        testingPersonnelService.deleteTestingPersonnel(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得检测人员信息管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-personnel:query')")
    public CommonResult<TestingPersonnelRespVO> getTestingPersonnel(@RequestParam("id") Long id) {
        TestingPersonnelDO testingPersonnel = testingPersonnelService.getTestingPersonnel(id);
        return success(BeanUtils.toBean(testingPersonnel, TestingPersonnelRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得检测人员信息管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-personnel:query')")
    public CommonResult<PageResult<TestingPersonnelRespVO>> getTestingPersonnelPage(@Valid TestingPersonnelPageReqVO pageReqVO) {
        PageResult<TestingPersonnelDO> pageResult = testingPersonnelService.getTestingPersonnelPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TestingPersonnelRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出检测人员信息管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-personnel:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTestingPersonnelExcel(@Valid TestingPersonnelPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TestingPersonnelDO> list = testingPersonnelService.getTestingPersonnelPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "检测人员信息管理.xls", "数据", TestingPersonnelRespVO.class,
                        BeanUtils.toBean(list, TestingPersonnelRespVO.class));
    }

}