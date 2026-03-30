package cn.iocoder.yudao.module.appearance.service.outdoorad.all;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.all.vo.*;
import cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad.all.OutdoorAdDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.appearance.dal.mysql.outdoorad.all.OutdoorAdMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.appearance.enums.ErrorCodeConstants.*;

/**
 * 户外广告 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class OutdoorAdServiceImpl implements OutdoorAdService {

    @Resource
    private OutdoorAdMapper outdoorAdMapper;

    @Override
    public Long createOutdoorAd( OutdoorAdSaveReqVO createReqVO) {
        // 插入
        OutdoorAdDO outdoorAd = BeanUtils.toBean(createReqVO, OutdoorAdDO.class);
        outdoorAdMapper.insert(outdoorAd);
        // 返回
        return outdoorAd.getId();
    }

    @Override
    public void updateOutdoorAd( OutdoorAdSaveReqVO updateReqVO) {
        // 校验存在
        validateOutdoorAdExists(updateReqVO.getId());
        // 更新
        OutdoorAdDO updateObj = BeanUtils.toBean(updateReqVO, OutdoorAdDO.class);
        outdoorAdMapper.updateById(updateObj);
    }

    @Override
    public void deleteOutdoorAd(Long id) {
        // 校验存在
        validateOutdoorAdExists(id);
        // 删除
        outdoorAdMapper.deleteById(id);
    }

    private void validateOutdoorAdExists(Long id) {
        if (outdoorAdMapper.selectById(id) == null) {
            throw exception(OUTDOOR_AD_NOT_EXISTS);
        }
    }

    @Override
    public OutdoorAdDO getOutdoorAd(OutdoorAdGetReqVO getReqVO) {
        return outdoorAdMapper.selectOneWithRelations(getReqVO);
    }

    @Override
    public PageResult<OutdoorAdDO> getOutdoorAdPage( OutdoorAdPageReqVO pageReqVO) {
        return outdoorAdMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<OutdoorAdDO> getOutdoorAdPageWithRelations( OutdoorAdPageReqVO pageReqVO) {
        List<OutdoorAdDO> list = outdoorAdMapper.selectPageWithRelations(pageReqVO);
        long total = list.size();
        return new PageResult<>(list, total);
    }

    @Override
    @Transactional
    public Map<String, Object> importOutdoorAd(List<OutdoorAdSaveReqVO> list) {
        Map<String, Object> result = new HashMap<>();
        int successCount = 0;
        int errorCount = 0;
        List<String> errorMessages = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            OutdoorAdSaveReqVO reqVO = list.get(i);
            try {
                // 校验必填字段
                if (reqVO.getName() == null || reqVO.getName().isEmpty()) {
                    errorMessages.add("第" + (i + 1) + "行：广告名称不能为空");
                    errorCount++;
                    continue;
                }
                if (reqVO.getLocation() == null || reqVO.getLocation().isEmpty()) {
                    errorMessages.add("第" + (i + 1) + "行：广告位置不能为空");
                    errorCount++;
                    continue;
                }
                if (reqVO.getAreaCode() == null || reqVO.getAreaCode().isEmpty()) {
                    errorMessages.add("第" + (i + 1) + "行：所属区域不能为空");
                    errorCount++;
                    continue;
                }
                // 校验尺寸格式
                if (reqVO.getApprovedSize() != null && !reqVO.getApprovedSize().isEmpty()) {
                    // 简单的尺寸格式校验，实际项目中可能需要更复杂的校验
                    if (!reqVO.getApprovedSize().matches("^\\d+\\.*\\d*\\s*[xX]\\s*\\d+\\.*\\d*$")) {
                        errorMessages.add("第" + (i + 1) + "行：审批尺寸格式不正确，应为类似 '10x20' 的格式");
                        errorCount++;
                        continue;
                    }
                }
                if (reqVO.getActualSize() != null && !reqVO.getActualSize().isEmpty()) {
                    if (!reqVO.getActualSize().matches("^\\d+\\.*\\d*\\s*[xX]\\s*\\d+\\.*\\d*$")) {
                        errorMessages.add("第" + (i + 1) + "行：实际尺寸格式不正确，应为类似 '10x20' 的格式");
                        errorCount++;
                        continue;
                    }
                }
                // 校验倾斜角度
                if (reqVO.getTiltAngle() != null && (reqVO.getTiltAngle().compareTo(new java.math.BigDecimal(0)) < 0 || 
                    reqVO.getTiltAngle().compareTo(new java.math.BigDecimal(360)) > 0)) {
                    errorMessages.add("第" + (i + 1) + "行：倾斜角度应在0-360之间");
                    errorCount++;
                    continue;
                }
                // 保存数据
                if (reqVO.getId() == null) {
                    createOutdoorAd(reqVO);
                } else {
                    updateOutdoorAd(reqVO);
                }
                successCount++;
            } catch (Exception e) {
                errorMessages.add("第" + (i + 1) + "行：" + e.getMessage());
                errorCount++;
            }
        }

        result.put("successCount", successCount);
        result.put("errorCount", errorCount);
        result.put("errorMessages", errorMessages);
        return result;
    }

    @Override
    public Map<String, Object> getCoreIndicators() {
        Map<String, Object> indicators = new HashMap<>();
        
        // 总广告数
        Integer totalAds = outdoorAdMapper.countTotalAds();
        indicators.put("totalAds", totalAds != null ? totalAds : 0);
        
        // 预警数
        Integer warningAds = outdoorAdMapper.countWarningAds();
        indicators.put("warningAds", warningAds != null ? warningAds : 0);
        
        // 待处置工单数
        Integer pendingOrders = outdoorAdMapper.countPendingOrders();
        indicators.put("pendingOrders", pendingOrders != null ? pendingOrders : 0);
        
        // 已闭环工单数
        Integer closedOrders = outdoorAdMapper.countClosedOrders();
        indicators.put("closedOrders", closedOrders != null ? closedOrders : 0);
        
        // 整改达标率
        double complianceRate = 0;
        if (warningAds != null && warningAds > 0) {
            complianceRate = (double) closedOrders / warningAds * 100;
        }
        indicators.put("complianceRate", Math.round(complianceRate * 100) / 100.0);
        
        return indicators;
    }

    @Override
    public List<Map<String, Object>> getAreaWarningTrend() {
        return outdoorAdMapper.getAreaWarningTrend();
    }

    @Override
    public List<Map<String, Object>> getRecentWarningTrend() {
        return outdoorAdMapper.getRecentWarningTrend();
    }

    @Override
    public List<Map<String, Object>> getAdStatusDistribution() {
        return outdoorAdMapper.getAdStatusDistribution();
    }

    @Override
    public List<Map<String, Object>> getWarningTypeDistribution() {
        return outdoorAdMapper.getWarningTypeDistribution();
    }

    @Override
    public List<Map<String, Object>> getReviewResultDistribution() {
        return outdoorAdMapper.getReviewResultDistribution();
    }
}