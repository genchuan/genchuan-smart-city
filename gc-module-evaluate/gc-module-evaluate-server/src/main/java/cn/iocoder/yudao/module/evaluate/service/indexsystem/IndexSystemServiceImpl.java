package cn.iocoder.yudao.module.evaluate.service.indexsystem;

import cn.hutool.core.convert.Convert;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexsystem.IndexSystemMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.INDEX_SYSTEM_NOT_EXISTS;

/**
 * 指标体系 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class IndexSystemServiceImpl implements IndexSystemService {

    @Resource
    private IndexSystemMapper indexSystemMapper;

    @Override
    public Long createIndexSystem(IndexSystemSaveReqVO createReqVO) {
        // 插入
        IndexSystemDO indexSystem = BeanUtils.toBean(createReqVO, IndexSystemDO.class);
        indexSystemMapper.insert(indexSystem);
        // 返回
        return indexSystem.getId();
    }

    @Override
    public void updateIndexSystem(IndexSystemSaveReqVO updateReqVO) {
        // 校验存在
        validateIndexSystemExists(updateReqVO.getId());
        // 更新
        IndexSystemDO updateObj = BeanUtils.toBean(updateReqVO, IndexSystemDO.class);
        indexSystemMapper.updateById(updateObj);
    }

    @Override
    public void deleteIndexSystem(Long id) {
        // 校验存在
        validateIndexSystemExists(id);
        // 删除
        indexSystemMapper.deleteById(id);
    }

    private void validateIndexSystemExists(Long id) {
        if (indexSystemMapper.selectById(id) == null) {
            throw exception(INDEX_SYSTEM_NOT_EXISTS);
        }
    }

    @Override
    public IndexSystemDO getIndexSystem(Long id) {
        return indexSystemMapper.selectById(id);
    }

    @Override
    public PageResult<IndexSystemDO> getIndexSystemPage(IndexSystemPageReqVO pageReqVO) {
        return indexSystemMapper.selectPage(pageReqVO);
    }
//---------------------------------新增---------------------------------------------
    @Override
    public PageResult<IndexSystemPageItemVO> getIndexSystemPageWithJoin(IndexSystemPageReqVO pageReqVO) {
        return indexSystemMapper.selectPageWithJoin(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IndexSystemDetailVO getIndexSystemDetail(String systemId) {
        // 1. 查询指标体系基本信息
        IndexSystemDetailVO.BaseInfo baseInfo = indexSystemMapper.selectDetailBaseInfo(systemId);
        if (baseInfo == null) {
            throw exception(INDEX_SYSTEM_NOT_EXISTS);
        }

        // 2. 查询分类列表
        List<IndexSystemDetailVO.CategoryVO> categories = indexSystemMapper.selectCategoriesBySystemId(systemId);

        // 3. 提取分类ID列表
        List<String> categoryIds = categories.stream()
                .map(IndexSystemDetailVO.CategoryVO::getCategoryId)
                .collect(Collectors.toList());

        // 4. 批量查询指标项
        if (!categoryIds.isEmpty()) {
            List<IndexSystemDetailVO.IndexItemVO> allItems = indexSystemMapper.selectItemsByCategoryIds(categoryIds);

            // 5. 按分类ID分组
            Map<String, List<IndexSystemDetailVO.IndexItemVO>> itemsByCategory = allItems.stream()
                    .collect(Collectors.groupingBy(IndexSystemDetailVO.IndexItemVO::getCategoryId));

            // 6. 将指标项设置到对应的分类中
            categories.forEach(category ->
                    category.setItems(itemsByCategory.get(category.getCategoryId()))
            );
        }

        // 7. 组装返回结果
        IndexSystemDetailVO detailVO = new IndexSystemDetailVO();
        detailVO.setBaseInfo(baseInfo);
        detailVO.setCategories(categories);

        return detailVO;
    }

    @Override
    public WeightCheckRespVO checkWeight(WeightCheckReqVO reqVO) {
        WeightCheckRespVO respVO = new WeightCheckRespVO();

        if (reqVO.getCategoryId() != null) {
            // 校验指标项权重（某个分类下的所有指标项权重总和）
            Double totalWeight = indexSystemMapper.selectItemWeightSum(reqVO.getCategoryId());
            respVO.setCheckType("ITEM_WEIGHT");
            respVO.setTotalWeight(totalWeight != null ? totalWeight : 0.0);
            respVO.setPassed(Math.abs((totalWeight != null ? totalWeight : 0.0) - 100.0) < 0.01);

            if (!respVO.getPassed()) {
                respVO.setErrorMessage(String.format("指标项权重总和应为100%，当前为%.2f%%", totalWeight));
            }
        } else {
            // 校验分类权重（某个体系下的所有分类权重总和）
            Double totalWeight = indexSystemMapper.selectCategoryWeightSum(reqVO.getSystemId());
            respVO.setCheckType("CATEGORY_WEIGHT");
            respVO.setTotalWeight(totalWeight != null ? totalWeight : 0.0);
            respVO.setPassed(Math.abs((totalWeight != null ? totalWeight : 0.0) - 100.0) < 0.01);

            if (!respVO.getPassed()) {
                respVO.setErrorMessage(String.format("分类权重总和应为100%，当前为%.2f%%", totalWeight));
            }
        }

        return respVO;
    }
    //----------------------xin-------------------
    @Override
    public PageResult<IndexSystemRespVO> getIndexSystemJoinPage(IndexSystemPageReqVO reqVO) {
        return indexSystemMapper.selectSystemJoinPage(reqVO);
    }

    @Override
    public IndexSystemRespVO getStatusCount(Integer statusId) {
        IndexSystemRespVO respVO = new IndexSystemRespVO();

        // 1. 改用普通QueryWrapper，支持字符串SQL片段
        QueryWrapper<IndexSystemDO> wrapper = new QueryWrapper<IndexSystemDO>()
                .ne("deleted", 1); // 对应原过滤条件，使用数据库下划线字段名

        // 动态拼接statusId条件
        if (statusId != null) {
            wrapper.eq("status_id", statusId);
        }

        // 2. 普通QueryWrapper原生支持直接传入SQL片段
        wrapper.select(
                "COUNT(*) AS totalCount",
                "SUM(CASE WHEN status_id = 1 THEN 1 ELSE 0 END) AS status1Count",
                "SUM(CASE WHEN status_id = 2 THEN 1 ELSE 0 END) AS status2Count"
        );

        // 3. 仅执行1次数据库查询，拿到聚合结果
        Map<String, Object> result = indexSystemMapper.selectMaps(wrapper).get(0);

        // 4. 结果转换+空值保护（Convert是芋道框架内置工具类，自动处理null）
        respVO.setTotalCount(Convert.toLong(result.get("totalCount"), 0L));
        respVO.setStatus1Count(Convert.toLong(result.get("status1Count"), 0L));
        respVO.setStatus2Count(Convert.toLong(result.get("status2Count"), 0L));

        return respVO;
    }
}