package cn.iocoder.yudao.module.usermerchant.service.membercenter.membersign;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.TimeRangeParser;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import static cn.iocoder.yudao.module.usermerchant.enums.LogRecordConstants.*;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membersign.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membersign.MemberSignDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.membersign.MemberSignMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 会员签到 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MemberSignServiceImpl implements MemberSignService {

    @Resource
    private MemberSignMapper memberSignMapper;

    @Override
    public Long createMemberSign(MemberSignSaveReqVO createReqVO) {
        // 插入
        MemberSignDO memberSign = BeanUtils.toBean(createReqVO, MemberSignDO.class);
        memberSignMapper.insert(memberSign);

        // 返回
        return memberSign.getId();
    }

    @Override
    public void updateMemberSign(MemberSignSaveReqVO updateReqVO) {
        // 校验存在
        validateMemberSignExists(updateReqVO.getId());
        // 更新
        MemberSignDO updateObj = BeanUtils.toBean(updateReqVO, MemberSignDO.class);
        memberSignMapper.updateById(updateObj);
    }

    @Override
    public void deleteMemberSign(Long id) {
        // 校验存在
        validateMemberSignExists(id);
        // 删除
        memberSignMapper.deleteById(id);
    }

    @Override
        public void deleteMemberSignListByIds(List<Long> ids) {
        // 删除
        memberSignMapper.deleteByIds(ids);
        }


    private void validateMemberSignExists(Long id) {
        if (memberSignMapper.selectById(id) == null) {
            throw exception(MEMBER_SIGN_NOT_EXISTS);
        }
    }

    @Override
    public MemberSignDO getMemberSign(Long id) {
        return memberSignMapper.selectById(id);
    }

    @Override
    public PageResult<MemberSignDO> getMemberSignPage(MemberSignPageReqVO pageReqVO) {
        return memberSignMapper.selectPage(pageReqVO);
    }

    @Override
    public MemberSignChartRespVO getChart(String timeRange) {
        // 1. 解析时间范围
        TimeRangeParser.TimeRangeParsed parsed;
        if (StrUtil.isBlank(timeRange)) {
            parsed = new TimeRangeParser.TimeRangeParsed(null, null, "day");
        } else {
            parsed = TimeRangeParser.parse(timeRange);
            if (parsed == null) {
                return new MemberSignChartRespVO(); // 解析失败返回空
            }
        }
        LocalDateTime start = parsed.getStart();
        LocalDateTime end = parsed.getEnd();
        String granularity = parsed.getGranularity();

        // 2. 折线图数据
        List<MemberSignChartRespVO.SignTrendVO> signTrend = memberSignMapper.selectSignTrend(start, end, granularity);

        // 3. 柱状图数据（按等级分布）
        List<MemberSignChartRespVO.SignUserDistributionVO> distribution = memberSignMapper.selectSignUserDistribution(start, end);

        // 4. 今日签到数
        Long todayCount = memberSignMapper.selectTodaySignCount();
        int todaySignCount = todayCount != null ? todayCount.intValue() : 0;

        // 5. 总会员数
        Long totalMember = memberSignMapper.selectTotalMemberCount();
        BigDecimal signRate = BigDecimal.ZERO;
        if (totalMember != null && totalMember > 0) {
            signRate = BigDecimal.valueOf(todaySignCount)
                    .divide(BigDecimal.valueOf(totalMember), 4, RoundingMode.HALF_UP);
        }

        // 6. 组装响应
        MemberSignChartRespVO respVO = new MemberSignChartRespVO();
        respVO.setSignTrend(signTrend);
        respVO.setSignUserDistribution(distribution);
        respVO.setTodaySignCount(todaySignCount);
        respVO.setSignRate(signRate);
        return respVO;
    }

}