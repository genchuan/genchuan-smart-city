package cn.iocoder.yudao.module.envirhealth.service.dictionary.greentype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.greentype.vo.GreenTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.greentype.vo.GreenTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.GreenTypeDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.park.GreenTypeMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.GREEN_TYPE_NOT_EXISTS;

/**
 * 绿化品类字典表【通用复用】 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class GreenTypeServiceImpl implements GreenTypeService {

    @Resource
    private GreenTypeMapper greenTypeMapper;

    @Override
    public Long createGreenType(GreenTypeSaveReqVO createReqVO) {
        // 插入
        GreenTypeDO greenType = BeanUtils.toBean(createReqVO, GreenTypeDO.class);
        greenTypeMapper.insert(greenType);
        // 返回
        return greenType.getId();
    }

    @Override
    public void updateGreenType(GreenTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateGreenTypeExists(updateReqVO.getId());
        // 更新
        GreenTypeDO updateObj = BeanUtils.toBean(updateReqVO, GreenTypeDO.class);
        greenTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteGreenType(Long id) {
        // 校验存在
        validateGreenTypeExists(id);
        // 删除
        greenTypeMapper.deleteById(id);
    }

    private void validateGreenTypeExists(Long id) {
        if (greenTypeMapper.selectById(id) == null) {
            throw exception(GREEN_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public GreenTypeDO getGreenType(Long id) {
        return greenTypeMapper.selectById(id);
    }

    @Override
    public PageResult<GreenTypeDO> getGreenTypePage(GreenTypePageReqVO pageReqVO) {
        return greenTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OptionVO> getGreenTypeOptions() {

        List<GreenTypeDO> list;
        list = greenTypeMapper.selectList(
                new LambdaQueryWrapperX<GreenTypeDO>()
                        .eq(GreenTypeDO::getDeleted, 0)
                        .orderByDesc(GreenTypeDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, greenTypeDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(greenTypeDO.getGreenName());
            vo.setValue(greenTypeDO.getGreenTypeId());
            return vo;
        });
    }
}