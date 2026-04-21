package cn.iocoder.yudao.module.kitchen.service.vrv.dictionary.bizdictitem;

import java.util.*;

import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdictitem.vo.BizDictItemPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdictitem.vo.BizDictItemSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdictitem.vo.ops.*;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.vrv.dictionary.bizdictitem.BizDictItemDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 业务字典项 Service 接口
 *
 * @author 亘川智城
 */
public interface BizDictItemService {

    /**
     * 创建业务字典项
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBizDictItem(@Valid BizDictItemSaveReqVO createReqVO);

    /**
     * 更新业务字典项
     *
     * @param updateReqVO 更新信息
     */
    void updateBizDictItem(@Valid BizDictItemSaveReqVO updateReqVO);

    /**
     * 删除业务字典项
     *
     * @param id 编号
     */
    void deleteBizDictItem(Long id);

    /**
    * 批量删除业务字典项
    *
    * @param ids 编号
    */
    void deleteBizDictItemListByIds(List<Long> ids);

    /**
     * 获得业务字典项
     *
     * @param id 编号
     * @return 业务字典项
     */
    BizDictItemDO getBizDictItem(Long id);

    /**
     * 获得业务字典项分页
     *
     * @param pageReqVO 分页查询
     * @return 业务字典项分页
     */
    PageResult<BizDictItemDO> getBizDictItemPage(BizDictItemPageReqVO pageReqVO);

    List<ListByTypeResp> listByType(ListByTypeReq req);

    Long addBizDictItem(@Valid AddReq createReqVO);

    void updateBiz(UpdateReq updateReqVO);

    BatchResult batchAddBizDictItem(List<AddReq> addReqList);

    List<ListByTypeResp> listByTypeFuzzy(ListByTypeFuzzyReq reqVO);
}
