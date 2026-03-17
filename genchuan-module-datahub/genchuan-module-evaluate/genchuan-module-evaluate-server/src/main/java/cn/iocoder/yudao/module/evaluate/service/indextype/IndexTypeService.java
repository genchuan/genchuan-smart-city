package cn.iocoder.yudao.module.evaluate.service.indextype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.indextype.vo.IndexTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.indextype.vo.IndexTypeSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.indextype.IndexTypeDO;
import jakarta.validation.Valid;

/**
 * 指标类型字典 Service 接口
 *
 * @author 亘川智城
 */
public interface IndexTypeService {

    /**
     * 创建指标类型字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIndexType(@Valid IndexTypeSaveReqVO createReqVO);

    /**
     * 更新指标类型字典
     *
     * @param updateReqVO 更新信息
     */
    void updateIndexType(@Valid IndexTypeSaveReqVO updateReqVO);

    /**
     * 删除指标类型字典
     *
     * @param id 编号
     */
    void deleteIndexType(Long id);

    /**
     * 获得指标类型字典
     *
     * @param id 编号
     * @return 指标类型字典
     */
    IndexTypeDO getIndexType(Long id);

    /**
     * 获得指标类型字典分页
     *
     * @param pageReqVO 分页查询
     * @return 指标类型字典分页
     */
    PageResult<IndexTypeDO> getIndexTypePage(IndexTypePageReqVO pageReqVO);

    /**
     * 根据typeId获取指标类型
     *
     * @param typeId 类型ID
     * @return 指标类型
     */
    IndexTypeDO getIndexTypeByTypeId(String typeId);

    /**
     * 根据name获取指标类型
     *
     * @param name 类型名称
     * @return 指标类型
     */
    IndexTypeDO getIndexTypeByName(String name);

}