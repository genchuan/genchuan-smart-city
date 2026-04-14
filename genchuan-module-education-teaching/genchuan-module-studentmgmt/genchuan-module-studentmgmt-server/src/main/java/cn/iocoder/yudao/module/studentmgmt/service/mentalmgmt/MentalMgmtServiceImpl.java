package cn.iocoder.yudao.module.studentmgmt.service.mentalmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo.MentalMgmtConsultReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo.MentalMgmtJoinPageRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo.MentalMgmtPageReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo.MentalMgmtSaveReqVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.mentalmgmt.MentalMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.mentalmgmt.MentalMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo.StudentInfoMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.MentalStatusEnum;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
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
        if (mentalMgmtDO.getConsultTime() != null) {
            LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
            String username = loginUser != null ? String.valueOf(loginUser.getId()) : null;
            LocalDateTime now = LocalDateTime.now();
            mentalMgmtDO.setConsultTime(LocalDateTimeUtils.parse(reqVO.getConsultTime()));
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
            LogRecordContext.putVariable("username", username);
            return true;
        }

        return false;
    }

}