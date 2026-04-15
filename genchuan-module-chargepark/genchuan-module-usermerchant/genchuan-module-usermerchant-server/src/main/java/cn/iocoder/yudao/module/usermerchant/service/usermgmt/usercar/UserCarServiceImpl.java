package cn.iocoder.yudao.module.usermerchant.service.usermgmt.usercar;

import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.userinfo.UserInfoDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.usermgmt.userinfo.UserInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.usercar.UserCarDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.usermgmt.usercar.UserCarMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 用户车辆 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class UserCarServiceImpl implements UserCarService {
    private static final String STATUS_BIND = "已绑定";
    private static final String STATUS_UNBIND = "已解绑";
    private static final String STATUS_REJECT = "已驳回";
    private static final String STATUS_REBIND = "待审核";

    @Resource
    private UserCarMapper userCarMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Boolean createUserCar(UserCarCreateReqVO createReqVO) {
        // 插入
        UserCarDO userCar = BeanUtils.toBean(createReqVO, UserCarDO.class);
        int rows = userCarMapper.insert(userCar);

        // 返回
        return rows > 0;
    }

    @Override
    public void updateUserCar(UserCarUpdateReqVO updateReqVO) {
        // 校验存在
        validateUserCarExists(updateReqVO.getId());
        // 更新
        UserCarDO updateObj = BeanUtils.toBean(updateReqVO, UserCarDO.class);
        userCarMapper.updateById(updateObj);
    }

    private void validateUserCarExists(Long id) {
        if (userCarMapper.selectById(id) == null) {
            throw exception(USER_CAR_NOT_EXISTS);
        }
    }

    @Override
    public UserCarDO getUserCar(Long id) {
        return userCarMapper.selectById(id);
    }

    @Override
    public PageResult<UserCarDO> getUserCarPage(UserCarPageReqVO pageReqVO) {
        return userCarMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean importUserCar(List<UserCarImportExcelVO> list, Boolean updateSupport) {
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        for (UserCarImportExcelVO vo : list) {
            if (vo.getId() != null) {
                UserCarDO existDO = userCarMapper.selectById(vo.getId());
                if (existDO != null) {
                    if (Boolean.TRUE.equals(updateSupport)) {
                        // 更新：复制属性，但保护创建信息
                        UserCarDO updateDO = BeanUtils.toBean(vo, UserCarDO.class);
                        updateDO.setCreator(null);
                        updateDO.setCreateTime(null);
                        userCarMapper.updateById(updateDO);
                    } else {
                        // updateSupport = false，跳过该条记录
                        continue;
                    }
                } else {
                    // ID 不存在，按新增处理（忽略用户提供的 ID，由数据库自增）
                    UserCarDO insertDO = BeanUtils.toBean(vo, UserCarDO.class);
                    insertDO.setId(null);
                    userCarMapper.insert(insertDO);
                }
            } else {
                // 无 ID，直接新增
                UserCarDO insertDO = BeanUtils.toBean(vo, UserCarDO.class);
                userCarMapper.insert(insertDO);
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void auditUserCar(List<Long> ids, String remark, String status) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 根据 ids 获取 userIds
        QueryWrapper<UserCarDO> carQueryWrapper = new QueryWrapper<>();
        carQueryWrapper.select("user_id").in("id", ids);
        List<UserCarDO> carList = userCarMapper.selectList(carQueryWrapper);
        List<Long> userIds = new ArrayList<>();
        for (UserCarDO car : carList) {
            Long userId = car.getUserId();
            if (userId != null && !userIds.contains(userId)) {
                userIds.add(userId);
            }
        }

        // 1. 处理已绑定或已驳回（审核操作）
        if (STATUS_BIND.equals(status) || STATUS_REJECT.equals(status)) {
            UpdateWrapper<UserCarDO> updateCarWrapper = new UpdateWrapper<>();
            updateCarWrapper.in("id", ids)
                    .set("status", status)
                    .set("auditor_id", getCurrentUserId())
                    .set("audit_time", LocalDateTime.now());
            if (remark != null) {
                updateCarWrapper.set("audit_remark", remark);
            }
            userCarMapper.update(null, updateCarWrapper);

            // 车辆数处理
            if (STATUS_BIND.equals(status) && !userIds.isEmpty()) {
                userInfoMapper.update(null, new UpdateWrapper<UserInfoDO>()
                        .in("id", userIds).setSql("car_count = car_count + 1"));
            } else if (STATUS_REJECT.equals(status)) {
                // 驳回不做车辆数变动
                System.out.println(LocalDateTime.now() + " INFO c.i.y.m.u.s.usermgmt.usercar.UserCarServiceImpl:170 审核状态为 已驳回 ，不影响用户车辆数");
            }
        }
        // 2. 处理解绑或重绑（非审核操作，不清审核人/时间/备注）
        else if (STATUS_UNBIND.equals(status) || STATUS_REBIND.equals(status)) {
            UpdateWrapper<UserCarDO> updateCarWrapper = new UpdateWrapper<>();
            updateCarWrapper.in("id", ids)
                    .set("status", status);
            // 可选：清空审核字段，避免残留
            updateCarWrapper.set("auditor_id", null)
                    .set("audit_time", null)
                    .set("audit_remark", null);
            userCarMapper.update(null, updateCarWrapper);

            // 车辆数处理
            if (STATUS_UNBIND.equals(status) && !userIds.isEmpty()) {
                userInfoMapper.update(null, new UpdateWrapper<UserInfoDO>()
                        .in("id", userIds).setSql("car_count = car_count - 1"));
            } else if (STATUS_REBIND.equals(status)) {
                // 重绑不改变车辆数（等审核通过后再增加）
                System.out.println(LocalDateTime.now() + " INFO c.i.y.m.u.s.usermgmt.usercar.UserCarServiceImpl:190 审核状态为 待审核 ，不影响用户车辆数");
            }
        } else {
            throw exception(ILLEGAL_STATUS);
        }
    }

    private Long getCurrentUserId() {
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        return loginUser != null ? loginUser.getId() : null;
    }

}