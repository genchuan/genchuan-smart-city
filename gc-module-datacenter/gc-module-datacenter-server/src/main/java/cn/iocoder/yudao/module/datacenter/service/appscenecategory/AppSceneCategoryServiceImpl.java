package cn.iocoder.yudao.module.datacenter.service.appscenecategory;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.appscenecategory.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.appscenecategory.AppSceneCategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.appscenecategory.AppSceneCategoryMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 应用场景分类配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class AppSceneCategoryServiceImpl implements AppSceneCategoryService {

    @Resource
    private AppSceneCategoryMapper appSceneCategoryMapper;

    @Override
    public Long createAppSceneCategory(AppSceneCategorySaveReqVO createReqVO) {
        // 插入
        AppSceneCategoryDO appSceneCategory = BeanUtils.toBean(createReqVO, AppSceneCategoryDO.class);
        appSceneCategoryMapper.insert(appSceneCategory);
        // 返回
        return appSceneCategory.getId();
    }

    @Override
    public void updateAppSceneCategory(AppSceneCategorySaveReqVO updateReqVO) {
        // 校验存在
        validateAppSceneCategoryExists(updateReqVO.getId());
        // 更新
        AppSceneCategoryDO updateObj = BeanUtils.toBean(updateReqVO, AppSceneCategoryDO.class);
        appSceneCategoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteAppSceneCategory(Long id) {
        // 校验存在
        validateAppSceneCategoryExists(id);
        // 删除
        appSceneCategoryMapper.deleteById(id);
    }

    private void validateAppSceneCategoryExists(Long id) {
        if (appSceneCategoryMapper.selectById(id) == null) {
            throw exception(APP_SCENE_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public AppSceneCategoryDO getAppSceneCategory(Long id) {
        return appSceneCategoryMapper.selectById(id);
    }

    @Override
    public PageResult<AppSceneCategoryDO> getAppSceneCategoryPage(AppSceneCategoryPageReqVO pageReqVO) {
        return appSceneCategoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AppSceneCategoryDO> getAppSceneCategoryTree() {
        // 查询所有分类数据
        List<AppSceneCategoryDO> allCategories = appSceneCategoryMapper.selectList();

        // 构建树形结构
        return buildTree(allCategories);
    }

    /**
     * 构建树形结构
     *
     * @param allCategories 所有分类数据
     * @return 树形结构列表
     */
    private List<AppSceneCategoryDO> buildTree(List<AppSceneCategoryDO> allCategories) {
        // 用于存储最终结果的树形列表
        List<AppSceneCategoryDO> treeList = new ArrayList<>();

        // 使用Map来快速查找节点，key为分类ID，value为分类对象
        Map<String, AppSceneCategoryDO> nodeMap = new HashMap<>();

        // 第一次遍历：将所有节点放入Map中，并初始化children列表
        for (AppSceneCategoryDO category : allCategories) {
            // 使用String.valueOf确保key的一致性
            nodeMap.put(String.valueOf(category.getId()), category);
            // 初始化children列表（如果还没有的话）
            // 这里需要为DO添加children字段，详见下面的DO修改
        }

        // 第二次遍历：建立父子关系
        for (AppSceneCategoryDO category : allCategories) {
            String parentId = category.getParentCatId();

            // 如果parentId为空或为0，说明是根节点
            if (parentId == null || "0".equals(parentId) || parentId.isEmpty()) {
                treeList.add(category);
            } else {
                // 查找父节点
                AppSceneCategoryDO parent = nodeMap.get(parentId);
                if (parent != null) {
                    // 将当前节点添加到父节点的children中
                    if (parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(category);
                }
            }
        }

        return treeList;
    }

}