package cn.iocoder.yudao.module.park.controller.admin.park.user.participatingunit;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.participatingunit.vo.ParticipatingUnitPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.participatingunit.vo.ParticipatingUnitRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.participatingunit.vo.ParticipatingUnitSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.participatingunit.ParticipatingUnitDO;
import cn.iocoder.yudao.module.park.service.park.user.participatingunit.ParticipatingUnitService;
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


@Tag(name = "管理后台 - 参与单位")
@RestController
@RequestMapping("/park/participating-unit")
@Validated
public class ParticipatingUnitController {

    @Resource
    private ParticipatingUnitService participatingUnitService;

    @PostMapping("/create")
    @Operation(summary = "创建参与单位")
    @PreAuthorize("@ss.hasPermission('park:participating-unit:create')")
    public CommonResult<Long> createParticipatingUnit(@Valid @RequestBody ParticipatingUnitSaveReqVO createReqVO) {
        return success(participatingUnitService.createParticipatingUnit(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新参与单位")
    @PreAuthorize("@ss.hasPermission('park:participating-unit:update')")
    public CommonResult<Boolean> updateParticipatingUnit(@Valid @RequestBody ParticipatingUnitSaveReqVO updateReqVO) {
        participatingUnitService.updateParticipatingUnit(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除参与单位")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:participating-unit:delete')")
    public CommonResult<Boolean> deleteParticipatingUnit(@RequestParam("id") Long id) {
        participatingUnitService.deleteParticipatingUnit(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得参与单位")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:participating-unit:query')")
    public CommonResult<ParticipatingUnitRespVO> getParticipatingUnit(@RequestParam("id") Long id) {
        ParticipatingUnitDO participatingUnit = participatingUnitService.getParticipatingUnit(id);
        return success(BeanUtils.toBean(participatingUnit, ParticipatingUnitRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得参与单位分页")
    @PreAuthorize("@ss.hasPermission('park:participating-unit:query')")
    public CommonResult<PageResult<ParticipatingUnitRespVO>> getParticipatingUnitPage(@Valid ParticipatingUnitPageReqVO pageReqVO) {
        PageResult<ParticipatingUnitDO> pageResult = participatingUnitService.getParticipatingUnitPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParticipatingUnitRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出参与单位 Excel")
    @PreAuthorize("@ss.hasPermission('park:participating-unit:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParticipatingUnitExcel(@Valid ParticipatingUnitPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParticipatingUnitDO> list = participatingUnitService.getParticipatingUnitPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "参与单位.xls", "数据", ParticipatingUnitRespVO.class,
                        BeanUtils.toBean(list, ParticipatingUnitRespVO.class));
    }

}
