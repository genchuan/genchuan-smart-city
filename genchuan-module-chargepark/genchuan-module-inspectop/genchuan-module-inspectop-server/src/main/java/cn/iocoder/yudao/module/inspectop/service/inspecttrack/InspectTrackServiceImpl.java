package cn.iocoder.yudao.module.inspectop.service.inspecttrack;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspecttrack.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspecttrack.InspectTrackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.inspecttrack.InspectTrackMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 巡检轨迹 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class InspectTrackServiceImpl implements InspectTrackService {

    @Resource
    private InspectTrackMapper inspectTrackMapper;

    @Override
    public Long createInspectTrack(InspectTrackSaveReqVO createReqVO) {
        // 插入
        InspectTrackDO inspectTrack = BeanUtils.toBean(createReqVO, InspectTrackDO.class);
        inspectTrackMapper.insert(inspectTrack);

        // 返回
        return inspectTrack.getId();
    }

    @Override
    public void updateInspectTrack(InspectTrackSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectTrackExists(updateReqVO.getId());
        // 更新
        InspectTrackDO updateObj = BeanUtils.toBean(updateReqVO, InspectTrackDO.class);
        inspectTrackMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectTrack(Long id) {
        // 校验存在
        validateInspectTrackExists(id);
        // 删除
        inspectTrackMapper.deleteById(id);
    }

    @Override
        public void deleteInspectTrackListByIds(List<Long> ids) {
        // 删除
        inspectTrackMapper.deleteByIds(ids);
        }


    private void validateInspectTrackExists(Long id) {
        if (inspectTrackMapper.selectById(id) == null) {
            throw exception(INSPECT_TRACK_NOT_EXISTS);
        }
    }

    @Override
    public InspectTrackDO getInspectTrack(Long id) {
        return inspectTrackMapper.selectById(id);
    }

    @Override
    public PageResult<InspectTrackDO> getInspectTrackPage(InspectTrackPageReqVO pageReqVO) {
        return inspectTrackMapper.selectPage(pageReqVO);
    }

}