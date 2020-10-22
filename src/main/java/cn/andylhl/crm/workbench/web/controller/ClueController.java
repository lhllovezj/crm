package cn.andylhl.crm.workbench.web.controller;

import cn.andylhl.crm.exception.ClueExecption;
import cn.andylhl.crm.settings.domain.User;
import cn.andylhl.crm.settings.service.UserService;
import cn.andylhl.crm.utils.*;
import cn.andylhl.crm.workbench.domain.Clue;
import cn.andylhl.crm.workbench.service.ActivityService;
import cn.andylhl.crm.workbench.service.ClueService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/***
 * @Title: ClueController
 * @Description: 线索控制器
 * @author: lhl
 * @date: 2020/10/21 19:32
 */
@WebServlet(urlPatterns = {"/workbench/clue/getUserList.do", "/workbench/clue/save.do"})
public class ClueController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*使用模板方设计模式，算法骨架*/
        System.out.println("进入到ClueController");

        String path = request.getServletPath();
        if ("/workbench/clue/getUserList.do".equals(path)) {
            getUserList(request, response);
        }
        else if ("/workbench/clue/save.do".equals(path)){
            save(request, response);
        }
        else {
            System.out.println("无效访问地址");
        }
    }

    /**
     * 保存线索对象
     * @param request
     * @param response
     */
    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行保存线索对象操作");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        ClueService service = (ClueService) ac.getBean("clueServiceImpl");
        String createBy = ((User) request.getSession().getAttribute("user")).getName();
        //获取参数
        Clue clue = new Clue();
        WebUtil.makeRequestToObject(request,clue);
        clue.setId(UUIDUtil.getUUID());
        clue.setCreateBy(createBy);
        clue.setCreateTime(DateUtil.format(new Date(), Const.DATE_Format_ALL));
        try {
            service.save(clue);
            PrintJson.printJsonFlag(response, true);
        } catch (Exception e) {
            PrintJson.printJsonFlag(response, false);
            e.printStackTrace();
        }
    }

    /**
     * 获取用户列表
     * @param request
     * @param response
     */
    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("获取用户列表");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        UserService service = (UserService) ac.getBean("userServiceImpl");
        List<User> userList = service.getUserList();
        PrintJson.printJsonObj(response, userList);
    }
}