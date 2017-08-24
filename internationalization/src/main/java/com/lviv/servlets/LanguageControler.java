package com.lviv.servlets;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;

import com.lviv.util.LanguageUtil;

@WebServlet("/lang")
public class LanguageControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String language = request.getParameter("language");

		if (language == null) {
			LanguageUtil.setDefaultLocale(request.getSession());
		}
		switch (language) {
		case "uk":
			Config.set(request.getSession(), Config.FMT_LOCALIZATION_CONTEXT,
					new LocalizationContext(ResourceBundle.getBundle("/message/message", new Locale("uk", "UA"))));
			break;

		case "en":
			Config.set(request.getSession(), Config.FMT_LOCALIZATION_CONTEXT,
					new LocalizationContext(ResourceBundle.getBundle("/message/message", new Locale("en", "US"))));
			break;

		default:
			LanguageUtil.setDefaultLocale(request.getSession());
			break;
		}

		request.getRequestDispatcher("/language.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}

}
