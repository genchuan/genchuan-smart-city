package cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.user;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.user.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/18 15:07
 */
@Component
public class UserCodeGenerator {

    @Resource
    private UserMapper userMapper;

    /**
     * 生成user_id（格式：uuid-use- + 3位序号，如 uuid-use-001）
     */
    public String generateUserId() {
        // 1. 查询全局最大序号（也可按日期维度，根据需求调整）
        Integer maxSeq = userMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接sys_vehicle_id
        return String.format("uuid-user-%03d", newSeq);
    }
}