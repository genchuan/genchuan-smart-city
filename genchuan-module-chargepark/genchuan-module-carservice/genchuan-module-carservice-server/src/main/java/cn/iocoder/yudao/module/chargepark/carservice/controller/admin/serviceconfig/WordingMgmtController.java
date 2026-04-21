package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo.WordingMgmtBatchSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo.WordingMgmtChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo.WordingMgmtCreateReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo.WordingMgmtPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo.WordingMgmtRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo.WordingMgmtSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo.WordingMgmtUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.serviceconfig.WordingMgmtDO;
import cn.iocoder.yudao.module.chargepark.carservice.service.decision.ServiceOpReportService;
import cn.iocoder.yudao.module.chargepark.carservice.service.serviceconfig.WordingMgmtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.CREATE;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.UPDATE;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "客服配置 - 话术管理")
@RestController
@RequestMapping("/carservice/wording-mgmt")
@Validated
public class WordingMgmtController {

    @Resource
    private WordingMgmtService wordingMgmtService;

    @Resource
    private ServiceOpReportService serviceOpReportService;

    @GetMapping("/page")
    @Operation(summary = "筛选/刷新 话术管理")
    @PreAuthorize("@ss.hasPermission('carservice:wording-mgmt:query')")
    public CommonResult<PageResult<WordingMgmtRespVO>> getWordingMgmtPage(@Valid WordingMgmtPageReqVO pageReqVO) {
        PageResult<WordingMgmtDO> pageResult = wordingMgmtService.getWordingMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WordingMgmtRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "详情 - 话术管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('carservice:wording-mgmt:query')")
    public CommonResult<WordingMgmtRespVO> getWordingMgmt(@RequestParam("id") Long id) {
        WordingMgmtDO wordingMgmt = wordingMgmtService.getWordingMgmt(id);
        return success(BeanUtils.toBean(wordingMgmt, WordingMgmtRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "新增 - 话术")
    @PreAuthorize("@ss.hasPermission('carservice:wording-mgmt:create')")
    @ApiAccessLog(operateType = CREATE)
    public CommonResult<Long> createWordingMgmt(@Valid @RequestBody WordingMgmtCreateReqVO reqVO) {
        // controller 层字段映射:从严格的 CreateReqVO 转到内部通用 SaveReqVO
        WordingMgmtSaveReqVO save = new WordingMgmtSaveReqVO();
        save.setName(reqVO.getName());
        save.setContent(reqVO.getContent());
        save.setType(reqVO.getType());
        return success(wordingMgmtService.createWordingMgmt(save));
    }

    @PutMapping("/update")
    @Operation(summary = "编辑 - 话术")
    @PreAuthorize("@ss.hasPermission('carservice:wording-mgmt:update')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> updateWordingMgmt(@Valid @RequestBody WordingMgmtUpdateReqVO reqVO) {
        WordingMgmtSaveReqVO save = new WordingMgmtSaveReqVO();
        save.setId(reqVO.getId());
        save.setName(reqVO.getName());
        save.setContent(reqVO.getContent());
        save.setType(reqVO.getType());
        wordingMgmtService.updateWordingMgmt(save);
        return success(true);
    }

    @PutMapping("/row-update")
    @Operation(summary = "编辑(列表行) - 同列表页编辑接口")
    @PreAuthorize("@ss.hasPermission('carservice:wording-mgmt:update')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> rowUpdateWordingMgmt(@Valid @RequestBody WordingMgmtUpdateReqVO reqVO) {
        return updateWordingMgmt(reqVO);
    }

    @GetMapping("/check-name-unique")
    @Operation(summary = "校验话术名称唯一性 - 03 文档新增弹窗要求")
    @PreAuthorize("@ss.hasPermission('carservice:wording-mgmt:query')")
    public CommonResult<Boolean> checkWordingMgmtNameUnique(
            @RequestParam("name") String name,
            @RequestParam(value = "id", required = false) Long id) {
        boolean unique = wordingMgmtService.checkNameUnique(name, id);
        return success(unique);
    }

    @PutMapping("/save")
    @Operation(summary = "批量保存 - 表格内联编辑的新增/编辑一次性提交",
            description = "前端允许在表格里同时新增多行 + 编辑多行,点\"保存\"一次性 POST 整个列表。" +
                    "items 内单条有 id = 编辑(走 updateWordingMgmt),无 id = 新增(走 createWordingMgmt)。" +
                    "两遍扫描:先整体 JSR-303 校验(含 @InDict 字典校验),全部通过后才进入写入阶段;" +
                    "写入阶段整体 @Transactional,任何一条失败全部回滚。")
    @PreAuthorize("@ss.hasPermission('carservice:wording-mgmt:save')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> saveWordingMgmt(@Valid @RequestBody WordingMgmtBatchSaveReqVO reqVO) {
        wordingMgmtService.batchSaveWordingMgmt(reqVO);
        return success(true);
    }

    @PutMapping("/enable")
    @Operation(summary = "生效 - 未生效 → 已生效")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:wording-mgmt:enable')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> enableWordingMgmt(@RequestParam("id") Long id) {
        wordingMgmtService.enableWordingMgmt(id);
        return success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用 - 已生效 → 未生效")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:wording-mgmt:disable')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> disableWordingMgmt(@RequestParam("id") Long id) {
        wordingMgmtService.disableWordingMgmt(id);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "话术管理统计图表 - 饼图+卡片")
    @PreAuthorize("@ss.hasPermission('carservice:wording-mgmt:query')")
    public CommonResult<WordingMgmtChartRespVO> getWordingMgmtChart() {
        return success(serviceOpReportService.chartWordingMgmt());
    }

    @GetMapping("/chart-drill-pie")
    @Operation(summary = "各类型话术统计(饼图钻取) - 同 page 接口")
    @PreAuthorize("@ss.hasPermission('carservice:wording-mgmt:query')")
    public CommonResult<PageResult<WordingMgmtRespVO>> drillWordingMgmtPie(@Valid WordingMgmtPageReqVO pageReqVO) {
        PageResult<WordingMgmtDO> pageResult = wordingMgmtService.getWordingMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WordingMgmtRespVO.class));
    }

}
