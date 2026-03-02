package cn.iocoder.yudao.module.evaluate.service.objecttype;

import cn.iocoder.yudao.module.evaluate.controller.admin.sys.objecttype.vo.ObjectTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.objecttype.vo.ObjectTypeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 对象类型字典 Service 接口
 *
 * @author 亘川智城
 */
public interface ObjectTypeService {

    /**
     * 创建对象类型字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createObjectType(@Valid ObjectTypeSaveReqVO createReqVO);

    /**
     * 更新对象类型字典
     *
     * @param updateReqVO 更新信息
     */
    void updateObjectType(@Valid ObjectTypeSaveReqVO updateReqVO);

    /**
     * 删除对象类型字典
     *
     * @param id 编号
     */
    void deleteObjectType(Long id);

    /**
     * 获得对象类型字典
     *
     * @param id 编号
     * @return 对象类型字典
     */
    ObjectTypeDO getObjectType(Long id);

    /**
     * 获得对象类型字典分页
     *
     * @param pageReqVO 分页查询
     * @return 对象类型字典分页
     */
    PageResult<ObjectTypeDO> getObjectTypePage(ObjectTypePageReqVO pageReqVO);

}