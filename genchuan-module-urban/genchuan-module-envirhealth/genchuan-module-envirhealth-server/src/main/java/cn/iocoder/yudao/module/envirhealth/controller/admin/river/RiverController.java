package cn.iocoder.yudao.module.envirhealth.controller.admin.river;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.river.RiverPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.river.RiverRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.river.RiverSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.RiverDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.detail.RiverDetailDO;
import cn.iocoder.yudao.module.envirhealth.service.river.river.RiverService;
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
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "环境卫生管理 - 河道")
@RestController
@RequestMapping("/envirhealth/river")
@Validated
public class RiverController {

    @Resource
    private RiverService riverService;

    @PostMapping("/create")
    @Operation(summary = "创建河道")
    @PreAuthorize("@ss.hasPermission('envirhealth:river:create')")
    public CommonResult<Long> createRiver(@Valid @RequestBody RiverSaveReqVO createReqVO) {
        return success(riverService.createRiver(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新河道")
    @PreAuthorize("@ss.hasPermission('envirhealth:river:update')")
    public CommonResult<Boolean> updateRiver(@Valid @RequestBody RiverSaveReqVO updateReqVO) {
        riverService.updateRiver(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除河道")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:river:delete')")
    public CommonResult<Boolean> deleteRiver(@RequestParam("id") Long id) {
        riverService.deleteRiver(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得河道")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:river:query')")
    public CommonResult<RiverRespVO> getRiver(@RequestParam("id") Long id) {
        RiverDO river = riverService.getRiver(id);
        return success(BeanUtils.toBean(river, RiverRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得河道分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:river:query')")
    public CommonResult<PageResult<RiverRespVO>> getRiverPage(@Valid RiverPageReqVO pageReqVO) {
        PageResult<RiverDO> pageResult = riverService.getRiverPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RiverRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出河道 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:river:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRiverExcel(@Valid RiverPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RiverDO> list = riverService.getRiverPage(pageReqVO).getList();

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("河道_" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xls", "UTF-8"));
        response.setCharacterEncoding("UTF-8");

        // 导出 Excel
        ExcelUtils.write(response, "河道.xls", "数据", RiverRespVO.class,
                        BeanUtils.toBean(list, RiverRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得河道详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:river:query')")
    public CommonResult<PageResult<RiverDetailDO>> getRiverDetailPage(
            @Valid RiverPageReqVO pageReqVO) {
        PageResult<RiverDetailDO> pageResult =
                riverService.getRiverDetailPage(pageReqVO);

        return success(pageResult);
    }
}
