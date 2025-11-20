package cn.iocoder.yudao.module.industry.dal.mysql.common;

import org.apache.ibatis.annotations.Mapper;
/**
 * 公共 SQL 占位接口（AreaSqlMapper）
 * 功能说明：
 * 1. 仅用于绑定 AreaSql.xml 中的公共 SQL 片段（likeByRegionShortCode 等）。
 * 2. 不允许在此接口中添加任何方法或实现逻辑。
 * 3. 其他 Mapper 可以通过 <include refid="cn.iocoder.yudao.module.industry.dal.mysql.common.AreaSqlMapper.XXX"/> 引用其中的 SQL。
 * 4. 如果修改公共 SQL，请在 XML 中修改，不要改此接口。
 */
@Mapper
public interface AreaSqlMapper {
}
