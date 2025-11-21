package cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.internationaldynamic;

import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.internationaldynamic.vo.InternationalDynamicPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.internationaldynamic.vo.InternationalDynamicRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.internationaldynamic.vo.InternationalDynamicSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.internationaldynamic.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.industrydynamic.internationaldynamic.InternationalDynamicDO;
import cn.iocoder.yudao.module.datacenter.service.industrydynamic.internationaldynamic.InternationalDynamicService;

@Tag(name = "管理后台 - 国际行业动态")
@RestController
@RequestMapping("/datacenter/international-dynamic")
@Validated
public class InternationalDynamicController {

    @Resource
    private InternationalDynamicService internationalDynamicService;

    @PostMapping("/create")
    @Operation(summary = "创建国际行业动态")
    @PreAuthorize("@ss.hasPermission('datacenter:international-dynamic:create')")
    public CommonResult<Long> createInternationalDynamic(@Valid @RequestBody InternationalDynamicSaveReqVO createReqVO) {
        return success(internationalDynamicService.createInternationalDynamic(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新国际行业动态")
    @PreAuthorize("@ss.hasPermission('datacenter:international-dynamic:update')")
    public CommonResult<Boolean> updateInternationalDynamic(@Valid @RequestBody InternationalDynamicSaveReqVO updateReqVO) {
        internationalDynamicService.updateInternationalDynamic(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除国际行业动态")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:international-dynamic:delete')")
    public CommonResult<Boolean> deleteInternationalDynamic(@RequestParam("id") Long id) {
        internationalDynamicService.deleteInternationalDynamic(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得国际行业动态")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:international-dynamic:query')")
    public CommonResult<InternationalDynamicRespVO> getInternationalDynamic(@RequestParam("id") Long id) {
        InternationalDynamicDO internationalDynamic = internationalDynamicService.getInternationalDynamic(id);
        return success(BeanUtils.toBean(internationalDynamic, InternationalDynamicRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得国际行业动态分页")
    @PreAuthorize("@ss.hasPermission('datacenter:international-dynamic:query')")
    public CommonResult<PageResult<InternationalDynamicRespVO>> getInternationalDynamicPage(@Valid InternationalDynamicPageReqVO pageReqVO) {
        PageResult<InternationalDynamicDO> pageResult = internationalDynamicService.getInternationalDynamicPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InternationalDynamicRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出国际行业动态 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:international-dynamic:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInternationalDynamicExcel(@Valid InternationalDynamicPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InternationalDynamicDO> list = internationalDynamicService.getInternationalDynamicPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "国际行业动态.xls", "数据", InternationalDynamicRespVO.class,
                        BeanUtils.toBean(list, InternationalDynamicRespVO.class));
    }

}