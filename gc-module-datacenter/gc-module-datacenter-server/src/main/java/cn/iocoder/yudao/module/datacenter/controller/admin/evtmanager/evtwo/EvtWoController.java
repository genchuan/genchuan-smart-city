package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtwo;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtwo.vo.EvtWoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtwo.vo.EvtWoRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtwo.vo.EvtWoSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtwo.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtwo.EvtWoDO;
import cn.iocoder.yudao.module.datacenter.service.evtmanager.evtwo.EvtWoService;

@Tag(name = "管理后台 - 事件工单")
@RestController
@RequestMapping("/datacenter/evt-wo")
@Validated
public class EvtWoController {

    @Resource
    private EvtWoService evtWoService;

    @PostMapping("/create")
    @Operation(summary = "创建事件工单")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-wo:create')")
    public CommonResult<Long> createEvtWo(@Valid @RequestBody EvtWoSaveReqVO createReqVO) {
        return success(evtWoService.createEvtWo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新事件工单")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-wo:update')")
    public CommonResult<Boolean> updateEvtWo(@Valid @RequestBody EvtWoSaveReqVO updateReqVO) {
        evtWoService.updateEvtWo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除事件工单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:evt-wo:delete')")
    public CommonResult<Boolean> deleteEvtWo(@RequestParam("id") Long id) {
        evtWoService.deleteEvtWo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得事件工单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-wo:query')")
    public CommonResult<EvtWoRespVO> getEvtWo(@RequestParam("id") Long id) {
        EvtWoDO evtWo = evtWoService.getEvtWo(id);
        return success(BeanUtils.toBean(evtWo, EvtWoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得事件工单分页")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-wo:query')")
    public CommonResult<PageResult<EvtWoRespVO>> getEvtWoPage(@Valid EvtWoPageReqVO pageReqVO) {
        PageResult<EvtWoDO> pageResult = evtWoService.getEvtWoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvtWoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出事件工单 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-wo:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvtWoExcel(@Valid EvtWoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvtWoDO> list = evtWoService.getEvtWoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "事件工单.xls", "数据", EvtWoRespVO.class,
                        BeanUtils.toBean(list, EvtWoRespVO.class));
    }

}