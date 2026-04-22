package cn.iocoder.yudao.module.studentmgmt.service.moralactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.moralactivity.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.moralactivity.MoralActivityDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.moralactivity.MoralActivityMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.AidWorkStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.AidWorkTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.MoralActivityStatusEnum;
import com.alibaba.fastjson.JSONObject;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.MORAL_ACTIVITY_NOT_EXISTS;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

/**
 * 德育活动 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MoralActivityServiceImpl implements MoralActivityService {

    @Resource
    private MoralActivityMapper moralActivityMapper;

    @Override
    public Long createMoralActivity(MoralActivitySaveReqVO createReqVO) {
        // 插入
        MoralActivityDO moralActivity = BeanUtils.toBean(createReqVO, MoralActivityDO.class);
        moralActivityMapper.insert(moralActivity);

        // 返回
        return moralActivity.getId();
    }

    @Override
    public void updateMoralActivity(MoralActivitySaveReqVO updateReqVO) {
        // 校验存在
        validateMoralActivityExists(updateReqVO.getId());
        // 更新
        MoralActivityDO updateObj = BeanUtils.toBean(updateReqVO, MoralActivityDO.class);
        moralActivityMapper.updateById(updateObj);
    }

    @Override
    public void deleteMoralActivity(Long id) {
        // 校验存在
        validateMoralActivityExists(id);
        // 删除
        moralActivityMapper.deleteById(id);
    }

    @Override
    public void deleteMoralActivityListByIds(List<Long> ids) {
        // 删除
        moralActivityMapper.deleteByIds(ids);
    }


    private MoralActivityDO validateMoralActivityExists(Long id) {
        MoralActivityDO moralActivity = moralActivityMapper.selectById(id);
        if (moralActivity == null) {
            throw exception(MORAL_ACTIVITY_NOT_EXISTS);
        }
        return moralActivity;
    }

    @Override
    public MoralActivityDO getMoralActivity(Long id) {
        return moralActivityMapper.selectById(id);
    }

    @Override
    public PageResult<MoralActivityDO> getMoralActivityPage(MoralActivityPageReqVO pageReqVO) {
        return moralActivityMapper.selectPage(pageReqVO);
    }

    @Override
    @LogRecord(type = MORAL_ACTIVITY_TYPE, subType = MORAL_ACTIVITY_PUBLISH_SUB_TYPE, bizNo = "{{#id}}",
            success = MORAL_ACTIVITY_PUBLISH_SUCCESS)
    public boolean publish(MoralActivityPublishReqVO reqVO) {
        Long[] ids = reqVO.getIds();
        int total = 0;

        for (Long id : ids) {
            // 校验存在
            MoralActivityDO moralActivity = validateMoralActivityExists(id);
            // 更新
            moralActivity.setStatus(MoralActivityStatusEnum.ONGOING.getStatus());
            moralActivity.setPublishTime(LocalDateTime.now());
            int i = moralActivityMapper.updateById(moralActivity);
            total += i;

        }
        if (total > 0) {
            // 记录操作日志上下文
            LogRecordContext.putVariable("id", reqVO.getIds()[0]);
        }
        return false;
    }


    @Override
    @LogRecord(type = MORAL_ACTIVITY_TYPE, subType = MORAL_ACTIVITY_JOIN_SUB_TYPE, bizNo = "{{#reqVO.id}}",
            success = MORAL_ACTIVITY_JOIN_SUCCESS)
    public boolean join(MoralActivityJoinReqVO reqVO) {
        // 校验存在
        MoralActivityDO moralActivity = validateMoralActivityExists(reqVO.getId());
        // 更新
        moralActivity.setJoinNum(moralActivity.getJoinNum() + 1);
        int i = moralActivityMapper.updateById(moralActivity);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    @LogRecord(type = MORAL_ACTIVITY_TYPE, subType = MORAL_ACTIVITY_RECORD_SUB_TYPE, bizNo = "{{#reqVO.id}}",
            success = MORAL_ACTIVITY_RECORD_SUCCESS)
    public boolean record(MoralActivityRecordReqVO reqVO) {
        // 校验存在
        MoralActivityDO moralActivity = validateMoralActivityExists(reqVO.getId());
        // 更新
        moralActivity.setJoinNum(moralActivity.getJoinNum() + 1);
        // 判断当前时间是否在活动时间之后，如果是，则设置状态为已结束
        if (LocalDateTime.now().isAfter(moralActivity.getEndTime())) {
            moralActivity.setStatus(MoralActivityStatusEnum.ENDED.getStatus());
        }
        int i = moralActivityMapper.updateById(moralActivity);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public MoralActivityChartRespVO chart(MoralActivityChartReqVO reqVO) {
        MoralActivityChartRespVO vo = new MoralActivityChartRespVO();

        LocalDateTime startTime = reqVO.getStartTime();
        LocalDateTime endTime = reqVO.getEndTime();
        if (startTime.isBefore(LocalDateTime.of(2020, 1, 1, 0, 0, 0))) {
            startTime = null;
        }
        if (endTime.isBefore(LocalDateTime.of(2020, 1, 1, 0, 0, 0))) {
            endTime = null;
        }

        // 1. 卡片数据
        // 按类型统计
        List<JSONObject> typeJson = moralActivityMapper.selectTypeCount(startTime, endTime, "");
        JSONObject newTypeJson = new JSONObject();
        typeJson.forEach(json -> {
            String type = json.getString("activityType");
            String nameByKey = AidWorkTypeEnum.getNameByKey(type);
            Long count = json.getLong("count");
            newTypeJson.put(nameByKey, count);
        });
        vo.setActivityTypeCount(newTypeJson);

        // 按状态统计
        List<JSONObject> statusJson = moralActivityMapper.selectStatusCount(startTime, endTime);
        JSONObject newStatusJson = new JSONObject();
        // 将statusMap里的status转为枚举的 name
        statusJson.forEach((json) -> {
            String status = json.getString("status");
            String nameByKey = AidWorkStatusEnum.getNameByKey(status);
            Long count = json.getLong("count");
            newStatusJson.put(nameByKey, count);
        });
        vo.setStatusCount(newStatusJson);

        // 月度活动数量趋势
        List<JSONObject> monthTrend = moralActivityMapper.selectMonthTrend(startTime, endTime);
        vo.setMonthTrend(monthTrend);

        // 月度参与人数趋势
        List<JSONObject> joinTrend = moralActivityMapper.selectJoinTrend(startTime, endTime);
        vo.setJoinTrend(joinTrend);

        return vo;
    }

    @Override
    public ChartActivityCountRespVO activityCount(MoralActivityChartReqVO reqVO) {
        LocalDateTime startTime = reqVO.getStartTime();
        LocalDateTime endTime = reqVO.getEndTime();
        if (startTime.isBefore(LocalDateTime.of(2020, 1, 1, 0, 0, 0))) {
            startTime = null;
        }
        if (endTime.isBefore(LocalDateTime.of(2020, 1, 1, 0, 0, 0))) {
            endTime = null;
        }
        // 按类型统计
        List<JSONObject> typeJson = moralActivityMapper.selectTypeCount(startTime, endTime, "");

        LocalDateTime finalStartTime = startTime;
        LocalDateTime finalEndTime = endTime;
        List activityCountList = new ArrayList<>();
        List joinCountList = new ArrayList<>();
        List typeList = new ArrayList<>();
        typeJson.forEach(json -> {
            String type = json.getString("activityType");
            String nameByKey = AidWorkTypeEnum.getNameByKey(type);
            typeList.add(nameByKey);
//            "activityCountList": [5,7,3],
            List<JSONObject> typeJsonList = moralActivityMapper.selectTypeCount(finalStartTime, finalEndTime, type);
            if (typeJsonList != null) {
                JSONObject jsonObject = typeJsonList.get(0);
                activityCountList.add(jsonObject.getLong("count"));
            }

//            "joinCountList": [200, 280, 50]
            Long joinCount = moralActivityMapper.selectJoinCountByType(finalStartTime, finalEndTime, type);
            joinCountList.add(joinCount);

        });
        ChartActivityCountRespVO vo = new ChartActivityCountRespVO();
        vo.setActivityCountList(activityCountList);
        vo.setJoinCountList(joinCountList);
        vo.setTypeList(typeList);
        return vo;
    }
}