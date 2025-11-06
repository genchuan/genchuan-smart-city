package cn.iocoder.yudao.module.datacenter.service.resschedmanage.emerteaminfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteaminfo.vo.EmerTeamInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteaminfo.vo.EmerTeamInfoSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerteaminfo.EmerTeamInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.resschedmanage.emerteaminfo.EmerTeamInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 救援队伍信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EmerTeamInfoServiceImpl implements EmerTeamInfoService {

    @Resource
    private EmerTeamInfoMapper emerTeamInfoMapper;

    @Override
    public Long createEmerTeamInfo(EmerTeamInfoSaveReqVO createReqVO) {
        // 插入
        EmerTeamInfoDO emerTeamInfo = BeanUtils.toBean(createReqVO, EmerTeamInfoDO.class);
        emerTeamInfoMapper.insert(emerTeamInfo);
        // 返回
        return emerTeamInfo.getId();
    }

    @Override
    public void updateEmerTeamInfo(EmerTeamInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateEmerTeamInfoExists(updateReqVO.getId());
        // 更新
        EmerTeamInfoDO updateObj = BeanUtils.toBean(updateReqVO, EmerTeamInfoDO.class);
        emerTeamInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmerTeamInfo(Long id) {
        // 校验存在
        validateEmerTeamInfoExists(id);
        // 删除
        emerTeamInfoMapper.deleteById(id);
    }

    private void validateEmerTeamInfoExists(Long id) {
        if (emerTeamInfoMapper.selectById(id) == null) {
            throw exception(EMER_TEAM_INFO_NOT_EXISTS);
        }
    }

    @Override
    public EmerTeamInfoDO getEmerTeamInfo(Long id) {
        return emerTeamInfoMapper.selectById(id);
    }

    @Override
    public PageResult<EmerTeamInfoDO> getEmerTeamInfoPage(EmerTeamInfoPageReqVO pageReqVO) {
        return emerTeamInfoMapper.selectPage(pageReqVO);
    }

}