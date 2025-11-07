package cn.iocoder.yudao.module.datacenter.service.resschedmanage.emerteaminfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteaminfo.vo.EmerTeamInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteaminfo.vo.EmerTeamInfoSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerteaminfo.EmerTeamInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 救援队伍信息 Service 接口
 *
 * @author 亘川智城
 */
public interface EmerTeamInfoService {

    /**
     * 创建救援队伍信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmerTeamInfo(@Valid EmerTeamInfoSaveReqVO createReqVO);

    /**
     * 更新救援队伍信息
     *
     * @param updateReqVO 更新信息
     */
    void updateEmerTeamInfo(@Valid EmerTeamInfoSaveReqVO updateReqVO);

    /**
     * 删除救援队伍信息
     *
     * @param id 编号
     */
    void deleteEmerTeamInfo(Long id);

    /**
     * 获得救援队伍信息
     *
     * @param id 编号
     * @return 救援队伍信息
     */
    EmerTeamInfoDO getEmerTeamInfo(Long id);

    /**
     * 获得救援队伍信息分页
     *
     * @param pageReqVO 分页查询
     * @return 救援队伍信息分页
     */
    PageResult<EmerTeamInfoDO> getEmerTeamInfoPage(EmerTeamInfoPageReqVO pageReqVO);

}