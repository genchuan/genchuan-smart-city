package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.eventmanagement.eventtypemanagement;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.eventmanagement.eventtypemanagement.vo.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.eventmanagement.eventtypemanagement.vo.*;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.eventtypemanagement.EventTypeManagementDO;
import cn.iocoder.yudao.module.datacenter.service.evtmanager.eventmanagement.eventtypemanagement.EventTypeManagementService;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 事件类型管理")
@RestController
@RequestMapping("/datacenter/event-type-management")
public class EventTypeManagementController {

    @Resource
    private EventTypeManagementService eventTypeManagementService;

    @PostMapping("/create")
    @Operation(summary = "创建事件类型")
    public CommonResult<Long> createEventType(@Valid @RequestBody EventTypeManagementCreateReqVO createReqVO) {
        return success(eventTypeManagementService.createEventType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新事件类型")
    public CommonResult<Boolean> updateEventType(@Valid @RequestBody EventTypeManagementUpdateReqVO updateReqVO) {
        eventTypeManagementService.updateEventType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除事件类型")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteEventType(@RequestParam("id") Long id) {
        eventTypeManagementService.deleteEventType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得事件类型")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<EventTypeManagementRespVO> getEventType(@RequestParam("id") Long id) {
        EventTypeManagementDO eventType = eventTypeManagementService.getEventType(id);
        return success(convert(eventType));
    }

    @GetMapping("/page")
    @Operation(summary = "获得事件类型分页")
    public CommonResult<PageResult<EventTypeManagementRespVO>> getEventTypePage(@Valid EventTypeManagementPageReqVO pageReqVO) {
        PageResult<EventTypeManagementDO> pageResult = eventTypeManagementService.getEventTypePage(pageReqVO);
        return success(new PageResult<>(convertList(pageResult.getList()), pageResult.getTotal()));
    }

    @GetMapping("/list-all")
    @Operation(summary = "获得所有事件类型列表")
    public CommonResult<List<EventTypeManagementRespVO>> getEventTypeList() {
        List<EventTypeManagementDO> list = eventTypeManagementService.getEventTypeList();
        return success(convertList(list));
    }

    @GetMapping("/list-big-categories")
    @Operation(summary = "获得所有启用的事件大类列表")
    public CommonResult<List<EventTypeManagementRespVO>> getEnabledBigCategories() {
        List<EventTypeManagementDO> list = eventTypeManagementService.getEnabledBigCategories();
        return success(convertList(list));
    }

    @GetMapping("/list-mid-categories")
    @Operation(summary = "根据大类代码获得启用的中类列表")
    @Parameter(name = "eventBigCode", description = "大类代码", required = true)
    public CommonResult<List<EventTypeManagementRespVO>> getEnabledMidCategories(@RequestParam("eventBigCode") String eventBigCode) {
        List<EventTypeManagementDO> list = eventTypeManagementService.getEnabledMidCategoriesByBigCode(eventBigCode);
        return success(convertList(list));
    }

    @GetMapping("/list-small-categories")
    @Operation(summary = "根据中类代码获得启用的小类列表")
    @Parameter(name = "eventMidCode", description = "中类代码", required = true)
    public CommonResult<List<EventTypeManagementRespVO>> getEnabledSmallCategories(@RequestParam("eventMidCode") String eventMidCode) {
        List<EventTypeManagementDO> list = eventTypeManagementService.getEnabledSmallCategoriesByMidCode(eventMidCode);
        return success(convertList(list));
    }

    @PutMapping("/update-enable-status")
    @Operation(summary = "更新启用状态")
    public CommonResult<Boolean> updateEnableStatus(@Valid @RequestBody EventTypeManagementUpdateStatusReqVO updateStatusReqVO) {
        eventTypeManagementService.updateEnableStatus(updateStatusReqVO.getId(), updateStatusReqVO.getEnableStatus());
        return success(true);
    }

    private EventTypeManagementRespVO convert(EventTypeManagementDO eventType) {
        return eventType != null ? EventTypeManagementRespVO.builder()
                .id(eventType.getId())
                .eventTypeId(eventType.getEventTypeId())
                .eventBigCode(eventType.getEventBigCode())
                .eventBigName(eventType.getEventBigName())
                .eventMidCode(eventType.getEventMidCode())
                .eventMidName(eventType.getEventMidName())
                .eventSmallCode(eventType.getEventSmallCode())
                .eventSmallName(eventType.getEventSmallName())
                .eventTypeDesc(eventType.getEventTypeDesc())
                .enableStatus(eventType.getEnableStatus())
                .createTime(eventType.getCreateTime())
                .createUser(eventType.getCreateUser())
                .build() : null;
    }

    private List<EventTypeManagementRespVO> convertList(List<EventTypeManagementDO> list) {
        return list.stream().map(this::convert).collect(java.util.stream.Collectors.toList());
    }
}