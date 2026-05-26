package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterAuditReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterConfirmReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterCorrectReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterCreateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.unplateenter.UnplateEnterDO;
import cn.iocoder.yudao.module.vehiclepass.service.entermgmt.unplateenter.UnplateEnterService;
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



@Tag(name = "管理后台 - 无牌入场")
@RestController
@RequestMapping("/vehiclepass/unplate-enter")
@Validated
public class UnplateEnterController {

    @Resource
    private UnplateEnterService enterService;

    @PostMapping("/vehiclepass-unplate-enter-create")
    @Operation(summary = "创建无牌入场")
    @PreAuthorize("@ss.hasPermission('unplate:enter:create')")
    public CommonResult<Long> createEnter(@Valid @RequestBody UnplateEnterSaveReqVO createReqVO) {
        return success(enterService.createEnter(createReqVO));
    }

    @PostMapping("/create")
    @Operation(summary = "创建无牌入场车辆")
    @PreAuthorize("@ss.hasPermission('vehiclepass:unplate-enter:create')")
    public CommonResult<Boolean> createEnterVehiclePass(@Valid @RequestBody UnplateEnterCreateReqVO createReqVO) {
        enterService.createEnterVehiclePass(createReqVO);
        return success(true);
    }

    @PutMapping("/update")
    @Operation(summary = "更新无牌入场")
    @PreAuthorize("@ss.hasPermission('unplate:enter:update')")
    public CommonResult<Boolean> updateEnter(@Valid @RequestBody UnplateEnterSaveReqVO updateReqVO) {
        enterService.updateEnter(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除无牌入场")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('unplate:enter:delete')")
    public CommonResult<Boolean> deleteEnter(@RequestParam("id") Long id) {
        enterService.deleteEnter(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除无牌入场")
    @PreAuthorize("@ss.hasPermission('unplate:enter:delete')")
    public CommonResult<Boolean> deleteEnterList(@RequestParam("ids") List<Long> ids) {
        enterService.deleteEnterListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得无牌入场")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('unplate:enter:query')")
    public CommonResult<UnplateEnterRespVO> getEnter(@RequestParam("id") Long id) {
        UnplateEnterRespVO enter = enterService.getUnplateEnterWithStation(id);
        return success(enter);
    }

    @GetMapping("/page")
    @Operation(summary = "获得无牌入场分页")
    @PreAuthorize("@ss.hasPermission('vehiclepass:unplate-enter:query')")
    public CommonResult<PageResult<UnplateEnterRespVO>> getEnterPage(@Valid UnplateEnterPageReqVO pageReqVO) {
        return success(enterService.getUnplateEnterPage(pageReqVO));
    }

    @PutMapping("/audit")
    @Operation(summary = "审核无牌入场")
    @PreAuthorize("@ss.hasPermission('vehiclepass:unplate-enter:audit')")
    public CommonResult<Boolean> auditEnter(@Valid @RequestBody UnplateEnterAuditReqVO reqVO) {
        enterService.auditEnter(reqVO);
        return success(true);
    }

    @PutMapping("/confirm")
    @Operation(summary = "确认无牌入场")
    @PreAuthorize("@ss.hasPermission('vehiclepass:unplate-enter:confirm')")
    public CommonResult<Boolean> confirmEnter(@Valid @RequestBody UnplateEnterConfirmReqVO reqVO) {
        enterService.confirmEnter(reqVO);
        return success(true);
    }

    @PutMapping("/correct")
    @Operation(summary = "修正无牌入场")
    @PreAuthorize("@ss.hasPermission('vehiclepass:unplate-enter:correct')")
    public CommonResult<Boolean> correctEnter(@Valid @RequestBody UnplateEnterCorrectReqVO reqVO) {
        enterService.correctEnter(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "无牌入场统计")
    @PreAuthorize("@ss.hasPermission('vehiclepass:unplate-enter:chart')")
    public CommonResult<UnplateEnterChartRespVO> getUnplateEnterChart(@Valid UnplateEnterChartReqVO reqVO) {
        return success(enterService.getUnplateEnterChart(reqVO));
    }

    @GetMapping("/export")
    @Operation(summary = "导出无牌入场 Excel")
    @PreAuthorize("@ss.hasPermission('unplate:enter:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEnterExcel(UnplateEnterPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageNo(1);
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<UnplateEnterRespVO> pageResult = enterService.getUnplateEnterPage(pageReqVO);
        ExcelUtils.write(response, "无牌入场.xls", "数据", UnplateEnterRespVO.class, pageResult.getList());
    }

}