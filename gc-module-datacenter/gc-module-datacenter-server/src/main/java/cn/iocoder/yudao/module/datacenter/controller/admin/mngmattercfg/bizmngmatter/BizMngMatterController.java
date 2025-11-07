package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatter;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatter.vo.BizMngMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatter.vo.BizMngMatterRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatter.vo.BizMngMatterSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatter.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmatter.BizMngMatterDO;
import cn.iocoder.yudao.module.datacenter.service.mngmattercfg.bizmngmatter.BizMngMatterService;

@Tag(name = "管理后台 - 管理事项信息")
@RestController
@RequestMapping("/datacenter/biz-mng-matter")
@Validated
public class BizMngMatterController {

    @Resource
    private BizMngMatterService bizMngMatterService;

    @PostMapping("/create")
    @Operation(summary = "创建管理事项信息")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter:create')")
    public CommonResult<Long> createBizMngMatter(@Valid @RequestBody BizMngMatterSaveReqVO createReqVO) {
        return success(bizMngMatterService.createBizMngMatter(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理事项信息")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter:update')")
    public CommonResult<Boolean> updateBizMngMatter(@Valid @RequestBody BizMngMatterSaveReqVO updateReqVO) {
        bizMngMatterService.updateBizMngMatter(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理事项信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter:delete')")
    public CommonResult<Boolean> deleteBizMngMatter(@RequestParam("id") Long id) {
        bizMngMatterService.deleteBizMngMatter(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理事项信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter:query')")
    public CommonResult<BizMngMatterRespVO> getBizMngMatter(@RequestParam("id") Long id) {
        BizMngMatterDO bizMngMatter = bizMngMatterService.getBizMngMatter(id);
        return success(BeanUtils.toBean(bizMngMatter, BizMngMatterRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理事项信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter:query')")
    public CommonResult<PageResult<BizMngMatterRespVO>> getBizMngMatterPage(@Valid BizMngMatterPageReqVO pageReqVO) {
        PageResult<BizMngMatterDO> pageResult = bizMngMatterService.getBizMngMatterPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BizMngMatterRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理事项信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBizMngMatterExcel(@Valid BizMngMatterPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BizMngMatterDO> list = bizMngMatterService.getBizMngMatterPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理事项信息.xls", "数据", BizMngMatterRespVO.class,
                        BeanUtils.toBean(list, BizMngMatterRespVO.class));
    }

}