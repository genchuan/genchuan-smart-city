package cn.iocoder.yudao.module.waterdetection.controller.admin.testingcapability;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.testingcapability.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testingcapability.TestingCapabilityDO;
import cn.iocoder.yudao.module.waterdetection.service.testingcapability.TestingCapabilityService;

@Tag(name = "管理后台 - 检测能力及设备管理")
@RestController
@RequestMapping("/waterdetection/testing-capability")
@Validated
public class TestingCapabilityController {

    @Resource
    private TestingCapabilityService testingCapabilityService;

    @PostMapping("/create")
    @Operation(summary = "创建检测能力及设备管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-capability:create')")
    public CommonResult<Long> createTestingCapability(@Valid @RequestBody TestingCapabilitySaveReqVO createReqVO) {
        return success(testingCapabilityService.createTestingCapability(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新检测能力及设备管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-capability:update')")
    public CommonResult<Boolean> updateTestingCapability(@Valid @RequestBody TestingCapabilitySaveReqVO updateReqVO) {
        testingCapabilityService.updateTestingCapability(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除检测能力及设备管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-capability:delete')")
    public CommonResult<Boolean> deleteTestingCapability(@RequestParam("id") Long id) {
        testingCapabilityService.deleteTestingCapability(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得检测能力及设备管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-capability:query')")
    public CommonResult<TestingCapabilityRespVO> getTestingCapability(@RequestParam("id") Long id) {
        TestingCapabilityDO testingCapability = testingCapabilityService.getTestingCapability(id);
        return success(BeanUtils.toBean(testingCapability, TestingCapabilityRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得检测能力及设备管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-capability:query')")
    public CommonResult<PageResult<TestingCapabilityRespVO>> getTestingCapabilityPage(@Valid TestingCapabilityPageReqVO pageReqVO) {
        PageResult<TestingCapabilityDO> pageResult = testingCapabilityService.getTestingCapabilityPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TestingCapabilityRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出检测能力及设备管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:testing-capability:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTestingCapabilityExcel(@Valid TestingCapabilityPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TestingCapabilityDO> list = testingCapabilityService.getTestingCapabilityPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "检测能力及设备管理.xls", "数据", TestingCapabilityRespVO.class,
                        BeanUtils.toBean(list, TestingCapabilityRespVO.class));
    }

}