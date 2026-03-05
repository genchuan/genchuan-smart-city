package cn.iocoder.yudao.module.envirhealth.service.park.greentype;

import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.greentype.GreenTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.greentype.GreenTypeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.GreenTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.park.GreenTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

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

}