package cn.iocoder.yudao.module.studentmgmt.service.moralresource;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.moralactivity.vo.ChartActivityCountRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.moralactivity.vo.MoralActivityChartRespVO;
import cn.iocoder.yudao.module.studentmgmt.enums.AidWorkStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.AidWorkTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.MoralResourceStatusEnum;
import com.alibaba.fastjson.JSONObject;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.moralresource.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.moralresource.MoralResourceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.moralresource.MoralResourceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

/**
 * 德育资源 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MoralResourceServiceImpl implements MoralResourceService {

    @Resource
    private MoralResourceMapper moralResourceMapper;

    @Override
    public Long createMoralResource(MoralResourceSaveReqVO createReqVO) {
        // 插入
        MoralResourceDO moralResource = BeanUtils.toBean(createReqVO, MoralResourceDO.class);
        moralResourceMapper.insert(moralResource);

        // 返回
        return moralResource.getId();
    }

    @Override
    public void updateMoralResource(MoralResourceSaveReqVO updateReqVO) {
        // 校验存在
        validateMoralResourceExists(updateReqVO.getId());
        // 更新
        MoralResourceDO updateObj = BeanUtils.toBean(updateReqVO, MoralResourceDO.class);
        moralResourceMapper.updateById(updateObj);
    }

    @Override
    public void deleteMoralResource(Long id) {
        // 校验存在
        validateMoralResourceExists(id);
        // 删除
        moralResourceMapper.deleteById(id);
    }

    @Override
        public void deleteMoralResourceListByIds(List<Long> ids) {
        // 删除
        moralResourceMapper.deleteByIds(ids);
        }


    private MoralResourceDO validateMoralResourceExists(Long id) {
        MoralResourceDO moralResource = moralResourceMapper.selectById(id);

        if ( moralResource == null) {
            throw exception(MORAL_RESOURCE_NOT_EXISTS);
        }
        return moralResource;
    }

    @Override
    public MoralResourceDO getMoralResource(Long id) {
        return moralResourceMapper.selectById(id);
    }

    @Override
    public PageResult<MoralResourceDO> getMoralResourcePage(MoralResourcePageReqVO pageReqVO) {
        return moralResourceMapper.selectPage(pageReqVO);
    }

    @Override
    @LogRecord(type = MORAL_RESOURCE_TYPE, subType = MORAL_RESOURCE_ONLINE_SUB_TYPE, bizNo = "{{#id}}",
            success = MORAL_RESOURCE_ONLINE_SUCCESS)
    public boolean online(MoralResourceOnlineReqVO reqVO) {
        int total = 0;
        for (Long id : reqVO.getIds()) {
            // 校验存在
            MoralResourceDO moralResource = validateMoralResourceExists(id);
            // 将资源状态修改为 “已上架”，自动更新上架时间为当前时间，自动记录操作审计日志
            moralResource.setStatus(MoralResourceStatusEnum.ONLINE.getStatus());
            moralResource.setPublishTime(LocalDateTime.now());

            // 更新
            MoralResourceDO updateObj = BeanUtils.toBean(reqVO, MoralResourceDO.class);
            int i = moralResourceMapper.updateById(updateObj);
            total += i;
        }
        if (total > 0) {
            // 记录操作日志上下文
            LogRecordContext.putVariable("id", reqVO.getIds()[0]);
            return true;
        }
        return false;
    }

    @Override
    @LogRecord(type = MORAL_RESOURCE_TYPE, subType = MORAL_RESOURCE_OFFLINE_SUB_TYPE, bizNo = "{{#id}}",
            success = MORAL_RESOURCE_OFFLINE_SUCCESS)
    public boolean offline(MoralResourceOnlineReqVO reqVO) {
        int total = 0;
        for (Long id : reqVO.getIds()) {
            // 校验存在
            MoralResourceDO moralResource = validateMoralResourceExists(id);
            // 将资源状态修改为 “已上架”，自动更新上架时间为当前时间，自动记录操作审计日志
            moralResource.setStatus(MoralResourceStatusEnum.OFFLINE.getStatus());
            moralResource.setPublishTime(LocalDateTime.now());

            // 更新
            MoralResourceDO updateObj = BeanUtils.toBean(reqVO, MoralResourceDO.class);
            int i = moralResourceMapper.updateById(updateObj);
            total += i;
        }
        if (total > 0) {
            // 记录操作日志上下文
            LogRecordContext.putVariable("id", reqVO.getIds()[0]);
            return true;
        }
        return false;
    }

    @Override
    public MoralResourceChartRespVO chart(MoralResourceChartReqVO reqVO) {
        MoralResourceChartRespVO vo = new MoralResourceChartRespVO();

        LocalDateTime startTime = reqVO.getStartTime();
        LocalDateTime endTime = reqVO.getEndTime();
        if (startTime != null && startTime.isBefore(LocalDateTime.of(2020, 1, 1, 0, 0, 0))) {
            startTime = null;
        }
        if (endTime !=null && endTime.isBefore(LocalDateTime.of(2020, 1, 1, 0, 0, 0))) {
            endTime = null;
        }

        // 1. 卡片数据
        // 按类型统计
        List<JSONObject> typeJson = moralResourceMapper.selectTypeCount(startTime, endTime);
        JSONObject newTypeJson = new JSONObject();
        typeJson.forEach(json -> {
            String type = json.getString("activityType");
            String nameByKey = AidWorkTypeEnum.getNameByKey(type);
            Long count = json.getLong("count");
            newTypeJson.put(nameByKey, count);
        });
        vo.setResourceTypeCount(newTypeJson);

        // 按状态统计
        List<JSONObject> statusJson = moralResourceMapper.selectStatusCount(startTime, endTime);
        JSONObject newStatusJson = new JSONObject();
        // 将statusMap里的status转为枚举的 name
        statusJson.forEach((json) -> {
            String status = json.getString("status");
            String nameByKey = AidWorkStatusEnum.getNameByKey(status);
            Long count = json.getLong("count");
            newStatusJson.put(nameByKey, count);
        });
        vo.setStatusCount(newStatusJson);

        // 月度学习人数趋势
        List<JSONObject> learnTrend = moralResourceMapper.selectLearnTrend(startTime, endTime);
        vo.setLearnTrend(learnTrend);

        // 月度学习完成率趋势
        List<JSONObject> rateTrend = moralResourceMapper.selectRateTrend (startTime, endTime);
        vo.setRateTrend(rateTrend);

        return vo;
    }

    @Override
    public ChartResourceCountRespVO resourceCount(MoralResourceChartReqVO reqVO) {
        LocalDateTime startTime = reqVO.getStartTime();
        LocalDateTime endTime = reqVO.getEndTime();
        if (startTime != null && startTime.isBefore(LocalDateTime.of(2020, 1, 1, 0, 0, 0))) {
            startTime = null;
        }
        if (endTime !=null && endTime.isBefore(LocalDateTime.of(2020, 1, 1, 0, 0, 0))) {
            endTime = null;
        }
        // 按类型统计
        List<JSONObject> typeJson = moralResourceMapper.selectTypeCount(startTime, endTime);
        LocalDateTime finalStartTime = startTime;
        LocalDateTime finalEndTime = endTime;
        List resourceCountList = new ArrayList<>();
        List learnRateList = new ArrayList<>();
        List typeList = new ArrayList<>();
        typeJson.forEach(json -> {
            String type = json.getString("type");
            String nameByKey = AidWorkTypeEnum.getNameByKey(type);
            typeList.add(nameByKey);
//            "resourceCountList": [5,7,3],
            Long typeCount = moralResourceMapper.selectTypeCountByType(finalStartTime, finalEndTime, type);
            resourceCountList.add(typeCount);

//            "learnRateList": [200, 280, 50]
            Long learnCount = moralResourceMapper.selectLearnCountByType(finalStartTime, finalEndTime, type);
            learnRateList.add(learnCount);

        });
        ChartResourceCountRespVO vo = new ChartResourceCountRespVO();
        vo.setTypeList(typeList);
        vo.setLearnRateList(learnRateList);
        vo.setResourceCountList(resourceCountList);
        return vo;
    }

}