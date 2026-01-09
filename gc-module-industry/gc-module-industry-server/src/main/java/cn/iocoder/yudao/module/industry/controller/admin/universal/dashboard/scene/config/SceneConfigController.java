package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo.SceneConfigQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo.SceneConfigRespVO;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo.SceneConfigSaveReqVO;
import cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.config.SceneConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 场景大屏4-6-场景配置")
@RestController
@RequestMapping("/industry/scene_config")
@Validated
public class SceneConfigController {
    @Resource
    private SceneConfigService sceneConfigService;

    @GetMapping("/get/{sceneKey}")
    @Operation(summary = "4. 获取场景配置接口")
    @Parameter(name = "sceneKey", description = "场景唯一标识", required = true, example = "0101")
    @PreAuthorize("@ss.hasPermission('industry:scene_config:query')")
    public CommonResult<SceneConfigRespVO> getSceneConfig(
            @PathVariable String sceneKey
    ) {
        SceneConfigQueryReqVO sceneConfigQueryReqVO=new SceneConfigQueryReqVO();
        sceneConfigQueryReqVO.setSceneKey(sceneKey);
        SceneConfigRespVO sceneConfigRespVO = sceneConfigService.getSceneConfig(sceneConfigQueryReqVO);
        return success(sceneConfigRespVO);
    }

    // ========= 保存 =========

    @PostMapping("/save/{sceneKey}")
    @Operation(summary = "5. 保存场景配置接口")
    @Parameter(name = "sceneKey", description = "场景唯一标识", required = true, example = "0101")
    @PreAuthorize("@ss.hasPermission('industry:scene_config:save')")
    public CommonResult<Boolean> saveSceneConfig(
            @PathVariable String sceneKey,
            @Valid @RequestBody SceneConfigSaveReqVO reqVO
    ) {
        reqVO.setSceneKey(sceneKey); // 将路径参数赋值到请求对象
        sceneConfigService.saveSceneConfig(reqVO);
        return success(true);
    }



    //清除场景配置接口
    @DeleteMapping("/clear/{sceneKey}")
    @Operation(summary = "6. 清除场景配置接口")
    @Parameter(name = "sceneKey", description = "场景唯一标识", required = true, example = "0101")
    @PreAuthorize("@ss.hasPermission('industry:scene_config:delete')")
    public CommonResult<Boolean> clearSceneConfig(@PathVariable String sceneKey) {
        sceneConfigService.clearSceneConfig(sceneKey);
        return success(true);
    }

}
