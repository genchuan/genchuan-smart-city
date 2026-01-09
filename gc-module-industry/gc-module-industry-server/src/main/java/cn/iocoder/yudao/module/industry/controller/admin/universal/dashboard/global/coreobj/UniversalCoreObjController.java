package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coreobj;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coreobj.vo.UniversalCoreObjQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coreobj.vo.UniversalCoreObjRespVO;

import cn.iocoder.yudao.module.industry.service.universal.dashboard.global.coreobj.UniversalCoreObjService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 通用大屏核心对象分布视图")
@RestController
@RequestMapping("/industry/universal-core-obj")
@Validated
public class UniversalCoreObjController {
    @Resource
    private UniversalCoreObjService universalCoreObjService;

    @GetMapping("/list")
    @Operation(summary = "获得通用大屏核心对象分布视图")
    @PreAuthorize("@ss.hasPermission('industry:universal-core-obj:query')")
    public CommonResult<List<UniversalCoreObjRespVO>> listUniversalCoreObj(
            @Valid UniversalCoreObjQueryReqVO universalCoreObjQueryReqVO
    ) {
        List<UniversalCoreObjRespVO> universalCoreObjRespVO = universalCoreObjService.listUniversalCoreObj(universalCoreObjQueryReqVO);
        return success(universalCoreObjRespVO);
    }
}
