package cn.iocoder.yudao.module.vehiclecharging.service.sysarea;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sysarea.vo.AreaPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sysarea.vo.AreaSaveReqVO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sysarea.AreaDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.sysarea.AreaMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;


/**
 * 统一行政区划配置表（树形结构） Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AreaServiceImpl implements AreaService {

    private static final String AREA_NOT_EXISTS = "统一行政区划配置表（树形结构）不存在";
    @Resource
    private AreaMapper areaMapper;

    @Override
    public Long createArea(AreaSaveReqVO createReqVO) {
        // 插入
        AreaDO area = BeanUtils.toBean(createReqVO, AreaDO.class);
        areaMapper.insert(area);

        // 返回
        return area.getId();
    }

    @Override
    public void updateArea(AreaSaveReqVO updateReqVO) {
        // 校验存在
        validateAreaExists(updateReqVO.getId());
        // 更新
        AreaDO updateObj = BeanUtils.toBean(updateReqVO, AreaDO.class);
        areaMapper.updateById(updateObj);
    }

    @Override
    public void deleteArea(Long id) {
        // 校验存在
        validateAreaExists(id);
        // 删除
        areaMapper.deleteById(id);
    }

    @Override
        public void deleteAreaListByIds(List<Long> ids) {
        // 删除
        areaMapper.deleteByIds(ids);
        }


    private void validateAreaExists(Long id) {
        if (areaMapper.selectById(id) == null) {
            throw exception(AREA_NOT_EXISTS);
        }
    }

    @Override
    public AreaDO getArea(Long id) {
        return areaMapper.selectById(id);
    }

    @Override
    public PageResult<AreaDO> getAreaPage(AreaPageReqVO pageReqVO) {
        return areaMapper.selectPage(pageReqVO);
    }

}