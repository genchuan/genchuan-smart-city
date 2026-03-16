package cn.iocoder.yudao.module.evaluate.service.area;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.area.vo.AreaPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.area.vo.AreaSaveReqVO;
import cn.iocoder.yudao.module.evaluate.controller.common.vo.SelectOptionRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.area.AreaDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.area.AreaMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.AREA_NOT_EXISTS;

/**
 * 区域编码 Service 实现类
 *
 * @author 亘川智城
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
    // Service 核心逻辑
    @Override
    public List<SelectOptionRespVO> getAreaSimpleList() {
        // 假设 AreaDO 有 areaCode 和 areaName 字段
        List<AreaDO> list = areaMapper.selectList(
                new LambdaQueryWrapperX<AreaDO>().eq(AreaDO::getStatusId, 1)
        );
        // 转换：value存 area_code(String), label存 area_name
        return list.stream()
                .map(item -> new SelectOptionRespVO(item.getAreaCode(), item.getAreaName()))
                .collect(Collectors.toList());
    }
}