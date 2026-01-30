package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.eventtype;

import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.eventtype.vo.EventTypePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.eventtype.vo.EventTypeRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.eventtype.vo.EventTypeSaveReqVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.eventtype.EventTypeDO;
import cn.iocoder.yudao.module.park.service.park.basicAssociation.eventtype.EventTypeService;

@Tag(name = "管理后台 - 监测事件类别")
@RestController
@RequestMapping("/park/event-type")
@Validated
public class EventTypeController {

    @Resource
    private EventTypeService eventTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建监测事件类别")
    @PreAuthorize("@ss.hasPermission('park:event-type:create')")
    public CommonResult<Long> createEventType(@Valid @RequestBody EventTypeSaveReqVO createReqVO) {
        return success(eventTypeService.createEventType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测事件类别")
    @PreAuthorize("@ss.hasPermission('park:event-type:update')")
    public CommonResult<Boolean> updateEventType(@Valid @RequestBody EventTypeSaveReqVO updateReqVO) {
        eventTypeService.updateEventType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测事件类别")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:event-type:delete')")
    public CommonResult<Boolean> deleteEventType(@RequestParam("id") Long id) {
        eventTypeService.deleteEventType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测事件类别")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:event-type:query')")
    public CommonResult<EventTypeRespVO> getEventType(@RequestParam("id") Long id) {
        EventTypeDO eventType = eventTypeService.getEventType(id);
        return success(BeanUtils.toBean(eventType, EventTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测事件类别分页")
    @PreAuthorize("@ss.hasPermission('park:event-type:query')")
    public CommonResult<PageResult<EventTypeRespVO>> getEventTypePage(@Valid EventTypePageReqVO pageReqVO) {
        PageResult<EventTypeDO> pageResult = eventTypeService.getEventTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EventTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测事件类别 Excel")
    @PreAuthorize("@ss.hasPermission('park:event-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEventTypeExcel(@Valid EventTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EventTypeDO> list = eventTypeService.getEventTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测事件类别.xls", "数据", EventTypeRespVO.class,
                        BeanUtils.toBean(list, EventTypeRespVO.class));
    }

}