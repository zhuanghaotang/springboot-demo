package com.novellatonyatt.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.novellatonyatt.dao.MenuRepository;
import com.novellatonyatt.dao.RoleRepository;
import com.novellatonyatt.dao.UserRepository;
import com.novellatonyatt.model.MenuModel;
import com.novellatonyatt.model.RoleModel;
import com.novellatonyatt.model.UserModel;
import com.novellatonyatt.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 17:38
 * @Description:
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果请求方法是option，则不做校验
        Map<String, Filter> filters = new HashMap<>();
        IgnoreOptionRequestAuthFilter filter = new IgnoreOptionRequestAuthFilter();
        filters.put("authc", filter);
        shiroFilterFactoryBean.setFilters(filters);

        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/user/register", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        securityManager.setSessionManager(new DefaultWebSessionManager());
        return securityManager;
    }

    @Bean
    public MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myRealm;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    static class IgnoreOptionRequestAuthFilter extends PassThruAuthenticationFilter {

        @Override
        public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
            HttpServletRequest req = (HttpServletRequest) request;
            if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
                return true;
            }
            return super.onPreHandle(request, response, mappedValue);
        }
    }

    static class MyRealm extends AuthorizingRealm {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private RoleRepository roleRepository;

        @Autowired
        private MenuRepository menuRepository;

        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
            String username = authenticationToken.getPrincipal().toString();
            UserModel userModel = userRepository.selectOne(new QueryWrapper<UserModel>().eq("username", username));
            if (userModel == null) {
                throw new UnknownAccountException();
            } else {
                return new SimpleAuthenticationInfo(username, userModel.getPassword(), this.getName());
            }
        }

        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
            String username = (String) principalCollection.getPrimaryPrincipal();
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            // 角色
            List<RoleModel> roleList = roleRepository.getRoleListByUsername(username);
            Set<String> enameSet = roleList.stream().map(RoleModel::getEname).collect(Collectors.toSet());
            simpleAuthorizationInfo.setRoles(enameSet);
            // 菜单
            List<MenuModel> menuList = menuRepository.getMenuByUsername(username);
            Set<String> permissionSet = menuList.stream().map(MenuModel::getPermission).collect(Collectors.toSet());
            simpleAuthorizationInfo.setStringPermissions(permissionSet);
            return simpleAuthorizationInfo;
        }
    }

}
