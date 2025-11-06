package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdeviceinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdeviceinfo.vo.EmerDeviceInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdeviceinfo.vo.EmerDeviceInfoRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdeviceinfo.vo.EmerDeviceInfoSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdeviceinfo.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerdeviceinfo.EmerDeviceInfoDO;
import cn.iocoder.yudao.module.datacenter.service.resschedmanage.emerdeviceinfo.EmerDeviceInfoService;

@Tag(name = "管理后台 - 救援设备信息")
@RestController
@RequestMapping("/datacenter/emer-device-info")
@Validated
public class EmerDeviceInfoController {

    @Resource
    private EmerDeviceInfoService emerDeviceInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建救援设备信息")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-device-info:create')")
    public CommonResult<Long> createEmerDeviceInfo(@Valid @RequestBody EmerDeviceInfoSaveReqVO createReqVO) {
        return success(emerDeviceInfoService.createEmerDeviceInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新救援设备信息")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-device-info:update')")
    public CommonResult<Boolean> updateEmerDeviceInfo(@Valid @RequestBody EmerDeviceInfoSaveReqVO updateReqVO) {
        emerDeviceInfoService.updateEmerDeviceInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除救援设备信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:emer-device-info:delete')")
    public CommonResult<Boolean> deleteEmerDeviceInfo(@RequestParam("id") Long id) {
        emerDeviceInfoService.deleteEmerDeviceInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得救援设备信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-device-info:query')")
    public CommonResult<EmerDeviceInfoRespVO> getEmerDeviceInfo(@RequestParam("id") Long id) {
        EmerDeviceInfoDO emerDeviceInfo = emerDeviceInfoService.getEmerDeviceInfo(id);
        return success(BeanUtils.toBean(emerDeviceInfo, EmerDeviceInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得救援设备信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-device-info:query')")
    public CommonResult<PageResult<EmerDeviceInfoRespVO>> getEmerDeviceInfoPage(@Valid EmerDeviceInfoPageReqVO pageReqVO) {
        PageResult<EmerDeviceInfoDO> pageResult = emerDeviceInfoService.getEmerDeviceInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EmerDeviceInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出救援设备信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-device-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEmerDeviceInfoExcel(@Valid EmerDeviceInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EmerDeviceInfoDO> list = emerDeviceInfoService.getEmerDeviceInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "救援设备信息.xls", "数据", EmerDeviceInfoRespVO.class,
                        BeanUtils.toBean(list, EmerDeviceInfoRespVO.class));
    }

}