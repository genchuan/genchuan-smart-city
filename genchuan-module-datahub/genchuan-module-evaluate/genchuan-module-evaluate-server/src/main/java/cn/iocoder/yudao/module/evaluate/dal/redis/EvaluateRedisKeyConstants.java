package cn.iocoder.yudao.module.evaluate.dal.redis;

/**
 * Evaluate 模块 Redis Key 常量类
 *
 * @author 亘川智城
 */
public interface EvaluateRedisKeyConstants {

    /**
     * 体系下分类列表的缓存
     * <p>
     * KEY 格式：eval_category_list:{systemUuid}
     * VALUE 数据类型：List<IndexCategoryDO> 分类列表
     * 缓存时间：24 小时（分类变更频率低）
     */
    String CATEGORY_LIST = "eval_category_list:%s";

    /**
     * 分类下指标项列表的缓存
     * <p>
     * KEY 格式：eval_item_list:{systemUuid}
     * VALUE 数据类型：List<IndexItemDO> 指标项列表
     * 缓存时间：24 小时
     */
    String ITEM_LIST = "eval_item_list:%s";

}
