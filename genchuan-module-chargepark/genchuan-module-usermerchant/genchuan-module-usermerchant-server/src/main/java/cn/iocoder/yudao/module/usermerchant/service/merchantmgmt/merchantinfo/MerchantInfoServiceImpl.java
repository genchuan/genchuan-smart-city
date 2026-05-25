package cn.iocoder.yudao.module.usermerchant.service.merchantmgmt.merchantinfo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.NameQueryHelper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.TimeRangeParser;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantinfo.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantinfo.MerchantInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.merchantmgmt.merchantinfo.MerchantInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.ILLEGAL_STATUS;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.MERCHANT_INFO_NOT_EXISTS;
import static cn.iocoder.yudao.module.usermerchant.enums.LogRecordConstants.*;

/**
 * 商户信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MerchantInfoServiceImpl implements MerchantInfoService {

    private static final int APPROVE_INDEX = 1;
    private static final int REJECT_INDEX = 0;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private MerchantInfoMapper merchantInfoMapper;

    @Override
    @LogRecord(type = TYPE_MERCHANT_INFO, subType = SUB_TYPE_CREATE_MERCHANT_INFO,
            bizNo = "{{#merchantInfo.id}}",
            success = SUCCESS_CREATE_MERCHANT_INFO)
    public Boolean createMerchantInfo(MerchantInfoUpdateReqVO createReqVO) {
        // 插入
        MerchantInfoDO merchantInfo = BeanUtils.toBean(createReqVO, MerchantInfoDO.class);
        int rows = merchantInfoMapper.insert(merchantInfo);
        // 记录操作日志上下文
        LogRecordContext.putVariable("merchantInfo", merchantInfo);
        // 返回
        return rows > 0;
    }

    @Override
    @LogRecord(type = TYPE_MERCHANT_INFO, subType = SUB_TYPE_UPDATE_MERCHANT_INFO,
            bizNo = "{{#updateReqVO.id}}",
            success = SUCCESS_UPDATE_MERCHANT_INFO)
    public void updateMerchantInfo(MerchantInfoUpdateReqVO updateReqVO) {
        // 校验存在
        validateMerchantInfoExists(updateReqVO.getId());
        // 更新
        MerchantInfoDO updateObj = BeanUtils.toBean(updateReqVO, MerchantInfoDO.class);
        merchantInfoMapper.updateById(updateObj);
    }

    @Override
    @LogRecord(type = TYPE_MERCHANT_INFO, subType = SUB_TYPE_DELETE_MERCHANT_INFO,
            bizNo = "{{#id}}",
            success = SUCCESS_DELETE_MERCHANT_INFO)
    public void deleteMerchantInfo(Long id) {
        // 校验存在
        validateMerchantInfoExists(id);
        // 删除
        merchantInfoMapper.deleteById(id);
    }

    @Override
    @LogRecord(type = TYPE_MERCHANT_INFO, subType = SUB_TYPE_DELETE_MERCHANT_INFO_LIST,
            bizNo = "{{{#ids}}}",
            success = SUCCESS_DELETE_MERCHANT_INFO_LIST)
        public void deleteMerchantInfoListByIds(List<Long> ids) {
        // 删除
        merchantInfoMapper.deleteByIds(ids);
        // 记录操作日志上下文
        LogRecordContext.putVariable("ids", ids);
        }


    private void validateMerchantInfoExists(Long id) {
        if (merchantInfoMapper.selectById(id) == null) {
            throw exception(MERCHANT_INFO_NOT_EXISTS);
        }
    }

    @Override
    public MerchantInfoDO getMerchantInfo(Long id) {
        return merchantInfoMapper.selectById(id);
    }

    @Override
    public PageResult<MerchantInfoDO> getMerchantInfoPage(MerchantInfoPageReqVO pageReqVO) {
        // 分页查询商户数据
        PageResult<MerchantInfoDO> pageResult = merchantInfoMapper.selectPage(pageReqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return pageResult;
        }
        // 对手机号进行脱敏处理
        for (MerchantInfoDO info : pageResult.getList()) {
            String phone = info.getPhone();
            if (phone != null && phone.length() >= 11) {
                // 保留前3位和后4位，中间4位星号
                String masked = phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
                info.setPhone(masked);
            }
            // 如果手机号长度不足11位，原样返回或置空，可根据需求调整
        }
        // 批量填充审核人昵称（通过 Feign 调用 system-server）
        NameQueryHelper.fillUserNames(pageResult.getList(),
                MerchantInfoDO::getAuditorId,
                MerchantInfoDO::setAuditorName,
                adminUserApi);
        return pageResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = TYPE_MERCHANT_INFO, subType = SUB_TYPE_IMPORT_MERCHANT_INFO,
            bizNo = "{{#list.stream().map(MerchantInfoImportExcelVO::getId).collect(T(java.util.stream.Collectors).toList())}}",
            success = SUCCESS_IMPORT_MERCHANT_INFO)
    public Boolean importInfos(List<MerchantInfoImportExcelVO> list, Boolean updateSupport) {
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        for (MerchantInfoImportExcelVO vo : list) {
            // 直接新增，忽略用户传入的 ID，由数据库自增生成
            MerchantInfoDO insertDO = BeanUtils.toBean(vo, MerchantInfoDO.class);
            insertDO.setId(null);   // 确保 ID 不传入，使用数据库自增
            insertDO.setStatus("待审核");
            merchantInfoMapper.insert(insertDO);
        }
        // 记录操作日志上下文
        LogRecordContext.putVariable("list", list);
        LogRecordContext.putVariable("updateSupport", updateSupport);
        return true;
    }

    @Override
    @LogRecord(type = TYPE_MERCHANT_INFO, subType = SUB_TYPE_BATCH_AUDIT_MERCHANT,
            bizNo = "{{{#reqVO.ids}}}",
            success = SUCCESS_BATCH_AUDIT_MERCHANT)
    public void batchUpdatePlateAuth(MerchantInfoSaveReqVO reqVO, int index) {
        List<Long> ids = reqVO.getIds();
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 1. 校验所有 id 存在
        for (Long id : ids) {
            validateMerchantInfoExists(id);
        }

        // 2. 处理不同的审核结果
        UpdateWrapper<MerchantInfoDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids);

        if (index == APPROVE_INDEX) {
            updateWrapper.set("status", "正常");
        } else if (index == REJECT_INDEX) {
            updateWrapper.set("status", "已驳回");
        } else {
            throw new ServiceException(ILLEGAL_STATUS);
        }
        updateWrapper.set("audit_result", reqVO.getAuditResult())
                     .set("audit_time", LocalDateTime.now())
                     .set("auditor_id", getCurrentUserId());

        // 3. 执行更新
        merchantInfoMapper.update(null, updateWrapper);
        // 记录操作日志上下文
        LogRecordContext.putVariable("reqVO", reqVO);
        LogRecordContext.putVariable("index", index);
    }

    /**
     * 获取当前登录用户ID
     */
    private Long getCurrentUserId() {
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        return loginUser != null ? loginUser.getId() : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = TYPE_MERCHANT_INFO, subType = SUB_TYPE_UPDATE_MERCHANT_STATUS,
            bizNo = "{{{#ids}}}",
            success = SUCCESS_UPDATE_MERCHANT_STATUS)
    public void updateMerchantStatus(List<Long> ids, String status) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 使用 UpdateWrapper 批量更新状态
        UpdateWrapper<MerchantInfoDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids)
                .set("status", status);
        merchantInfoMapper.update(null, updateWrapper);
        // 记录操作日志上下文
        LogRecordContext.putVariable("ids", ids);
        LogRecordContext.putVariable("status", status);
    }

    @Override
    public MerchantInfoChartRespVO getMerchantInfoChart(MerchantInfoChartReqVO chartReqVO) {
        MerchantInfoChartRespVO chartRespVO = new MerchantInfoChartRespVO();
        String timeRange = chartReqVO.getTimeRange();

        LocalDateTime start = null;
        LocalDateTime end = null;
        String granularity = "day"; // 默认按日分组

        // 如果传入了时间范围，则尝试解析
        if (StrUtil.isNotBlank(timeRange)) {
            TimeRangeParser.TimeRangeParsed parsed = TimeRangeParser.parse(timeRange);
            if (parsed == null) {
                // 解析失败，返回空数据（或可抛异常）
                return chartRespVO;
            }
            start = parsed.getStart();
            end = parsed.getEnd();
            granularity = parsed.getGranularity();
        }
        // 否则 start, end 保持 null（全量），granularity 保持默认 "day"

        // 折线图数据
        List<MerchantInfoChartRespVO.MerchantGrowthTrendVO> growthTrend =
                merchantInfoMapper.selectMerchantGrowthTrend(start, end, granularity);
        chartRespVO.setMerchantGrowthTrend(growthTrend);

        // 柱状图数据（仅使用时间范围筛选，不涉及分组）
        List<MerchantInfoChartRespVO.MerchantTypeDistributionVO> typeDistribution =
                merchantInfoMapper.selectMerchantTypeDistribution(start, end);
        chartRespVO.setMerchantTypeDistribution(typeDistribution);

        // 总数统计
        chartRespVO.setTotalMerchantCount(merchantInfoMapper.selectTotalMerchantCount(start, end));
        chartRespVO.setNewMerchantCount(merchantInfoMapper.selectNewMerchantCount(start, end));

        return chartRespVO;
    }

}