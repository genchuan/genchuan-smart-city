package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.CollectConfigDO;
import cn.iocoder.yudao.module.ordertrade.service.debtcollect.CollectConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.UPDATE;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 管理后台 - 追缴配置
 *
 * @author genchuan
 */
@Tag(name = "订单交易 -联合追缴- 追缴配置")
@RestController
@RequestMapping("/ordertrade/collect-config")
@Validated
public class CollectConfigController {

    @Resource
    private CollectConfigService collectConfigService;

    // ==================== ① 标准CRUD ====================

    @PostMapping("/create")
    @Operation(summary = "创建追缴配置")
    public CommonResult<Long> createCollectConfig(@Valid @RequestBody CollectConfigSaveReqVO createReqVO) {
        return success(collectConfigService.createCollectConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新追缴配置")
    public CommonResult<Boolean> updateCollectConfig(@Valid @RequestBody CollectConfigSaveReqVO updateReqVO) {
        collectConfigService.updateCollectConfig(updateReqVO);
        return success(true);
    }

   /* @DeleteMapping("/delete")
    @Operation(summary = "删除追缴配置")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteCollectConfig(@RequestParam("id") Long id) {
        collectConfigService.deleteCollectConfig(id);
        return success(true);
    }*/

    @PutMapping("/save")
    @Operation(summary = "保存追缴配置")
    public CommonResult<Boolean> saveCollectConfig(@Valid @RequestBody CollectConfigSaveReqVO reqVO) {
        if (reqVO.getId() == null) {
            collectConfigService.createCollectConfig(reqVO);
        } else {
            collectConfigService.updateCollectConfig(reqVO);
        }
        return success(true);
    }

   /* @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除追缴配置")
    @Parameter(name = "ids", description = "主键列表", required = true)
    public CommonResult<Boolean> deleteCollectConfigList(@RequestParam("ids") List<Long> ids) {
        collectConfigService.deleteCollectConfigListByIds(ids);
        return success(true);
    }*/

    @GetMapping("/get")
    @Operation(summary = "获得追缴配置详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<CollectConfigRespVO> getCollectConfig(@RequestParam("id") Long id) {
        CollectConfigDO obj = collectConfigService.getCollectConfig(id);
        return success(BeanUtils.toBean(obj, CollectConfigRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得追缴配置分页列表")
    public CommonResult<PageResult<CollectConfigRespVO>> getCollectConfigPage(@Valid CollectConfigPageReqVO pageReqVO) {
        PageResult<CollectConfigDO> pageResult = collectConfigService.getCollectConfigPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CollectConfigRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出追缴配置 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCollectConfigExcel(@Valid CollectConfigPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CollectConfigDO> list = collectConfigService.getCollectConfigPage(pageReqVO).getList();
        ExcelUtils.write(response, "追缴配置.xls", "数据", CollectConfigRespVO.class,
                BeanUtils.toBean(list, CollectConfigRespVO.class));
    }

    // ==================== ② 业务操作接口 ====================

    @PutMapping("/enable")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "生效追缴配置")
    public CommonResult<Boolean> enableCollectConfig(@Valid @RequestBody IdReqVO reqVO) {
        collectConfigService.enableCollectConfig(reqVO);
        return success(true);
    }
    @PutMapping("/disable")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "禁用追缴配置")
    public CommonResult<Boolean> disableCollectConfig(@Valid @RequestBody IdReqVO reqVO) {
        collectConfigService.disableCollectConfig(reqVO);
        return success(true);
    }

    // ==================== ③ 数据可视化接口 ====================

    @GetMapping("/chart")
    @Operation(summary = "获得追缴配置统计图表数据（折线图+柱状图/饼图+卡片）")
    public CommonResult<CollectConfigChartRespVO> getCollectConfigChart(@Valid CollectConfigChartReqVO chartReqVO) {
        return success(collectConfigService.getCollectConfigChart(chartReqVO));
    }
}
