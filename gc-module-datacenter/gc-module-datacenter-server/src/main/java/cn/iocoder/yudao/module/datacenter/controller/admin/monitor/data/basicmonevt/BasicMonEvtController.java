package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonevt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonevt.vo.BasicMonEvtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonevt.vo.BasicMonEvtRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonevt.vo.BasicMonEvtSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.data.basicmonevt.BasicMonEvtDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.data.basicmonevt.BasicMonEvtService;

@Tag(name = "管理后台 - 基础监测事件")
@RestController
@RequestMapping("/datacenter/basic-mon-evt")
@Validated
public class BasicMonEvtController {

    @Resource
    private BasicMonEvtService basicMonEvtService;

    @PostMapping("/create")
    @Operation(summary = "创建基础监测事件")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-evt:create')")
    public CommonResult<Long> createBasicMonEvt(@Valid @RequestBody BasicMonEvtSaveReqVO createReqVO) {
        return success(basicMonEvtService.createBasicMonEvt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新基础监测事件")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-evt:update')")
    public CommonResult<Boolean> updateBasicMonEvt(@Valid @RequestBody BasicMonEvtSaveReqVO updateReqVO) {
        basicMonEvtService.updateBasicMonEvt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除基础监测事件")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-evt:delete')")
    public CommonResult<Boolean> deleteBasicMonEvt(@RequestParam("id") Long id) {
        basicMonEvtService.deleteBasicMonEvt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得基础监测事件")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-evt:query')")
    public CommonResult<BasicMonEvtRespVO> getBasicMonEvt(@RequestParam("id") Long id) {
        BasicMonEvtDO basicMonEvt = basicMonEvtService.getBasicMonEvt(id);
        return success(BeanUtils.toBean(basicMonEvt, BasicMonEvtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得基础监测事件分页")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-evt:query')")
    public CommonResult<PageResult<BasicMonEvtRespVO>> getBasicMonEvtPage(@Valid BasicMonEvtPageReqVO pageReqVO) {
        PageResult<BasicMonEvtDO> pageResult = basicMonEvtService.getBasicMonEvtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BasicMonEvtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出基础监测事件 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-evt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBasicMonEvtExcel(@Valid BasicMonEvtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BasicMonEvtDO> list = basicMonEvtService.getBasicMonEvtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "基础监测事件.xls", "数据", BasicMonEvtRespVO.class,
                        BeanUtils.toBean(list, BasicMonEvtRespVO.class));
    }

}