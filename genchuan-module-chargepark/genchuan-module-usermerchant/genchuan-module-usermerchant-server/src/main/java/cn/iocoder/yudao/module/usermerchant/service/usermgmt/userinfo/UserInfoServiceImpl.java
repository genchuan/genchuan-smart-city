package cn.iocoder.yudao.module.usermerchant.service.usermgmt.userinfo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.userinfo.UserInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.usermgmt.userinfo.UserInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 用户信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Boolean createUserInfo(@Valid UserInfoCreateReqVO createReqVO) {
        // 插入
        UserInfoDO userInfo = BeanUtils.toBean(createReqVO, UserInfoDO.class);
        userInfo.setUserNo(generateUserNo());
        int rows = userInfoMapper.insert(userInfo);
        // 返回是否插入成功
        return rows > 0;
    }

    @Override
    public void updateUserInfo(UserInfoUpdateReqVO updateReqVO) {
        // 校验存在
        validateUserInfoExists(updateReqVO.getId());
        // 更新
        UserInfoDO updateObj = BeanUtils.toBean(updateReqVO, UserInfoDO.class);
        userInfoMapper.updateById(updateObj);
    }

    private void validateUserInfoExists(Long id) {
        if (userInfoMapper.selectById(id) == null) {
            throw exception(USER_INFO_NOT_EXISTS);
        }
    }

    @Override
    public UserInfoDO getUserInfo(Long id) {
        return userInfoMapper.selectById(id);
    }

    @Override
    public PageResult<UserInfoDO> getUserInfoPage(UserInfoPageReqVO pageReqVO) {
        return userInfoMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean importUsers(List<UserInfoImportExcelVO> list, Boolean updateSupport) {
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        for (UserInfoImportExcelVO vo : list) {
            if (vo.getId() != null) {
                UserInfoDO existDO = userInfoMapper.selectById(vo.getId());
                if (existDO != null) {
                    if (Boolean.TRUE.equals(updateSupport)) {
                        // 更新：复制属性，但保护创建信息
                        UserInfoDO updateDO = BeanUtils.toBean(vo, UserInfoDO.class);
                        updateDO.setCreator(null);
                        updateDO.setCreateTime(null);
                        userInfoMapper.updateById(updateDO);
                    } else {
                        // updateSupport = false，跳过该条记录
                        continue;
                    }
                } else {
                    // ID 不存在，按新增处理（忽略用户提供的 ID，由数据库自增）
                    UserInfoDO insertDO = BeanUtils.toBean(vo, UserInfoDO.class);
                    insertDO.setId(null);
                    userInfoMapper.insert(insertDO);
                }
            } else {
                // 无 ID，直接新增
                UserInfoDO insertDO = BeanUtils.toBean(vo, UserInfoDO.class);
                userInfoMapper.insert(insertDO);
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserStatus(List<Long> ids, String status) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 使用 UpdateWrapper 批量更新状态
        UpdateWrapper<UserInfoDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids)
                .set("status", status);
        userInfoMapper.update(null, updateWrapper);
    }

    @Override
    public UserInfoChartRespVO getUserInfoChart(UserInfoChartReqVO chartReqVO) {
        UserInfoChartRespVO chartRespVO = new UserInfoChartRespVO();
        //拆分时间范围
        String timeRange = chartReqVO.getTimeRange();

        // 解析时间范围，获取开始时间、结束时间以及分组类型（日/月/年）
        TimeRangeParsed parsed = parseTimeRange(timeRange);
        if (parsed == null) {
            // 若解析失败，可返回空数据或抛异常
            return chartRespVO;
        }
        //折线图渲染
        // 折线图数据
        List<UserInfoChartRespVO.UserGrowthTrendVO> growthTrend = userInfoMapper.selectUserGrowthTrend(
                parsed.getStart(), parsed.getEnd(), parsed.getGranularity());
        chartRespVO.setUserGrowthTrend(growthTrend);
        //柱状图渲染
        List<UserInfoChartRespVO.UserTypeDistributionVO> typeDistribution = userInfoMapper.selectUserTypeDistribution(
                parsed.getStart(), parsed.getEnd());
        chartRespVO.setUserTypeDistribution(typeDistribution);
        //总数统计
        chartRespVO.setTotalUserCount(userInfoMapper.selectTotalUserCount(parsed.getStart(), parsed.getEnd()));
        chartRespVO.setNewUserCount(userInfoMapper.selectNewUserCount(parsed.getStart(), parsed.getEnd()));

        return chartRespVO;
    }

    private String generateUserNo() {
        // 当前日期格式：yyyyMMdd
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String prefix = "U-" + datePart;

        // 查询当天已生成的最大序号
        LambdaQueryWrapper<UserInfoDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(UserInfoDO::getUserNo)
                .likeRight(UserInfoDO::getUserNo, prefix)   // 匹配前缀，如 "U-20250415-"
                .orderByDesc(UserInfoDO::getUserNo)
                .last("LIMIT 1");
        UserInfoDO last = userInfoMapper.selectOne(wrapper);

        int seq = 0;
        if (last != null && last.getUserNo() != null) {
            String lastNo = last.getUserNo();
            // 提取后面的数字部分
            String seqStr = lastNo.substring(prefix.length());
            try {
                seq = Integer.parseInt(seqStr) + 1;
            } catch (NumberFormatException e) {
                seq = 0;
            }
        }
        // 超过 999 可以重置或抛出异常，根据业务决定
        if (seq > 999) {
            throw exception(USER_INFO_NO_REACHED_LIMIT);
        }
        return prefix + String.format("%03d", seq);
    }

    /**
     * 解析时间范围字符串
     * 格式示例： "2025-04-01~2025-04-30" -> 日粒度
     *           "2025-04~2025-04" -> 月粒度
     *           "2025~2025" -> 年粒度
     * 返回包含 start, end, granularity 的对象
     */
    private TimeRangeParsed parseTimeRange(String timeRange) {
        if (timeRange == null || !timeRange.contains("~")) {
            return null;
        }
        String[] parts = timeRange.split("~");
        if (parts.length != 2) {
            return null;
        }
        String startStr = parts[0].trim();
        String endStr = parts[1].trim();

        // 解析开始和结束时间（支持 yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss）
        LocalDateTime start = parseDateTime(startStr);
        LocalDateTime end = parseDateTime(endStr);
        if (start == null || end == null) {
            return null;
        }

        // 推断粒度
        String granularity;
        // 年粒度：开始是年初（月=1，日=1，时=0，分=0，秒=0），结束是年末（月=12，日=31，时=23，分=59，秒=59），且同年
        if (start.getMonth() == Month.JANUARY && start.getDayOfMonth() == 1 && start.getHour() == 0 && start.getMinute() == 0 && start.getSecond() == 0 &&
                end.getMonth() == Month.DECEMBER && end.getDayOfMonth() == 31 && end.getHour() == 23 && end.getMinute() == 59 && end.getSecond() == 59 &&
                start.getYear() == end.getYear()) {
            granularity = "year";
        }
        // 月粒度：开始是月初（日=1，时=0，分=0，秒=0），结束是月末（同年同月，且日期为该月最后一天，时=23，分=59，秒=59）
        else if (start.getDayOfMonth() == 1 && start.getHour() == 0 && start.getMinute() == 0 && start.getSecond() == 0 &&
                start.getYear() == end.getYear() && start.getMonth() == end.getMonth() &&
                end.getDayOfMonth() == end.toLocalDate().lengthOfMonth() && end.getHour() == 23 && end.getMinute() == 59 && end.getSecond() == 59) {
            granularity = "month";
        }
        else {
            granularity = "day";
        }

        return new TimeRangeParsed(start, end, granularity);
    }

    /**
     * 解析日期时间字符串，支持格式：
     * - yyyy-MM-dd HH:mm:ss
     * - yyyy-MM-dd
     */
    private LocalDateTime parseDateTime(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        // 尝试完整格式
        try {
            return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (DateTimeParseException e) {
            // 尝试日期格式
            try {
                LocalDate date = LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return date.atStartOfDay();
            } catch (DateTimeParseException e2) {
                return null;
            }
        }
    }

    @Data
    private static class TimeRangeParsed {
        private final LocalDateTime start;
        private final LocalDateTime end;
        private final String granularity; // "day", "month", "year"
        public TimeRangeParsed(LocalDateTime start, LocalDateTime end, String granularity) {
            this.start = start;
            this.end = end;
            this.granularity = granularity;
        }
    }

}