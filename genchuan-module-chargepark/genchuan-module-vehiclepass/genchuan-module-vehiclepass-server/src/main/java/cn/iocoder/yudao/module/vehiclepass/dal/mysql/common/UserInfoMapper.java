package cn.iocoder.yudao.module.vehiclepass.dal.mysql.common;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.UserSimpleRespVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    @Select("SELECT id AS userId, nickname FROM user_info WHERE deleted = 0 ORDER BY id ASC")
    List<UserSimpleRespVO> selectUserSimpleList();
}
