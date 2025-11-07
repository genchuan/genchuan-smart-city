package cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplancat;

import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplancat.vo.EmerPlanCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplancat.vo.EmerPlanCatRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplancat.vo.EmerPlanCatSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplancat.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.emerplan.emerplancat.EmerPlanCatDO;
import cn.iocoder.yudao.module.datacenter.service.emerplan.emerplancat.EmerPlanCatService;

@Tag(name = "管理后台 - 预案分类配置")
@RestController
@RequestMapping("/datacenter/emer-plan-cat")
@Validated
public class EmerPlanCatController {

    @Resource
    private EmerPlanCatService emerPlanCatService;

    @PostMapping("/create")
    @Operation(summary = "创建预案分类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-plan-cat:create')")
    public CommonResult<Long> createEmerPlanCat(@Valid @RequestBody EmerPlanCatSaveReqVO createReqVO) {
        return success(emerPlanCatService.createEmerPlanCat(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预案分类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-plan-cat:update')")
    public CommonResult<Boolean> updateEmerPlanCat(@Valid @RequestBody EmerPlanCatSaveReqVO updateReqVO) {
        emerPlanCatService.updateEmerPlanCat(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预案分类配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:emer-plan-cat:delete')")
    public CommonResult<Boolean> deleteEmerPlanCat(@RequestParam("id") Long id) {
        emerPlanCatService.deleteEmerPlanCat(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预案分类配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-plan-cat:query')")
    public CommonResult<EmerPlanCatRespVO> getEmerPlanCat(@RequestParam("id") Long id) {
        EmerPlanCatDO emerPlanCat = emerPlanCatService.getEmerPlanCat(id);
        return success(BeanUtils.toBean(emerPlanCat, EmerPlanCatRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预案分类配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-plan-cat:query')")
    public CommonResult<PageResult<EmerPlanCatRespVO>> getEmerPlanCatPage(@Valid EmerPlanCatPageReqVO pageReqVO) {
        PageResult<EmerPlanCatDO> pageResult = emerPlanCatService.getEmerPlanCatPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EmerPlanCatRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预案分类配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-plan-cat:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEmerPlanCatExcel(@Valid EmerPlanCatPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EmerPlanCatDO> list = emerPlanCatService.getEmerPlanCatPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预案分类配置.xls", "数据", EmerPlanCatRespVO.class,
                        BeanUtils.toBean(list, EmerPlanCatRespVO.class));
    }

}