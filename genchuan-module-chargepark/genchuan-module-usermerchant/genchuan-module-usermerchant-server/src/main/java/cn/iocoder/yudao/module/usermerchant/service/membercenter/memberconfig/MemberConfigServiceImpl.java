package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberconfig;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo.*;
import cn.iocoder.yudao.module.usermerchant.convert.membercenter.memberconfig.MemberConfigConvert;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberconfig.MemberConfigDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberconfig.MemberConfigMapper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.TimeRangeParser;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.memberconfig.MemberConfigService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.MEMBER_CONFIG_NOT_EXISTS;

/**
 * 会员配置 Service 实现类
 *
 * @author QingX
 */
@Service
@Validated
@DS("member")
public class MemberConfigServiceImpl implements MemberConfigService {

    @Resource
    private MemberConfigMapper memberConfigMapper;

    @Override
    public PageResult<MemberConfigDO> getMemberConfigPage(MemberConfigPageReqVO pageReqVO) {
        return memberConfigMapper.selectPage(pageReqVO);
    }

    @Override
    public Long createMemberConfig(MemberConfigSaveReqVO createReqVO) {
        // 插入
        MemberConfigDO memberConfig = BeanUtils.toBean(createReqVO, MemberConfigDO.class);
        memberConfigMapper.insert(memberConfig);

        // 返回
        return memberConfig.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveConfig(MemberConfigSaveReqVO saveReqVO) {
        if (saveReqVO.getId() == null) {
            // 新增：调用 create 逻辑
            MemberConfigDO config = BeanUtils.toBean(saveReqVO, MemberConfigDO.class);
            memberConfigMapper.insert(config);
        } else {
            // 更新：调用 update 逻辑，先校验存在
            validateMemberConfigExists(saveReqVO.getId());
            MemberConfigDO updateObj = BeanUtils.toBean(saveReqVO, MemberConfigDO.class);
            memberConfigMapper.updateById(updateObj);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateConfigStatus(List<Long> ids, String status) {
        if (org.springframework.util.CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 使用 UpdateWrapper 批量更新状态
        UpdateWrapper<MemberConfigDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids)
                .set("status", status);
        memberConfigMapper.update(null, updateWrapper);
    }

    @Override
    public MemberConfigDO getMemberConfig(Long id) {
        return memberConfigMapper.selectById(id);
    }

    @Override
    public void updateConfig(MemberConfigUpdateReqVO updateReqVO) {
        // 校验存在
        validateMemberConfigExists(updateReqVO.getId());
        // 更新
        MemberConfigDO updateObj = BeanUtils.toBean(updateReqVO, MemberConfigDO.class);
        memberConfigMapper.updateById(updateObj);
    }

    @Override
    public MemberConfigChartRespVO getMemberConfigChart(MemberConfigChartReqVO chartReqVO) {
        MemberConfigChartRespVO chartRespVO = new MemberConfigChartRespVO();
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
        //饼状图渲染
        List<MemberConfigChartRespVO.ConfigTypeDistributionVO> typeDistribution =
                memberConfigMapper.selectConfigTypeDistribution(parsed.getStart(), parsed.getEnd());
        chartRespVO.setConfigTypeDistribution(typeDistribution);
        //总数统计
        chartRespVO.setEffectConfigCount(memberConfigMapper.selectEffectConfigCount(parsed.getStart(), parsed.getEnd()));
        //比率统计
        chartRespVO.setMemberMatchRate(memberConfigMapper.selectMemberMatchRate(parsed.getStart(), parsed.getEnd()));
        return chartRespVO;
    }

    private void validateMemberConfigExists(Long id) {
        if (memberConfigMapper.selectById(id) == null) {
            throw new ServiceException(MEMBER_CONFIG_NOT_EXISTS);
        }
    }

}