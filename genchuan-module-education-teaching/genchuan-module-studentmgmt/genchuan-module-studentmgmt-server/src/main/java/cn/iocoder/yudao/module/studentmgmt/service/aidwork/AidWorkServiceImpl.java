package cn.iocoder.yudao.module.studentmgmt.service.aidwork;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.aidwork.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.aidwork.AidWorkDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.aidwork.AidWorkMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.AidWorkProcessStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.AidWorkStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.AidWorkTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.AID_WORK_NOT_EXISTS;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

/**
 * 奖助勤贷 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AidWorkServiceImpl implements AidWorkService {

    @Resource
    private AidWorkMapper aidWorkMapper;

    @Override
    @LogRecord(type = AID_TYPE, subType = AID_CREATE_SUB_TYPE, bizNo = "{{#aid.id}}",
            success = AID_CREATE_SUCCESS)
    public Long createAidWork(AidWorkSaveReqVO createReqVO) {
        // 插入
        AidWorkDO aidWork = BeanUtils.toBean(createReqVO, AidWorkDO.class);
        aidWorkMapper.insert(aidWork);

        // 记录操作日志上下文
        LogRecordContext.putVariable("aid", aidWork);
        // 返回
        return aidWork.getId();
    }

    @Override
    @LogRecord(type = AID_TYPE, subType = AID_UPDATE_SUB_TYPE, bizNo = "{{#updateReqVO.id}}",
            success = AID_UPDATE_SUCCESS)
    public void updateAidWork(AidWorkSaveReqVO updateReqVO) {
        // 校验存在
        AidWorkDO aidWorkDO = validateAidWorkExists(updateReqVO.getId());
        // 更新
        AidWorkDO updateObj = BeanUtils.toBean(updateReqVO, AidWorkDO.class);
        int i = aidWorkMapper.updateById(updateObj);
        // 3. 记录操作日志上下文
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, BeanUtils.toBean(aidWorkDO, AidWorkSaveReqVO.class));
        LogRecordContext.putVariable("aid", aidWorkDO);
    }

    @Override
    public void deleteAidWork(Long id) {
        // 校验存在
        validateAidWorkExists(id);
        // 删除
        aidWorkMapper.deleteById(id);
    }

    @Override
    public void deleteAidWorkListByIds(List<Long> ids) {
        // 删除
        aidWorkMapper.deleteByIds(ids);
    }


    private AidWorkDO validateAidWorkExists(Long id) {
        AidWorkDO aidWorkDO = aidWorkMapper.selectById(id);
        if (aidWorkDO == null) {
            throw exception(AID_WORK_NOT_EXISTS);
        }
        return aidWorkDO;
    }

    @Override
    public AidWorkDO getAidWork(Long id) {
        return aidWorkMapper.selectById(id);
    }

    @Override
    public PageResult<AidWorkDO> getAidWorkPage(AidWorkPageReqVO pageReqVO) {
        return aidWorkMapper.selectPage(pageReqVO);
    }

    @Override
    @LogRecord(type = AID_TYPE, subType = AID_AUDIT_SUB_TYPE, bizNo = "{{#aid.id}}",
            success = AID_AUDIT_SUCCESS)
    public boolean audit(AidWorkAuditReqVO reqVO) {
        AidWorkDO aidWorkDO = validateAidWorkExists(reqVO.getId());
        aidWorkDO.setAuditTime(LocalDateTime.now());
        // 获取当前用户
        String username = SecurityFrameworkUtils.getLoginUserNickname();
        aidWorkDO.setAuditUser(username);
        aidWorkDO.setStatus(reqVO.getAuditResult());
        aidWorkDO.setProcessStatus(AidWorkProcessStatusEnum.AID_WORK_PROCESS_STATUS_ENUM_1.getStatus());

        int i = aidWorkMapper.updateById(aidWorkDO);
        if (i > 0) {
            // 记录操作日志上下文
            LogRecordContext.putVariable("aid", aidWorkDO);
            LogRecordContext.putVariable("status", true);
            return true;
        }
        return false;

    }

    @Override
    @LogRecord(type = AID_TYPE, subType = AID_FOLLOW_SUB_TYPE, bizNo = "{{#aid.id}}",
            success = AID_FOLLOW_SUCCESS)
    public boolean follow(AidWorkFollowReqVO reqVO) {
        AidWorkDO aidWorkDO = validateAidWorkExists(reqVO.getId());
        aidWorkDO.setAuditTime(LocalDateTime.now());
        // 获取当前用户
        String username = SecurityFrameworkUtils.getLoginUserNickname();
        aidWorkDO.setAuditUser(username);
        aidWorkDO.setProcessStatus(AidWorkProcessStatusEnum.AID_WORK_PROCESS_STATUS_ENUM_1.getStatus());

        int i = aidWorkMapper.updateById(aidWorkDO);
        if (i > 0) {
            // 记录操作日志上下文
            LogRecordContext.putVariable("aid", aidWorkDO);
            return true;
        }
        return false;

    }

    @Override
    public AidWorkChartRespVO chart(@Valid AidWorkChartReqVO reqVO) {
        AidWorkChartRespVO vo = new AidWorkChartRespVO();

        LocalDateTime startTime = reqVO.getStartTime();
        LocalDateTime endTime = reqVO.getEndTime();
        if (startTime.isBefore(LocalDateTime.of(2020, 1, 1, 0, 0, 0))) {
            startTime = null;
        }
        if (endTime.isBefore(LocalDateTime.of(2020, 1, 1, 0, 0, 0))) {
            endTime = null;
        }

        // 1. 卡片数据
        JSONObject baseApplyVo = aidWorkMapper.selectTotalCount(startTime, endTime, "");
        if (null != baseApplyVo) {
            Long count = baseApplyVo.getLong("count");
            Double amount = baseApplyVo.getDouble("total");
            vo.setTotalApplyCount(count);
            vo.setTotalApplyAmount(amount);
        }
        JSONObject passJson = aidWorkMapper.selectTotalCount(startTime, endTime, AidWorkStatusEnum.AID_WORK_STATUS_1.getStatus());
        if (null != passJson) {
            Long count = baseApplyVo.getLong("count");
            Double amount = baseApplyVo.getDouble("total");
            vo.setTotalPassCount(count);
            vo.setTotalGrantAmount(amount);
        }

        List<Map<String, Long>> typeMap = aidWorkMapper.selectTypeCount(startTime, endTime);
        Map<String, Long> newTypeMap = new HashMap<>();
        typeMap.forEach(map -> {
            String type = String.valueOf(map.get("type"));
            String nameByKey = AidWorkTypeEnum.getNameByKey(type);
            Long count = map.get("count");
            newTypeMap.put(nameByKey, count);
        });

        vo.setTypeCountMap(newTypeMap);
        List<Map<String, Long>> statusMap = aidWorkMapper.selectStatusCount(startTime, endTime);
        Map<String, Long> newStatusMap = new HashMap<>();
        // 将statusMap里的status转为枚举的 name
        statusMap.forEach((map) -> {
            String status = String.valueOf(map.get("status"));
            String nameByKey = AidWorkStatusEnum.getNameByKey(status);
            Long count = map.get("count");
            newStatusMap.put(nameByKey, count);
        });
        vo.setStatusCountMap(newStatusMap);

        return vo;
    }

    @Override
    public List<AidWorkApplyCountRespVO> applyCount(@Valid AidWorkApplyCountReqVO reqVO) {
        LocalDateTime startTime = reqVO.getStartTime();
        LocalDateTime endTime = reqVO.getEndTime();
        if (startTime.isBefore(LocalDateTime.of(2020, 1, 1, 0, 0, 0))) {
            startTime = null;
        }
        if (endTime.isBefore(LocalDateTime.of(2020, 1, 1, 0, 0, 0))) {
            endTime = null;
        }

        List<AidWorkApplyCountRespVO> list = new ArrayList<>();
        // 1. 卡片数据

        List<Map<String, Long>> typeMap = aidWorkMapper.selectTypeCount(startTime, endTime);
        LocalDateTime finalStartTime = startTime;
        LocalDateTime finalEndTime = endTime;
        typeMap.forEach(map -> {
            JSONObject data = new JSONObject();
            String type = String.valueOf(map.get("type"));
            String nameByKey = AidWorkTypeEnum.getNameByKey(type);

            AidWorkApplyCountRespVO vo = new AidWorkApplyCountRespVO();
            vo.setName(nameByKey);
            vo.setType(type);
            Long countTotal = map.get("count");
            vo.setApplyCount( countTotal);

            List<JSONObject> statusList = aidWorkMapper.selectStatusCountByType(finalStartTime, finalEndTime, type);
            statusList.forEach(statusJson -> {

                String status = String.valueOf(statusJson.get("status"));
                if (status.equals(AidWorkStatusEnum.AID_WORK_STATUS_2.getStatus())) {
                    // 已完成数
                    Long finishCount = statusJson.getLong("count");
                    vo.setFinishCount(finishCount);
                    // 计算该类型办理完成率，并保留两位小数
                    BigDecimal finishCountBigDecimal = BigDecimal.valueOf(finishCount);
                    BigDecimal countTotalBigDecimal = BigDecimal.valueOf(countTotal);
                    BigDecimal finishRate = finishCountBigDecimal.divide(countTotalBigDecimal, 2, BigDecimal.ROUND_HALF_UP);
                    vo.setFinishRate(finishRate);
                }
            });
            list.add(vo);
        });

        return list;
    }

}