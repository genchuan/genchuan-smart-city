package cn.iocoder.yudao.module.usermerchant.service.merchantmgmt.merchantlink;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.UserCarChartRespVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.usercar.UserCarDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.merchantmgmt.merchantinfo.MerchantInfoMapper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.NameQueryHelper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.TimeRangeParser;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import static cn.iocoder.yudao.module.usermerchant.enums.LogRecordConstants.*;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantlink.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantlink.MerchantLinkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.merchantmgmt.merchantlink.MerchantLinkMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 商户对接 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MerchantLinkServiceImpl implements MerchantLinkService {
    private static final String STATUS_LINK = "已对接";
    private static final String STATUS_UNLINK = "未对接";

    @Resource
    private MerchantLinkMapper merchantLinkMapper;

    @Resource
    private MerchantInfoMapper merchantInfoMapper;

    @Override
    @LogRecord(type = TYPE_MERCHANT_LINK, subType = SUB_TYPE_CREATE_MERCHANT_LINK,
            bizNo = "{{#merchantLink.id}}",
            success = SUCCESS_CREATE_MERCHANT_LINK)
    public Boolean createMerchantLink(MerchantLinkSaveReqVO createReqVO) {
        // 插入
        MerchantLinkDO merchantLink = BeanUtils.toBean(createReqVO, MerchantLinkDO.class);
        int rows = merchantLinkMapper.insert(merchantLink);
        // 记录操作日志上下文
        LogRecordContext.putVariable("merchantLink", merchantLink);
        // 返回
        return rows > 0;
    }

    @Override
    @LogRecord(type = TYPE_MERCHANT_LINK, subType = SUB_TYPE_UPDATE_MERCHANT_LINK,
            bizNo = "{{#updateReqVO.id}}",
            success = SUCCESS_UPDATE_MERCHANT_LINK)
    public void updateMerchantLink(MerchantLinkSaveReqVO updateReqVO) {
        // 校验存在
        validateMerchantLinkExists(updateReqVO.getId());
        // 更新
        MerchantLinkDO updateObj = BeanUtils.toBean(updateReqVO, MerchantLinkDO.class);
        merchantLinkMapper.updateById(updateObj);
    }

    @Override
    @LogRecord(type = TYPE_MERCHANT_LINK, subType = SUB_TYPE_DELETE_MERCHANT_LINK,
            bizNo = "{{#id}}",
            success = SUCCESS_DELETE_MERCHANT_LINK)
    public void deleteMerchantLink(Long id) {
        // 校验存在
        validateMerchantLinkExists(id);
        // 删除
        merchantLinkMapper.deleteById(id);
    }

    @Override
    @LogRecord(type = TYPE_MERCHANT_LINK, subType = SUB_TYPE_DELETE_MERCHANT_LINK_LIST,
            bizNo = "{{{#ids}}}",
            success = SUCCESS_DELETE_MERCHANT_LINK_LIST)
    public void deleteMerchantLinkListByIds(List<Long> ids) {
        // 删除
        merchantLinkMapper.deleteByIds(ids);
        //记录操作日志上下文
        LogRecordContext.putVariable("ids", ids);
    }


    private void validateMerchantLinkExists(Long id) {
        if (merchantLinkMapper.selectById(id) == null) {
            throw exception(MERCHANT_LINK_NOT_EXISTS);
        }
    }

    @Override
    public MerchantLinkDO getMerchantLink(Long id) {
        return merchantLinkMapper.selectById(id);
    }

    @Override
    public PageResult<MerchantLinkDO> getMerchantLinkPage(MerchantLinkPageReqVO pageReqVO) {
        if (StrUtil.isNotBlank(pageReqVO.getMerchantName())) {
            Long merchantId = merchantInfoMapper.getIdByNickname(pageReqVO.getMerchantName());
            if (merchantId == null) {
                return new PageResult<>(Collections.emptyList(), 0L);
            }
            pageReqVO.setMerchantId(merchantId);
        }
        PageResult<MerchantLinkDO> pageResult = merchantLinkMapper.selectPage(pageReqVO);
        NameQueryHelper.fillNamesByIds(
                pageResult.getList(),
                MerchantLinkDO::getMerchantId,
                MerchantLinkDO::setMerchantName,
                "merchant_info", "id", "name"
        );
        return pageResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveMerchantLink(MerchantLinkSaveReqVO saveReqVO) {
        if (saveReqVO.getId() == null) {
            // 新增
            return createMerchantLink(saveReqVO);
        } else {
            // 更新
            updateMerchantLink(saveReqVO);
            return true;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = TYPE_MERCHANT_LINK, subType = SUB_TYPE_LINK_MERCHANT, // 子类型可统一，用 success 区分
            bizNo = "{{{#reqVO.ids}}}",
            success = "#status == T(cn.iocoder.yudao.module.usermerchant.service.merchantmgmt.merchantlink.MerchantLinkServiceImpl).STATUS_LINK ? '对接商户，ID：' + #reqVO.ids : '断开商户对接，ID：' + #reqVO.ids")
    public void linkMerchantLink(MerchantLinkLinkReqVO reqVO, String status) {
        if (!STATUS_LINK.equals(status) && !STATUS_UNLINK.equals(status)) {
            throw exception(ILLEGAL_STATUS);
        }
        UpdateWrapper<MerchantLinkDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", reqVO.getIds())
                .set("status", status);
        merchantLinkMapper.update(null, updateWrapper);
        // 记录操作日志上下文
        LogRecordContext.putVariable("reqVO", reqVO);
        LogRecordContext.putVariable("status", status);
    }

    @Override
    public MerchantLinkChartRespVO getMerchantLinkChart(MerchantLinkChartReqVO chartReqVO) {
        MerchantLinkChartRespVO chartRespVO = new MerchantLinkChartRespVO();
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
        // 柱状图数据
        List<MerchantLinkChartRespVO.LinkTypeDistributionVO> typeDistribution = merchantLinkMapper.selectLinkTypeDistribution(
                parsed.getStart(), parsed.getEnd(), parsed.getGranularity());
        chartRespVO.setLinkTypeDistribution(typeDistribution);
        // 总数统计
        chartRespVO.setLinkMerchantCount(merchantLinkMapper.selectLinkMerchantCount(parsed.getStart(), parsed.getEnd()));
        // 计算比率
        chartRespVO.setLinkSuccessRate(merchantLinkMapper.selectLinkSuccessRate(parsed.getStart(), parsed.getEnd()));
        return chartRespVO;
    }

}