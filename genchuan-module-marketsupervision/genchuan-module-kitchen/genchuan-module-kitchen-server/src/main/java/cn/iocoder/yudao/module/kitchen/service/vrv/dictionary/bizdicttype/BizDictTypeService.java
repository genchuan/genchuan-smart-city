package cn.iocoder.yudao.module.kitchen.service.vrv.dictionary.bizdicttype;

import java.util.*;

import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype.vo.BizDictTypePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype.vo.BizDictTypeSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype.vo.ops.AddReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype.vo.ops.UpdateReq;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.vrv.dictionary.bizdicttype.BizDictTypeDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 业务字典分类 Service 接口
 *
 * @author 亘川智城
 */
public interface BizDictTypeService {

    /**
     * 创建业务字典分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBizDictType(@Valid BizDictTypeSaveReqVO createReqVO);

    /**
     * 更新业务字典分类
     *
     * @param updateReqVO 更新信息
     */
    void updateBizDictType(@Valid BizDictTypeSaveReqVO updateReqVO);

    /**
     * 删除业务字典分类
     *
     * @param id 编号
     */
    void deleteBizDictType(Long id);

    /**
    * 批量删除业务字典分类
    *
    * @param ids 编号
    */
    void deleteBizDictTypeListByIds(List<Long> ids);

    /**
     * 获得业务字典分类
     *
     * @param id 编号
     * @return 业务字典分类
     */
    BizDictTypeDO getBizDictType(Long id);

    /**
     * 获得业务字典分类分页
     *
     * @param pageReqVO 分页查询
     * @return 业务字典分类分页
     */
    PageResult<BizDictTypeDO> getBizDictTypePage(BizDictTypePageReqVO pageReqVO);

    Long addBizDictType(AddReq createReqVO);

    void updateBiz(UpdateReq updateReqVO);

    void deleteBizDictTypeBatch(List<Long> ids);
}
