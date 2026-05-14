package cn.iocoder.yudao.module.usermerchant.service.usermgmt.usercar;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.userinfo.UserInfoDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.usermgmt.userinfo.UserInfoMapper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.NameQueryHelper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.TimeRangeParser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
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
    private AdminUserApi adminUserApi;

    @Resource
    private JdbcTemplate jdbcTemplate;

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
        // 1. 处理昵称筛选：如果前端传了 nickname，则转换为 userId 并设置到查询条件
        if (StrUtil.isNotBlank(pageReqVO.getNickname())) {
            Long userId = userInfoMapper.getIdByNickname(pageReqVO.getNickname());
            if (userId == null) {
                return new PageResult<>(Collections.emptyList(), 0L);
            }
            pageReqVO.setUserId(userId);
        }
        // 2. 分页查询车辆数据
        PageResult<UserCarDO> pageResult = userCarMapper.selectPage(pageReqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return pageResult;
        }

        // 批量填充所属用户昵称
        NameQueryHelper.fillNamesByIds(
                pageResult.getList(),
                UserCarDO::getUserId,
                UserCarDO::setNickname,
                "user_info", "id", "nickname"
        );

        // 批量填充审核人昵称（通过 Feign 调用 system-server）
        NameQueryHelper.fillUserNames(pageResult.getList(),
                UserCarDO::getAuditorId,
                UserCarDO::setAuditorName,
                adminUserApi);

        return pageResult;
    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Boolean importUserCar(List<UserCarImportExcelVO> list, Boolean updateSupport) {
//        if (CollectionUtils.isEmpty(list)) {
//            return true;
//        }
//        for (UserCarImportExcelVO vo : list) {
//            if (vo.getId() != null) {
//                UserCarDO existDO = userCarMapper.selectById(vo.getId());
//                if (existDO != null) {
//                    if (Boolean.TRUE.equals(updateSupport)) {
//                        // 更新：复制属性，但保护创建信息
//                        UserCarDO updateDO = BeanUtils.toBean(vo, UserCarDO.class);
//                        updateDO.setCreator(null);
//                        updateDO.setCreateTime(null);
//                        userCarMapper.updateById(updateDO);
//                    } else {
//                        // updateSupport = false，跳过该条记录
//                        continue;
//                    }
//                } else {
//                    // ID 不存在，按新增处理（忽略用户提供的 ID，由数据库自增）
//                    UserCarDO insertDO = BeanUtils.toBean(vo, UserCarDO.class);
//                    insertDO.setId(null);
//                    userCarMapper.insert(insertDO);
//                }
//            } else {
//                // 无 ID，直接新增
//                UserCarDO insertDO = BeanUtils.toBean(vo, UserCarDO.class);
//                userCarMapper.insert(insertDO);
//            }
//        }
//        return true;
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean importUserCar(List<UserCarImportExcelVO> list, Boolean updateSupport) {
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        for (UserCarImportExcelVO vo : list) {
            // 自动填充绑定时间（如果为空）
            if (vo.getBindTime() == null) {
                vo.setBindTime(LocalDateTime.now());
            }
            // 1. 确定最终 userId
            Long finalUserId = resolveUserId(vo); // 内部处理 userId 或 userName 查询
            vo.setUserId(finalUserId);

            // 2. 新增
            if (vo.getId() == null) {
                UserCarDO insertDO = BeanUtils.toBean(vo, UserCarDO.class);
                insertDO.setStatus(STATUS_REBIND);
                insertDO.setId(null);
                userCarMapper.insert(insertDO);
                // 新增时状态不是已绑定，不调整 car_count
                continue;
            }

            // 3. 更新
            if (!Boolean.TRUE.equals(updateSupport)) {
                continue; // 不支持更新，跳过
            }
            UserCarDO oldDO = userCarMapper.selectById(vo.getId());
            if (oldDO == null) {
                // ID 不存在，按新增处理
                UserCarDO insertDO = BeanUtils.toBean(vo, UserCarDO.class);
                insertDO.setStatus(STATUS_REBIND);
                insertDO.setId(null);
                userCarMapper.insert(insertDO);
                continue;
            }

            // 如果旧状态是“已绑定”，先解绑
            if (STATUS_BIND.equals(oldDO.getStatus())) {
                auditUserCar(Collections.singletonList(vo.getId()), null, STATUS_UNBIND);
            }

            // 执行更新（状态强制待审核，其他字段按 VO 设置）
            UserCarDO updateDO = BeanUtils.toBean(vo, UserCarDO.class);
            updateDO.setId(vo.getId());
            updateDO.setStatus(STATUS_REBIND);
            // 保护创建信息
            updateDO.setCreator(null);
            updateDO.setCreateTime(null);
            userCarMapper.updateById(updateDO);
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

    @Override
    public UserCarChartRespVO getUserCarChart(UserCarChartReqVO chartReqVO) {
        UserCarChartRespVO chartRespVO = new UserCarChartRespVO();
        String timeRange = chartReqVO.getTimeRange();

        TimeRangeParser.TimeRangeParsed parsed;
        if (StrUtil.isBlank(timeRange)) {
            // 未传时间范围：全量查询，start 和 end 为 null，粒度默认 day
            parsed = new TimeRangeParser.TimeRangeParsed(null, null, "day");
        } else {
            parsed = TimeRangeParser.parse(timeRange);
            if (parsed == null) {
                // 解析失败，返回空数据
                return chartRespVO;
            }
        }
        // 柱状图数据
        List<UserCarChartRespVO.CarTypeDistributionVO> typeDistribution = userCarMapper.selectCarTypeDistribution(
                parsed.getStart(), parsed.getEnd(), parsed.getGranularity());
        chartRespVO.setCarTypeDistribution(typeDistribution);
        // 总数统计
        chartRespVO.setBindCarCount(userCarMapper.selectBindCarCount(parsed.getStart(), parsed.getEnd()));
        // 计算比率
        chartRespVO.setAuditPassRate(userCarMapper.selectAuditPassRate(parsed.getStart(), parsed.getEnd()));
        return chartRespVO;
    }

    private Long getCurrentUserId() {
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        return loginUser != null ? loginUser.getId() : null;
    }

    // 辅助方法：解析 userId
    private Long resolveUserId(UserCarImportExcelVO vo) {
        if (vo.getUserId() != null) {
            UserInfoDO user = userInfoMapper.selectById(vo.getUserId());
            if (user == null) throw new ServiceException(USER_INFO_NOT_EXISTS);
            return vo.getUserId();
        }
        if (StringUtils.hasText(vo.getUserName())) {
            Long userId = userInfoMapper.getIdByNickname(vo.getUserName());
            if (userId == null) throw new ServiceException(USER_INFO_NOT_EXISTS);
            return userId;
        }
        throw new ServiceException(USER_INFO_NOT_EXISTS);
    }

//    // 新增辅助方法：解析审核人ID
//    private Long resolveAuditorId(UserCarImportExcelVO vo) {
//        if (vo.getAuditorId() != null) {
//            // 校验审核人ID是否存在
//            CommonResult<AdminUserRespDTO> userResult = adminUserApi.getUser(vo.getAuditorId());
//            if (userResult.isSuccess() && userResult.getData() != null) {
//                return vo.getAuditorId();
//            } else {
//                throw new ServiceException(USER_INFO_NOT_EXISTS);
//            }
//        }
//        if (StringUtils.hasText(vo.getAuditorName())) {
//            // 通过姓名查询ID
//            Long id = NameQueryHelper.getIdByName("system_users", "nickname", vo.getAuditorName(), "id");
//            if (id == null) {
//                throw new ServiceException(USER_INFO_NOT_EXISTS);
//            }
//            return id;
//        }
//        // 两者都为空，返回null（允许未指定审核人）
//        return null;
//    }

}