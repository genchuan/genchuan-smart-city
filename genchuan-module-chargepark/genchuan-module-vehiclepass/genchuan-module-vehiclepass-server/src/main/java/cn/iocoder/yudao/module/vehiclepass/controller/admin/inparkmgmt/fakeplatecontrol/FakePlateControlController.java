package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlBatchHandleReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlCheckReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlIgnoreReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlUpdateProgressReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.MyFakePlateControlRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.fakeplatecontrol.FakePlateControlDO;
import cn.iocoder.yudao.module.vehiclepass.service.inparkmgmt.fakeplatecontrol.FakePlateControlService;
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

@Tag(name = "管理后台 - 套牌管控")
@RestController
@RequestMapping("/vehiclepass/fake-plate-control")
@Validated
public class FakePlateControlController {

    @Resource
    private FakePlateControlService plateControlService;

    @PostMapping("/create")
    @Operation(summary = "创建套牌管控")
    @PreAuthorize("@ss.hasPermission('fake:plate-control:create')")
    public CommonResult<Long> createPlateControl(@Valid @RequestBody FakePlateControlSaveReqVO createReqVO) {
        return success(plateControlService.createPlateControl(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新套牌管控")
    @PreAuthorize("@ss.hasPermission('fake:plate-control:update')")
    public CommonResult<Boolean> updatePlateControl(@Valid @RequestBody FakePlateControlSaveReqVO updateReqVO) {
        plateControlService.updatePlateControl(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除套牌管控")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('fake:plate-control:delete')")
    public CommonResult<Boolean> deletePlateControl(@RequestParam("id") Long id) {
        plateControlService.deletePlateControl(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除套牌管控")
    @PreAuthorize("@ss.hasPermission('fake:plate-control:delete')")
    public CommonResult<Boolean> deletePlateControlList(@RequestParam("ids") List<Long> ids) {
        plateControlService.deletePlateControlListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得套牌管控")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('fake:plate-control:query')")
    public CommonResult<FakePlateControlRespVO> getPlateControl(@RequestParam("id") Long id) {
        FakePlateControlDO plateControl = plateControlService.getPlateControl(id);
        return success(BeanUtils.toBean(plateControl, FakePlateControlRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得套牌管控分页")
    @PreAuthorize("@ss.hasPermission('vehiclepass:fake-plate-control:query')")
    public CommonResult<PageResult<MyFakePlateControlRespVO>> getPlateControlPage(@Valid FakePlateControlPageReqVO pageReqVO) {
        return success(plateControlService.getFakePlateControlPage(pageReqVO));
    }

    @PostMapping("/batch-handle")
    @Operation(summary = "批量处置套牌管控")
    @PreAuthorize("@ss.hasPermission('vehiclepass:fake-plate-control:batch-handle')")
    public CommonResult<Boolean> batchHandle(@Valid @RequestBody FakePlateControlBatchHandleReqVO reqVO) {
        plateControlService.batchHandle(reqVO);
        return success(true);
    }

    @PutMapping("/check")
    @Operation(summary = "核查套牌管控")
    @PreAuthorize("@ss.hasPermission('vehiclepass:fake-plate-control:check')")
    public CommonResult<Boolean> check(@Valid @RequestBody FakePlateControlCheckReqVO reqVO) {
        plateControlService.check(reqVO);
        return success(true);
    }

    @PutMapping("/ignore")
    @Operation(summary = "忽略套牌管控")
    @PreAuthorize("@ss.hasPermission('vehiclepass:fake-plate-control:ignore')")
    public CommonResult<Boolean> ignore(@Valid @RequestBody FakePlateControlIgnoreReqVO reqVO) {
        plateControlService.ignore(reqVO);
        return success(true);
    }

    @PutMapping("/update-progress")
    @Operation(summary = "更新处置进度")
    @PreAuthorize("@ss.hasPermission('vehiclepass:fake-plate-control:update-progress')")
    public CommonResult<Boolean> updateProgress(@Valid @RequestBody FakePlateControlUpdateProgressReqVO reqVO) {
        plateControlService.updateProgress(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "套牌管控统计")
    @PreAuthorize("@ss.hasPermission('vehiclepass:fake-plate-control:chart')")
    public CommonResult<FakePlateControlChartRespVO> getChart(@Valid FakePlateControlChartReqVO reqVO) {
        return success(plateControlService.getChart(reqVO));
    }

    @GetMapping("/export")
    @Operation(summary = "导出套牌管控 Excel")
    @PreAuthorize("@ss.hasPermission('fake:plate-control:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPlateControlExcel(@Valid FakePlateControlPageReqVO pageReqVO,
                                        HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<MyFakePlateControlRespVO> pageResult = plateControlService.getFakePlateControlPage(pageReqVO);
        ExcelUtils.write(response, "套牌管控.xls", "数据", MyFakePlateControlRespVO.class, pageResult.getList());
    }

}