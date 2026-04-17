package cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.activityconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo.ActivityConfigChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo.ActivityConfigCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo.ActivityConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo.ActivityConfigUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.ActivityConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity.ActivityConfigMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;

@Service
@Validated
public class ActivityConfigServiceImpl implements ActivityConfigService {

    @Resource
    private ActivityConfigMapper activityConfigMapper;

    @Override
    public PageResult<ActivityConfigDO> getPage(ActivityConfigPageReqVO reqVO) {
        return activityConfigMapper.selectPage(reqVO);
    }

    @Override
    public ActivityConfigDO get(Long id) {
        return activityConfigMapper.selectById(id);
    }

    @Override
    public Long create(ActivityConfigCreateReqVO reqVO) {
        // 校验名称唯一
        validateNameUnique(null, reqVO.getName());
        ActivityConfigDO activityConfig = BeanUtils.toBean(reqVO, ActivityConfigDO.class);
        activityConfig.setStatus("未生效");
        activityConfig.setJoinCount(0);
        activityConfigMapper.insert(activityConfig);
        return activityConfig.getId();
    }

    @Override
    public void update(ActivityConfigUpdateReqVO reqVO) {
        validateExists(reqVO.getId());
        ActivityConfigDO updateObj = BeanUtils.toBean(reqVO, ActivityConfigDO.class);
        activityConfigMapper.updateById(updateObj);
    }

    @Override
    public void enable(Long id) {
        ActivityConfigDO activityConfig = validateExists(id);
        if (!"未生效".equals(activityConfig.getStatus())) {
            throw exception(ACTIVITY_CONFIG_NOT_EXISTS);
        }
        activityConfig.setStatus("已生效");
        activityConfig.setAuditTime(LocalDateTime.now());
        activityConfig.setEffectTime(LocalDateTime.now());
        activityConfigMapper.updateById(activityConfig);
    }

    @Override
    public void disable(Long id) {
        ActivityConfigDO activityConfig = validateExists(id);
        if (!"已生效".equals(activityConfig.getStatus())) {
            throw exception(ACTIVITY_CONFIG_NOT_EXISTS);
        }
        activityConfig.setStatus("未生效");
        activityConfigMapper.updateById(activityConfig);
    }

    @Override
    public ActivityConfigChartRespVO getChart(String timeRange) {
        // TODO: 实现图表统计逻辑，暂时返回空数据
        ActivityConfigChartRespVO respVO = new ActivityConfigChartRespVO();
        respVO.setEnableCount(0);
        respVO.setJoinRate(BigDecimal.ZERO);
        respVO.setTypeList(new ArrayList<>());
        return respVO;
    }

    private ActivityConfigDO validateExists(Long id) {
        ActivityConfigDO activityConfig = activityConfigMapper.selectById(id);
        if (activityConfig == null) {
            throw exception(ACTIVITY_CONFIG_NOT_EXISTS);
        }
        return activityConfig;
    }

    private void validateNameUnique(Long id, String name) {
        ActivityConfigDO existing = activityConfigMapper.selectOne(ActivityConfigDO::getName, name);
        if (existing != null && !existing.getId().equals(id)) {
            throw exception(ACTIVITY_CONFIG_NAME_EXISTS);
        }
    }

}
