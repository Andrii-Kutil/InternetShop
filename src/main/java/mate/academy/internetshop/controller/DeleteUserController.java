package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteUserController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(DeleteUserController.class);

    @Inject
    private static BucketService bucketService;

    @Inject
    private static OrderService orderService;

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            Long userId = Long.parseLong(req.getParameter("user_id"));
            User user = userService.get(userId);
            Bucket bucket = bucketService.getByUser(user);
            bucketService.delete(bucket);
            List<Order> userOrders = orderService.getUserOrders(user);
            for (Order order : userOrders) {
                orderService.delete(order);
            }
            userService.delete(user);
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.setAttribute("dpe_msg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/dbError.jsp").forward(req, resp);
        }

        resp.sendRedirect(req.getContextPath() + "/servlet/getAllUsers");
    }
}
