package cn.iocoder.yudao.module.waterdetection.service.issuetracking;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.issuetracking.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.issuetracking.IssueTrackingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.issuetracking.IssueTrackingMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 问题上报与闭环跟踪 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class IssueTrackingServiceImpl implements IssueTrackingService {

    @Resource
    private IssueTrackingMapper issueTrackingMapper;

    @Override
    public Long createIssueTracking(IssueTrackingSaveReqVO createReqVO) {
        // 插入
        IssueTrackingDO issueTracking = BeanUtils.toBean(createReqVO, IssueTrackingDO.class);
        issueTrackingMapper.insert(issueTracking);
        // 返回
        return issueTracking.getId();
    }

    @Override
    public void updateIssueTracking(IssueTrackingSaveReqVO updateReqVO) {
        // 校验存在
        validateIssueTrackingExists(updateReqVO.getId());
        // 更新
        IssueTrackingDO updateObj = BeanUtils.toBean(updateReqVO, IssueTrackingDO.class);
        issueTrackingMapper.updateById(updateObj);
    }

    @Override
    public void deleteIssueTracking(Long id) {
        // 校验存在
        validateIssueTrackingExists(id);
        // 删除
        issueTrackingMapper.deleteById(id);
    }

    private void validateIssueTrackingExists(Long id) {
        if (issueTrackingMapper.selectById(id) == null) {
            throw exception(ISSUE_TRACKING_NOT_EXISTS);
        }
    }

    @Override
    public IssueTrackingDO getIssueTracking(Long id) {
        return issueTrackingMapper.selectById(id);
    }

    @Override
    public PageResult<IssueTrackingDO> getIssueTrackingPage(IssueTrackingPageReqVO pageReqVO) {
        return issueTrackingMapper.selectPage(pageReqVO);
    }

}