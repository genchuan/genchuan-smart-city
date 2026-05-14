package cn.iocoder.yudao.module.studentmgmt.service.dutymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dutymgmt.DutyMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.dutymgmt.DutyMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.*;
import com.alibaba.fastjson.JSONObject;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

/**
 * 值班管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DutyMgmtServiceImpl implements DutyMgmtService {

    @Resource
    private DutyMgmtMapper dutyMgmtMapper;

    @Override
    public Long createDutyMgmt(DutyMgmtSaveReqVO createReqVO) {
        // 插入
        DutyMgmtDO dutyMgmt = BeanUtils.toBean(createReqVO, DutyMgmtDO.class);
        dutyMgmtMapper.insert(dutyMgmt);

        // 返回
        return dutyMgmt.getId();
    }

    @Override
    @LogRecord(type = DUTY_TYPE, subType = DUTY_UPDATE_SUB_TYPE, bizNo = "{{#updateReqVO.id}}",
            success = DUTY_UPDATE_SUCCESS)
    public void updateDutyMgmt(@Valid DutyMgmtUpdateReqVO updateReqVO) {
        // 校验存在
        DutyMgmtDO dutyMgmtDO = validateDutyMgmtExists(updateReqVO.getId());
        // 更新
        DutyMgmtDO updateObj = BeanUtils.toBean(updateReqVO, DutyMgmtDO.class);
        dutyMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteDutyMgmt(Long id) {
        // 校验存在
        validateDutyMgmtExists(id);
        // 删除
        dutyMgmtMapper.deleteById(id);
    }

    @Override
    public void deleteDutyMgmtListByIds(List<Long> ids) {
        // 删除
        dutyMgmtMapper.deleteByIds(ids);
    }


    private DutyMgmtDO validateDutyMgmtExists(Long id) {
        DutyMgmtDO dutyMgmtDO = dutyMgmtMapper.selectById(id);
        if (dutyMgmtDO == null) {
            throw exception(DUTY_MGMT_NOT_EXISTS);
        }
        return dutyMgmtDO;
    }

    @Override
    public DutyMgmtDO getDutyMgmt(Long id) {
        return dutyMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<DutyMgmtDO> getDutyMgmtPage(DutyMgmtPageReqVO pageReqVO) {
        return dutyMgmtMapper.selectPage(pageReqVO);
    }

    /**
     * 排班
     *
     * @param reqVO
     * @return
     */
    @Override
    @LogRecord(type = DUTY_TYPE, subType = DUTY_CREATE_SUB_TYPE, bizNo = "{{#duty.id}}",
            success = DUTY_CREATE_SUCCESS)
    public boolean schedule(@Valid DutyMgmtScheduleReqVO reqVO) {

        LocalDate[] dutyDates = reqVO.getDutyDate();
        int total = 0;
        DutyMgmtDO dutyMgmt = new DutyMgmtDO();
        for (LocalDate dutyDate : dutyDates) {
            dutyMgmt = new DutyMgmtDO();
            dutyMgmt.setDutyDate(dutyDate);
            dutyMgmt.setDutyUser(reqVO.getDutyUser());
            dutyMgmt.setStatus(DutyStatusEnum.DUTY_STATUS_PENDING_CHECKIN.getStatus());
            dutyMgmt.setCreateTime(LocalDateTime.now());
            dutyMgmt.setRemark(reqVO.getRemark());
            int insert = dutyMgmtMapper.insert(dutyMgmt);
            total = total + insert;
        }
        // 记录操作日志上下文
        LogRecordContext.putVariable("total", total);
        LogRecordContext.putVariable("duty", dutyMgmt);
        if (total == dutyDates.length) {
            return true;
        }
        return false;
    }

    /**
     * 签到
     *
     * @param reqVo
     * @return
     */
    @Override
    @LogRecord(type = DUTY_TYPE, subType = DUTY_CHECK_IN_SUB_TYPE, bizNo = "{{#duty.id}}",
            success = DUTY_CHECK_IN_SUCCESS)
    public boolean checkin(DutyMgmtCheckinReqVO reqVo) {
        Long[] ids = reqVo.getIds();
        int total = 0;
        for (Long id : ids) {
            DutyMgmtDO dutyMgmt = validateDutyMgmtExists(id);
            if (DutyCheckInStatusEnum.DUTY_CHCECK_IN_STATUS_CHECKED_IN.getStatus().equals(dutyMgmt.getCheckInStatus())) {
                throw exception(DUTY_MGMT_CHECK_IN_STATUS_CHECKED_IN);
            }

            dutyMgmt.setCheckInStatus(DutyCheckInStatusEnum.DUTY_CHCECK_IN_STATUS_CHECKED_IN.getStatus());

            if (dutyMgmt.getStatus().equals(DutyStatusEnum.DUTY_STATUS_PENDING_CHECKIN.getStatus())) {
                dutyMgmt.setStatus(DutyStatusEnum.DUTY_STATUS_COMPLETED.getStatus());
            }
            dutyMgmt.setCheckInTime(LocalDateTime.now());
            int insert = dutyMgmtMapper.updateById(dutyMgmt);
            // 记录操作日志上下文
            LogRecordContext.putVariable("duty", dutyMgmt);
            total = total + insert;
        }
            if (total > 0) {
                return true;
            }

        return false;
    }

    /**
     * 调班申请
     *
     * @param reqVo
     * @return
     */
    @Override
    @LogRecord(type = DUTY_TYPE, subType = DUTY_SHIFT_APPLY_SUB_TYPE, bizNo = "{{#duty.id}}",
            success = DUTY_SHIFT_APPLY_SUCCESS)
    public boolean shiftApply(@Valid DutyMgmtShiftApplyReqVO reqVo) {
        Long[] ids = reqVo.getIds();
        int total = 0;
        for (Long id : ids) {
            DutyMgmtDO dutyMgmt = validateDutyMgmtExists(id);

            // 签到状态为已签到，则不能调班
            if (DutyCheckInStatusEnum.DUTY_CHCECK_IN_STATUS_CHECKED_IN.getStatus().equals(dutyMgmt.getCheckInStatus())) {
                throw exception("已签到，不可调班");
            }

            // 状态不待打卡，则不能调班
            if (!dutyMgmt.getStatus().equals(DutyStatusEnum.DUTY_STATUS_PENDING_CHECKIN.getStatus())) {
                throw exception("非打卡状态，不可调班");
            }

            // 状态不是为空，或不是为驳回，则不能调班
            if (StringUtils.isNotBlank(dutyMgmt.getTransferStatus())) {
                if (!dutyMgmt.getTransferStatus().equals(DutyTransferStatusEnum.TRANSFER_STATUS_REJECTED.getStatus())) {
                    throw exception("当前调班状态，不可申请");
                }
            }

            dutyMgmt.setTransferUser(reqVo.getTransferUser());
            dutyMgmt.setTransferReason(reqVo.getTransferReason());
            dutyMgmt.setTransferStatus(DutyTransferStatusEnum.TRANSFER_STATUS_PENDING_PENDING.getStatus());
            dutyMgmt.setStatus(DutyStatusEnum.DUTY_STATUS_PENDING_TRANSFER.getStatus());

            int insert = dutyMgmtMapper.updateById(dutyMgmt);
            total = total + insert;
            // 记录操作日志上下文
            LogRecordContext.putVariable("duty", dutyMgmt);
        }

        if (total > 0) {
            return true;
        }
        return false;
    }

    /**
     * 调班审核
     *
     * @param reqVo
     * @return
     */
    @Override
    @LogRecord(type = DUTY_TYPE, subType = DUTY_SHIFT_AUDIT_SUB_TYPE, bizNo = "{{#duty.id}}",
            success = DUTY_SHIFT_AUDIT_SUCCESS)
    public boolean shiftAudit(@Valid DutyMgmtShiftAuditReqVO reqVo) {

        DutyMgmtDO dutyMgmt = validateDutyMgmtExists(reqVo.getId());
        // 状态不待打卡，则不能调班
        if (!dutyMgmt.getTransferStatus().equals(DutyTransferStatusEnum.TRANSFER_STATUS_PENDING_PENDING.getStatus())) {
            throw exception("当前不是待审批状态，不可审批");
        }

        dutyMgmt.setTransferStatus(reqVo.getAuditResult());
        dutyMgmt.setStatus(DutyStatusEnum.DUTY_STATUS_PENDING_CHECKIN.getStatus());
        dutyMgmt.setRemark(reqVo.getRemark());

        int insert = dutyMgmtMapper.updateById(dutyMgmt);
        // 记录操作日志上下文
        LogRecordContext.putVariable("duty", dutyMgmt);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    /**
     * 出车申请
     *
     * @param reqVo
     * @return
     */
    @Override
    @LogRecord(type = DUTY_TYPE, subType = DUTY_VEHICLE_APPLY_SUB_TYPE, bizNo = "{{#duty.id}}",
            success = DUTY_VEHICLE_APPLY_SUCCESS)
    public boolean vehicleApply(DutyMgmtVehicleApplyReqVO reqVo) {
        Long[] ids = reqVo.getIds();
        int total = 0;
        for (Long id : ids) {
            DutyMgmtDO dutyMgmt = validateDutyMgmtExists(id);

            // 状态不待打卡，则不能调班
            if (StringUtils.isNotBlank(dutyMgmt.getCarStatus())) {
                if (!dutyMgmt.getCarStatus().equals(DutyCarStatusEnum.CAR_STATUS_APPROVED.getStatus())) {
                    throw exception("当前出车状态，不可申请");
                }
            }

            dutyMgmt.setCarStatus(DutyCarStatusEnum.CAR_STATUS_PENDING.getStatus());
            dutyMgmt.setCarReason(reqVo.getCarReason());
            dutyMgmt.setCarDestination(reqVo.getCarDestination());

            dutyMgmt.setStatus(DutyStatusEnum.DUTY_STATUS_PENDING_CAR.getStatus());

            int insert = dutyMgmtMapper.updateById(dutyMgmt);
            total = total + insert;
            // 记录操作日志上下文
            LogRecordContext.putVariable("duty", dutyMgmt);

        }
        if (total > 0) {
            return true;
        }
        return false;
    }


    /**
     * 出车审核
     *
     * @param reqVo
     * @return
     */
    @Override
    @LogRecord(type = DUTY_TYPE, subType = DUTY_VEHICLE_AUDIT_SUB_TYPE, bizNo = "{{#duty.id}}",
            success = DUTY_VEHICLE_AUDIT_SUCCESS)
    public boolean vehicleAudit(DutyMgmtShiftAuditReqVO reqVo) {

        DutyMgmtDO dutyMgmt = validateDutyMgmtExists(reqVo.getId());

        if (!dutyMgmt.getCarStatus().equals(DutyCarStatusEnum.CAR_STATUS_PENDING.getStatus())) {
            throw exception("不是待审批，不可审批");
        }

        dutyMgmt.setCarStatus(reqVo.getAuditResult());
        dutyMgmt.setRemark(reqVo.getRemark());
        // 状态设置为待打卡
        dutyMgmt.setStatus(DutyStatusEnum.DUTY_STATUS_PENDING_CHECKIN.getStatus());

        int insert = dutyMgmtMapper.updateById(dutyMgmt);
        // 记录操作日志上下文
        LogRecordContext.putVariable("duty", dutyMgmt);
        if (insert > 0) {
            return true;
        }
        return false;
    }


    /**
     * 上传记录
     *
     * @param reqVo
     * @return
     */
    @Override
    @LogRecord(type = DUTY_TYPE, subType = DUTY_UPLOAD_RECORD_SUB_TYPE, bizNo = "{{#duty.id}}",
            success = DUTY_UPLOAD_RECORD_SUCCESS)
    public boolean uploadRecord(DutyMgmtUploadRecordReqVO reqVo) {

        DutyMgmtDO dutyMgmt = validateDutyMgmtExists(reqVo.getId());

        if (DutyCheckInStatusEnum.DUTY_CHCECK_IN_STATUS_CHECKED_IN.getStatus().equals(dutyMgmt.getCheckInStatus())) {
            dutyMgmt.setStatus(DutyStatusEnum.DUTY_STATUS_COMPLETED.getStatus());
        }
        dutyMgmt.setRecordContent(reqVo.getRecordContent());
        dutyMgmt.setRecordUploadTime(LocalDateTime.now());

        int insert = dutyMgmtMapper.updateById(dutyMgmt);
        // 记录操作日志上下文
        LogRecordContext.putVariable("duty", dutyMgmt);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    /**
     * 统计
     *
     * @param reqVo
     * @return
     */
    @Override
    public DutyMgmtChartRespVO chart(DutyMgmtChartReqVO reqVo) {
        DutyMgmtChartRespVO vo = new DutyMgmtChartRespVO();

        LocalDateTime startTime = reqVo.getStartTime();
        LocalDateTime endTime = reqVo.getEndTime();

        // 1. 卡片数据
//        "data": {
//            "totalDutyCount": 124,
//                    "todayDutyCount": 4,
//                    "checkInRate": 96.77,
//                    "shiftApplyCount": 8,
//                    "vehicleApplyCount": 5,
//                    "statusCountMap": {
//                         "待打卡": 12,
//                        "待调班审批": 2,
//                        "待出车审批": 1,
//                        "已完成": 109
//            }
        //totalCount (integer): 本期考评总记录数。
        vo = dutyMgmtMapper.selectTotalDutyCount(startTime, endTime);

        // 获取今日日期
        LocalDate today = LocalDate.now(); // 获取当前日期
        // 将日期转换为当天的0点时间
        LocalDateTime begin = today.atStartOfDay();
        // 将日期转换为当天的23点59分59秒
        LocalDateTime end = today.atTime(23, 59, 59);
        System.out.println(begin); // 输出当天的0点时间
        System.out.println(end); // 输出当天的0点时间

        JSONObject todayCountJson = dutyMgmtMapper.selectTotalDutyCountByCheckInStatus(begin, end, DutyCheckInStatusEnum.DUTY_CHCECK_IN_STATUS_CHECKED_IN.getStatus());
        if (null != todayCountJson) {
            // 今日值班人数
            Integer totalDutyCount = todayCountJson.getInteger("totalDutyCount");
            vo.setTodayDutyCount(totalDutyCount);
            // 已经打卡数
            if (totalDutyCount.equals(0)) {
                vo.setCheckInRate(BigDecimal.ZERO);
            } else {
                Integer statusCount = todayCountJson.getInteger("statusCount");
                // checkInRate (decimal): 打卡率，百分比保留 2 位小数。
                BigDecimal checkInRate = new BigDecimal(statusCount).divide(new BigDecimal(totalDutyCount), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
                vo.setCheckInRate(checkInRate);
            }
        }
        return vo;
    }

    @Override
    public DutyMgmtChartIndexRespVO dutyIndex(DutyMgmtChartReqVO reqVo) {
        DutyMgmtChartIndexRespVO vo = new DutyMgmtChartIndexRespVO();

        LocalDateTime startTime = reqVo.getStartTime();
        LocalDateTime endTime = reqVo.getEndTime();

        // 1. 卡片数据
//        "data": {
//            "monthList": ["2025-01", "2025-02", "2025-03"],
//            "dutyCountList": [112, 98, 124],
//            "checkInRateList": [95.54, 96.94, 96.77],
//            "shiftRateList": [6.25, 7.14, 6.45],
//            "vehicleRateList": [4.46, 3.06, 4.03]
//        }

        //totalCount (integer): 本期考评总记录数。
        List<String> monthList = dutyMgmtMapper.selectMonthList(startTime, endTime);
        // dutyCountList (array): 对应月份值班次数统计。
        //checkInRateList (array): 对应月份打卡率统计。
        //shiftRateList (array): 对应月份调班率统计。
        //vehicleRateList (array): 对应月份出车率统计。

        List<Integer> dutyCountList = new ArrayList<>();
        List<BigDecimal> checkInRateList = new ArrayList<>();
        List<BigDecimal> shiftRateList = new ArrayList<>();
        List<BigDecimal> vehicleRateList = new ArrayList<>();


        for (String month : monthList) {
            //值班次数
            Integer dutyCount = dutyMgmtMapper.selectDutyCount(month);
            // 对应月份的打卡次数
            JSONObject dutyCountJson = dutyMgmtMapper.selectTotalDutyCountByMonth(month, DutyCheckInStatusEnum.DUTY_CHCECK_IN_STATUS_CHECKED_IN.getStatus());
            if (null != dutyCountJson) {
                Integer statusCount = dutyCountJson.getInteger("statusCount");
                Integer totalCount = dutyCountJson.getInteger("totalCount");
                dutyCountList.add(dutyCount);
                // 打卡率
                BigDecimal checkInRate = BigDecimal.ZERO;
                if (!totalCount.equals(0)) {
                    checkInRate = new BigDecimal(statusCount).divide(new BigDecimal(totalCount), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
                }
                checkInRateList.add(checkInRate);
            }

            // 对应月份的调班次数
            JSONObject shiftCountJson = dutyMgmtMapper.selectTotalShiftCountByMonth(month, DutyTransferStatusEnum.TRANSFER_STATUS_PENDING_APPROVED.getStatus());
            if (null != shiftCountJson) {
                Integer statusCount = shiftCountJson.getInteger("statusCount");
                Integer totalCount = shiftCountJson.getInteger("totalCount");
                // 调班率
                BigDecimal shiftRate = BigDecimal.ZERO;
                if (!totalCount.equals(0)) {
                    shiftRate = new BigDecimal(statusCount).divide(new BigDecimal(totalCount), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
                }
                shiftRateList.add(shiftRate);
            }

            // 对应月份的出车次数
            JSONObject vehicleCountJson = dutyMgmtMapper.selectTotalVehicleCountByMonth(month, DutyCarStatusEnum.CAR_STATUS_APPROVED.getStatus());
            if (null != vehicleCountJson) {
                Integer statusCount = vehicleCountJson.getInteger("statusCount");
                Integer totalCount = vehicleCountJson.getInteger("totalCount");
                // 出车率
                BigDecimal vehicleRate = BigDecimal.ZERO;
                if (!totalCount.equals(0)) {
                    vehicleRate = new BigDecimal(statusCount).divide(new BigDecimal(totalCount), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
                }
                vehicleRateList.add(vehicleRate);
            }

        }
        vo.setMonthList(monthList);
        vo.setDutyCountList(dutyCountList);
        vo.setCheckInRateList(checkInRateList);
        vo.setShiftRateList(shiftRateList);
        vo.setVehicleRateList(vehicleRateList);
        return vo;
    }

}