package cn.iocoder.yudao.module.studentmgmt.service.mentalmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.mentalmgmt.MentalMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.mentalmgmt.MentalMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo.StudentInfoMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.MentalMentalStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.MentalRiskLevelEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.MentalStatusEnum;
import com.alibaba.fastjson.JSONObject;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.MENTAL_MGMT_NOT_EXISTS;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

/**
 * 心理管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MentalMgmtServiceImpl implements MentalMgmtService {

    @Resource
    private MentalMgmtMapper mentalMgmtMapper;
    @Resource
    private StudentInfoMapper studentInfoMapper;

    @Override
    @LogRecord(type = VIOLATE_TYPE, subType = VIOLATE_CREATE_SUB_TYPE, bizNo = "{{#mental.id}}",
            success = VIOLATE_CREATE_SUCCESS)
    public Long createMentalMgmt(MentalMgmtSaveReqVO createReqVO) {
        // 插入
        MentalMgmtDO mentalMgmt = BeanUtils.toBean(createReqVO, MentalMgmtDO.class);
        mentalMgmtMapper.insert(mentalMgmt);


        // 查询所有学生的姓名
        StudentInfoDO studentInfoDO = studentInfoMapper.selectById(mentalMgmt.getStudentId());
        // 获取所有学生的姓名
        String studentName = studentInfoDO.getName();

        // 记录操作日志上下文
        LogRecordContext.putVariable("mental", mentalMgmt);
        LogRecordContext.putVariable("studentName", studentName);

        // 返回
        return mentalMgmt.getId();
    }

    @Override
    public void updateMentalMgmt(MentalMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateMentalMgmtExists(updateReqVO.getId());
        // 更新
        MentalMgmtDO updateObj = BeanUtils.toBean(updateReqVO, MentalMgmtDO.class);
        mentalMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteMentalMgmt(Long id) {
        // 校验存在
        validateMentalMgmtExists(id);
        // 删除
        mentalMgmtMapper.deleteById(id);
    }

    @Override
    public void deleteMentalMgmtListByIds(List<Long> ids) {
        // 删除
        mentalMgmtMapper.deleteByIds(ids);
    }


    private MentalMgmtDO validateMentalMgmtExists(Long id) {
        MentalMgmtDO mentalMgmtDO = mentalMgmtMapper.selectById(id);
        if (mentalMgmtDO == null) {
            throw exception(MENTAL_MGMT_NOT_EXISTS);
        }
        return mentalMgmtDO;
    }

    @Override
    public MentalMgmtDO getMentalMgmt(Long id) {
        return mentalMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<MentalMgmtDO> getMentalMgmtPage(MentalMgmtPageReqVO pageReqVO) {
        return mentalMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<MentalMgmtJoinPageRespVO> getMentalMgmtJoinPage(MentalMgmtPageReqVO pageReqVO) {
        return mentalMgmtMapper.selectJoinPage(pageReqVO);
    }

    @Override
    @LogRecord(type = MENTAL_TYPE, subType = MENTAL_CONSULT_SUB_TYPE, bizNo = "{{#mental.id}}",
            success = MENTAL_CONSULT_SUCCESS)
    public boolean consult(MentalMgmtConsultReqVO reqVO, LoginUser user) {
        MentalMgmtDO mentalMgmtDO = validateMentalMgmtExists(reqVO.getId());
        String status = mentalMgmtDO.getStatus();
        if (status.equals(MentalStatusEnum.MENTAL_STATUS_WAIT_EVALUATE.getStatus())) {
//            LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
//            String username = SecurityFrameworkUtils.getLoginUserNickname();
            LocalDateTime now = LocalDateTime.now();
            mentalMgmtDO.setConsultTime(reqVO.getConsultTime());
            mentalMgmtDO.setUpdateTime(now);
            // 查询所有学生的姓名
            StudentInfoDO studentInfoDO = studentInfoMapper.selectById(mentalMgmtDO.getStudentId());
            // 获取所有学生的姓名
            String studentName = studentInfoDO.getName();
            mentalMgmtDO.setStatus(MentalStatusEnum.MENTAL_STATUS_CONSULTING.getStatus());
            mentalMgmtMapper.updateById(mentalMgmtDO);

            // 记录操作日志上下文
            LogRecordContext.putVariable("mental", mentalMgmtDO);
            LogRecordContext.putVariable("studentName", studentName);
            return true;
        }
        else{
            throw exception("该学生不是待评估状态，无法预约！");
        }
    }

    @Override
    @LogRecord(type = MENTAL_TYPE, subType = MENTAL_INTERVENE_SUB_TYPE, bizNo = "{{#mental.id}}",
            success = MENTAL_INTERVENE_SUCCESS)
    public boolean intervene(@Valid MentalMgmtInterveneReqVO reqVO) {
        MentalMgmtDO mentalMgmtDO = validateMentalMgmtExists(reqVO.getId());
        String status = mentalMgmtDO.getStatus();
        if (status.equals(MentalStatusEnum.MENTAL_STATUS_CONSULTING.getStatus()) ) {
//            LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
//            String username = SecurityFrameworkUtils.getLoginUserNickname();
            LocalDateTime now = LocalDateTime.now();
            mentalMgmtDO.setInterveneTime(reqVO.getInterveneTime());
            mentalMgmtDO.setUpdateTime(now);
            mentalMgmtDO.setInterveneContent(reqVO.getInterveneContent());
            mentalMgmtDO.setStatus(MentalStatusEnum.MENTAL_STATUS_INTERVENED.getStatus());
            int i = mentalMgmtMapper.updateById(mentalMgmtDO);

            // 查询学生的姓名
            StudentInfoDO studentInfoDO = studentInfoMapper.selectById(mentalMgmtDO.getStudentId());
            String studentName = studentInfoDO.getName();

            // 记录操作日志上下文
            LogRecordContext.putVariable("mental", mentalMgmtDO);
            LogRecordContext.putVariable("studentName", studentName);
//            System.out.println("optionUsername: " + username);
//            LogRecordContext.putVariable("optionUsername", "admin");
            if (i > 0) {
                return true;
            }
        }
        else{
            throw exception("该学生不是咨询中状态，无法干预！");
        }
        return false;
    }

    @Override
    @LogRecord(type = MENTAL_TYPE, subType = MENTAL_UPDATE_STATUS_SUB_TYPE, bizNo = "{{#mental.id}}",
            success = MENTAL_UPDATE_STATUS_SUCCESS)
    public boolean updateStatus(MentalMgmtUpdateStatusReqVO reqVO) {
        MentalMgmtDO mentalMgmtDO = validateMentalMgmtExists(reqVO.getId());

//            LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        String username = SecurityFrameworkUtils.getLoginUserNickname();
        LocalDateTime now = LocalDateTime.now();
        mentalMgmtDO.setUpdateTime(now);
        mentalMgmtDO.setMentalStatus(reqVO.getMentalStatus());
        mentalMgmtDO.setRiskLevel(reqVO.getRiskLevel());
        mentalMgmtDO.setUpdater(username);
        int i = mentalMgmtMapper.updateById(mentalMgmtDO);

        // 查询学生的姓名
        StudentInfoDO studentInfoDO = studentInfoMapper.selectById(mentalMgmtDO.getStudentId());
        String studentName = studentInfoDO.getName();
        if (i > 0) {
            // 记录操作日志上下文
            LogRecordContext.putVariable("mental", mentalMgmtDO);
            LogRecordContext.putVariable("studentName", studentName);
            LogRecordContext.putVariable("username", username);
            LogRecordContext.putVariable("status", MentalStatusEnum.getNameByKey(reqVO.getMentalStatus()));
            return true;
        }

        return false;
    }

    @Override
    public MentalMgmtChartRespVO chart() {
        MentalMgmtChartRespVO vo = new MentalMgmtChartRespVO();
        // 1. 卡片数据
        // totalCount (integer): 心理档案总数量。
        vo.setTotalCount(mentalMgmtMapper.selectTotalCount("", "", ""));
        // focusCount (integer): 心理状态关注的学生数量。
        vo.setFocusCount(mentalMgmtMapper.selectTotalCount(MentalMentalStatusEnum.MENTAL_MGMT_MENTAL_STATUS_FOCUS.getStatus(), "", ""));
        // highRiskCount (integer): 心理状态高危的学生数量。
        vo.setHighRiskCount(mentalMgmtMapper.selectTotalCount(MentalMentalStatusEnum.MENTAL_MGMT_MENTAL_STATUS_HIGH_RISK.getStatus(), "", ""));
        // lowRiskCount (integer): 风险等级低学生的数量。
        vo.setLowRiskCount(mentalMgmtMapper.selectTotalCount("", MentalRiskLevelEnum.MENTAL_MGMT_RISK_LEVEL_LOW.getStatus(), ""));
        // normalCount (integer): 心理状态正常的学生数量。
        vo.setNormalCount(mentalMgmtMapper.selectTotalCount(MentalMentalStatusEnum.MENTAL_MGMT_MENTAL_STATUS_NORMAL.getStatus(), "", ""));
        // midRiskCount (integer): 风险等级中的学生的数量。
        vo.setMidRiskCount(mentalMgmtMapper.selectTotalCount("", MentalRiskLevelEnum.MENTAL_MGMT_RISK_LEVEL_MEDIUM.getStatus(), ""));
        // highRiskLevelCount (integer): 风险等级高的学生的数量。
        vo.setHighRiskLevelCount(mentalMgmtMapper.selectTotalCount("", MentalRiskLevelEnum.MENTAL_MGMT_RISK_LEVEL_HIGH.getStatus(), ""));
        // waitEvaluateCount (integer): 待评估状态的档案数量。
        vo.setWaitEvaluateCount(mentalMgmtMapper.selectTotalCount("", "", MentalStatusEnum.MENTAL_STATUS_WAIT_EVALUATE.getStatus()));
        // consultingCount (integer): 咨询中状态的档案数量。
        vo.setConsultingCount(mentalMgmtMapper.selectTotalCount("", "", MentalStatusEnum.MENTAL_STATUS_CONSULTING.getStatus()));
        // intervenedCount (integer): 已干预状态的档案数量。
        vo.setIntervenedCount(mentalMgmtMapper.selectTotalCount("", "", MentalStatusEnum.MENTAL_STATUS_INTERVENED.getStatus()));
        // recent7DayCount (integer): 近 7 天新增心理档案数量。
        vo.setRecent7DayCount(mentalMgmtMapper.selectRecent7DayCount());
        return vo;
    }

    @Override
    public MentalMgmtStatusDistributionRespVO statusDistribution() {
        MentalMgmtStatusDistributionRespVO vo = new MentalMgmtStatusDistributionRespVO();
        // 1. 卡片数据
        // 心理状态分布数据
        List<JSONObject> statusList = mentalMgmtMapper.selectMentalStatusDistributionCount();
        // 对应的key值转换成枚举值
        statusList.forEach(item -> {
            item.put("name", MentalMentalStatusEnum.getNameByKey(item.getString("name")));
        });
        vo.setMentalStatusDistribution(statusList);

        // 风险等级分布数据
        List<JSONObject> riskList = mentalMgmtMapper.selectRiskLevelDistributionCount();
        riskList.forEach(item -> {
            item.put("name", MentalRiskLevelEnum.getNameByKey(item.getString("name")));
        });
        vo.setRiskLevelDistribution(riskList);

        return vo;
    }


}