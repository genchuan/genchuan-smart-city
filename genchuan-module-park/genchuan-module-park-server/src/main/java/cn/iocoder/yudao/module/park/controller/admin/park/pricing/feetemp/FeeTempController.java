package cn.iocoder.yudao.module.park.controller.admin.park.pricing.feetemp;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.feetemp.vo.FeeTempPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.feetemp.vo.FeeTempRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.feetemp.vo.FeeTempSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.feetemp.FeeTempDO;
import cn.iocoder.yudao.module.park.service.park.pricing.feetemp.FeeTempService;
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


@Tag(name = "管理后台 - 临停收费规则")
@RestController
@RequestMapping("/park/fee-temp")
@Validated
public class FeeTempController {

    @Resource
    private FeeTempService feeTempService;

    @PostMapping("/create")
    @Operation(summary = "创建临停收费规则")
    @PreAuthorize("@ss.hasPermission('park:fee-temp:create')")
    public CommonResult<Long> createFeeTemp(@Valid @RequestBody FeeTempSaveReqVO createReqVO) {
        return success(feeTempService.createFeeTemp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新临停收费规则")
    @PreAuthorize("@ss.hasPermission('park:fee-temp:update')")
    public CommonResult<Boolean> updateFeeTemp(@Valid @RequestBody FeeTempSaveReqVO updateReqVO) {
        feeTempService.updateFeeTemp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除临停收费规则")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:fee-temp:delete')")
    public CommonResult<Boolean> deleteFeeTemp(@RequestParam("id") Long id) {
        feeTempService.deleteFeeTemp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得临停收费规则")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:fee-temp:query')")
    public CommonResult<FeeTempRespVO> getFeeTemp(@RequestParam("id") Long id) {
        FeeTempDO feeTemp = feeTempService.getFeeTemp(id);
        return success(BeanUtils.toBean(feeTemp, FeeTempRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得临停收费规则分页")
    @PreAuthorize("@ss.hasPermission('park:fee-temp:query')")
    public CommonResult<PageResult<FeeTempRespVO>> getFeeTempPage(@Valid FeeTempPageReqVO pageReqVO) {
        PageResult<FeeTempDO> pageResult = feeTempService.getFeeTempPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, FeeTempRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出临停收费规则 Excel")
    @PreAuthorize("@ss.hasPermission('park:fee-temp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFeeTempExcel(@Valid FeeTempPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FeeTempDO> list = feeTempService.getFeeTempPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "临停收费规则.xls", "数据", FeeTempRespVO.class,
                        BeanUtils.toBean(list, FeeTempRespVO.class));
    }

}
