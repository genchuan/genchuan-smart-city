package cn.iocoder.yudao.module.vehiclecharging.service.abnormalorder;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.PageUtil;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo.NewPileAlarmRespVO;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.abnormalorder.AbnormalOrderDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.abnormalorder.AbnormalOrderMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;
import static com.github.yulichang.extension.kt.toolkit.KtWrappers.update;

/**
 * 异常订单 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AbnormalOrderServiceImpl implements AbnormalOrderService {

    @Resource
    private AbnormalOrderMapper abnormalOrderMapper;

    @Override
    public Long createAbnormalOrder(AbnormalOrderSaveReqVO createReqVO) {
        // 插入
        AbnormalOrderDO abnormalOrder = BeanUtils.toBean(createReqVO, AbnormalOrderDO.class);
        abnormalOrderMapper.insert(abnormalOrder);

        // 返回
        return abnormalOrder.getId();
    }

    @Override
    public void updateAbnormalOrder(AbnormalOrderSaveReqVO updateReqVO) {
        // 校验存在
        validateAbnormalOrderExists(updateReqVO.getId());
        // 更新
        AbnormalOrderDO updateObj = BeanUtils.toBean(updateReqVO, AbnormalOrderDO.class);
        abnormalOrderMapper.updateById(updateObj);
    }

    @Override
    public void deleteAbnormalOrder(Long id) {
        // 校验存在
        validateAbnormalOrderExists(id);
        // 删除
        abnormalOrderMapper.deleteById(id);
    }

    @Override
    public void deleteAbnormalOrderListByIds(List<Long> ids) {
        // 删除
        abnormalOrderMapper.deleteByIds(ids);
    }


    private void validateAbnormalOrderExists(Long id) {
        if (abnormalOrderMapper.selectById(id) == null) {
            throw exception(ORDER_ALARM_NOT_EXISTS);
        }
    }

    @Override
    public AbnormalOrderDO getAbnormalOrder(Long id) {
        return abnormalOrderMapper.selectById(id);
    }

    @Override
    public PageResult<AbnormalOrderDO> getAbnormalOrderPage(AbnormalOrderPageReqVO pageReqVO) {
        return abnormalOrderMapper.selectPage(pageReqVO);
    }

    /**
     * 获取异常订单分页
     *
     * @return 异常订单分页
     */

    @Override
    public PageResult<NewAbnormalOrderRespVO> newgetAbnormalOrderPage(NewAbnormalOrderPageReqVO reqVO) {
        IPage<NewAbnormalOrderRespVO> page = abnormalOrderMapper.selectAbnormalOrderPage(
                MyBatisUtils.buildPage(reqVO),
                reqVO
        );
        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    @Override
    public Boolean verifyAbnormalOrder(AbnormalOrderVerifyReqVO reqVO) {
        UpdateWrapper<AbnormalOrderDO> updateWrapper = new UpdateWrapper<>();
        // 批量 ID 条件
        updateWrapper.in("id", reqVO.getIds());

        // 要更新的字段
        updateWrapper.set("abnormal_status", "已核实");
        updateWrapper.set("check_user", SecurityFrameworkUtils.getLoginUserNickname());
        updateWrapper.set("check_time", LocalDateTime.now());
        updateWrapper.set("abnormal_reason", reqVO.getVerifyResult());
        updateWrapper.set("remark", reqVO.getVerifyRemark());

        abnormalOrderMapper.update(null, updateWrapper);

        return true;
    }

}