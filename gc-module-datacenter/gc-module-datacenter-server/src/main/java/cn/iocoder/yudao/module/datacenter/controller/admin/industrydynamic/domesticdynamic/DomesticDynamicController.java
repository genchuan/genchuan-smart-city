package cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.domesticdynamic;

import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.domesticdynamic.vo.DomesticDynamicPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.domesticdynamic.vo.DomesticDynamicRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.domesticdynamic.vo.DomesticDynamicSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.domesticdynamic.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.industrydynamic.domesticdynamic.DomesticDynamicDO;
import cn.iocoder.yudao.module.datacenter.service.industrydynamic.domesticdynamic.DomesticDynamicService;

@Tag(name = "管理后台 - 国内行业动态")
@RestController
@RequestMapping("/datacenter/domestic-dynamic")
@Validated
public class DomesticDynamicController {

    @Resource
    private DomesticDynamicService domesticDynamicService;

    @PostMapping("/create")
    @Operation(summary = "创建国内行业动态")
    @PreAuthorize("@ss.hasPermission('datacenter:domestic-dynamic:create')")
    public CommonResult<Long> createDomesticDynamic(@Valid @RequestBody DomesticDynamicSaveReqVO createReqVO) {
        return success(domesticDynamicService.createDomesticDynamic(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新国内行业动态")
    @PreAuthorize("@ss.hasPermission('datacenter:domestic-dynamic:update')")
    public CommonResult<Boolean> updateDomesticDynamic(@Valid @RequestBody DomesticDynamicSaveReqVO updateReqVO) {
        domesticDynamicService.updateDomesticDynamic(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除国内行业动态")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:domestic-dynamic:delete')")
    public CommonResult<Boolean> deleteDomesticDynamic(@RequestParam("id") Long id) {
        domesticDynamicService.deleteDomesticDynamic(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得国内行业动态")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:domestic-dynamic:query')")
    public CommonResult<DomesticDynamicRespVO> getDomesticDynamic(@RequestParam("id") Long id) {
        DomesticDynamicDO domesticDynamic = domesticDynamicService.getDomesticDynamic(id);
        return success(BeanUtils.toBean(domesticDynamic, DomesticDynamicRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得国内行业动态分页")
    @PreAuthorize("@ss.hasPermission('datacenter:domestic-dynamic:query')")
    public CommonResult<PageResult<DomesticDynamicRespVO>> getDomesticDynamicPage(@Valid DomesticDynamicPageReqVO pageReqVO) {
        PageResult<DomesticDynamicDO> pageResult = domesticDynamicService.getDomesticDynamicPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomesticDynamicRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出国内行业动态 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:domestic-dynamic:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomesticDynamicExcel(@Valid DomesticDynamicPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomesticDynamicDO> list = domesticDynamicService.getDomesticDynamicPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "国内行业动态.xls", "数据", DomesticDynamicRespVO.class,
                        BeanUtils.toBean(list, DomesticDynamicRespVO.class));
    }

}