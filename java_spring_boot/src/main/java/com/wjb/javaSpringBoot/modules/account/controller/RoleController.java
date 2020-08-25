package com.wjb.javaSpringBoot.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.wjb.javaSpringBoot.modules.account.entity.Role;
import com.wjb.javaSpringBoot.modules.account.service.RoleService;
import com.wjb.javaSpringBoot.modules.common.vo.Result;
import com.wjb.javaSpringBoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ASUS on 2020/8/23 00:12
 */
@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 127.0.0.1/api/roles ---- get
     */
    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    @PostMapping(value = "/roles", consumes = "application/json")
    public PageInfo<Role> getRoles(@RequestBody SearchVo searchVo) {
        return roleService.getRoles(searchVo);
    }

    @PostMapping(value = "/role", consumes = "application/json")
    public Result<Role> insertRole(@RequestBody Role role) {
        return roleService.editRole(role);
    }

    @PutMapping(value = "/role", consumes = "application/json")
    public Result<Role> updateRole(@RequestBody Role role) {
        return roleService.editRole(role);
    }

    @RequestMapping("/role/{roleId}")
    public Role getRole(@PathVariable int roleId) {
        return roleService.getRoleById(roleId);
    }

    @DeleteMapping("/role/{roleId}")
    public Result<Role> deletRole(@PathVariable int roleId) {
        return roleService.deleteRole(roleId);
    }
}
