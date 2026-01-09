package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.forceview;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.forceview.vo.LawForceViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.forceview.vo.LawForceViewRespVO;

import cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.forceview.LawForceViewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 执法力量分布视图")
@RestController
@RequestMapping("/industry/law-force-view")
@Validated
public class LawForceViewController {
    @Resource
    private LawForceViewService lawForceViewService;
    @GetMapping("/get")
    @Operation(summary = "获得执法力量分布视图")
    @PreAuthorize("@ss.hasPermission('industry:law-force-view:query')")
    public CommonResult<LawForceViewRespVO> getLawForceView(
            @Valid LawForceViewQueryReqVO lawForceViewQueryReqVO
    ) {
        LawForceViewRespVO lawForceViewRespVO = lawForceViewService.getLawForceView(lawForceViewQueryReqVO);
        return success(lawForceViewRespVO);
    }


}
