package cn.iocoder.yudao.module.envirhealth.service.dictionary.area;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.area.vo.AreaOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.area.vo.AreaPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.area.vo.AreaSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.area.AreaDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.dictionary.area.AreaMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.AREA_NOT_EXISTS;

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

    @Override
    public List<AreaOptionVO> getAreaOptions() {

        List<AreaDO> list;
        list = areaMapper.selectList(
                new LambdaQueryWrapperX<AreaDO>()
                        .eq(AreaDO::getDeleted, 0)
                        .orderByDesc(AreaDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, areaDO -> {
            AreaOptionVO vo = new AreaOptionVO();
            vo.setLabel(areaDO.getAreaName());
            vo.setValue(areaDO.getAreaCode());
            return vo;
        });
    }

    @Override
    public String getAreaNameByCode(String areaCode) {
        if (areaCode == null || areaCode.isEmpty()) {
            return null;
        }
        AreaDO areaDO = areaMapper.selectByAreaCode(areaCode);
        return areaDO != null ? areaDO.getAreaName() : null;
    }
}