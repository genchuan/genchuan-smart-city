package cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.codeutils;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * CodeQueryUtils 配置类
 *
 * 版本V1
 * 作用：
 * 1. 将 Spring 注入的 JdbcTemplate 设置到 CodeQueryUtils 中
 * 2. 让 CodeQueryUtils 可以直接使用 JdbcTemplate 执行 SQL 查询
 */
@Configuration // 声明这是一个 Spring 配置类，Spring 会扫描并管理它
public class CodeQueryConfig {

    @Resource // 自动注入 Spring 容器中的 JdbcTemplate Bean
    private JdbcTemplate jdbcTemplate;

    @PostConstruct // 在 Bean 初始化完成后执行的方法
    public void init() {
        // 将注入的 JdbcTemplate 设置到 CodeQueryUtils 静态变量中
        // 使 CodeQueryUtils 可以在任何地方调用 queryByCode 等方法
        CodeQueryUtils.setJdbcTemplate(jdbcTemplate);
    }
}
