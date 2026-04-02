package cn.iocoder.yudao.module.vehiclecharging.service.pilealarm;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.pilealarm.PilealarmDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.pilealarm.PilealarmMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;

/**
 * 充电桩告警 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PilealarmServiceImpl implements PilealarmService {

    @Resource
    private PilealarmMapper pilealarmMapper;

    @Override
    public String createPilealarm(PilealarmSaveReqVO createReqVO) {
        // 插入
        PilealarmDO pilealarm = BeanUtils.toBean(createReqVO, PilealarmDO.class);
        pilealarmMapper.insert(pilealarm);

        // 返回
        return pilealarm.getId();
    }

    @Override
    public void updatePilealarm(PilealarmSaveReqVO updateReqVO) {
        // 校验存在
        validatePilealarmExists(updateReqVO.getId());
        // 更新
        PilealarmDO updateObj = BeanUtils.toBean(updateReqVO, PilealarmDO.class);
        pilealarmMapper.updateById(updateObj);
    }

    @Override
    public void deletePilealarm(String id) {
        // 校验存在
        validatePilealarmExists(id);
        // 删除
        pilealarmMapper.deleteById(id);
    }

    @Override
    public void deletePilealarmListByIds(List<String> ids) {
        // 删除
        pilealarmMapper.deleteByIds(ids);
    }


    private void validatePilealarmExists(String id) {
        if (pilealarmMapper.selectById(id) == null) {
            throw exception(PILEALARM_NOT_EXISTS);
        }
    }

    @Override
    public PilealarmDO getPilealarm(String id) {
        return pilealarmMapper.selectById(id);
    }

    @Override
    public PageResult<PilealarmDO> getPilealarmPage(PilealarmPageReqVO pageReqVO) {
        return pilealarmMapper.selectPage(pageReqVO);
    }

}