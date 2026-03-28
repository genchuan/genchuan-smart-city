package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo.*;
import cn.iocoder.yudao.module.facility.service.manhole.manholewarn.ManholeCoverWarnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@RestController
@RequestMapping("manhole/warn")
@Tag(name = "窨井盖监测 - 预警处理管理")
public class ManholeCoverWarnController {

    @Resource
    private ManholeCoverWarnService manholeCoverWarnService;

    @GetMapping("/page")
    @Operation(summary = "窨井盖预警数据分页查询", description = "分页查询窨井盖各类预警信息，支持按井盖、区域、类型、状态、时间筛选")
    public CommonResult<PageResult<ManholeCoverWarnPageRespVO>> pageWarn(@Validated ManholeCoverWarnPageReqVO reqVO) {
        return success(manholeCoverWarnService.getWarnPage(reqVO));
    }

    @GetMapping("/detail/{warnId}")
    @Operation(summary = "井盖预警详情")
    public CommonResult<ManholeCoverWarnDetailRespVO> getWarnDetail(
            @PathVariable("warnId") String warnId
    ) {
        ManholeCoverWarnDetailReqVO reqVO = new ManholeCoverWarnDetailReqVO();
        reqVO.setWarnId(warnId);
        return success(manholeCoverWarnService.getWarnDetail(reqVO));
    }

    @Operation(summary = "异常联动报警触发", description = "根据井盖ID触发报警")
    @PostMapping("/trigger-alarm/{coverId}")
    public CommonResult<ManholeCoverWarnTriggerAlarmRespVO> triggerAlarm(
            @Schema(description = "窨井盖唯一ID", example = "c1d2e3f4-g5h6-7890-cdef-0123456789ab")
            @PathVariable String coverId,

            @RequestBody @Valid ManholeCoverWarnTriggerAlarmReqVO req) {
        return success(manholeCoverWarnService.triggerAlarm(coverId, req));
    }


    @PutMapping("/update-status/{warnId}")
    @Operation(summary = "更新窨井盖预警状态")
    public CommonResult<ManholeCoverWarnUpdateStatusRespVO> updateWarnStatus(
            @PathVariable String warnId,
            @Validated @RequestBody ManholeCoverWarnUpdateStatusReqVO reqVO) {

        ManholeCoverWarnUpdateStatusRespVO respVO = manholeCoverWarnService.updateWarnStatus(warnId, reqVO);

        return CommonResult.success(respVO);
    }

}
