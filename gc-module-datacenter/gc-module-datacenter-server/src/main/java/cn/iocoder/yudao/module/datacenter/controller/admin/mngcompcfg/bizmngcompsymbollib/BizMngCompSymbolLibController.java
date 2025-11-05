package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompsymbollib;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompsymbollib.vo.BizMngCompSymbolLibPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompsymbollib.vo.BizMngCompSymbolLibRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompsymbollib.vo.BizMngCompSymbolLibSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompsymbollib.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcompsymbollib.BizMngCompSymbolLibDO;
import cn.iocoder.yudao.module.datacenter.service.mngcompcfg.bizmngcompsymbollib.BizMngCompSymbolLibService;

@Tag(name = "管理后台 - 管理部件图示符号库")
@RestController
@RequestMapping("/datacenter/biz-mng-comp-symbol-lib")
@Validated
public class BizMngCompSymbolLibController {

    @Resource
    private BizMngCompSymbolLibService bizMngCompSymbolLibService;

    @PostMapping("/create")
    @Operation(summary = "创建管理部件图示符号库")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-symbol-lib:create')")
    public CommonResult<Long> createBizMngCompSymbolLib(@Valid @RequestBody BizMngCompSymbolLibSaveReqVO createReqVO) {
        return success(bizMngCompSymbolLibService.createBizMngCompSymbolLib(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理部件图示符号库")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-symbol-lib:update')")
    public CommonResult<Boolean> updateBizMngCompSymbolLib(@Valid @RequestBody BizMngCompSymbolLibSaveReqVO updateReqVO) {
        bizMngCompSymbolLibService.updateBizMngCompSymbolLib(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理部件图示符号库")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-symbol-lib:delete')")
    public CommonResult<Boolean> deleteBizMngCompSymbolLib(@RequestParam("id") Long id) {
        bizMngCompSymbolLibService.deleteBizMngCompSymbolLib(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理部件图示符号库")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-symbol-lib:query')")
    public CommonResult<BizMngCompSymbolLibRespVO> getBizMngCompSymbolLib(@RequestParam("id") Long id) {
        BizMngCompSymbolLibDO bizMngCompSymbolLib = bizMngCompSymbolLibService.getBizMngCompSymbolLib(id);
        return success(BeanUtils.toBean(bizMngCompSymbolLib, BizMngCompSymbolLibRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理部件图示符号库分页")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-symbol-lib:query')")
    public CommonResult<PageResult<BizMngCompSymbolLibRespVO>> getBizMngCompSymbolLibPage(@Valid BizMngCompSymbolLibPageReqVO pageReqVO) {
        PageResult<BizMngCompSymbolLibDO> pageResult = bizMngCompSymbolLibService.getBizMngCompSymbolLibPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BizMngCompSymbolLibRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理部件图示符号库 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-symbol-lib:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBizMngCompSymbolLibExcel(@Valid BizMngCompSymbolLibPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BizMngCompSymbolLibDO> list = bizMngCompSymbolLibService.getBizMngCompSymbolLibPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理部件图示符号库.xls", "数据", BizMngCompSymbolLibRespVO.class,
                        BeanUtils.toBean(list, BizMngCompSymbolLibRespVO.class));
    }

}