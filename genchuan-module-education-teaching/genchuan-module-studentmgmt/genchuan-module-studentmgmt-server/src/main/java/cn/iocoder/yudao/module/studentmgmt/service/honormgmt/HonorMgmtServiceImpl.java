package cn.iocoder.yudao.module.studentmgmt.service.honormgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.honormgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.honormgmt.HonorMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.honormgmt.HonorMgmtMapper;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.HONOR_MGMT_NOT_EXISTS;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

/**
 * 荣誉管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class HonorMgmtServiceImpl implements HonorMgmtService {

    @Resource
    private HonorMgmtMapper honorMgmtMapper;

    @Override
    @LogRecord(type = STUDENT_HONOR_TYPE, subType = STUDENT_HONOR_CREATE_SUB_TYPE, bizNo = "{{#honorMgmt.id}}",
            success = STUDENT_HONOR_CREATE_SUCCESS)
    public Long createHonorMgmt(HonorMgmtSaveReqVO createReqVO) {
        // 插入
        HonorMgmtDO honorMgmt = BeanUtils.toBean(createReqVO, HonorMgmtDO.class);
        honorMgmtMapper.insert(honorMgmt);

        // 记录操作日志上下文
        LogRecordContext.putVariable("honorMgmt", honorMgmt);

        // 返回
        return honorMgmt.getId();
    }

    @Override
    @LogRecord(type = STUDENT_HONOR_TYPE, subType = STUDENT_HONOR_UPDATE_SUB_TYPE, bizNo = "{{#honorMgmt.id}}",
            success = STUDENT_HONOR_UPDATE_SUCCESS)
    public void updateHonorMgmt(HonorMgmtSaveReqVO updateReqVO) {
        // 校验存在
        HonorMgmtDO honorMgmtDO = validateHonorMgmtExists(updateReqVO.getId());
        // 更新
        HonorMgmtDO updateObj = BeanUtils.toBean(updateReqVO, HonorMgmtDO.class);
        honorMgmtMapper.updateById(updateObj);
        // 记录操作日志上下文
        LogRecordContext.putVariable("honorMgmt", honorMgmtDO);
        LogRecordContext.putVariable("honorName", honorMgmtDO.getHonorName());
    }

    @LogRecord(type = STUDENT_HONOR_TYPE, subType = STUDENT_HONOR_DELETE_SUB_TYPE, bizNo = "{{#id}}",
            success = STUDENT_HONOR_DELETE_SUCCESS)
    public void writeDeleteHonorMgmtLog(Long id, String honorName) {
        LogRecordContext.putVariable("honorName", honorName);
    }

    @Override
    @LogRecord(type = STUDENT_HONOR_TYPE, subType = STUDENT_HONOR_DELETE_SUB_TYPE, bizNo = "{{#id}}",
            success = STUDENT_HONOR_DELETE_SUCCESS)
    public void deleteHonorMgmt(Long id) {
        // 校验存在
        validateHonorMgmtExists(id);
        // 删除
        honorMgmtMapper.deleteById(id);
    }

    @Override
    public void deleteHonorMgmtListByIds(List<Long> ids) {
        List<HonorMgmtDO> honorMgmtDOS = honorMgmtMapper.selectByIds(ids);
        // 删除
        honorMgmtMapper.deleteByIds(ids);
        // 逐条追加变更日志
        for (HonorMgmtDO detail : honorMgmtDOS) {
            HonorMgmtDO info = honorMgmtMapper.selectById(detail.getId());
            if (info != null) {
                writeDeleteHonorMgmtLog(info.getId(), info.getHonorName());
            }
        }
    }


    private HonorMgmtDO validateHonorMgmtExists(Long id) {
        HonorMgmtDO honorMgmtDO = honorMgmtMapper.selectById(id);
        if (honorMgmtDO == null) {
            throw exception(HONOR_MGMT_NOT_EXISTS);
        }
        return honorMgmtDO;
    }

    @Override
    public HonorMgmtDO getHonorMgmt(Long id) {
        return honorMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<HonorMgmtDO> getHonorMgmtPage(HonorMgmtPageReqVO pageReqVO) {
        return honorMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    @LogRecord(type = STUDENT_HONOR_TYPE, subType = STUDENT_HONOR_PUSH_SUB_TYPE, bizNo = "{{#honor.id}}",
            success = STUDENT_HONOR_PUSH_SUCCESS)
    public boolean pushHonorMgmt(HonorMgmtPushReqVO reqVO) {
        HonorMgmtDO honorMgmtDO = honorMgmtMapper.selectById(reqVO.getId());
        honorMgmtDO.setPushTime(LocalDateTime.now());
        honorMgmtDO.setStatus("已推送");
        int i = honorMgmtMapper.updateById(honorMgmtDO);
        // 记录操作日志上下文
        LogRecordContext.putVariable("honor", honorMgmtDO);
        LogRecordContext.putVariable("honorName", honorMgmtDO.getHonorName());
        if (i > 0) {
            return true;
        }
        return false;
    }

    /**
     * 荣誉信息分布看板
     *
     * @param reqVO
     * @return
     */
    @Override
    public HonorMgmtChartRespVO getChart(HonorMgmtChartReqVO reqVO) {

        HonorMgmtChartRespVO vo = new HonorMgmtChartRespVO();

        String grade = reqVO.getGrade();
        String major = reqVO.getMajor();

        // 1. 卡片数据
        vo.setTotalHonorCount(honorMgmtMapper.selectTotalHonorCount(grade, major, "", ""));
        // 待审核
        vo.setPendingAuditCount(honorMgmtMapper.selectTotalHonorCount(grade, major, "1", ""));
        // 优秀学生
        vo.setExcellentStudentCount(honorMgmtMapper.selectTotalHonorCount(grade, major, "", "1"));
        // 奖学金
        vo.setScholarshipCount(honorMgmtMapper.selectTotalHonorCount(grade, major, "", "2"));
        // 竞赛获奖
        vo.setCompetitionCount(honorMgmtMapper.selectTotalHonorCount(grade, major, "", "3"));

        vo.setTodayPushCount(honorMgmtMapper.selectTodayPushCount(grade, major));

        return vo;
    }

    @LogRecord(type = STUDENT_HONOR_TYPE, subType = STUDENT_HONOR_DELETE_SUB_TYPE, bizNo = "{{#honor.id}}",
            success = STUDENT_HONOR_DELETE_SUCCESS)
    public void deleteHonorLog(HonorMgmtDO honor, String honorName) {
        // 记录操作日志上下文
        LogRecordContext.putVariable("honor", honor);
        LogRecordContext.putVariable("honorName", honorName);
    }

    @LogRecord(type = STUDENT_HONOR_TYPE, subType = STUDENT_HONOR_UPDATE_AUDIT_STATUS_SUB_TYPE, bizNo = "{{#honor.id}}",
            success = STUDENT_HONOR_UPDATE_AUDIT_STATUS_SUCCESS)
    public void auditHonorLog(HonorMgmtDO honor, String honorName) {
        // 记录操作日志上下文
        LogRecordContext.putVariable("honor", honor);
        LogRecordContext.putVariable("honorName", honorName);
    }

    @Override
    @LogRecord(type = STUDENT_HONOR_TYPE, subType = STUDENT_HONOR_UPDATE_AUDIT_STATUS_SUB_TYPE, bizNo = "{{#honorMgmt.id}}",
            success = STUDENT_HONOR_UPDATE_AUDIT_STATUS_SUCCESS)
    public boolean audit(HonorMgmtAuditReqVO reqVO, LoginUser loginUser) {
        List<Long> ids = reqVO.getIds();
        List<HonorMgmtDO> honorMgmtDOS = honorMgmtMapper.selectByIds(ids);
        String auditRemark = reqVO.getAuditRemark();
        String auditUser = loginUser.getId()+"";
        String status = reqVO.getStatus();
        Integer i = honorMgmtMapper.audit(ids, auditRemark, auditUser, status);

        // 记录操作日志上下文
        if (i>0 && i.equals(ids.size())) {
            // 逐条追加变更日志
            for (HonorMgmtDO detail : honorMgmtDOS) {
                HonorMgmtDO info = honorMgmtMapper.selectById(detail.getId());
                if (info != null) {
                    // 记录操作日志上下文
                    auditHonorLog(info, info.getHonorName());
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public PageResult<HonorMgmtPageRespVO> getHonorMgmtJoinPage(HonorMgmtPageReqVO pageReqVO) {
        return honorMgmtMapper.selectJoinPage(pageReqVO);
    }

}