package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterext;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterext.vo.BizMngMatterExtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterext.vo.BizMngMatterExtRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterext.vo.BizMngMatterExtSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterext.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmatterext.BizMngMatterExtDO;
import cn.iocoder.yudao.module.datacenter.service.mngmattercfg.bizmngmatterext.BizMngMatterExtService;

@Tag(name = "管理后台 - 管理事项扩展管理事项配置")
@RestController
@RequestMapping("/datacenter/biz-mng-matter-ext")
@Validated
public class BizMngMatterExtController {

    @Resource
    private BizMngMatterExtService bizMngMatterExtService;

    @PostMapping("/create")
    @Operation(summary = "创建管理事项扩展管理事项配置")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-ext:create')")
    public CommonResult<Long> createBizMngMatterExt(@Valid @RequestBody BizMngMatterExtSaveReqVO createReqVO) {
        return success(bizMngMatterExtService.createBizMngMatterExt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理事项扩展管理事项配置")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-ext:update')")
    public CommonResult<Boolean> updateBizMngMatterExt(@Valid @RequestBody BizMngMatterExtSaveReqVO updateReqVO) {
        bizMngMatterExtService.updateBizMngMatterExt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理事项扩展管理事项配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-ext:delete')")
    public CommonResult<Boolean> deleteBizMngMatterExt(@RequestParam("id") Long id) {
        bizMngMatterExtService.deleteBizMngMatterExt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理事项扩展管理事项配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-ext:query')")
    public CommonResult<BizMngMatterExtRespVO> getBizMngMatterExt(@RequestParam("id") Long id) {
        BizMngMatterExtDO bizMngMatterExt = bizMngMatterExtService.getBizMngMatterExt(id);
        return success(BeanUtils.toBean(bizMngMatterExt, BizMngMatterExtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理事项扩展管理事项配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-ext:query')")
    public CommonResult<PageResult<BizMngMatterExtRespVO>> getBizMngMatterExtPage(@Valid BizMngMatterExtPageReqVO pageReqVO) {
        PageResult<BizMngMatterExtDO> pageResult = bizMngMatterExtService.getBizMngMatterExtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BizMngMatterExtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理事项扩展管理事项配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-ext:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBizMngMatterExtExcel(@Valid BizMngMatterExtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BizMngMatterExtDO> list = bizMngMatterExtService.getBizMngMatterExtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理事项扩展管理事项配置.xls", "数据", BizMngMatterExtRespVO.class,
                        BeanUtils.toBean(list, BizMngMatterExtRespVO.class));
    }

}