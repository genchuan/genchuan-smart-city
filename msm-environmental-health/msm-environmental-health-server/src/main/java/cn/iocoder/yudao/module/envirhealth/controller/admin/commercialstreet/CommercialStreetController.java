package cn.iocoder.yudao.module.envirhealth.controller.admin.commercialstreet;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.commercialstreet.detail.CommercialStreetDetailDO;
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

import cn.iocoder.yudao.module.envirhealth.controller.admin.commercialstreet.vo.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.commercialstreet.CommercialStreetDO;
import cn.iocoder.yudao.module.envirhealth.service.commercialstreet.CommercialStreetService;

@Tag(name = "环境卫生管理 - 商业街")
@RestController
@RequestMapping("/envirhealth/commercial-street")
@Validated
public class CommercialStreetController {

    @Resource
    private CommercialStreetService commercialStreetService;

    @PostMapping("/create")
    @Operation(summary = "创建商业街")
    @PreAuthorize("@ss.hasPermission('envirhealth:commercial-street:create')")
    public CommonResult<Long> createCommercialStreet(@Valid @RequestBody CommercialStreetSaveReqVO createReqVO) {
        return success(commercialStreetService.createCommercialStreet(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新商业街")
    @PreAuthorize("@ss.hasPermission('envirhealth:commercial-street:update')")
    public CommonResult<Boolean> updateCommercialStreet(@Valid @RequestBody CommercialStreetSaveReqVO updateReqVO) {
        commercialStreetService.updateCommercialStreet(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除商业街")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:commercial-street:delete')")
    public CommonResult<Boolean> deleteCommercialStreet(@RequestParam("id") Long id) {
        commercialStreetService.deleteCommercialStreet(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得商业街")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:commercial-street:query')")
    public CommonResult<CommercialStreetRespVO> getCommercialStreet(@RequestParam("id") Long id) {
        CommercialStreetDO commercialStreet = commercialStreetService.getCommercialStreet(id);
        return success(BeanUtils.toBean(commercialStreet, CommercialStreetRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得商业街分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:commercial-street:query')")
    public CommonResult<PageResult<CommercialStreetRespVO>> getCommercialStreetPage(@Valid CommercialStreetPageReqVO pageReqVO) {
        PageResult<CommercialStreetDO> pageResult = commercialStreetService.getCommercialStreetPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CommercialStreetRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出商业街 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:commercial-street:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCommercialStreetExcel(@Valid CommercialStreetPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CommercialStreetDO> list = commercialStreetService.getCommercialStreetPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "商业街.xls", "数据", CommercialStreetRespVO.class,
                        BeanUtils.toBean(list, CommercialStreetRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得商业街详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:commercial-street:query')")
    public CommonResult<PageResult<CommercialStreetDetailDO>> getCommercialStreetDetailPage(
            @Valid CommercialStreetPageReqVO pageReqVO) {
        PageResult<CommercialStreetDetailDO> pageResult =
                commercialStreetService.getCommercialStreetDetailPage(pageReqVO);

        return success(pageResult);
    }
}