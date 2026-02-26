package cn.iocoder.yudao.module.envir.controller.admin.publictoilet;

import cn.iocoder.yudao.module.envir.dal.dataobject.publictoilet.PublicToiletDetailDO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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

import cn.iocoder.yudao.module.envir.controller.admin.publictoilet.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.publictoilet.PublicToiletDO;
import cn.iocoder.yudao.module.envir.service.publictoilet.PublicToiletService;

@Tag(name = "管理后台 - 公厕")
@RestController
@RequestMapping("/envir/public-toilet")
@Validated
public class PublicToiletController {

    @Resource
    private PublicToiletService publicToiletService;

    @PostMapping("/create")
    @Operation(summary = "创建公厕")
    @PreAuthorize("@ss.hasPermission('envir:public-toilet:create')")
    public CommonResult<Long> createPublicToilet(@Valid @RequestBody PublicToiletSaveReqVO createReqVO) {
        return success(publicToiletService.createPublicToilet(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新公厕")
    @PreAuthorize("@ss.hasPermission('envir:public-toilet:update')")
    public CommonResult<Boolean> updatePublicToilet(@Valid @RequestBody PublicToiletSaveReqVO updateReqVO) {
        publicToiletService.updatePublicToilet(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除公厕")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envir:public-toilet:delete')")
    public CommonResult<Boolean> deletePublicToilet(@RequestParam("id") Long id) {
        publicToiletService.deletePublicToilet(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得公厕")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envir:public-toilet:query')")
    public CommonResult<PublicToiletRespVO> getPublicToilet(@RequestParam("id") Long id) {
        PublicToiletDO publicToilet = publicToiletService.getPublicToilet(id);
        return success(BeanUtils.toBean(publicToilet, PublicToiletRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得公厕分页")
    @PreAuthorize("@ss.hasPermission('envir:public-toilet:query')")
    public CommonResult<PageResult<PublicToiletRespVO>> getPublicToiletPage(@Valid PublicToiletPageReqVO pageReqVO) {
        PageResult<PublicToiletDO> pageResult = publicToiletService.getPublicToiletPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PublicToiletRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出公厕 Excel")
    @PreAuthorize("@ss.hasPermission('envir:public-toilet:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPublicToiletExcel(@Valid PublicToiletPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PublicToiletDO> list = publicToiletService.getPublicToiletPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "公厕.xls", "数据", PublicToiletRespVO.class,
                        BeanUtils.toBean(list, PublicToiletRespVO.class));
    }

    @GetMapping("/list-detail")
    @Operation(summary = "获取公厕列表(详情)")
    @PreAuthorize("@ss.hasPermission('envir:public-toilet:query')")
    public CommonResult<List<PublicToiletDetailDO>> getPublicToiletListDetail() {
        List<PublicToiletDetailDO> list = publicToiletService.getPublicToiletListDetail();
        return success(list);
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获取公厕列表详情(分页)")
    @PreAuthorize("@ss.hasPermission('envir:public-toilet:query')")
    public CommonResult<PageResult<PublicToiletDetailDO>> getPublicToiletDetailPage(@Valid PublicToiletPageReqVO pageReqVO) {
        PageResult<PublicToiletDetailDO> pageResult = publicToiletService.getPublicToiletDetailPage(pageReqVO);
        return success(pageResult);
    }
}