package cn.iocoder.yudao.module.data.service.mattercategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.data.controller.admin.mattercategory.vo.MatterCategoryPageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.mattercategory.vo.MatterCategorySaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.mattercategory.vo.MatterCategorySimpleTreeRespVO;
import cn.iocoder.yudao.module.data.dal.dataobject.mattercategory.MatterCategoryDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 管理事项分类 Service 接口
 *
 * @author zhucongquan
 */
public interface MatterCategoryService {

    /**
     * 创建管理事项分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long creatematterCategory(@Valid MatterCategorySaveReqVO createReqVO);

    /**
     * 更新管理事项分类
     *
     * @param updateReqVO 更新信息
     */
    void updatematterCategory(@Valid MatterCategorySaveReqVO updateReqVO);

    /**
     * 删除管理事项分类
     *
     * @param id 编号
     */
    void deletematterCategory(Long id);

    /**
     * 批量删除管理事项分类
     *
     * @param ids 编号列表
     */
    void deletematterCategories(List<Long> ids);

    /**
     * 获得管理事项分类
     *
     * @param id 编号
     * @return 管理事项分类
     */
    MatterCategoryDO getmatterCategory(Long id);

    /**
     * 获得管理事项分类分页
     *
     * @param pageReqVO 分页查询
     * @return 管理事项分类分页
     */
    PageResult<MatterCategoryDO> getmatterCategoryPage(MatterCategoryPageReqVO pageReqVO);

    /**
     * 获得管理事项分类简化树（仅包含id、label、children）
     *
     * @return 管理事项分类简化树列表
     */
    List<MatterCategorySimpleTreeRespVO> getmatterCategorySimpleTree();

    /**
     * 获取指定父节点下的所有子节点ID（包括自身）
     *
     * @param parentId 父节点ID
     * @param includeSelf 是否包含父节点自身
     * @return 子节点ID列表
     */
    List<Long> getSubMatterCategoryIds(String parentId, boolean includeSelf);

}