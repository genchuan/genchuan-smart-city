package cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicpolinterpret;

import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicpolinterpret.vo.PublicPolInterpretPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicpolinterpret.vo.PublicPolInterpretRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicpolinterpret.vo.PublicPolInterpretSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicpolinterpret.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.publicinfopublish.publicpolinterpret.PublicPolInterpretDO;
import cn.iocoder.yudao.module.datacenter.service.publicinfopublish.publicpolinterpret.PublicPolInterpretService;

@Tag(name = "管理后台 - 政策解读发布")
@RestController
@RequestMapping("/datacenter/public-pol-interpret")
@Validated
public class PublicPolInterpretController {

    @Resource
    private PublicPolInterpretService publicPolInterpretService;

    @PostMapping("/create")
    @Operation(summary = "创建政策解读发布")
    @PreAuthorize("@ss.hasPermission('datacenter:public-pol-interpret:create')")
    public CommonResult<Long> createPublicPolInterpret(@Valid @RequestBody PublicPolInterpretSaveReqVO createReqVO) {
        return success(publicPolInterpretService.createPublicPolInterpret(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新政策解读发布")
    @PreAuthorize("@ss.hasPermission('datacenter:public-pol-interpret:update')")
    public CommonResult<Boolean> updatePublicPolInterpret(@Valid @RequestBody PublicPolInterpretSaveReqVO updateReqVO) {
        publicPolInterpretService.updatePublicPolInterpret(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除政策解读发布")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:public-pol-interpret:delete')")
    public CommonResult<Boolean> deletePublicPolInterpret(@RequestParam("id") Long id) {
        publicPolInterpretService.deletePublicPolInterpret(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得政策解读发布")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:public-pol-interpret:query')")
    public CommonResult<PublicPolInterpretRespVO> getPublicPolInterpret(@RequestParam("id") Long id) {
        PublicPolInterpretDO publicPolInterpret = publicPolInterpretService.getPublicPolInterpret(id);
        return success(BeanUtils.toBean(publicPolInterpret, PublicPolInterpretRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得政策解读发布分页")
    @PreAuthorize("@ss.hasPermission('datacenter:public-pol-interpret:query')")
    public CommonResult<PageResult<PublicPolInterpretRespVO>> getPublicPolInterpretPage(@Valid PublicPolInterpretPageReqVO pageReqVO) {
        PageResult<PublicPolInterpretDO> pageResult = publicPolInterpretService.getPublicPolInterpretPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PublicPolInterpretRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出政策解读发布 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:public-pol-interpret:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPublicPolInterpretExcel(@Valid PublicPolInterpretPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PublicPolInterpretDO> list = publicPolInterpretService.getPublicPolInterpretPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "政策解读发布.xls", "数据", PublicPolInterpretRespVO.class,
                        BeanUtils.toBean(list, PublicPolInterpretRespVO.class));
    }

}