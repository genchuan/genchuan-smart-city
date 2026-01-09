package cn.iocoder.yudao.module.smartcity.controller.admin.bridgesafety;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.bridgesafety.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.bridgesafety.BridgeSafetyDO;
import cn.iocoder.yudao.module.smartcity.service.bridgesafety.BridgeSafetyService;

@Tag(name = "管理后台 - 桥梁安全")
@RestController
@RequestMapping("/smartcity/bridge-safety")
@Validated
public class BridgeSafetyController {

    @Resource
    private BridgeSafetyService bridgeSafetyService;

    @PostMapping("/create")
    @Operation(summary = "创建桥梁安全")
    @PreAuthorize("@ss.hasPermission('smartcity:bridge-safety:create')")
    public CommonResult<Long> createBridgeSafety(@Valid @RequestBody BridgeSafetySaveReqVO createReqVO) {
        return success(bridgeSafetyService.createBridgeSafety(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新桥梁安全")
    @PreAuthorize("@ss.hasPermission('smartcity:bridge-safety:update')")
    public CommonResult<Boolean> updateBridgeSafety(@Valid @RequestBody BridgeSafetySaveReqVO updateReqVO) {
        bridgeSafetyService.updateBridgeSafety(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除桥梁安全")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:bridge-safety:delete')")
    public CommonResult<Boolean> deleteBridgeSafety(@RequestParam("id") Long id) {
        bridgeSafetyService.deleteBridgeSafety(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得桥梁安全")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:bridge-safety:query')")
    public CommonResult<BridgeSafetyRespVO> getBridgeSafety(@RequestParam("id") Long id) {
        BridgeSafetyDO bridgeSafety = bridgeSafetyService.getBridgeSafety(id);
        return success(BeanUtils.toBean(bridgeSafety, BridgeSafetyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得桥梁安全分页")
    @PreAuthorize("@ss.hasPermission('smartcity:bridge-safety:query')")
    public CommonResult<PageResult<BridgeSafetyRespVO>> getBridgeSafetyPage(@Valid BridgeSafetyPageReqVO pageReqVO) {
        PageResult<BridgeSafetyDO> pageResult = bridgeSafetyService.getBridgeSafetyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BridgeSafetyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出桥梁安全 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:bridge-safety:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBridgeSafetyExcel(@Valid BridgeSafetyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BridgeSafetyDO> list = bridgeSafetyService.getBridgeSafetyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "桥梁安全.xls", "数据", BridgeSafetyRespVO.class,
                        BeanUtils.toBean(list, BridgeSafetyRespVO.class));
    }

}