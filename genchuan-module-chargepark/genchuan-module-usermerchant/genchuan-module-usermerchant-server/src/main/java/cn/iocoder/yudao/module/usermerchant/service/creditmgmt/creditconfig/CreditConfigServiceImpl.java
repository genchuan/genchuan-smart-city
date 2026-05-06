package cn.iocoder.yudao.module.usermerchant.service.creditmgmt.creditconfig;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.TimeRangeParser;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.creditconfig.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.creditmgmt.creditconfig.CreditConfigDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.creditmgmt.creditconfig.CreditConfigMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 信用配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CreditConfigServiceImpl implements CreditConfigService {

    @Resource
    private CreditConfigMapper creditConfigMapper;

    @Override
    public Long createCreditConfig(CreditConfigSaveReqVO createReqVO) {
        // 插入
        CreditConfigDO creditConfig = BeanUtils.toBean(createReqVO, CreditConfigDO.class);
        creditConfigMapper.insert(creditConfig);

        // 返回
        return creditConfig.getId();
    }

    @Override
    public void updateCreditConfig(CreditConfigSaveReqVO updateReqVO) {
        // 校验存在
        validateCreditConfigExists(updateReqVO.getId());
        // 更新
        CreditConfigDO updateObj = BeanUtils.toBean(updateReqVO, CreditConfigDO.class);
        creditConfigMapper.updateById(updateObj);
    }

    @Override
    public void deleteCreditConfig(Long id) {
        // 校验存在
        validateCreditConfigExists(id);
        // 删除
        creditConfigMapper.deleteById(id);
    }

    @Override
        public void deleteCreditConfigListByIds(List<Long> ids) {
        // 删除
        creditConfigMapper.deleteByIds(ids);
        }


    private void validateCreditConfigExists(Long id) {
        if (creditConfigMapper.selectById(id) == null) {
            throw exception(CREDIT_CONFIG_NOT_EXISTS);
        }
    }

    @Override
    public CreditConfigDO getCreditConfig(Long id) {
        return creditConfigMapper.selectById(id);
    }

    @Override
    public PageResult<CreditConfigDO> getCreditConfigPage(CreditConfigPageReqVO pageReqVO) {
        return creditConfigMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateConfigStatus(List<Long> ids, String status) {
        if (org.springframework.util.CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 使用 UpdateWrapper 批量更新状态
        UpdateWrapper<CreditConfigDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids)
                .set("status", status);
        creditConfigMapper.update(null, updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveConfig(CreditConfigSaveReqVO saveReqVO) {
        CreditConfigDO config = BeanUtils.toBean(saveReqVO, CreditConfigDO.class);
        if (saveReqVO.getId() == null) {
            // 新增：调用 create 逻辑
            creditConfigMapper.insert(config);
        } else {
            // 更新：调用 update 逻辑，先校验存在
            validateMemberConfigExists(saveReqVO.getId());
            creditConfigMapper.updateById(config);
        }
    }

    @Override
    public CreditConfigChartRespVO getCreditConfigChart(CreditConfigChartReqVO chartReqVO) {
        CreditConfigChartRespVO chartRespVO = new CreditConfigChartRespVO();
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

        //饼图数据
        List<CreditConfigChartRespVO.ConfigTypeDistributionVO> configTypeDistribution =
                creditConfigMapper.selectConfigTypeDistribution(parsed.getStart(), parsed.getEnd());
        chartRespVO.setConfigTypeDistribution(configTypeDistribution);

        //计算总数
        chartRespVO.setEffectConfigCount(creditConfigMapper.selectEffectConfigCount(parsed.getStart(), parsed.getEnd()));

        //计算比率（模拟）
        chartRespVO.setCreditScoreAccuracy(
                //creditConfigMapper.selectCreditScoreAccuracy(parsed.getStart(), parsed.getEnd())
                //未完成
                 BigDecimal.valueOf(0.58)
        );


        return chartRespVO;
    }

    private void validateMemberConfigExists(Long id) {
        if (creditConfigMapper.selectById(id) == null) {
            throw new ServiceException(CREDIT_CONFIG_NOT_EXISTS);
        }
    }

}