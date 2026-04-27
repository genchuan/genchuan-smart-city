package cn.iocoder.yudao.module.usermerchant.service.merchantmgmt.merchantinfo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo.UserInfoChartReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo.UserInfoChartRespVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.userinfo.UserInfoDO;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.NameQueryHelper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.TimeRangeParser;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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

/**
 * 商户信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MerchantInfoServiceImpl implements MerchantInfoService {

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private MerchantInfoMapper merchantInfoMapper;

    @Override
    public Boolean createMerchantInfo(MerchantInfoSaveReqVO createReqVO) {
        // 插入
        MerchantInfoDO merchantInfo = BeanUtils.toBean(createReqVO, MerchantInfoDO.class);
        int rows = merchantInfoMapper.insert(merchantInfo);

        // 返回
        return rows > 0;
    }

    @Override
    public void updateMerchantInfo(MerchantInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateMerchantInfoExists(updateReqVO.getId());
        // 更新
        MerchantInfoDO updateObj = BeanUtils.toBean(updateReqVO, MerchantInfoDO.class);
        merchantInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteMerchantInfo(Long id) {
        // 校验存在
        validateMerchantInfoExists(id);
        // 删除
        merchantInfoMapper.deleteById(id);
    }

    @Override
        public void deleteMerchantInfoListByIds(List<Long> ids) {
        // 删除
        merchantInfoMapper.deleteByIds(ids);
        }


    private void validateMerchantInfoExists(Long id) {
        if (merchantInfoMapper.selectById(id) == null) {
            throw exception(MERCHANT_INFO_NOT_EXISTS);
        }
    }

    @Override
    public MerchantInfoDO getMerchantInfo(Long id) {
        MerchantInfoDO merchant = merchantInfoMapper.selectById(id);
        if (merchant != null) {
            String phone = merchant.getPhone();
            if (phone != null && phone.length() >= 11) {
                // 保留前3位和后4位，中间4位星号
                String masked = phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
                merchant.setPhone(masked);
            }
            // 如果手机号长度不足11位，原样返回或置空，可根据需求调整
        }
        return merchant;
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
    public Boolean importInfos(List<MerchantInfoImportExcelVO> list, Boolean updateSupport) {
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        for (MerchantInfoImportExcelVO vo : list) {
            if (vo.getId() != null) {
                MerchantInfoDO existDO = merchantInfoMapper.selectById(vo.getId());
                if (existDO != null) {
                    if (Boolean.TRUE.equals(updateSupport)) {
                        // 更新：复制属性，但保护创建信息
                        MerchantInfoDO updateDO = BeanUtils.toBean(vo, MerchantInfoDO.class);
                        updateDO.setCreator(null);
                        updateDO.setCreateTime(null);
                        merchantInfoMapper.updateById(updateDO);
                    } else {
                        // updateSupport = false，跳过该条记录
                        continue;
                    }
                } else {
                    // ID 不存在，按新增处理（忽略用户提供的 ID，由数据库自增）
                    MerchantInfoDO insertDO = BeanUtils.toBean(vo, MerchantInfoDO.class);
                    insertDO.setId(null);
                    merchantInfoMapper.insert(insertDO);
                }
            } else {
                // 无 ID，直接新增
                MerchantInfoDO insertDO = BeanUtils.toBean(vo, MerchantInfoDO.class);
                merchantInfoMapper.insert(insertDO);
            }
        }
        return true;
    }

    @Override
    public void batchUpdatePlateAuth(MerchantInfoSaveReqVO reqVO,int index) {
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

        if (index == 1) {
            updateWrapper.set("status", "正常");
        } else if (index == 0) {
            updateWrapper.set("status", "已驳回");
        } else {
            throw new ServiceException(ILLEGAL_STATUS);
        }
        updateWrapper.set("audit_result", reqVO.getAuditResult())
                     .set("audit_time", LocalDateTime.now())
                     .set("auditor_id", getCurrentUserId());

        // 3. 执行更新
        merchantInfoMapper.update(null, updateWrapper);
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
    public void updateMerchantStatus(List<Long> ids, String status) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 使用 UpdateWrapper 批量更新状态
        UpdateWrapper<MerchantInfoDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids)
                .set("status", status);
        merchantInfoMapper.update(null, updateWrapper);
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