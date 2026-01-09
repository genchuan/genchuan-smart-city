package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.riskview;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.riskview.vo.EmergRiskViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.riskview.vo.EmergRiskViewRespVO;

import cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.riskview.EmergRiskViewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 应急风险分布视图")
@RestController
@RequestMapping("/industry/emerg-risk-view")
@Validated
public class EmergRiskViewController {
    @Resource
    private EmergRiskViewService emergRiskViewService;

    @GetMapping("/get")
    @Operation(summary = "获得风险分布视图")
    @PreAuthorize("@ss.hasPermission('industry:emerg-risk-view:query')")
    public CommonResult<List<EmergRiskViewRespVO>> listEmergRiskView(
            @Valid EmergRiskViewQueryReqVO emergRiskViewQueryReqVO
    ) {
        List<EmergRiskViewRespVO> emergRiskViewRespVOList = emergRiskViewService.listEmergRiskView(emergRiskViewQueryReqVO);
        return success(emergRiskViewRespVOList);
    }


}
