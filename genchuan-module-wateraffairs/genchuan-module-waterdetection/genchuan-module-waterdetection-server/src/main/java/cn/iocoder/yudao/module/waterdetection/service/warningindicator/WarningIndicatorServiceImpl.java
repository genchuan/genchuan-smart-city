package cn.iocoder.yudao.module.waterdetection.service.warningindicator;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.warningindicator.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.warningindicator.WarningIndicatorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.warningindicator.WarningIndicatorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 预警指标配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class WarningIndicatorServiceImpl implements WarningIndicatorService {

    @Resource
    private WarningIndicatorMapper warningIndicatorMapper;

    @Override
    public Long createWarningIndicator(WarningIndicatorSaveReqVO createReqVO) {
        // 插入
        WarningIndicatorDO warningIndicator = BeanUtils.toBean(createReqVO, WarningIndicatorDO.class);
        warningIndicatorMapper.insert(warningIndicator);
        // 返回
        return warningIndicator.getId();
    }

    @Override
    public void updateWarningIndicator(WarningIndicatorSaveReqVO updateReqVO) {
        // 校验存在
        validateWarningIndicatorExists(updateReqVO.getId());
        // 更新
        WarningIndicatorDO updateObj = BeanUtils.toBean(updateReqVO, WarningIndicatorDO.class);
        warningIndicatorMapper.updateById(updateObj);
    }

    @Override
    public void deleteWarningIndicator(Long id) {
        // 校验存在
        validateWarningIndicatorExists(id);
        // 删除
        warningIndicatorMapper.deleteById(id);
    }

    private void validateWarningIndicatorExists(Long id) {
        if (warningIndicatorMapper.selectById(id) == null) {
            throw exception(WARNING_INDICATOR_NOT_EXISTS);
        }
    }

    @Override
    public WarningIndicatorDO getWarningIndicator(Long id) {
        return warningIndicatorMapper.selectById(id);
    }

    @Override
    public PageResult<WarningIndicatorDO> getWarningIndicatorPage(WarningIndicatorPageReqVO pageReqVO) {
        return warningIndicatorMapper.selectPage(pageReqVO);
    }

}