package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.relatedriskident;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.relatedriskident.vo.RelatedRiskIdentPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.relatedriskident.vo.RelatedRiskIdentRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.relatedriskident.vo.RelatedRiskIdentSaveReqVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.relatedriskident.RelatedRiskIdentDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.traceability.relatedriskident.RelatedRiskIdentService;

@Tag(name = "管理后台 - 关联风险识别")
@RestController
@RequestMapping("/datacenter/related-risk-ident")
@Validated
public class RelatedRiskIdentController {

    @Resource
    private RelatedRiskIdentService relatedRiskIdentService;

    @PostMapping("/create")
    @Operation(summary = "创建关联风险识别")
    @PreAuthorize("@ss.hasPermission('datacenter:related-risk-ident:create')")
    public CommonResult<Long> createRelatedRiskIdent(@Valid @RequestBody RelatedRiskIdentSaveReqVO createReqVO) {
        return success(relatedRiskIdentService.createRelatedRiskIdent(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新关联风险识别")
    @PreAuthorize("@ss.hasPermission('datacenter:related-risk-ident:update')")
    public CommonResult<Boolean> updateRelatedRiskIdent(@Valid @RequestBody RelatedRiskIdentSaveReqVO updateReqVO) {
        relatedRiskIdentService.updateRelatedRiskIdent(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除关联风险识别")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:related-risk-ident:delete')")
    public CommonResult<Boolean> deleteRelatedRiskIdent(@RequestParam("id") Long id) {
        relatedRiskIdentService.deleteRelatedRiskIdent(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得关联风险识别")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:related-risk-ident:query')")
    public CommonResult<RelatedRiskIdentRespVO> getRelatedRiskIdent(@RequestParam("id") Long id) {
        RelatedRiskIdentDO relatedRiskIdent = relatedRiskIdentService.getRelatedRiskIdent(id);
        return success(BeanUtils.toBean(relatedRiskIdent, RelatedRiskIdentRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得关联风险识别分页")
    @PreAuthorize("@ss.hasPermission('datacenter:related-risk-ident:query')")
    public CommonResult<PageResult<RelatedRiskIdentRespVO>> getRelatedRiskIdentPage(@Valid RelatedRiskIdentPageReqVO pageReqVO) {
        PageResult<RelatedRiskIdentDO> pageResult = relatedRiskIdentService.getRelatedRiskIdentPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RelatedRiskIdentRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出关联风险识别 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:related-risk-ident:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRelatedRiskIdentExcel(@Valid RelatedRiskIdentPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RelatedRiskIdentDO> list = relatedRiskIdentService.getRelatedRiskIdentPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "关联风险识别.xls", "数据", RelatedRiskIdentRespVO.class,
                        BeanUtils.toBean(list, RelatedRiskIdentRespVO.class));
    }

}