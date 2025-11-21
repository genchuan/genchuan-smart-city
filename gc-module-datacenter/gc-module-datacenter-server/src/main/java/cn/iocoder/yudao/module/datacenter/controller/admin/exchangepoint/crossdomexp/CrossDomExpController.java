package cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.crossdomexp;

import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.crossdomexp.vo.CrossDomExpPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.crossdomexp.vo.CrossDomExpRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.crossdomexp.vo.CrossDomExpSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.crossdomexp.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.exchangepoint.crossdomexp.CrossDomExpDO;
import cn.iocoder.yudao.module.datacenter.service.exchangepoint.crossdomexp.CrossDomExpService;

@Tag(name = "管理后台 - 跨域经验交流")
@RestController
@RequestMapping("/datacenter/cross-dom-exp")
@Validated
public class CrossDomExpController {

    @Resource
    private CrossDomExpService crossDomExpService;

    @PostMapping("/create")
    @Operation(summary = "创建跨域经验交流")
    @PreAuthorize("@ss.hasPermission('datacenter:cross-dom-exp:create')")
    public CommonResult<Long> createCrossDomExp(@Valid @RequestBody CrossDomExpSaveReqVO createReqVO) {
        return success(crossDomExpService.createCrossDomExp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新跨域经验交流")
    @PreAuthorize("@ss.hasPermission('datacenter:cross-dom-exp:update')")
    public CommonResult<Boolean> updateCrossDomExp(@Valid @RequestBody CrossDomExpSaveReqVO updateReqVO) {
        crossDomExpService.updateCrossDomExp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除跨域经验交流")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:cross-dom-exp:delete')")
    public CommonResult<Boolean> deleteCrossDomExp(@RequestParam("id") Long id) {
        crossDomExpService.deleteCrossDomExp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得跨域经验交流")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:cross-dom-exp:query')")
    public CommonResult<CrossDomExpRespVO> getCrossDomExp(@RequestParam("id") Long id) {
        CrossDomExpDO crossDomExp = crossDomExpService.getCrossDomExp(id);
        return success(BeanUtils.toBean(crossDomExp, CrossDomExpRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得跨域经验交流分页")
    @PreAuthorize("@ss.hasPermission('datacenter:cross-dom-exp:query')")
    public CommonResult<PageResult<CrossDomExpRespVO>> getCrossDomExpPage(@Valid CrossDomExpPageReqVO pageReqVO) {
        PageResult<CrossDomExpDO> pageResult = crossDomExpService.getCrossDomExpPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CrossDomExpRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出跨域经验交流 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:cross-dom-exp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCrossDomExpExcel(@Valid CrossDomExpPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CrossDomExpDO> list = crossDomExpService.getCrossDomExpPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "跨域经验交流.xls", "数据", CrossDomExpRespVO.class,
                        BeanUtils.toBean(list, CrossDomExpRespVO.class));
    }

}