package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbagetype;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetype.vo.GarbageTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetype.vo.GarbageTypeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetype.GarbageTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetype.GarbageTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 垃圾品类字典 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class GarbageTypeServiceImpl implements GarbageTypeService {

    @Resource
    private GarbageTypeMapper garbageTypeMapper;

    @Override
    public Long createGarbageType(GarbageTypeSaveReqVO createReqVO) {
        // 插入
        GarbageTypeDO garbageType = BeanUtils.toBean(createReqVO, GarbageTypeDO.class);
        garbageTypeMapper.insert(garbageType);
        // 返回
        return garbageType.getId();
    }

    @Override
    public void updateGarbageType(GarbageTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateGarbageTypeExists(updateReqVO.getId());
        // 更新
        GarbageTypeDO updateObj = BeanUtils.toBean(updateReqVO, GarbageTypeDO.class);
        garbageTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteGarbageType(Long id) {
        // 校验存在
        validateGarbageTypeExists(id);
        // 删除
        garbageTypeMapper.deleteById(id);
    }

    private void validateGarbageTypeExists(Long id) {
        if (garbageTypeMapper.selectById(id) == null) {
            throw exception(GARBAGE_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public GarbageTypeDO getGarbageType(Long id) {
        return garbageTypeMapper.selectById(id);
    }

    @Override
    public PageResult<GarbageTypeDO> getGarbageTypePage(GarbageTypePageReqVO pageReqVO) {
        return garbageTypeMapper.selectPage(pageReqVO);
    }

}