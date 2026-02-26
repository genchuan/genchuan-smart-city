package cn.iocoder.yudao.module.envir.service.area;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.area.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.area.AreaDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.area.AreaMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 区域编码 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AreaServiceImpl implements AreaService {

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