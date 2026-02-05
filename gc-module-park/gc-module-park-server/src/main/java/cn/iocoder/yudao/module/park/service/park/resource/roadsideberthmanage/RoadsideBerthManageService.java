package cn.iocoder.yudao.module.park.service.park.resource.roadsideberthmanage;

import cn.iocoder.yudao.module.park.controller.admin.park.resource.roadsideberthmanage.vo.RoadsideBerthManagePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.roadsideberthmanage.vo.RoadsideBerthManageSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.resource.roadsideberthmanage.RoadsideBerthManageDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 路测泊位管理 Service 接口
 *
 * @author zhucongquan
 */
public interface RoadsideBerthManageService {

    /**
     * 创建路测泊位管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRoadsideBerthManage(@Valid RoadsideBerthManageSaveReqVO createReqVO);

    /**
     * 更新路测泊位管理
     *
     * @param updateReqVO 更新信息
     */
    void updateRoadsideBerthManage(@Valid RoadsideBerthManageSaveReqVO updateReqVO);

    /**
     * 删除路测泊位管理
     *
     * @param id 编号
     */
    void deleteRoadsideBerthManage(Long id);

    /**
     * 获得路测泊位管理
     *
     * @param id 编号
     * @return 路测泊位管理
     */
    RoadsideBerthManageDO getRoadsideBerthManage(Long id);

    /**
     * 获得路测泊位管理分页
     *
     * @param pageReqVO 分页查询
     * @return 路测泊位管理分页
     */
    PageResult<RoadsideBerthManageDO> getRoadsideBerthManagePage(RoadsideBerthManagePageReqVO pageReqVO);

    /**
     * 根据泊位编号获得路测泊位管理
     *
     * @param berthCode 泊位编号
     * @return 路测泊位管理
     */
    RoadsideBerthManageDO getRoadsideBerthManageByBerthCode(String berthCode, String parkId);

}