package cn.iocoder.yudao.module.smartcity.controller.admin.eventprocessing;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventprocessing.vo.EventProcessingPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventprocessing.vo.EventProcessingRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventprocessing.vo.EventProcessingSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventprocessing.EventProcessingDO;
import cn.iocoder.yudao.module.smartcity.service.eventprocessing.EventProcessingService;
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

@Tag(name = "管理后台 - 事件处理")
@RestController
@RequestMapping("/smartcity/event-processing")
@Validated
public class EventProcessingController {

    @Resource
    private EventProcessingService eventProcessingService;

    @PostMapping("/create")
    @Operation(summary = "创建事件处理")
    @PreAuthorize("@ss.hasPermission('smartcity:event-processing:create')")
    public CommonResult<Long> createEventProcessing(@Valid @RequestBody EventProcessingSaveReqVO createReqVO) {
        return success(eventProcessingService.createEventProcessing(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新事件处理")
    @PreAuthorize("@ss.hasPermission('smartcity:event-processing:update')")
    public CommonResult<Boolean> updateEventProcessing(@Valid @RequestBody EventProcessingSaveReqVO updateReqVO) {
        eventProcessingService.updateEventProcessing(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除事件处理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:event-processing:delete')")
    public CommonResult<Boolean> deleteEventProcessing(@RequestParam("id") Long id) {
        eventProcessingService.deleteEventProcessing(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得事件处理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:event-processing:query')")
    public CommonResult<EventProcessingRespVO> getEventProcessing(@RequestParam("id") Long id) {
        EventProcessingDO eventProcessing = eventProcessingService.getEventProcessing(id);
        return success(BeanUtils.toBean(eventProcessing, EventProcessingRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得事件处理分页")
    @PreAuthorize("@ss.hasPermission('smartcity:event-processing:query')")
    public CommonResult<PageResult<EventProcessingRespVO>> getEventProcessingPage(@Valid EventProcessingPageReqVO pageReqVO) {
        PageResult<EventProcessingDO> pageResult = eventProcessingService.getEventProcessingPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EventProcessingRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出事件处理 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:event-processing:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEventProcessingExcel(@Valid EventProcessingPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EventProcessingDO> list = eventProcessingService.getEventProcessingPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "事件处理.xls", "数据", EventProcessingRespVO.class,
                        BeanUtils.toBean(list, EventProcessingRespVO.class));
    }

}