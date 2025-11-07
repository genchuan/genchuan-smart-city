package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompspatial;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompspatial.vo.BizMngCompSpatialPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompspatial.vo.BizMngCompSpatialRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompspatial.vo.BizMngCompSpatialSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompspatial.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcompspatial.BizMngCompSpatialDO;
import cn.iocoder.yudao.module.datacenter.service.mngcompcfg.bizmngcompspatial.BizMngCompSpatialService;

@Tag(name = "管理后台 - 管理部件空间数据")
@RestController
@RequestMapping("/datacenter/biz-mng-comp-spatial")
@Validated
public class BizMngCompSpatialController {

    @Resource
    private BizMngCompSpatialService bizMngCompSpatialService;

    @PostMapping("/create")
    @Operation(summary = "创建管理部件空间数据")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-spatial:create')")
    public CommonResult<Long> createBizMngCompSpatial(@Valid @RequestBody BizMngCompSpatialSaveReqVO createReqVO) {
        return success(bizMngCompSpatialService.createBizMngCompSpatial(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理部件空间数据")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-spatial:update')")
    public CommonResult<Boolean> updateBizMngCompSpatial(@Valid @RequestBody BizMngCompSpatialSaveReqVO updateReqVO) {
        bizMngCompSpatialService.updateBizMngCompSpatial(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理部件空间数据")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-spatial:delete')")
    public CommonResult<Boolean> deleteBizMngCompSpatial(@RequestParam("id") Long id) {
        bizMngCompSpatialService.deleteBizMngCompSpatial(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理部件空间数据")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-spatial:query')")
    public CommonResult<BizMngCompSpatialRespVO> getBizMngCompSpatial(@RequestParam("id") Long id) {
        BizMngCompSpatialDO bizMngCompSpatial = bizMngCompSpatialService.getBizMngCompSpatial(id);
        return success(BeanUtils.toBean(bizMngCompSpatial, BizMngCompSpatialRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理部件空间数据分页")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-spatial:query')")
    public CommonResult<PageResult<BizMngCompSpatialRespVO>> getBizMngCompSpatialPage(@Valid BizMngCompSpatialPageReqVO pageReqVO) {
        PageResult<BizMngCompSpatialDO> pageResult = bizMngCompSpatialService.getBizMngCompSpatialPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BizMngCompSpatialRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理部件空间数据 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp-spatial:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBizMngCompSpatialExcel(@Valid BizMngCompSpatialPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BizMngCompSpatialDO> list = bizMngCompSpatialService.getBizMngCompSpatialPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理部件空间数据.xls", "数据", BizMngCompSpatialRespVO.class,
                        BeanUtils.toBean(list, BizMngCompSpatialRespVO.class));
    }

}