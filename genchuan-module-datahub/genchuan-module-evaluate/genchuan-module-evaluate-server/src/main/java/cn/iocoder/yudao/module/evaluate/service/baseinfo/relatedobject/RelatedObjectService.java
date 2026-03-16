package cn.iocoder.yudao.module.evaluate.service.baseinfo.relatedobject;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.relatedobject.vo.RelatedObjectPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.relatedobject.vo.RelatedObjectSaveReqVO;
import cn.iocoder.yudao.module.evaluate.controller.common.vo.SelectOptionRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.baseinfo.relatedobject.RelatedObjectDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 关联对象 Service 接口
 *
 * @author 亘川智城
 */
public interface RelatedObjectService {

    /**
     * 创建关联对象
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRelatedObject(@Valid RelatedObjectSaveReqVO createReqVO);

    /**
     * 更新关联对象
     *
     * @param updateReqVO 更新信息
     */
    void updateRelatedObject(@Valid RelatedObjectSaveReqVO updateReqVO);

    /**
     * 删除关联对象
     *
     * @param id 编号
     */
    void deleteRelatedObject(Long id);

    /**
     * 获得关联对象
     *
     * @param id 编号
     * @return 关联对象
     */
    RelatedObjectDO getRelatedObject(Long id);

    /**
     * 获得关联对象分页
     *
     * @param pageReqVO 分页查询
     * @return 关联对象分页
     */
    PageResult<RelatedObjectDO> getRelatedObjectPage(RelatedObjectPageReqVO pageReqVO);

    List<SelectOptionRespVO> getRelatedObjectSimpleList();
}