package cn.iocoder.yudao.module.vehiclecharging.service.ratesetting;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo.chart.*;
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

    /**
     * 复制费率方案
     *
     * @param reqVO 复制请求参数（源ID + 新编码、新生效/失效时间）
     */
    @Override
    public void copyRateSetting(RateSettingCopyReqVO reqVO) {
        // 1. 校验参数：源费率方案ID不能为空
        if (reqVO.getId() == null) {
            throw new IllegalArgumentException("复制的源费率方案ID不能为空");
        }
        // 2. 查询源费率方案（判断是否存在）
        RateSettingDO rateSettingDO = rateSettingMapper.selectById(reqVO.getId());
        if (rateSettingDO == null) {
            throw new IllegalArgumentException("复制的源费率方案不存在，ID：" + reqVO.getId());
        }

        // 3. 赋值新的费率编码（去重重复代码）
        rateSettingDO.setRateCode(reqVO.getNewRateCode());
        // 4. 赋值新的生效/失效时间
        rateSettingDO.setEffectTime(reqVO.getNewStartTime());
        rateSettingDO.setExpireTime(reqVO.getNewEndTime());
        // 5. 复制后的方案默认状态：未生效
        rateSettingDO.setRateStatus("未生效");

        // 6. 关键：清空ID，让MyBatis-Plus执行insert新增，而非update
        rateSettingDO.setId(null);

        // 7. 插入新的费率方案
        rateSettingMapper.insert(rateSettingDO);
    }

    @Override
    public RateSettingStatusCountRespVO getRateSettingStatusCount(RateSettingStatusCountReqVO reqVO) {
        RateSettingStatusCountRespVO respVO = rateSettingMapper.getRateSettingStatusCount(reqVO);
        return respVO;
    }

    @Override
    public List<RateSettingGradeCountRespVO> getRateSettingGradeCount(RateSettingGradeCountReqVO reqVO) {
        List<RateSettingGradeCountRespVO> respVOList =rateSettingMapper.getRateSettingGradeCount(reqVO);
        return respVOList;
    }

    @Override
    public RateSettingChartRespVO getRateSettingChart(RateSettingChartReqVO reqVO) {
        //1.获取卡片信息
        RateSettingStatusCountReqVO rateSettingStatusCountReqVO =new RateSettingStatusCountReqVO();
        rateSettingStatusCountReqVO.setStartTime(reqVO.getStartTime());
        rateSettingStatusCountReqVO.setEndTime(reqVO.getEndTime());
        RateSettingStatusCountRespVO rateSettingStatusCount = getRateSettingStatusCount(rateSettingStatusCountReqVO);
        //2.获取柱状图数据
        RateSettingStationCountReqVO reqVO2 = new RateSettingStationCountReqVO();
        reqVO2.setStartTime(reqVO.getStartTime());
        reqVO2.setEndTime(reqVO.getEndTime());
        List<RateSettingStationCountRespVO> rateSettingStationCountRespVO = rateSettingMapper.getRateSettingStationCount(reqVO2);

        //3.配置返回参数
        RateSettingChartRespVO respVO =new RateSettingChartRespVO();
        respVO.setCardData(rateSettingStatusCount);
        respVO.setBarData(rateSettingStationCountRespVO);

        return respVO;
    }

}
