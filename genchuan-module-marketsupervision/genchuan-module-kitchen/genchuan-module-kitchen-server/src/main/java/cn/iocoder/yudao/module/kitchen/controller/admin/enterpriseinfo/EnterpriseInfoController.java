package cn.iocoder.yudao.module.kitchen.controller.admin.enterpriseinfo;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.enterpriseinfo.vo.EnterpriseInfoPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.enterpriseinfo.vo.EnterpriseInfoRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.enterpriseinfo.vo.EnterpriseInfoSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.enterpriseinfo.EnterpriseInfoDO;
import cn.iocoder.yudao.module.kitchen.service.enterpriseinfo.EnterpriseInfoService;
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


@Tag(name = "管理后台 - 企业信息")
@RestController
@RequestMapping("/kitchen/enterprise-info")
@Validated
public class EnterpriseInfoController {

    @Resource
    private EnterpriseInfoService enterpriseInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建企业信息")
    @PreAuthorize("@ss.hasPermission('kitchen:enterprise-info:create')")
    public CommonResult<Long> createEnterpriseInfo(@Valid @RequestBody EnterpriseInfoSaveReqVO createReqVO) {
        return success(enterpriseInfoService.createEnterpriseInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新企业信息")
    @PreAuthorize("@ss.hasPermission('kitchen:enterprise-info:update')")
    public CommonResult<Boolean> updateEnterpriseInfo(@Valid @RequestBody EnterpriseInfoSaveReqVO updateReqVO) {
        enterpriseInfoService.updateEnterpriseInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除企业信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('kitchen:enterprise-info:delete')")
    public CommonResult<Boolean> deleteEnterpriseInfo(@RequestParam("id") Long id) {
        enterpriseInfoService.deleteEnterpriseInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得企业信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('kitchen:enterprise-info:query')")
    public CommonResult<EnterpriseInfoRespVO> getEnterpriseInfo(@RequestParam("id") Long id) {
        EnterpriseInfoDO enterpriseInfo = enterpriseInfoService.getEnterpriseInfo(id);
        return success(BeanUtils.toBean(enterpriseInfo, EnterpriseInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得企业信息分页")
    @PreAuthorize("@ss.hasPermission('kitchen:enterprise-info:query')")
    public CommonResult<PageResult<EnterpriseInfoRespVO>> getEnterpriseInfoPage(@Valid EnterpriseInfoPageReqVO pageReqVO) {
        PageResult<EnterpriseInfoDO> pageResult = enterpriseInfoService.getEnterpriseInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EnterpriseInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出企业信息 Excel")
    @PreAuthorize("@ss.hasPermission('kitchen:enterprise-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEnterpriseInfoExcel(@Valid EnterpriseInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EnterpriseInfoDO> list = enterpriseInfoService.getEnterpriseInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "企业信息.xls", "数据", EnterpriseInfoRespVO.class,
                        BeanUtils.toBean(list, EnterpriseInfoRespVO.class));
    }

}
