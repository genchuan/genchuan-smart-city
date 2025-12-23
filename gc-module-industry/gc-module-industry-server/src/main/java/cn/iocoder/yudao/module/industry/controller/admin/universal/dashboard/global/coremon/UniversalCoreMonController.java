package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremon;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremon.vo.UniversalCoreMonQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremon.vo.UniversalCoreMonRespVO;

import cn.iocoder.yudao.module.industry.service.universal.dashboard.global.coremon.UniversalCoreMonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 通用大屏核心要素运行监测")
@RestController
@RequestMapping("/industry/universal-core-mon")
@Validated
public class UniversalCoreMonController {
    @Resource
    private UniversalCoreMonService universalCoreMonService;

    @GetMapping("/get")
    @Operation(summary = "获得通用大屏核心要素运行监测")
    @PreAuthorize("@ss.hasPermission('industry:universal-core-mon:query')")
    public CommonResult<List<UniversalCoreMonRespVO>> listUniversalCoreMon(
            @Valid UniversalCoreMonQueryReqVO universalCoreMonQueryReqVO
    ) {
        List<UniversalCoreMonRespVO> universalCoreMonRespVO = universalCoreMonService.listUniversalCoreMon(universalCoreMonQueryReqVO);
        return success(universalCoreMonRespVO);
    }
}
