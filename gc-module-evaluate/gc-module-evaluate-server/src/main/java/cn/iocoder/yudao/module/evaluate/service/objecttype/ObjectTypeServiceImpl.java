package cn.iocoder.yudao.module.evaluate.service.objecttype;

import cn.iocoder.yudao.module.evaluate.controller.admin.sys.objecttype.vo.ObjectTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.objecttype.vo.ObjectTypeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.objecttype.ObjectTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

/**
 * 对象类型字典 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ObjectTypeServiceImpl implements ObjectTypeService {

    @Resource
    private ObjectTypeMapper objectTypeMapper;

    @Override
    public Long createObjectType(ObjectTypeSaveReqVO createReqVO) {
        // 插入
        ObjectTypeDO objectType = BeanUtils.toBean(createReqVO, ObjectTypeDO.class);
        objectTypeMapper.insert(objectType);
        // 返回
        return objectType.getId();
    }

    @Override
    public void updateObjectType(ObjectTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateObjectTypeExists(updateReqVO.getId());
        // 更新
        ObjectTypeDO updateObj = BeanUtils.toBean(updateReqVO, ObjectTypeDO.class);
        objectTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteObjectType(Long id) {
        // 校验存在
        validateObjectTypeExists(id);
        // 删除
        objectTypeMapper.deleteById(id);
    }

    private void validateObjectTypeExists(Long id) {
        if (objectTypeMapper.selectById(id) == null) {
            throw exception(OBJECT_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public ObjectTypeDO getObjectType(Long id) {
        return objectTypeMapper.selectById(id);
    }

    @Override
    public PageResult<ObjectTypeDO> getObjectTypePage(ObjectTypePageReqVO pageReqVO) {
        return objectTypeMapper.selectPage(pageReqVO);
    }

}