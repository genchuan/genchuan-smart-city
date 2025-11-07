package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtarch.vo.EvtArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtarch.vo.EvtArchRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtarch.vo.EvtArchSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtarch.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtarch.EvtArchDO;
import cn.iocoder.yudao.module.datacenter.service.evtmanager.evtarch.EvtArchService;

@Tag(name = "管理后台 - 事件办结归档")
@RestController
@RequestMapping("/datacenter/evt-arch")
@Validated
public class EvtArchController {

    @Resource
    private EvtArchService evtArchService;

    @PostMapping("/create")
    @Operation(summary = "创建事件办结归档")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-arch:create')")
    public CommonResult<Long> createEvtArch(@Valid @RequestBody EvtArchSaveReqVO createReqVO) {
        return success(evtArchService.createEvtArch(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新事件办结归档")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-arch:update')")
    public CommonResult<Boolean> updateEvtArch(@Valid @RequestBody EvtArchSaveReqVO updateReqVO) {
        evtArchService.updateEvtArch(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除事件办结归档")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:evt-arch:delete')")
    public CommonResult<Boolean> deleteEvtArch(@RequestParam("id") Long id) {
        evtArchService.deleteEvtArch(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得事件办结归档")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-arch:query')")
    public CommonResult<EvtArchRespVO> getEvtArch(@RequestParam("id") Long id) {
        EvtArchDO evtArch = evtArchService.getEvtArch(id);
        return success(BeanUtils.toBean(evtArch, EvtArchRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得事件办结归档分页")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-arch:query')")
    public CommonResult<PageResult<EvtArchRespVO>> getEvtArchPage(@Valid EvtArchPageReqVO pageReqVO) {
        PageResult<EvtArchDO> pageResult = evtArchService.getEvtArchPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvtArchRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出事件办结归档 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-arch:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvtArchExcel(@Valid EvtArchPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvtArchDO> list = evtArchService.getEvtArchPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "事件办结归档.xls", "数据", EvtArchRespVO.class,
                        BeanUtils.toBean(list, EvtArchRespVO.class));
    }

}