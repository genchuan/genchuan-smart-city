package cn.iocoder.yudao.module.smartcity.controller.admin.enforcementpublicity;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.enforcementpublicity.vo.EnforcementPublicityPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.enforcementpublicity.vo.EnforcementPublicityRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.enforcementpublicity.vo.EnforcementPublicitySaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.enforcementpublicity.EnforcementPublicityDO;
import cn.iocoder.yudao.module.smartcity.service.enforcementpublicity.EnforcementPublicityService;
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

@Tag(name = "管理后台 - 执法公示")
@RestController
@RequestMapping("/smartcity/enforcement-publicity")
@Validated
public class EnforcementPublicityController {

    @Resource
    private EnforcementPublicityService enforcementPublicityService;

    @PostMapping("/create")
    @Operation(summary = "创建执法公示")
    @PreAuthorize("@ss.hasPermission('smartcity:enforcement-publicity:create')")
    public CommonResult<Long> createEnforcementPublicity(@Valid @RequestBody EnforcementPublicitySaveReqVO createReqVO) {
        return success(enforcementPublicityService.createEnforcementPublicity(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新执法公示")
    @PreAuthorize("@ss.hasPermission('smartcity:enforcement-publicity:update')")
    public CommonResult<Boolean> updateEnforcementPublicity(@Valid @RequestBody EnforcementPublicitySaveReqVO updateReqVO) {
        enforcementPublicityService.updateEnforcementPublicity(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除执法公示")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:enforcement-publicity:delete')")
    public CommonResult<Boolean> deleteEnforcementPublicity(@RequestParam("id") Long id) {
        enforcementPublicityService.deleteEnforcementPublicity(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得执法公示")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:enforcement-publicity:query')")
    public CommonResult<EnforcementPublicityRespVO> getEnforcementPublicity(@RequestParam("id") Long id) {
        EnforcementPublicityDO enforcementPublicity = enforcementPublicityService.getEnforcementPublicity(id);
        return success(BeanUtils.toBean(enforcementPublicity, EnforcementPublicityRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得执法公示分页")
    @PreAuthorize("@ss.hasPermission('smartcity:enforcement-publicity:query')")
    public CommonResult<PageResult<EnforcementPublicityRespVO>> getEnforcementPublicityPage(@Valid EnforcementPublicityPageReqVO pageReqVO) {
        PageResult<EnforcementPublicityDO> pageResult = enforcementPublicityService.getEnforcementPublicityPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EnforcementPublicityRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出执法公示 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:enforcement-publicity:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEnforcementPublicityExcel(@Valid EnforcementPublicityPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EnforcementPublicityDO> list = enforcementPublicityService.getEnforcementPublicityPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "执法公示.xls", "数据", EnforcementPublicityRespVO.class,
                        BeanUtils.toBean(list, EnforcementPublicityRespVO.class));
    }

}