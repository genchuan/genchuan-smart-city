package cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.AiAlertMessagePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.AiAlertMessageRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.AiAlertMessageSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.add.AddAiAlertMessageReq;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.aialertmessage.AiAlertMessageDO;
import cn.iocoder.yudao.module.kitchen.service.aialertmessage.AiAlertMessageService;
import cn.iocoder.yudao.module.stationresource.api.parking.ParkingSpaceInfoApi;
import cn.iocoder.yudao.module.stationresource.api.parking.dto.ParkingSpaceInfoRespDTO;
import cn.iocoder.yudao.module.stationresource.api.station.StationInfoApi;
import cn.iocoder.yudao.module.stationresource.api.station.dto.StationInfoRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - AI告警消息")
@RestController
@RequestMapping("/kitchen/ai-alert-message")
@Validated
public class AiAlertMessageController {
    @Resource
    private StationInfoApi stationInfoApi;      // 场站信息 RPC
    @Resource
    private ParkingSpaceInfoApi parkingSpaceInfoApi;  // 车位信息 RPC
    @Resource
    private AiAlertMessageService aiAlertMessageService;

    //TODO 模拟数据 后续接了三方后进行修改或者新建
    @PostMapping("/add")
    @Operation(summary = "(模拟）新增AI告警消息")
    //@PreAuthorize("@ss.hasPermission('kitchen:ai-alert-message:add')")
    public CommonResult<Long> addAiAlertMessage(@Valid @RequestBody AddAiAlertMessageReq reqVO) {
        Long id = aiAlertMessageService.addAiAlertMessage(reqVO);
        return success(id);
    }
    @PostMapping("/create")
    @Operation(summary = "(次级)创建AI告警消息")
    //@PreAuthorize("@ss.hasPermission('kitchen:ai-alert-message:create')")
    public CommonResult<Long> createAiAlertMessage(@Valid @RequestBody AiAlertMessageSaveReqVO createReqVO) {
        return success(aiAlertMessageService.createAiAlertMessage(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新AI告警消息")
    //@PreAuthorize("@ss.hasPermission('kitchen:ai-alert-message:update')")
    public CommonResult<Boolean> updateAiAlertMessage(@Valid @RequestBody AiAlertMessageSaveReqVO updateReqVO) {
        aiAlertMessageService.updateAiAlertMessage(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除AI告警消息")
    @Parameter(name = "id", description = "编号", required = true)
    //@PreAuthorize("@ss.hasPermission('kitchen:ai-alert-message:delete')")
    public CommonResult<Boolean> deleteAiAlertMessage(@RequestParam("id") Long id) {
        aiAlertMessageService.deleteAiAlertMessage(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得AI告警消息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('kitchen:ai-alert-message:query')")
    public CommonResult<AiAlertMessageRespVO> getAiAlertMessage(@RequestParam("id") Long id) {
        AiAlertMessageDO aiAlertMessage = aiAlertMessageService.getAiAlertMessage(id);
        return success(BeanUtils.toBean(aiAlertMessage, AiAlertMessageRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得AI告警消息分页")
    //@PreAuthorize("@ss.hasPermission('kitchen:ai-alert-message:query')")
    public CommonResult<PageResult<AiAlertMessageRespVO>> getAiAlertMessagePage(@Valid AiAlertMessagePageReqVO pageReqVO) {
        PageResult<AiAlertMessageRespVO> pageResult = aiAlertMessageService.getAiAlertMessagePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AiAlertMessageRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出AI告警消息 Excel")
    //@PreAuthorize("@ss.hasPermission('kitchen:ai-alert-message:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAiAlertMessageExcel(@Valid AiAlertMessagePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AiAlertMessageRespVO> list = aiAlertMessageService.getAiAlertMessagePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "AI告警消息.xls", "数据", AiAlertMessageRespVO.class,
                        BeanUtils.toBean(list, AiAlertMessageRespVO.class));
    }

    // ==================== 场站资源 RPC 测试接口 ====================

    @GetMapping("/test-station-rpc")
    @Operation(summary = "测试场站资源RPC调用——获取场站详情及车位信息")
    @Parameter(name = "stationId", description = "场站ID", required = true, example = "1")
    public CommonResult<StationInfoRespDTO> testStationRpc(@RequestParam("stationId") Long stationId) {
        // 调用场站信息 RPC
        StationInfoRespDTO station = stationInfoApi.getStation(stationId).getCheckedData();
        return success(station);
    }

    @GetMapping("/test-parking-rpc")
    @Operation(summary = "测试车位信息RPC调用——按ID获取车位")
    @Parameter(name = "spaceId", description = "车位ID", required = true, example = "1")
    public CommonResult<ParkingSpaceInfoRespDTO> testParkingRpc(@RequestParam("spaceId") Long spaceId) {
        // 调用车位信息 RPC
        ParkingSpaceInfoRespDTO space = parkingSpaceInfoApi.getSpace(spaceId).getCheckedData();
        return success(space);
    }

}
