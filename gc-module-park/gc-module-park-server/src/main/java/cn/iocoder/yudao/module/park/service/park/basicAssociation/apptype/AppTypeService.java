package cn.iocoder.yudao.module.park.service.park.basicAssociation.apptype;

import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.apptype.vo.AppTypePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.apptype.vo.AppTypeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.apptype.AppTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 行业应用类别 Service 接口
 *
 * @author zhucongquan
 */
public interface AppTypeService {

    /**
     * 创建行业应用类别
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAppType(@Valid AppTypeSaveReqVO createReqVO);

    /**
     * 更新行业应用类别
     *
     * @param updateReqVO 更新信息
     */
    void updateAppType(@Valid AppTypeSaveReqVO updateReqVO);

    /**
     * 删除行业应用类别
     *
     * @param id 编号
     */
    void deleteAppType(Long id);

    /**
     * 获得行业应用类别
     *
     * @param id 编号
     * @return 行业应用类别
     */
    AppTypeDO getAppType(Long id);

    /**
     * 获得行业应用类别分页
     *
     * @param pageReqVO 分页查询
     * @return 行业应用类别分页
     */
    PageResult<AppTypeDO> getAppTypePage(AppTypePageReqVO pageReqVO);

}