package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo.SceneConfigQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo.SceneConfigRespVO;

import cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.config.SceneConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 获取场景配置接口")
@RestController
@RequestMapping("/industry/scene_config")
@Validated
public class SceneConfigController {
    @Resource
    private SceneConfigService sceneConfigService;

    @GetMapping("/get")
    @Operation(summary = "获得获取场景配置接口")
    @PreAuthorize("@ss.hasPermission('industry:scene_config:query')")
    public CommonResult<SceneConfigRespVO> getSceneConfig(
            @Valid SceneConfigQueryReqVO sceneConfigQueryReqVO
    ) {
        SceneConfigRespVO sceneConfigRespVO = sceneConfigService.getSceneConfig(sceneConfigQueryReqVO);
        return success(sceneConfigRespVO);
    }
}
