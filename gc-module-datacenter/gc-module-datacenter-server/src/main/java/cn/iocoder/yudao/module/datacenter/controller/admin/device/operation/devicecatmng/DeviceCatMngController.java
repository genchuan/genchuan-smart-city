package cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicecatmng;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicecatmng.vo.DeviceCatMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicecatmng.vo.DeviceCatMngRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicecatmng.vo.DeviceCatMngSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicecatmng.DeviceCatMngDO;
import cn.iocoder.yudao.module.datacenter.service.device.operation.devicecatmng.DeviceCatMngService;

@Tag(name = "管理后台 - 设备分类管理")
@RestController
@RequestMapping("/datacenter/device-cat-mng")
@Validated
public class DeviceCatMngController {

    @Resource
    private DeviceCatMngService deviceCatMngService;

    @PostMapping("/create")
    @Operation(summary = "创建设备分类管理")
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-mng:create')")
    public CommonResult<Long> createDeviceCatMng(@Valid @RequestBody DeviceCatMngSaveReqVO createReqVO) {
        return success(deviceCatMngService.createDeviceCatMng(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备分类管理")
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-mng:update')")
    public CommonResult<Boolean> updateDeviceCatMng(@Valid @RequestBody DeviceCatMngSaveReqVO updateReqVO) {
        deviceCatMngService.updateDeviceCatMng(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备分类管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-mng:delete')")
    public CommonResult<Boolean> deleteDeviceCatMng(@RequestParam("id") Long id) {
        deviceCatMngService.deleteDeviceCatMng(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备分类管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-mng:query')")
    public CommonResult<DeviceCatMngRespVO> getDeviceCatMng(@RequestParam("id") Long id) {
        DeviceCatMngDO deviceCatMng = deviceCatMngService.getDeviceCatMng(id);
        return success(BeanUtils.toBean(deviceCatMng, DeviceCatMngRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备分类管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-mng:query')")
    public CommonResult<PageResult<DeviceCatMngRespVO>> getDeviceCatMngPage(@Valid DeviceCatMngPageReqVO pageReqVO) {
        PageResult<DeviceCatMngDO> pageResult = deviceCatMngService.getDeviceCatMngPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceCatMngRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备分类管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-mng:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceCatMngExcel(@Valid DeviceCatMngPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceCatMngDO> list = deviceCatMngService.getDeviceCatMngPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备分类管理.xls", "数据", DeviceCatMngRespVO.class,
                        BeanUtils.toBean(list, DeviceCatMngRespVO.class));
    }

}