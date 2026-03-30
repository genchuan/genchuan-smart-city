package cn.iocoder.yudao.module.park.controller.admin.park.user.enterpriseinformation;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.enterpriseinformation.vo.EnterpriseInformationPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.enterpriseinformation.vo.EnterpriseInformationRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.enterpriseinformation.vo.EnterpriseInformationSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.enterpriseinformation.EnterpriseInformationDO;
import cn.iocoder.yudao.module.park.service.park.user.enterpriseinformation.EnterpriseInformationService;
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
@RequestMapping("/park/enterprise-information")
@Validated
public class EnterpriseInformationController {

    @Resource
    private EnterpriseInformationService enterpriseInformationService;

    @PostMapping("/create")
    @Operation(summary = "创建企业信息")
    @PreAuthorize("@ss.hasPermission('park:enterprise-information:create')")
    public CommonResult<Long> createEnterpriseInformation(@Valid @RequestBody EnterpriseInformationSaveReqVO createReqVO) {
        return success(enterpriseInformationService.createEnterpriseInformation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新企业信息")
    @PreAuthorize("@ss.hasPermission('park:enterprise-information:update')")
    public CommonResult<Boolean> updateEnterpriseInformation(@Valid @RequestBody EnterpriseInformationSaveReqVO updateReqVO) {
        enterpriseInformationService.updateEnterpriseInformation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除企业信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:enterprise-information:delete')")
    public CommonResult<Boolean> deleteEnterpriseInformation(@RequestParam("id") Long id) {
        enterpriseInformationService.deleteEnterpriseInformation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得企业信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:enterprise-information:query')")
    public CommonResult<EnterpriseInformationRespVO> getEnterpriseInformation(@RequestParam("id") Long id) {
        EnterpriseInformationDO enterpriseInformation = enterpriseInformationService.getEnterpriseInformation(id);
        return success(BeanUtils.toBean(enterpriseInformation, EnterpriseInformationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得企业信息分页")
    @PreAuthorize("@ss.hasPermission('park:enterprise-information:query')")
    public CommonResult<PageResult<EnterpriseInformationRespVO>> getEnterpriseInformationPage(@Valid EnterpriseInformationPageReqVO pageReqVO) {
        PageResult<EnterpriseInformationDO> pageResult = enterpriseInformationService.getEnterpriseInformationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EnterpriseInformationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出企业信息 Excel")
    @PreAuthorize("@ss.hasPermission('park:enterprise-information:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEnterpriseInformationExcel(@Valid EnterpriseInformationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EnterpriseInformationDO> list = enterpriseInformationService.getEnterpriseInformationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "企业信息.xls", "数据", EnterpriseInformationRespVO.class,
                        BeanUtils.toBean(list, EnterpriseInformationRespVO.class));
    }

}
