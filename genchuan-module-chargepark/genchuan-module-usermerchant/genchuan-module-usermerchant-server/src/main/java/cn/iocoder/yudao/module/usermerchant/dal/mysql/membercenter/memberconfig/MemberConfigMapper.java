package cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberconfig;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberconfig.MemberConfigDO;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;

/**
 * 积分设置 Mapper
 *
 * @author QingX
 */
@Mapper
@DS("member")
public interface MemberConfigMapper extends BaseMapperX<MemberConfigDO> {
}
