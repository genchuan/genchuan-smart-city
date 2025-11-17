package cn.iocoder.yudao.module.datacenter.service.inspection.assign.inspecttasktrack;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttasktrack.vo.InspectTaskTrackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttasktrack.vo.InspectTaskTrackSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.assign.inspecttasktrack.InspectTaskTrackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.assign.inspecttasktrack.InspectTaskTrackMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检任务跟踪 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectTaskTrackServiceImpl implements InspectTaskTrackService {

    @Resource
    private InspectTaskTrackMapper inspectTaskTrackMapper;

    @Override
    public Long createInspectTaskTrack(InspectTaskTrackSaveReqVO createReqVO) {
        // 插入
        InspectTaskTrackDO inspectTaskTrack = BeanUtils.toBean(createReqVO, InspectTaskTrackDO.class);
        inspectTaskTrackMapper.insert(inspectTaskTrack);
        // 返回
        return inspectTaskTrack.getId();
    }

    @Override
    public void updateInspectTaskTrack(InspectTaskTrackSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectTaskTrackExists(updateReqVO.getId());
        // 更新
        InspectTaskTrackDO updateObj = BeanUtils.toBean(updateReqVO, InspectTaskTrackDO.class);
        inspectTaskTrackMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectTaskTrack(Long id) {
        // 校验存在
        validateInspectTaskTrackExists(id);
        // 删除
        inspectTaskTrackMapper.deleteById(id);
    }

    private void validateInspectTaskTrackExists(Long id) {
        if (inspectTaskTrackMapper.selectById(id) == null) {
            throw exception(INSPECT_TASK_TRACK_NOT_EXISTS);
        }
    }

    @Override
    public InspectTaskTrackDO getInspectTaskTrack(Long id) {
        return inspectTaskTrackMapper.selectById(id);
    }

    @Override
    public PageResult<InspectTaskTrackDO> getInspectTaskTrackPage(InspectTaskTrackPageReqVO pageReqVO) {
        return inspectTaskTrackMapper.selectPage(pageReqVO);
    }

}