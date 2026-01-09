package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.overview;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.overview.vo.*;

import cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.overview.LawOverviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 执法全域数据概览")
@RestController
@RequestMapping("/industry/law-overview")
@Validated
public class LawOverviewController {
    @Resource
    private LawOverviewService lawOverviewService;
    @GetMapping("/get")
    @Operation(summary = "获得执法全域数据概览")
    @PreAuthorize("@ss.hasPermission('industry:law-overview:query')")
    public CommonResult<LawOverviewRespVO> getLawOverview(
            @Valid LawOverviewQueryReqVO lawOverviewQueryReqVO
    ) {
        LawOverviewRespVO lawOverviewRespVO = lawOverviewService.getLawOverview(lawOverviewQueryReqVO);
        return success(lawOverviewRespVO);
    }


    /**
     * 获取执法全域数据概览所使用的行政区列表（名称 + 地区码）
     */
    @GetMapping("/get-law-overview-area")
    @Operation(summary = "获得执法全域数据概览的行政区（名称和地区码）")
    @PreAuthorize("@ss.hasPermission('industry:law-overview-area:query')")
    public CommonResult<List<LawOverviewArea>> getLawOverviewArea() {

        // 调用服务层，查询所需的行政区列表
        List<LawOverviewArea> areaList = lawOverviewService.getLawOverviewArea();

        // 返回统一格式的响应
        return success(areaList);
    }


    /**
     * 获取行政区域列表（名称和地区码）
     *
     * 功能说明：
     * 1. 若未指定上级行政区（regionShortCode为空），返回所有省级行政区。
     * 2. 若指定省级行政区（regionShortCode末尾为"0000"），返回该省下的所有市级行政区（不包含省本身）。
     * 3. 若指定市级行政区（regionShortCode末尾为"00"但不为"0000"），返回该市下的所有县级行政区（不包含市本身）。
     * 4. 若指定县级行政区（其他情况），返回该县本身。
     *
     * 用途：
     * - 可用于前端行政区级联选择、数据筛选等场景。
     *
     * @param areaReqVO 行政区请求参数对象，包含上级行政区编码 regionShortCode
     * @return 返回指定条件下的行政区列表，每个行政区包含名称和地区码
     */
    @GetMapping("/get-all-area")
    @Operation(summary = "获取行政区域列表，可按上级行政区获取下级行政区")
    @PreAuthorize("@ss.hasPermission('industry:law-overview-all-area:query')")
    public CommonResult<List<AreaRespVO>> getAllArea(@Valid AreaReqVO areaReqVO) {

        // 调用服务层查询行政区列表
        List<AreaRespVO> areaList = lawOverviewService.getAllArea(areaReqVO);

        // 返回统一格式的响应
        return success(areaList);
    }


}
