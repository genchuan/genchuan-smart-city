package cn.iocoder.yudao.module.usermerchant.service.membercenter.membertag;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membertag.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membertag.MemberTagDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.membertag.MemberTagMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 会员标签 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MemberTagServiceImpl implements MemberTagService {

    @Resource
    private MemberTagMapper memberTagMapper;

    @Override
    public Long createMemberTag(MemberTagSaveReqVO createReqVO) {
        // 插入
        MemberTagDO memberTag = BeanUtils.toBean(createReqVO, MemberTagDO.class);
        memberTagMapper.insert(memberTag);

        // 返回
        return memberTag.getId();
    }

    @Override
    public void updateMemberTag(MemberTagSaveReqVO updateReqVO) {
        // 校验存在
        validateMemberTagExists(updateReqVO.getId());
        // 更新
        MemberTagDO updateObj = BeanUtils.toBean(updateReqVO, MemberTagDO.class);
        memberTagMapper.updateById(updateObj);
    }

    @Override
    public void deleteMemberTag(Long id) {
        // 校验存在
        validateMemberTagExists(id);
        // 删除
        memberTagMapper.deleteById(id);
    }

    @Override
        public void deleteMemberTagListByIds(List<Long> ids) {
        // 删除
        memberTagMapper.deleteByIds(ids);
        }


    private void validateMemberTagExists(Long id) {
        if (memberTagMapper.selectById(id) == null) {
            throw exception(MEMBER_TAG_NOT_EXISTS);
        }
    }

    @Override
    public MemberTagDO getMemberTag(Long id) {
        return memberTagMapper.selectById(id);
    }

    @Override
    public PageResult<MemberTagDO> getMemberTagPage(MemberTagPageReqVO pageReqVO) {
        return memberTagMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean importUsers(List<MemberTagImportExcelVO> list, Boolean updateSupport) {
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        for (MemberTagImportExcelVO vo : list) {
            if (vo.getId() != null) {
                MemberTagDO existDO = memberTagMapper.selectById(vo.getId());
                if (existDO != null) {
                    if (Boolean.TRUE.equals(updateSupport)) {
                        // 更新：复制属性，但保护创建信息
                        MemberTagDO updateDO = BeanUtils.toBean(vo, MemberTagDO.class);
                        updateDO.setCreator(null);
                        updateDO.setCreateTime(null);
                        memberTagMapper.updateById(updateDO);
                    } else {
                        // updateSupport = false，跳过该条记录
                        continue;
                    }
                } else {
                    // ID 不存在，按新增处理（忽略用户提供的 ID，由数据库自增）
                    MemberTagDO insertDO = BeanUtils.toBean(vo, MemberTagDO.class);
                    insertDO.setId(null);
                    memberTagMapper.insert(insertDO);
                }
            } else {
                // 无 ID，直接新增
                MemberTagDO insertDO = BeanUtils.toBean(vo, MemberTagDO.class);
                memberTagMapper.insert(insertDO);
            }
        }
        return true;
    }

    @Override
    public void updateTagStatus(List<Long> ids, String status) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 使用 UpdateWrapper 批量更新状态
        UpdateWrapper<MemberTagDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids)
                .set("status", status);
        memberTagMapper.update(null, updateWrapper);
    }

}