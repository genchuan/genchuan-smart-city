package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatcat;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatcat.vo.EmerMatCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatcat.vo.EmerMatCatRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatcat.vo.EmerMatCatSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatcat.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emermatcat.EmerMatCatDO;
import cn.iocoder.yudao.module.datacenter.service.resschedmanage.emermatcat.EmerMatCatService;

@Tag(name = "管理后台 - 应急物资分类配置")
@RestController
@RequestMapping("/datacenter/emer-mat-cat")
@Validated
public class EmerMatCatController {

    @Resource
    private EmerMatCatService emerMatCatService;

    @PostMapping("/create")
    @Operation(summary = "创建应急物资分类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-mat-cat:create')")
    public CommonResult<Long> createEmerMatCat(@Valid @RequestBody EmerMatCatSaveReqVO createReqVO) {
        return success(emerMatCatService.createEmerMatCat(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新应急物资分类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-mat-cat:update')")
    public CommonResult<Boolean> updateEmerMatCat(@Valid @RequestBody EmerMatCatSaveReqVO updateReqVO) {
        emerMatCatService.updateEmerMatCat(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除应急物资分类配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:emer-mat-cat:delete')")
    public CommonResult<Boolean> deleteEmerMatCat(@RequestParam("id") Long id) {
        emerMatCatService.deleteEmerMatCat(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得应急物资分类配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-mat-cat:query')")
    public CommonResult<EmerMatCatRespVO> getEmerMatCat(@RequestParam("id") Long id) {
        EmerMatCatDO emerMatCat = emerMatCatService.getEmerMatCat(id);
        return success(BeanUtils.toBean(emerMatCat, EmerMatCatRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得应急物资分类配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-mat-cat:query')")
    public CommonResult<PageResult<EmerMatCatRespVO>> getEmerMatCatPage(@Valid EmerMatCatPageReqVO pageReqVO) {
        PageResult<EmerMatCatDO> pageResult = emerMatCatService.getEmerMatCatPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EmerMatCatRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出应急物资分类配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-mat-cat:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEmerMatCatExcel(@Valid EmerMatCatPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EmerMatCatDO> list = emerMatCatService.getEmerMatCatPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "应急物资分类配置.xls", "数据", EmerMatCatRespVO.class,
                        BeanUtils.toBean(list, EmerMatCatRespVO.class));
    }

}