package cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplanlib;

import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplanlib.vo.EmerPlanLibPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplanlib.vo.EmerPlanLibRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplanlib.vo.EmerPlanLibSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplanlib.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.emerplan.emerplanlib.EmerPlanLibDO;
import cn.iocoder.yudao.module.datacenter.service.emerplan.emerplanlib.EmerPlanLibService;

@Tag(name = "管理后台 - 预案库")
@RestController
@RequestMapping("/datacenter/emer-plan-lib")
@Validated
public class EmerPlanLibController {

    @Resource
    private EmerPlanLibService emerPlanLibService;

    @PostMapping("/create")
    @Operation(summary = "创建预案库")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-plan-lib:create')")
    public CommonResult<Long> createEmerPlanLib(@Valid @RequestBody EmerPlanLibSaveReqVO createReqVO) {
        return success(emerPlanLibService.createEmerPlanLib(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预案库")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-plan-lib:update')")
    public CommonResult<Boolean> updateEmerPlanLib(@Valid @RequestBody EmerPlanLibSaveReqVO updateReqVO) {
        emerPlanLibService.updateEmerPlanLib(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预案库")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:emer-plan-lib:delete')")
    public CommonResult<Boolean> deleteEmerPlanLib(@RequestParam("id") Long id) {
        emerPlanLibService.deleteEmerPlanLib(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预案库")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-plan-lib:query')")
    public CommonResult<EmerPlanLibRespVO> getEmerPlanLib(@RequestParam("id") Long id) {
        EmerPlanLibDO emerPlanLib = emerPlanLibService.getEmerPlanLib(id);
        return success(BeanUtils.toBean(emerPlanLib, EmerPlanLibRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预案库分页")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-plan-lib:query')")
    public CommonResult<PageResult<EmerPlanLibRespVO>> getEmerPlanLibPage(@Valid EmerPlanLibPageReqVO pageReqVO) {
        PageResult<EmerPlanLibDO> pageResult = emerPlanLibService.getEmerPlanLibPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EmerPlanLibRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预案库 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-plan-lib:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEmerPlanLibExcel(@Valid EmerPlanLibPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EmerPlanLibDO> list = emerPlanLibService.getEmerPlanLibPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预案库.xls", "数据", EmerPlanLibRespVO.class,
                        BeanUtils.toBean(list, EmerPlanLibRespVO.class));
    }

}