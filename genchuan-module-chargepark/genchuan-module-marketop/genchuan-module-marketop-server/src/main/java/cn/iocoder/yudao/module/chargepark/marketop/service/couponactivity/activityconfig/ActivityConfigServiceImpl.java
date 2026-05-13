package cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.activityconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo.ActivityConfigChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo.ActivityConfigCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo.ActivityConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo.ActivityConfigUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.ActivityConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity.ActivityConfigMapper;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.LogRecordConstants.*;

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
    @LogRecord(type = ACTIVITY_CONFIG_TYPE, subType = ACTIVITY_CONFIG_CREATE_SUB_TYPE, bizNo = "{{#activityConfig.id}}",
            success = ACTIVITY_CONFIG_CREATE_SUCCESS)
    public Long create(ActivityConfigCreateReqVO reqVO) {
        // 校验名称唯一
        validateNameUnique(null, reqVO.getName());
        ActivityConfigDO activityConfig = BeanUtils.toBean(reqVO, ActivityConfigDO.class);
        activityConfig.setStatus("1");
        activityConfig.setJoinCount(0);
        activityConfigMapper.insert(activityConfig);
        // 记录操作日志上下文
        LogRecordContext.putVariable("activityConfig", activityConfig);
        return activityConfig.getId();
    }

    @Override
    @LogRecord(type = ACTIVITY_CONFIG_TYPE, subType = ACTIVITY_CONFIG_UPDATE_SUB_TYPE, bizNo = "{{#reqVO.id}}",
            success = ACTIVITY_CONFIG_UPDATE_SUCCESS)
    public void update(ActivityConfigUpdateReqVO reqVO) {
        ActivityConfigDO activityConfigDO = validateExists(reqVO.getId());
        ActivityConfigDO updateObj = BeanUtils.toBean(reqVO, ActivityConfigDO.class);
        activityConfigMapper.updateById(updateObj);
        // 记录操作日志上下文
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, BeanUtils.toBean(activityConfigDO, ActivityConfigUpdateReqVO.class));
        LogRecordContext.putVariable("activityConfig", updateObj);
    }

    @Override
    @LogRecord(type = ACTIVITY_CONFIG_TYPE, subType = ACTIVITY_CONFIG_ENABLE_SUB_TYPE, bizNo = "{{#id}}",
            success = ACTIVITY_CONFIG_ENABLE_SUCCESS)
    public void enable(Long id) {
        ActivityConfigDO activityConfig = validateExists(id);
        if (!"0".equals(activityConfig.getStatus())) {
            throw exception(ACTIVITY_CONFIG_NOT_EXISTS);
        }
        activityConfig.setStatus("1");
        activityConfig.setAuditTime(LocalDateTime.now());
        activityConfig.setEffectTime(LocalDateTime.now());
        activityConfigMapper.updateById(activityConfig);
        // 记录操作日志上下文
        LogRecordContext.putVariable("activityConfigName", activityConfig.getName());
    }

    @Override
    @LogRecord(type = ACTIVITY_CONFIG_TYPE, subType = ACTIVITY_CONFIG_DISABLE_SUB_TYPE, bizNo = "{{#id}}",
            success = ACTIVITY_CONFIG_DISABLE_SUCCESS)
    public void disable(Long id) {
        ActivityConfigDO activityConfig = validateExists(id);
        if (!"1".equals(activityConfig.getStatus())) {
            throw exception(ACTIVITY_CONFIG_NOT_EXISTS);
        }
        activityConfig.setStatus("0");
        activityConfigMapper.updateById(activityConfig);
        // 记录操作日志上下文
        LogRecordContext.putVariable("activityConfigName", activityConfig.getName());
    }

    @Override
    public ActivityConfigChartRespVO getChart() {
        ActivityConfigChartRespVO respVO = new ActivityConfigChartRespVO();

        // EnableCount: status为1的记录数
        Long enableCount = activityConfigMapper.selectCount(new LambdaQueryWrapperX<ActivityConfigDO>()
                .eq(ActivityConfigDO::getStatus, "1"));
        respVO.setEnableCount(enableCount != null ? enableCount.intValue() : 0);

        // JoinRate: 暂不计算
        respVO.setJoinRate(0);

        // TypeList: 按type分组统计
        List<java.util.Map<String, Object>> typeCountList = activityConfigMapper.selectTypeCountList();
        List<ActivityConfigChartRespVO.TypeCountItem> typeList = typeCountList.stream().map(m -> {
            ActivityConfigChartRespVO.TypeCountItem item = new ActivityConfigChartRespVO.TypeCountItem();
            item.setType((String) m.get("type"));
            item.setCount(((Number) m.get("count")).intValue());
            return item;
        }).collect(Collectors.toList());
        respVO.setTypeList(typeList);

        // UserGroupList: 按userGroup分组统计
        List<java.util.Map<String, Object>> userGroupCountList = activityConfigMapper.selectUserGroupCountList();
        List<ActivityConfigChartRespVO.UserGroupCountItem> userGroupList = userGroupCountList.stream().map(m -> {
            ActivityConfigChartRespVO.UserGroupCountItem item = new ActivityConfigChartRespVO.UserGroupCountItem();
            item.setUserGroup((String) m.get("userGroup"));
            item.setCount(((Number) m.get("count")).intValue());
            return item;
        }).collect(Collectors.toList());
        respVO.setUserGroupList(userGroupList);

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
