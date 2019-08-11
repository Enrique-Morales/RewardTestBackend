package net.app.factory;

import javax.servlet.http.HttpServletRequest;

import net.app.bean.ReplyBean;
import net.app.service.ReviewService;
import net.app.service.ReviewlistService;
import net.app.service.UserService;
import net.app.service.UsertypeService;


public class ServiceFactory {

    public static ReplyBean executeService(HttpServletRequest oRequest) throws Exception {

        String ob = oRequest.getParameter("ob");
        String op = oRequest.getParameter("op");
        ReplyBean oReplyBean = null;

        switch (ob) {
            case "usertype":
                UsertypeService oUsertypeService = new UsertypeService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oUsertypeService.get();
                        break;
                    case "create":
                        oReplyBean = oUsertypeService.create();
                        break;
                    case "update":
                        oReplyBean = oUsertypeService.update();
                        break;
                    case "remove":
                        oReplyBean = oUsertypeService.remove();
                        break;
                    case "getcount":
                        oReplyBean = oUsertypeService.getcount();
                        break;
                    case "getpage":
                        oReplyBean = oUsertypeService.getpage();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                        break;
                }
                break;
            case "user":
                UserService oUserService = new UserService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oUserService.get();
                        break;
                    case "create":
                        oReplyBean = oUserService.create();
                        break;
                    case "update":
                        oReplyBean = oUserService.update();
                        break;
                    case "remove":
                        oReplyBean = oUserService.remove();
                        break;
                    case "getcount":
                        oReplyBean = oUserService.getcount();
                        break;
                    case "getpage":
                        oReplyBean = oUserService.getpage();
                        break;
                    case "login":
                        oReplyBean = oUserService.login();
                        break;
                    case "logout":
                        oReplyBean = oUserService.logout();
                        break;
                    case "check":
                        oReplyBean = oUserService.check();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                        break;
                }
                break;
            case "reviewlist":
                ReviewlistService oReviewlistService = new ReviewlistService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oReviewlistService.get();
                        break;
                    case "create":
                        oReplyBean = oReviewlistService.create();
                        break;
                    case "update":
                        oReplyBean = oReviewlistService.update();
                        break;
                    case "remove":
                        oReplyBean = oReviewlistService.remove();
                        break;
                    case "getcount":
                        oReplyBean = oReviewlistService.getcount();
                        break;
                    case "getpage":
                        oReplyBean = oReviewlistService.getpage();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                        break;
                }
                break;
            case "review":
                ReviewService oReviewService = new ReviewService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oReviewService.get();
                        break;
                    case "create":
                        oReplyBean = oReviewService.create();
                        break;
                    case "update":
                        oReplyBean = oReviewService.update();
                        break;
                    case "remove":
                        oReplyBean = oReviewService.remove();
                        break;
                    case "getcount":
                        oReplyBean = oReviewService.getcount();
                        break;
                    case "getpage":
                        oReplyBean = oReviewService.getpage();
                        break;
                    case "getpagebyuser":
                        oReplyBean = oReviewService.getpageByUser();
                        break;
                    case "getnonsubmitted":
                        oReplyBean = oReviewService.getNonSubmittedByUser();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                        break;
                }
                break;
            default:
                oReplyBean = new ReplyBean(500, "Object doesn't exist");
                break;
        }
        return oReplyBean;
    }

}
