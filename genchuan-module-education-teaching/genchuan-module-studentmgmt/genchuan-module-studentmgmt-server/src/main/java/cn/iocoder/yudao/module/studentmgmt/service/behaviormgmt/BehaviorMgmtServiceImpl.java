package cn.iocoder.yudao.module.studentmgmt.service.behaviormgmt;

import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.dormcheck.DormCheckMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo.StudentInfoMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.*;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import cn.iocoder.yudao.module.system.api.dict.DictDataApi;
import com.alibaba.fastjson.JSONObject;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.behaviormgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.behaviormgmt.BehaviorMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.behaviormgmt.BehaviorMgmtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

/**
 * 行为管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class BehaviorMgmtServiceImpl implements BehaviorMgmtService {

    @Resource
    private BehaviorMgmtMapper behaviorMgmtMapper;

    @Resource
    private DormCheckMapper dormCheckMapper;
    @Resource
    private StudentInfoMapper studentInfoMapper;
    @Resource
    private DeptApi deptApi;
    @Resource
    private DictDataApi dictDataApi;

    @Override
    public Long createBehaviorMgmt(BehaviorMgmtSaveReqVO createReqVO) {
        // 插入
        BehaviorMgmtDO behaviorMgmt = BeanUtils.toBean(createReqVO, BehaviorMgmtDO.class);
        behaviorMgmtMapper.insert(behaviorMgmt);

        // 返回
        return behaviorMgmt.getId();
    }

    @Override
    public void updateBehaviorMgmt(BehaviorMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateBehaviorMgmtExists(updateReqVO.getId());
        // 更新
        BehaviorMgmtDO updateObj = BeanUtils.toBean(updateReqVO, BehaviorMgmtDO.class);
        behaviorMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteBehaviorMgmt(Long id) {
        // 校验存在
        validateBehaviorMgmtExists(id);
        // 删除
        behaviorMgmtMapper.deleteById(id);
    }

    @Override
    public void deleteBehaviorMgmtListByIds(List<Long> ids) {
        // 删除
        behaviorMgmtMapper.deleteByIds(ids);
    }


    private BehaviorMgmtDO validateBehaviorMgmtExists(Long id) {
        BehaviorMgmtDO behaviorMgmtDO = behaviorMgmtMapper.selectById(id);
        if (behaviorMgmtDO == null) {
            throw exception(BEHAVIOR_MGMT_NOT_EXISTS);
        }
        return behaviorMgmtDO;
    }

    @Override
    public BehaviorMgmtDO getBehaviorMgmt(Long id) {
        return behaviorMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<BehaviorMgmtDO> getBehaviorMgmtPage(BehaviorMgmtPageReqVO pageReqVO) {
        return behaviorMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    @LogRecord(type = BEHAVIOR_TYPE, subType = BEHAVIOR_AUDIT_SUB_TYPE, bizNo = "{{#behavior.id}}",
            success = BEHAVIOR_AUDIT_SUCCESS)
    public boolean audit(BehaviorMgmtAuditReqVO reqVO) {
        Long[] ids = reqVO.getIds();
        int total = 0;
        for (Long id : ids) {
            BehaviorMgmtDO behaviorMgmtDO = validateBehaviorMgmtExists(id);
            behaviorMgmtDO.setAuditTime(LocalDateTime.now());
            // 获取当前用户
            //        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
            String username = SecurityFrameworkUtils.getLoginUserNickname();
            behaviorMgmtDO.setAuditUser(username);
            behaviorMgmtDO.setStatus(reqVO.getStatus());
            behaviorMgmtDO.setRemark(reqVO.getRemark());

            int i = behaviorMgmtMapper.updateById(behaviorMgmtDO);
            LogRecordContext.putVariable("behavior", behaviorMgmtDO);
            total += i;
        }
        if (total > 0) {
            // 记录操作日志上下文
            return true;
        }
        return false;

    }

    @Override
    @LogRecord(type = BEHAVIOR_TYPE, subType = BEHAVIOR_CANCEL_SUB_TYPE, bizNo = "{{#behavior.id}}",
            success = BEHAVIOR_CANCEL_SUCCESS)
    public boolean cancel(BehaviorMgmtCancelReqVO reqVO) {
        BehaviorMgmtDO behaviorMgmtDO = validateBehaviorMgmtExists(reqVO.getId());
        behaviorMgmtDO.setAuditTime(LocalDateTime.now());
        // 获取当前用户
//        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        String username = SecurityFrameworkUtils.getLoginUserNickname();
        behaviorMgmtDO.setAuditUser(username);
        behaviorMgmtDO.setStatus(BehaviorStatusEnum.BEHAVIOR_MGMT_STATUS_3.getStatus());
        behaviorMgmtDO.setRemark(reqVO.getCancelReason());

        int i = behaviorMgmtMapper.updateById(behaviorMgmtDO);
        if (i > 0) {
            // 记录操作日志上下文
            LogRecordContext.putVariable("behavior", behaviorMgmtDO);
            return true;
        }
        return false;

    }

    @Override
    public BehaviorMgmtChartRespVO chart(BehaviorMgmtChartReqVO reqVO) {
        BehaviorMgmtChartRespVO vo = new BehaviorMgmtChartRespVO();
        // 1. 卡片数据
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
//        totalLeaveCount (integer): 本月请假总次数。
//        pendingAuditCount (integer): 待审批请假申请数。
//        attendanceAbnormalCount (integer): 考勤异常人数。
//        syncCount (integer): 已同步考勤记录数。
//        leaveTypeDistribution (array): 请假类型分布统计，包含类型名称、对应数量。
//        dailyLeaveTrend (array): 每日请假趋势，包含日期、对应请假人数。
        // 班级ID
        Long classId = reqVO.getClassId();
        // 判断是否有按班级查询
        if (null != classId) {
            // 通过classId 查询 System模块的 dept.id 的名称，减少关联查询
            CommonResult<DeptRespDTO> dept = deptApi.getDept(classId);
            DeptRespDTO data = dept.getData();
            if (data == null) {
                throw new ServiceException(ErrorCodeConstants.DEPT_NOT_EXISTS);
            }
            String className = data.getName();
            // 获取班级的请假次数
            vo.setTotalLeaveCount(behaviorMgmtMapper.selectTotalCountByClassName(startTime, endTime, "", "", "", className));
            vo.setPendingAuditCount(behaviorMgmtMapper.selectTotalCountByClassName(startTime, endTime, BehaviorStatusEnum.BEHAVIOR_MGMT_STATUS_0.getStatus(), "", "", className));
            vo.setSyncCount(behaviorMgmtMapper.selectTotalCountByClassName(startTime, endTime, "", BehaviorAttendanceSyncEnum.ATTENDANCE_SYNC_1.getStatus(), "", className));

            vo.setAttendanceAbnormalCount(dormCheckMapper.selectAbnormalCount(startTime, endTime, DormCheckStatusEnum.DORM_CHECK_STATUS_0.getStatus()));

            // 将key转换成name
            List<JSONObject> leaveTypeList = behaviorMgmtMapper.selectLeaveTypeCountByClassName(startTime, endTime, className);
            leaveTypeList.forEach(item -> {
                String dictDataLabel = "";
                CommonResult<List<DictDataRespDTO>> dictDataList = dictDataApi.getDictDataList(BehaviorLevelTypeEnum.DICT_TYPE);
                if (dictDataList.getData() != null) {
                    for (DictDataRespDTO dictData : dictDataList.getData()) {
                        if (dictData.getValue().equals(item.get("name"))) {
                            dictDataLabel = dictData.getLabel();
                            break;
                        }
                    }
                }
                item.put("name", dictDataLabel);
            });
            vo.setLeaveTypeDistribution(leaveTypeList);
            List<JSONObject> dailyLeaveTrendList = behaviorMgmtMapper.selectDailyLeaveTrendByClassName(startTime, endTime, className);
            vo.setDailyLeaveTrend(dailyLeaveTrendList);
            return vo;

        }

        vo.setTotalLeaveCount(behaviorMgmtMapper.selectTotalCount(startTime, endTime, "", "", ""));
        vo.setPendingAuditCount(behaviorMgmtMapper.selectTotalCount(startTime, endTime, BehaviorStatusEnum.BEHAVIOR_MGMT_STATUS_0.getStatus(), "", ""));
        vo.setSyncCount(behaviorMgmtMapper.selectTotalCount(startTime, endTime, "", BehaviorAttendanceSyncEnum.ATTENDANCE_SYNC_1.getStatus(), ""));

        vo.setAttendanceAbnormalCount(dormCheckMapper.selectAbnormalCount(startTime, endTime, DormCheckStatusEnum.DORM_CHECK_STATUS_0.getStatus()));

        // 将key转换成name
        List<JSONObject> leaveTypeList = behaviorMgmtMapper.selectLeaveTypeCount(startTime, endTime);
        leaveTypeList.forEach(item -> {
            String dictDataLabel = "";
            String status = item.getString("name");
            CommonResult<List<DictDataRespDTO>> dictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.BEHAVIOR_MGMT_LEAVE_TYPE.getType());
            if (dictDataList.getData() != null) {
                for (DictDataRespDTO dictData : dictDataList.getData()) {
                    if (dictData.getValue().equals(status)) {
                        dictDataLabel = dictData.getLabel();
                        break;
                    }
                }
            }
            item.put("name", dictDataLabel);
        });
        vo.setLeaveTypeDistribution(leaveTypeList);
        List<JSONObject> dailyLeaveTrendList = behaviorMgmtMapper.selectDailyLeaveTrend(startTime, endTime);
        vo.setDailyLeaveTrend(dailyLeaveTrendList);

        return vo;
    }

    @Override
    public BehaviorMgmtAttendanceCountRespVO attendanceCount(BehaviorMgmtAttendanceCountReqVO reqVO) {
        BehaviorMgmtAttendanceCountRespVO vo = new BehaviorMgmtAttendanceCountRespVO();
        // 1. 卡片数据
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
        // 年级
        String grade = reqVO.getGrade();
        List<JSONObject> list = new ArrayList<>();

        List<String> classList = studentInfoMapper.selectAllClass(grade);
        // 各班级统计数据，包含班级名称、请假次数、考勤异常人数。
        for (String className : classList) {
            JSONObject classStatistics = new JSONObject();
            // 获取班级的请假次数
            Integer leaveCount = behaviorMgmtMapper.selectTotalCountByClassName(startTime, endTime, BehaviorStatusEnum.BEHAVIOR_MGMT_STATUS_1.getStatus(), "", "", className);
            classStatistics.put("className", className);
            classStatistics.put("leaveCount", leaveCount);
            // 查询abnormalCount考勤异常人数
            Integer abnormalCount = dormCheckMapper.selectAbnormalCountByClassName(startTime, endTime, DormCheckStatusEnum.DORM_CHECK_STATUS_0.getStatus(), className);
            classStatistics.put("abnormalCount", abnormalCount);
            list.add(classStatistics);
        }
        vo.setClassStatistics(list);
        return vo;
    }

}