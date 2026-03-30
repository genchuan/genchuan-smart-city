package cn.iocoder.yudao.module.data.controller.admin.eventinstance;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.data.controller.admin.eventinstance.vo.EventInstancePageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.eventinstance.vo.EventInstanceRespVO;
import cn.iocoder.yudao.module.data.controller.admin.eventinstance.vo.EventInstanceSaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.eventinstance.vo.EventInstanceUpdateStatusReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.eventinstance.EventInstanceDO;
import cn.iocoder.yudao.module.data.service.eventinstance.EventInstanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.IMPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 监测事件实例")
@RestController
@RequestMapping("/data/event-instance")
@Validated
public class EventInstanceController {

    @Resource
    private EventInstanceService eventInstanceService;

    @PostMapping("/create")
    @Operation(summary = "创建监测事件实例")
    @PreAuthorize("@ss.hasPermission('data:event-instance:create')")
    public CommonResult<Long> createEventInstance(@Valid @RequestBody EventInstanceSaveReqVO createReqVO) {
        return success(eventInstanceService.createEventInstance(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测事件实例")
    @PreAuthorize("@ss.hasPermission('data:event-instance:update')")
    public CommonResult<Boolean> updateEventInstance(@Valid @RequestBody EventInstanceSaveReqVO updateReqVO) {
        eventInstanceService.updateEventInstance(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测事件实例")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('data:event-instance:delete')")
    public CommonResult<Boolean> deleteEventInstance(@RequestParam("id") Long id) {
        eventInstanceService.deleteEventInstance(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测事件实例")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('data:event-instance:query')")
    public CommonResult<EventInstanceRespVO> getEventInstance(@RequestParam("id") Long id) {
        EventInstanceDO eventInstance = eventInstanceService.getEventInstance(id);
        return success(BeanUtils.toBean(eventInstance, EventInstanceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测事件实例分页")
    @PreAuthorize("@ss.hasPermission('data:event-instance:query')")
    public CommonResult<PageResult<EventInstanceRespVO>> getEventInstancePage(@Valid EventInstancePageReqVO pageReqVO) {
        PageResult<EventInstanceDO> pageResult = eventInstanceService.getEventInstancePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EventInstanceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测事件实例 Excel")
    @PreAuthorize("@ss.hasPermission('data:event-instance:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEventInstanceExcel(@Valid EventInstancePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EventInstanceDO> list = eventInstanceService.getEventInstancePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测事件实例.xls", "数据", EventInstanceRespVO.class,
                        BeanUtils.toBean(list, EventInstanceRespVO.class));
    }

    @PostMapping("/import-excel")
    @Operation(summary = "导入监测事件实例 Excel")
    @PreAuthorize("@ss.hasPermission('data:event-instance:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<String> importEventInstanceExcel(@RequestParam("file") MultipartFile file) throws IOException {
        // 调用服务层进行导入
        String importResult = eventInstanceService.importEventInstanceExcel(file);
        return success(importResult);
    }

    @PutMapping("/batch-update-status")
    @Operation(summary = "批量更新监测事件实例状态")
    @PreAuthorize("@ss.hasPermission('data:event-instance:update')")
    public CommonResult<Integer> updateEventInstanceStatusBatch(@Valid @RequestBody EventInstanceUpdateStatusReqVO updateReqVO) {
        Integer updatedCount = eventInstanceService.updateEventInstanceStatusBatch(updateReqVO);
        return success(updatedCount);
    }

}