package cn.iocoder.yudao.module.evaluate.service.objecttype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.objecttype.vo.ObjectTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.objecttype.vo.ObjectTypeSaveReqVO;
import cn.iocoder.yudao.module.evaluate.controller.common.vo.SelectOptionRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.objecttype.ObjectTypeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.OBJECT_TYPE_NOT_EXISTS;

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

    // Service 核心逻辑
    @Override
    public List<SelectOptionRespVO> getObjectTypeSimpleList() {
        List<ObjectTypeDO> list = objectTypeMapper.selectList(
                new LambdaQueryWrapperX<ObjectTypeDO>()
        );
        // 转换：value存 id(Long), label存 name
        return list.stream()
                .map(item -> new SelectOptionRespVO(item.getTypeId(), item.getName()))
                .collect(Collectors.toList());
    }
}