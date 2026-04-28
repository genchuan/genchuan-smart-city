package cn.iocoder.yudao.module.usermerchant.service.usermgmt.userinfo;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.TimeRangeParser;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        PageResult<UserInfoDO> pageResult = userInfoMapper.selectPage(pageReqVO);
        // 对手机号进行脱敏处理
        for (UserInfoDO user : pageResult.getList()) {
            String phone = user.getPhone();
            if (phone != null && phone.length() >= 11) {
                // 保留前3位和后4位，中间4位星号
                String masked = phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
                user.setPhone(masked);
            }
            // 如果手机号长度不足11位，原样返回或置空，可根据需求调整
        }
        return pageResult;
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

        TimeRangeParser.TimeRangeParsed parsed;
        if (StrUtil.isBlank(timeRange)) {
            // 未传时间范围：全量查询，start 和 end 为 null，粒度默认 day
            parsed = new TimeRangeParser.TimeRangeParsed(null, null, "day");
        } else {
            parsed = TimeRangeParser.parse(timeRange);
            if (parsed == null) {
                // 解析失败，返回空数据
                return chartRespVO;
            }
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

        int seq = 1;
        if (last != null && last.getUserNo() != null) {
            String lastNo = last.getUserNo();
            // 提取后面的数字部分
            String seqStr = lastNo.substring(prefix.length());
            try {
                seq = Integer.parseInt(seqStr) + 1;
            } catch (NumberFormatException e) {
                seq = 1;
            }
        }
        // 超过 999 可以重置或抛出异常，根据业务决定
        if (seq > 999) {
            throw exception(USER_INFO_NO_REACHED_LIMIT);
        }
        return prefix + String.format("%03d", seq);
    }

}