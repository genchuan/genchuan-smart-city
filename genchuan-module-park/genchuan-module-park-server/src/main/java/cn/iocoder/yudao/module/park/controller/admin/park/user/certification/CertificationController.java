package cn.iocoder.yudao.module.park.controller.admin.park.user.certification;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.certification.vo.CertificationPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.certification.vo.CertificationRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.certification.vo.CertificationSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.certification.CertificationDO;
import cn.iocoder.yudao.module.park.service.park.user.certification.CertificationService;
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


@Tag(name = "管理后台 - 认证记录")
@RestController
@RequestMapping("/park/certification")
@Validated
public class CertificationController {

    @Resource
    private CertificationService certificationService;

    @PostMapping("/create")
    @Operation(summary = "创建认证记录")
    @PreAuthorize("@ss.hasPermission('park:certification:create')")
    public CommonResult<Long> createCertification(@Valid @RequestBody CertificationSaveReqVO createReqVO) {
        return success(certificationService.createCertification(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新认证记录")
    @PreAuthorize("@ss.hasPermission('park:certification:update')")
    public CommonResult<Boolean> updateCertification(@Valid @RequestBody CertificationSaveReqVO updateReqVO) {
        certificationService.updateCertification(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除认证记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:certification:delete')")
    public CommonResult<Boolean> deleteCertification(@RequestParam("id") Long id) {
        certificationService.deleteCertification(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得认证记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:certification:query')")
    public CommonResult<CertificationRespVO> getCertification(@RequestParam("id") Long id) {
        CertificationDO certification = certificationService.getCertification(id);
        return success(BeanUtils.toBean(certification, CertificationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得认证记录分页")
    @PreAuthorize("@ss.hasPermission('park:certification:query')")
    public CommonResult<PageResult<CertificationRespVO>> getCertificationPage(@Valid CertificationPageReqVO pageReqVO) {
        PageResult<CertificationDO> pageResult = certificationService.getCertificationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CertificationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出认证记录 Excel")
    @PreAuthorize("@ss.hasPermission('park:certification:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCertificationExcel(@Valid CertificationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CertificationDO> list = certificationService.getCertificationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "认证记录.xls", "数据", CertificationRespVO.class,
                        BeanUtils.toBean(list, CertificationRespVO.class));
    }

}
