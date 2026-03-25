package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo.ManholeConfigPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo.ManholeConfigReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo.ManholeConfigRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo.ManholeConfigSaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholeconfig.ManholeConfigDO;
import cn.iocoder.yudao.module.facility.service.manhole.manholeconfig.ManholeConfigService;
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

@Tag(name = "管理后台 - 窨井盖监测配置")
@RestController
@RequestMapping("/manholeconfig/manhole-config")
@Validated
public class ManholeConfigController {

    @Resource
    private ManholeConfigService manholeConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建窨井盖监测配置")
    @PreAuthorize("@ss.hasPermission('manholeconfig:manhole-config:create')")
    public CommonResult<Long> createManholeConfig(@Valid @RequestBody ManholeConfigSaveReqVO createReqVO) {
        return success(manholeConfigService.createManholeConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新窨井盖监测配置")
    @PreAuthorize("@ss.hasPermission('manholeconfig:manhole-config:update')")
    public CommonResult<Boolean> updateManholeConfig(@Valid @RequestBody ManholeConfigSaveReqVO updateReqVO) {
        manholeConfigService.updateManholeConfig(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除窨井盖监测配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('manholeconfig:manhole-config:delete')")
    public CommonResult<Boolean> deleteManholeConfig(@RequestParam("id") Long id) {
        manholeConfigService.deleteManholeConfig(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得窨井盖监测配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('manholeconfig:manhole-config:query')")
    public CommonResult<ManholeConfigRespVO> getManholeConfig(@RequestParam("id") Long id) {
        ManholeConfigDO manholeConfig = manholeConfigService.getManholeConfig(id);
        return success(BeanUtils.toBean(manholeConfig, ManholeConfigRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得窨井盖监测配置分页")
    @PreAuthorize("@ss.hasPermission('manholeconfig:manhole-config:query')")
    public CommonResult<PageResult<ManholeConfigRespVO>> getManholeConfigPage(@Valid ManholeConfigPageReqVO pageReqVO) {
        PageResult<ManholeConfigDO> pageResult = manholeConfigService.getManholeConfigPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ManholeConfigRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出窨井盖监测配置 Excel")
    @PreAuthorize("@ss.hasPermission('manholeconfig:manhole-config:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportManholeConfigExcel(@Valid ManholeConfigPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ManholeConfigDO> list = manholeConfigService.getManholeConfigPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "窨井盖监测配置.xls", "数据", ManholeConfigRespVO.class,
                        BeanUtils.toBean(list, ManholeConfigRespVO.class));
    }

    /**
     * 保存监测配置
     */
    @PostMapping("/saveConfig")
    @Operation(summary = "保存监测配置", description = "新增/编辑窨井盖监测参数配置")
    public CommonResult<String> saveConfig(@Valid @RequestBody ManholeConfigReqVO configVO) {
        manholeConfigService.saveConfig(configVO);
        return CommonResult.success("配置保存成功！");
    }

}