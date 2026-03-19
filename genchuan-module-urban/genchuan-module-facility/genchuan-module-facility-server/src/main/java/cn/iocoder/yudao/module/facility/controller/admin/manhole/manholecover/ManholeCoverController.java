package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover.vo.ManholeCoverDetailRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover.vo.ManholeCoverPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover.vo.ManholeCoverRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover.vo.ManholeCoverSaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholecover.ManholeCoverDO;
import cn.iocoder.yudao.module.facility.service.manhole.manholecover.ManholeCoverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 窨井盖设施")
@RestController
@RequestMapping("/manhole/cover")
@Validated
public class ManholeCoverController {

    @Resource
    private ManholeCoverService coverService;

    @PostMapping("/create")
    @Operation(summary = "创建窨井盖设施")
    @PreAuthorize("@ss.hasPermission('manhole:cover:create')")
    public CommonResult<Long> createCover(@Valid @RequestBody ManholeCoverSaveReqVO createReqVO) {
        return success(coverService.createCover(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新窨井盖设施")
    @PreAuthorize("@ss.hasPermission('manhole:cover:update')")
    public CommonResult<Boolean> updateCover(@Valid @RequestBody ManholeCoverSaveReqVO updateReqVO) {
        coverService.updateCover(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除窨井盖设施")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('manhole:cover:delete')")
    public CommonResult<Boolean> deleteCover(@RequestParam("id") Long id) {
        coverService.deleteCover(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得窨井盖设施")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('manhole:cover:query')")
    public CommonResult<ManholeCoverRespVO> getCover(@RequestParam("id") Long id) {
        ManholeCoverDO cover = coverService.getCover(id);
        return success(BeanUtils.toBean(cover, ManholeCoverRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得窨井盖设施分页")
    @PreAuthorize("@ss.hasPermission('manhole:cover:query')")
    public CommonResult<PageResult<ManholeCoverRespVO>> getCoverPage(@Valid ManholeCoverPageReqVO pageReqVO) {
        PageResult<ManholeCoverDO> pageResult = coverService.getCoverPage(pageReqVO);
        System.out.println(pageResult);
        return success(BeanUtils.toBean(pageResult, ManholeCoverRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出窨井盖设施 Excel")
    @PreAuthorize("@ss.hasPermission('manhole:cover:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCoverExcel(@Valid ManholeCoverPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ManholeCoverDO> list = coverService.getCoverPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "窨井盖设施.xls", "数据", ManholeCoverRespVO.class,
                        BeanUtils.toBean(list, ManholeCoverRespVO.class));
    }

    @GetMapping("/detail")
    @Operation(summary = "获得窨井盖设施详情（包含近 24 小时统计、故障记录）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('manhole:cover:query')")
    public CommonResult<ManholeCoverDetailRespVO> getCoverDetail(@RequestParam("id") Long id) {
        return success(coverService.getCoverDetail(id));
    }

}