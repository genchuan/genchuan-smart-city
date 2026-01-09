package cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.keypop;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.keypop.vo.KeyPopQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.keypop.vo.KeyPopRespVO;

import cn.iocoder.yudao.module.industry.service.health.dashboard.global.keypop.KeyPopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 卫健重点人群健康视图")
@RestController
@RequestMapping("/industry/key-pop")
@Validated
public class KeyPopController {
    @Resource
    private KeyPopService keyPopService;
    @GetMapping("/get")
    @Operation(summary = "获得重点人群健康视图")
    @PreAuthorize("@ss.hasPermission('industry:key-pop:query')")
    public CommonResult<KeyPopRespVO> getKeyPop(
            @Valid KeyPopQueryReqVO keyPopQueryReqVO
    ) {
        KeyPopRespVO keyPopRespVO = keyPopService.getKeyPopOverview(keyPopQueryReqVO);
        return success(keyPopRespVO);
    }


}
