package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.caseview;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.caseview.vo.LawCaseViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.caseview.vo.LawCaseViewRespVO;

import cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.caseview.LawCaseViewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 执法案件总览")
@RestController
@RequestMapping("/industry/law-case-view")
@Validated
public class LawCaseViewController {
    @Resource
    private LawCaseViewService lawCaseViewService;
    @GetMapping("/get")
    @Operation(summary = "获得执法案件总览")
    @PreAuthorize("@ss.hasPermission('industry:law-case-view:query')")
    public CommonResult<LawCaseViewRespVO> getLawCaseView(
            @Valid LawCaseViewQueryReqVO lawCaseViewQueryReqVO
    ) {
        LawCaseViewRespVO lawCaseViewRespVO = lawCaseViewService.getLawCaseView(lawCaseViewQueryReqVO);
        return success(lawCaseViewRespVO);
    }

    /**
     * 获取执法案件的案件类型分类列表
     * 用于前端下拉展示或筛选条件
     */
    @GetMapping("/get-case-type")
    @Operation(summary = "获得执法案件的案件类型分类列表")
    @PreAuthorize("@ss.hasPermission('industry:law-case-view-type:query')")
    public CommonResult<List<String>> getLawCaseViewTypeList() {
        // 从业务层获取案件类型列表
        List<String> typeList = lawCaseViewService.getLawCaseViewTypeList();
        // 返回统一响应结果
        return success(typeList);
    }


    /**
     * 获取执法案件的案件来源列表
     * 用于前端筛选条件或统计展示
     */
    @GetMapping("/get-case-source")
    @Operation(summary = "获得执法案件 案件类型来源 列表")
    @PreAuthorize("@ss.hasPermission('industry:law-case-view-source:query')")
    public CommonResult<List<String>> getLawCaseViewSourceList() {
        // 从业务层获取案件来源列表
        List<String> sourceList = lawCaseViewService.getLawCaseViewSourceList();
        // 返回统一响应结果
        return success(sourceList);
    }


}
