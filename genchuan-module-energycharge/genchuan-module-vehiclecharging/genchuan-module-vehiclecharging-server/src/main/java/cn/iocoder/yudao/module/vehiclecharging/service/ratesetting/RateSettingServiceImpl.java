package cn.iocoder.yudao.module.vehiclecharging.service.ratesetting;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.ratesetting.RateSettingDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.ratesetting.RateSettingMapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;

/**
 * 费率设置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RateSettingServiceImpl implements RateSettingService {

    @Resource
    private RateSettingMapper rateSettingMapper;

    @Override
    public Long createRateSetting(RateSettingSaveReqVO createReqVO) {
        // 插入
        RateSettingDO rateSetting = BeanUtils.toBean(createReqVO, RateSettingDO.class);
        rateSettingMapper.insert(rateSetting);

        // 返回
        return rateSetting.getId();
    }

    @Override
    public void updateRateSetting(RateSettingSaveReqVO updateReqVO) {
        // 校验存在
        validateRateSettingExists(updateReqVO.getId());
        // 更新
        RateSettingDO updateObj = BeanUtils.toBean(updateReqVO, RateSettingDO.class);
        rateSettingMapper.updateById(updateObj);
    }

    @Override
    public void deleteRateSetting(Long id) {
        // 校验存在
        validateRateSettingExists(id);
        // 删除
        rateSettingMapper.deleteById(id);
    }

    @Override
        public void deleteRateSettingListByIds(List<Long> ids) {
        // 删除
        rateSettingMapper.deleteByIds(ids);
        }


    private void validateRateSettingExists(Long id) {
        if (rateSettingMapper.selectById(id) == null) {
            throw exception(RATE_SETTING_NOT_EXISTS);
        }
    }

    @Override
    public RateSettingDO getRateSetting(Long id) {
        return rateSettingMapper.selectById(id);
    }

    @Override
    public PageResult<RateSettingRespVO> getRateSettingPage(RateSettingPageReqVO pageReqVO) {
        PageResult result = new PageResult<>();

        List<RateSettingRespVO> list = rateSettingMapper.selectPage(pageReqVO);

        long count = rateSettingMapper.selectPageCount(pageReqVO);

        result.setTotal(count);
        result.setList(list);

        return result;
    }

    @Override
    public void disableRateSetting(RateSettingDisableReqVO reqVO) {
        List<Long> idList = reqVO.getIdList();
        if (CollUtil.isEmpty(idList)) {
            throw exception("修改列表不存在");
        }

        // 1. 批量查询费率方案，校验是否存在
        List<RateSettingDO> rateSettings = rateSettingMapper.selectBatchIds(idList);
        if (CollUtil.isEmpty(rateSettings)) {
            throw exception("修改列表不存在数据库");
        }

        // 2. 批量更新为【已失效】状态
        LambdaUpdateChainWrapper<RateSettingDO> wrapper = new LambdaUpdateChainWrapper<>(rateSettingMapper);
        wrapper.in(RateSettingDO::getId, idList)
                .set(RateSettingDO::getRateStatus, "已失效");
        wrapper.update();
    }

    @Override
    public void enableRateSetting(RateSettingEnableReqVO reqVO) {
        List<Long> idList = reqVO.getIdList();
        if (CollUtil.isEmpty(idList)) {
            throw exception("修改列表不存在");
        }

        // 1. 批量查询费率方案，校验是否存在
        List<RateSettingDO> rateSettings = rateSettingMapper.selectBatchIds(idList);
        if (CollUtil.isEmpty(rateSettings)) {
            throw exception("修改列表不存在数据库");
        }

        // 2. 批量更新为【已失效】状态
        LambdaUpdateChainWrapper<RateSettingDO> wrapper = new LambdaUpdateChainWrapper<>(rateSettingMapper);
        wrapper.in(RateSettingDO::getId, idList)
                .set(RateSettingDO::getRateStatus, "已生效");
        wrapper.update();
    }

    @Override
    public void copyRateSetting(RateSettingCopyReqVO reqVO) {
//        RateSettingDO rateSettingDO = rateSettingMapper.selectById(reqVO.getId());
//
//        rateSettingDO.setRateCode(reqVO.getNewRateCode());
//        rateSettingDO.set(reqVO.getNewRateCode());

    }

}
