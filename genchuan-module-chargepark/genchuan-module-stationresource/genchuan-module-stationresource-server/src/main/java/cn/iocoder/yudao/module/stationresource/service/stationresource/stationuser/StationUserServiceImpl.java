package cn.iocoder.yudao.module.stationresource.service.stationresource.stationuser;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationuser.vo.StationUserPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationuser.vo.StationUserSaveReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationuser.StationUserDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.stationuser.StationUserMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.stationresource.enums.ErrorCodeConstants.*;

/**
 * 站点用户 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class StationUserServiceImpl implements StationUserService {

    @Resource
    private StationUserMapper stationUserMapper;

    @Override
    public Long createStationUser(StationUserSaveReqVO createReqVO) {
        // 插入
        StationUserDO stationUser = BeanUtils.toBean(createReqVO, StationUserDO.class);
        stationUserMapper.insert(stationUser);

        // 返回
        return stationUser.getId();
    }

    @Override
    public void updateStationUser(StationUserSaveReqVO updateReqVO) {
        // 校验存在
        validateStationUserExists(updateReqVO.getId());
        // 更新
        StationUserDO updateObj = BeanUtils.toBean(updateReqVO, StationUserDO.class);
        stationUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteStationUser(Long id) {
        // 校验存在
        validateStationUserExists(id);
        // 删除
        stationUserMapper.deleteById(id);
    }

    @Override
        public void deleteStationUserListByIds(List<Long> ids) {
        // 删除
        stationUserMapper.deleteByIds(ids);
        }


    private void validateStationUserExists(Long id) {
        if (stationUserMapper.selectById(id) == null) {
            throw exception(STATION_USER_NOT_EXISTS);
        }
    }

    @Override
    public StationUserDO getStationUser(Long id) {
        return stationUserMapper.selectById(id);
    }

    @Override
    public PageResult<StationUserDO> getStationUserPage(StationUserPageReqVO pageReqVO) {
        return stationUserMapper.selectPage(pageReqVO);
    }

}
