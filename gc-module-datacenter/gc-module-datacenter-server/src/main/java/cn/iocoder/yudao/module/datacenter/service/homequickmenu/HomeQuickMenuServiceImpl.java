package cn.iocoder.yudao.module.datacenter.service.homequickmenu;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.homequickmenu.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.homequickmenu.HomeQuickMenuDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.homequickmenu.HomeQuickMenuMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 系统首页快捷菜单配置 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class HomeQuickMenuServiceImpl implements HomeQuickMenuService {

    @Resource
    private HomeQuickMenuMapper homeQuickMenuMapper;

    @Override
    public Long createHomeQuickMenu(HomeQuickMenuSaveReqVO createReqVO) {
        // 插入
        HomeQuickMenuDO homeQuickMenu = BeanUtils.toBean(createReqVO, HomeQuickMenuDO.class);
        homeQuickMenuMapper.insert(homeQuickMenu);
        // 返回
        return homeQuickMenu.getId();
    }

    @Override
    public void updateHomeQuickMenu(HomeQuickMenuSaveReqVO updateReqVO) {
        // 校验存在
        validateHomeQuickMenuExists(updateReqVO.getId());
        // 更新
        HomeQuickMenuDO updateObj = BeanUtils.toBean(updateReqVO, HomeQuickMenuDO.class);
        homeQuickMenuMapper.updateById(updateObj);
    }

    @Override
    public void deleteHomeQuickMenu(Long id) {
        // 校验存在
        validateHomeQuickMenuExists(id);
        // 删除
        homeQuickMenuMapper.deleteById(id);
    }

    private void validateHomeQuickMenuExists(Long id) {
        if (homeQuickMenuMapper.selectById(id) == null) {
            throw exception(HOME_QUICK_MENU_NOT_EXISTS);
        }
    }

    @Override
    public HomeQuickMenuDO getHomeQuickMenu(Long id) {
        return homeQuickMenuMapper.selectById(id);
    }

    @Override
    public PageResult<HomeQuickMenuDO> getHomeQuickMenuPage(HomeQuickMenuPageReqVO pageReqVO) {
        return homeQuickMenuMapper.selectPage(pageReqVO);
    }

}