package cn.iocoder.yudao.module.waterdetection.service.meteruserrelation;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.meteruserrelation.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.meteruserrelation.MeterUserRelationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.meteruserrelation.MeterUserRelationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 户表关联及变更管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class MeterUserRelationServiceImpl implements MeterUserRelationService {

    @Resource
    private MeterUserRelationMapper meterUserRelationMapper;

    @Override
    public Long createMeterUserRelation(MeterUserRelationSaveReqVO createReqVO) {
        // 插入
        MeterUserRelationDO meterUserRelation = BeanUtils.toBean(createReqVO, MeterUserRelationDO.class);
        meterUserRelationMapper.insert(meterUserRelation);
        // 返回
        return meterUserRelation.getId();
    }

    @Override
    public void updateMeterUserRelation(MeterUserRelationSaveReqVO updateReqVO) {
        // 校验存在
        validateMeterUserRelationExists(updateReqVO.getId());
        // 更新
        MeterUserRelationDO updateObj = BeanUtils.toBean(updateReqVO, MeterUserRelationDO.class);
        meterUserRelationMapper.updateById(updateObj);
    }

    @Override
    public void deleteMeterUserRelation(Long id) {
        // 校验存在
        validateMeterUserRelationExists(id);
        // 删除
        meterUserRelationMapper.deleteById(id);
    }

    private void validateMeterUserRelationExists(Long id) {
        if (meterUserRelationMapper.selectById(id) == null) {
            throw exception(METER_USER_RELATION_NOT_EXISTS);
        }
    }

    @Override
    public MeterUserRelationDO getMeterUserRelation(Long id) {
        return meterUserRelationMapper.selectById(id);
    }

    @Override
    public PageResult<MeterUserRelationDO> getMeterUserRelationPage(MeterUserRelationPageReqVO pageReqVO) {
        return meterUserRelationMapper.selectPage(pageReqVO);
    }

}