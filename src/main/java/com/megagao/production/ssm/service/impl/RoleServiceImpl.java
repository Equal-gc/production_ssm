package com.megagao.production.ssm.service.impl;

import java.util.List;

import com.megagao.production.ssm.domain.authority.SysUserRole;
import com.megagao.production.ssm.domain.authority.SysUserRoleExample;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.vo.RoleVO;
import com.megagao.production.ssm.mapper.authority.SysRoleMapper;
import com.megagao.production.ssm.service.RoleService;
import com.megagao.production.ssm.domain.authority.SysRoleExample;
import com.megagao.production.ssm.domain.authority.SysRolePermission;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.authority.SysRole;
import com.megagao.production.ssm.mapper.authority.SysRolePermissionMapper;
import com.megagao.production.ssm.mapper.authority.SysUserRoleMapper;
import com.megagao.production.ssm.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.megagao.production.ssm.util.*;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
    SysRoleMapper sysRoleMapper;

	@Autowired
	SysUserRoleMapper sysUserRoleMapper;

	@Autowired
	SysRolePermissionMapper sysRolePermissionMapper;

	@Override
	public EUDataGridResult getList(int page, int rows, RoleVO sysRole) throws Exception{
		//查询列表
		SysRoleExample example = new SysRoleExample();
		//由原来的0条件新增到1条件，created by zdq
		SysRoleExample.Criteria criteria = example.createCriteria();
		String cid=(String)SessionUtil.getSessionAttribute("company_id");
		//System.out.println("4444444444444444444444444444444444"+cid);
		criteria.andCompanyIdEqualTo(cid);

		//分页处理
		PageHelper.startPage(page, rows);
		List<RoleVO> list = sysRoleMapper.selectByExample(example);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<RoleVO> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}


	@Override
	public List<RoleVO> findByRoleNameAndId(String rolename, String id) throws Exception{
		SysRoleExample example = new SysRoleExample();
		SysRoleExample.Criteria criteria = example.createCriteria();
		criteria.andRoleNameEqualTo(rolename);
		if(id != null){
			criteria.andRoleIdNotEqualTo(id);
		}
		List<RoleVO> sysRoleList = sysRoleMapper.selectByExample(example);
		return sysRoleList;
	}


	@Override
	public RoleVO get(String string) throws Exception{
		return sysRoleMapper.selectByPrimaryKey(string);
	}


	@Override
	public RoleVO findRoleByUserId(String userId) throws Exception{
		SysUserRoleExample example = new SysUserRoleExample();
		SysUserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andSysUserIdEqualTo(userId);
		SysUserRole sysUserRole = sysUserRoleMapper.selectByExample(example).get(0);
		RoleVO sysRole = sysRoleMapper.selectByPrimaryKey(sysUserRole.getSysRoleId());
		return sysRole;
	}

	@Override
	public CustomResult delete(String string) throws Exception{
		int i = sysRoleMapper.deleteByPrimaryKey(string);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

	@Override
	public CustomResult deleteBatch(String[] ids) throws Exception{
		int i = sysRoleMapper.deleteBatch(ids);
		if(i>=0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

	@Override
	public CustomResult insert(SysRole role) throws Exception{
		//在业务层整合
		SysRolePermission sysRolePermission = new SysRolePermission();
		sysRolePermission.setId(IDUtils.genStringId());
		sysRolePermission.setSysRoleId(role.getRoleId());
		sysRolePermission.setSysPermissionId(role.getPermission());
		//存角色权限表
		int k = sysRolePermissionMapper.insertSelective(sysRolePermission);
		//给role添加公司id,created by zdq
		role.setCompanyId((String)SessionUtil.getSessionAttribute("company_id"));
		int i = sysRoleMapper.insert(role);
		if(i>0 && k>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "新增角色信息失败");
		}
	}

	@Override
	public CustomResult update(SysRole role) throws Exception{
		int i = sysRoleMapper.updateByPrimaryKeySelective(role);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "修改角色信息失败");
		}
	}

	@Override
	public CustomResult updateAll(SysRole role) throws Exception{
		//在业务层整合处理
		SysRolePermission sysRolePermission = new SysRolePermission();
		sysRolePermission.setSysRoleId(role.getRoleId());
		sysRolePermission.setSysPermissionId(role.getPermission());
		//修改角色权限表
		int k = sysRolePermissionMapper.updateRolePermission(sysRolePermission);

		int i = sysRoleMapper.updateByPrimaryKey(role);
		if(i>0 && k>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "修改角色信息失败");
		}
	}

	@Override
	public List<RoleVO> find() throws Exception{
		SysRoleExample example = new SysRoleExample();
		return sysRoleMapper.selectByExample(example);
	}


	@Override
	public List<RoleVO> searchSysRoleBySysRoleName(String sysRoleName) throws Exception{
		SysRoleExample example = new SysRoleExample();
		SysRoleExample.Criteria criteria = example.createCriteria();
		criteria.andRoleNameLike(sysRoleName);
		return sysRoleMapper.selectByExample(example);
	}

	@Override
	public List<RoleVO> searchSysRoleBySysRoleId(String sysRoleId) throws Exception{
		SysRoleExample example = new SysRoleExample();
		SysRoleExample.Criteria criteria = example.createCriteria();
		criteria.andRoleIdLike(sysRoleId);
		return sysRoleMapper.selectByExample(example);
	}


	@Override
	public EUDataGridResult searchRoleByRoleId(Integer page, Integer rows,
			String roleId) throws Exception{
		//分页处理
		PageHelper.startPage(page, rows);
		//此处将搜索条件增加至两个，created by zdq
		String companyId=(String)SessionUtil.getSessionAttribute("company_id");
		List<RoleVO> list = sysRoleMapper.searchRoleByRoleId(roleId,companyId);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<RoleVO> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}


	@Override
	public EUDataGridResult searchRoleByRoleName(Integer page, Integer rows,
			String roleName) throws Exception{
		//分页处理
		PageHelper.startPage(page, rows);
		//此处将搜索条件增加至两个，created by zdq
		String companyId=(String)SessionUtil.getSessionAttribute("company_id");
		List<RoleVO> list = sysRoleMapper.searchRoleByRoleName(roleName,companyId);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<RoleVO> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
}
