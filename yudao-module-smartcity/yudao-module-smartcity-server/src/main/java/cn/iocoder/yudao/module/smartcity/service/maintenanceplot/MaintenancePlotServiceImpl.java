package cn.iocoder.yudao.module.smartcity.service.maintenanceplot;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplot.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenanceplot.MaintenancePlotDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.maintenanceplot.MaintenancePlotMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 养护地块 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class MaintenancePlotServiceImpl implements MaintenancePlotService {

    @Resource
    private MaintenancePlotMapper maintenancePlotMapper;

    @Override
    public Long createMaintenancePlot(MaintenancePlotSaveReqVO createReqVO) {
        // 插入
        MaintenancePlotDO maintenancePlot = BeanUtils.toBean(createReqVO, MaintenancePlotDO.class);
        maintenancePlotMapper.insert(maintenancePlot);
        // 返回
        return maintenancePlot.getId();
    }

    @Override
    public void updateMaintenancePlot(MaintenancePlotSaveReqVO updateReqVO) {
        // 校验存在
        validateMaintenancePlotExists(updateReqVO.getId());
        // 更新
        MaintenancePlotDO updateObj = BeanUtils.toBean(updateReqVO, MaintenancePlotDO.class);
        maintenancePlotMapper.updateById(updateObj);
    }

    @Override
    public void deleteMaintenancePlot(Long id) {
        // 校验存在
        validateMaintenancePlotExists(id);
        // 删除
        maintenancePlotMapper.deleteById(id);
    }

    private void validateMaintenancePlotExists(Long id) {
        if (maintenancePlotMapper.selectById(id) == null) {
            throw exception(MAINTENANCE_PLOT_NOT_EXISTS);
        }
    }

    @Override
    public MaintenancePlotDO getMaintenancePlot(Long id) {
        return maintenancePlotMapper.selectById(id);
    }

    @Override
    public PageResult<MaintenancePlotDO> getMaintenancePlotPage(MaintenancePlotPageReqVO pageReqVO) {
        return maintenancePlotMapper.selectPage(pageReqVO);
    }

}