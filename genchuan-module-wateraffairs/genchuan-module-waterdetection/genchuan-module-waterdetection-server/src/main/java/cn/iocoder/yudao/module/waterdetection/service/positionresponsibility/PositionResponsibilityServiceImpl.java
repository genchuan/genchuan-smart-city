package cn.iocoder.yudao.module.waterdetection.service.positionresponsibility;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.positionresponsibility.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.positionresponsibility.PositionResponsibilityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.positionresponsibility.PositionResponsibilityMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 岗位职责划分管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class PositionResponsibilityServiceImpl implements PositionResponsibilityService {

    @Resource
    private PositionResponsibilityMapper positionResponsibilityMapper;

    @Override
    public Long createPositionResponsibility(PositionResponsibilitySaveReqVO createReqVO) {
        // 插入
        PositionResponsibilityDO positionResponsibility = BeanUtils.toBean(createReqVO, PositionResponsibilityDO.class);
        positionResponsibilityMapper.insert(positionResponsibility);
        // 返回
        return positionResponsibility.getId();
    }

    @Override
    public void updatePositionResponsibility(PositionResponsibilitySaveReqVO updateReqVO) {
        // 校验存在
        validatePositionResponsibilityExists(updateReqVO.getId());
        // 更新
        PositionResponsibilityDO updateObj = BeanUtils.toBean(updateReqVO, PositionResponsibilityDO.class);
        positionResponsibilityMapper.updateById(updateObj);
    }

    @Override
    public void deletePositionResponsibility(Long id) {
        // 校验存在
        validatePositionResponsibilityExists(id);
        // 删除
        positionResponsibilityMapper.deleteById(id);
    }

    private void validatePositionResponsibilityExists(Long id) {
        if (positionResponsibilityMapper.selectById(id) == null) {
            throw exception(POSITION_RESPONSIBILITY_NOT_EXISTS);
        }
    }

    @Override
    public PositionResponsibilityDO getPositionResponsibility(Long id) {
        return positionResponsibilityMapper.selectById(id);
    }

    @Override
    public PageResult<PositionResponsibilityDO> getPositionResponsibilityPage(PositionResponsibilityPageReqVO pageReqVO) {
        return positionResponsibilityMapper.selectPage(pageReqVO);
    }

}