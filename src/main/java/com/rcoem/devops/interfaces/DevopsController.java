package com.rcoem.devops.interfaces;

import com.rcoem.devops.application.DevopsService;
import com.rcoem.devops.model.BuildInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Controller
@RequestMapping("/devops")
public class DevopsController {

    @Value("${source.path}")
    private String path;

    @Value("${environment}")
    private String env;

    @Value("${build.number:local}")
    private String buildNumber;

    @Autowired
    DevopsService devopsService;

    @GetMapping("/oracle")
    @ResponseBody
    public ResponseEntity<String> getOracle() {
        String[] fortunes = {
                "You will pass this lab!",
                "Success is in your Jenkinsfile.",
                "Error 404: Fortune not found."
        };
        int random = (int) (Math.random() * fortunes.length);
        return ResponseEntity.ok("THE ORACLE SAYS: " + fortunes[random]);
    }

    @GetMapping("/health")
    @ResponseBody
    public ResponseEntity<String> getHealth() {
        return ResponseEntity.ok("Online");
    }

    @GetMapping("/env-path")
    @ResponseBody
    public String getPath() {
        return devopsService.getPath();
    }

    @GetMapping("/build-info")
    @ResponseBody
    public ResponseEntity<BuildInfo> getBuildInfo() {
        String deployedAt = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        return ResponseEntity.ok(
                new BuildInfo(buildNumber, env, deployedAt, "Running")
        );
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        return buildDashboardModel(model);
    }

    private static final Set<String> SPA_SLUGS = Set.of(
            "dashboard", "endpoints", "pipeline",
            "environment", "build-info", "health",
            "oracle"
    );

    @GetMapping("/{section}")
    public String spaShell(@PathVariable String section, Model model) {
        if (!SPA_SLUGS.contains(section)) {
            return "redirect:/devops/";
        }
        return buildDashboardModel(model);
    }

    private String buildDashboardModel(Model model) {
        model.addAttribute("env", env);
        model.addAttribute("buildNumber", buildNumber);
        model.addAttribute("path", path);
        model.addAttribute("deployedAt",
                LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        return "index";
    }
}