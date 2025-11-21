package cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarngrid;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarngrid.vo.EarlyWarnGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarngrid.vo.EarlyWarnGridSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarngrid.EarlyWarnGridDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.region.earlywarngrid.EarlyWarnGridMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 按网格分域预警告警统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnGridServiceImpl implements EarlyWarnGridService {

    @Resource
    private EarlyWarnGridMapper earlyWarnGridMapper;

    @Override
    public Long createEarlyWarnGrid(EarlyWarnGridSaveReqVO createReqVO) {
        // 插入
        EarlyWarnGridDO earlyWarnGrid = BeanUtils.toBean(createReqVO, EarlyWarnGridDO.class);
        earlyWarnGridMapper.insert(earlyWarnGrid);
        // 返回
        return earlyWarnGrid.getId();
    }

    @Override
    public void updateEarlyWarnGrid(EarlyWarnGridSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnGridExists(updateReqVO.getId());
        // 更新
        EarlyWarnGridDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnGridDO.class);
        earlyWarnGridMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnGrid(Long id) {
        // 校验存在
        validateEarlyWarnGridExists(id);
        // 删除
        earlyWarnGridMapper.deleteById(id);
    }

    private void validateEarlyWarnGridExists(Long id) {
        if (earlyWarnGridMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_GRID_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnGridDO getEarlyWarnGrid(Long id) {
        return earlyWarnGridMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnGridDO> getEarlyWarnGridPage(EarlyWarnGridPageReqVO pageReqVO) {
        return earlyWarnGridMapper.selectPage(pageReqVO);
    }

}