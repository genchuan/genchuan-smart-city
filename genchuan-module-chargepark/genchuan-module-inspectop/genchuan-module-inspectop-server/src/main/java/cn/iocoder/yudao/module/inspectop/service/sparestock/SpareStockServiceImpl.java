package cn.iocoder.yudao.module.inspectop.service.sparestock;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.sparestock.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.sparestock.SpareStockDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.sparestock.SpareStockMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 备件仓储 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class SpareStockServiceImpl implements SpareStockService {

    @Resource
    private SpareStockMapper spareStockMapper;

    @Override
    public Long createSpareStock(SpareStockSaveReqVO createReqVO) {
        // 插入
        SpareStockDO spareStock = BeanUtils.toBean(createReqVO, SpareStockDO.class);
        spareStockMapper.insert(spareStock);

        // 返回
        return spareStock.getId();
    }

    @Override
    public void updateSpareStock(SpareStockSaveReqVO updateReqVO) {
        // 校验存在
        validateSpareStockExists(updateReqVO.getId());
        // 更新
        SpareStockDO updateObj = BeanUtils.toBean(updateReqVO, SpareStockDO.class);
        spareStockMapper.updateById(updateObj);
    }

    @Override
    public void deleteSpareStock(Long id) {
        // 校验存在
        validateSpareStockExists(id);
        // 删除
        spareStockMapper.deleteById(id);
    }

    @Override
        public void deleteSpareStockListByIds(List<Long> ids) {
        // 删除
        spareStockMapper.deleteByIds(ids);
        }


    private void validateSpareStockExists(Long id) {
        if (spareStockMapper.selectById(id) == null) {
            throw exception(SPARE_STOCK_NOT_EXISTS);
        }
    }

    @Override
    public SpareStockDO getSpareStock(Long id) {
        return spareStockMapper.selectById(id);
    }

    @Override
    public PageResult<SpareStockDO> getSpareStockPage(SpareStockPageReqVO pageReqVO) {
        return spareStockMapper.selectPage(pageReqVO);
    }

}