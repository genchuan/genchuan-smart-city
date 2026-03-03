package cn.iocoder.yudao.module.envirhealth.service.team;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.controller.admin.team.vo.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.team.TeamDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 班组 Service 接口
 *
 * @author 芋道源码
 */
public interface TeamService {

    /**
     * 创建班组
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTeam(@Valid TeamSaveReqVO createReqVO);

    /**
     * 更新班组
     *
     * @param updateReqVO 更新信息
     */
    void updateTeam(@Valid TeamSaveReqVO updateReqVO);

    /**
     * 删除班组
     *
     * @param id 编号
     */
    void deleteTeam(Long id);

    /**
     * 获得班组
     *
     * @param id 编号
     * @return 班组
     */
    TeamDO getTeam(Long id);

    /**
     * 获得班组分页
     *
     * @param pageReqVO 分页查询
     * @return 班组分页
     */
    PageResult<TeamDO> getTeamPage(TeamPageReqVO pageReqVO);

}