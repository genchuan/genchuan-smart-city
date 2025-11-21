package cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publiccomplain;

import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publiccomplain.vo.PublicComplainPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publiccomplain.vo.PublicComplainRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publiccomplain.vo.PublicComplainSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publiccomplain.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publiccomplain.PublicComplainDO;
import cn.iocoder.yudao.module.datacenter.service.bizservicedemand.publiccomplain.PublicComplainService;

@Tag(name = "管理后台 - 投诉举报")
@RestController
@RequestMapping("/datacenter/public-complain")
@Validated
public class PublicComplainController {

    @Resource
    private PublicComplainService publicComplainService;

    @PostMapping("/create")
    @Operation(summary = "创建投诉举报")
    @PreAuthorize("@ss.hasPermission('datacenter:public-complain:create')")
    public CommonResult<Long> createPublicComplain(@Valid @RequestBody PublicComplainSaveReqVO createReqVO) {
        return success(publicComplainService.createPublicComplain(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新投诉举报")
    @PreAuthorize("@ss.hasPermission('datacenter:public-complain:update')")
    public CommonResult<Boolean> updatePublicComplain(@Valid @RequestBody PublicComplainSaveReqVO updateReqVO) {
        publicComplainService.updatePublicComplain(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除投诉举报")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:public-complain:delete')")
    public CommonResult<Boolean> deletePublicComplain(@RequestParam("id") Long id) {
        publicComplainService.deletePublicComplain(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得投诉举报")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:public-complain:query')")
    public CommonResult<PublicComplainRespVO> getPublicComplain(@RequestParam("id") Long id) {
        PublicComplainDO publicComplain = publicComplainService.getPublicComplain(id);
        return success(BeanUtils.toBean(publicComplain, PublicComplainRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得投诉举报分页")
    @PreAuthorize("@ss.hasPermission('datacenter:public-complain:query')")
    public CommonResult<PageResult<PublicComplainRespVO>> getPublicComplainPage(@Valid PublicComplainPageReqVO pageReqVO) {
        PageResult<PublicComplainDO> pageResult = publicComplainService.getPublicComplainPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PublicComplainRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出投诉举报 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:public-complain:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPublicComplainExcel(@Valid PublicComplainPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PublicComplainDO> list = publicComplainService.getPublicComplainPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "投诉举报.xls", "数据", PublicComplainRespVO.class,
                        BeanUtils.toBean(list, PublicComplainRespVO.class));
    }

}