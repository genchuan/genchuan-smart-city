package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.overview;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.overview.vo.EmergOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.overview.vo.EmergOverviewRespVO;
import cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.overview.EmergOverviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 应急全域数据概览")
@RestController
@RequestMapping("/industry/emerg-overview")
@Validated
public class EmergOverviewController {
    @Resource
    private EmergOverviewService emergOverviewService;
    @GetMapping("/get")
    @Operation(summary = "获得应急全域数据概览")
    @PreAuthorize("@ss.hasPermission('industry:emerg-overview:query')")
    public CommonResult<EmergOverviewRespVO> getEmergOverview(
            @Valid EmergOverviewQueryReqVO emergOverviewQueryReqVO
    ) {
        EmergOverviewRespVO emergOverview = emergOverviewService.getEmergOverview(emergOverviewQueryReqVO);
        return success(emergOverview);
    }


}
