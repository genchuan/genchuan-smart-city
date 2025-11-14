package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonpt.vo.BasicMonPtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonpt.vo.BasicMonPtRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonpt.vo.BasicMonPtSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.data.basicmonpt.BasicMonPtDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.data.basicmonpt.BasicMonPtService;

@Tag(name = "管理后台 - 基础监测点位")
@RestController
@RequestMapping("/datacenter/basic-mon-pt")
@Validated
public class BasicMonPtController {

    @Resource
    private BasicMonPtService basicMonPtService;

    @PostMapping("/create")
    @Operation(summary = "创建基础监测点位")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-pt:create')")
    public CommonResult<Long> createBasicMonPt(@Valid @RequestBody BasicMonPtSaveReqVO createReqVO) {
        return success(basicMonPtService.createBasicMonPt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新基础监测点位")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-pt:update')")
    public CommonResult<Boolean> updateBasicMonPt(@Valid @RequestBody BasicMonPtSaveReqVO updateReqVO) {
        basicMonPtService.updateBasicMonPt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除基础监测点位")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-pt:delete')")
    public CommonResult<Boolean> deleteBasicMonPt(@RequestParam("id") Long id) {
        basicMonPtService.deleteBasicMonPt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得基础监测点位")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-pt:query')")
    public CommonResult<BasicMonPtRespVO> getBasicMonPt(@RequestParam("id") Long id) {
        BasicMonPtDO basicMonPt = basicMonPtService.getBasicMonPt(id);
        return success(BeanUtils.toBean(basicMonPt, BasicMonPtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得基础监测点位分页")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-pt:query')")
    public CommonResult<PageResult<BasicMonPtRespVO>> getBasicMonPtPage(@Valid BasicMonPtPageReqVO pageReqVO) {
        PageResult<BasicMonPtDO> pageResult = basicMonPtService.getBasicMonPtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BasicMonPtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出基础监测点位 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-pt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBasicMonPtExcel(@Valid BasicMonPtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BasicMonPtDO> list = basicMonPtService.getBasicMonPtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "基础监测点位.xls", "数据", BasicMonPtRespVO.class,
                        BeanUtils.toBean(list, BasicMonPtRespVO.class));
    }

}