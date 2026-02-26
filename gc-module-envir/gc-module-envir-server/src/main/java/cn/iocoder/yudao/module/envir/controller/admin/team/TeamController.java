package cn.iocoder.yudao.module.envir.controller.admin.team;

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

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.envir.controller.admin.team.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.team.TeamDO;
import cn.iocoder.yudao.module.envir.service.team.TeamService;

@Tag(name = "管理后台 - 班组")
@RestController
@RequestMapping("/envir/team")
@Validated
public class TeamController {

    @Resource
    private TeamService teamService;

    @PostMapping("/create")
    @Operation(summary = "创建班组")
    @PreAuthorize("@ss.hasPermission('envir:team:create')")
    public CommonResult<Long> createTeam(@Valid @RequestBody TeamSaveReqVO createReqVO) {
        return success(teamService.createTeam(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新班组")
    @PreAuthorize("@ss.hasPermission('envir:team:update')")
    public CommonResult<Boolean> updateTeam(@Valid @RequestBody TeamSaveReqVO updateReqVO) {
        teamService.updateTeam(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除班组")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envir:team:delete')")
    public CommonResult<Boolean> deleteTeam(@RequestParam("id") Long id) {
        teamService.deleteTeam(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得班组")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envir:team:query')")
    public CommonResult<TeamRespVO> getTeam(@RequestParam("id") Long id) {
        TeamDO team = teamService.getTeam(id);
        return success(BeanUtils.toBean(team, TeamRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得班组分页")
    @PreAuthorize("@ss.hasPermission('envir:team:query')")
    public CommonResult<PageResult<TeamRespVO>> getTeamPage(@Valid TeamPageReqVO pageReqVO) {
        PageResult<TeamDO> pageResult = teamService.getTeamPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TeamRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出班组 Excel")
    @PreAuthorize("@ss.hasPermission('envir:team:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTeamExcel(@Valid TeamPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TeamDO> list = teamService.getTeamPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "班组.xls", "数据", TeamRespVO.class,
                        BeanUtils.toBean(list, TeamRespVO.class));
    }

}