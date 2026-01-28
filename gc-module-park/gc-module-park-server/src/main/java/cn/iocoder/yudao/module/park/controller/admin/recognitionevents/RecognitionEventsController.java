package cn.iocoder.yudao.module.park.controller.admin.recognitionevents;

import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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

import java.time.LocalDateTime;
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

import cn.iocoder.yudao.module.park.controller.admin.recognitionevents.vo.*;
import cn.iocoder.yudao.module.park.dal.dataobject.recognitionevents.RecognitionEventsDO;
import cn.iocoder.yudao.module.park.service.recognitionevents.RecognitionEventsService;

@Tag(name = "管理后台 - 车牌识别事件")
@RestController
@RequestMapping("/park/recognition-events")
@Validated
public class RecognitionEventsController {

    @Resource
    private RecognitionEventsService recognitionEventsService;

    private static final Logger log = LoggerFactory.getLogger(RecognitionEventsController.class);

    @PostMapping("/create")
    @Operation(summary = "创建车牌识别事件")
    @PreAuthorize("@ss.hasPermission('park:recognition-events:create')")
    public CommonResult<Long> createRecognitionEvents(@Valid @RequestBody RecognitionEventsSaveReqVO createReqVO) {
        return success(recognitionEventsService.createRecognitionEvents(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新车牌识别事件")
    @PreAuthorize("@ss.hasPermission('park:recognition-events:update')")
    public CommonResult<Boolean> updateRecognitionEvents(@Valid @RequestBody RecognitionEventsSaveReqVO updateReqVO) {
        recognitionEventsService.updateRecognitionEvents(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车牌识别事件")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:recognition-events:delete')")
    public CommonResult<Boolean> deleteRecognitionEvents(@RequestParam("id") Long id) {
        recognitionEventsService.deleteRecognitionEvents(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车牌识别事件")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:recognition-events:query')")
    public CommonResult<RecognitionEventsRespVO> getRecognitionEvents(@RequestParam("id") Long id) {
        RecognitionEventsDO recognitionEvents = recognitionEventsService.getRecognitionEvents(id);
        return success(BeanUtils.toBean(recognitionEvents, RecognitionEventsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车牌识别事件分页")
    @PreAuthorize("@ss.hasPermission('park:recognition-events:query')")
    public CommonResult<PageResult<RecognitionEventsRespVO>> getRecognitionEventsPage(@Valid RecognitionEventsPageReqVO pageReqVO) {
        PageResult<RecognitionEventsDO> pageResult = recognitionEventsService.getRecognitionEventsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RecognitionEventsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车牌识别事件 Excel")
    @PreAuthorize("@ss.hasPermission('park:recognition-events:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRecognitionEventsExcel(@Valid RecognitionEventsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RecognitionEventsDO> list = recognitionEventsService.getRecognitionEventsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "车牌识别事件.xls", "数据", RecognitionEventsRespVO.class,
                        BeanUtils.toBean(list, RecognitionEventsRespVO.class));
    }
    @PostMapping("/receive-event")
    @Operation(summary = "统一接收设备所有事件")
    public ResponseEntity<String> receiveDeviceEvent(HttpServletRequest request) {

        // 强制设置租户ID为1
        TenantContextHolder.setTenantId(1L);

        // 1. 获取所有请求参数
        Map<String, String> params = getRequestParams(request);

        // 2. 验证必要参数
        if (!params.containsKey("type") || !params.containsKey("mode")) {
            return errorResponse(1, "缺少必要参数");
        }

        // 3. 根据事件类型分发处理
        String eventType = params.get("type");
        switch (eventType) {
            case "online": // 车牌识别事件
            case "offline":
                return handleRecognitionEvent(params);

            case "heartbeat": // 心跳事件
                return handleHeartbeatEvent(params);

            case "ioinput": // IO事件
                return handleIoEvent(params);

            default:
                return errorResponse(2, "未知事件类型: " + eventType);
        }
    }

    private ResponseEntity<String> handleRecognitionEvent(Map<String, String> params) {
        try {
            // 创建VO对象并设置字段值
            RecognitionEventsSaveReqVO createReqVO = new RecognitionEventsSaveReqVO();

            // 设置必填字段
            createReqVO.setType(params.get("type"));
            createReqVO.setMode(Integer.parseInt(params.getOrDefault("mode", "5")));
            createReqVO.setProtoVer(params.getOrDefault("proto_ver", "1.0"));
            createReqVO.setPlateNum(params.get("plate_num"));
            createReqVO.setPlateVal(Boolean.parseBoolean(params.getOrDefault("plate_val", "false")));
            createReqVO.setStartTime(Integer.parseInt(params.getOrDefault("start_time", "0")));
            createReqVO.setParkId(params.get("park_id"));
            createReqVO.setCamId(params.get("cam_id"));
            createReqVO.setCamIp(params.get("cam_ip"));
            createReqVO.setVdcType(params.getOrDefault("vdc_type", "in"));
            createReqVO.setIsWhitelist(Boolean.parseBoolean(params.getOrDefault("is_whitelist", "false")));
            createReqVO.setTrigerType(params.getOrDefault("triger_type", "video"));

            // 设置可选字段（带空值检查和类型转换）
            createReqVO.setPlateColor(params.get("plate_color"));

            if (params.containsKey("confidence") && params.get("confidence") != null) {
                try {
                    createReqVO.setConfidence(Integer.parseInt(params.get("confidence")));
                } catch (NumberFormatException e) {
                    log.warn("置信度格式错误: {}", params.get("confidence"));
                    createReqVO.setConfidence(null);
                }
            } else {
                createReqVO.setConfidence(null);
            }

            createReqVO.setCarLogo(params.get("car_logo"));
            createReqVO.setCarSublogo(params.get("car_sublogo"));
            createReqVO.setCarColor(params.get("car_color"));
            createReqVO.setVehicleType(params.get("vehicle_type"));

            if (params.containsKey("encrypt_verify") && params.get("encrypt_verify") != null) {
                createReqVO.setEncryptVerify(Boolean.parseBoolean(params.get("encrypt_verify")));
            } else {
                createReqVO.setEncryptVerify(null);
            }

            createReqVO.setPicture(params.get("picture"));
            createReqVO.setCloseupPic(params.get("closeup_pic"));

            // 设置创建时间为当前时间
            createReqVO.setCreatedAt(LocalDateTime.now());

            // 调用Service保存数据
            recognitionEventsService.createRecognitionEvents(createReqVO);

            return successResponse();
        } catch (Exception e) {
            log.error("处理识别事件失败", e);
            return errorResponse(3, "处理识别事件失败: " + e.getMessage());
        }
    }

    private ResponseEntity<String> handleHeartbeatEvent(Map<String, String> params) {
        // 记录心跳日志
        log.info("接收到心跳: cam_id={}, time={}",
                params.get("cam_id"), LocalDateTime.now());
        return successResponse();
    }

    private ResponseEntity<String> handleIoEvent(Map<String, String> params) {
        // 处理IO事件
        log.info("接收到IO事件: ionum={}, status={}",
                params.get("ionum"), params.get("iostatus"));
        return successResponse();
    }

    private Map<String, String> getRequestParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            params.put(paramName, request.getParameter(paramName));
        }
        return params;
    }

    private ResponseEntity<String> successResponse() {
        return ResponseEntity.ok()
                .header("Content-Type", "application/json;charset=utf-8")
                .body("{\"error_num\":0,\"error_str\":\"noerror\"}");
    }

    private ResponseEntity<String> errorResponse(int errorNum, String errorStr) {
        String json = String.format("{\"error_num\":%d,\"error_str\":\"%s\"}", errorNum, errorStr);
        return ResponseEntity.status(500)
                .header("Content-Type", "application/json;charset=utf-8")
                .body(json);
    }

}