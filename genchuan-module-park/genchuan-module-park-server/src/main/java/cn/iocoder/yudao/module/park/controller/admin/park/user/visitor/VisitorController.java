package cn.iocoder.yudao.module.park.controller.admin.park.user.visitor;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.visitor.vo.VisitorPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.visitor.vo.VisitorRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.visitor.vo.VisitorSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.visitor.VisitorDO;
import cn.iocoder.yudao.module.park.service.park.user.visitor.VisitorService;
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


@Tag(name = "管理后台 - 访客")
@RestController
@RequestMapping("/park/visitor")
@Validated
public class VisitorController {

    @Resource
    private VisitorService visitorService;

    @PostMapping("/create")
    @Operation(summary = "创建访客")
    @PreAuthorize("@ss.hasPermission('park:visitor:create')")
    public CommonResult<Long> createVisitor(@Valid @RequestBody VisitorSaveReqVO createReqVO) {
        return success(visitorService.createVisitor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新访客")
    @PreAuthorize("@ss.hasPermission('park:visitor:update')")
    public CommonResult<Boolean> updateVisitor(@Valid @RequestBody VisitorSaveReqVO updateReqVO) {
        visitorService.updateVisitor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除访客")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:visitor:delete')")
    public CommonResult<Boolean> deleteVisitor(@RequestParam("id") Long id) {
        visitorService.deleteVisitor(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得访客")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:visitor:query')")
    public CommonResult<VisitorRespVO> getVisitor(@RequestParam("id") Long id) {
        VisitorDO visitor = visitorService.getVisitor(id);
        return success(BeanUtils.toBean(visitor, VisitorRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得访客分页")
    @PreAuthorize("@ss.hasPermission('park:visitor:query')")
    public CommonResult<PageResult<VisitorRespVO>> getVisitorPage(@Valid VisitorPageReqVO pageReqVO) {
        PageResult<VisitorDO> pageResult = visitorService.getVisitorPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, VisitorRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出访客 Excel")
    @PreAuthorize("@ss.hasPermission('park:visitor:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportVisitorExcel(@Valid VisitorPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<VisitorDO> list = visitorService.getVisitorPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "访客.xls", "数据", VisitorRespVO.class,
                        BeanUtils.toBean(list, VisitorRespVO.class));
    }

}
