package cn.iocoder.yudao.module.datacenter.service.homequickmenu;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.homequickmenu.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.homequickmenu.HomeQuickMenuDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 系统首页快捷菜单配置 Service 接口
 *
 * @author zhucongquan
 */
public interface HomeQuickMenuService {

    /**
     * 创建系统首页快捷菜单配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHomeQuickMenu(@Valid HomeQuickMenuSaveReqVO createReqVO);

    /**
     * 更新系统首页快捷菜单配置
     *
     * @param updateReqVO 更新信息
     */
    void updateHomeQuickMenu(@Valid HomeQuickMenuSaveReqVO updateReqVO);

    /**
     * 删除系统首页快捷菜单配置
     *
     * @param id 编号
     */
    void deleteHomeQuickMenu(Long id);

    /**
     * 获得系统首页快捷菜单配置
     *
     * @param id 编号
     * @return 系统首页快捷菜单配置
     */
    HomeQuickMenuDO getHomeQuickMenu(Long id);

    /**
     * 获得系统首页快捷菜单配置分页
     *
     * @param pageReqVO 分页查询
     * @return 系统首页快捷菜单配置分页
     */
    PageResult<HomeQuickMenuDO> getHomeQuickMenuPage(HomeQuickMenuPageReqVO pageReqVO);

}