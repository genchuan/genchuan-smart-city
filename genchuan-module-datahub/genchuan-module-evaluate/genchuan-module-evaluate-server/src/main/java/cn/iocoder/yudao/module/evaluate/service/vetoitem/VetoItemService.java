package cn.iocoder.yudao.module.evaluate.service.vetoitem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.vetoitem.vo.VetoItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.vetoitem.vo.VetoItemSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.vetoitem.VetoItemDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 否决项 Service 接口
 *
 * @author 芋道源码
 */
public interface VetoItemService {

    /**
     * 创建否决项
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createVetoItem(@Valid VetoItemSaveReqVO createReqVO);

    /**
     * 更新否决项
     *
     * @param updateReqVO 更新信息
     */
    void updateVetoItem(@Valid VetoItemSaveReqVO updateReqVO);

    /**
     * 删除否决项
     *
     * @param id 编号
     */
    void deleteVetoItem(Long id);

    /**
    * 批量删除否决项
    *
    * @param ids 编号
    */
    void deleteVetoItemListByIds(List<Long> ids);

    /**
     * 获得否决项
     *
     * @param id 编号
     * @return 否决项
     */
    VetoItemDO getVetoItem(Long id);

    /**
     * 获得否决项分页
     *
     * @param pageReqVO 分页查询
     * @return 否决项分页
     */
    PageResult<VetoItemDO> getVetoItemPage(VetoItemPageReqVO pageReqVO);

}