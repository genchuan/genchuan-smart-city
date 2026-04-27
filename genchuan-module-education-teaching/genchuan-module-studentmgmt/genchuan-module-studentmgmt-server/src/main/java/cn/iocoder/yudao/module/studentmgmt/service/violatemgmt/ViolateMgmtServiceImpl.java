package cn.iocoder.yudao.module.studentmgmt.service.violatemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.dict.core.DictFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.violatemgmt.ViolateMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo.StudentInfoMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.violatemgmt.ViolateMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentMgmtDictTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.ViolaateStatusEnum;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.STUDENT_INFO_NOT_EXISTS;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.VIOLATE_MGMT_NOT_EXISTS;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

/**
 * 违纪管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ViolateMgmtServiceImpl implements ViolateMgmtService {

    @Resource
    private ViolateMgmtMapper violateMgmtMapper;

    @Resource
    private StudentInfoMapper studentInfoMapper;

    @Override
    @LogRecord(type = VIOLATE_TYPE, subType = VIOLATE_CREATE_SUB_TYPE, bizNo = "{{#violate.id}}", success = VIOLATE_CREATE_SUCCESS)
    public Long createViolateMgmt(ViolateMgmtSaveReqVO createReqVO) {
        // 插入
        ViolateMgmtDO violateMgmt = BeanUtils.toBean(createReqVO, ViolateMgmtDO.class);
        int i = violateMgmtMapper.insert(violateMgmt);

        // 查询所有学生的姓名
        StudentInfoDO studentInfoDO = studentInfoMapper.selectById(violateMgmt.getStudentId());
        // 获取所有学生的姓名
        String studentName = studentInfoDO.getName();

        // 记录操作日志上下文
        LogRecordContext.putVariable("violate", violateMgmt);
        LogRecordContext.putVariable("studentName", studentName);

        // 返回
        return violateMgmt.getId();
    }

    @Override
    @LogRecord(type = VIOLATE_TYPE, subType = VIOLATE_UPDATE_SUB_TYPE, bizNo = "{{#updateReqVO.id}}", success = VIOLATE_UPDATE_SUCCESS)
    public void updateViolateMgmt(ViolateMgmtSaveReqVO updateReqVO) {
        // 校验存在
        ViolateMgmtDO violateMgmtDO = validateViolateMgmtExists(updateReqVO.getId());
        // 更新
        ViolateMgmtDO updateObj = BeanUtils.toBean(updateReqVO, ViolateMgmtDO.class);
        violateMgmtMapper.updateById(updateObj);
        // 记录操作日志上下文
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, BeanUtils.toBean(violateMgmtDO, ViolateMgmtSaveReqVO.class));
        LogRecordContext.putVariable("violate", violateMgmtDO);
    }

    @Override
    public void deleteViolateMgmt(Long id) {
        // 校验存在
        validateViolateMgmtExists(id);
        // 删除
        violateMgmtMapper.deleteById(id);
    }

    @Override
    public void deleteViolateMgmtListByIds(List<Long> ids) {
        // 删除
        violateMgmtMapper.deleteByIds(ids);
    }


    private ViolateMgmtDO validateViolateMgmtExists(Long id) {
        ViolateMgmtDO violateMgmtDO = violateMgmtMapper.selectById(id);
        if (violateMgmtDO == null) {
            throw exception(VIOLATE_MGMT_NOT_EXISTS);
        }
        return violateMgmtDO;
    }

    @Override
    public ViolateMgmtDO getViolateMgmt(Long id) {
        return violateMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<ViolateMgmtDO> getViolateMgmtPage(ViolateMgmtPageReqVO pageReqVO) {
        return violateMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    @LogRecord(type = VIOLATE_TYPE, subType = VIOLATE_UPDATE_AUDIT_STATUS_SUB_TYPE, bizNo = "{{#id}}", success = VIOLATE_UPDATE_AUDIT_STATUS_SUCCESS)
    public boolean auditViolateMgmtListByIds(List<Long> ids, Long userId) {
        List<ViolateMgmtDO> violateMgmtDOS = violateMgmtMapper.selectByIds(ids);

        String status = ViolaateStatusEnum.VIOLATE_MGMT_VIOLATE_STATUS_APPROVE.getStatus();
        Integer i = violateMgmtMapper.auditViolateMgmtListByIds(ids, status, userId);

        if (i > 0 && i == ids.size()) {
            // 取出violateMgmtDOS里的所有学生的ID
            List<Long> studentIds = violateMgmtDOS.stream().map(ViolateMgmtDO::getStudentId).collect(Collectors.toList());
            // 查询所有学生的姓名
            List<StudentInfoDO> studentInfoDOS = studentInfoMapper.selectList(new QueryWrapper<StudentInfoDO>().in("id", studentIds));
            // 获取所有学生的姓名
            List<String> studentName = studentInfoDOS.stream().map(StudentInfoDO::getName).collect(Collectors.toList());
            // 记录操作日志上下文
            LogRecordContext.putVariable("id", ids.get(0));
            LogRecordContext.putVariable("studentName", studentName);
            LogRecordContext.putVariable("status", true);
            return true;
        }
        return false;
    }

    @Override
    @LogRecord(type = VIOLATE_TYPE, subType = VIOLATE_PUSH_SUB_TYPE, bizNo = "{{#id}}", success = VIOLATE_PUSH_SUCCESS)
    public Boolean push(Long id, Long userId) {
        ViolateMgmtDO violateMgmtDO = violateMgmtMapper.selectById(id);
        if (violateMgmtDO != null) {
            violateMgmtDO.setPushTime(LocalDateTime.now());
            int i = violateMgmtMapper.updateById(violateMgmtDO);

            if (i > 0) {
                // 查询所有学生的姓名
                StudentInfoDO studentInfoDO = studentInfoMapper.selectById(violateMgmtDO.getStudentId());
                // 获取学生的姓名
                if (studentInfoDO == null) {
                    throw exception(STUDENT_INFO_NOT_EXISTS);
                }
                String studentName = studentInfoDO.getName();

                // 记录操作日志上下文
                LogRecordContext.putVariable("id", id);
                LogRecordContext.putVariable("studentName", studentName);
                LogRecordContext.putVariable("status", true);
                return true;
            }
        }
        return null;
    }

    @Override
    public PageResult<ViolateMgmtPageRespVO> getViolateMgmtPageVo(ViolateMgmtPageReqVO reqVO) {
        return violateMgmtMapper.selectJoinPage(reqVO);
    }

    @Override
    @LogRecord(type = VIOLATE_TYPE, subType = VIOLATE_WARN_SUB_TYPE, bizNo = "{{#id}}", success = VIOLATE_WARN_SUCCESS)
    public Boolean warn(Long id, Long userId) {
        ViolateMgmtDO violateMgmtDO = violateMgmtMapper.selectById(id);
        if (violateMgmtDO != null) {
            violateMgmtDO.setWarnTime(LocalDateTime.now());
            violateMgmtDO.setStatus(ViolaateStatusEnum.VIOLATE_MGMT_VIOLATE_STATUS_WARN.getStatus());
            int i = violateMgmtMapper.updateById(violateMgmtDO);

            if (i > 0) {
                // 查询所有学生的姓名
                StudentInfoDO studentInfoDO = studentInfoMapper.selectById(violateMgmtDO.getStudentId());
                // 获取所有学生的姓名
                String studentName = studentInfoDO.getName();

                // 记录操作日志上下文
                LogRecordContext.putVariable("id", id);
                LogRecordContext.putVariable("studentName", studentName);
                LogRecordContext.putVariable("status", true);
                return true;
            }
        }
        return null;
    }

    @Override
    public ViolateDashboardVO chart(ViolateChartReqVO reqVO) {
        ViolateDashboardVO vo = new ViolateDashboardVO();

        LocalDateTime startTime = reqVO.getStartTime();
        LocalDateTime endTime = reqVO.getEndTime();

        // 1. 卡片数据
        vo.setTotalCount(violateMgmtMapper.selectTotalCount(startTime, endTime, "", ""));
        vo.setPendingCount(violateMgmtMapper.selectTotalCount(startTime, endTime, ViolaateStatusEnum.VIOLATE_MGMT_VIOLATE_STATUS_PENDING.getStatus(), ""));
        vo.setWarnCount(violateMgmtMapper.selectTotalCount(startTime, endTime, ViolaateStatusEnum.VIOLATE_MGMT_VIOLATE_STATUS_WARN.getStatus(), ""));
        Long highRiskStudentCount = violateMgmtMapper.selectHighRiskStudentCount(startTime, endTime);
        if (highRiskStudentCount != null) {
            vo.setHighRiskStudentCount(highRiskStudentCount);
        } else {
            vo.setHighRiskStudentCount(0L);
        }

        List<JSONObject> jsonObjects = violateMgmtMapper.selectViolateTypeCount(startTime, endTime);
        JSONObject violateTypeCount = new JSONObject();
        for (JSONObject jsonObject : jsonObjects) {
            String typeName = jsonObject.getString("violate_type");
            Long count = jsonObject.getLong("count");
            violateTypeCount.put(typeName, count);
        }
        vo.setViolateTypeCount(violateTypeCount);
        return vo;
    }

    @Override
    public ViolateCountDashboardVO violateCount(ViolateChartReqVO reqVO) {
        ViolateCountDashboardVO vo = new ViolateCountDashboardVO();
        LocalDateTime startTime = reqVO.getStartTime();
        LocalDateTime endTime = reqVO.getEndTime();

        vo.setClassCountList(violateMgmtMapper.selectViolateClassCount(startTime, endTime));
        List<JSONObject> countList = violateMgmtMapper.selectViolateTypeCount(startTime, endTime);
        // 累加所有类型的总统计数
        long totalCount = countList.stream().mapToLong(jsonObject -> jsonObject.getLong("count")).sum();

        List<JSONObject> typeCountList = new ArrayList<>();
        // 获取所有违纪类型字典信息
        List<String> dbValues = DictFrameworkUtils.getDictDataValueList(StudentMgmtDictTypeEnum.VIOLATE_MGMT_VIOLATE_TYPE.getType());
        // 将countList里的key转换成字典信息，并计算百分比
        for (JSONObject jsonObject : countList) {
            JSONObject typeCountJson = new JSONObject();
            // 名称
            typeCountJson.put("typeName", DictFrameworkUtils.parseDictDataLabel(StudentMgmtDictTypeEnum.VIOLATE_MGMT_VIOLATE_TYPE.getType(), jsonObject.getString("violate_type")));
            // 数量
            typeCountJson.put("count", jsonObject.getLong("count"));
            // 百分比
            typeCountJson.put("percent", String.format("%.2f", jsonObject.getLong("count") * 100.0 / totalCount));
            typeCountList.add(typeCountJson);
        }
        vo.setTypeCountList(typeCountList);
        return vo;
    }

    @Override
    public List<ViolateWarnIndexRespVO> warnIndex(ViolateWarnIndexReqVO reqVO) {
        ViolateWarnIndexRespVO vo = new ViolateWarnIndexRespVO();
        // cycle (string, optional): 统计周期，可选周 / 月 / 学期，默认当前月。
        String cycle = reqVO.getCycle();

        /*vo.setClassCountList(violateMgmtMapper.selectViolateClassCount(startTime, endTime));
        List<JSONObject> countList = violateMgmtMapper.selectViolateTypeCount(startTime, endTime);
        // 累加所有类型的总统计数
        long totalCount = countList.stream().mapToLong(jsonObject -> jsonObject.getLong("count")).sum();

        List<JSONObject> typeCountList = new ArrayList<>();
        // 获取所有违纪类型字典信息
        List<String> dbValues = DictFrameworkUtils.getDictDataValueList(StudentMgmtDictTypeEnum.VIOLATE_MGMT_VIOLATE_TYPE.getType());
        // 将countList里的key转换成字典信息，并计算百分比
        for (JSONObject jsonObject : countList) {
            JSONObject typeCountJson = new JSONObject();
            // 名称
            typeCountJson.put("typeName", DictFrameworkUtils.parseDictDataLabel(StudentMgmtDictTypeEnum.VIOLATE_MGMT_VIOLATE_TYPE.getType(), jsonObject.getString("violate_type")));
            // 数量
            typeCountJson.put("count", jsonObject.getLong("count"));
            // 百分比
            typeCountJson.put("percent", String.format("%.2f", jsonObject.getLong("num") * 100.0 / totalCount));
            typeCountList.add(typeCountJson);
        }
        vo.setTypeCountList(typeCountList);*/
        return null;
    }
}