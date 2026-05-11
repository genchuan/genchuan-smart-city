package cn.iocoder.yudao.module.usermerchant.service.groupclient.groupcar;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupinfo.vo.GroupInfoImportExcelVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.groupclient.groupinfo.GroupInfoDO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantrecharge.MerchantRechargeDO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.usercar.UserCarDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.groupclient.groupinfo.GroupInfoMapper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.NameQueryHelper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupcar.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.groupclient.groupcar.GroupCarDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.groupclient.groupcar.GroupCarMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 集团车辆 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class GroupCarServiceImpl implements GroupCarService {

    @Resource
    private GroupInfoMapper groupInfoMapper;

    @Resource
    private GroupCarMapper groupCarMapper;

    @Override
    public Boolean createGroupCar(GroupCarSaveReqVO createReqVO) {
        // 插入
        GroupCarDO groupCar = BeanUtils.toBean(createReqVO, GroupCarDO.class);
        int rows = groupCarMapper.insert(groupCar);

        // 返回
        return rows > 0;
    }

    @Override
    public Boolean updateGroupCar(GroupCarUpdateReqVO updateReqVO) {
        // 校验存在
        validateGroupCarExists(updateReqVO.getId());
        // 更新
        GroupCarDO updateObj = BeanUtils.toBean(updateReqVO, GroupCarDO.class);
        int rows = groupCarMapper.updateById(updateObj);
        return rows > 0;
    }

    @Override
    public void deleteGroupCar(Long id) {
        // 校验存在
        validateGroupCarExists(id);
        // 删除
        groupCarMapper.deleteById(id);
    }

    @Override
        public void deleteGroupCarListByIds(List<Long> ids) {
        // 删除
        groupCarMapper.deleteByIds(ids);
        }


    private void validateGroupCarExists(Long id) {
        if (groupCarMapper.selectById(id) == null) {
            throw exception(GROUP_CAR_NOT_EXISTS);
        }
    }

    @Override
    public GroupCarDO getGroupCar(Long id) {
        return groupCarMapper.selectById(id);
    }

    @Override
    public PageResult<GroupCarDO> getGroupCarPage(GroupCarPageReqVO pageReqVO) {
        if (StrUtil.isNotBlank(pageReqVO.getGroupName())) {
            Long groupId = groupInfoMapper.getIdByNickname(pageReqVO.getGroupName());
            if (groupId == null) {
                return new PageResult<>(Collections.emptyList(), 0L);
            }
            pageReqVO.setGroupId(groupId);
        }
        PageResult<GroupCarDO> pageResult = groupCarMapper.selectPage(pageReqVO);
        NameQueryHelper.fillNamesByIds(
                pageResult.getList(),
                GroupCarDO::getGroupId,
                GroupCarDO::setGroupName,
                "group_info", "id", "name"
        );
        return pageResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean importGroups(List<GroupCarImportExcelVO> list, Boolean updateSupport) {
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        for (GroupCarImportExcelVO vo : list) {
            if (vo.getId() != null) {
                GroupCarDO existDO = groupCarMapper.selectById(vo.getId());
                if (existDO != null) {
                    if (Boolean.TRUE.equals(updateSupport)) {
                        // 更新：复制属性，但保护创建信息
                        GroupCarDO updateDO = BeanUtils.toBean(vo, GroupCarDO.class);
                        updateDO.setCreator(null);
                        updateDO.setCreateTime(null);
                        groupCarMapper.updateById(updateDO);
                    } else {
                        // updateSupport = false，跳过该条记录
                        continue;
                    }
                } else {
                    // ID 不存在，按新增处理（忽略用户提供的 ID，由数据库自增）
                    GroupCarDO insertDO = BeanUtils.toBean(vo, GroupCarDO.class);
                    insertDO.setId(null);
                    groupCarMapper.insert(insertDO);
                }
            } else {
                // 无 ID，直接新增
                GroupCarDO insertDO = BeanUtils.toBean(vo, GroupCarDO.class);
                groupCarMapper.insert(insertDO);
            }
        }
        return true;
    }

    @Override
    public void auditGroupCar(GroupCarAuditReqVO reqVO) {
        if (CollectionUtils.isEmpty(reqVO.getIds())) {
            return;
        }
        UpdateWrapper<GroupCarDO> updateCarWrapper = new UpdateWrapper<>();
        updateCarWrapper.in("id", reqVO.getIds());
        if ("待审核".equals(reqVO.getStatus())) {
            updateCarWrapper.set("status", reqVO.getStatus());
        } else {
            if("已解绑".equals(reqVO.getStatus())) {
                updateCarWrapper.set("status", reqVO.getStatus())
                    .set("auditor_id", null)
                    .set("audit_time", null)
                    .set("audit_remark", null);
            } else {
                updateCarWrapper.set("auditor_id", getCurrentUserId())
                            .set("audit_time", LocalDateTime.now())
                            .set("audit_remark", reqVO.getAuditRemark());
                if ("已绑定".equals(reqVO.getStatus())) {
                    updateCarWrapper.set("status", reqVO.getStatus());
                }
            }
        }
        groupCarMapper.update(null, updateCarWrapper);
    }

    private Long getCurrentUserId() {
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        return loginUser != null ? loginUser.getId() : null;
    }

}