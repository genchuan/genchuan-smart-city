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
        interconnection.setConnectStatus("auditing"); // waitapply未申请,auditing审核中,opened已开通,closed已关闭
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
            updateObj.setConnectStatus("opened");
        } else {
            updateObj.setConnectStatus("closed");
        }
        // 审核用户使用当前登录用户昵称
        updateObj.setAuditUser(getCurrentUserNickname());
        updateObj.setAuditTime(LocalDateTime.now());
        interconnectionMapper.updateById(updateObj);
    }

    @Override
    public void closeInterconnection( InterconnectionCloseReqVO closeReqVO ) {
        // 校验存在
        validateInterconnectionExists(Long.valueOf(closeReqVO.getId()));
        // 关闭
        InterconnectionDO updateObj = BeanUtils.toBean(closeReqVO, InterconnectionDO.class);
        //关闭同步有待实现
        interconnectionMapper.updateById(updateObj);
    }

    @Override
    public void reapplyInterconnection( Long id ) {
//        针对已驳回或已关闭的对接申请，重新提交审核申请，将状态修改为审核中，
        // 校验存在
        validateInterconnectionExists(id);
        InterconnectionDO interconnection = interconnectionMapper.selectById(id);
        if (!interconnection.getConnectStatus().equals("closed") && !interconnection.getConnectStatus().equals("waitapply")) {
            throw new ServiceException(INTERCONNECTION_STATUS_ERROR);
        }
        // 重新申请
        interconnection.setConnectStatus("auditing");
        interconnectionMapper.updateById(interconnection);
    }

    @Override
    public InterconnectionChartRespVO getInterconnectionChart() {
        // 1. 查询各状态数量
        InterconnectionChartRespVO chartRespVO = new InterconnectionChartRespVO();
        chartRespVO.setTotalCount(interconnectionMapper.selectTotalCount());
        chartRespVO.setOpenedCount(interconnectionMapper.selectOpenedCount());
        chartRespVO.setAuditingCount(interconnectionMapper.selectAuditingCount());
        chartRespVO.setWaitApplyCount(interconnectionMapper.selectWaitApplyCount());
        chartRespVO.setClosedCount(interconnectionMapper.selectClosedCount());

        // 2. 饼图数据：计算占比
        List<InterconnectionChartRespVO.InterconnectionStatusRatioVO> statusList = interconnectionMapper.selectStatusRatio();
        // 将状态 code 转换为中文名称（根据字典或常量）
        Map<String, String> statusNameMap = Map.of(
                "opened", "已开通",
                "auditing", "审核中",
                "waitapply", "未申请",
                "closed", "已关闭"
        );
        for (InterconnectionChartRespVO.InterconnectionStatusRatioVO vo : statusList) {
            String code = vo.getStatus();
            vo.setStatus(statusNameMap.getOrDefault(code, code));
            // 计算 ratio（保留两位小数）
            BigDecimal ratio = BigDecimal.valueOf(vo.getCount())
                    .divide(BigDecimal.valueOf(chartRespVO.getTotalCount()), 2, RoundingMode.HALF_UP);
            vo.setRatio(ratio);
        }
        chartRespVO.setStatusRatio(statusList);

        // 3. 柱状图数据
        List<InterconnectionChartRespVO.InterconnectionCooperatorCountVO> cooperatorList = interconnectionMapper.selectCooperatorCount();
        chartRespVO.setCooperatorCount(cooperatorList);

        return chartRespVO;
    }

    @Override
    public List<InterconnectionChartRespVO.InterconnectionCooperatorCountVO> getCooperatorCountByStatus(String status) {
        // 可选：校验状态是否合法（根据字典或常量）
        // 直接调用 Mapper 查询
        return interconnectionMapper.selectCooperatorCountByStatus(status);
    }

    @Override
    public List<InterconnectionChartRespVO.InterconnectionStatusRatioVO> getStatusCountByCooperator(String cooperator) {
        // 1. 查询该合作方下各状态的对接数量
        List<InterconnectionChartRespVO.InterconnectionStatusRatioVO> statusList =
                interconnectionMapper.selectStatusCountByCooperator(cooperator);

        // 2. 计算总数量（用于占比）
        int total = statusList.stream().mapToInt(InterconnectionChartRespVO.InterconnectionStatusRatioVO::getCount).sum();

        // 3. 将状态 code 转换为中文名称，并计算占比
        Map<String, String> statusNameMap = Map.of(
                "opened", "已开通",
                "auditing", "审核中",
                "waitapply", "未申请",
                "closed", "已关闭"
        );
        for (InterconnectionChartRespVO.InterconnectionStatusRatioVO vo : statusList) {
            String code = vo.getStatus();
            vo.setStatus(statusNameMap.getOrDefault(code, code));
            // 计算占比（保留两位小数），避免除零
            BigDecimal ratio = total == 0 ? BigDecimal.ZERO :
                    BigDecimal.valueOf(vo.getCount())
                            .divide(BigDecimal.valueOf(total), 2, RoundingMode.HALF_UP);
            vo.setRatio(ratio);
        }

        return statusList;
    }

    @Override
    public List<InterconnectionApplyDailyCountVO> getDailyApplyCount( LocalDate startTime, LocalDate endTime) {
        // 1. 默认时间范围：最近7天（如果未传）
        if (startTime == null && endTime == null) {
            endTime = LocalDate.now();
            startTime = endTime.minusDays(7);
        } else if (startTime == null) {
            startTime = endTime.minusDays(7);
        } else if (endTime == null) {
            endTime = startTime.plusDays(7);
        }
        // 2. 查询数据库中的每日申请数量
        List<Map<String, Object>> dbList = interconnectionMapper.selectDailyApplyCount(startTime, endTime);
        Map<java.time.LocalDate, Integer> countMap = new HashMap<>();
        for (Map<String, Object> map : dbList) {
            java.time.LocalDate date = ((Date) map.get("date")).toLocalDate();
            Integer count = ((Number) map.get("count")).intValue();
            countMap.put(date, count);
        }
        // 3. 补全日期范围内的所有日期（确保缺失的日期 count=0）
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