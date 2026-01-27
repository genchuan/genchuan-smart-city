package cn.iocoder.yudao.module.park.controller.admin.park.user.blackwhitelist;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.blackwhitelist.vo.BlackWhiteListPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.blackwhitelist.vo.BlackWhiteListRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.blackwhitelist.vo.BlackWhiteListSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.blackwhitelist.BlackWhiteListDO;
import cn.iocoder.yudao.module.park.service.park.user.blackwhitelist.BlackWhiteListService;
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


@Tag(name = "管理后台 - 黑白名单")
@RestController
@RequestMapping("/park/black-white-list")
@Validated
public class BlackWhiteListController {

    @Resource
    private BlackWhiteListService blackWhiteListService;

    @PostMapping("/create")
    @Operation(summary = "创建黑白名单")
    @PreAuthorize("@ss.hasPermission('park:black-white-list:create')")
    public CommonResult<Long> createBlackWhiteList(@Valid @RequestBody BlackWhiteListSaveReqVO createReqVO) {
        return success(blackWhiteListService.createBlackWhiteList(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新黑白名单")
    @PreAuthorize("@ss.hasPermission('park:black-white-list:update')")
    public CommonResult<Boolean> updateBlackWhiteList(@Valid @RequestBody BlackWhiteListSaveReqVO updateReqVO) {
        blackWhiteListService.updateBlackWhiteList(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除黑白名单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:black-white-list:delete')")
    public CommonResult<Boolean> deleteBlackWhiteList(@RequestParam("id") Long id) {
        blackWhiteListService.deleteBlackWhiteList(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得黑白名单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:black-white-list:query')")
    public CommonResult<BlackWhiteListRespVO> getBlackWhiteList(@RequestParam("id") Long id) {
        BlackWhiteListDO blackWhiteList = blackWhiteListService.getBlackWhiteList(id);
        return success(BeanUtils.toBean(blackWhiteList, BlackWhiteListRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得黑白名单分页")
    @PreAuthorize("@ss.hasPermission('park:black-white-list:query')")
    public CommonResult<PageResult<BlackWhiteListRespVO>> getBlackWhiteListPage(@Valid BlackWhiteListPageReqVO pageReqVO) {
        PageResult<BlackWhiteListDO> pageResult = blackWhiteListService.getBlackWhiteListPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BlackWhiteListRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出黑白名单 Excel")
    @PreAuthorize("@ss.hasPermission('park:black-white-list:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBlackWhiteListExcel(@Valid BlackWhiteListPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BlackWhiteListDO> list = blackWhiteListService.getBlackWhiteListPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "黑白名单.xls", "数据", BlackWhiteListRespVO.class,
                        BeanUtils.toBean(list, BlackWhiteListRespVO.class));
    }

}
