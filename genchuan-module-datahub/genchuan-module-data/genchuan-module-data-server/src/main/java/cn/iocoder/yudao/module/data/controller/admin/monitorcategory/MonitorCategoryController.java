package cn.iocoder.yudao.module.data.controller.admin.monitorcategory;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.data.controller.admin.monitorcategory.vo.MonitorCategoryPageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.monitorcategory.vo.MonitorCategoryRespVO;
import cn.iocoder.yudao.module.data.controller.admin.monitorcategory.vo.MonitorCategorySaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.monitorcategory.vo.MonitorCategorySimpleTreeRespVO;
import cn.iocoder.yudao.module.data.dal.dataobject.monitorcategory.MonitorCategoryDO;
import cn.iocoder.yudao.module.data.service.monitorcategory.MonitorCategoryService;
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

@Tag(name = "管理后台 - 监测部件分类")
@RestController
@RequestMapping("/data/monitor-category")
@Validated
public class MonitorCategoryController {

    @Resource
    private MonitorCategoryService monitorCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建监测部件分类")
    @PreAuthorize("@ss.hasPermission('data:monitor-category:create')")
    public CommonResult<Long> createMonitorCategory(@Valid @RequestBody MonitorCategorySaveReqVO createReqVO) {
        return success(monitorCategoryService.createMonitorCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测部件分类")
    @PreAuthorize("@ss.hasPermission('data:monitor-category:update')")
    public CommonResult<Boolean> updateMonitorCategory(@Valid @RequestBody MonitorCategorySaveReqVO updateReqVO) {
        monitorCategoryService.updateMonitorCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测部件分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('data:monitor-category:delete')")
    public CommonResult<Boolean> deleteMonitorCategory(@RequestParam("id") Long id) {
        monitorCategoryService.deleteMonitorCategory(id);
        return success(true);
    }

    @DeleteMapping("/batch-delete")
    @Operation(summary = "批量删除监测部件分类")
    @PreAuthorize("@ss.hasPermission('data:monitor-category:delete')")
    public CommonResult<Boolean> deleteMonitorCategories(@RequestParam("ids") List<Long> ids) {
        monitorCategoryService.deleteMonitorCategories(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测部件分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('data:monitor-category:query')")
    public CommonResult<MonitorCategoryRespVO> getMonitorCategory(@RequestParam("id") Long id) {
        MonitorCategoryDO monitorCategory = monitorCategoryService.getMonitorCategory(id);
        return success(BeanUtils.toBean(monitorCategory, MonitorCategoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测部件分类分页")
    @PreAuthorize("@ss.hasPermission('data:monitor-category:query')")
    public CommonResult<PageResult<MonitorCategoryRespVO>> getMonitorCategoryPage(@Valid MonitorCategoryPageReqVO pageReqVO) {
        PageResult<MonitorCategoryDO> pageResult = monitorCategoryService.getMonitorCategoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonitorCategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测部件分类 Excel")
    @PreAuthorize("@ss.hasPermission('data:monitor-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonitorCategoryExcel(@Valid MonitorCategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonitorCategoryDO> list = monitorCategoryService.getMonitorCategoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测部件分类.xls", "数据", MonitorCategoryRespVO.class,
                        BeanUtils.toBean(list, MonitorCategoryRespVO.class));
    }

    @GetMapping("/tree-simple")
    @Operation(summary = "获得监测部件分类简化树")
    @PreAuthorize("@ss.hasPermission('data:monitor-category:query')")
    public CommonResult<List<MonitorCategorySimpleTreeRespVO>> getMonitorCategorySimpleTree() {
        List<MonitorCategorySimpleTreeRespVO> tree = monitorCategoryService.getMonitorCategorySimpleTree();
        return success(tree);
    }

}