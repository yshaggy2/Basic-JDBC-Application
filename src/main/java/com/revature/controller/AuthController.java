package com.revature.controller;

import com.revature.model.LoginDTO;
import com.revature.service.AuthService;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import jakarta.servlet.http.HttpSession;

import static org.eclipse.jetty.http.HttpStatus.Code.TEMPORARY_REDIRECT;

public class AuthController {
    AuthService as = new AuthService();
    //Register And Login
    public static HttpSession ses = null;

    public Handler beforeHandler = ctx -> {
        if (ses == null) {
            if (ctx.path().equals("/login") || ctx.path().equals("/register")) return;
            else {
                ctx.status(401).result("Unauthorized");
                ctx.result("Please Login.");
                ctx.skipRemainingHandlers();
            }
        } else {
            System.out.println("Authorization successful.");  // Proceed to the next handler if authenticated
        }
    };
    public Handler loginHandler = ctx -> {
        LoginDTO lDTO = ctx.bodyAsClass(LoginDTO.class);
        String message = as.login(lDTO);
        if (message.contains("successful")) {
            ses = ctx.req().getSession();
            ses.setAttribute("user", lDTO.getUsername());
            ctx.sessionAttribute("user", lDTO.getUsername());
            ctx.status(200);
        } else {
            ctx.status(400);
        }
        ctx.result(message);
    };
    public Handler registerHandler = ctx -> {
        LoginDTO lDTO = ctx.bodyAsClass(LoginDTO.class);
        String message = as.register(lDTO);
        if (message.contains("successful")) {
            ctx.status(202);
        } else {
            ctx.status(400);
        }
        ctx.result(message);
    };
    public Handler logoutHandler = ctx -> {
        ctx.result("User Logged out");
      ctx.sessionAttribute("user", null);
      ses.setAttribute("user", null);
      ses.invalidate();
      ses = null;
    };
}
