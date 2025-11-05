package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterminor;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterminor.vo.BizMngMatterMinorPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterminor.vo.BizMngMatterMinorRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterminor.vo.BizMngMatterMinorSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterminor.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmatterminor.BizMngMatterMinorDO;
import cn.iocoder.yudao.module.datacenter.service.mngmattercfg.bizmngmatterminor.BizMngMatterMinorService;

@Tag(name = "管理后台 - 管理事项小类")
@RestController
@RequestMapping("/datacenter/biz-mng-matter-minor")
@Validated
public class BizMngMatterMinorController {

    @Resource
    private BizMngMatterMinorService bizMngMatterMinorService;

    @PostMapping("/create")
    @Operation(summary = "创建管理事项小类")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-minor:create')")
    public CommonResult<Long> createBizMngMatterMinor(@Valid @RequestBody BizMngMatterMinorSaveReqVO createReqVO) {
        return success(bizMngMatterMinorService.createBizMngMatterMinor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理事项小类")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-minor:update')")
    public CommonResult<Boolean> updateBizMngMatterMinor(@Valid @RequestBody BizMngMatterMinorSaveReqVO updateReqVO) {
        bizMngMatterMinorService.updateBizMngMatterMinor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理事项小类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-minor:delete')")
    public CommonResult<Boolean> deleteBizMngMatterMinor(@RequestParam("id") Long id) {
        bizMngMatterMinorService.deleteBizMngMatterMinor(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理事项小类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-minor:query')")
    public CommonResult<BizMngMatterMinorRespVO> getBizMngMatterMinor(@RequestParam("id") Long id) {
        BizMngMatterMinorDO bizMngMatterMinor = bizMngMatterMinorService.getBizMngMatterMinor(id);
        return success(BeanUtils.toBean(bizMngMatterMinor, BizMngMatterMinorRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理事项小类分页")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-minor:query')")
    public CommonResult<PageResult<BizMngMatterMinorRespVO>> getBizMngMatterMinorPage(@Valid BizMngMatterMinorPageReqVO pageReqVO) {
        PageResult<BizMngMatterMinorDO> pageResult = bizMngMatterMinorService.getBizMngMatterMinorPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BizMngMatterMinorRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理事项小类 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-minor:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBizMngMatterMinorExcel(@Valid BizMngMatterMinorPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BizMngMatterMinorDO> list = bizMngMatterMinorService.getBizMngMatterMinorPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理事项小类.xls", "数据", BizMngMatterMinorRespVO.class,
                        BeanUtils.toBean(list, BizMngMatterMinorRespVO.class));
    }

}