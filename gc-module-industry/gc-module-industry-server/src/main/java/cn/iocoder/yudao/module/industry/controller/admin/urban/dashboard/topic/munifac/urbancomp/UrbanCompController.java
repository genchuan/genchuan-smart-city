package cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.urbancomp;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.urbancomp.vo.UrbanCompQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.urbancomp.vo.UrbanCompRespVO;

import cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.urbancomp.UrbanCompService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 市政设施专题-市政设施管理部件")
@RestController
@RequestMapping("/industry/urban_comp")
@Validated
public class UrbanCompController {
    @Resource
    private UrbanCompService urbanCompService;

    @GetMapping("/get")
    @Operation(summary = "获得市政设施专题-市政设施管理部件")
    @PreAuthorize("@ss.hasPermission('industry:urban_comp:query')")
    public CommonResult<UrbanCompRespVO> getUrbanComp(
            @Valid UrbanCompQueryReqVO urbanCompQueryReqVO
    ) {
        UrbanCompRespVO urbanCompRespVO = urbanCompService.getUrbanComp(urbanCompQueryReqVO);
        return success(urbanCompRespVO);
    }
}
