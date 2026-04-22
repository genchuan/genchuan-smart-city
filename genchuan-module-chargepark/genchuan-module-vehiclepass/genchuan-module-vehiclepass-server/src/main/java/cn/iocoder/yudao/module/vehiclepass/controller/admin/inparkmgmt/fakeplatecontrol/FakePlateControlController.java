package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlBatchHandleReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlPageReqVO;
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
@RequestMapping("/fake/plate-control")
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
    @PreAuthorize("@ss.hasPermission('fake:plate-control:query')")
    public CommonResult<PageResult<FakePlateControlRespVO>> getPlateControlPage(@Valid FakePlateControlPageReqVO pageReqVO) {
        PageResult<FakePlateControlDO> pageResult = plateControlService.getPlateControlPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, FakePlateControlRespVO.class));
    }

    @GetMapping("/my/page")
    @PreAuthorize("@ss.hasPermission('vehiclepass:fake-plate-control:query')")
    public CommonResult<PageResult<MyFakePlateControlRespVO>> page(FakePlateControlPageReqVO reqVO) {
        return CommonResult.success(plateControlService.getFakePlateControlPage(reqVO));
    }

    @PostMapping("/batch-handle")
    @Operation(summary = "批量处置套牌管控")
    @PreAuthorize("@ss.hasPermission('vehiclepass:fake-plate-control:batch-handle')")
    public CommonResult<Boolean> batchHandle(@Valid @RequestBody FakePlateControlBatchHandleReqVO reqVO) {
        plateControlService.batchHandle(reqVO);
        return success(true);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出套牌管控 Excel")
    @PreAuthorize("@ss.hasPermission('fake:plate-control:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPlateControlExcel(@Valid FakePlateControlPageReqVO pageReqVO,
                                        HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FakePlateControlDO> list = plateControlService.getPlateControlPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "套牌管控.xls", "数据", FakePlateControlRespVO.class,
                BeanUtils.toBean(list, FakePlateControlRespVO.class));
    }

}