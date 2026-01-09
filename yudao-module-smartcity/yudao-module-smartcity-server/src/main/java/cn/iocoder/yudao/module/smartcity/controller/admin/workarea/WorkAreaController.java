package cn.iocoder.yudao.module.smartcity.controller.admin.workarea;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.workarea.vo.WorkAreaPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.workarea.vo.WorkAreaRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.workarea.vo.WorkAreaSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.workarea.WorkAreaDO;
import cn.iocoder.yudao.module.smartcity.service.workarea.WorkAreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 作业区域")
@RestController
@RequestMapping("/smartcity/work-area")
@Validated
public class WorkAreaController {

    @Resource
    private WorkAreaService workAreaService;

    @PostMapping("/create")
    @Operation(summary = "创建作业区域")
    @PreAuthorize("@ss.hasPermission('smartcity:work-area:create')")
    public CommonResult<Long> createWorkArea(@Valid @RequestBody WorkAreaSaveReqVO createReqVO) {
        return success(workAreaService.createWorkArea(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新作业区域")
    @PreAuthorize("@ss.hasPermission('smartcity:work-area:update')")
    public CommonResult<Boolean> updateWorkArea(@Valid @RequestBody WorkAreaSaveReqVO updateReqVO) {
        workAreaService.updateWorkArea(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除作业区域")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:work-area:delete')")
    public CommonResult<Boolean> deleteWorkArea(@RequestParam("id") Long id) {
        workAreaService.deleteWorkArea(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得作业区域")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:work-area:query')")
    public CommonResult<WorkAreaRespVO> getWorkArea(@RequestParam("id") Long id) {
        WorkAreaDO workArea = workAreaService.getWorkArea(id);
        return success(BeanUtils.toBean(workArea, WorkAreaRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得作业区域分页")
    @PreAuthorize("@ss.hasPermission('smartcity:work-area:query')")
    public CommonResult<PageResult<WorkAreaRespVO>> getWorkAreaPage(@Valid WorkAreaPageReqVO pageReqVO) {
        PageResult<WorkAreaDO> pageResult = workAreaService.getWorkAreaPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WorkAreaRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出作业区域 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:work-area:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWorkAreaExcel(@Valid WorkAreaPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WorkAreaDO> list = workAreaService.getWorkAreaPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "作业区域.xls", "数据", WorkAreaRespVO.class,
                        BeanUtils.toBean(list, WorkAreaRespVO.class));
    }

}