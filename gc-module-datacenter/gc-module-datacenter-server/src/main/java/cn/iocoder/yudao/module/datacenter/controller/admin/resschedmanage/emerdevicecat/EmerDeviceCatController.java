package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdevicecat;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdevicecat.vo.EmerDeviceCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdevicecat.vo.EmerDeviceCatRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdevicecat.vo.EmerDeviceCatSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdevicecat.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerdevicecat.EmerDeviceCatDO;
import cn.iocoder.yudao.module.datacenter.service.resschedmanage.emerdevicecat.EmerDeviceCatService;

@Tag(name = "管理后台 - 救援设备分类配置")
@RestController
@RequestMapping("/datacenter/emer-device-cat")
@Validated
public class EmerDeviceCatController {

    @Resource
    private EmerDeviceCatService emerDeviceCatService;

    @PostMapping("/create")
    @Operation(summary = "创建救援设备分类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-device-cat:create')")
    public CommonResult<Long> createEmerDeviceCat(@Valid @RequestBody EmerDeviceCatSaveReqVO createReqVO) {
        return success(emerDeviceCatService.createEmerDeviceCat(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新救援设备分类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-device-cat:update')")
    public CommonResult<Boolean> updateEmerDeviceCat(@Valid @RequestBody EmerDeviceCatSaveReqVO updateReqVO) {
        emerDeviceCatService.updateEmerDeviceCat(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除救援设备分类配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:emer-device-cat:delete')")
    public CommonResult<Boolean> deleteEmerDeviceCat(@RequestParam("id") Long id) {
        emerDeviceCatService.deleteEmerDeviceCat(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得救援设备分类配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-device-cat:query')")
    public CommonResult<EmerDeviceCatRespVO> getEmerDeviceCat(@RequestParam("id") Long id) {
        EmerDeviceCatDO emerDeviceCat = emerDeviceCatService.getEmerDeviceCat(id);
        return success(BeanUtils.toBean(emerDeviceCat, EmerDeviceCatRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得救援设备分类配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-device-cat:query')")
    public CommonResult<PageResult<EmerDeviceCatRespVO>> getEmerDeviceCatPage(@Valid EmerDeviceCatPageReqVO pageReqVO) {
        PageResult<EmerDeviceCatDO> pageResult = emerDeviceCatService.getEmerDeviceCatPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EmerDeviceCatRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出救援设备分类配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-device-cat:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEmerDeviceCatExcel(@Valid EmerDeviceCatPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EmerDeviceCatDO> list = emerDeviceCatService.getEmerDeviceCatPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "救援设备分类配置.xls", "数据", EmerDeviceCatRespVO.class,
                        BeanUtils.toBean(list, EmerDeviceCatRespVO.class));
    }

}