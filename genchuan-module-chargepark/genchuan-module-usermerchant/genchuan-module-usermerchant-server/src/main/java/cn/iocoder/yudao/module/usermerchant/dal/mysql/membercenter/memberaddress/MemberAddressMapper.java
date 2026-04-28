package cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberaddress;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberaddress.MemberAddressDO;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@DS("member")
public interface MemberAddressMapper extends BaseMapperX<MemberAddressDO> {

    default MemberAddressDO selectByIdAndUserId(Long id, Long userId) {
        return selectOne(MemberAddressDO::getId, id, MemberAddressDO::getUserId, userId);
    }

    default List<MemberAddressDO> selectListByUserIdAndDefaulted(Long userId, Boolean defaulted) {
        return selectList(new LambdaQueryWrapperX<MemberAddressDO>().eq(MemberAddressDO::getUserId, userId)
                .eqIfPresent(MemberAddressDO::getDefaultStatus, defaulted));
    }

}
