package cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publichotlinelink;

import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publichotlinelink.vo.PublicHotlineLinkPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publichotlinelink.vo.PublicHotlineLinkRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publichotlinelink.vo.PublicHotlineLinkSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publichotlinelink.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publichotlinelink.PublicHotlineLinkDO;
import cn.iocoder.yudao.module.datacenter.service.bizservicedemand.publichotlinelink.PublicHotlineLinkService;

@Tag(name = "管理后台 - 热线对接")
@RestController
@RequestMapping("/datacenter/public-hotline-link")
@Validated
public class PublicHotlineLinkController {

    @Resource
    private PublicHotlineLinkService publicHotlineLinkService;

    @PostMapping("/create")
    @Operation(summary = "创建热线对接")
    @PreAuthorize("@ss.hasPermission('datacenter:public-hotline-link:create')")
    public CommonResult<Long> createPublicHotlineLink(@Valid @RequestBody PublicHotlineLinkSaveReqVO createReqVO) {
        return success(publicHotlineLinkService.createPublicHotlineLink(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新热线对接")
    @PreAuthorize("@ss.hasPermission('datacenter:public-hotline-link:update')")
    public CommonResult<Boolean> updatePublicHotlineLink(@Valid @RequestBody PublicHotlineLinkSaveReqVO updateReqVO) {
        publicHotlineLinkService.updatePublicHotlineLink(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除热线对接")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:public-hotline-link:delete')")
    public CommonResult<Boolean> deletePublicHotlineLink(@RequestParam("id") Long id) {
        publicHotlineLinkService.deletePublicHotlineLink(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得热线对接")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:public-hotline-link:query')")
    public CommonResult<PublicHotlineLinkRespVO> getPublicHotlineLink(@RequestParam("id") Long id) {
        PublicHotlineLinkDO publicHotlineLink = publicHotlineLinkService.getPublicHotlineLink(id);
        return success(BeanUtils.toBean(publicHotlineLink, PublicHotlineLinkRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得热线对接分页")
    @PreAuthorize("@ss.hasPermission('datacenter:public-hotline-link:query')")
    public CommonResult<PageResult<PublicHotlineLinkRespVO>> getPublicHotlineLinkPage(@Valid PublicHotlineLinkPageReqVO pageReqVO) {
        PageResult<PublicHotlineLinkDO> pageResult = publicHotlineLinkService.getPublicHotlineLinkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PublicHotlineLinkRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出热线对接 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:public-hotline-link:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPublicHotlineLinkExcel(@Valid PublicHotlineLinkPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PublicHotlineLinkDO> list = publicHotlineLinkService.getPublicHotlineLinkPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "热线对接.xls", "数据", PublicHotlineLinkRespVO.class,
                        BeanUtils.toBean(list, PublicHotlineLinkRespVO.class));
    }

}