/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.TableRow;
import utils.geometry.*;



@WebServlet(name = "AreaCheckServlet", value = "/check")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double x;
        double y;
        double r;

        System.out.println("in area");

        try {
            x = Double.parseDouble(req.getParameter("coordinateX"));
            y = Double.parseDouble(req.getParameter("coordinateY").replace(',', '.'));
            r = Double.parseDouble(req.getParameter("radius"));

            checkR(r);
            checkX(x, r);
            checkY(y, r);

            addRowToSessionTable(req, createTableRow(x, y, r));

            System.out.println("im here");
            getServletContext().getRequestDispatcher("/table.jsp").forward(req, resp);
        } catch (NumberFormatException e){
            getServletContext().getRequestDispatcher("/main.jsp").forward(req, resp);
        }
    }

    protected void checkX(double x, double r) {
        if (x < -10*r || 10*r < x) {
            throw new NumberFormatException();
        }
    }

    protected void checkY(double y, double r) {
        if (y < -10*r || 10*r < y) {
            throw new NumberFormatException();
        }
    }

    protected void checkR(double r) {
        if (r < 1 || r > 5) {
            throw new NumberFormatException();
        }
    }

    protected TableRow createTableRow(double x, double y, double r) {
        Figure fig = getFigure(r);
        return new TableRow(x, y, r, fig.checkPointInArea(new Point(x, y)));
    }

    protected void addRowToSessionTable(HttpServletRequest req, TableRow row) {
        List<TableRow> table;
        if (req.getSession().getAttribute("table") != null) {
            table = (List<TableRow>) req.getSession().getAttribute("table");
        } else {
            table = new ArrayList<>();
        }
        table.add(row);
        req.getSession().setAttribute("table", table);
    }

    protected Figure getFigure(double r) {
        return new CompoundFigure(
                new Circle(
                        new Point(0,0), r, Math.PI, Math.PI*3/2
                ),
                new Triangle(
                        new Point(0, 0),
                        new Point(0, r/2),
                        new Point(-r, 0)
                ),
                new Rectangle(
                        new Point(0, 0),
                        new Point(r, r/2)
                )
        );

    }
}
