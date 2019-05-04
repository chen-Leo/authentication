package com.example.authentication.controller;


import com.example.authentication.entity.json.UserJsonEntity;
import com.example.authentication.service.LoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;



/**
 * @author hello
 */
@Controller
@RequestMapping(value = "/userlogin")
public class AuthorizeController {

    @Autowired
    LoginInfoService loginInfoService;

    /**
     * 授权的登陆验证接口
     * @param request 需要包含用户账号（userid);密码(password);发请授权登陆的(redirect_url)
     * @return 跳转回发送请求的界面，包含可以拉取用户信息usercode
     * @throws Exception  "密码错误" 和 非法的密码输入" 及 "非法的用户名输入"异常
     */
    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public String testGet(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
        String redirectUri = request.getParameter("redirect_url");
        String userCode = loginInfoService.addUserCode(request);
        String url = "redirect:"+redirectUri;
        redirectAttributes.addAttribute("usercode",userCode);
        return url;
}

    /**
     * 通过usercode拉取用户信息接口
     * @param usercode 用于拉取信息加密后的String
     * @return  UserJsonEntity
     */
    @RequestMapping(value = "/loginByUsercode",method = RequestMethod.GET)
    @ResponseBody
    public UserJsonEntity loginByAuthorize(String usercode)  {
        return loginInfoService.loginByUserCode(usercode);
    }



}
