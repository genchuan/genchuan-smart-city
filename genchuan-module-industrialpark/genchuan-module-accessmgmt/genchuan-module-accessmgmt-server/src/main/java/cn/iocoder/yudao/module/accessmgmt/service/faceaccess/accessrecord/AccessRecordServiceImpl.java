package cn.iocoder.yudao.module.accessmgmt.service.faceaccess.accessrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.accessrecord.vo.*;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.faceaccess.accessrecord.AccessRecordDO;
import cn.iocoder.yudao.module.accessmgmt.dal.mysql.faceaccess.accessrecord.AccessRecordMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.accessmgmt.enums.ErrorCodeConstants.*;

/**
 * 通行记录 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AccessRecordServiceImpl implements AccessRecordService {

    @Resource
    private AccessRecordMapper accessRecordMapper;

    // ==================== 基础查询 ====================

    @Override
    public PageResult<AccessRecordRespVO> getAccessRecordPage(AccessRecordPageReqVO pageReqVO) {
        // 分页查询，Mapper 层按 userName(模糊)/accessArea/verifyType/accessStatus/时间范围动态条件筛选，按主键倒序
        PageResult<AccessRecordDO> pageResult = accessRecordMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, AccessRecordRespVO.class);
    }

    @Override
    public AccessRecordRespVO getAccessRecord(Long id) {
        // 按主键查单条，不存在抛 ACCESS_RECORD_NOT_EXISTS 业务异常
        AccessRecordDO entity = accessRecordMapper.selectById(id);
        if (entity == null) {
            throw exception(ACCESS_RECORD_NOT_EXISTS);
        }
        return BeanUtils.toBean(entity, AccessRecordRespVO.class);
    }

    // ==================== 异常通行处理（核查 → 告警 → 处置） ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean checkAccessRecord(AccessRecordCheckReqVO reqVO) {
        // 校验记录是否存在
        AccessRecordDO exist = accessRecordMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(ACCESS_RECORD_NOT_EXISTS);
        }
        // 写入 checkResult（核查结论）
        AccessRecordDO updateObj = new AccessRecordDO();
        updateObj.setId(reqVO.getId());
        updateObj.setCheckResult(reqVO.getCheckResult());
        accessRecordMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean alarmAccessRecord(AccessRecordAlarmReqVO reqVO) {
        // 校验记录是否存在
        AccessRecordDO exist = accessRecordMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(ACCESS_RECORD_NOT_EXISTS);
        }
        // alarmContent 写入 alarmStatus 字段
        AccessRecordDO updateObj = new AccessRecordDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAlarmStatus(reqVO.getAlarmContent());
        accessRecordMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean handleAccessRecord(AccessRecordHandleReqVO reqVO) {
        // 校验记录是否存在
        AccessRecordDO exist = accessRecordMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(ACCESS_RECORD_NOT_EXISTS);
        }
        // 写入 handleResult（处置结果）
        AccessRecordDO updateObj = new AccessRecordDO();
        updateObj.setId(reqVO.getId());
        updateObj.setHandleResult(reqVO.getHandleResult());
        accessRecordMapper.updateById(updateObj);
        return true;
    }

    // ==================== 统计态势 ====================

    @Override
    public AccessRecordChartRespVO getAccessRecordChart(Long startTime, Long endTime) {
        // 四维度聚合：时段趋势 / 每日趋势 / 区域分布 / 人员频次
        AccessRecordChartRespVO chartVO = new AccessRecordChartRespVO();
        chartVO.setTimeTrendList(accessRecordMapper.selectTimeTrendList(startTime, endTime));
        chartVO.setDayTrendList(accessRecordMapper.selectDayTrendList(startTime, endTime));
        chartVO.setAreaCountList(accessRecordMapper.selectAreaCountList(startTime, endTime));
        chartVO.setUserCountList(accessRecordMapper.selectUserCountList(startTime, endTime));
        return chartVO;
    }

}
