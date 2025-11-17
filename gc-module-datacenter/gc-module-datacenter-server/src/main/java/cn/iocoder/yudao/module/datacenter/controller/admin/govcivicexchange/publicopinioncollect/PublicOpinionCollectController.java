package cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicopinioncollect;

import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicopinioncollect.vo.PublicOpinionCollectPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicopinioncollect.vo.PublicOpinionCollectRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicopinioncollect.vo.PublicOpinionCollectSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicopinioncollect.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publicopinioncollect.PublicOpinionCollectDO;
import cn.iocoder.yudao.module.datacenter.service.govcivicexchange.publicopinioncollect.PublicOpinionCollectService;

@Tag(name = "管理后台 - 民意征集")
@RestController
@RequestMapping("/datacenter/public-opinion-collect")
@Validated
public class PublicOpinionCollectController {

    @Resource
    private PublicOpinionCollectService publicOpinionCollectService;

    @PostMapping("/create")
    @Operation(summary = "创建民意征集")
    @PreAuthorize("@ss.hasPermission('datacenter:public-opinion-collect:create')")
    public CommonResult<Long> createPublicOpinionCollect(@Valid @RequestBody PublicOpinionCollectSaveReqVO createReqVO) {
        return success(publicOpinionCollectService.createPublicOpinionCollect(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新民意征集")
    @PreAuthorize("@ss.hasPermission('datacenter:public-opinion-collect:update')")
    public CommonResult<Boolean> updatePublicOpinionCollect(@Valid @RequestBody PublicOpinionCollectSaveReqVO updateReqVO) {
        publicOpinionCollectService.updatePublicOpinionCollect(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除民意征集")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:public-opinion-collect:delete')")
    public CommonResult<Boolean> deletePublicOpinionCollect(@RequestParam("id") Long id) {
        publicOpinionCollectService.deletePublicOpinionCollect(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得民意征集")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:public-opinion-collect:query')")
    public CommonResult<PublicOpinionCollectRespVO> getPublicOpinionCollect(@RequestParam("id") Long id) {
        PublicOpinionCollectDO publicOpinionCollect = publicOpinionCollectService.getPublicOpinionCollect(id);
        return success(BeanUtils.toBean(publicOpinionCollect, PublicOpinionCollectRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得民意征集分页")
    @PreAuthorize("@ss.hasPermission('datacenter:public-opinion-collect:query')")
    public CommonResult<PageResult<PublicOpinionCollectRespVO>> getPublicOpinionCollectPage(@Valid PublicOpinionCollectPageReqVO pageReqVO) {
        PageResult<PublicOpinionCollectDO> pageResult = publicOpinionCollectService.getPublicOpinionCollectPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PublicOpinionCollectRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出民意征集 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:public-opinion-collect:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPublicOpinionCollectExcel(@Valid PublicOpinionCollectPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PublicOpinionCollectDO> list = publicOpinionCollectService.getPublicOpinionCollectPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "民意征集.xls", "数据", PublicOpinionCollectRespVO.class,
                        BeanUtils.toBean(list, PublicOpinionCollectRespVO.class));
    }

}