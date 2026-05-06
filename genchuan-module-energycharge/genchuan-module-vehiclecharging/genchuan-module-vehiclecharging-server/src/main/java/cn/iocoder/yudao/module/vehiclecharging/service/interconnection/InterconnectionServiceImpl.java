package cn.iocoder.yudao.module.vehiclecharging.service.interconnection;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.*;
import java.util.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.interconnection.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.interconnection.InterconnectionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;

import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.interconnection.InterconnectionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;

/**
 * 互联互通表 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class InterconnectionServiceImpl implements InterconnectionService {

    @Resource
    private InterconnectionMapper interconnectionMapper;

    private void validateInterconnectionExists(Long id) {
        if (interconnectionMapper.selectById(id) == null) {
            throw exception(INTERCONNECTION_NOT_EXISTS);
        }
    }

    @Override
    public InterconnectionDO getInterconnection(Long id) {
        return interconnectionMapper.selectById(id);
    }

    @Override
    public PageResult<InterconnectionDO> getInterconnectionPage(InterconnectionPageReqVO pageReqVO) {
        return interconnectionMapper.selectPage(pageReqVO);
    }

    /**
     * 获取当前登录用户的昵称，若未登录则返回默认值 "admin"
     */
    private String getCurrentUserNickname() {
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        if (loginUser != null && loginUser.getInfo() != null) {
            String nickname = loginUser.getInfo().get(LoginUser.INFO_KEY_NICKNAME);
            if (nickname != null && !nickname.isEmpty()) {
                return nickname;
            }
        }
        return "admin";
    }

    @Override
    public Long applyInterconnection(InterconnectionApplyReqVO applyReqVO) {
        InterconnectionDO interconnection = BeanUtils.toBean(applyReqVO, InterconnectionDO.class);
        interconnection.setConnectStatus("审核中");
        String nickname = getCurrentUserNickname();
        interconnection.setCreator(nickname);
        interconnection.setUpdater(nickname);
        interconnection.setDeleted(false);
        interconnection.setCreateTime(LocalDateTime.now());
        interconnection.setUpdateTime(LocalDateTime.now());
        interconnectionMapper.insert(interconnection);
        return interconnection.getId();
    }

    @Override
    public void auditInterconnection(InterconnectionAuditReqVO auditReqVO) {
        validateInterconnectionExists(Long.valueOf(auditReqVO.getId()));
        InterconnectionDO updateObj = BeanUtils.toBean(auditReqVO, InterconnectionDO.class);
        if (Boolean.TRUE.equals(updateObj.getPass())) {
            updateObj.setConnectStatus("已开通");
        } else {
            updateObj.setConnectStatus("已关闭");
        }
        updateObj.setAuditUser(getCurrentUserNickname());
        updateObj.setAuditTime(LocalDateTime.now());
        interconnectionMapper.updateById(updateObj);
    }

    @Override
    public void closeInterconnection(InterconnectionCloseReqVO closeReqVO) {
        validateInterconnectionExists(Long.valueOf(closeReqVO.getId()));
        InterconnectionDO updateObj = BeanUtils.toBean(closeReqVO, InterconnectionDO.class);
        updateObj.setConnectStatus("已关闭");
        interconnectionMapper.updateById(updateObj);
    }

    @Override
    public void reapplyInterconnection(Long id) {
        validateInterconnectionExists(id);
        InterconnectionDO interconnection = interconnectionMapper.selectById(id);
        if (!interconnection.getConnectStatus().equals("已关闭") && !interconnection.getConnectStatus().equals("未申请")) {
            throw new ServiceException(INTERCONNECTION_STATUS_ERROR);
        }
        interconnection.setConnectStatus("审核中");
        interconnectionMapper.updateById(interconnection);
    }

    @Override
    public InterconnectionChartRespVO getInterconnectionChart() {
        InterconnectionChartRespVO chartRespVO = new InterconnectionChartRespVO();
        chartRespVO.setTotalCount(interconnectionMapper.selectTotalCount());
        chartRespVO.setOpenedCount(interconnectionMapper.selectOpenedCount());
        chartRespVO.setAuditingCount(interconnectionMapper.selectAuditingCount());
        chartRespVO.setWaitApplyCount(interconnectionMapper.selectWaitApplyCount());
        chartRespVO.setClosedCount(interconnectionMapper.selectClosedCount());

        List<InterconnectionChartRespVO.InterconnectionStatusRatioVO> statusList = interconnectionMapper.selectStatusRatio();
        for (InterconnectionChartRespVO.InterconnectionStatusRatioVO vo : statusList) {
            BigDecimal ratio = BigDecimal.valueOf(vo.getCount())
                    .divide(BigDecimal.valueOf(chartRespVO.getTotalCount()), 2, RoundingMode.HALF_UP);
            vo.setRatio(ratio);
        }
        chartRespVO.setStatusRatio(statusList);

        chartRespVO.setCooperatorCount(interconnectionMapper.selectCooperatorCount());
        return chartRespVO;
    }

    @Override
    public List<InterconnectionChartRespVO.InterconnectionCooperatorCountVO> getCooperatorCountByStatus(String status) {
        return interconnectionMapper.selectCooperatorCountByStatus(status);
    }

    @Override
    public List<InterconnectionChartRespVO.InterconnectionStatusRatioVO> getStatusCountByCooperator(String cooperator) {
        List<InterconnectionChartRespVO.InterconnectionStatusRatioVO> statusList =
                interconnectionMapper.selectStatusCountByCooperator(cooperator);
        int total = statusList.stream().mapToInt(InterconnectionChartRespVO.InterconnectionStatusRatioVO::getCount).sum();
        for (InterconnectionChartRespVO.InterconnectionStatusRatioVO vo : statusList) {
            BigDecimal ratio = total == 0 ? BigDecimal.ZERO :
                    BigDecimal.valueOf(vo.getCount())
                            .divide(BigDecimal.valueOf(total), 2, RoundingMode.HALF_UP);
            vo.setRatio(ratio);
        }
        return statusList;
    }

    @Override
    public List<InterconnectionApplyDailyCountVO> getDailyApplyCount(LocalDate startTime, LocalDate endTime) {
        if (startTime == null && endTime == null) {
            endTime = LocalDate.now();
            startTime = endTime.minusDays(7);
        } else if (startTime == null) {
            startTime = endTime.minusDays(7);
        } else if (endTime == null) {
            endTime = startTime.plusDays(7);
        }
        List<Map<String, Object>> dbList = interconnectionMapper.selectDailyApplyCount(startTime, endTime);
        Map<LocalDate, Integer> countMap = new HashMap<>();
        for (Map<String, Object> map : dbList) {
            LocalDate date = ((Date) map.get("date")).toLocalDate();
            Integer count = ((Number) map.get("count")).intValue();
            countMap.put(date, count);
        }
        List<InterconnectionApplyDailyCountVO> result = new ArrayList<>();
        for (LocalDate date = startTime; !date.isAfter(endTime); date = date.plusDays(1)) {
            InterconnectionApplyDailyCountVO vo = new InterconnectionApplyDailyCountVO();
            vo.setDate(date.toString());
            vo.setCount(countMap.getOrDefault(date, 0));
            result.add(vo);
        }
        return result;
    }
}