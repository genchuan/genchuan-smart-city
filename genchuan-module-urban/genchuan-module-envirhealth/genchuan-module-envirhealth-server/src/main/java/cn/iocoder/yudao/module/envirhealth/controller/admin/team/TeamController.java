package cn.iocoder.yudao.module.envirhealth.controller.admin.team;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.team.vo.TeamPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.team.vo.TeamRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.team.vo.TeamSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.team.TeamDO;
import cn.iocoder.yudao.module.envirhealth.service.team.TeamService;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
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

@Tag(name = "字典表 - 班组")
@RestController
@RequestMapping("/envirhealth/team")
@Validated
public class TeamController {

    @Resource
    private TeamService teamService;

    @PostMapping("/create")
    @Operation(summary = "创建班组")
    @PreAuthorize("@ss.hasPermission('envirhealth:team:create')")
    public CommonResult<Long> createTeam(@Valid @RequestBody TeamSaveReqVO createReqVO) {
        return success(teamService.createTeam(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新班组")
    @PreAuthorize("@ss.hasPermission('envirhealth:team:update')")
    public CommonResult<Boolean> updateTeam(@Valid @RequestBody TeamSaveReqVO updateReqVO) {
        teamService.updateTeam(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除班组")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:team:delete')")
    public CommonResult<Boolean> deleteTeam(@RequestParam("id") Long id) {
        teamService.deleteTeam(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得班组")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:team:query')")
    public CommonResult<TeamRespVO> getTeam(@RequestParam("id") Long id) {
        TeamDO team = teamService.getTeam(id);
        return success(BeanUtils.toBean(team, TeamRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得班组分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:team:query')")
    public CommonResult<PageResult<TeamRespVO>> getTeamPage(@Valid TeamPageReqVO pageReqVO) {
        PageResult<TeamDO> pageResult = teamService.getTeamPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TeamRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出班组 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:team:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTeamExcel(@Valid TeamPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TeamDO> list = teamService.getTeamPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "班组.xls", "数据", TeamRespVO.class,
                        BeanUtils.toBean(list, TeamRespVO.class));
    }

    /**
     * 获得班组下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得班组(下拉框)")
    @PreAuthorize("@ss.hasPermission('health:team:query')")
    public CommonResult<List<OptionVO>> getTeamOptions() {
        return success(teamService.getTeamOptions());
    }

}
