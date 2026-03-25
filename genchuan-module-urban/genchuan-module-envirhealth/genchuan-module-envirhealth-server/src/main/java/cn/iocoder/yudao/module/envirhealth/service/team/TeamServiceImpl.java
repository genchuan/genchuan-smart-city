package cn.iocoder.yudao.module.envirhealth.service.team;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.team.vo.TeamPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.team.vo.TeamSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.team.TeamDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.team.TeamMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TEAM_NOT_EXISTS;

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

    @Override
    public List<OptionVO> getTeamOptions() {

        List<TeamDO> list;
        list = teamMapper.selectList(
                new LambdaQueryWrapperX<TeamDO>()
                        .eq(TeamDO::getDeleted, 0)
                        .orderByDesc(TeamDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, teamDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(teamDO.getName());
            vo.setValue(teamDO.getSysTeamId());
            return vo;
        });
    }
}