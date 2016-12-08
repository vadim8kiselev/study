package com.kiselev.ejb.controller;

import com.kiselev.ejb.dao.DAOLocal;
import com.kiselev.ejb.model.entities.EntityBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class IndexServlet extends HttpServlet {

    @EJB
    private DAOLocal<EntityBean> dao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            dao.delete(Long.parseLong(req.getParameter("id")));
        } else {
            dao.add(new EntityBean(req.getParameter("username"), req.getParameter("name")));
        }
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EntityBean> list = dao.getObjects();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/index").forward(req, resp);
    }
}
