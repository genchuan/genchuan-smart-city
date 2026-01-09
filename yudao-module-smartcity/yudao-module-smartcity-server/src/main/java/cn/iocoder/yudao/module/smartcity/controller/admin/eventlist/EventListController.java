package cn.iocoder.yudao.module.smartcity.controller.admin.eventlist;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventlist.vo.EventListPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventlist.vo.EventListRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventlist.vo.EventListSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventlist.EventListDO;
import cn.iocoder.yudao.module.smartcity.service.eventlist.EventListService;
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

@Tag(name = "管理后台 - 事件列表")
@RestController
@RequestMapping("/smartcity/event-list")
@Validated
public class EventListController {

    @Resource
    private EventListService eventListService;

    @PostMapping("/create")
    @Operation(summary = "创建事件列表")
    @PreAuthorize("@ss.hasPermission('smartcity:event-list:create')")
    public CommonResult<Long> createEventList(@Valid @RequestBody EventListSaveReqVO createReqVO) {
        return success(eventListService.createEventList(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新事件列表")
    @PreAuthorize("@ss.hasPermission('smartcity:event-list:update')")
    public CommonResult<Boolean> updateEventList(@Valid @RequestBody EventListSaveReqVO updateReqVO) {
        eventListService.updateEventList(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除事件列表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:event-list:delete')")
    public CommonResult<Boolean> deleteEventList(@RequestParam("id") Long id) {
        eventListService.deleteEventList(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得事件列表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:event-list:query')")
    public CommonResult<EventListRespVO> getEventList(@RequestParam("id") Long id) {
        EventListDO eventList = eventListService.getEventList(id);
        return success(BeanUtils.toBean(eventList, EventListRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得事件列表分页")
    @PreAuthorize("@ss.hasPermission('smartcity:event-list:query')")
    public CommonResult<PageResult<EventListRespVO>> getEventListPage(@Valid EventListPageReqVO pageReqVO) {
        PageResult<EventListDO> pageResult = eventListService.getEventListPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EventListRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出事件列表 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:event-list:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEventListExcel(@Valid EventListPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EventListDO> list = eventListService.getEventListPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "事件列表.xls", "数据", EventListRespVO.class,
                        BeanUtils.toBean(list, EventListRespVO.class));
    }

}