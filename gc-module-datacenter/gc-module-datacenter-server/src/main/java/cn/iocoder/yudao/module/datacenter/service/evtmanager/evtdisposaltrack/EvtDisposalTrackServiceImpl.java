package cn.iocoder.yudao.module.datacenter.service.evtmanager.evtdisposaltrack;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtdisposaltrack.vo.EvtDisposalTrackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtdisposaltrack.vo.EvtDisposalTrackSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;


import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtdisposaltrack.EvtDisposalTrackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evtmanager.evtdisposaltrack.EvtDisposalTrackMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 事件处置跟踪 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EvtDisposalTrackServiceImpl implements EvtDisposalTrackService {

    @Resource
    private EvtDisposalTrackMapper evtDisposalTrackMapper;

    @Override
    public Long createEvtDisposalTrack(EvtDisposalTrackSaveReqVO createReqVO) {
        // 插入
        EvtDisposalTrackDO evtDisposalTrack = BeanUtils.toBean(createReqVO, EvtDisposalTrackDO.class);
        evtDisposalTrackMapper.insert(evtDisposalTrack);
        // 返回
        return evtDisposalTrack.getId();
    }

    @Override
    public void updateEvtDisposalTrack(EvtDisposalTrackSaveReqVO updateReqVO) {
        // 校验存在
        validateEvtDisposalTrackExists(updateReqVO.getId());
        // 更新
        EvtDisposalTrackDO updateObj = BeanUtils.toBean(updateReqVO, EvtDisposalTrackDO.class);
        evtDisposalTrackMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvtDisposalTrack(Long id) {
        // 校验存在
        validateEvtDisposalTrackExists(id);
        // 删除
        evtDisposalTrackMapper.deleteById(id);
    }

    private void validateEvtDisposalTrackExists(Long id) {
        if (evtDisposalTrackMapper.selectById(id) == null) {
            throw exception(EVT_DISPOSAL_TRACK_NOT_EXISTS);
        }
    }

    @Override
    public EvtDisposalTrackDO getEvtDisposalTrack(Long id) {
        return evtDisposalTrackMapper.selectById(id);
    }

    @Override
    public PageResult<EvtDisposalTrackDO> getEvtDisposalTrackPage(EvtDisposalTrackPageReqVO pageReqVO) {
        return evtDisposalTrackMapper.selectPage(pageReqVO);
    }

}