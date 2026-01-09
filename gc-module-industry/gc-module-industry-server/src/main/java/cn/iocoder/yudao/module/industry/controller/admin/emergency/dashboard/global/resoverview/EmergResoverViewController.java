package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.resoverview;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.resoverview.vo.EmergResoverDispatchReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.resoverview.vo.EmergResoverViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.resoverview.vo.EmergResoverViewRespVO;
import cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.resoverview.EmergResoverViewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 应急资源总览")
@RestController
@RequestMapping("/industry/emerg-resover-view")
@Validated
public class EmergResoverViewController {

    @Resource
    private EmergResoverViewService emergResoverViewService;

    @GetMapping("/list")
    @Operation(summary = "获得应急资源总览")
    @PreAuthorize("@ss.hasPermission('industry:emerg-resover-view:query')")
    public CommonResult<List<EmergResoverViewRespVO>> listEmergResoverView(
            @Valid EmergResoverViewQueryReqVO emergResoverViewQueryReqVO
    ) {
        List<EmergResoverViewRespVO> emergResoverViewRespVO = emergResoverViewService.listEmergResoverView(emergResoverViewQueryReqVO);
        return success(emergResoverViewRespVO);
    }

    @GetMapping("/dispatch")
    @Operation(summary = "调度应急资源")
    @PreAuthorize("@ss.hasPermission('industry:dispatch-emerg-resover:update')")
    public CommonResult<Boolean> dispatchEmergResover(
            @Valid EmergResoverDispatchReqVO emergResoverDispatchReqVO
            ) {
        emergResoverViewService.dispatchEmergResover(emergResoverDispatchReqVO);
        return success(true);
    }
}
