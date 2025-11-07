package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmattermajor;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmattermajor.vo.BizMngMatterMajorPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmattermajor.vo.BizMngMatterMajorRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmattermajor.vo.BizMngMatterMajorSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmattermajor.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmattermajor.BizMngMatterMajorDO;
import cn.iocoder.yudao.module.datacenter.service.mngmattercfg.bizmngmattermajor.BizMngMatterMajorService;

@Tag(name = "管理后台 - 管理事项大类")
@RestController
@RequestMapping("/datacenter/biz-mng-matter-major")
@Validated
public class BizMngMatterMajorController {

    @Resource
    private BizMngMatterMajorService bizMngMatterMajorService;

    @PostMapping("/create")
    @Operation(summary = "创建管理事项大类")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-major:create')")
    public CommonResult<Long> createBizMngMatterMajor(@Valid @RequestBody BizMngMatterMajorSaveReqVO createReqVO) {
        return success(bizMngMatterMajorService.createBizMngMatterMajor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理事项大类")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-major:update')")
    public CommonResult<Boolean> updateBizMngMatterMajor(@Valid @RequestBody BizMngMatterMajorSaveReqVO updateReqVO) {
        bizMngMatterMajorService.updateBizMngMatterMajor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理事项大类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-major:delete')")
    public CommonResult<Boolean> deleteBizMngMatterMajor(@RequestParam("id") Long id) {
        bizMngMatterMajorService.deleteBizMngMatterMajor(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理事项大类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-major:query')")
    public CommonResult<BizMngMatterMajorRespVO> getBizMngMatterMajor(@RequestParam("id") Long id) {
        BizMngMatterMajorDO bizMngMatterMajor = bizMngMatterMajorService.getBizMngMatterMajor(id);
        return success(BeanUtils.toBean(bizMngMatterMajor, BizMngMatterMajorRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理事项大类分页")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-major:query')")
    public CommonResult<PageResult<BizMngMatterMajorRespVO>> getBizMngMatterMajorPage(@Valid BizMngMatterMajorPageReqVO pageReqVO) {
        PageResult<BizMngMatterMajorDO> pageResult = bizMngMatterMajorService.getBizMngMatterMajorPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BizMngMatterMajorRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理事项大类 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-matter-major:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBizMngMatterMajorExcel(@Valid BizMngMatterMajorPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BizMngMatterMajorDO> list = bizMngMatterMajorService.getBizMngMatterMajorPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理事项大类.xls", "数据", BizMngMatterMajorRespVO.class,
                        BeanUtils.toBean(list, BizMngMatterMajorRespVO.class));
    }

}