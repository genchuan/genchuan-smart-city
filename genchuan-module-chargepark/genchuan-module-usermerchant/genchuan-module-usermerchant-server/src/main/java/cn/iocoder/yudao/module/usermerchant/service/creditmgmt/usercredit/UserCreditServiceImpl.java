package cn.iocoder.yudao.module.usermerchant.service.creditmgmt.usercredit;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.usercredit.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.creditmgmt.usercredit.UserCreditDO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.plateauth.PlateAuthDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.creditmgmt.usercredit.UserCreditMapper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.NameQueryHelper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.TimeRangeParser;
import cn.iocoder.yudao.module.usermerchant.service.creditmgmt.usercredit.UserCreditService;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import static cn.iocoder.yudao.module.usermerchant.enums.LogRecordConstants.*;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.USER_CREDIT_NOT_EXISTS;

/**
 * 用户信用 Service 实现类
 *
 * @author 亘川智城
 */
@Slf4j
@Service
@Validated
public class UserCreditServiceImpl implements UserCreditService {

    @Resource
    private UserCreditMapper userCreditMapper;

    @Override
    public Long createUserCredit(UserCreditSaveReqVO createReqVO) {
        // 插入
        UserCreditDO userCredit = BeanUtils.toBean(createReqVO, UserCreditDO.class);
        userCreditMapper.insert(userCredit);

        // 返回
        return userCredit.getId();
    }

    @Override
    public void updateUserCredit(UserCreditSaveReqVO updateReqVO) {
        // 校验存在
        validateUserCreditExists(updateReqVO.getId());
        // 更新
        UserCreditDO updateObj = BeanUtils.toBean(updateReqVO, UserCreditDO.class);
        userCreditMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserCredit(Long id) {
        // 校验存在
        validateUserCreditExists(id);
        // 删除
        userCreditMapper.deleteById(id);
    }

    @Override
        public void deleteUserCreditListByIds(List<Long> ids) {
        // 删除
        userCreditMapper.deleteByIds(ids);
        }


    private void validateUserCreditExists(Long id) {
        if (userCreditMapper.selectById(id) == null) {
            throw exception(USER_CREDIT_NOT_EXISTS);
        }
    }

    @Override
    public UserCreditDO getUserCredit(Long id) {
        return userCreditMapper.selectById(id);
    }

    @Override
    public PageResult<UserCreditDO> getUserCreditPage(UserCreditPageReqVO pageReqVO) {
        // 如果前端传了nickname，则转换为userId并设置到查询条件
        if (StrUtil.isNotBlank(pageReqVO.getNickname())) {
            Long userId = userCreditMapper.getIdByNickname(pageReqVO.getNickname());
            if (userId == null) {
                return new PageResult<>(Collections.emptyList(), 0L);
            }
            pageReqVO.setUserId(userId);
        }
        PageResult<UserCreditDO> pageResult = userCreditMapper.selectPage(pageReqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return pageResult;
        }

        NameQueryHelper.fillNamesByIds(
                pageResult.getList(),
                UserCreditDO::getUserId,
                UserCreditDO::setNickname,
                "user_info", "id", "nickname"
        );

//        NameQueryHelper.fillNamesByIds(
//                pageResult.getList(),
//                UserCreditDO::getCreator,
//                UserCreditDO::setCreateName,
//                "user_info", "id", "nickname"
//        );
//
//        NameQueryHelper.fillNamesByIds(
//                pageResult.getList(),
//                UserCreditDO::getUpdater,
//                UserCreditDO::setUpdateName,
//                "user_info", "id", "nickname"
//        );

//        return userCreditMapper.selectPage(pageReqVO);

        return pageResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = TYPE_USER_CREDIT, subType = SUB_TYPE_REMIND_USER_CREDIT,
            bizNo = "{{{#ids}}}",
            success = SUCCESS_REMIND_USER_CREDIT)
    public void remindUserCredit(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 业务说明：仅做提醒操作，例如发送站内信、短信等。目前需求未明确具体内容，直接返回成功。
        // TODO 根据实际业务补充提醒逻辑（如调用消息服务、记录提醒日志等）
        log.info("用户信用提醒，信用ID列表：{}", ids);
        // 记录操作日志上下文
        LogRecordContext.putVariable("ids", ids);
    }

    @Override
    public UserCreditChartRespVO getUserCreditChart(UserCreditChartReqVO chartReqVO) {
        UserCreditChartRespVO chartRespVO = new UserCreditChartRespVO();
        String timeRange = chartReqVO.getTimeRange();

        TimeRangeParser.TimeRangeParsed parsed;
        if (StrUtil.isBlank(timeRange)) {
            // 未传时间范围：全量查询
            parsed = new TimeRangeParser.TimeRangeParsed(null, null, "day");
        } else {
            parsed = TimeRangeParser.parse(timeRange);
            if (parsed == null) {
                // 解析失败，返回空数据
                return chartRespVO;
            }
            }

        // 饼图：信用等级分布
        List<UserCreditChartRespVO.CreditLevelDistributionVO> levelDistribution =
                userCreditMapper.selectCreditLevelDistribution(parsed.getStart(), parsed.getEnd());
        chartRespVO.setCreditLevelDistribution(levelDistribution);

        // 平均信用分
        Integer avgScore = userCreditMapper.selectAvgCreditScore(parsed.getStart(), parsed.getEnd());
        chartRespVO.setAvgCreditScore(avgScore != null ? avgScore : 0);

        // 低信用用户数（信用分低于60，可根据业务调整）
        Integer lowCount = userCreditMapper.selectLowCreditUserCount(parsed.getStart(), parsed.getEnd());
        chartRespVO.setLowCreditUserCount(lowCount != null ? lowCount : 0);

        return chartRespVO;
    }

}