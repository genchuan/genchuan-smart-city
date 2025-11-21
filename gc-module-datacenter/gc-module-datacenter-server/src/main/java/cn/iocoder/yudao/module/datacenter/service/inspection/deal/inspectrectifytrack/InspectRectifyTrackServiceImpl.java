package cn.iocoder.yudao.module.datacenter.service.inspection.deal.inspectrectifytrack;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytrack.vo.InspectRectifyTrackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytrack.vo.InspectRectifyTrackSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.deal.inspectrectifytrack.InspectRectifyTrackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.deal.inspectrectifytrack.InspectRectifyTrackMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检整改进度跟踪视图 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectRectifyTrackServiceImpl implements InspectRectifyTrackService {

    @Resource
    private InspectRectifyTrackMapper inspectRectifyTrackMapper;

    @Override
    public Long createInspectRectifyTrack(InspectRectifyTrackSaveReqVO createReqVO) {
        // 插入
        InspectRectifyTrackDO inspectRectifyTrack = BeanUtils.toBean(createReqVO, InspectRectifyTrackDO.class);
        inspectRectifyTrackMapper.insert(inspectRectifyTrack);
        // 返回
        return inspectRectifyTrack.getId();
    }

    @Override
    public void updateInspectRectifyTrack(InspectRectifyTrackSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectRectifyTrackExists(updateReqVO.getId());
        // 更新
        InspectRectifyTrackDO updateObj = BeanUtils.toBean(updateReqVO, InspectRectifyTrackDO.class);
        inspectRectifyTrackMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectRectifyTrack(Long id) {
        // 校验存在
        validateInspectRectifyTrackExists(id);
        // 删除
        inspectRectifyTrackMapper.deleteById(id);
    }

    private void validateInspectRectifyTrackExists(Long id) {
        if (inspectRectifyTrackMapper.selectById(id) == null) {
            throw exception(INSPECT_RECTIFY_TRACK_NOT_EXISTS);
        }
    }

    @Override
    public InspectRectifyTrackDO getInspectRectifyTrack(Long id) {
        return inspectRectifyTrackMapper.selectById(id);
    }

    @Override
    public PageResult<InspectRectifyTrackDO> getInspectRectifyTrackPage(InspectRectifyTrackPageReqVO pageReqVO) {
        return inspectRectifyTrackMapper.selectPage(pageReqVO);
    }

}