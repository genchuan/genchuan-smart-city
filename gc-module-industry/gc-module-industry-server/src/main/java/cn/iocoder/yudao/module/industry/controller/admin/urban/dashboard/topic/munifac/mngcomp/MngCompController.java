package cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.mngcomp;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.mngcomp.vo.MngCompQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.mngcomp.vo.MngCompRespVO;

import cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.mngcomp.MngCompService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 市政设施专题-管理部件")
@RestController
@RequestMapping("/industry/mng_comp")
@Validated
public class MngCompController {
    @Resource
    private MngCompService mngCompService;

    @GetMapping("/get")
    @Operation(summary = "获得市政设施专题-管理部件")
    @PreAuthorize("@ss.hasPermission('industry:mng_comp:query')")
    public CommonResult<MngCompRespVO> getMngComp(
            @Valid MngCompQueryReqVO mngCompQueryReqVO
    ) {
        MngCompRespVO mngCompRespVO = mngCompService.getMngComp(mngCompQueryReqVO);
        return success(mngCompRespVO);
    }
}
