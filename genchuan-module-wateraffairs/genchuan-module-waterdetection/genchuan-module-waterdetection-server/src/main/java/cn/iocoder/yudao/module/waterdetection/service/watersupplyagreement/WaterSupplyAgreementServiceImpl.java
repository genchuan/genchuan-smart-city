package cn.iocoder.yudao.module.waterdetection.service.watersupplyagreement;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.watersupplyagreement.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersupplyagreement.WaterSupplyAgreementDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.watersupplyagreement.WaterSupplyAgreementMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 供水协议管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class WaterSupplyAgreementServiceImpl implements WaterSupplyAgreementService {

    @Resource
    private WaterSupplyAgreementMapper waterSupplyAgreementMapper;

    @Override
    public Long createWaterSupplyAgreement(WaterSupplyAgreementSaveReqVO createReqVO) {
        // 插入
        WaterSupplyAgreementDO waterSupplyAgreement = BeanUtils.toBean(createReqVO, WaterSupplyAgreementDO.class);
        waterSupplyAgreementMapper.insert(waterSupplyAgreement);
        // 返回
        return waterSupplyAgreement.getId();
    }

    @Override
    public void updateWaterSupplyAgreement(WaterSupplyAgreementSaveReqVO updateReqVO) {
        // 校验存在
        validateWaterSupplyAgreementExists(updateReqVO.getId());
        // 更新
        WaterSupplyAgreementDO updateObj = BeanUtils.toBean(updateReqVO, WaterSupplyAgreementDO.class);
        waterSupplyAgreementMapper.updateById(updateObj);
    }

    @Override
    public void deleteWaterSupplyAgreement(Long id) {
        // 校验存在
        validateWaterSupplyAgreementExists(id);
        // 删除
        waterSupplyAgreementMapper.deleteById(id);
    }

    private void validateWaterSupplyAgreementExists(Long id) {
        if (waterSupplyAgreementMapper.selectById(id) == null) {
            throw exception(WATER_SUPPLY_AGREEMENT_NOT_EXISTS);
        }
    }

    @Override
    public WaterSupplyAgreementDO getWaterSupplyAgreement(Long id) {
        return waterSupplyAgreementMapper.selectById(id);
    }

    @Override
    public PageResult<WaterSupplyAgreementDO> getWaterSupplyAgreementPage(WaterSupplyAgreementPageReqVO pageReqVO) {
        return waterSupplyAgreementMapper.selectPage(pageReqVO);
    }

}