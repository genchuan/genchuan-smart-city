package cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.faulthdl;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.faulthdl.vo.FaultHdlQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.faulthdl.vo.FaultHdlRespVO;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.faulthdl.vo.FaultHdlUpdateReqVO;
import cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.faulthdl.FaultHdlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 市政设施专题-标记故障处置")
@RestController
@RequestMapping("/industry/fault-hdl")
@Validated
public class FaultHdlController {
    @Resource
    private FaultHdlService faultHdlService;

    @GetMapping("/get")
    @Operation(summary = "获得市政设施专题-标记故障处置完成")
    @PreAuthorize("@ss.hasPermission('industry:fault-hdl:query')")
    public CommonResult<FaultHdlRespVO> getFaultHdl(
            @Valid FaultHdlQueryReqVO faultHdlQueryReqVO
    ) {
        FaultHdlRespVO faultHdlRespVO = faultHdlService.getFaultHdl(faultHdlQueryReqVO);
        return success(faultHdlRespVO);
    }

    @PutMapping("/mark-fault-handled")
    @Operation(summary = "市政设施专题-标记故障处置完成")
    @PreAuthorize("@ss.hasPermission('industry:mark-fault-handled:query')")
    public CommonResult<Boolean> getFaultHdl(
            @Valid FaultHdlUpdateReqVO faultHdlUpdateReqVO
            ) {
        faultHdlService.markFaultHandled(faultHdlUpdateReqVO);
        return success(true);
    }
}
