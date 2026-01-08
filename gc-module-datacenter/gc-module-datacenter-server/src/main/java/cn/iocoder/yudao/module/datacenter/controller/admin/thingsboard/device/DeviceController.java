package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device;


import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.asset.AssetDO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.device.DeviceDO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.thingsboard.device.DeviceMapper;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.AssetServiceImpl;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.device.Dao.DeviceTbDao;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.device.DeviceService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
        import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
        import jakarta.servlet.http.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
        import java.io.IOException;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.DeviceInfo;
import org.thingsboard.server.common.data.kv.AttributeKvEntry;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;


@Tag(name = "管理后台 - 设备")
@RestController
@RequestMapping("/datacenter/thingsboard/device")
@Validated
public class DeviceController {

    @Resource
    private DeviceService deviceService;
    private static final Logger log = LoggerFactory.getLogger(AssetServiceImpl.class);


    @PostMapping("/create")
    @Operation(summary = "创建设备")
    @PreAuthorize("@ss.hasPermission('device:device:create')")
    public CommonResult<String> createDevice(@Valid @RequestBody DeviceSaveReqVO createReqVO) {
        return success(deviceService.createDevice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备")
    @PreAuthorize("@ss.hasPermission('device:device:update')")
    public CommonResult<Boolean> updateDevice(@Valid @RequestBody DeviceSaveReqVO updateReqVO) {
        deviceService.updateDevice(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('device:device:delete')")
    public CommonResult<Boolean> deleteDevice(@RequestParam("id") String id) {
        deviceService.deleteDevice(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除设备")
    @PreAuthorize("@ss.hasPermission('device:device:delete')")
    public CommonResult<Boolean> deleteDeviceList(@RequestParam("ids") List<String> ids) {
        deviceService.deleteDeviceListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('device:device:query')")
    public CommonResult<DeviceRespVO> getDevice(@RequestParam("id") String id) {
        DeviceDO device = deviceService.getDevice(id);
        return success(BeanUtils.toBean(device, DeviceRespVO.class));
    }

    @GetMapping("/getAttr")
    @Operation(summary = "获得设备属性")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('device:device:query')")
    public CommonResult<List<AttributeKvEntry>> getDeviceAttr(@RequestParam("id") String id) {
        List<AttributeKvEntry> device = deviceService.getAttributeKvEntries(id);
        return success(device);
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备分页")
    @PreAuthorize("@ss.hasPermission('device:device:query')")
    public CommonResult<PageResult<DeviceRespVO>> getDevicePage(@Valid DevicePageReqVO pageReqVO) {
        PageResult<DeviceDO> pageResult = deviceService.getDevicePage(pageReqVO);

        // 手动转换，避免类型不匹配
        List<DeviceRespVO> voList = pageResult.getList().stream()
                .map(this::convertToDeviceRespVO)
                .collect(Collectors.toList());

        PageResult<DeviceRespVO> voPageResult = new PageResult<>();
        voPageResult.setList(voList);
        voPageResult.setTotal(pageResult.getTotal());

        return success(voPageResult);
    }

    @GetMapping("/alarm-page")
    @Operation(summary = "获得告警分页")
    @PreAuthorize("@ss.hasPermission('device:alarm:query')")
    public CommonResult<PageResult<AlarmRespVO>> getAlarmPage(
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "page", defaultValue = "0") Integer page) {

        PageResult<AlarmRespVO> pageResult = deviceService.getAlarmPage(pageSize, page);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备 Excel")
    @PreAuthorize("@ss.hasPermission('device:device:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceExcel(@Valid DevicePageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceDO> list = deviceService.getDevicePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备.xls", "数据", DeviceRespVO.class,
                BeanUtils.toBean(list, DeviceRespVO.class));
    }

    @PostMapping("/sync")
    @Operation(summary = "同步ThingsBoard设备")
    @PreAuthorize("@ss.hasPermission('device:device:sync')")
    public CommonResult<Map<String, Object>> syncDevicesFromThingsBoard() {
        Map<String, Object> result = deviceService.syncDevicesFromThingsBoard();
        return success(result);
    }

    @PostMapping("/{deviceId}/attributes")
    @Operation(summary = "添加设备属性")
    @PreAuthorize("@ss.hasPermission('device:device:update')")
    public CommonResult<Boolean> addDeviceAttributes(
            @Parameter(description = "设备ID", required = true)
            @PathVariable("deviceId") String deviceId,
            @RequestBody Map<String, Object> attributes) {

        deviceService.addDeviceAttributes(deviceId, attributes);
        return success(true);
    }

    @DeleteMapping("/{deviceId}/attributes")
    @Operation(summary = "删除设备属性")
    @PreAuthorize("@ss.hasPermission('device:device:update')")
    public CommonResult<Boolean> deleteDeviceAttributes(
            @Parameter(description = "设备ID", required = true)
            @PathVariable("deviceId") String deviceId,
            @Parameter(description = "属性作用域", example = "SERVER_SCOPE")
            @RequestParam(value = "scope", defaultValue = "SERVER_SCOPE") String scope,
            @Parameter(description = "要删除的属性键，用逗号分隔", required = true)
            @RequestParam("keys") String keys) {

        List<String> keyList = Arrays.asList(keys.split(","));
        deviceService.deleteDeviceAttributes(deviceId, scope, keyList);
        return success(true);
    }

    /**
     * 手动转换 DeviceDO 到 DeviceRespVO
     */
    private DeviceRespVO convertToDeviceRespVO(DeviceDO deviceDO) {
        DeviceRespVO respVO = new DeviceRespVO();

        // 基本字段直接复制
        respVO.setId(deviceDO.getId());
        respVO.setTenantId(deviceDO.getTbTenantId());
        respVO.setCustomerId(deviceDO.getCustomerId());
        respVO.setName(deviceDO.getName());
        respVO.setType(deviceDO.getType());
        respVO.setLabel(deviceDO.getLabel());
        respVO.setDeviceProfileId(deviceDO.getDeviceProfileId());
        respVO.setFirmwareId(deviceDO.getFirmwareId());
        respVO.setSoftwareId(deviceDO.getSoftwareId());
        respVO.setExternalId(deviceDO.getExternalId());
        respVO.setVersion(deviceDO.getVersion());
        respVO.setExtCommon1(deviceDO.getExtCommon1());
        respVO.setExtCommon2(deviceDO.getExtCommon2());
        respVO.setExtCommon3(deviceDO.getExtCommon3());
        respVO.setExtCommon4(deviceDO.getExtCommon4());
        respVO.setCreateTime(deviceDO.getCreateTime());

        // 处理attributes字段：将JSON字符串转换为对象列表
        if (deviceDO.getAttributes() != null && !deviceDO.getAttributes().isEmpty()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                List<DeviceAttributeRespVO> attributes = mapper.readValue(
                        deviceDO.getAttributes(),
                        new TypeReference<List<DeviceAttributeRespVO>>() {}
                );
                respVO.setAttributes(attributes);
            } catch (Exception e) {
                log.warn("转换设备属性失败，设备ID: {}", deviceDO.getId(), e);
                respVO.setAttributes(new ArrayList<>());
            }
        } else {
            respVO.setAttributes(new ArrayList<>());
        }

        return respVO;
    }

}
