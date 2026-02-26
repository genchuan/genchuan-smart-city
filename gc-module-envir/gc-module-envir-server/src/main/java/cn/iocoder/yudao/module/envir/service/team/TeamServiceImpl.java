package cn.iocoder.yudao.module.envir.service.team;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.team.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.team.TeamDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.team.TeamMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 班组 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TeamServiceImpl implements TeamService {

    @Resource
    private TeamMapper teamMapper;

    @Override
    public Long createTeam(TeamSaveReqVO createReqVO) {
        // 插入
        TeamDO team = BeanUtils.toBean(createReqVO, TeamDO.class);
        teamMapper.insert(team);
        // 返回
        return team.getId();
    }

    @Override
    public void updateTeam(TeamSaveReqVO updateReqVO) {
        // 校验存在
        validateTeamExists(updateReqVO.getId());
        // 更新
        TeamDO updateObj = BeanUtils.toBean(updateReqVO, TeamDO.class);
        teamMapper.updateById(updateObj);
    }

    @Override
    public void deleteTeam(Long id) {
        // 校验存在
        validateTeamExists(id);
        // 删除
        teamMapper.deleteById(id);
    }

    private void validateTeamExists(Long id) {
        if (teamMapper.selectById(id) == null) {
            throw exception(TEAM_NOT_EXISTS);
        }
    }

    @Override
    public TeamDO getTeam(Long id) {
        return teamMapper.selectById(id);
    }

    @Override
    public PageResult<TeamDO> getTeamPage(TeamPageReqVO pageReqVO) {
        return teamMapper.selectPage(pageReqVO);
    }

}