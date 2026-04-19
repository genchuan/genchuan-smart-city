package cn.iocoder.yudao.module.studentmgmt.service.targetmgmt;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.honormgmt.vo.HonorMgmtChartRespVO;
import cn.iocoder.yudao.module.studentmgmt.enums.TargetStatusEnum;
import com.alibaba.fastjson.JSONObject;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.targetmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.targetmgmt.TargetMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.targetmgmt.TargetMgmtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

/**
 * 指标管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TargetMgmtServiceImpl implements TargetMgmtService {

    @Resource
    private TargetMgmtMapper targetMgmtMapper;

    @Override
    @LogRecord(type = TARGET_TYPE, subType = TARGET_CREATE_SUB_TYPE, bizNo = "{{#createReqVO.id}}",
            success = TARGET_CREATE_SUCCESS)
    public Long createTargetMgmt(TargetMgmtSaveReqVO createReqVO) {
        // 插入
        TargetMgmtDO targetMgmt = BeanUtils.toBean(createReqVO, TargetMgmtDO.class);
        targetMgmtMapper.insert(targetMgmt);

        // 返回
        return targetMgmt.getId();
    }

    @Override
    @LogRecord(type = TARGET_TYPE, subType = TARGET_UPDATE_SUB_TYPE, bizNo = "{{#updateReqVO.id}}",
            success = TARGET_UPDATE_SUCCESS)
    public void updateTargetMgmt(TargetMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateTargetMgmtExists(updateReqVO.getId());
        // 更新
        TargetMgmtDO updateObj = BeanUtils.toBean(updateReqVO, TargetMgmtDO.class);
        targetMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteTargetMgmt(Long id) {
        // 校验存在
        validateTargetMgmtExists(id);
        // 删除
        targetMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteTargetMgmtListByIds(List<Long> ids) {
        // 删除
        targetMgmtMapper.deleteByIds(ids);
        }


    private TargetMgmtDO validateTargetMgmtExists(Long id) {
        TargetMgmtDO targetMgmt = targetMgmtMapper.selectById(id);
        if ( targetMgmt == null) {
            throw exception(TARGET_MGMT_NOT_EXISTS);
        }
        return targetMgmt;
    }

    @Override
    public TargetMgmtDO getTargetMgmt(Long id) {
        return targetMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<TargetMgmtDO> getTargetMgmtPage(TargetMgmtPageReqVO pageReqVO) {
        return targetMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    @LogRecord(type = TARGET_TYPE,
            subType = TARGET_CONFIG_SUB_TYPE, bizNo = "{{#reqVO.id}}",
            success = TARGET_CONFIG_SUCCESS)
    public boolean config(TargetMgmtConfigReqVO reqVO) {
        // 校验存在
        TargetMgmtDO targetMgmtDO = validateTargetMgmtExists(reqVO.getId());
        // 更新
        TargetMgmtDO updateObj = BeanUtils.toBean(reqVO, TargetMgmtDO.class);
        int i = targetMgmtMapper.updateById(updateObj);
        // 记录操作日志上下文
        LogRecordContext.putVariable("target", targetMgmtDO);
        return i > 0;
    }

    @Override
    @LogRecord(type = TARGET_TYPE,
            subType = TARGET_ENABLE_SUB_TYPE, bizNo = "{{#id}}",
            success = TARGET_ENABLE_SUCCESS)
    public boolean enable(TargetMgmtEnableReqVO reqVO) {

        Integer[] ids = reqVO.getIds();
        int total = 0;
        for (Integer id : ids) {
            // 校验存在
            TargetMgmtDO targetMgmtDO = validateTargetMgmtExists(Long.valueOf(id));
            // 更新
            targetMgmtDO.setEnableTime(LocalDateTime.now());
            targetMgmtDO.setStatus(TargetStatusEnum.ENABLE.getStatus());
            int i = targetMgmtMapper.updateById(targetMgmtDO);
            total += i;
        }
        LogRecordContext.putVariable("id", reqVO.getIds()[0]);
        return total == ids.length;
    }

    @Override
    @LogRecord(type = TARGET_TYPE,
            subType = TARGET_DISABLE_SUB_TYPE, bizNo = "{{#id}}",
            success = TARGET_DISABLE_SUCCESS)
    public boolean disable(TargetMgmtEnableReqVO reqVO) {

        Integer[] ids = reqVO.getIds();
        int total = 0;
        for (Integer id : ids) {
            // 校验存在
            TargetMgmtDO targetMgmtDO = validateTargetMgmtExists(Long.valueOf(id));
            // 更新
            targetMgmtDO.setEnableTime(LocalDateTime.now());
            targetMgmtDO.setStatus(TargetStatusEnum.DISABLE.getStatus());
            int i = targetMgmtMapper.updateById(targetMgmtDO);
            total += i;
        }
        LogRecordContext.putVariable("id", reqVO.getIds()[0]);

        return total == ids.length;
    }

    @Override
    public TargetMgmtChartRespVO chart(TargetMgmtChartReqVO reqVO) {

        TargetMgmtChartRespVO vo = new TargetMgmtChartRespVO();

        LocalDateTime startTime = reqVO.getStartTime();
        LocalDateTime endTime = reqVO.getEndTime();

        // 1. 卡片数据
        // 状态分布统计
        List<JSONObject> statusList = targetMgmtMapper.selectStatusCount(startTime, endTime);
        JSONObject statusCountJson = new JSONObject();
        for (JSONObject status : statusList) {
            statusCountJson.put(status.getString("status"), status.getLong("count"));
        }
        vo.setStatusCount(statusCountJson);
        // 2. 评价人类型数据
        List<JSONObject> evaluatorTypeList = targetMgmtMapper.selectEvaluatorTypeCount(startTime, endTime);
        JSONObject evaluatorTypeCountJson = new JSONObject();
        for (JSONObject evaluatorType : evaluatorTypeList) {
            evaluatorTypeCountJson.put(evaluatorType.getString("evaluatorType"), evaluatorType.getLong("count"));
        }
        vo.setEvaluatorTypeCount(evaluatorTypeCountJson);

        // 3. 计分方式数据
        List<JSONObject> scoreTypeList = targetMgmtMapper.selectScoreTypeCount(startTime, endTime);
        JSONObject scoreTypeCountJson = new JSONObject();
        for (JSONObject scoreType : scoreTypeList) {
            scoreTypeCountJson.put(scoreType.getString("scoreType"), scoreType.getLong("count"));
        }
        vo.setScoreTypeCount(scoreTypeCountJson);
        // 4、指标得分分布数据
        List<JSONObject> scoreDistributionList = targetMgmtMapper.selectScoreDistribution(startTime, endTime);

        // 各个分数区间统计
        // 0-20
        int range20 = 0;
        // 20-40
        int range40 = 0;
        // 40-60
        int range60 = 0;
        // 60-80
        int range80 = 0;
        // 80-100
        int range100 = 0;
        for (JSONObject scoreDistribution : scoreDistributionList) {
            Long score = scoreDistribution.getLong("score");
            Long count = scoreDistribution.getLong("count");
            // 对分数进行区间判断处理，并统计该区间的数量
            if (score >= 0 && score < 20) {
                range20 += count;
            }else if (score >= 20 && score < 40) {
                range40 += count;
            }else if (score >= 40 && score < 60) {
                range60 += count;
            }else if (score >= 60 && score < 80) {
                range80 += count;
            }else if (score >= 80 && score < 100) {
                range100 += count;
            }
        }
        JSONObject scoreDistribution = new JSONObject();
        scoreDistribution.put("0-20", range20);
        scoreDistribution.put("20-40", range40);
        scoreDistribution.put("40-60", range60);
        scoreDistribution.put("60-80", range60);
        scoreDistribution.put("80-100", range80);
        List<JSONObject> list = new ArrayList<>();
        list.add(scoreDistribution);
        vo.setScoreDistribution(list);
        return vo;
    }

    @Override
    public TargetMgmtChartIndexRespVO targetIndex() {

        TargetMgmtChartIndexRespVO vo = new TargetMgmtChartIndexRespVO();

        // 1. 卡片数据
        vo = targetMgmtMapper.selectTotalIndex(TargetStatusEnum.ENABLE.getStatus());

        return vo;
    }
}