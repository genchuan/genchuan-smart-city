package cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementsupervision;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementsupervision.vo.LawEnforcementSupervisionPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementsupervision.vo.LawEnforcementSupervisionRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementsupervision.vo.LawEnforcementSupervisionSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.lawenforcementsupervision.LawEnforcementSupervisionDO;
import cn.iocoder.yudao.module.smartcity.service.lawenforcementsupervision.LawEnforcementSupervisionService;
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

@Tag(name = "管理后台 - 执法监督")
@RestController
@RequestMapping("/smartcity/law-enforcement-supervision")
@Validated
public class LawEnforcementSupervisionController {

    @Resource
    private LawEnforcementSupervisionService lawEnforcementSupervisionService;

    @PostMapping("/create")
    @Operation(summary = "创建执法监督")
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement-supervision:create')")
    public CommonResult<Long> createLawEnforcementSupervision(@Valid @RequestBody LawEnforcementSupervisionSaveReqVO createReqVO) {
        return success(lawEnforcementSupervisionService.createLawEnforcementSupervision(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新执法监督")
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement-supervision:update')")
    public CommonResult<Boolean> updateLawEnforcementSupervision(@Valid @RequestBody LawEnforcementSupervisionSaveReqVO updateReqVO) {
        lawEnforcementSupervisionService.updateLawEnforcementSupervision(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除执法监督")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement-supervision:delete')")
    public CommonResult<Boolean> deleteLawEnforcementSupervision(@RequestParam("id") Long id) {
        lawEnforcementSupervisionService.deleteLawEnforcementSupervision(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得执法监督")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement-supervision:query')")
    public CommonResult<LawEnforcementSupervisionRespVO> getLawEnforcementSupervision(@RequestParam("id") Long id) {
        LawEnforcementSupervisionDO lawEnforcementSupervision = lawEnforcementSupervisionService.getLawEnforcementSupervision(id);
        return success(BeanUtils.toBean(lawEnforcementSupervision, LawEnforcementSupervisionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得执法监督分页")
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement-supervision:query')")
    public CommonResult<PageResult<LawEnforcementSupervisionRespVO>> getLawEnforcementSupervisionPage(@Valid LawEnforcementSupervisionPageReqVO pageReqVO) {
        PageResult<LawEnforcementSupervisionDO> pageResult = lawEnforcementSupervisionService.getLawEnforcementSupervisionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, LawEnforcementSupervisionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出执法监督 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement-supervision:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportLawEnforcementSupervisionExcel(@Valid LawEnforcementSupervisionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<LawEnforcementSupervisionDO> list = lawEnforcementSupervisionService.getLawEnforcementSupervisionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "执法监督.xls", "数据", LawEnforcementSupervisionRespVO.class,
                        BeanUtils.toBean(list, LawEnforcementSupervisionRespVO.class));
    }

}