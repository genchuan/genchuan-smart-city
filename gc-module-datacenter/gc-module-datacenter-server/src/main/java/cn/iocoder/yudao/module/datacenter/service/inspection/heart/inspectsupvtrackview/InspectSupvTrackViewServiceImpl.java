package cn.iocoder.yudao.module.datacenter.service.inspection.heart.inspectsupvtrackview;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtrackview.vo.InspectSupvTrackViewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtrackview.vo.InspectSupvTrackViewSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.heart.inspectsupvtrackview.InspectSupvTrackViewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.heart.inspectsupvtrackview.InspectSupvTrackViewMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检督办进度跟踪视图 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectSupvTrackViewServiceImpl implements InspectSupvTrackViewService {

    @Resource
    private InspectSupvTrackViewMapper inspectSupvTrackViewMapper;

    @Override
    public Long createInspectSupvTrackView(InspectSupvTrackViewSaveReqVO createReqVO) {
        // 插入
        InspectSupvTrackViewDO inspectSupvTrackView = BeanUtils.toBean(createReqVO, InspectSupvTrackViewDO.class);
        inspectSupvTrackViewMapper.insert(inspectSupvTrackView);
        // 返回
        return inspectSupvTrackView.getId();
    }

    @Override
    public void updateInspectSupvTrackView(InspectSupvTrackViewSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectSupvTrackViewExists(updateReqVO.getId());
        // 更新
        InspectSupvTrackViewDO updateObj = BeanUtils.toBean(updateReqVO, InspectSupvTrackViewDO.class);
        inspectSupvTrackViewMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectSupvTrackView(Long id) {
        // 校验存在
        validateInspectSupvTrackViewExists(id);
        // 删除
        inspectSupvTrackViewMapper.deleteById(id);
    }

    private void validateInspectSupvTrackViewExists(Long id) {
        if (inspectSupvTrackViewMapper.selectById(id) == null) {
            throw exception(INSPECT_SUPV_TRACK_VIEW_NOT_EXISTS);
        }
    }

    @Override
    public InspectSupvTrackViewDO getInspectSupvTrackView(Long id) {
        return inspectSupvTrackViewMapper.selectById(id);
    }

    @Override
    public PageResult<InspectSupvTrackViewDO> getInspectSupvTrackViewPage(InspectSupvTrackViewPageReqVO pageReqVO) {
        return inspectSupvTrackViewMapper.selectPage(pageReqVO);
    }

}