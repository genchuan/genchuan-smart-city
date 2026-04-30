package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationuser;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationuser.vo.StationUserPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationuser.vo.StationUserRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationuser.vo.StationUserSaveReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationuser.StationUserDO;
import cn.iocoder.yudao.module.stationresource.service.stationresource.stationuser.StationUserService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;


@Tag(name = "管理后台 - 站点用户")
@RestController
@RequestMapping("/stationresource/station-user")
@Validated
public class StationUserController {

    @Resource
    private StationUserService stationUserService;

    @PostMapping("/create")
    @Operation(summary = "创建站点用户")
    @PreAuthorize("@ss.hasPermission('stationresource:station-user:create')")
    public CommonResult<Long> createStationUser(@Valid @RequestBody StationUserSaveReqVO createReqVO) {
        return success(stationUserService.createStationUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新站点用户")
    @PreAuthorize("@ss.hasPermission('stationresource:station-user:update')")
    public CommonResult<Boolean> updateStationUser(@Valid @RequestBody StationUserSaveReqVO updateReqVO) {
        stationUserService.updateStationUser(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除站点用户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('stationresource:station-user:delete')")
    public CommonResult<Boolean> deleteStationUser(@RequestParam("id") Long id) {
        stationUserService.deleteStationUser(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除站点用户")
                @PreAuthorize("@ss.hasPermission('stationresource:station-user:delete')")
    public CommonResult<Boolean> deleteStationUserList(@RequestParam("ids") List<Long> ids) {
        stationUserService.deleteStationUserListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得站点用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:station-user:query')")
    public CommonResult<StationUserRespVO> getStationUser(@RequestParam("id") Long id) {
        StationUserDO stationUser = stationUserService.getStationUser(id);
        return success(BeanUtils.toBean(stationUser, StationUserRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得站点用户分页")
    @PreAuthorize("@ss.hasPermission('stationresource:station-user:query')")
    public CommonResult<PageResult<StationUserRespVO>> getStationUserPage(@Valid StationUserPageReqVO pageReqVO) {
        return success(stationUserService.getStationUserPage(pageReqVO));
    }

    @GetMapping("/export")
    @Operation(summary = "导出站点用户 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:station-user:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStationUserExcel(@Valid StationUserPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        // 0. 配置
        String inputFileName = "站点用户_";

        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StationUserRespVO> list = stationUserService.getStationUserPage(pageReqVO).getList();

        // 1、强制设置响应头，确保浏览器触发下载
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        // 2、动态生成文件名，带上当前日期
        String dateStr = java.time.LocalDate.now().toString();
        String fileOriginName = inputFileName + dateStr + ".xls";
        String fileName = URLEncoder.encode(fileOriginName, StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8","");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);

        // 3、调用 ExcelUtils 导出
        ExcelUtils.write(response, "站点用户.xls", "数据", StationUserRespVO.class, list);
    }

}
