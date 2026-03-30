package cn.iocoder.yudao.module.waterdetection.service.onlinelabcomparison;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.onlinelabcomparison.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.onlinelabcomparison.OnlineLabComparisonDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.onlinelabcomparison.OnlineLabComparisonMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 在线数据与实验室比对 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class OnlineLabComparisonServiceImpl implements OnlineLabComparisonService {

    @Resource
    private OnlineLabComparisonMapper onlineLabComparisonMapper;

    @Override
    public Long createOnlineLabComparison(OnlineLabComparisonSaveReqVO createReqVO) {
        // 插入
        OnlineLabComparisonDO onlineLabComparison = BeanUtils.toBean(createReqVO, OnlineLabComparisonDO.class);
        onlineLabComparisonMapper.insert(onlineLabComparison);
        // 返回
        return onlineLabComparison.getId();
    }

    @Override
    public void updateOnlineLabComparison(OnlineLabComparisonSaveReqVO updateReqVO) {
        // 校验存在
        validateOnlineLabComparisonExists(updateReqVO.getId());
        // 更新
        OnlineLabComparisonDO updateObj = BeanUtils.toBean(updateReqVO, OnlineLabComparisonDO.class);
        onlineLabComparisonMapper.updateById(updateObj);
    }

    @Override
    public void deleteOnlineLabComparison(Long id) {
        // 校验存在
        validateOnlineLabComparisonExists(id);
        // 删除
        onlineLabComparisonMapper.deleteById(id);
    }

    private void validateOnlineLabComparisonExists(Long id) {
        if (onlineLabComparisonMapper.selectById(id) == null) {
            throw exception(ONLINE_LAB_COMPARISON_NOT_EXISTS);
        }
    }

    @Override
    public OnlineLabComparisonDO getOnlineLabComparison(Long id) {
        return onlineLabComparisonMapper.selectById(id);
    }

    @Override
    public PageResult<OnlineLabComparisonDO> getOnlineLabComparisonPage(OnlineLabComparisonPageReqVO pageReqVO) {
        return onlineLabComparisonMapper.selectPage(pageReqVO);
    }

}