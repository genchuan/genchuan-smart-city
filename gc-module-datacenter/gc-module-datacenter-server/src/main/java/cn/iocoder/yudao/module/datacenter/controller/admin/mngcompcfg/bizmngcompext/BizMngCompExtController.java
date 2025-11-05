package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompext;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompext.vo.BizMngCompExtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompext.vo.BizMngCompExtRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompext.vo.BizMngCompExtSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompext.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcompext.BizMngCompExtDO;
import cn.iocoder.yudao.module.datacenter.service.mngcompcfg.bizmngcompext.BizMngCompExtService;

@Tag(name = "管理后台 - 管理部件扩展管理部件配置")
@RestController
@RequestMapping("/datacenter/biz-mng-comp-ext")
@Validated
public class BizMngCompExtController {

    @Resource
    private BizMngCompExtService bizMngCompExtService;

    @PostMapping("/create")
    @Operation(summary = "创建管理部件扩展管理部件配置")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-ext:create')")
    public CommonResult<Long> createBizMngCompExt(@Valid @RequestBody BizMngCompExtSaveReqVO createReqVO) {
        return success(bizMngCompExtService.createBizMngCompExt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理部件扩展管理部件配置")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-ext:update')")
    public CommonResult<Boolean> updateBizMngCompExt(@Valid @RequestBody BizMngCompExtSaveReqVO updateReqVO) {
        bizMngCompExtService.updateBizMngCompExt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理部件扩展管理部件配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-ext:delete')")
    public CommonResult<Boolean> deleteBizMngCompExt(@RequestParam("id") Long id) {
        bizMngCompExtService.deleteBizMngCompExt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理部件扩展管理部件配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-ext:query')")
    public CommonResult<BizMngCompExtRespVO> getBizMngCompExt(@RequestParam("id") Long id) {
        BizMngCompExtDO bizMngCompExt = bizMngCompExtService.getBizMngCompExt(id);
        return success(BeanUtils.toBean(bizMngCompExt, BizMngCompExtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理部件扩展管理部件配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-ext:query')")
    public CommonResult<PageResult<BizMngCompExtRespVO>> getBizMngCompExtPage(@Valid BizMngCompExtPageReqVO pageReqVO) {
        PageResult<BizMngCompExtDO> pageResult = bizMngCompExtService.getBizMngCompExtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BizMngCompExtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理部件扩展管理部件配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-ext:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBizMngCompExtExcel(@Valid BizMngCompExtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BizMngCompExtDO> list = bizMngCompExtService.getBizMngCompExtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理部件扩展管理部件配置.xls", "数据", BizMngCompExtRespVO.class,
                        BeanUtils.toBean(list, BizMngCompExtRespVO.class));
    }

}