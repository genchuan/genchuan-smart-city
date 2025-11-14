package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonidx;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonidx.vo.BasicMonIdxPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonidx.vo.BasicMonIdxRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonidx.vo.BasicMonIdxSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.data.basicmonidx.BasicMonIdxDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.data.basicmonidx.BasicMonIdxService;

@Tag(name = "管理后台 - 基础监测指标")
@RestController
@RequestMapping("/datacenter/basic-mon-idx")
@Validated
public class BasicMonIdxController {

    @Resource
    private BasicMonIdxService basicMonIdxService;

    @PostMapping("/create")
    @Operation(summary = "创建基础监测指标")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-idx:create')")
    public CommonResult<Long> createBasicMonIdx(@Valid @RequestBody BasicMonIdxSaveReqVO createReqVO) {
        return success(basicMonIdxService.createBasicMonIdx(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新基础监测指标")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-idx:update')")
    public CommonResult<Boolean> updateBasicMonIdx(@Valid @RequestBody BasicMonIdxSaveReqVO updateReqVO) {
        basicMonIdxService.updateBasicMonIdx(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除基础监测指标")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-idx:delete')")
    public CommonResult<Boolean> deleteBasicMonIdx(@RequestParam("id") Long id) {
        basicMonIdxService.deleteBasicMonIdx(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得基础监测指标")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-idx:query')")
    public CommonResult<BasicMonIdxRespVO> getBasicMonIdx(@RequestParam("id") Long id) {
        BasicMonIdxDO basicMonIdx = basicMonIdxService.getBasicMonIdx(id);
        return success(BeanUtils.toBean(basicMonIdx, BasicMonIdxRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得基础监测指标分页")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-idx:query')")
    public CommonResult<PageResult<BasicMonIdxRespVO>> getBasicMonIdxPage(@Valid BasicMonIdxPageReqVO pageReqVO) {
        PageResult<BasicMonIdxDO> pageResult = basicMonIdxService.getBasicMonIdxPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BasicMonIdxRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出基础监测指标 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-idx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBasicMonIdxExcel(@Valid BasicMonIdxPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BasicMonIdxDO> list = basicMonIdxService.getBasicMonIdxPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "基础监测指标.xls", "数据", BasicMonIdxRespVO.class,
                        BeanUtils.toBean(list, BasicMonIdxRespVO.class));
    }

}