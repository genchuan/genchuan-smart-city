package cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcement;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcement.vo.LawEnforcementPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcement.vo.LawEnforcementRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcement.vo.LawEnforcementSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.lawenforcement.LawEnforcementDO;
import cn.iocoder.yudao.module.smartcity.service.lawenforcement.LawEnforcementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 城市管理执法")
@RestController
@RequestMapping("/smartcity/law-enforcement")
@Validated
public class LawEnforcementController {

    @Resource
    private LawEnforcementService lawEnforcementService;

    @PostMapping("/create")
    @Operation(summary = "创建城市管理执法")
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement:create')")
    public CommonResult<Long> createLawEnforcement(@Valid @RequestBody LawEnforcementSaveReqVO createReqVO) {
        return success(lawEnforcementService.createLawEnforcement(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新城市管理执法")
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement:update')")
    public CommonResult<Boolean> updateLawEnforcement(@Valid @RequestBody LawEnforcementSaveReqVO updateReqVO) {
        lawEnforcementService.updateLawEnforcement(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除城市管理执法")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement:delete')")
    public CommonResult<Boolean> deleteLawEnforcement(@RequestParam("id") Long id) {
        lawEnforcementService.deleteLawEnforcement(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得城市管理执法")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement:query')")
    public CommonResult<LawEnforcementRespVO> getLawEnforcement(@RequestParam("id") Long id) {
        LawEnforcementDO lawEnforcement = lawEnforcementService.getLawEnforcement(id);
        return success(BeanUtils.toBean(lawEnforcement, LawEnforcementRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得城市管理执法分页")
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement:query')")
    public CommonResult<PageResult<LawEnforcementRespVO>> getLawEnforcementPage(@Valid LawEnforcementPageReqVO pageReqVO) {
        PageResult<LawEnforcementDO> pageResult = lawEnforcementService.getLawEnforcementPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, LawEnforcementRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出城市管理执法 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportLawEnforcementExcel(@Valid LawEnforcementPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<LawEnforcementDO> list = lawEnforcementService.getLawEnforcementPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "城市管理执法.xls", "数据", LawEnforcementRespVO.class,
                        BeanUtils.toBean(list, LawEnforcementRespVO.class));
    }

}