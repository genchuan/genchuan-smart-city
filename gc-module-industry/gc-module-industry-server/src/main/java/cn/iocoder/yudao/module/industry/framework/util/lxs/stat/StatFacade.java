package cn.iocoder.yudao.module.industry.framework.util.lxs.stat;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 统计统一入口（给 Controller 用）
 */
public final class StatFacade {

    private StatFacade() {}

    public static <REQ, T> Map<String, Object> statPage(
            Function<REQ, PageResult<T>> pageFunc,
            REQ reqVO) {

        //1.获取统计的原数据列表
        PageResult<T> pageResult = pageFunc.apply(reqVO);

        if (pageResult == null || pageResult.getList() == null) {
            return Map.of();
        }

        //2.返回统计之后的数据
        return StatUtils.statList(pageResult.getList());
    }
}
