package cn.iocoder.yudao.module.data.controller.admin.eventcategory;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.data.controller.admin.eventcategory.vo.EventCategoryPageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.eventcategory.vo.EventCategoryRespVO;
import cn.iocoder.yudao.module.data.controller.admin.eventcategory.vo.EventCategorySaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.eventcategory.vo.EventCategorySimpleTreeRespVO;
import cn.iocoder.yudao.module.data.dal.dataobject.eventcategory.EventCategoryDO;
import cn.iocoder.yudao.module.data.service.eventcategory.EventCategoryService;
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

@Tag(name = "管理后台 - 监测事件分类")
@RestController
@RequestMapping("/data/event-category")
@Validated
public class EventCategoryController {

    @Resource
    private EventCategoryService eventCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建监测事件分类")
    @PreAuthorize("@ss.hasPermission('data:event-category:create')")
    public CommonResult<Long> createEventCategory(@Valid @RequestBody EventCategorySaveReqVO createReqVO) {
        return success(eventCategoryService.createEventCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测事件分类")
    @PreAuthorize("@ss.hasPermission('data:event-category:update')")
    public CommonResult<Boolean> updateEventCategory(@Valid @RequestBody EventCategorySaveReqVO updateReqVO) {
        eventCategoryService.updateEventCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测事件分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('data:event-category:delete')")
    public CommonResult<Boolean> deleteEventCategory(@RequestParam("id") Long id) {
        eventCategoryService.deleteEventCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测事件分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('data:event-category:query')")
    public CommonResult<EventCategoryRespVO> getEventCategory(@RequestParam("id") Long id) {
        EventCategoryDO eventCategory = eventCategoryService.getEventCategory(id);
        return success(BeanUtils.toBean(eventCategory, EventCategoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测事件分类分页")
    @PreAuthorize("@ss.hasPermission('data:event-category:query')")
    public CommonResult<PageResult<EventCategoryRespVO>> getEventCategoryPage(@Valid EventCategoryPageReqVO pageReqVO) {
        PageResult<EventCategoryDO> pageResult = eventCategoryService.getEventCategoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EventCategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测事件分类 Excel")
    @PreAuthorize("@ss.hasPermission('data:event-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEventCategoryExcel(@Valid EventCategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EventCategoryDO> list = eventCategoryService.getEventCategoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测事件分类.xls", "数据", EventCategoryRespVO.class,
                        BeanUtils.toBean(list, EventCategoryRespVO.class));
    }

    @DeleteMapping("/batch-delete")
    @Operation(summary = "批量删除监测事件分类")
    @PreAuthorize("@ss.hasPermission('data:event-category:delete')")
    public CommonResult<Boolean> deleteEventCategories(@RequestBody List<Long> ids) {
        eventCategoryService.deleteEventCategories(ids);
        return success(true);
    }

    @GetMapping("/tree")
    @Operation(summary = "获得监测事件分类树")
    @PreAuthorize("@ss.hasPermission('data:event-category:query')")
    public CommonResult<List<EventCategorySimpleTreeRespVO>> getEventCategoryTree() {
        List<EventCategorySimpleTreeRespVO> tree = eventCategoryService.getEventCategorySimpleTree();
        return success(tree);
    }

}