package cn.iocoder.yudao.module.inspectop.service.oilmonitor;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.oilmonitor.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.oilmonitor.OilMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.oilmonitor.OilMonitorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 油车占位监测 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class OilMonitorServiceImpl implements OilMonitorService {

    @Resource
    private OilMonitorMapper oilMonitorMapper;

    @Override
    public Long createOilMonitor(OilMonitorSaveReqVO createReqVO) {
        // 插入
        OilMonitorDO oilMonitor = BeanUtils.toBean(createReqVO, OilMonitorDO.class);
        oilMonitorMapper.insert(oilMonitor);

        // 返回
        return oilMonitor.getId();
    }

    @Override
    public void updateOilMonitor(OilMonitorSaveReqVO updateReqVO) {
        // 校验存在
        validateOilMonitorExists(updateReqVO.getId());
        // 更新
        OilMonitorDO updateObj = BeanUtils.toBean(updateReqVO, OilMonitorDO.class);
        oilMonitorMapper.updateById(updateObj);
    }

    @Override
    public void deleteOilMonitor(Long id) {
        // 校验存在
        validateOilMonitorExists(id);
        // 删除
        oilMonitorMapper.deleteById(id);
    }

    @Override
        public void deleteOilMonitorListByIds(List<Long> ids) {
        // 删除
        oilMonitorMapper.deleteByIds(ids);
        }


    private void validateOilMonitorExists(Long id) {
        if (oilMonitorMapper.selectById(id) == null) {
            throw exception(OIL_MONITOR_NOT_EXISTS);
        }
    }

    @Override
    public OilMonitorDO getOilMonitor(Long id) {
        return oilMonitorMapper.selectById(id);
    }

    @Override
    public PageResult<OilMonitorDO> getOilMonitorPage(OilMonitorPageReqVO pageReqVO) {
        return oilMonitorMapper.selectPage(pageReqVO);
    }

}