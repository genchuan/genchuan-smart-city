package cn.iocoder.yudao.module.waterdetection.service.warningthreshold;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.warningthreshold.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.warningthreshold.WarningThresholdDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.warningthreshold.WarningThresholdMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 预警阈值管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class WarningThresholdServiceImpl implements WarningThresholdService {

    @Resource
    private WarningThresholdMapper warningThresholdMapper;

    @Override
    public Long createWarningThreshold(WarningThresholdSaveReqVO createReqVO) {
        // 插入
        WarningThresholdDO warningThreshold = BeanUtils.toBean(createReqVO, WarningThresholdDO.class);
        warningThresholdMapper.insert(warningThreshold);
        // 返回
        return warningThreshold.getId();
    }

    @Override
    public void updateWarningThreshold(WarningThresholdSaveReqVO updateReqVO) {
        // 校验存在
        validateWarningThresholdExists(updateReqVO.getId());
        // 更新
        WarningThresholdDO updateObj = BeanUtils.toBean(updateReqVO, WarningThresholdDO.class);
        warningThresholdMapper.updateById(updateObj);
    }

    @Override
    public void deleteWarningThreshold(Long id) {
        // 校验存在
        validateWarningThresholdExists(id);
        // 删除
        warningThresholdMapper.deleteById(id);
    }

    private void validateWarningThresholdExists(Long id) {
        if (warningThresholdMapper.selectById(id) == null) {
            throw exception(WARNING_THRESHOLD_NOT_EXISTS);
        }
    }

    @Override
    public WarningThresholdDO getWarningThreshold(Long id) {
        return warningThresholdMapper.selectById(id);
    }

    @Override
    public PageResult<WarningThresholdDO> getWarningThresholdPage(WarningThresholdPageReqVO pageReqVO) {
        return warningThresholdMapper.selectPage(pageReqVO);
    }

}