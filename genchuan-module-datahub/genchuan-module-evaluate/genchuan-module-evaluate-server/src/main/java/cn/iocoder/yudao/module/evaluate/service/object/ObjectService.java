package cn.iocoder.yudao.module.evaluate.service.object;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.EvalObjectOverviewVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.ObjectPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.ObjectRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.ObjectSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.object.ObjectDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 评价对象 Service 接口
 *
 * @author 亘川智城
 */
public interface ObjectService {

    /**
     * 创建评价对象
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createObject(@Valid ObjectSaveReqVO createReqVO);

    /**
     * 更新评价对象
     *
     * @param updateReqVO 更新信息
     */
    void updateObject(@Valid ObjectSaveReqVO updateReqVO);

    /**
     * 删除评价对象
     *
     * @param id 编号
     */
    void deleteObject(Long id);

    /**
     * 获得评价对象
     *
     * @param id 编号
     * @return 评价对象
     */
    ObjectDO getObject(Long id);

    /**
     * 获得评价对象分页
     *
     * @param pageReqVO 分页查询
     * @return 评价对象分页
     */
    PageResult<ObjectDO> getObjectPage(ObjectPageReqVO pageReqVO);


    ObjectRespVO getDetail(Long id);

    /**
     * 分页查询评价对象（联表）
     */
    PageResult<ObjectRespVO> pageJoinQuery(ObjectPageReqVO reqVO);

    /**
     * 根据ID获取评价对象详情
     */
    ObjectRespVO getDetailById(String objectId);


    /**
     * 批量导入评价对象
     */
    void importObjects(List<ObjectSaveReqVO> importList);

    /**
     * 校验评价对象名称在同一区域是否唯一
     */
    void validateNameUnique(String name, String areaCode, String excludeObjectId);

    //新
    PageResult<ObjectRespVO> getAllObjectPage(ObjectPageReqVO pageParam);

    ObjectRespVO getStatusCount(Integer statusId);

    EvalObjectOverviewVO getOverview();
}