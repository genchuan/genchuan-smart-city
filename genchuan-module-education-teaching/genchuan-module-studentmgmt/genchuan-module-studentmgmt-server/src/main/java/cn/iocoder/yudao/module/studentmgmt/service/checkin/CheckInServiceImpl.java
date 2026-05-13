package cn.iocoder.yudao.module.studentmgmt.service.checkin;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.checkin.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.checkin.CheckInDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.checkin.CheckInMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.CheckInAccountStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.CheckInStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.TreatStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.TreatTypeEnum;
import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.CHECK_IN_NOT_EXISTS;

/**
 * 报到管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CheckInServiceImpl implements CheckInService {

    @Resource
    private CheckInMapper checkInMapper;

    @Override
    public Long createCheckIn(CheckInSaveReqVO createReqVO) {
        // 插入
        CheckInDO checkIn = BeanUtils.toBean(createReqVO, CheckInDO.class);
        checkInMapper.insert(checkIn);

        // 返回
        return checkIn.getId();
    }

    @Override
    public void updateCheckIn(CheckInSaveReqVO updateReqVO) {
        // 校验存在
        validateCheckInExists(updateReqVO.getId());
        // 更新
        CheckInDO updateObj = BeanUtils.toBean(updateReqVO, CheckInDO.class);
        checkInMapper.updateById(updateObj);
    }

    @Override
    public void deleteCheckIn(Long id) {
        // 校验存在
        validateCheckInExists(id);
        // 删除
        checkInMapper.deleteById(id);
    }

    @Override
        public void deleteCheckInListByIds(List<Long> ids) {
        // 删除
        checkInMapper.deleteByIds(ids);
        }


    private CheckInDO validateCheckInExists(Long id) {
        CheckInDO checkInDO = checkInMapper.selectById(id);
        if ( checkInDO == null) {
            throw exception(CHECK_IN_NOT_EXISTS);
        }
        return checkInDO;
    }

    @Override
    public CheckInDO getCheckIn(Long id) {
        return checkInMapper.selectById(id);
    }

    @Override
    public PageResult<CheckInDO> getCheckInPage(CheckInPageReqVO pageReqVO) {
        return checkInMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean supply(CheckInSupplyReqVO reqVO) {
        int total = 0;
        for (Long id : reqVO.getIds()) {
            // 校验存在
            CheckInDO checkInDO = validateCheckInExists(id);
            checkInDO.setExamScore(reqVO.getExamScore());
            checkInDO.setSupplyInfo(reqVO.getSupplyInfo());
            checkInDO.setStatus(CheckInStatusEnum.PENDING_AUDIT.getStatus());

            int i = checkInMapper.updateById(checkInDO);
            total += i;
        }
        return total > 0;
    }

    @Override
    public Boolean confirm(CheckInConfirmReqVO reqVO) {

        int total = 0;
        for (Long id : reqVO.getIds()) {
            // 校验存在
            CheckInDO checkInDO = validateCheckInExists(id);
            checkInDO.setConfirmTime(LocalDateTime.now());
            checkInDO.setStatus(CheckInStatusEnum.PENDING_AUDIT.getStatus());
            int i = checkInMapper.updateById(checkInDO);
            total += i;
        }
        return total > 0;
    }

    @Override
    public Boolean audit(CheckInConfirmReqVO reqVO) {
        int total = 0;
        for (Long id : reqVO.getIds()) {
            // 校验存在
            CheckInDO checkInDO = validateCheckInExists(id);
            // TODO 调用创建用户RPC接口

            checkInDO.setConfirmTime(LocalDateTime.now());
            checkInDO.setStatus(CheckInStatusEnum.CHECKED_IN.getStatus());
            String loginUserNickname = SecurityFrameworkUtils.getLoginUserNickname();
            checkInDO.setAuditUser(loginUserNickname);
            checkInDO.setAuditTime(LocalDateTime.now());
            checkInDO.setAccountStatus(CheckInAccountStatusEnum.CREATED.getStatus());
            int i = checkInMapper.updateById(checkInDO);
            total += i;
        }
        return total > 0;
    }

    @Override
    public CheckInChartRespVO chart(CheckInChartReqVO reqVO) {
        CheckInChartRespVO vo = new CheckInChartRespVO();

        Integer year = reqVO.getYear();
        if (year == null) {
            year = LocalDateTime.now().getYear();
        }

        // 1. 卡片数据
        vo = checkInMapper.selectTotalCount(year,
                CheckInStatusEnum.PENDING_CONFIRM.getStatus(),
                CheckInStatusEnum.PENDING_AUDIT.getStatus(),
                CheckInStatusEnum.CHECKED_IN.getStatus()
                );

        // 2. 趋势数据
        List<JSONObject> countJsonList = checkInMapper.selectDateList(year);
        // "dateList": ["2025-08-25", "2025-08-26", "2025-08-27", "2025-08-28", "2025-08-29"],
        //"dailyConfirmList": [20, 35, 42, 58, 65],
        //"dailyAuditList": [15, 30, 40, 55, 60]
        List<String> dateList = new ArrayList<>();
        List<Integer> dailyConfirmList = new ArrayList<>();
        List<Integer> dailyAuditList = new ArrayList<>();
        for (JSONObject jsonObject : countJsonList) {
            dateList.add(jsonObject.getString("date"));
            dailyConfirmList.add(jsonObject.getInteger("confirmCount"));
            dailyAuditList.add(jsonObject.getInteger("auditCount"));
        }
        vo.setDateList(dateList);
        vo.setDailyConfirmList(dailyConfirmList);
        vo.setDailyAuditList(dailyAuditList);
        return vo;
    }

    @Override
    public CheckInChartIndexRespVO checkinIndex(CheckInChartReqVO reqVO) {
        CheckInChartIndexRespVO vo = new CheckInChartIndexRespVO();

        Integer year = reqVO.getYear();
        if (year == null) {
            year = LocalDateTime.now().getYear();
        }

        // 1. 卡片数据
        //totalRegisterCount (integer): 总报名人数。
        //totalConfirmCount (integer): 已确认报到人数。
        //checkinRate (decimal): 报到率。
        //accountCreatedCount (integer): 已创建账号人数。
        //accountCreateRate (decimal): 账号创建完成率。。
        vo = checkInMapper.selectCheckInCount(year,
                CheckInStatusEnum.CHECKED_IN.getStatus(),
                CheckInAccountStatusEnum.CREATED.getStatus()
        );
        return vo;
    }

}