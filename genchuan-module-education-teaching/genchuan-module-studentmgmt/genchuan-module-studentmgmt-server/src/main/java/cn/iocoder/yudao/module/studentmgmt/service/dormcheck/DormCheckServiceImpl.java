package cn.iocoder.yudao.module.studentmgmt.service.dormcheck;

import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.behaviormgmt.BehaviorMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormcheck.DormCheckDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.behaviormgmt.BehaviorMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.dormcheck.DormCheckMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo.StudentInfoMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.DormCheckAbnormalTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.DormCheckCheckStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.DormCheckStatusEnum;
import cn.iocoder.yudao.module.system.api.dict.DictDataApi;
import com.alibaba.fastjson.JSONObject;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.DORM_CHECK_NOT_EXISTS;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

/**
 * 宿舍考勤 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DormCheckServiceImpl implements DormCheckService {

    @Resource
    private DormCheckMapper dormCheckMapper;

    @Resource
    BehaviorMgmtMapper behaviorMgmtMapper;
    @Resource
    StudentInfoMapper studentInfoMapper;

    @Resource
    private DictDataApi dictDataApi;

    @Override
    @LogRecord(type = DORM_CHECK_TYPE, subType = DORM_CHECK_CREATE_SUB_TYPE, bizNo = "{{#assessMgmt.id}}",
            success = DORM_CHECK_CREATE_SUB_TYPE_SUCCESS)
    public boolean createDormCheck(DormCheckCreateReqVO checkReqVO) {
        int total = 0;
        Long[] studentIds = checkReqVO.getStudentIds();
        LocalDateTime checkTime = checkReqVO.getCheckTime();
        if (checkTime == null) {
            checkTime = LocalDateTime.now();
        }
        // 查询这些学生的所有考勤记录
//        List<DormCheckDO> dormCheckList = dormCheckMapper.selectList(
//                new LambdaQueryChainWrapper<>(DormCheckDO.class)
//                .in(DormCheckDO::getStudentId, studentIds)
//        );

        // 检查该学生是否在当前时间段内有请假
        List<BehaviorMgmtDO> leaveRecordList = behaviorMgmtMapper.selectLeaveRecordByStudentIds(studentIds);

        // 创建
        for (Long studentId : studentIds) {
            DormCheckDO dormCheck = new DormCheckDO();
            dormCheck.setStudentId(studentId);
            // 判断该学生是否请假，即该学生是否在leaveRecordList里有记录
            if (leaveRecordList != null && leaveRecordList.contains(studentId)) {
                // 有请假记录，请假学生自动标记为正常状态
                dormCheck.setCheckStatus(DormCheckStatusEnum.DORM_CHECK_STATUS_0.getStatus());
                dormCheck.setCheckTime(checkTime);
                dormCheck.setStatus(DormCheckStatusEnum.DORM_CHECK_STATUS_0.getStatus());

            } else {
                // 没有请假记录
                dormCheck.setStatus(DormCheckStatusEnum.DORM_CHECK_STATUS_1.getStatus());
                dormCheck.setCheckTime(checkTime);
                // TODO 根据打卡时间自动判定考勤状态与异常类型
                dormCheck.setCheckStatus(DormCheckCheckStatusEnum.DORM_CHECK_CHECK_STATUS_2.getStatus());
                dormCheck.setAbnormalType(DormCheckAbnormalTypeEnum.DORM_CHECK_ABNORMAL_TYPE_2.getStatus());
                // 自动计算本次考勤的整体在寝率
            }
            int insert = dormCheckMapper.insert(dormCheck);
            total += insert;

        }
        if (total == studentIds.length) {
            return true;
        }
        return false;
    }


    @Override
    public void updateDormCheck(DormCheckSaveReqVO updateReqVO) {
        // 校验存在
        validateDormCheckExists(updateReqVO.getId());
        // 更新
        DormCheckDO updateObj = BeanUtils.toBean(updateReqVO, DormCheckDO.class);
        dormCheckMapper.updateById(updateObj);
    }

    @Override
    public void deleteDormCheck(Long id) {
        // 校验存在
        validateDormCheckExists(id);
        // 删除
        dormCheckMapper.deleteById(id);
    }

    @Override
    public void deleteDormCheckListByIds(List<Long> ids) {
        // 删除
        dormCheckMapper.deleteByIds(ids);
    }


    private DormCheckDO validateDormCheckExists(Long id) {
        DormCheckDO dormCheck = dormCheckMapper.selectById(id);
        if (dormCheck == null) {
            throw exception(DORM_CHECK_NOT_EXISTS);
        }
        return dormCheck;
    }

    @Override
    public DormCheckDO getDormCheck(Long id) {
        return dormCheckMapper.selectById(id);
    }

    @Override
    public PageResult<DormCheckDO> getDormCheckPage(DormCheckPageReqVO pageReqVO) {
        return dormCheckMapper.selectPage(pageReqVO);
    }

    @Override
    @LogRecord(type = DORM_CHECK_TYPE, subType = DORM_CHECK_RECHECK_SUB_TYPE, bizNo = "{{#id}}",
            success = DORM_CHECK_RECHECK_SUB_TYPE_SUCCESS)
    public Boolean recheck(DormCheckRecheckReqVO reqVO) {
        int total = 0;
        for (Long id : reqVO.getIds()) {
            // 校验存在
            DormCheckDO dormCheck = validateDormCheckExists(id);
            String status = dormCheck.getStatus();
            if (status.equals(DormCheckStatusEnum.DORM_CHECK_STATUS_0.getStatus())) {
                // 状态为正常，则无需补卡
                continue;
            }

            // 设置异常类型为无，记录补卡人、补卡时间；
            dormCheck.setAbnormalType(DormCheckAbnormalTypeEnum.DORM_CHECK_ABNORMAL_TYPE_0.getStatus());
            String repairUser = reqVO.getRepairUser();
            if (StringUtils.isNotBlank(repairUser)) {
                dormCheck.setRepairUser(repairUser);
            } else {
                // 获取当前登录用户
                dormCheck.setRepairUser(SecurityFrameworkUtils.getLoginUserNickname());
            }
            LocalDateTime repairTime = reqVO.getRepairTime();
            if (repairTime != null) {
                dormCheck.setRepairTime(repairTime);
            } else {
                dormCheck.setRepairTime(LocalDateTime.now());
            }
            dormCheck.setStatus(DormCheckStatusEnum.DORM_CHECK_STATUS_0.getStatus());
            // 更新
            int i = dormCheckMapper.updateById(dormCheck);
            total += i;
            // 记录操作日志上下文
            LogRecordContext.putVariable("id", id);
        }
        if (total > 0) {
            return true;
        }
        return false;
    }

    @Override
    @LogRecord(type = DORM_CHECK_TYPE, subType = DORM_CHECK_PUSH_SUB_TYPE, bizNo = "{{#id}}",
            success = DORM_CHECK_PUSH_SUB_TYPE_SUCCESS)
    public Boolean push(DormCheckPushReqVO reqVO) {
        int total = 0;
        LocalDateTime pushTime = reqVO.getPushTime();
        if (pushTime == null) {
            pushTime = LocalDateTime.now();
        }
        for (Long id : reqVO.getIds()) {
            // 校验存在
            DormCheckDO dormCheck = validateDormCheckExists(id);
//            String status = dormCheck.getStatus();
            // 自动校验记录是否为异常状态，正常记录不允许推送
//            if (status.equals(DormCheckStatusEnum.DORM_CHECK_STATUS_0.getStatus())) {
//                // 状态为正常，则无需推送
//                throw exception(id + "，状态为正常，无需推送");
////                continue;
//            }
            dormCheck.setPushTime(pushTime);
            // TODO 同步考勤异常信息与预警提醒；

            int i = dormCheckMapper.updateById(dormCheck);
            total += i;
            // 记录操作日志上下文
            LogRecordContext.putVariable("id", id);
        }
        if (total > 0) {
            return true;
        }

        return false;

    }

    @Override
    public DormCheckChartRespVO chart(DormCheckChartReqVO reqVO) {
        DormCheckChartRespVO vo = new DormCheckChartRespVO();

//        LocalDateTime checkTime  = reqVO.getCheckTime();
        LocalDate checkTime = reqVO.getCheckTime();
//        Date checkTime = reqVO.getCheckTime();

        if (checkTime == null) {
//            checkTime = LocalDateTime.now();
            checkTime = LocalDate.now();
//            checkTime = new Date();
        }
        // 转换为 LocalDate（只保留年月日）
//        LocalDate localDate = checkTime.toLocalDate();
//        LocalDate localDate = LocalDate.parse(checkTime);

        // 1. 卡片数据
        //totalCount (integer): 本期考评总记录数。
        vo = dormCheckMapper.selectTotalCheckCount(checkTime, DormCheckStatusEnum.DORM_CHECK_STATUS_0.getStatus());
        if (vo == null) {
            vo = new DormCheckChartRespVO();
        }
        if (vo.getNormalCount() == null) {
            vo.setNormalCount(0);
        }
        if (vo.getAbnormalCount() == null) {
            vo.setAbnormalCount(0);
        }
        if (vo.getWarningCount() == null) {
            vo.setWarningCount(0);
        }
        if (vo.getInRate() == null) {
            vo.setInRate(BigDecimal.ONE);
        }
        // TODO 预警人数
        // abnormalStats 异常类型统计列表
        List<DictDataRespDTO> dictDataList = dictDataApi.getDictDataList(DormCheckAbnormalTypeEnum.DICT_TYPE).getData();
        List<JSONObject> abnormalStatsList = dormCheckMapper.getAbnormalStatsList(checkTime);
        List<JSONObject> abnormalStats = new ArrayList<>();
        if (abnormalStatsList != null & abnormalStatsList.size() > 0) {

            // 获取字典数据
            abnormalStatsList.forEach(item -> {
                String dictDataLabel = "";
                String type = item.getString("abnormal_type");
                if (dictDataList != null) {
                    for (DictDataRespDTO dictData : dictDataList) {
                        if (dictData.getValue().equals(type)) {
                            dictDataLabel = dictData.getLabel();
                            break;
                        }
                    }
                }
                item.put("name", dictDataLabel);
                item.put("count", item.getInteger("count"));
                abnormalStats.add(item);
            });
        }
        else {
            // 获取字典数据
            String dictDataLabel = "";
            if (dictDataList != null) {
                for (DictDataRespDTO dictData : dictDataList) {
                    JSONObject item = new JSONObject();
                    dictDataLabel = dictData.getLabel();
                    item.put("name", dictDataLabel);
                    item.put("count", 0);
                    abnormalStats.add(item);
                }
            }
        }

        vo.setAbnormalStats(abnormalStats);

        return vo;
    }

    @Override
    public DormCheckChartCountRespVO checkCount(DormCheckChartCountReqVO reqVO) {
        DormCheckChartCountRespVO vo = new DormCheckChartCountRespVO();
        String grade = reqVO.getGrade();
        LocalDate checkTime = reqVO.getCheckTime();
        // 查询该年级下的甩的班级
        List<String> classList = studentInfoMapper.selectAllClass(grade);
//        List<String> classNames = new ArrayList<>();
        List<Integer> abnormalCount = new ArrayList<>();
        List<BigDecimal> inRate = new ArrayList<>();
        for (String className : classList) {
            // 查询该年级下的所有学生的考勤异常人数和在寝率
            JSONObject coreIndex = dormCheckMapper.getCoreIndex(className, checkTime);
            if (coreIndex == null) {
                abnormalCount.add(0);
                inRate.add(new BigDecimal("100.00"));
                continue;
            }
            abnormalCount.add(coreIndex.getInteger("abnormalCount"));
            inRate.add(coreIndex.getBigDecimal("inRate"));
        }

        vo.setLabels(classList);
        vo.setAbnormalCount(abnormalCount);
        vo.setInRate(inRate);
        return vo;
    }


}