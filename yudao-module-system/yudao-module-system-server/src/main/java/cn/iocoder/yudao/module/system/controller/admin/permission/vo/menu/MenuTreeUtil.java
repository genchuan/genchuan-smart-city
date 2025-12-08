package cn.iocoder.yudao.module.system.controller.admin.permission.vo.menu;


import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.system.controller.admin.permission.vo.menu.MenuTreeRespVO;
import cn.iocoder.yudao.module.system.dal.dataobject.permission.MenuDO;
import cn.iocoder.yudao.module.system.enums.permission.MenuTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单树工具类
 */
@Slf4j
public class MenuTreeUtil {

    /**
     * 根据指定菜单ID列表构建树形结构（不过滤按钮类型，包含所有传入的菜单）
     * 如果父节点不存在于列表中，该菜单将作为根节点显示
     *
     * @param menuList 菜单列表
     * @return 树形结构的菜单列表
     */
    public static List<MenuTreeRespVO> buildMenuTreeByIds(List<MenuDO> menuList) {
        if (CollUtil.isEmpty(menuList)) {
            return Collections.emptyList();
        }

        log.info("构建菜单树，传入菜单数量: {}", menuList.size());

        // 不过滤任何类型，使用所有传入的菜单
        List<MenuTreeRespVO> menuTreeList = menuList.stream()
                .map(MenuTreeUtil::convertToTreeVO)
                .sorted(Comparator.comparing(MenuTreeRespVO::getSort))
                .collect(Collectors.toList());

        log.info("转换后的菜单VO数量: {}", menuTreeList.size());

        // 构建树形结构
        return buildTreeByIds(menuTreeList);
    }

    /**
     * 将菜单列表转换为树形结构（排除按钮类型）
     *
     * @param menuList 菜单列表
     * @return 树形结构的菜单列表
     */
    public static List<MenuTreeRespVO> buildMenuTree(List<MenuDO> menuList) {
        if (CollUtil.isEmpty(menuList)) {
            return Collections.emptyList();
        }

        // 过滤掉按钮类型的菜单
        List<MenuDO> filteredMenus = menuList.stream()
                .filter(menu -> !MenuTypeEnum.BUTTON.getType().equals(menu.getType()))
                .collect(Collectors.toList());

        if (CollUtil.isEmpty(filteredMenus)) {
            return Collections.emptyList();
        }

        // 先按sort排序
        filteredMenus.sort(Comparator.comparing(MenuDO::getSort));

        // 转换为MenuTreeRespVO
        List<MenuTreeRespVO> menuTreeList = filteredMenus.stream()
                .map(MenuTreeUtil::convertToTreeVO)
                .collect(Collectors.toList());

        // 构建树形结构
        return buildTree(menuTreeList, menuList);
    }

    /**
     * 构建树形结构
     */
    private static List<MenuTreeRespVO> buildTree(List<MenuTreeRespVO> menuTreeList, List<MenuDO> originalMenuList) {
        // 存储ID到菜单的映射
        Map<Long, MenuTreeRespVO> menuMap = new HashMap<>();
        List<MenuTreeRespVO> rootMenus = new ArrayList<>();

        // 第一遍遍历：建立映射关系
        for (MenuTreeRespVO menu : menuTreeList) {
            menuMap.put(menu.getId(), menu);
        }

        // 第二遍遍历：建立父子关系
        for (MenuTreeRespVO menu : menuTreeList) {
            if (menu.getParentId() == null || menu.getParentId().equals(MenuDO.ID_ROOT)) {
                // 根节点
                rootMenus.add(menu);
            } else {
                // 子节点，找到父节点
                MenuTreeRespVO parent = menuMap.get(menu.getParentId());
                if (parent != null) {
                    if (parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(menu);
                }
            }
        }

        // 对每个节点的子节点按sort排序
        for (MenuTreeRespVO menu : menuTreeList) {
            if (menu.getChildren() != null) {
                menu.getChildren().sort(Comparator.comparing(MenuTreeRespVO::getSort));
            }
        }

        // 根节点按sort排序
        rootMenus.sort(Comparator.comparing(MenuTreeRespVO::getSort));

        return rootMenus;
    }

    /**
     * 构建树形结构（新方法，处理父节点不存在的情况）
     */
    private static List<MenuTreeRespVO> buildTreeByIds(List<MenuTreeRespVO> menuTreeList) {
        if (CollUtil.isEmpty(menuTreeList)) {
            return Collections.emptyList();
        }

        // 存储ID到菜单的映射
        Map<Long, MenuTreeRespVO> menuMap = new HashMap<>();
        List<MenuTreeRespVO> rootMenus = new ArrayList<>();

        // 第一遍遍历：建立映射关系
        for (MenuTreeRespVO menu : menuTreeList) {
            menuMap.put(menu.getId(), menu);
        }

        log.info("菜单映射表大小: {}", menuMap.size());

        // 第二遍遍历：建立父子关系
        for (MenuTreeRespVO menu : menuTreeList) {
            Long parentId = menu.getParentId();

            // 如果父节点为根节点或为空，直接作为根节点
            if (parentId == null || parentId.equals(MenuDO.ID_ROOT)) {
                rootMenus.add(menu);
                log.info("菜单 {} 作为根节点（父节点为根节点）", menu.getId());
                continue;
            }

            // 查找父节点
            MenuTreeRespVO parent = menuMap.get(parentId);
            if (parent != null) {
                // 父节点存在于当前列表中，正常添加为子节点
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(menu);
                log.info("菜单 {} 添加到父节点 {} 的子节点", menu.getId(), parentId);
            } else {
                // 父节点不存在于当前列表中，作为根节点显示
                rootMenus.add(menu);
                log.info("菜单 {} 作为根节点（父节点 {} 不存在于列表中）", menu.getId(), parentId);
            }
        }

        // 对每个节点的子节点按sort排序
        for (MenuTreeRespVO menu : menuTreeList) {
            if (menu.getChildren() != null) {
                menu.getChildren().sort(Comparator.comparing(MenuTreeRespVO::getSort));
            }
        }

        // 根节点按sort排序
        rootMenus.sort(Comparator.comparing(MenuTreeRespVO::getSort));

        log.info("最终根节点数量: {}", rootMenus.size());
        return rootMenus;
    }


    /**
     * 将MenuDO转换为MenuTreeRespVO
     */
    private static MenuTreeRespVO convertToTreeVO(MenuDO menu) {
        MenuTreeRespVO vo = new MenuTreeRespVO();
        vo.setId(menu.getId());
        vo.setName(menu.getName());
        vo.setParentId(menu.getParentId());
        vo.setType(menu.getType());
        vo.setPath(menu.getPath());
        vo.setIcon(menu.getIcon());
        vo.setPermission(menu.getPermission());
        vo.setSort(menu.getSort());
        vo.setComponent(menu.getComponent());
        vo.setComponentName(menu.getComponentName());
        vo.setStatus(menu.getStatus());
        vo.setVisible(menu.getVisible());
        vo.setKeepAlive(menu.getKeepAlive());
        vo.setAlwaysShow(menu.getAlwaysShow());
        return vo;
    }
}