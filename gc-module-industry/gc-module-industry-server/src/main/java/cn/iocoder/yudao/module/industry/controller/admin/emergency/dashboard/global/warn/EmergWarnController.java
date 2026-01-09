package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.warn;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.warn.vo.EmergWarnQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.warn.vo.EmergWarnRespVO;

import cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.warn.EmergWarnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 应急安全近期预警")
@RestController
@RequestMapping("/industry/emerg-warn")
@Validated
public class EmergWarnController {
    @Resource
    private EmergWarnService emergWarnService;
    @GetMapping("/get")
    @Operation(summary = "获得应急安全近期预警")
    @PreAuthorize("@ss.hasPermission('industry:emerg-warn:query')")
    public CommonResult<List<EmergWarnRespVO>> getEmergWarn(
            @Valid EmergWarnQueryReqVO emergWarnQueryReqVO
    ) {
        List<EmergWarnRespVO> emergWarnRespVOList = emergWarnService.getEmergWarn(emergWarnQueryReqVO);
        return success(emergWarnRespVOList);
    }


}
