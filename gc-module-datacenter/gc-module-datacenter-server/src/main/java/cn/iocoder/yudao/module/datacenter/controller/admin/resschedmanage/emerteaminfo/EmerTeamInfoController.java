package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteaminfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteaminfo.vo.EmerTeamInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteaminfo.vo.EmerTeamInfoRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteaminfo.vo.EmerTeamInfoSaveReqVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteaminfo.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerteaminfo.EmerTeamInfoDO;
import cn.iocoder.yudao.module.datacenter.service.resschedmanage.emerteaminfo.EmerTeamInfoService;

@Tag(name = "管理后台 - 救援队伍信息")
@RestController
@RequestMapping("/datacenter/emer-team-info")
@Validated
public class EmerTeamInfoController {

    @Resource
    private EmerTeamInfoService emerTeamInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建救援队伍信息")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-team-info:create')")
    public CommonResult<Long> createEmerTeamInfo(@Valid @RequestBody EmerTeamInfoSaveReqVO createReqVO) {
        return success(emerTeamInfoService.createEmerTeamInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新救援队伍信息")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-team-info:update')")
    public CommonResult<Boolean> updateEmerTeamInfo(@Valid @RequestBody EmerTeamInfoSaveReqVO updateReqVO) {
        emerTeamInfoService.updateEmerTeamInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除救援队伍信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:emer-team-info:delete')")
    public CommonResult<Boolean> deleteEmerTeamInfo(@RequestParam("id") Long id) {
        emerTeamInfoService.deleteEmerTeamInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得救援队伍信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-team-info:query')")
    public CommonResult<EmerTeamInfoRespVO> getEmerTeamInfo(@RequestParam("id") Long id) {
        EmerTeamInfoDO emerTeamInfo = emerTeamInfoService.getEmerTeamInfo(id);
        return success(BeanUtils.toBean(emerTeamInfo, EmerTeamInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得救援队伍信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-team-info:query')")
    public CommonResult<PageResult<EmerTeamInfoRespVO>> getEmerTeamInfoPage(@Valid EmerTeamInfoPageReqVO pageReqVO) {
        PageResult<EmerTeamInfoDO> pageResult = emerTeamInfoService.getEmerTeamInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EmerTeamInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出救援队伍信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-team-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEmerTeamInfoExcel(@Valid EmerTeamInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EmerTeamInfoDO> list = emerTeamInfoService.getEmerTeamInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "救援队伍信息.xls", "数据", EmerTeamInfoRespVO.class,
                        BeanUtils.toBean(list, EmerTeamInfoRespVO.class));
    }

}