package cn.iocoder.yudao.module.energymgmt.service.energymonitor.datacollect;

import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mzt.logapi.context.LogRecordContext;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.datacollect.vo.*;
import cn.iocoder.yudao.module.energymgmt.dal.dataobject.energymonitor.datacollect.EnergyCollectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.energymgmt.dal.mysql.energymonitor.datacollect.EnergyCollectMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.energymgmt.enums.ErrorCodeConstants.*;

/**
 * 能耗采集 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EnergyCollectServiceImpl implements EnergyCollectService {

    @Resource
    private EnergyCollectMapper energyCollectMapper;

    @Override
    public PageResult<EnergyCollectDO> getEnergyCollectPage(EnergyCollectPageReqVO pageReqVO) {
        return energyCollectMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean dockEnergyCollect(EnergyCollectDockReqVO dockReqVO) {
        // 补充信息
        EnergyCollectDO energyCollect = BeanUtils.toBean(dockReqVO, EnergyCollectDO.class);
        energyCollect.setCollectTime(LocalDateTime.now());
        energyCollect.setCollectStatus("采集正常");
        energyCollect.setEnergyValue(BigDecimal.valueOf(10));
        energyCollect.setHandleUser(SecurityFrameworkUtils.getLoginUserNickname());
        energyCollect.setCreator(SecurityFrameworkUtils.getLoginUserNickname());
        energyCollect.setCreateTime(LocalDateTime.now());
        energyCollect.setUpdater(SecurityFrameworkUtils.getLoginUserNickname());
        energyCollect.setUpdateTime(LocalDateTime.now());

        // 插入
        int rows = energyCollectMapper.insert(energyCollect);

        // 返回
        return rows > 0;
    }

    @Override
    public Boolean collectEnergyCollect(EnergyCollectCollectReqVO collectReqVO) {
        // 校验存在
        validateEnergyCollectExists(collectReqVO.getId());

        // 补充信息
        EnergyCollectDO updateObj = BeanUtils.toBean(collectReqVO, EnergyCollectDO.class);
        updateObj.setUpdater(SecurityFrameworkUtils.getLoginUserNickname());
        updateObj.setUpdateTime(LocalDateTime.now());

        // 更新
        int rows = energyCollectMapper.updateById(updateObj);

        // 返回
        return rows > 0;
    }

    @Override
    public Boolean monitorEnergyCollect(EnergyCollectMonitorReqVO monitorReqVO) {
        // 校验存在
        if (CollectionUtils.isEmpty(monitorReqVO.getIds())) {
            return false;
        }

        // 使用 UpdateWrapper 批量更新状态
        UpdateWrapper<EnergyCollectDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", monitorReqVO.getIds())
                .set("rtime_monitoring", 1)
                .set("updater", SecurityFrameworkUtils.getLoginUserNickname())
                .set("update_time", LocalDateTime.now());

        // 更新
        int rows = energyCollectMapper.update(null, updateWrapper);

        // 返回
        return rows > 0;
    }

    @Override
    public EnergyCollectDO getEnergyCollect(Long id) {
        return energyCollectMapper.selectById(id);
    }

    @Override
    public Boolean checkEnergyCollect(EnergyCollectCheckReqVO checkReqVO) {
        //todo：等接了设备再说
        return true;
    }

    @Override
    public Boolean restartEnergyCollect(EnergyCollectRestartReqVO restartReqVO) {
        // 校验存在
        validateEnergyCollectExists(restartReqVO.getId());

        EnergyCollectDO updateObj = BeanUtils.toBean(restartReqVO, EnergyCollectDO.class);
        // 调取对象,检验状态
        if(getEnergyCollect(restartReqVO.getId()).getCollectStatus().equals("采集异常")) {
            updateObj.setCollectStatus("采集正常");
            updateObj.setUpdater(SecurityFrameworkUtils.getLoginUserNickname());
            updateObj.setUpdateTime(LocalDateTime.now());
        } else {
            throw exception(ILLEGAL_STATUS);
        }

        // 更新
        int rows = energyCollectMapper.updateById(updateObj);

        // 返回
        return rows > 0;
    }

    private void validateEnergyCollectExists(Long id) {
        if (energyCollectMapper.selectById(id) == null) {
            throw exception(ENERGY_COLLECT_NOT_EXISTS);
        }
    }
//    ———————————————————— 以上是所需实现层 ————————————————————

    @Override
    public Long createEnergyCollect(EnergyCollectSaveReqVO createReqVO) {
        // 插入
        EnergyCollectDO energyCollect = BeanUtils.toBean(createReqVO, EnergyCollectDO.class);
        energyCollectMapper.insert(energyCollect);

        // 返回
        return energyCollect.getId();
    }

    @Override
    public void updateEnergyCollect(EnergyCollectSaveReqVO updateReqVO) {
        // 校验存在
        validateEnergyCollectExists(updateReqVO.getId());
        // 更新
        EnergyCollectDO updateObj = BeanUtils.toBean(updateReqVO, EnergyCollectDO.class);
        energyCollectMapper.updateById(updateObj);
    }

    @Override
    public void deleteEnergyCollect(Long id) {
        // 校验存在
        validateEnergyCollectExists(id);
        // 删除
        energyCollectMapper.deleteById(id);
    }

    @Override
        public void deleteEnergyCollectListByIds(List<Long> ids) {
        // 删除
        energyCollectMapper.deleteByIds(ids);
        }

}