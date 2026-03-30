package cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.dockingrecord;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.dockingrecord.vo.DockingRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.dockingrecord.vo.DockingRecordRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.dockingrecord.vo.DockingRecordSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.dockingrecord.DockingRecordDO;
import cn.iocoder.yudao.module.evaluate.service.dockingrecord.DockingRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 系统对接记录")
@RestController
@RequestMapping("/evaluate/docking-record")
@Validated
public class DockingRecordController {

    @Resource
    private DockingRecordService dockingRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建系统对接记录")
    @PreAuthorize("@ss.hasPermission('evaluate:docking-record:create')")
    public CommonResult<Long> createDockingRecord(@Valid @RequestBody DockingRecordSaveReqVO createReqVO) {
        return success(dockingRecordService.createDockingRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新系统对接记录")
    @PreAuthorize("@ss.hasPermission('evaluate:docking-record:update')")
    public CommonResult<Boolean> updateDockingRecord(@Valid @RequestBody DockingRecordSaveReqVO updateReqVO) {
        dockingRecordService.updateDockingRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除系统对接记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:docking-record:delete')")
    public CommonResult<Boolean> deleteDockingRecord(@RequestParam("id") Long id) {
        dockingRecordService.deleteDockingRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得系统对接记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:docking-record:query')")
    public CommonResult<DockingRecordRespVO> getDockingRecord(@RequestParam("id") Long id) {
        DockingRecordDO dockingRecord = dockingRecordService.getDockingRecord(id);
        return success(BeanUtils.toBean(dockingRecord, DockingRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得系统对接记录分页")
    @PreAuthorize("@ss.hasPermission('evaluate:docking-record:query')")
    public CommonResult<PageResult<DockingRecordRespVO>> getDockingRecordPage(@Valid DockingRecordPageReqVO pageReqVO) {
        PageResult<DockingRecordDO> pageResult = dockingRecordService.getDockingRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DockingRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出系统对接记录 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:docking-record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDockingRecordExcel(@Valid DockingRecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DockingRecordDO> list = dockingRecordService.getDockingRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "系统对接记录.xls", "数据", DockingRecordRespVO.class,
                        BeanUtils.toBean(list, DockingRecordRespVO.class));
    }

}