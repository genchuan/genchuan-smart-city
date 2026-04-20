package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.chargeparklink;

import cn.hutool.core.collection.CollUtil;
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
import static cn.iocoder.yudao.module.stationresource.enums.ErrorCodeConstants.*;

/**
 * 充停联动 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ChargeParkLinkServiceImpl implements ChargeParkLinkService {

    @Resource
    private ChargeParkLinkMapper chargeParkLinkMapper;

    @Override
    public Long createChargeParkLink(ChargeParkLinkSaveReqVO createReqVO) {
        // 插入
        ChargeParkLinkDO chargeParkLink = BeanUtils.toBean(createReqVO, ChargeParkLinkDO.class);
        chargeParkLinkMapper.insert(chargeParkLink);

        // 返回
        return chargeParkLink.getId();
    }

    @Override
    public void updateChargeParkLink(ChargeParkLinkSaveReqVO updateReqVO) {
        // 校验存在
        validateChargeParkLinkExists(updateReqVO.getId());
        // 更新
        ChargeParkLinkDO updateObj = BeanUtils.toBean(updateReqVO, ChargeParkLinkDO.class);
        chargeParkLinkMapper.updateById(updateObj);
    }

    @Override
    public void deleteChargeParkLink(Long id) {
        // 校验存在
        validateChargeParkLinkExists(id);
        // 删除
        chargeParkLinkMapper.deleteById(id);
    }

    @Override
        public void deleteChargeParkLinkListByIds(List<Long> ids) {
        // 删除
        chargeParkLinkMapper.deleteByIds(ids);
        }


    private void validateChargeParkLinkExists(Long id) {
        if (chargeParkLinkMapper.selectById(id) == null) {
            throw exception(CHARGE_PARK_LINK_NOT_EXISTS);
        }
    }

    @Override
    public ChargeParkLinkDO getChargeParkLink(Long id) {
        return chargeParkLinkMapper.selectById(id);
    }

    @Override
    public PageResult<ChargeParkLinkDO> getChargeParkLinkPage(ChargeParkLinkPageReqVO pageReqVO) {
        return chargeParkLinkMapper.selectPage(pageReqVO);
    }

}
