package cn.iocoder.yudao.module.data.service.matterinstance;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.dal.dataobject.mattercategory.matterCategoryDO;
import cn.iocoder.yudao.module.data.dal.mysql.mattercategory.matterCategoryMapper;
import cn.iocoder.yudao.module.data.service.mattercategory.matterCategoryService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo.*;
import cn.iocoder.yudao.module.data.dal.dataobject.matterinstance.matterInstanceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.data.dal.mysql.matterinstance.matterInstanceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.data.enums.ErrorCodeConstants.*;

/**
 * 管理事项实例 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class matterInstanceServiceImpl implements matterInstanceService {

    @Resource
    private matterInstanceMapper matterInstanceMapper;

    @Resource
    private matterCategoryService matterCategoryService1;

    @Resource
    private matterCategoryMapper matterCategoryMapper;

    @Override
    public Long creatematterInstance(matterInstanceSaveReqVO createReqVO) {
        // 插入
        matterInstanceDO matterInstance = BeanUtils.toBean(createReqVO, matterInstanceDO.class);
        matterInstanceMapper.insert(matterInstance);
        // 返回
        return matterInstance.getId();
    }

    @Override
    public void updatematterInstance(matterInstanceSaveReqVO updateReqVO) {
        // 校验存在
        validatematterInstanceExists(updateReqVO.getId());
        // 更新
        matterInstanceDO updateObj = BeanUtils.toBean(updateReqVO, matterInstanceDO.class);
        matterInstanceMapper.updateById(updateObj);
    }

    @Override
    public void deletematterInstance(Long id) {
        // 校验存在
        validatematterInstanceExists(id);
        // 删除
        matterInstanceMapper.deleteById(id);
    }

    private void validatematterInstanceExists(Long id) {
        if (matterInstanceMapper.selectById(id) == null) {
            throw exception(MATTER_INSTANCE_NOT_EXISTS);
        }
    }

    @Override
    public matterInstanceDO getmatterInstance(Long id) {
        return matterInstanceMapper.selectById(id);
    }

    @Override
    public PageResult<matterInstanceDO> getmatterInstancePage(matterInstancePageReqVO pageReqVO) {
        // 处理树形查询参数
        String treeParentCategoryId = pageReqVO.getTreeParentCategoryId();
        if (treeParentCategoryId != null && !treeParentCategoryId.trim().isEmpty()) {
            // 获取该分类节点及其所有子分类的ID列表
            List<String> subCategoryIds = getSubCategoryIdsForTree(treeParentCategoryId, pageReqVO.getIncludeSelf());

            if (subCategoryIds.isEmpty()) {
                // 如果没有找到任何关联的分类，则返回空结果
                return new PageResult<>(Collections.emptyList(), 0L);
            }
            // 调用 Mapper 中新增的方法，根据分类ID列表进行分页查询
            return matterInstanceMapper.selectPageByCategoryIds(pageReqVO, subCategoryIds);
        }
        // 普通分页查询
        return matterInstanceMapper.selectPage(pageReqVO);
    }

    @Override
    public List<String> getSubCategoryIdsForTree(String parentCategoryId, boolean includeSelf) {
        // 1. 获取子分类的自增主键id列表
        List<Long> subCategoryIdList = matterCategoryService1.getSubMatterCategoryIds(parentCategoryId, includeSelf);

        if (subCategoryIdList.isEmpty()) {
            return Collections.emptyList();
        }

        // 2. 由于matterCategoryService1.getSubMatterCategoryIds返回的是自增主键id(Long)
        // 但实例表中的category_id存储的是业务ID(String)，如'CAT001'
        // 所以我们需要从分类表中查询对应的业务ID

        // 3. 使用LambdaQueryWrapperX查询分类表，获取这些id对应的业务ID
        List<cn.iocoder.yudao.module.data.dal.dataobject.mattercategory.matterCategoryDO> categories =
                matterCategoryMapper.selectList(new LambdaQueryWrapperX<matterCategoryDO>()
                        .in(cn.iocoder.yudao.module.data.dal.dataobject.mattercategory.matterCategoryDO::getId, subCategoryIdList));

        // 4. 提取业务ID列表
        return categories.stream()
                .map(cn.iocoder.yudao.module.data.dal.dataobject.mattercategory.matterCategoryDO::getMatterCategoryId)
                .collect(Collectors.toList());
    }

}