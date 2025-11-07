package cn.iocoder.yudao.module.smartcity.controller.admin.oflawenforcementpersonnel;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.oflawenforcementpersonnel.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.oflawenforcementpersonnel.OfLawEnforcementPersonnelDO;
import cn.iocoder.yudao.module.smartcity.service.oflawenforcementpersonnel.OfLawEnforcementPersonnelService;

@Tag(name = "管理后台 - 执法人员管理")
@RestController
@RequestMapping("/smartcity/of-law-enforcement-personnel")
@Validated
public class OfLawEnforcementPersonnelController {

    @Resource
    private OfLawEnforcementPersonnelService ofLawEnforcementPersonnelService;

    @PostMapping("/create")
    @Operation(summary = "创建执法人员管理")
    @PreAuthorize("@ss.hasPermission('smartcity:of-law-enforcement-personnel:create')")
    public CommonResult<Long> createOfLawEnforcementPersonnel(@Valid @RequestBody OfLawEnforcementPersonnelSaveReqVO createReqVO) {
        return success(ofLawEnforcementPersonnelService.createOfLawEnforcementPersonnel(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新执法人员管理")
    @PreAuthorize("@ss.hasPermission('smartcity:of-law-enforcement-personnel:update')")
    public CommonResult<Boolean> updateOfLawEnforcementPersonnel(@Valid @RequestBody OfLawEnforcementPersonnelSaveReqVO updateReqVO) {
        ofLawEnforcementPersonnelService.updateOfLawEnforcementPersonnel(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除执法人员管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:of-law-enforcement-personnel:delete')")
    public CommonResult<Boolean> deleteOfLawEnforcementPersonnel(@RequestParam("id") Long id) {
        ofLawEnforcementPersonnelService.deleteOfLawEnforcementPersonnel(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得执法人员管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:of-law-enforcement-personnel:query')")
    public CommonResult<OfLawEnforcementPersonnelRespVO> getOfLawEnforcementPersonnel(@RequestParam("id") Long id) {
        OfLawEnforcementPersonnelDO ofLawEnforcementPersonnel = ofLawEnforcementPersonnelService.getOfLawEnforcementPersonnel(id);
        return success(BeanUtils.toBean(ofLawEnforcementPersonnel, OfLawEnforcementPersonnelRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得执法人员管理分页")
    @PreAuthorize("@ss.hasPermission('smartcity:of-law-enforcement-personnel:query')")
    public CommonResult<PageResult<OfLawEnforcementPersonnelRespVO>> getOfLawEnforcementPersonnelPage(@Valid OfLawEnforcementPersonnelPageReqVO pageReqVO) {
        PageResult<OfLawEnforcementPersonnelDO> pageResult = ofLawEnforcementPersonnelService.getOfLawEnforcementPersonnelPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OfLawEnforcementPersonnelRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出执法人员管理 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:of-law-enforcement-personnel:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOfLawEnforcementPersonnelExcel(@Valid OfLawEnforcementPersonnelPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OfLawEnforcementPersonnelDO> list = ofLawEnforcementPersonnelService.getOfLawEnforcementPersonnelPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "执法人员管理.xls", "数据", OfLawEnforcementPersonnelRespVO.class,
                        BeanUtils.toBean(list, OfLawEnforcementPersonnelRespVO.class));
    }

}