package SpringProject.web.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class DashboardController {
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";
    }
}