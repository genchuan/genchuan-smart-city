package cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicsvcinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicsvcinfo.vo.PublicSvcInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicsvcinfo.vo.PublicSvcInfoRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicsvcinfo.vo.PublicSvcInfoSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicsvcinfo.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.publicinfopublish.publicsvcinfo.PublicSvcInfoDO;
import cn.iocoder.yudao.module.datacenter.service.publicinfopublish.publicsvcinfo.PublicSvcInfoService;

@Tag(name = "管理后台 - 服务信息发布")
@RestController
@RequestMapping("/datacenter/public-svc-info")
@Validated
public class PublicSvcInfoController {

    @Resource
    private PublicSvcInfoService publicSvcInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建服务信息发布")
    @PreAuthorize("@ss.hasPermission('datacenter:public-svc-info:create')")
    public CommonResult<Long> createPublicSvcInfo(@Valid @RequestBody PublicSvcInfoSaveReqVO createReqVO) {
        return success(publicSvcInfoService.createPublicSvcInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新服务信息发布")
    @PreAuthorize("@ss.hasPermission('datacenter:public-svc-info:update')")
    public CommonResult<Boolean> updatePublicSvcInfo(@Valid @RequestBody PublicSvcInfoSaveReqVO updateReqVO) {
        publicSvcInfoService.updatePublicSvcInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除服务信息发布")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:public-svc-info:delete')")
    public CommonResult<Boolean> deletePublicSvcInfo(@RequestParam("id") Long id) {
        publicSvcInfoService.deletePublicSvcInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得服务信息发布")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:public-svc-info:query')")
    public CommonResult<PublicSvcInfoRespVO> getPublicSvcInfo(@RequestParam("id") Long id) {
        PublicSvcInfoDO publicSvcInfo = publicSvcInfoService.getPublicSvcInfo(id);
        return success(BeanUtils.toBean(publicSvcInfo, PublicSvcInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得服务信息发布分页")
    @PreAuthorize("@ss.hasPermission('datacenter:public-svc-info:query')")
    public CommonResult<PageResult<PublicSvcInfoRespVO>> getPublicSvcInfoPage(@Valid PublicSvcInfoPageReqVO pageReqVO) {
        PageResult<PublicSvcInfoDO> pageResult = publicSvcInfoService.getPublicSvcInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PublicSvcInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出服务信息发布 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:public-svc-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPublicSvcInfoExcel(@Valid PublicSvcInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PublicSvcInfoDO> list = publicSvcInfoService.getPublicSvcInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "服务信息发布.xls", "数据", PublicSvcInfoRespVO.class,
                        BeanUtils.toBean(list, PublicSvcInfoRespVO.class));
    }

}